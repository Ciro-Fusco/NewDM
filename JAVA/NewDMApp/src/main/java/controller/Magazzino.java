package controller;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import entity.Prodotto;
import exceptions.DatabaseException;
import exceptions.ProdottoNotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Magazzino implements Initializable {

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

  // Viene eseguito ogni volta che si carica una nuova finestra
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    String nomeFile =
        url.toString().substring(url.toString().lastIndexOf('/') + 1, url.toString().length());

    if (nomeFile.equals("InserisciNuovoProdottoRiepilogo.fxml")) {
      riepilogoNuovoProdotto.setText(prodotto.toString());
    }

    if (nomeFile.equals("InserisciProdottoRiepilogo.fxml")) {
      riepilogoProdotto.setText(tempProdotto.toString());
    }

    if (nomeFile.equals("ModPrezzoProdottoPopUp.fxml")) {
      labelPrezzoProd.setText(Double.toString(prodotto.getPrezzo()));
      labelNomeProd.setText(prodotto.getNome());
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
      throws IOException, ProdottoNotFoundException, DatabaseException {
    try {
      tempProdotto = new Prodotto();
      prodotto = Prodotto.search(Long.parseLong(codiceProd.getText()));
      tempProdotto.setQuantity(prodotto.getQuantity() + Integer.parseInt(quantitaProd.getText()));
      tempProdotto.setCodice(prodotto.getCodice());
      tempProdotto.setPrezzo(prodotto.getPrezzo());
      tempProdotto.setNome(prodotto.getNome());
      tempProdotto.setAcquistato(prodotto.getAcquistato());
      App.setRoot("InserisciProdottoRiepilogo");
    } catch (NumberFormatException excpt) {
      excpt.printStackTrace();
      AlertMessage.showError("Compila i campi in modo corretto");
    }
  }

  public void inserisciProdotto(MouseEvent mouseEvent) throws DatabaseException, IOException {
    prodotto.adddbquantity(tempProdotto.getQuantity() - prodotto.getQuantity());
    AlertMessage.showInformation("Quantità aggiornata correttamente");
    App.setRoot("InserisciProdotto");
  }

  ///////////////////////////////////////////////////////////////////////////////

  // INSERISCI NUOVO PRODOTTO

  public void openNuovoProdottoRiepilogo(MouseEvent mouseEvent) throws IOException {
    try {
      prodotto = new Prodotto();
      prodotto.setNome(nomeProd.getText());
      prodotto.setPrezzo(Integer.parseInt(prezzoProd.getText()));
      prodotto.setQuantity(Integer.parseInt(quantitaProd.getText()));
      prodotto.setCodice(Integer.parseInt(codiceProd.getText()));
      App.setRoot("InserisciNuovoProdottoRiepilogo");
    } catch (NumberFormatException exception) {
      exception.printStackTrace();
      AlertMessage.showError("Compila i campi in modo corretto");
    }
  }

  public void inserisciNuovoProdotto(MouseEvent mouseEvent) throws DatabaseException, IOException {
    prodotto.createProdotto();
    AlertMessage.showInformation("Prodotto inserito correttamente!");
    App.setRoot("InserisciNuovoProdottoRiepilogo");
  }

  ///////////////////////////////////////////////////////////////////

  // MOD PREZZO PRODOTTO

  public void aggiornaPrezzo(MouseEvent mouseEvent) throws DatabaseException {
    try {
      prodotto.modificaPrezzo(Double.parseDouble(prezzoProd.getText()));
      prodotto.setPrezzo(Double.parseDouble(prezzoProd.getText()));
      AlertMessage.showInformation("Prezzo aggiornato con successo");
      labelPrezzoProd.setText(Double.toString(prodotto.getPrezzo()));
    } catch (NumberFormatException exception) {
      exception.printStackTrace();
      AlertMessage.showError("Compila i campi in modo corretto");
    }
  }

  public void openModificaPrezzoPopUp(MouseEvent mouseEvent)
      throws ProdottoNotFoundException, DatabaseException, IOException {
    try {
      prodotto = Prodotto.search(Long.parseLong(codiceProd.getText()));
      App.setRoot("ModPrezzoProdottoPopUp");
    } catch (NumberFormatException exception) {
      exception.printStackTrace();
      AlertMessage.showError("Compila i campi in modo corretto");
    }
  }

  /////////////////////////////////////////////////////////

  // ORDINA PRODOTTO

  public void cercaProdottoOrdinaProd(MouseEvent mouseEvent) {
    // Cerca prod by codice
  }

  /////////////////////////////////////////////////////////
}
