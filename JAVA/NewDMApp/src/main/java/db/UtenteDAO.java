package db;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Entity.Utente.setUtente;

public class UtenteDAO {

  /**
   * Cerca nel Database l'esistenza di un utente e lo autentica nel sistema.
   *
   * @param user Nome utente
   * @param pass Password non ancora codificata
   * @return true -- se l'utente è stato autenticato; false -- altrimenti.
   * @throws SQLException Errore del Database;
   */
  public static boolean Login(String user, String pass) throws SQLException {

    PreparedStatement prep = DatabaseConnection.con.prepareStatement(query.login);
    prep.setString(1, user);
    String shapass = DigestUtils.sha1Hex(pass);
    prep.setString(2, shapass);
    ResultSet res = prep.executeQuery();
    if (res.next()) {
      setUtente(res.getString("NOME"), res.getString("COGNOME"), res.getString("USERNAME"));
      return true;
    }
    return false;
  }
}
