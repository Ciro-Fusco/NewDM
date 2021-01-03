package db;

import entity.Prodotto;
import entity.Scontrino;
import exceptions.DatabaseException;
import exceptions.ElencaException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ElencaDao {
  public static void save(Scontrino s) throws DatabaseException {

    List<Prodotto> l = s.getList();
    for (Prodotto c : l) {
      try {
        PreparedStatement state = DatabaseConnection.con.prepareStatement(Query.elenca);
        state.setInt(1, s.getId());
        state.setString(2, s.getData());
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

  public static void checkCorrispondenza(
      int codiceScontrino, String dataScontrino, long codiceProdotto)
      throws DatabaseException, ElencaException {

    try {
      PreparedStatement state = DatabaseConnection.con.prepareStatement(Query.elencaCheck);
      state.setInt(1, codiceScontrino);
      state.setString(2, dataScontrino);
      state.setLong(3, codiceProdotto);
      ResultSet rs = state.executeQuery();
      if (!rs.next()) {
        throw new ElencaException("Prodotto non correlato allo Scontrino immesso");
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
      throw new DatabaseException("Errore nel Database.");
    }
  }
}
