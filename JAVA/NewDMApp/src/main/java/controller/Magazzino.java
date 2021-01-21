package controller;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import entity.Prodotto;
import exceptions.DatabaseException;
import exceptions.ProdottoException;
import exceptions.ProdottoNotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
  @FXML private TextField tipologiaProd;
  @FXML private RadioButton piccolaDim;
  @FXML private RadioButton medioDim;
  @FXML private RadioButton grandeDim;
  @FXML private RadioButton breveSca;
  @FXML private RadioButton mediaSca;
  @FXML private RadioButton lungaSca;
  @FXML private ToggleGroup dimensioni;
  @FXML private ToggleGroup scadenza;

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
                  prodotto.setDimensioneConfezione(((RadioButton)dimensioni.getSelectedToggle()).getText());
                  prodotto.setScadenza(((RadioButton)scadenza.getSelectedToggle()).getText());
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

  public void inserisciNuovoProdotto(MouseEvent mouseEvent) throws DatabaseException, IOException {
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

  public void cercaProdottoOrdinaProd(MouseEvent mouseEvent) {
    // Cerca prod by codice
  }

  /////////////////////////////////////////////////////////
}
