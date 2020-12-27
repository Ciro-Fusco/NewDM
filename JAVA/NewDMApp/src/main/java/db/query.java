package db;

/**
 * Interface contenente tutte le query che il sistema potrà eseguire.
 *
 */
public interface query {

    String login = "SELECT * FROM Utente AS u WHERE u.username=? AND u.password=?";
    String scontrino = "";

}
