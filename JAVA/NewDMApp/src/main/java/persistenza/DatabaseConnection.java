package persistenza;

import exceptions.DatabaseException;

import javax.xml.crypto.Data;
import java.sql.*;

/**
 * Crea la connessione al database. Singleton.
 */
public class DatabaseConnection {
  private static Connection con = null;
  private static DatabaseConnection instance = null;

  /**
   * Esegue la connessione al Database.
   *
   * @throws DatabaseException Errore del Database
   */
  private static void connect() throws DatabaseException {

    try {
      if ((con == null) || con.isClosed()) {
        // caricamento e registrazione driver
        Class.forName("com.mysql.cj.jdbc.Driver"); // Carica il driver
        String url =
            "jdbc:mysql://localhost:3306/NEGOZIO?"
                + "allowPublicKeyRetrieval=true&&useSSL=false&serverTimezone=UTC";
        String username = "root";
        String pwd = "federernadal";
        con = DriverManager.getConnection(url, username, pwd);
      } else {
        System.out.println();
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DatabaseException("Connessione al Database non riuscita");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  /** Costruttore per il singleton */
  private DatabaseConnection() {}

  /**
   * Restituisce l'istanza di DatabaseConnection
   *
   * @return l'istanza
   * @throws DatabaseException Errore nel Database
   */
  public static DatabaseConnection getInstance() throws DatabaseException {
    if (instance == null) {
      instance = new DatabaseConnection();
      connect();
      return instance;
    } else {
      connect();
      return instance;
    }
  }

  /**
   * Chiude la connessione con il Database.
   *
   * @throws DatabaseException Errore del persistenza
   */
  public static void close() throws DatabaseException {

    try {
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DatabaseException("Chiusura della connessione non riuscita");
    }
  }

  /**
   * Restituisce l'istanza di connesione al database
   * @return l'istanza di connesione al database
   */
  public Connection getCon() {
    return con;
  }

  //METODO PER IL TEST
  public static DatabaseConnection getInstanceTEST() throws DatabaseException {
    return  instance;
  }
}
