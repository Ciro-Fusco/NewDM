package persistenza.dao;

import business.cassa.Scontrino;
import exceptions.DatabaseException;
import exceptions.ProdottoException;
import exceptions.ScontrinoNonValidoException;
import exceptions.ScontrinoNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import persistenza.DatabaseConnection;

/**
 * DAO per il salvataggio persistente di uno Scontrino
 */
public class ScontrinoDao {

  /**
   * Salva le informazioni dello scontrino nel Database e crea le voci della tabella ELENCA
   *
   * @param s Scontrino da salvare
   * @throws DatabaseException Errore del Database
   */
  public static void save(Scontrino s) throws DatabaseException {

    try {
      PreparedStatement prep =
              DatabaseConnection.getInstance()
                      .getCon()
                      .prepareStatement(Query.newScontrino, Statement.RETURN_GENERATED_KEYS);
      prep.setString(1, s.getData().substring(0, 10));
      prep.setString(2, s.getData().substring(11, 19));
      prep.setDouble(3, s.getVersato());
      prep.setDouble(4, s.getTot());
      prep.setDouble(5, s.getResto());
      prep.executeUpdate();
      ResultSet rs = prep.getGeneratedKeys();
      rs.next();
      s.setId(rs.getInt(1));

      ElencaDao.save(s);

    } catch (SQLException | ProdottoException e) {
      e.printStackTrace();
      throw new DatabaseException("Errore nel salvataggio dello Scontrino");
    }
  }

  /**
   * Cerca una stringa nel persistenza alla tabella Scontrino contenente codice e data dello
   * scontrino
   *
   * @param codice        il codice dello scontrino
   * @param dataScontrino la data dello scontrino
   * @throws ScontrinoNotFoundException  Scontrino non trovato
   * @throws ScontrinoNonValidoException Lo Scontrino non è valido ai fini della garanzia
   * @throws DatabaseException           Errore del persistenza
   */
  public static void checkScontrino(long codice, String dataScontrino)
          throws ScontrinoNonValidoException, DatabaseException, ScontrinoNotFoundException {
    try {
      PreparedStatement prep =
              DatabaseConnection.getInstance().getCon().prepareStatement(Query.checkScontrino);
      prep.setLong(1, codice);
      prep.setString(2, dataScontrino);

      ResultSet res = prep.executeQuery();
      if (!res.next()) {
        throw new ScontrinoNotFoundException("Scontrino non trovato\nControlla il codice");
      } else {
        String data_temp = res.getString("data");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime data_obj = LocalDate.parse(data_temp, formatter).atStartOfDay();
        LocalDateTime data_2_years_ago = LocalDateTime.now().minusYears(2);
        if (data_obj.isBefore(data_2_years_ago))
          throw new ScontrinoNonValidoException(
                  "Inserire una data valida, non precedente a 2 anni fa e non successiva alla data odierna");
        else {
          System.out.println();
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DatabaseException("Errore generico\nRiprova tra qualche secondo");
    }
  }
}
