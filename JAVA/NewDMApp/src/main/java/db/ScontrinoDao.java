package db;

import entity.Prodotto;
import entity.Scontrino;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ScontrinoDao {

  /**
   * Salva lo Scontrino nel Database e crea le voci della tabella ELENCA
   *
   * @param s Scontrino da salvare
   */
  public static void save(Scontrino s) {

    try {
      PreparedStatement prep = DatabaseConnection.con.prepareStatement(Query.scontrino);
      prep.setString(1, s.getData());
      prep.setDouble(2, s.getVersato());
      prep.setDouble(3, s.getTot());
      prep.setDouble(4, s.getResto());
      ResultSet res = prep.executeQuery();
      while (res.next()) {
        s.setId(res.getLong("id"));
      }

      List<Prodotto> l = s.getList();
      l.forEach(
          (c) -> {
            try {
              PreparedStatement state = DatabaseConnection.con.prepareStatement(Query.elenca);
              state.setLong(1, s.getId());
              state.setString(2, s.getData());
              state.setLong(3, c.getCodice());
              state.executeQuery();
            } catch (SQLException throwables) {
              throwables.printStackTrace();
            }
            c.updatedbquantity();
          });
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}