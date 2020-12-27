package db;

/**
 * Interface contenente tutte le query che il sistema potrà eseguire.
 *
 */
public interface query {

    String login = "SELECT * FROM Utente AS u WHERE u.username=? AND u.password=?";
    String scontrino = "INSERT INTO Scontrino(data,importo_versato,totale,resto) VALUES(?,?,?,?)";
    String prodotto = "SELECT * FROM Prodotto AS P WHERE p.codice=?";

}
