package Entity;

import db.DatabaseConnection;
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
    private static void setUtente(String nome, String cognome, String username) {
        Utente.nome = nome;
        Utente.cognome = cognome;
        Utente.username = username;
    }

    /**
     *
     * Cerca nel Database l'esistenza di un utente e lo autentica nel sistema.
     *
     * @param user Nome utente
     * @param pass Password non ancora codificata
     * @return true -- se l'utente è stato autenticato; false -- altrimenti
     * @throws SQLException
     */
    public static boolean Login(String user, String pass) throws SQLException {

        PreparedStatement prep = DatabaseConnection.con.prepareStatement(query.login);
        prep.setString(1,user);
        String shapass = DigestUtils.sha1Hex(pass);
        prep.setString(2,shapass);
        ResultSet res = prep.executeQuery();
       if(res.next()){
           setUtente(res.getString("NOME"),res.getString("COGNOME"),res.getString("USERNAME"));
           return true;
       }
       return false;


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


}
