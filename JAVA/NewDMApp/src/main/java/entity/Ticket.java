package entity;

import db.ElencaDao;
import db.ScontrinoDao;
import db.TicketDao;
import exceptions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

  public Ticket() {
  }

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

  public String getNomeCognome() {
    return nomeCognome;
  }

  public void setNomeCognome(String nomeCognome) {
    this.nomeCognome = nomeCognome;
  }

  public String getCf() {
    return cf;
  }

  public void setCf(String cf) {
    this.cf = cf;
  }

  public String getIndirizzo() {
    return indirizzo;
  }

  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public String getNumeroDiSerie() {
    return numeroDiSerie;
  }

  public String getNomeProdotto() {
    return nomeProdotto;
  }

  public void setNomeProdotto(String nomeProdotto) {
    this.nomeProdotto = nomeProdotto;
  }

  public void setNumeroDiSerie(String numeroDiSerie) {
    this.numeroDiSerie = numeroDiSerie;
  }

  public String getStato() {
    return stato;
  }

  public void setStato(String stato) {
    this.stato = stato;
  }

  public String getProblema() {
    return problema;
  }

  public void setProblema(String problema) {
    this.problema = problema;
  }

  public String getDataApertura() {
    return dataApertura;
  }

  public void setDataApertura(String dataApertura) {
    this.dataApertura = dataApertura;
  }

  public String getDataScontrino() {
    return dataScontrino;
  }

  public void setDataScontrino(String dataScontrino) {
    this.dataScontrino = dataScontrino;
  }

  public long getNumTel() {
    return numTel;
  }

  public void setNumTel(long numTel) {
    this.numTel = numTel;
  }

  public long getCodiceScontrino() {
    return codiceScontrino;
  }

  public void setCodiceScontrino(int codiceScontrino) {
    this.codiceScontrino = codiceScontrino;
  }

  public long getCodiceProdotto() {
    return codiceProdotto;
  }

  public void setCodiceProdotto(long codiceProdotto) {
    this.codiceProdotto = codiceProdotto;
  }

  public static Ticket getTicket(String apertura, String CF, Long serie)
          throws DatabaseException, TicketException {
    return TicketDao.getTicket(apertura, CF, serie);
  }
}
