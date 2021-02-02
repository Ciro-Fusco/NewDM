package business.cassa;

import persistenza.ScontrinoDao;
import business.inventario.Prodotto;
import exceptions.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/** Lo Scontrino che viene emesso al termine degli acquisti. */
public class Scontrino implements Serializable {

  private List<Prodotto> prodottoList;
  private double tot = 0;
  private double resto;
  private double versato;
  private String data;
  private String riepilogo = "";
  private long id;

  /** Crea uno scontrino vuoto alla data corrente */
  public Scontrino() {
    this.data = setData();
  }

  /**
   * Inizializza la data dello scontrino alla data corrente
   *
   * @return La stringa contenente la data corrente
   */
  private String setData() {
    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    return date.format(myFormatObj);
  }

  /**
   * Aggiunge un prodotto alla lista dei prodotti. Se la lista &egrave; vuota la inizializza e poi
   * procede ad aggiungere. Se il prodotto richiesto non esiste lancia una eccezione.
   *
   * @param cod codice del prodotto
   * @throws ProdottoNotFoundException Il codice inserito non corrisponde ad alcun prodotto.
   * @throws DatabaseException Errore del Database
   */
  public void addProdotto(Long cod)
      throws ProdottoNotFoundException, DatabaseException, ProdottoException {
    if (prodottoList == null) {
      prodottoList = new ArrayList<Prodotto>();
    }
    Prodotto p = Prodotto.search(cod);
    if (prodottoList.contains(p)) {
      p = prodottoList.get(prodottoList.indexOf(p));
      riepilogo =
          riepilogo.replaceFirst(
              p.getNome()
                  + "   x "
                  + p.getAcquistato()
                  + "     € "
                  + p.getPrezzo() * p.getAcquistato(),
              p.getNome()
                  + "   x "
                  + p.updateAcquistato(1)
                  + "     € "
                  + p.getPrezzo() * p.getAcquistato());
    } else {
      prodottoList.add(p);
      riepilogo +=
          "\n"
              + p.getNome()
              + "   x "
              + p.updateAcquistato(1)
              + "     € "
              + p.getPrezzo() * p.getAcquistato();
    }
  }

  /** Calcola il totale dello scontrino */
  public void calcolaTot() {
    this.tot = 0;
    prodottoList.forEach(
        (p) -> {
          this.tot += p.getPrezzo() * p.getAcquistato();
        });
  }

  /** Calcola il resto da dare al Cliente */
  public void calcolaResto() {

    this.resto = this.versato - this.tot;
  }

  /**
   * Imposta l'importo versato dal cliente
   * @param versato l'importo dato dal cliente
   * @throws ScontrinoException Importo pagato non sufficiente
   */
  public void setVersato(double versato) throws ScontrinoException {
    if (versato < this.tot) {
      throw new ScontrinoException("Importo versato non sufficiente");
    }
    this.versato = versato;
    calcolaResto();
  }

  /**
   * Restituisce il totale dello Scontrino
   * @return il totale dello Scontrino
   */
  public double getTot() {
    return tot;
  }

  /**
   * Restituisce il resto da dare al cliente
   * @return il resto da dare al cliente
   */
  public double getResto() {
    return resto;
  }

  /**+
   * Restituisce l'importo versato dal cliente
   * @return l'importo versato dal cliente
   */
  public double getVersato() {
    return versato;
  }

  /**
   * Restituisce la data di apertura dello Scontrino
   * @return la data di apertura dello Scontrino
   */
  public String getData() {
    return data;
  }

  public void setDataSbagliataTEST() {
    LocalDateTime date = LocalDateTime.of(1999, 06, 05, 00, 00);
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    this.data = date.format(myFormatObj);
  }

  /**
   * Restituisce il riepilogo dei prodotti inseriti nello Scontrino
   * @return il riepilogo dello scontrino
   */
  public String getRiepilogo() {
    return riepilogo;
  }

  /**
   * Imposta l'id dello Scontrino
   * @param id l'id dello scontrino
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Restituisce l'id dello Scontrino
   * @return l'id dello Scontrino
   */
  public long getId() {
    return id;
  }

  /**
   * Restituisce la lista di prodotti inseriti nello Scontrino
   * @return la lista dei prodotti
   */
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

  /**
   * Verifica l'esistenza di uno Scontrino
   * @param codice il codice dello scontrino da verificare
   * @param dataScontrino la data dello scontrino da verificare
   * @throws ScontrinoException Errore nella ricerca dello scontrino
    * @throws DatabaseException Errore del database
   */
  public static void checkScontrino(long codice, String dataScontrino)
      throws ScontrinoException, DatabaseException {
    ScontrinoDao.checkScontrino(codice, dataScontrino);
  }
}
