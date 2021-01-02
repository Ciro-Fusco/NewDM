package db;

import entity.Prodotto;
import entity.Scontrino;
import exceptions.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ElencaDao {
    public static void save(Scontrino s) throws DatabaseException {

        List<Prodotto> l = s.getList();
        for(Prodotto c : l){
            try{
                PreparedStatement state = DatabaseConnection.con.prepareStatement(Query.elenca);
                state.setInt(1, s.getId());
                state.setString(2, s.getData());
                state.setLong(3, c.getCodice());
                state.setInt(4,c.getAcquistato());
                state.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DatabaseException("Errore non fatale nel Database.");
            }
            c.leavedbquantity();
        }
    }
    public static void checkCorrispondenza(long codice_scontrino, String dataScontrino, long codice_prodotto) {
    }
}
