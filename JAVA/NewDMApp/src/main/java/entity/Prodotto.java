package entity;

import db.ProdottoDao;

public class Prodotto {

  private int acquistato;
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

  public static Prodotto search(Long cod) {
    return ProdottoDao.search(cod);
  }

  /**
   * Aggiorna la quantità del prodotto nel Database
   *
   * @return true se la quantità del prodotto è stata aggiornata correttamente
   */
  public boolean updatedbquantity() {
    return ProdottoDao.updatedbquantity(this);
  }

  /** Salva un nuovo Prodotto nel database */
  public boolean createProdotto() {
    return ProdottoDao.createProdotto(this);
  }
}
