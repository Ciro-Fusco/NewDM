package db;

import exceptions.DatabaseException;

import java.sql.*;

public class DatabaseConnection {
  public static Connection con = null;

  /**
   * Esegue la connessione al Database.
   * @throws DatabaseException Errore del Database
   */
  public static void connect() throws DatabaseException {

    try {
      if ((con == null) || con.isClosed()) {
        // caricamento e registrazione driver
        Class.forName("com.mysql.cj.jdbc.Driver"); // Carica il Driver
        String url =
            "jdbc:mysql://localhost:3306/NEGOZIO?"
                + "allowPublicKeyRetrieval=true&&useSSL=false&serverTimezone=UTC";
        String username = "root";
        String pwd = "federernadal";
        con = DriverManager.getConnection(url, username, pwd);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DatabaseException("Connessione al Database non riuscita");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

  }

  /** Chiude la connessione con il Database.
   * @throws DatabaseException Errore del database
   */
  public static void close() throws DatabaseException {

    try {
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DatabaseException("Chiusura della connessione non riuscita");
    }
  }

  public static Connection getCon() {
    return con;
  }
}
