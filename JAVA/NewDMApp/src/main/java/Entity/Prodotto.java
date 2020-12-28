package Entity;

import db.DatabaseConnection;
import db.ProdottoDAO;
import db.query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Prodotto {

  private int acquistato;
  private double prezzo;
  private long codice;
  private String Nome;
  private int quantity;

  public Prodotto(double prezzo, long codice, String Nome, int quantity) {
    this.prezzo = prezzo;
    this.codice = codice;
    this.Nome = Nome;
    this.quantity = quantity;
  }

  public double getPrezzo() {
    return prezzo;
  }

  public String getNome() {
    return this.Nome;
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
    Nome = nome;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public static Prodotto search(Long cod) {
    return ProdottoDAO.search(cod);
  }

  /**
   * Aggiorna la quantità del prodotto nel Database
   *
   * @return true se la quantità del prodotto è stata aggiornata correttamente
   */
  public boolean updateDBQuantity() {
          return ProdottoDAO.updateDBQuantity(this);
  }

  /**
   * Salva un nuovo Prodotto nel database
   */
  public boolean createProdotto() {
    return ProdottoDAO.createProdotto(this);
  }
}
