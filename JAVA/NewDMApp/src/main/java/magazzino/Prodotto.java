package magazzino;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import exceptions.DatabaseException;
import exceptions.ProdottoException;
import exceptions.ProdottoNotFoundException;

public class Prodotto {

  private int acquistato; /* Numero di volte in cui il prodotto è stato inserito nello scontrino */
  private double prezzo;
  private long codice;
  private String nome;
  private int quantity;
  private String tipologia, scadenza, dimensioneConfezione;

  /**
   * Crea un nuovo Prodotto
   *
   * @param prezzo Prezzo del Prodotto
   * @param codice Codice generico del prodotto
   * @param nome Nome del prodotto
   * @param quantity Quantità del Prodotto
   * @param dimensione Dimensione della confezione del Prodotto
   * @param scade Durata media del Prodotto prima della scadenza
   * @param tipologia Categoria di Prodotto
   */
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

  /** Crea un nuovo Prodotto senza parametri */
  public Prodotto() {}

  /**
   * Restituisce la categoria del Prodotto
   *
   * @return la categoria del Prodotto
   */
  public String getTipologia() {
    return tipologia;
  }

  /**
   * Imposta la categoria del Prodotto
   *
   * @param tipologia la categoria del prodotto
   */
  public void setTipologia(String tipologia) {
    this.tipologia = tipologia;
  }

  /**
   * Restituisce la durata media del prodotto prima di scadere
   *
   * @return la durata media (breve,media,lunga) del Prodotto prima di scadere
   */
  public String getScadenza() {
    return scadenza;
  }

  /**
   * Imposta la durata media del prodotto prima di scadere
   *
   * @param scadenza durata media (breve,media,lunga) del Prodotto prima di scadere
   */
  public void setScadenza(String scadenza) {
    this.scadenza = scadenza;
  }

  /**
   * Restituisce la dimensione della confezione del prodotto
   *
   * @return la dimensione della confezione del prodotto (piccola,media,grande)
   */
  public String getDimensioneConfezione() {
    return dimensioneConfezione;
  }

  /**
   * Imposta la dimensione della confezione del Prodotto
   *
   * @param dimensioneConfezione la dimensione della confezione del prodotto(piccola,media,grande)
   */
  public void setDimensioneConfezione(String dimensioneConfezione) {
    this.dimensioneConfezione = dimensioneConfezione;
  }

  /**
   * Restituisce il prezzo del Prodotto
   *
   * @return il prezzo del Prodotto
   */
  public double getPrezzo() {
    return prezzo;
  }

  /**
   * Restituisce il nome del Prodotto
   *
   * @return il nome del Prodotto
   */
  public String getNome() {
    return this.nome;
  }

  /**
   * Restituisce la quantità di pezzi del prodotto contenuti dallo scontrino che ha invocato la
   * creazione di questo prodotto
   *
   * @return la quantità di pezzi contenuti
   */
  public int getAcquistato() {
    return acquistato;
  }

  /**
   * Aggiorna le unità di prodotto inserite nello scontrino
   *
   * @param q unità da aggiungere
   * @return quantità totale nello scontrino
   */
  public int updateAcquistato(int q) {
    return this.acquistato += q;
  }

  /**
   * Imposta la quantità di pezzi acquistati nello scontrino
   *
   * @param acquistato la quantità di pezzi da salvare
   */
  public void setAcquistato(int acquistato) {
    this.acquistato = acquistato;
  }

  /**
   * Imposta il prezzo del Prodotto
   *
   * @param prezzo il prezzo del prodotto
   * @throws ProdottoException Il prezzo deve essere positivo
   */
  public void setPrezzo(double prezzo) throws ProdottoException {
    if (prezzo <= 0) throw new ProdottoException("Il prezzo del prodotto deve essere positivo");

    this.prezzo = prezzo;
  }

  /**
   * Restituisce il codice identificativo del Prodotto
   *
   * @return il codice del Prodotto
   */
  public long getCodice() {
    return codice;
  }

  /** */
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
  public void leavedbquantity() throws DatabaseException, ProdottoException {
    if (quantity > 0) {
      ProdottoDao.leavedbquantity(this);
    } else {
      throw new ProdottoException("Prodotto esaurito");
    }
  }

  /**
   * Aumenta la quantità di questo prodotto nel database
   *
   * @param i quantità da aggiungere al Database
   * @throws DatabaseException Errore del Database
   * @throws ProdottoException Il prezzo deve essere positivo
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
   * @throws DatabaseException Errore del Database
   * @throws ProdottoException Il prezzo e la quantita del Prodotto devono essere positivi
   */
  public void createProdotto() throws DatabaseException, ProdottoException {
    if (prezzo <= 0 || quantity <= 0)
      throw new ProdottoException("Prezzo e quantità devono essere entrambi positivi");
    ProdottoDao.createProdotto(this);
  }

  /**
   * Modifica il prezzo del Prodotto
   *
   * @param prezzo Nuovo prezzo
   * @throws DatabaseException Errore generico nel Database
   * @throws ProdottoException Il prezzo deve essere positivo
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
        if (getCodice() == prodotto.getCodice()) {
          return true;
        } else {
          return false;
        }
      }
    }
  }

  public boolean eliminaProdotto() throws DatabaseException {
    return ProdottoDao.eliminaProdotto(this);
  }

  //PER TESTING

  public void setQuantitySenzaControllo(int quantity) throws ProdottoException {
    this.quantity = quantity;
  }
}
