package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.DatabaseException;
import exceptions.UtenteNotFoundException;
import org.apache.commons.codec.digest.DigestUtils;
import static entity.Utente.setUtente;

public class UtenteDao {

  /**
   * Cerca nel Database l'esistenza di un utente e lo autentica nel sistema.
   *
   * @param user Nome utente
   * @param pass Password non ancora codificata
   * @return true -- se l'utente è stato autenticato; false -- altrimenti.
   * @throws DatabaseException        Errore generico;
   * @throws UtenteNotFoundException Utente non trovato nel Database;
   */
  public static boolean login(String user, String pass) throws DatabaseException, UtenteNotFoundException {
    try {


      PreparedStatement prep = DatabaseConnection.con.prepareStatement(Query.login);
      prep.setString(1, user);
      String shapass = DigestUtils.sha1Hex(pass);
      prep.setString(2, shapass);
      ResultSet res = prep.executeQuery();
      if (res.next()) {
        setUtente(res.getString("NOME"), res.getString("COGNOME"), res.getString("USERNAME"));
        return true;
      }
      throw new UtenteNotFoundException("Utente non trovato\nControlla username e password");
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DatabaseException("Errore generico");
    }
  }

}
