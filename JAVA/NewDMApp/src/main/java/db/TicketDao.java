package db;

import entity.Scontrino;
import entity.Ticket;
import exceptions.DatabaseException;
import exceptions.TicketNotFoundException;

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
      prep.setLong(7, t.getCodiceProdotto());
      prep.setString(8, t.getNumeroDiSerie());
      prep.setLong(9, t.getCodiceScontrino());
      prep.setString(10, t.getDataScontrino());
      prep.setString(11, t.getProblema());
      prep.setString(12, t.getDataApertura());
      prep.setString(13, t.getStato());
      prep.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
      throw new DatabaseException("Errore nel salvataggio dello Scontrino");
    }
  }

  public static Ticket getTicket(String apertura, String CF, Long serie) throws TicketNotFoundException, DatabaseException {
    try {
      PreparedStatement prep = DatabaseConnection.con.prepareStatement(Query.getTicket);
      prep.setString(1, apertura);
      prep.setString(2, CF);
      prep.setLong(3, serie);
      ResultSet res = prep.executeQuery();
      if (!res.next()) {
        throw new TicketNotFoundException("Ticket non trovato. Controllare i campi");
      } else {
        Ticket t = new Ticket();
        t.setNomeCognome(res.getString("NOME_CLIENTE"));
        t.setCf(res.getString("CODICE_FISCALE"));
        t.setIndirizzo(res.getString("INDIRIZZO"));
        t.setTipo(res.getString("TIPO"));
        t.setNomeProdotto(res.getString("NOME_PRODOTTO"));
        t.setNumeroDiSerie(res.getString("NUMERO_DI_SERIE"));
        t.setNumTel(res.getLong("NUM_TEL"));
        t.setCodiceScontrino(res.getInt("SCONTRINO"));
        t.setDataApertura(res.getString("DATA_APERTURA"));
        t.setCodiceProdotto(res.getLong("PRODOTTO"));
        t.setStato(res.getString("STATO"));
        return t;
      }

    } catch (SQLException e) {
      e.printStackTrace();
      throw new DatabaseException("Errore nella ricerca del Ticket");
    }


  }
}
