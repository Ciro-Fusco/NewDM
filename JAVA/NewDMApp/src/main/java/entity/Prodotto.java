package entity;

import db.ProdottoDao;
import exceptions.DatabaseException;
import exceptions.ProdottoException;
import exceptions.ProdottoNotFoundException;

import java.util.Locale;
import java.util.Objects;

public class Prodotto {

  private int acquistato; /* Numero di volte in cui il prodotto è stato inserito nello scontrino */
  private double prezzo;
  private long codice;
  private String nome;
  private int quantity;
  private String tipologia, scadenza, dimensioneConfezione;

  public Prodotto(
      double prezzo,
      long codice,
      String nome,
      int quantity,
      String dimensione,
      String scade,
      String tipologia) {
    this.prezzo = prezzo;
    this.codice = codice;
    this.nome = nome;
    this.quantity = quantity;
    this.scadenza = scade;
    this.dimensioneConfezione = dimensione;
    String temp = tipologia.substring(0, 1).toUpperCase();
    tipologia = tipologia.toLowerCase();
    this.tipologia = tipologia.replace(tipologia.substring(0, 1), temp);
  }

  public Prodotto() {}

  public String getTipologia() {
    return tipologia;
  }

  public void setTipologia(String tipologia) {
    this.tipologia = tipologia;
  }

  public String getScadenza() {
    return scadenza;
  }

  public void setScadenza(String scadenza) {
    this.scadenza = scadenza;
  }

  public String getDimensioneConfezione() {
    return dimensioneConfezione;
  }

  public void setDimensioneConfezione(String dimensioneConfezione) {
    this.dimensioneConfezione = dimensioneConfezione;
  }

  public double getPrezzo() {
    return prezzo;
  }

  public String getNome() {
    return this.nome;
  }

  public int getAcquistato() {
    return acquistato;
  }

  /**
   * Aggiorna le unità di prodotto inserite nello scontrino presente nello scontrino
   *
   * @param q unità da aggiungere
   * @return quantità totale nello scontrino
   */
  public int updateAcquistato(int q) {
    return this.acquistato += q;
  }

  public void setAcquistato(int acquistato) {
    this.acquistato = acquistato;
  }

  public void setPrezzo(double prezzo) throws ProdottoException {
    if (prezzo <= 0) throw new ProdottoException("Il prezzo del prodotto deve essere positivo");

    this.prezzo = prezzo;
  }

  public long getCodice() {
    return codice;
  }

  public void setCodice(long codice) {
    this.codice = codice;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) throws ProdottoException {
    if (quantity <= 0) throw new ProdottoException("La quantità deve essere maggiore di 0");

    this.quantity = quantity;
  }

  /**
   * Cerca un prodotto dato il codice
   *
   * @param cod Codice del prodotto da trovare
   * @return Il prodotto cercato
   * @throws ProdottoNotFoundException Prodotto non trovato
   * @throws DatabaseException Errore nel database
   */
  public static Prodotto search(Long cod) throws ProdottoException, DatabaseException {
    return ProdottoDao.search(cod);
  }

  /**
   * Diminuisce la quantità del Prodotto dal Database in base al numero di volte in cui il codice è
   * stato inserito nello scontrino.
   *
   * @throws DatabaseException Errore del Database
   */
  public void leavedbquantity() throws DatabaseException{
    if (quantity > 0)
    ProdottoDao.leavedbquantity(this);

  }

  /**
   * Aumenta la quantità di questo prodotto nel database
   *
   * @param i quantità da aggiungere al Database
   * @throws DatabaseException Errore del Database
   */
  public void adddbquantity(int i) throws DatabaseException, ProdottoException {
    if (i > 0) ProdottoDao.adddbquantity(i, this);
    else {
      throw new ProdottoException("Inserire un valore maggiore di 0");
    }
  }

  /**
   * Salva questo Prodotto nel database
   *
   * @return true se il Prodotto è stato creato correttamente
   * @throws DatabaseException Errore del Database
   */
  public boolean createProdotto() throws DatabaseException, ProdottoException {
    if (prezzo <= 0 || quantity <= 0)
      throw new ProdottoException("Prezzo e quantità devono essere entrambi positivi");
    return ProdottoDao.createProdotto(this);
  }

  /**
   * Modifica il prezzo del Prodotto
   *
   * @param prezzo Nuovo prezzo
   * @throws DatabaseException Errore generico nel Database
   */
  public void modificaPrezzo(double prezzo) throws DatabaseException, ProdottoException {
    if (prezzo < 0) {
      throw new ProdottoException("Prezzo nuovo negativo.");
    }
    ProdottoDao.modificaPrezzo(this, prezzo);
  }

  @Override
  public String toString() {
    return "Prodotto{"
        + "acquistato="
        + acquistato
        + ", prezzo="
        + prezzo
        + ", codice="
        + codice
        + ", nome='"
        + nome
        + '\''
        + ", quantity="
        + quantity
        + ", tipologia='"
        + tipologia
        + '\''
        + ", scadenza='"
        + scadenza
        + '\''
        + ", dimensioneConfezione='"
        + dimensioneConfezione
        + '\''
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    else {
      if (o == null || getClass() != o.getClass()) return false;
      else {
        Prodotto prodotto = (Prodotto) o;
        return getCodice() == prodotto.getCodice();
      }
    }
  }

  public boolean eliminaProdotto() throws DatabaseException {
    return ProdottoDao.eliminaProdotto(this);
  }
}
