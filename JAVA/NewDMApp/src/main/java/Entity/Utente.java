package Entity;

import db.DatabaseConnection;
import db.UtenteDAO;
import db.query;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.Console;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Classe statica. Può contenere una sola istanza di utente alla volta.
 * Non possiede costruttori.
 */
public class Utente {

    private static String nome, cognome,username;

    /**
     *
     * @param nome nome dell'utente
     * @param cognome cognome dell'utente
     * @param username username dell'utente
     *
     * Inserisce le informazioni riguardo l'utente nella classe.
     *
     *
     */
    public static void setUtente(String nome, String cognome, String username) {
        Utente.nome = nome;
        Utente.cognome = cognome;
        Utente.username = username;
    }



    /**
     * Elimina le informazioni circa l'utente correntemente autenticato
     */
    public static void clear(){
        setUtente(null,null,null);
    }

    /**
     *
     * @return nome -- il nome dell'utente autenticato; null -- se non è autenticato nessun utente;
     */
    public static String getNome() {
        return nome;
    }


    /**
     *
     *
     * @return cognome -- il cognome dell'utente autenticato; null -- se non è autenticato nessun utente;
     */
    public static String getCognome() {
        return cognome;
    }

    /**
     *
     * @return Username-- Nome utente dell'utente autenticato; null -- se non è autenticato nessun utente;
     */

    public static String getUsername() {
        return username;
    }


    @Override
    public String toString() {
        return "Utente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", username='" + username + '\'' +
                '}';
    }


    public static boolean Login(String us, String pass){

        try{
            return UtenteDAO.Login(us,pass);
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }

    }


}
