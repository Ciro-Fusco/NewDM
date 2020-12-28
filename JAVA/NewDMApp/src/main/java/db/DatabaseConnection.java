package db;

import java.sql.*;

public class DatabaseConnection {
    public static Connection con = null ;

    /**
     * Esegue la connessione al Database.
     *
     * @return true se il collegamento è riuscito; false altrimenti.
     *
     */
    public static boolean Connect () {

        if(con == null) {
            try {
                //caricamento e registrazione driver
                Class.forName("com.mysql.cj.jdbc.Driver"); //Carica il Driver
                String url = "jdbc:mysql://localhost:3306/NEGOZIO?allowPublicKeyRetrieval=true&&useSSL=false&serverTimezone=UTC";
                String username = "root";
                String pwd = "ci1ro23456";
                con = DriverManager.getConnection(url, username, pwd);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;


    }

    /**
     * Chiude la connessione con il Database.
     *
     * @return true se la connessione è stata chiusa con successo; false altrimenti.
     */
    public static boolean Close()  {

        try {
            con.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}
