package entity;

import db.ProdottoDao;
import exceptions.DatabaseException;
import exceptions.ProdottoNotFoundException;

import java.util.Objects;

public class Prodotto {

  private int acquistato; /* Numero di volte in cui il prodotto è stato inserito nello scontrino */
  private double prezzo;
  private long codice;
  private String nome;
  private int quantity;

  public Prodotto(double prezzo, long codice, String nome, int quantity) {
    this.prezzo = prezzo;
    this.codice = codice;
    this.nome = nome;
    this.quantity = quantity;
  }

  public Prodotto() {

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

  public void setPrezzo(double prezzo) {
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

  public void setQuantity(int quantity) {
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
  public static Prodotto search(Long cod) throws ProdottoNotFoundException, DatabaseException {
    return ProdottoDao.search(cod);
  }

  /**
   * Diminuisce la quantità del Prodotto dal Database in base al numero di volte in cui
   * il codice è stato inserito nello scontrino.
   *
   * @throws DatabaseException Errore del Database
   */
  public void leavedbquantity() throws DatabaseException {
     ProdottoDao.leavedbquantity(this);
  }

  /**
   * Aumenta la quantità di questo prodotto nel database
   * @param i quantità da aggiungere al Database
   * @throws DatabaseException Errore del Database
   */
  public void adddbquantity(int i) throws DatabaseException{
    ProdottoDao.adddbquantity(i,this);
  }

  /** Salva questo Prodotto nel database
   * @throws DatabaseException Errore del Database
   * @return true se il Prodotto è stato creato correttamente
   */
  public boolean createProdotto() throws DatabaseException {
    return ProdottoDao.createProdotto(this);
  }

  /**
   * Modifica il prezzo del Prodotto
   * @param prezzo Nuovo prezzo
   * @throws DatabaseException Errore generico nel Database
   */
  public void modificaPrezzo(double prezzo) throws DatabaseException{
    ProdottoDao.modificaPrezzo(this,prezzo);
  }

  @Override
  public String toString() {
    return "Prodotto{" +
            "acquistato=" + acquistato +
            ", prezzo=" + prezzo +
            ", codice=" + codice +
            ", nome='" + nome + '\'' +
            ", quantity=" + quantity +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Prodotto prodotto = (Prodotto) o;
    return getCodice() == prodotto.getCodice();
  }


}
