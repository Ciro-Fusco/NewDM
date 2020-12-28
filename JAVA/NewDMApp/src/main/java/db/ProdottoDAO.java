package db;

import Entity.Prodotto;
import Exceptions.ProdottoNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdottoDAO {


    /**
     *Cerca un prodotto nel Database dato il codice
     * @param cod codice del prodotto
     * @return un nuovo Prodotto contenente i dati della query
     */
    public static Prodotto search(Long cod) {

        PreparedStatement prep = null;
        try {
            prep = DatabaseConnection.con.prepareStatement(query.prodotto);
            prep.setLong(1,cod);
            ResultSet res = prep.executeQuery();

            if(res.next()){
                return new Prodotto(res.getDouble("Prezzo"),res.getLong("Codice"),
                        res.getString("Nome"),res.getInt("quantità"));
            }
            return null;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }


    }

    /**
     * Aggiorna la quantità del prodotto nel DB
     * @param p prodotto da aggiornare
     * @return true se la quantità del prodotto è stata aggiornata correttamente, false altrimenti
     */

    public static boolean updateDBQuantity(Prodotto p) {

        PreparedStatement prep = null;
        try {
            prep = DatabaseConnection.con.prepareStatement(query.upDBQuant);
            prep.setInt(1,p.getAcquistato());
            prep.setLong(2,p.getCodice());
            prep.executeQuery();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }



}
