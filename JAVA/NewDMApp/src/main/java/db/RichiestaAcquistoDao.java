package db;

import entity.RichiestaAcquisto;
import entity.Scontrino;
import exceptions.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RichiestaAcquistoDao {

  public static void save(RichiestaAcquisto r) throws DatabaseException {
    try {
      PreparedStatement prep =
              DatabaseConnection.con.prepareStatement(Query.saveRichiesta, Statement.RETURN_GENERATED_KEYS);
      prep.setInt(1, r.getQuantity());
      prep.setString(2, r.getData());
      prep.setString(3, r.getStato());
      prep.setLong(4, r.getCodice());
      prep.executeUpdate();
      ResultSet rs = prep.getGeneratedKeys();
      rs.next();
      r.setId(rs.getLong(1));

    } catch (SQLException e) {
      e.printStackTrace();
      throw new DatabaseException("Errore nel salvataggio della richiesta d'acquisto");
    }
  }

}
