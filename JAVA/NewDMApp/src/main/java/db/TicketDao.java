package db;

import entity.Scontrino;
import entity.Ticket;
import exceptions.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TicketDao {

    public static void save(Ticket t) throws DatabaseException {
        try {
            PreparedStatement prep = DatabaseConnection.con.prepareStatement(Query.newTicket);
            prep.setString(1, t.getNomeCognome());
            prep.setString(2, t.getCf());
            prep.setString(3, t.getIndirizzo());
            prep.setLong(4, t.getNumTel());
            prep.setString(5, t.getTipo());
            prep.setString(6, t.getNomeProdotto());
            prep.setLong(6, t.getCodiceProdotto());
            prep.setString(7, t.getNumeroDiSerie());
            prep.setLong(8, t.getCodiceScontrino());
            prep.setString(9,t.getDataScontrino());
            prep.setString(10,t.getProblema());
            prep.setString(11,t.getDataApertura());
            prep.setString(12, t.getStato());
            prep.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Errore nel salvataggio dello Scontrino");
        }
    }
}
