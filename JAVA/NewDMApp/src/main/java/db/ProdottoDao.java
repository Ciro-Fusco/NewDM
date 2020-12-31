package db;

import entity.Prodotto;
import exceptions.DatabaseException;
import exceptions.ProdottoNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdottoDao {

  /**
   * Cerca un prodotto nel Database dato il codice
   *
   * @param cod codice del prodotto
   * @return un nuovo Prodotto contenente i dati della query
   * @throws DatabaseException errore del database:
   * @throws ProdottoNotFoundException prodotto non trovato nel database;
   */
  public static Prodotto search(Long cod) throws DatabaseException, ProdottoNotFoundException {

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
            res.getInt("quantity"));
      }
      throw new ProdottoNotFoundException("Prodotto non trovato");

    } catch (SQLException throwables) {
      throwables.printStackTrace();
      throw new DatabaseException("Errore generico del Database");
    }
  }

  /**
   * Aggiorna la quantità del prodotto nel DB
   *
   * @param p prodotto da aggiornare
   *@throws DatabaseException errore del database:
   */
  public static void leavedbquantity(Prodotto p) throws DatabaseException {

    PreparedStatement prep = null;
    try {
      prep = DatabaseConnection.con.prepareStatement(Query.downDBQuant);
      prep.setInt(1, p.getAcquistato());
      prep.setLong(2, p.getCodice());
      prep.executeUpdate();

    } catch (SQLException throwables) {
      throwables.printStackTrace();
      throw new DatabaseException(
          "Errore nell'aggiornamento della quantità del Prodotto: " + p.getNome());
    }
  }

  /**
   *
   * @param i quantità da aggiungere al Database
   * @param p Prodotto a cui aggiungere la quantitàs
   * @throws DatabaseException Errore del Database
   */
  public static void adddbquantity(int i,Prodotto p) throws DatabaseException{
    PreparedStatement prep = null;
    try {
      prep = DatabaseConnection.con.prepareStatement(Query.upDBQuant);
      prep.setInt(1, i);
      prep.setLong(2, p.getCodice());
      prep.executeUpdate();

    } catch (SQLException throwables) {
      throwables.printStackTrace();
      throw new DatabaseException(
              "Errore nell'aggiornamento della quantità del Prodotto: " + p.getNome());
    }
  }

  public static boolean createProdotto(Prodotto p) throws DatabaseException {
    PreparedStatement prep = null;
    try {
      prep = DatabaseConnection.con.prepareStatement(Query.newProdotto);
      prep.setLong(1, p.getCodice());
      prep.setString(2, p.getNome());
      prep.setInt(3, p.getQuantity());
      prep.setDouble(4, p.getPrezzo());
      prep.executeUpdate();
      return true;

    } catch (SQLException throwables) {
      throwables.printStackTrace();
      throw new DatabaseException("Errore nel salvataggio del Prodotto");
    }
  }

  /**
   * Modifica il prezzo di un prodotto
   * @param p Prodotto da modificare
   * @param prezzo Nuovo prezzo da impostare
   * @throws DatabaseException Errore nel Database
   */
  public static void modificaPrezzo(Prodotto p, double prezzo) throws DatabaseException{
    PreparedStatement prep = null;
    try {
      prep = DatabaseConnection.con.prepareStatement(Query.modificaPrezzo);
      prep.setDouble(1, prezzo);
      prep.setLong(2, p.getCodice());
      prep.executeUpdate();

    } catch (SQLException throwables) {
      throwables.printStackTrace();
      throw new DatabaseException("Errore nel salvataggio del Prodotto");
    }

  }
}
