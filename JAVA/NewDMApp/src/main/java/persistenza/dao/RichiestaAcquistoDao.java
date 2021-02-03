package persistenza.dao;

import business.fornitura.RichiestaAcquisto;
import persistenza.DatabaseConnection;
import persistenza.dao.Query;
import exceptions.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DAO per il salvataggio persistente di una richiesta di acquisto
 */
public class RichiestaAcquistoDao {

  /**
   * Salva nel persistenza le informazioni riguardo una RichiestaAcquisto
   *
   * @param r La Richiesta da salvare
   * @throws DatabaseException Errore nel persistenza
   */
  public static void save(RichiestaAcquisto r) throws DatabaseException {
    try {
      PreparedStatement prep =
          DatabaseConnection.getInstance()
              .getCon()
              .prepareStatement(Query.saveRichiesta, Statement.RETURN_GENERATED_KEYS);
      prep.setInt(1, r.getQuantity());
      prep.setString(2, r.getData().substring(0, 10));
      prep.setString(3, r.getData().substring(11, 19));
      prep.setString(4, r.getStato());
      prep.setLong(5, r.getCodice());
      prep.executeUpdate();
      ResultSet rs = prep.getGeneratedKeys();
      rs.next();
      r.setId(rs.getLong(1));

    } catch (SQLException e) {
      e.printStackTrace();
      throw new DatabaseException("Errore nel salvataggio della richiesta d'acquisto");
    }
  }
}
