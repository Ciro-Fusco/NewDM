package assistenza;

import cassa.Scontrino;
import cassa.ElencaDao;
import exceptions.*;
import magazzino.Prodotto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Il Ticket contenente le informazioni che riguardano una richiesta di Assistenza*/
public class Ticket {

  private String nomeCognome,
          cf,
          indirizzo,
          numeroDiSerie,
          nomeProdotto,
          stato,
          problema,
          dataApertura,
          dataScontrino,
          tipo;
  private long numTel, codiceProdotto;
  private long codiceScontrino;
  private static final String APERTO = "Aperto";
  private static final String IN_LAVORAZIONE = "In Lavorazione";
  private static final String CHIUSO = "Chiuso";

  /**
   * Crea un nuovo Ticket
   *
   * @param nomeCognome     Nome e Cognome del cliente
   * @param cf              Codice Fiscale del cliente
   * @param indirizzo       Indirizzo di residenza del cliente
   * @param tipo            Tipo di prodotto per cui si chiede assistenza
   * @param nomeProdotto    Nome del prodotto per cui si chiede assistenza
   * @param numeroDiSerie   Numero di serie del prodotto per cui si richiede assistenza
   * @param num_tel         Numero di telefono del cliente
   * @param codiceScontrino Codice dello scontrino di acquisto del prodotto per cui si richiede assistenza
   * @param dataScontrino   Data dello scontrino di acquisto del prodotto per cui si richiede assistenza
   * @param codiceProdotto  Codice identificativo comune (codice a barre o codice utilizzato nel negozio per identificare
   *                        un prodotto) del prodotto per cui si richiede assistenza
   * @throws ScontrinoException Scontrino non esistente
   * @throws DatabaseException  Errore nel uso del database
   * @throws ProdottoException  Prodotto non esistente
   * @throws ElencaException    Lo Scontrino e il Prodotto non corrispondono
   */
  public Ticket(
          String nomeCognome,
          String cf,
          String indirizzo,
          String tipo,
          String nomeProdotto,
          String numeroDiSerie,
          long num_tel,
          long codiceScontrino,
          String dataScontrino,
          long codiceProdotto)
          throws ScontrinoException, DatabaseException, ProdottoException, ElencaException {
    this.nomeCognome = nomeCognome;
    this.cf = cf;
    this.indirizzo = indirizzo;
    this.tipo = tipo;
    this.numeroDiSerie = numeroDiSerie;
    this.nomeProdotto = nomeProdotto;
    this.dataScontrino = dataScontrino;
    this.dataApertura = setDataApertura();
    this.numTel = num_tel;
    Scontrino.checkScontrino(codiceScontrino, dataScontrino);
    this.codiceScontrino = codiceScontrino;
    this.dataScontrino = dataScontrino;
    Prodotto.search(codiceProdotto);
    this.codiceProdotto = codiceProdotto;
    ElencaDao.checkCorrispondenza(codiceScontrino, dataScontrino, codiceProdotto);
    this.stato = APERTO;
  }

  /**
   * Crea uno scontrino senza inizializzarlo
   */
  public Ticket() {
  }

  /**
   * Imposta la data di apertura del Ticket alla data e ora corrente
   *
   * @return la stringa contenente data e ora di creazione del Ticket
   */
  private String setDataApertura() {
    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    return date.format(myFormatObj);
  }

  /**
   * Salva il ticket nel Database
   *
   * @throws DatabaseException Errore nel Database
   */
  public void save() throws DatabaseException {
    TicketDao.save(this);
  }

  /**
   * Resituisce Nome e Cognome del CLiente
   *
   * @return una stringa contente il nome e il cognome del cliente
   */
  public String getNomeCognome() {
    return nomeCognome;
  }

  /**
   * Imposta Nome e Cognome del Cliente
   *
   * @param nomeCognome Stringa contente Nome e Cognome del Cliente
   */
  public void setNomeCognome(String nomeCognome) {
    this.nomeCognome = nomeCognome;
  }

  /**
   * Restituisce il Codice Fiscale del Cliente
   *
   * @return il Codice Fiscale del Cliente
   */
  public String getCf() {
    return cf;
  }

  /**
   * Imposta il Codice Fiscale del Cliente
   *
   * @param cf Il codice fiscale del Cliente
   */
  public void setCf(String cf) {
    this.cf = cf;
  }

  /**
   * Restituisce l'indirizzo di residenza del Cliente
   *
   * @return l'indirizzo di residenza del Cliente
   */
  public String getIndirizzo() {
    return indirizzo;
  }

  /**
   * Imposta l'indirizzo di residenza del Cliente
   *
   * @param indirizzo l'indirizzo di Residenza del Cliente
   */
  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  /**
   * Restituisce il tipo di categoria del Prodotto
   * @return il tipo di categoria del prodotto
   */
  public String getTipo() {
    return tipo;
  }

  /**
   * Imposta la categoria del Prodotto
   * @param tipo il tipo di categoria del Prodotto
   */
  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  /**
   * Restituisce il Numero di Serie del Prodotto
   * @return il Numero di Serie del Prodotto
   */
  public String getNumeroDiSerie() {
    return numeroDiSerie;
  }

  /**
   * Restituisce il nome del Prodotto
   * @return il nome del Prodotto
   */
  public String getNomeProdotto() {
    return nomeProdotto;
  }

  /**
   * Imposta il nome del Prodotto
   * @param nomeProdotto il nome del Prodotto
   */
  public void setNomeProdotto(String nomeProdotto) {
    this.nomeProdotto = nomeProdotto;
  }

  /**
   * Imposta il Numero di Serie del Prodotto
   * @param numeroDiSerie il Numero di Serie del Prodotto
   */
  public void setNumeroDiSerie(String numeroDiSerie) {
    this.numeroDiSerie = numeroDiSerie;
  }

  /**
   * Restituisce lo stato di lavorazione del Ticket
   * @return lo stato di lavorazione del Ticket
   */
  public String getStato() {
    return stato;
  }

  /**
   * Imposta lo stato di lavorazione del Ticket
   * @param stato lo stato di lavorazione del Ticket
   */
  public void setStato(String stato) {
    this.stato = stato;
  }

  /**
   * Restituisce il problema per cui è stato creato il Ticket
   * @return il problema relativo al Prodotto
   */
  public String getProblema() {
    return problema;
  }

  /**
   * Imposta il problema per cui è stato creato il Ticket
   * @param problema il problema relativo al Prodotto
   */
  public void setProblema(String problema) {
    this.problema = problema;
  }

  /**
   * Restituisce la data di apertura del Ticket
   * @return una stringa contenente la data di apertura del Ticket
   */
  public String getDataApertura() {
    return dataApertura;
  }

  /**
   * Imposta la data di apertura del Ticket, utile per la creazione di un Ticket a partire da uno persistente
   * @param dataApertura una stringa contenente la data di apertura del Ticket
   */
  public void setDataApertura(String dataApertura) {
    this.dataApertura = dataApertura;
  }

  /**
   * Restituisce la data dello Scontrino
   * @return la data dello Scontrino
   */
  public String getDataScontrino() {
    return dataScontrino;
  }

  /**
   * Imposta la data dello Scontrino
   * @param dataScontrino la data dello scontrino
   */
  public void setDataScontrino(String dataScontrino) {
    this.dataScontrino = dataScontrino;
  }

  /**
   * Restituisce il numero di telefono del Cliente
   * @return il numero di telefono
   */
  public long getNumTel() {
    return numTel;
  }

  /**
   * Imposta il numero di telefono del Cliente
   * @param numTel il numero di telefono
   */
  public void setNumTel(long numTel) {
    this.numTel = numTel;
  }

  /**
   * Restituisce il codice dello Scontrino
   * @return il codice dello Scontrino
   */
  public long getCodiceScontrino() {
    return codiceScontrino;
  }

  /**
   * Imposta il codice dello Scontrino
   * @param codiceScontrino il codice dello scontrino
   */
  public void setCodiceScontrino(int codiceScontrino) {
    this.codiceScontrino = codiceScontrino;
  }

  /**
   * Restituisce il codice generico del Prodotto
   * @return il codice generico del Prodotto
   */
  public long getCodiceProdotto() {
    return codiceProdotto;
  }

  /**
   * Imposta il codice generico del Prodotto
   * @param codiceProdotto il codice generico del Prodotto
   */
  public void setCodiceProdotto(long codiceProdotto) {
    this.codiceProdotto = codiceProdotto;
  }

  /**
   * Cerca un Ticket dati tutti i parametri
   * @param apertura Data di apertura del Ticket
   * @param CF Codice Fiscale del Cliente
   * @param serie Numero di Serie del Prodotto
   * @return il Ticket cercato
   * @throws DatabaseException Errore del Database
   * @throws TicketException Errore nella ricerca del Ticket
   */
  public static Ticket getTicket(String apertura, String CF, String serie)
          throws DatabaseException, TicketException {
    return TicketDao.getTicket(apertura, CF, serie);
  }

  /**
   * Metodo toString() del Ticket
   * @return una stringa contenente la conversione canonica dell'oggetto
   */
  @Override
  public String toString() {
    return "Ticket{"
            + "nomeCognome='"
            + nomeCognome
            + '\''
            + ", cf='"
            + cf
            + '\''
            + ", indirizzo='"
            + indirizzo
            + '\''
            + ", numeroDiSerie='"
            + numeroDiSerie
            + '\''
            + ", nomeProdotto='"
            + nomeProdotto
            + '\''
            + ", stato='"
            + stato
            + '\''
            + ", problema='"
            + problema
            + '\''
            + ", dataApertura='"
            + dataApertura
            + '\''
            + ", dataScontrino='"
            + dataScontrino
            + '\''
            + ", tipo='"
            + tipo
            + '\''
            + ", numTel="
            + numTel
            + ", codiceProdotto="
            + codiceProdotto
            + ", codiceScontrino="
            + codiceScontrino
            + '}';
  }
}
