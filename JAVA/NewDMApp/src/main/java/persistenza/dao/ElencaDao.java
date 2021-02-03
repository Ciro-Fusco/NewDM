package persistenza.dao;

import business.cassa.Scontrino;
import persistenza.DatabaseConnection;
import exceptions.ProdottoException;
import business.inventario.Prodotto;
import exceptions.DatabaseException;
import exceptions.ElencaException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO per la persistenza della relazione tra un codice di uno scontrino e i codici dei prodotti a cui
 * si riferisce
 */
public class ElencaDao {
  /**
   * Salva nel persistenza la relazione tra un codice di uno scontrino e i codici dei prodotti a cui
   * si riferisce
   *
   * @param s Lo Scontrino da cui ricavare le relazioni
   * @throws DatabaseException Errore del Database
   */
  public static void save(Scontrino s) throws DatabaseException, ProdottoException {

    List<Prodotto> l = s.getList();
    for (Prodotto c : l) {
      try {
        PreparedStatement state =
            DatabaseConnection.getInstance().getCon().prepareStatement(Query.elenca);
        state.setLong(1, s.getId());
        state.setString(2, s.getData().substring(0, 10));
        state.setLong(3, c.getCodice());
        state.setInt(4, c.getAcquistato());
        state.executeUpdate();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
        throw new DatabaseException("Errore non fatale nel Database.");
      }
      c.leavedbquantity();
    }
  }

  /**
   * Controlla se nel persistenza alla tabella Elenca esiste una riga contenente codice e data dello
   * Scontrino e codice Prodotto così come passato per parametro
   *
   * @param codiceScontrino il codice dello Scontrino
   * @param dataScontrino la data dello Scontrino
   * @param codiceProdotto il codice del Prodotto
   * @throws DatabaseException Errore del Database
   * @throws ElencaException Relazione non trovata
   */
  public static void checkCorrispondenza(
      long codiceScontrino, String dataScontrino, long codiceProdotto)
      throws DatabaseException, ElencaException {

    try {
      PreparedStatement state =
          DatabaseConnection.getInstance().getCon().prepareStatement(Query.elencaCheck);
      state.setLong(1, codiceScontrino);
      state.setString(2, dataScontrino);
      state.setLong(3, codiceProdotto);
      ResultSet rs = state.executeQuery();
      if (!rs.next()) {
        throw new ElencaException("Prodotto non correlato allo Scontrino immesso");
      } else {
        int i = 1;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
      throw new DatabaseException("Errore nel Database.");
    }
  }
}
