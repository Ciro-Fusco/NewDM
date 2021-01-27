package cassa;

import database.DatabaseConnection;
import database.Query;
import magazzino.Prodotto;
import exceptions.DatabaseException;
import exceptions.ElencaException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ElencaDao {
  /**
   * Salva nel database la relazione tra uno Scontrino e i Prodotti a cui si riferisce
   * @param s Lo Scontrino da cui ricavare le relazioni
   * @throws DatabaseException Errore del Database
   */
  public static void save(Scontrino s) throws DatabaseException {

    List<Prodotto> l = s.getList();
    for (Prodotto c : l) {
      try {
        PreparedStatement state = DatabaseConnection.con.prepareStatement(Query.elenca);
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
   * Controlla se ad uno Scontrino corrisponde
   * @param codiceScontrino
   * @param dataScontrino
   * @param codiceProdotto
   * @throws DatabaseException
   * @throws ElencaException
   */
  public static void checkCorrispondenza(
      long codiceScontrino, String dataScontrino, long codiceProdotto)
      throws DatabaseException, ElencaException {

    try {
      PreparedStatement state = DatabaseConnection.con.prepareStatement(Query.elencaCheck);
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
