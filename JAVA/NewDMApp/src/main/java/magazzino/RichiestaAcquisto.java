package magazzino;

import exceptions.DatabaseException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Richiesta di acquisto di un Prodotto
 */
public class RichiestaAcquisto {

  Long codice, id;
  int quantity;
  String data, stato;
  private static final String DA_ESEGUIRE = "Da Eseguire";
  private static final String ESEGUITO = "Eseguito";

  /**
   * Crea un richiesta di acquisto alla data corrente ee con lo stato "DA ESEGUIRE"
   */
  public RichiestaAcquisto() {
    this.data = setData();
    this.stato = DA_ESEGUIRE;
  }

  /**
   * Restituisce il codice del Prodotto per cui si richiede l'approvvigionamento
   * @return Il codice del Prodotto
   */
  public Long getCodice() {
    return codice;
  }

  /**
   * Imposta il codice del Prodotto per cui si richiede l'approvvigionamento
   * @param codice il codice del Prodotto
   */
  public void setCodice(Long codice) {
    this.codice = codice;
  }

  /**
   * Restituisce l'identificativo della Richiesta
   * @return
   */
  public Long getId() {
    return id;
  }

  /**
   * Imposta l'identificativo della Richiesta
   * @param id Il codice identificativo della Richiesta
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Restituisce la quantità di Prodotto da acquistare
   * @return La quantità di prodotto da acquistare
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * Imposta la quantità di prodotto da acquistare
   * @param quantity La quantità del prodotto
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * Restituisce la data di apertura della Richiesta
   * @return La data di apertura della Richiesta
   */
  public String getData() {
    return data;
  }

  /**
   * Restituisce lo stato di esecuzione della Richiesta
   * @return Lo stato della Richiesta
   */
  public String getStato() {
    return stato;
  }

  /**
   * Imposta lo stato di esecuzione della Richiesta
   * @param stato Lo stato della Richiesta
   */
  public void setStato(String stato) {
    this.stato = stato;
  }

  /**
   * Imposta la data di apertura della Richiesta
   * @return La data di apertura della richiesta
   */
  private String setData() {
    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    return date.format(myFormatObj);
  }

  @Override
  public String toString() {
    return "RichiestaAcquisto{"
        + "codice="
        + codice
        + ", quantity="
        + quantity
        + ", data='"
        + data
        + '\''
        + ", stato='"
        + stato
        + '\''
        + '}';
  }

  /**
   * Salva la Richiesta di acquisto nel database
   * @throws DatabaseException Errore nel Database
   */
  public void save() throws DatabaseException {
    RichiestaAcquistoDao.save(this);
  }
}
