package db;

import entity.Prodotto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdottoDao {

  /**
   * Cerca un prodotto nel Database dato il codice
   *
   * @param cod codice del prodotto
   * @return un nuovo Prodotto contenente i dati della query
   */
  public static Prodotto search(Long cod) {

    PreparedStatement prep = null;
    try {
      prep = DatabaseConnection.con.prepareStatement(Query.prodotto);
      prep.setLong(1, cod);
      ResultSet res = prep.executeQuery();

      if (res.next()) {
        return new Prodotto(
            res.getDouble("Prezzo"),
            res.getLong("Codice"),
            res.getString("Nome"),
            res.getInt("quantità"));
      }
      return null;

    } catch (SQLException throwables) {
      throwables.printStackTrace();
      return null;
    }
  }

  /**
   * Aggiorna la quantità del prodotto nel DB
   *
   * @param p prodotto da aggiornare
   * @return true se la quantità del prodotto è stata aggiornata correttamente, false altrimenti
   */
  public static boolean updatedbquantity(Prodotto p) {

    PreparedStatement prep = null;
    try {
      prep = DatabaseConnection.con.prepareStatement(Query.upDBQuant);
      prep.setInt(1, p.getAcquistato());
      prep.setLong(2, p.getCodice());
      prep.executeQuery();
      return true;

    } catch (SQLException throwables) {
      throwables.printStackTrace();
      return false;
    }
  }

  public static boolean createProdotto(Prodotto p) {
    PreparedStatement prep = null;
    try {
      prep = DatabaseConnection.con.prepareStatement(Query.newProdotto);
      prep.setLong(1, p.getCodice());
      prep.setString(2, p.getNome());
      prep.setInt(3, p.getQuantity());
      prep.setDouble(4, p.getPrezzo());
      prep.executeQuery();
      return true;

    } catch (SQLException throwables) {
      throwables.printStackTrace();
      return false;
    }
  }
}
