package Controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class Magazzino {

  public void OpenDashboardMagazzino(MouseEvent mouseEvent) throws IOException {
    App.setRoot("DashboardMagazzino");
  }

  // DASHBOARD MAGAZZINO

  public void OpenDashboard(MouseEvent mouseEvent) throws IOException {
    App.setRoot("Dashboard");
  }

  @FXML
  public void OpenInserisciProdotto(MouseEvent mouseEvent) throws Exception {
    App.setRoot("InserisciProdotto");
  }

  @FXML
  public void OpenInserisciNuovoProdotto(MouseEvent mouseEvent) throws Exception {
    App.setRoot("InserisciNuovoProdotto");
  }

  @FXML
  public void OpenOrdinaProdotto(MouseEvent mouseEvent) throws Exception {
    App.setRoot("OrdinaProdotto");
  }

  @FXML
  public void OpenModificaPrezzo(MouseEvent mouseEvent) throws Exception {
    App.setRoot("ModPrezzoProdotto");
  }

  ////////////////////////////////////////////////////////////////////////////

  // INSERISCI PRODOTTO GIA PRESENTE

  public void InserisciProdotto(MouseEvent mouseEvent) {
    // Inserimento prodotto
  }

  ///////////////////////////////////////////////////////////////////////////////

  // INSERISCI NUOVO PRODOTTO

  public void InserisciNuovoProdotto(MouseEvent mouseEvent) {
    // Inserimento nuovo prodotto
  }

  ///////////////////////////////////////////////////////////////////

  // MOD PREZZO PRODOTTO

  public void CercaProdottoModPrezzo(MouseEvent mouseEvent) {
    // Cerca prod by codice
  }

  /////////////////////////////////////////////////////////

  // ORDINA PRODOTTO

  public void CercaProdottoOrdinaProd(MouseEvent mouseEvent) {
    // Cerca prod by codice
  }

  /////////////////////////////////////////////////////////
}
