package business.inventario;

import business.fornitura.ModuleIAConverter;
import business.fornitura.RichiestaAcquisto;
import business.utenza.Utente;
import exceptions.DatabaseException;
import exceptions.ProdottoException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import presentazione.AlertMessage;
import presentazione.App;

/** Controller per le interazioni della sezione Magazzino. */
public class MagazzinoController implements Initializable {

  private static Prodotto prodotto;
  private static Prodotto tempProdotto;
  @FXML private TextField nomeProd;
  @FXML private TextField prezzoProd;
  @FXML private TextField codiceProd;
  @FXML private TextField quantitaProd;
  @FXML public TextField riepilogoNuovoProdotto;
  @FXML public TextField riepilogoProdotto;
  @FXML private Label labelNomeProd;
  @FXML private Label labelPrezzoProd;
  @FXML private Label labelOrdineCalcSugg;
  @FXML private Label labelNomeProdSugg;
  @FXML private Label labelQuantitaProdSugg;
  @FXML private TextField tipologiaProd;
  @FXML private TextField prezzoSped;
  @FXML private RadioButton piccolaDim;
  @FXML private RadioButton medioDim;
  @FXML private RadioButton grandeDim;
  @FXML private RadioButton breveSca;
  @FXML private RadioButton mediaSca;
  @FXML private RadioButton lungaSca;
  @FXML private RadioButton primavera;
  @FXML private RadioButton estate;
  @FXML private RadioButton autunno;
  @FXML private RadioButton inverno;
  @FXML private ToggleGroup dimensioni;
  @FXML private ToggleGroup scadenza;
  @FXML private ToggleGroup stagione;
  @FXML private ToggleGroup tipoSupermerc;
  @FXML private ToggleGroup festivita;
  private static int ordineCalcolato;

  // Viene eseguito ogni volta che si carica una nuova finestra
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    String nomeFile =
        url.toString().substring(url.toString().lastIndexOf('/') + 1, url.toString().length());

    if (nomeFile.equals("OrdinaProdottoQuantitaSugg.fxml")) {
      labelNomeProdSugg.setText(prodotto.getNome());
      labelQuantitaProdSugg.setText(Integer.toString(prodotto.getQuantity()));
      labelOrdineCalcSugg.setText(Integer.toString(ordineCalcolato));
    } else {
      System.out.println();
    }

    if (nomeFile.equals("InserisciNuovoProdottoRiepilogo.fxml")) {
      riepilogoNuovoProdotto.setText(prodotto.toString());
    } else {
      System.out.println();
    }

    if (nomeFile.equals("InserisciProdottoRiepilogo.fxml")) {
      riepilogoProdotto.setText(tempProdotto.toString());
    } else {
      System.out.println();
    }

    if (nomeFile.equals("ModPrezzoProdottoPopUpForm.fxml")) {
      labelPrezzoProd.setText(Double.toString(prodotto.getPrezzo()));
      labelNomeProd.setText(prodotto.getNome());
    } else {
      System.out.println();
    }
  }

  /**
   * Apre la dashboard del magazzino.
   *
   * @param mouseEvent mouseEvent
   * @throws IOException Errore cambio scena
   */
  public void openDashboardMagazzino(MouseEvent mouseEvent) throws IOException {
    App.setRoot("DashboardMagazzino");
  }

  // DASHBOARD MAGAZZINO

  /**
   * Apre la dashboard iniziale.
   *
   * @param mouseEvent mouseEvent
   * @throws IOException Errore cambio scena
   * @throws DatabaseException Errore del Database
   */
  public void openDashboard(MouseEvent mouseEvent) throws IOException, DatabaseException {
    Utente.logout();
    App.setRoot("Dashboard");
  }

  /**
   * Apre il form per la modifica della quantità di un prodotto.
   *
   * @param mouseEvent mouseEvent
   * @throws IOException Errore cambio scena
   */
  @FXML
  public void openInserisciProdotto(MouseEvent mouseEvent) throws Exception {
    App.setRoot("InserisciProdottoForm");
  }

  /**
   * Apre il form per l'inserimento di un nuovo prodotto.
   *
   * @param mouseEvent mouseEvent
   * @throws IOException Errore cambio scena
   */
  @FXML
  public void openInserisciNuovoProdotto(MouseEvent mouseEvent) throws Exception {
    App.setRoot("InserisciNuovoProdottoForm");
  }

  /**
   * Apre il form per la creazione di una nuova richiesta d'acquisto.
   *
   * @param mouseEvent mouseEvent
   * @throws IOException Errore cambio scena
   */
  @FXML
  public void openOrdinaProdotto(MouseEvent mouseEvent) throws Exception {
    App.setRoot("OrdinaProdottoForm");
  }

  /**
   * Apre il form per la modifica del prezzo di un prodotto.
   *
   * @param mouseEvent mouseEvent
   * @throws IOException Errore cambio scena
   */
  @FXML
  public void openModificaPrezzo(MouseEvent mouseEvent) throws Exception {
    App.setRoot("ModPrezzoProdottoForm");
  }

  ////////////////////////////////////////////////////////////////////////////

  // INSERISCI PRODOTTO GIA PRESENTE

  /**
   * Apre la schermata di riepilogo della quantità del prodotto modificata.
   *
   * @param mouseEvent mouseEvent
   * @throws IOException Errore cambio scena
   * @throws ProdottoException Prodotto non trovato
   * @throws DatabaseException Errore nel Database
   */
  public void openInserisciProdottoRiepilogo(MouseEvent mouseEvent)
      throws IOException, ProdottoException, DatabaseException {

    // controllo che sia un codice prodotto valido
    if (codiceProd.getText().matches("^[0-9]{13}$")) {
      Prodotto.search(Long.parseLong(codiceProd.getText()));
      // controllo se la quantita è positiva
      if (quantitaProd.getText().matches("^[1-9][0-9]*$")) {
        tempProdotto = new Prodotto();
        prodotto = Prodotto.search(Long.parseLong(codiceProd.getText()));
        tempProdotto.setQuantity(prodotto.getQuantity() + Integer.parseInt(quantitaProd.getText()));
        tempProdotto.setCodice(prodotto.getCodice());
        tempProdotto.setPrezzo(prodotto.getPrezzo());
        tempProdotto.setNome(prodotto.getNome());
        tempProdotto.setAcquistato(prodotto.getAcquistato());
        App.setRoot("InserisciProdottoRiepilogo");

      } else {
        AlertMessage.showError("Inserisci una quantità positiva");
      }
    } else {
      AlertMessage.showError("Inserisci un codice prodotto valido");
    }
  }

  /**
   * Esegue l'aggiornamento della quantità del prodotto.
   *
   * @param mouseEvent mouseEvent
   * @throws IOException Errore cambio scena
   */
  public void inserisciProdotto(MouseEvent mouseEvent)
      throws DatabaseException, IOException, ProdottoException {
    prodotto.adddbquantity(tempProdotto.getQuantity() - prodotto.getQuantity());
    AlertMessage.showInformation("Quantità aggiornata correttamente");
    App.setRoot("InserisciProdottoForm");
  }

  ///////////////////////////////////////////////////////////////////////////////

  // INSERISCI NUOVO PRODOTTO

  /**
   * Apre la schermata di riepilogo dell'inserimento del nuovo prodotto.
   *
   * @param mouseEvent mouseEvent
   * @throws IOException Errore cambio scena
   */
  public void openNuovoProdottoRiepilogo(MouseEvent mouseEvent)
      throws IOException, ProdottoException {

    // controllo se è un nome prodotto valido
    if (nomeProd.getText().length() >= 2 && nomeProd.getText().length() <= 255) {
      // controllo se la quantita è positiva
      if (quantitaProd.getText().matches("^[1-9][0-9]*$")) {
        // controllo che sia un codice prodotto valido
        if (codiceProd.getText().matches("^[0-9]{13}$")) {
          // controllo che sia un prezzo valido, decimale con precisione di due
          if (prezzoProd.getText().matches("[0-9]+(\\.[0-9][0-9]?)?")
              && Double.parseDouble(prezzoProd.getText()) > 0) {
            // controllo se è un tipo prodotto valido
            if (tipologiaProd.getText().length() >= 2 && tipologiaProd.getText().length() <= 255) {
              // Controllo se è stata selezionata una dimensione
              if (dimensioni.getSelectedToggle() != null) {
                // controllo se è stata selezionata una scadenza
                if (scadenza.getSelectedToggle() != null) {
                  prodotto = new Prodotto();
                  prodotto.setNome(nomeProd.getText());
                  prodotto.setPrezzo(Double.parseDouble(prezzoProd.getText()));
                  prodotto.setQuantity(Integer.parseInt(quantitaProd.getText()));
                  prodotto.setCodice(Long.parseLong(codiceProd.getText()));
                  prodotto.setTipologia(tipologiaProd.getText());
                  prodotto.setDimensioneConfezione(
                      ((RadioButton) dimensioni.getSelectedToggle()).getText());
                  prodotto.setScadenza(((RadioButton) scadenza.getSelectedToggle()).getText());
                  App.setRoot("InserisciNuovoProdottoRiepilogo");
                } else {
                  AlertMessage.showError("Selezionare una scadenza");
                }
              } else {
                AlertMessage.showError("Selezionare una dimensione");
              }
            } else {
              AlertMessage.showError("Inserire una tipologia valida");
            }
          } else {
            AlertMessage.showError("Inserire un prezzo valido");
          }
        } else {
          AlertMessage.showError("Inserisci un codice prodotto valido (13 cifre)");
        }
      } else {
        AlertMessage.showError("Inserisci una quantità positiva");
      }
    } else {
      AlertMessage.showError("Inserisci un nome valido");
    }
  }

  /**
   * Esegue il salvataggio del nuovo prodotto.
   *
   * @param mouseEvent mouseEvent
   * @throws IOException Errore cambio scena
   */
  public void inserisciNuovoProdotto(MouseEvent mouseEvent)
      throws DatabaseException, IOException, ProdottoException {
    prodotto.createProdotto();
    AlertMessage.showInformation("Prodotto inserito correttamente!");
    App.setRoot("InserisciNuovoProdottoForm");
  }

  ///////////////////////////////////////////////////////////////////

  // MOD PREZZO PRODOTTO

  /**
   * Esegue la modifica del prezzo del prodotto.
   *
   * @param mouseEvent mouseEvent
   * @throws IOException Errore cambio scena
   */
  public void aggiornaPrezzo(MouseEvent mouseEvent)
      throws DatabaseException, ProdottoException, IOException {

    // controllo che sia un prezzo valido, decimale con precisione di due
    if (prezzoProd.getText().matches("[0-9]+(\\.[0-9][0-9]?)?")) {
      prodotto.modificaPrezzo(Double.parseDouble(prezzoProd.getText()));
      prodotto.setPrezzo(Double.parseDouble(prezzoProd.getText()));
      AlertMessage.showInformation("Prezzo aggiornato con successo");
      App.setRoot("ModPrezzoProdottoForm");
    } else {
      AlertMessage.showError("Inserisci un prezzo valido");
    }
  }

  /**
   * Apre la schermata per l'inserimento del nuovo prezzo.
   *
   * @param mouseEvent mouseEvent
   * @throws IOException Errore cambio scena
   */
  public void openModificaPrezzoPopUp(MouseEvent mouseEvent)
      throws ProdottoException, DatabaseException, IOException {

    // controllo che sia un codice prodotto valido
    if (codiceProd.getText().matches("^[0-9]{13}$")) {
      prodotto = Prodotto.search(Long.parseLong(codiceProd.getText()));
      App.setRoot("ModPrezzoProdottoPopUpForm");
    } else {
      AlertMessage.showError("Inserisci un codice prodotto valido");
    }
  }

  /////////////////////////////////////////////////////////

  // ORDINA PRODOTTO

  /**
   * Apre la schermata di suggerimento per la richiesta d'acquisto.
   *
   * @param mouseEvent mouseEvent
   * @throws IOException Errore cambio scena
   */
  public void cercaProdottoOrdinaProd(MouseEvent mouseEvent)
      throws DatabaseException, ProdottoException, IOException {

    // controlllo se codice inserito è valido
    if (codiceProd.getText().matches("^[0-9]{13}$")) {
      // controllo se è un prezzo valido
      if (prezzoSped.getText().matches("[0-9]+(\\.[0-9][0-9]?)?")) {
        // controllo se è stato selezionata una stagione
        if (stagione.getSelectedToggle() != null) {
          // controllo se è stato selezionato un tipo di supermercato
          if (tipoSupermerc.getSelectedToggle() != null) {
            // controllo se è stato selezionato un giorno feriale o lavorativo
            if (festivita.getSelectedToggle() != null) {
              prodotto = Prodotto.search(Long.parseLong(codiceProd.getText()));
              ordineCalcolato =
                  ModuleIAConverter.prevedi(
                      prodotto,
                      Double.parseDouble(prezzoSped.getText()),
                      ((RadioButton) stagione.getSelectedToggle()).getText(),
                      ((RadioButton) tipoSupermerc.getSelectedToggle()).getText(),
                      ((RadioButton) festivita.getSelectedToggle()).getText());
              System.out.println(ordineCalcolato);
              App.setRoot("OrdinaProdottoQuantitaSugg");
            } else {
              AlertMessage.showError("Selezionare una festività");
            }
          } else {
            AlertMessage.showError("Selezionare un tipo di supermercato");
          }
        } else {
          AlertMessage.showError("Selezionare una stagione");
        }
      } else {
        AlertMessage.showError("Inserire un prezzo spedizione valido");
      }
    } else {
      AlertMessage.showError("Inserire un codice prodotto valido");
    }
  }

  /**
   * Apre il form per l'inserimento manuale della quantità per la nuova richiesta d'acquisto.
   *
   * @param mouseEvent mouseEvent
   * @throws IOException Errore cambio scena
   */
  @FXML
  public void openOrdinaProdottoManu(ActionEvent mouseEvent) throws Exception {
    App.setRoot("OrdinaProdottoQuantitaManuForm");
  }

  /**
   * Esegue il salvataggio della richiesta d'acquisto con quantità suggerita.
   *
   * @param mouseEvent mouseEvent
   * @throws IOException Errore cambio scena
   * @throws DatabaseException Errore nel Database
   */
  @FXML
  public void confermaOrdine(MouseEvent mouseEvent) throws DatabaseException, IOException {
    RichiestaAcquisto ra = new RichiestaAcquisto();
    ra.setCodice(prodotto.getCodice());
    ra.setQuantity(ordineCalcolato);
    ra.save();
    AlertMessage.showInformation("Ordine effettuato con successo");
    App.setRoot("OrdinaProdottoForm");
  }

  /**
   * Esegue il salvataggio della richiesta d'acquisto con la quantità inserita manualmente.
   *
   * @param mouseEvent mouseEvent
   * @throws IOException Errore cambio scena
   * @throws DatabaseException Errore nel Database
   */
  @FXML
  public void confermaOrdineManu(MouseEvent mouseEvent) throws DatabaseException, IOException {
    // controllo se è stata inserita una quantità valida
    if (quantitaProd.getText().matches("[0-9]+(\\.[0-9][0-9]?)?")) {
      RichiestaAcquisto ra = new RichiestaAcquisto();
      ra.setCodice(prodotto.getCodice());
      ra.setQuantity(Integer.parseInt(quantitaProd.getText()));
      ra.save();
      AlertMessage.showInformation("Ordine effettuato con successo");
      App.setRoot("OrdinaProdottoForm");
    } else {
      AlertMessage.showError("Inserire una quantità valida");
    }
  }

  /////////////////////////////////////////////////////////

  // GET E SETTER PER TEST

  public void setNomeProd(TextField nomeProd) {
    this.nomeProd = nomeProd;
  }

  public void setNomeProd(String nomeProd) {
    this.nomeProd = new TextField();
    this.nomeProd.setText(nomeProd);
  }

  public void setPrezzoProd(TextField prezzoProd) {
    this.prezzoProd = prezzoProd;
  }

  public void setPrezzoProd(String prezzoProd) {
    this.prezzoProd = new TextField();
    this.prezzoProd.setText(prezzoProd);
  }

  public void setCodiceProd(TextField codiceProd) {
    this.codiceProd = codiceProd;
  }

  public void setCodiceProd(String codiceProd) {
    this.codiceProd = new TextField();
    this.codiceProd.setText(codiceProd);
  }

  public void setQuantitaProd(TextField quantitaProd) {
    this.quantitaProd = quantitaProd;
  }

  public void setQuantitaProd(String quantitaProd) {
    this.quantitaProd = new TextField();
    this.quantitaProd.setText(quantitaProd);
  }

  public TextField getQuantitaProd() {
    return quantitaProd;
  }

  public void setRiepilogoNuovoProdotto(TextField riepilogoNuovoProdotto) {
    this.riepilogoNuovoProdotto = riepilogoNuovoProdotto;
  }

  public void setRiepilogoNuovoProdotto(String riepilogoNuovoProdotto) {
    this.riepilogoNuovoProdotto = new TextField();
    this.riepilogoNuovoProdotto.setText(riepilogoNuovoProdotto);
  }

  public void setLabelOrdineCalcSugg(String labelOrdineCalcSugg) {
    this.labelOrdineCalcSugg = new Label();
    this.labelOrdineCalcSugg.setText(labelOrdineCalcSugg);
  }

  public void setLabelOrdineCalcSugg(Label labelOrdineCalcSugg) {
    this.labelOrdineCalcSugg = labelOrdineCalcSugg;
  }

  public void setRiepilogoProdotto(TextField riepilogoProdotto) {
    this.riepilogoProdotto = riepilogoProdotto;
  }

  public void setRiepilogoProdotto(String riepilogoProdotto) {
    this.riepilogoProdotto = new TextField();
    this.riepilogoProdotto.setText(riepilogoProdotto);
  }

  public void setLabelNomeProd(Label labelNomeProd) {
    this.labelNomeProd = labelNomeProd;
  }

  public void setLabelNomeProd(String labelNomeProd) {
    this.labelNomeProd = new Label();
    this.labelNomeProd.setText(labelNomeProd);
  }

  public void setLabelPrezzoProd(Label labelPrezzoProd) {
    this.labelPrezzoProd = labelPrezzoProd;
  }

  public void setLabelPrezzoProd(String labelPrezzoProd) {
    this.labelPrezzoProd = new Label();
    this.labelPrezzoProd.setText(labelPrezzoProd);
  }

  public void setLabelNomeProdSugg(Label labelNomeProdSugg) {
    this.labelNomeProdSugg = labelNomeProdSugg;
  }

  public void setLabelNomeProdSugg(String labelNomeProdSugg) {
    this.labelNomeProdSugg = new Label();
    this.labelNomeProdSugg.setText(labelNomeProdSugg);
  }

  public void setLabelQuantitaProdSugg(String labelQuantitaProdSugg) {
    this.labelQuantitaProdSugg = new Label();
    this.labelQuantitaProdSugg.setText(labelQuantitaProdSugg);
  }

  public void setLabelQuantitaProdSugg(Label labelQuantitaProdSugg) {
    this.labelQuantitaProdSugg = labelQuantitaProdSugg;
  }

  public void setTipologiaProd(TextField tipologiaProd) {
    this.tipologiaProd = tipologiaProd;
  }

  public void setTipologiaProd(String tipologiaProd) {
    this.tipologiaProd = new TextField();
    this.tipologiaProd.setText(tipologiaProd);
  }

  public void setPrezzoSped(String prezzoSped) {
    this.prezzoSped = new TextField();
    this.prezzoSped.setText(prezzoSped);
  }

  public void setPrezzoSped(TextField prezzoSped) {
    this.prezzoSped = prezzoSped;
  }

  public void setMedioDim(RadioButton medioDim) {
    this.medioDim = medioDim;
  }

  public void setMedioDim(boolean medioDim) {
    this.medioDim = new RadioButton();
    this.medioDim.setSelected(medioDim);
  }

  public void setPiccolaDim(boolean piccolaDim) {
    this.piccolaDim = new RadioButton();
    this.piccolaDim.setSelected(piccolaDim);
  }

  public void setPiccolaDim(RadioButton piccolaDim) {
    this.piccolaDim = piccolaDim;
  }

  public void setGrandeDim(boolean grandeDim) {
    this.grandeDim = new RadioButton();
    this.grandeDim.setSelected(grandeDim);
  }

  public void setGrandeDim(RadioButton grandeDim) {
    this.grandeDim = grandeDim;
  }

  public void setBreveSca(boolean breveSca) {
    this.breveSca = new RadioButton();
    this.breveSca.setSelected(breveSca);
  }

  public void setBreveSca(RadioButton breveSca) {
    this.breveSca = breveSca;
  }

  public void setMediaSca(boolean mediaSca) {
    this.mediaSca = new RadioButton();
    this.mediaSca.setSelected(mediaSca);
  }

  public void setMediaSca(RadioButton mediaSca) {
    this.mediaSca = mediaSca;
  }

  public void setLungaSca(boolean lungaSca) {
    this.lungaSca = new RadioButton();
    this.lungaSca.setSelected(lungaSca);
  }

  public void setLungaSca(RadioButton lungaSca) {
    this.lungaSca = lungaSca;
  }

  public void setPrimavera(boolean primavera) {
    this.primavera = new RadioButton();
    this.primavera.setSelected(primavera);
  }

  public void setPrimavera(RadioButton primavera) {
    this.primavera = primavera;
  }

  public void setEstate(RadioButton estate) {
    this.estate = estate;
  }

  public void setAutunno(boolean autunno) {
    this.autunno = new RadioButton();
    this.autunno.setSelected(autunno);
  }

  public void setAutunno(RadioButton autunno) {
    this.autunno = autunno;
  }

  public void setInverno(boolean inverno) {
    this.inverno = new RadioButton();
    this.inverno.setSelected(inverno);
  }

  public void setInverno(RadioButton inverno) {
    this.inverno = inverno;
  }

  public void setDimensioni(RadioButton dimensioni) {
    this.dimensioni = new ToggleGroup();
    dimensioni.setToggleGroup(this.dimensioni);
    this.dimensioni.selectToggle(dimensioni);
  }

  public void setDimensioni(ToggleGroup dimensioni) {
    this.dimensioni = dimensioni;
  }

  public void setScadenza(RadioButton scadenza) {
    this.scadenza = new ToggleGroup();
    scadenza.setToggleGroup(this.scadenza);
    this.scadenza.selectToggle(scadenza);
  }

  public void setScadenza(ToggleGroup scadenza) {
    this.scadenza = scadenza;
  }

  public void setStagione(RadioButton stagione) {
    this.stagione = new ToggleGroup();
    stagione.setToggleGroup(this.stagione);
    this.stagione.selectToggle(stagione);
  }

  public void setStagione(ToggleGroup stagione) {
    this.stagione = stagione;
  }

  public void setTipoSupermerc(RadioButton tipoSupermerc) {
    this.tipoSupermerc = new ToggleGroup();
    tipoSupermerc.setToggleGroup(this.tipoSupermerc);
    this.tipoSupermerc.selectToggle(tipoSupermerc);
  }

  public void setTipoSupermerc(ToggleGroup tipoSupermerc) {
    this.tipoSupermerc = tipoSupermerc;
  }

  public static Prodotto getProdotto() {
    return prodotto;
  }

  public static void setProdotto(Prodotto prodotto) {
    MagazzinoController.prodotto = prodotto;
  }

  public static Prodotto getTempProdotto() {
    return tempProdotto;
  }

  public static void setTempProdotto(Prodotto tempProdotto) {
    MagazzinoController.tempProdotto = tempProdotto;
  }

  public TextField getNomeProd() {
    return nomeProd;
  }

  public TextField getPrezzoProd() {
    return prezzoProd;
  }

  public TextField getCodiceProd() {
    return codiceProd;
  }

  public TextField getRiepilogoNuovoProdotto() {
    return riepilogoNuovoProdotto;
  }

  public TextField getRiepilogoProdotto() {
    return riepilogoProdotto;
  }

  public Label getLabelNomeProd() {
    return labelNomeProd;
  }

  public Label getLabelPrezzoProd() {
    return labelPrezzoProd;
  }

  public Label getLabelOrdineCalcSugg() {
    return labelOrdineCalcSugg;
  }

  public Label getLabelNomeProdSugg() {
    return labelNomeProdSugg;
  }

  public Label getLabelQuantitaProdSugg() {
    return labelQuantitaProdSugg;
  }

  public TextField getTipologiaProd() {
    return tipologiaProd;
  }

  public TextField getPrezzoSped() {
    return prezzoSped;
  }

  public RadioButton getPiccolaDim() {
    return piccolaDim;
  }

  public RadioButton getMedioDim() {
    return medioDim;
  }

  public RadioButton getGrandeDim() {
    return grandeDim;
  }

  public RadioButton getBreveSca() {
    return breveSca;
  }

  public RadioButton getMediaSca() {
    return mediaSca;
  }

  public RadioButton getLungaSca() {
    return lungaSca;
  }

  public RadioButton getPrimavera() {
    return primavera;
  }

  public RadioButton getEstate() {
    return estate;
  }

  public RadioButton getAutunno() {
    return autunno;
  }

  public RadioButton getInverno() {
    return inverno;
  }

  public static int getOrdineCalcolato() {
    return ordineCalcolato;
  }

  public ToggleGroup getDimensioni() {
    return dimensioni;
  }

  public ToggleGroup getScadenza() {
    return scadenza;
  }

  public ToggleGroup getStagione() {
    return stagione;
  }

  public ToggleGroup getTipoSupermerc() {
    return tipoSupermerc;
  }

  public void setFestivita(ToggleGroup festivita) {
    this.festivita = festivita;
  }

  public void setFestivita(RadioButton festivita) {
    this.festivita = new ToggleGroup();
    festivita.setToggleGroup(this.festivita);
    this.festivita.selectToggle(festivita);
  }

  public static void setOrdineCalcolato(int ordineCalcolato) {
    MagazzinoController.ordineCalcolato = ordineCalcolato;
  }
}
