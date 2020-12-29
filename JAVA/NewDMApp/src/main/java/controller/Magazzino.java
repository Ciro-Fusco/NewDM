package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class Magazzino {

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
    // Inserimento prodotto
  }

  ///////////////////////////////////////////////////////////////////////////////

  // INSERISCI NUOVO PRODOTTO

  public void inserisciNuovoProdotto(MouseEvent mouseEvent) {
    // Inserimento nuovo prodotto
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
