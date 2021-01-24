package db;

/**
 * Interface contenente tutte le query che il sistema potrà eseguire.
 */
public interface Query {

  String login = "SELECT * FROM UTENTE AS u WHERE u.USERNAME=? AND u.PASSWORD=?";
  String newScontrino = "INSERT INTO SCONTRINO(data,importo_versato,totale,resto) VALUES(?,?,?,?)";
  String prodotto = "SELECT * FROM PRODOTTO AS p WHERE p.codice=?";
  String elenca = "INSERT INTO ELENCA VALUES(?,?,?,?)";
  String elencaCheck = "SELECT * FROM ELENCA AS e WHERE e.id=? AND e.data=? AND e.codice=?";
  String upDBQuant = "UPDATE PRODOTTO as p SET p.quantity = (p.quantity + ?) WHERE p.codice=?";
  String downDBQuant = "UPDATE PRODOTTO as p SET p.quantity = (p.quantity - ?) WHERE p.codice=?";
  String newProdotto = "INSERT INTO PRODOTTO VALUES (?,?,?,?,?,?,?)";
  String modificaPrezzo = "UPDATE PRODOTTO as p SET p.prezzo = ? WHERE p.codice=?";
  String newTicket = "INSERT INTO TICKET VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
  String checkScontrino = "SELECT * FROM SCONTRINO AS s WHERE s.id=? AND s.data LIKE ?";
  String saveRichiesta = "INSERT INTO RICHIESTA_ACQUISTO(quantity,data,stato,prodotto) VALUES(?,?,?,?)";
  String getTicket = "SELECT * FROM TICKET AS t WHERE t.DATA_APERTURA=? AND t.CODICE_FISCALE=? AND t.NUMERO_DI_SERIE=?";
  String eliminaProd="DELETE FROM PRODOTTO WHERE codice=?";
}
