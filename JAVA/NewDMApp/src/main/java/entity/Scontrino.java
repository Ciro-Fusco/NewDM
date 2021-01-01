package entity;

import exceptions.DatabaseException;
import exceptions.ProdottoNotFoundException;
import db.ScontrinoDao;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/** Lo Scontrino che viene emesso al termine degli acquisti. */
public class Scontrino {

  private List<Prodotto> prodottoList;
  private double tot = 0;
  private double resto;
  private double versato;
  private final String data; // Da vedere
  private String riepilogo;
  private Long id;

  /** Crea uno scontrino vuoto alla data corrente
   *
   */
  public Scontrino() {
    this.data = setData();
  }

  /**
   * Inizializza la data dello scontrino alla data corrente
   *
   * @return La stringa contenente la data corrente
   */
  private String setData(){
  LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    return date.format(myFormatObj).toString();
  }

  /**
   * Aggiunge un prodotto alla lista dei prodotti. Se la lista &egrave; vuota la inizializza e poi
   * procede ad aggiungere. Se il prodotto richiesto non esiste lancia una eccezione.
   *
   * @param cod codice del prodotto
   * @throws ProdottoNotFoundException Il codice inserito non corrisponde ad alcun prodotto.
   * @throws DatabaseException Errore del Database
   */
  public void addProdotto(Long cod) throws ProdottoNotFoundException, DatabaseException {
    if (prodottoList == null) {
      prodottoList = new ArrayList<Prodotto>();
    }
    Prodotto p = Prodotto.search(cod);
    if (p == null) {
      throw new ProdottoNotFoundException("Prodotto non trovato");
    } else {
      if (prodottoList.contains(p)) {
        p=prodottoList.get(prodottoList.indexOf(p));
        riepilogo =riepilogo.replaceFirst(
            p.getNome() + "   x " + p.getAcquistato() + "     " + p.getPrezzo() * p.getAcquistato(),
            p.getNome()
                + "   x "
                + p.updateAcquistato(1)
                + "     "
                + p.getPrezzo() * p.getAcquistato());
      }
      prodottoList.add(p);
      riepilogo +=
          "\n"
              + p.getNome()
              + "   x "
              + p.updateAcquistato(1)
              + "     "
              + p.getPrezzo() * p.getAcquistato();
    }
  }

  /** Calcola il totale dello scontrino */
  public void calcolaTot() {
    this.tot=0;
    prodottoList.forEach(
        (p) -> {
          this.tot += p.getPrezzo() * p.getAcquistato();
        });
  }

  /** Calcola il resto da dare al Cliente */
  public void calcolaResto() {

    this.resto = this.versato - this.tot;
  }

  public void setVersato(double versato) {
    this.versato = versato;
    calcolaResto();
  }

  public double getTot() {
    return tot;
  }

  public double getResto() {
    return resto;
  }

  public double getVersato() {
    return versato;
  }

  public String getData() {
    return data;
  }

  public String getRiepilogo() {
    return riepilogo;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public List<Prodotto> getList() {
    return prodottoList;
  }

  /**
   * Salva lo scontrino nel Database
   *
   * @throws DatabaseException Errore nel Database
   */
  public void save() throws DatabaseException {
    ScontrinoDao.save(this);
  }
}
