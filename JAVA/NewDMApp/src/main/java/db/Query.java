package db;

/** Interface contenente tutte le query che il sistema potrà eseguire. */
public interface Query {

  String login = "SELECT * FROM UTENTE AS u WHERE u.USERNAME=? AND u.PASSWORD=?";
  String newScontrino = "INSERT INTO SCONTRINO(data,importo_versato,totale,resto) VALUES(?,?,?,?)";
  String prodotto = "SELECT * FROM PRODOTTO AS p WHERE p.codice=?";
  String elenca = "INSERT INTO ELENCA VALUES(?,?,?)";
  String upDBQuant = "UPDATE PRODOTTO as p SET p.quantity = (p.quantity + ?) WHERE p.codice=?";
  String downDBQuant = "UPDATE PRODOTTO as p SET p.quantity = (p.quantity - ?) WHERE p.codice=?";
  String newProdotto = "INSERT INTO PRODOTTO VALUES (?,?,?,?)";
  String modificaPrezzo = "UPDATE PRODOTTO as p SET p.prezzo = ? WHERE p.codice=?"; ;
}
