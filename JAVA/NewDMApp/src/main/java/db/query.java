package db;

/**
 * Interface contenente tutte le query che il sistema potrà eseguire.
 *
 */
public interface query {

    String login = "SELECT * FROM UTENTE AS u WHERE u.USERNAME=? AND u.PASSWORD=?";
    String scontrino = "INSERT INTO SCONTRINO(data,importo_versato,totale,resto) VALUES(?,?,?,?)";
    String prodotto = "SELECT * FROM PRODOTTO AS p WHERE p.codice=?";
    String elenca = "INSERT INTO ELENCA VALUES(?,?,?)";
    String upQuant= "UPDATE PRODOTTO SET quantity = ? WHERE codice=?";

}
