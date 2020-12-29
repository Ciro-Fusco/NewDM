package entity;

import db.DatabaseConnection;
import db.ProdottoDao;
import exceptions.DatabaseException;
import exceptions.ProdottoNotFoundException;

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
   * Aggiorna la quantità del prodotto nel Database
   *
   * @return true se la quantità del prodotto è stata aggiornata correttamente
   */
  public boolean updatedbquantity() throws DatabaseException {
    return ProdottoDao.updatedbquantity(this);
  }

  /** Salva un nuovo Prodotto nel database */
  public boolean createProdotto() throws DatabaseException {
    return ProdottoDao.createProdotto(this);
  }
}
