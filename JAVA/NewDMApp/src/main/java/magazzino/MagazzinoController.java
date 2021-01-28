package magazzino;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import start.AlertMessage;
import start.App;
import exceptions.DatabaseException;
import exceptions.ProdottoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

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

    if (nomeFile.equals("ModPrezzoProdottoPopUp.fxml")) {
      labelPrezzoProd.setText(Double.toString(prodotto.getPrezzo()));
      labelNomeProd.setText(prodotto.getNome());
    } else {
      System.out.println();
    }
  }

  public void openDashboardMagazzino(MouseEvent mouseEvent) throws IOException {
    App.setRoot("DashboardMagazzino");
  }

  // DASHBOARD MAGAZZINO

  public void openDashboard(MouseEvent mouseEvent) throws IOException {
    App.setRoot("Dashboard");
  }

  @FXML
  public void openInserisciProdotto(MouseEvent mouseEvent) throws Exception {
    App.setRoot("InserisciProdotto");
  }

  @FXML
  public void openInserisciNuovoProdotto(MouseEvent mouseEvent) throws Exception {
    App.setRoot("InserisciNuovoProdotto");
  }

  @FXML
  public void openOrdinaProdotto(MouseEvent mouseEvent) throws Exception {
    App.setRoot("OrdinaProdotto");
  }

  @FXML
  public void openModificaPrezzo(MouseEvent mouseEvent) throws Exception {
    App.setRoot("ModPrezzoProdotto");
  }

  ////////////////////////////////////////////////////////////////////////////

  // INSERISCI PRODOTTO GIA PRESENTE

  public void openInserisciProdottoRiepilogo(MouseEvent mouseEvent)
      throws IOException, ProdottoException, DatabaseException {

    // controllo che sia un codice prodotto valido
    if (codiceProd.getText().matches("^[0-9]{13}$")) {
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

  public void inserisciProdotto(MouseEvent mouseEvent)
      throws DatabaseException, IOException, ProdottoException {
    prodotto.adddbquantity(tempProdotto.getQuantity() - prodotto.getQuantity());
    AlertMessage.showInformation("Quantità aggiornata correttamente");
    App.setRoot("InserisciProdotto");
  }

  ///////////////////////////////////////////////////////////////////////////////

  // INSERISCI NUOVO PRODOTTO

  public void openNuovoProdottoRiepilogo(MouseEvent mouseEvent)
      throws IOException, ProdottoException {

    // controllo se è un nome prodotto valido
    if (nomeProd.getText().length() >= 2 && nomeProd.getText().length() <= 255) {
      // controllo se la quantita è positiva
      if (quantitaProd.getText().matches("^[1-9][0-9]*$")) {
        // controllo che sia un codice prodotto valido
        if (codiceProd.getText().matches("^[0-9]{13}$")) {
          // controllo che sia un prezzo valido, decimale con precisione di due
          if (prezzoProd.getText().matches("[0-9]+(\\.[0-9][0-9]?)?")) {
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

  public void inserisciNuovoProdotto(MouseEvent mouseEvent) throws DatabaseException, IOException, ProdottoException {
    prodotto.createProdotto();
    AlertMessage.showInformation("Prodotto inserito correttamente!");
    App.setRoot("InserisciNuovoProdotto");
  }

  ///////////////////////////////////////////////////////////////////

  // MOD PREZZO PRODOTTO

  public void aggiornaPrezzo(MouseEvent mouseEvent)
      throws DatabaseException, ProdottoException, IOException {

    // controllo che sia un prezzo valido, decimale con precisione di due
    if (prezzoProd.getText().matches("[0-9]+(\\.[0-9][0-9]?)?")) {
      prodotto.modificaPrezzo(Double.parseDouble(prezzoProd.getText()));
      prodotto.setPrezzo(Double.parseDouble(prezzoProd.getText()));
      AlertMessage.showInformation("Prezzo aggiornato con successo");
      App.setRoot("ModPrezzoProdotto");
    } else {
      AlertMessage.showError("Inserisci un prezzo valido");
    }
  }

  public void openModificaPrezzoPopUp(MouseEvent mouseEvent)
      throws ProdottoException, DatabaseException, IOException {

    // controllo che sia un codice prodotto valido
    if (codiceProd.getText().matches("^[0-9]{13}$")) {
      prodotto = Prodotto.search(Long.parseLong(codiceProd.getText()));
      App.setRoot("ModPrezzoProdottoPopUp");
    } else {
      AlertMessage.showError("Inserisci un codice prodotto valido");
    }
  }

  /////////////////////////////////////////////////////////

  // ORDINA PRODOTTO

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

  @FXML
  public void openOrdinaProdottoManu(ActionEvent mouseEvent) throws Exception {
    App.setRoot("OrdinaProdottoQuantitaManu");
  }

  public void confermaOrdine(MouseEvent mousevent) throws DatabaseException, IOException {
    RichiestaAcquisto ra = new RichiestaAcquisto();
    ra.setCodice(prodotto.getCodice());
    ra.setQuantity(ordineCalcolato);
    ra.save();
    AlertMessage.showInformation("Ordine effettuato con successo");
    App.setRoot("OrdineProdotto");
  }

  public void confermaOrdineManu(MouseEvent mousevent) throws DatabaseException, IOException {
    // controllo se è stata inserita una quantità valida
    if (quantitaProd.getText().matches("[0-9]+(\\.[0-9][0-9]?)?")) {
      RichiestaAcquisto ra = new RichiestaAcquisto();
      ra.setCodice(prodotto.getCodice());
      ra.setQuantity(Integer.parseInt(quantitaProd.getText()));
      ra.save();
      AlertMessage.showInformation("Ordine effettuato con successo");
      App.setRoot("OrdinaProdotto");
    } else {
      AlertMessage.showError("Inserire una quantità valida");
    }
  }

  /////////////////////////////////////////////////////////

  // GET E SETTER PER TEST

  public void setNomeProd(TextField nomeProd) {
    this.nomeProd = nomeProd;
  }

  public void setPrezzoProd(TextField prezzoProd) {
    this.prezzoProd = prezzoProd;
  }

  public void setCodiceProd(TextField codiceProd) {
    this.codiceProd = codiceProd;
  }

  public void setQuantitaProd(TextField quantitaProd) {
    this.quantitaProd = quantitaProd;
  }

  public void setRiepilogoNuovoProdotto(TextField riepilogoNuovoProdotto) {
    this.riepilogoNuovoProdotto = riepilogoNuovoProdotto;
  }

  public void setRiepilogoProdotto(TextField riepilogoProdotto) {
    this.riepilogoProdotto = riepilogoProdotto;
  }

  public void setLabelNomeProd(Label labelNomeProd) {
    this.labelNomeProd = labelNomeProd;
  }

  public void setLabelPrezzoProd(Label labelPrezzoProd) {
    this.labelPrezzoProd = labelPrezzoProd;
  }

  public void setLabelOrdineCalcSugg(Label labelOrdineCalcSugg) {
    this.labelOrdineCalcSugg = labelOrdineCalcSugg;
  }

  public void setLabelNomeProdSugg(Label labelNomeProdSugg) {
    this.labelNomeProdSugg = labelNomeProdSugg;
  }

  public void setLabelQuantitaProdSugg(Label labelQuantitaProdSugg) {
    this.labelQuantitaProdSugg = labelQuantitaProdSugg;
  }

  public void setTipologiaProd(TextField tipologiaProd) {
    this.tipologiaProd = tipologiaProd;
  }

  public void setPrezzoSped(TextField prezzoSped) {
    this.prezzoSped = prezzoSped;
  }

  public void setPiccolaDim(RadioButton piccolaDim) {
    this.piccolaDim = piccolaDim;
  }

  public void setMedioDim(RadioButton medioDim) {
    this.medioDim = medioDim;
  }

  public void setGrandeDim(RadioButton grandeDim) {
    this.grandeDim = grandeDim;
  }

  public void setBreveSca(RadioButton breveSca) {
    this.breveSca = breveSca;
  }

  public void setMediaSca(RadioButton mediaSca) {
    this.mediaSca = mediaSca;
  }

  public void setLungaSca(RadioButton lungaSca) {
    this.lungaSca = lungaSca;
  }

  public void setPrimavera(RadioButton primavera) {
    this.primavera = primavera;
  }

  public void setEstate(RadioButton estate) {
    this.estate = estate;
  }

  public void setAutunno(RadioButton autunno) {
    this.autunno = autunno;
  }

  public void setInverno(RadioButton inverno) {
    this.inverno = inverno;
  }

  public void setDimensioni(ToggleGroup dimensioni) {
    this.dimensioni = dimensioni;
  }

  public void setScadenza(ToggleGroup scadenza) {
    this.scadenza = scadenza;
  }

  public void setStagione(ToggleGroup stagione) {
    this.stagione = stagione;
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

  public void setNomeProd(String nomeProd) {
    this.nomeProd = new TextField();
    this.nomeProd.setText(nomeProd);
  }

  public TextField getPrezzoProd() {
    return prezzoProd;
  }

  public void setPrezzoProd(String prezzoProd) {
    this.prezzoProd = new TextField();
    this.prezzoProd.setText(prezzoProd);
  }

  public TextField getCodiceProd() {
    return codiceProd;
  }

  public void setCodiceProd(String codiceProd) {
    this.codiceProd = new TextField();
    this.codiceProd.setText(codiceProd);
  }

  public TextField getQuantitaProd() {
    return quantitaProd;
  }

  public void setQuantitaProd(String quantitaProd) {
    this.quantitaProd = new TextField();
    this.quantitaProd.setText(quantitaProd);
  }

  public TextField getRiepilogoNuovoProdotto() {
    return riepilogoNuovoProdotto;
  }

  public void setRiepilogoNuovoProdotto(String riepilogoNuovoProdotto) {
    this.riepilogoNuovoProdotto = new TextField();
    this.riepilogoNuovoProdotto.setText(riepilogoNuovoProdotto);
  }

  public TextField getRiepilogoProdotto() {
    return riepilogoProdotto;
  }

  public void setRiepilogoProdotto(String riepilogoProdotto) {
    this.riepilogoProdotto = new TextField();
    this.riepilogoProdotto.setText(riepilogoProdotto);
  }

  public Label getLabelNomeProd() {
    return labelNomeProd;
  }

  public void setLabelNomeProd(String labelNomeProd) {
    this.labelNomeProd = new Label();
    this.labelNomeProd.setText(labelNomeProd);
  }

  public Label getLabelPrezzoProd() {
    return labelPrezzoProd;
  }

  public void setLabelPrezzoProd(String labelPrezzoProd) {
    this.labelPrezzoProd = new Label();
    this.labelPrezzoProd.setText(labelPrezzoProd);
  }

  public Label getLabelOrdineCalcSugg() {
    return labelOrdineCalcSugg;
  }

  public void setLabelOrdineCalcSugg(String labelOrdineCalcSugg) {
    this.labelOrdineCalcSugg = new Label();
    this.labelOrdineCalcSugg.setText(labelOrdineCalcSugg);
  }

  public Label getLabelNomeProdSugg() {
    return labelNomeProdSugg;
  }

  public void setLabelNomeProdSugg(String labelNomeProdSugg) {
    this.labelNomeProdSugg = new Label();
    this.labelNomeProdSugg.setText(labelNomeProdSugg);
  }

  public Label getLabelQuantitaProdSugg() {
    return labelQuantitaProdSugg;
  }

  public void setLabelQuantitaProdSugg(String labelQuantitaProdSugg) {
    this.labelQuantitaProdSugg = new Label();
    this.labelQuantitaProdSugg.setText(labelQuantitaProdSugg);
  }

  public TextField getTipologiaProd() {
    return tipologiaProd;
  }

  public void setTipologiaProd(String tipologiaProd) {
    this.tipologiaProd = new TextField();
    this.tipologiaProd.setText(tipologiaProd);
  }

  public TextField getPrezzoSped() {
    return prezzoSped;
  }

  public void setPrezzoSped(String prezzoSped) {
    this.prezzoSped = new TextField();
    this.prezzoSped.setText(prezzoSped);
  }

  public RadioButton getPiccolaDim() {
    return piccolaDim;
  }

  public void setPiccolaDim(boolean piccolaDim) {
    this.piccolaDim = new RadioButton();
    this.piccolaDim.setSelected(piccolaDim);
  }

  public RadioButton getMedioDim() {
    return medioDim;
  }

  public void setMedioDim(boolean medioDim) {
    this.medioDim = new RadioButton();
    this.medioDim.setSelected(medioDim);
  }

  public RadioButton getGrandeDim() {
    return grandeDim;
  }

  public void setGrandeDim(boolean grandeDim) {
    this.grandeDim = new RadioButton();
    this.grandeDim.setSelected(grandeDim);
  }

  public RadioButton getBreveSca() {
    return breveSca;
  }

  public void setBreveSca(boolean breveSca) {
    this.breveSca = new RadioButton();
    this.breveSca.setSelected(breveSca);
  }

  public RadioButton getMediaSca() {
    return mediaSca;
  }

  public void setMediaSca(boolean mediaSca) {
    this.mediaSca = new RadioButton();
    this.mediaSca.setSelected(mediaSca);
  }

  public RadioButton getLungaSca() {
    return lungaSca;
  }

  public void setLungaSca(boolean lungaSca) {
    this.lungaSca = new RadioButton();
    this.lungaSca.setSelected(lungaSca);
  }

  public RadioButton getPrimavera() {
    return primavera;
  }

  public void setPrimavera(boolean primavera) {
    this.primavera = new RadioButton();
    this.primavera.setSelected(primavera);
  }

  public RadioButton getEstate() {
    return estate;
  }

  public void setEstate(boolean estate) {
    this.estate = new RadioButton();
    this.estate.setSelected(estate);
  }

  public RadioButton getAutunno() {
    return autunno;
  }

  public void setAutunno(boolean autunno) {
    this.autunno = new RadioButton();
    this.autunno.setSelected(autunno);
  }

  public RadioButton getInverno() {
    return inverno;
  }

  public void setInverno(boolean inverno) {
    this.inverno = new RadioButton();
    this.inverno.setSelected(inverno);
  }

  public static int getOrdineCalcolato() {
    return ordineCalcolato;
  }

  public ToggleGroup getDimensioni() {
    return dimensioni;
  }

  public void setDimensioni(RadioButton dimensioni) {
    this.dimensioni = new ToggleGroup();
    dimensioni.setToggleGroup(this.dimensioni);
    this.dimensioni.selectToggle(dimensioni);
  }

  public ToggleGroup getScadenza() {
    return scadenza;
  }

  public void setScadenza(RadioButton scadenza) {
    this.scadenza = new ToggleGroup();
    scadenza.setToggleGroup(this.scadenza);
    this.scadenza.selectToggle(scadenza);
  }

  public ToggleGroup getStagione() {
    return stagione;
  }

  public void setStagione(RadioButton stagione) {
    this.stagione = new ToggleGroup();
    stagione.setToggleGroup(this.stagione);
    this.stagione.selectToggle(stagione);
  }

  public ToggleGroup getTipoSupermerc() {
    return tipoSupermerc;
  }

  public void setTipoSupermerc(RadioButton tipoSupermerc) {
    this.tipoSupermerc = new ToggleGroup();
    tipoSupermerc.setToggleGroup(this.tipoSupermerc);
    this.tipoSupermerc.selectToggle(tipoSupermerc);
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
