package controller;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import entity.Prodotto;
import exceptions.DatabaseException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Magazzino implements Initializable {

  private static Prodotto nuovoProdotto;
  @FXML private TextField nomeProd;
  @FXML private TextField prezzoProd;
  @FXML private TextField codiceProd;
  @FXML private TextField quantitaProd;
  @FXML public TextField riepilogoNuovoProdotto;

  //Viene eseguito ogni volta che si carica una nuova finestra
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    String nomeFile =
        url.toString().substring(url.toString().lastIndexOf('/') + 1, url.toString().length());

    if (nomeFile.equals("InserisciNuovoProdottoRiepilogo.fxml")) {
      riepilogoNuovoProdotto.setText(nuovoProdotto.toString());
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

  public void inserisciProdotto(MouseEvent mouseEvent) {
    // Inserisci prodotto
  }

  ///////////////////////////////////////////////////////////////////////////////

  // INSERISCI NUOVO PRODOTTO

  public void openNuovoProdottoRiepilogo(MouseEvent mouseEvent) throws IOException {
    nuovoProdotto = new Prodotto();
    nuovoProdotto.setNome(nomeProd.getText());
    nuovoProdotto.setPrezzo(Integer.parseInt(prezzoProd.getText()));
    nuovoProdotto.setQuantity(Integer.parseInt(quantitaProd.getText()));
    nuovoProdotto.setCodice(Integer.parseInt(codiceProd.getText()));
    App.setRoot("InserisciNuovoProdottoRiepilogo");
  }

  public void inserisciNuovoProdotto(MouseEvent mouseEvent) throws DatabaseException {
    nuovoProdotto.createProdotto();
  }

  ///////////////////////////////////////////////////////////////////

  // MOD PREZZO PRODOTTO

  public void cercaProdottoModPrezzo(MouseEvent mouseEvent) {
    // Cerca prod by codice
  }

  /////////////////////////////////////////////////////////

  // ORDINA PRODOTTO

  public void cercaProdottoOrdinaProd(MouseEvent mouseEvent) {
    // Cerca prod by codice
  }

  /////////////////////////////////////////////////////////
}
