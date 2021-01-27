package cassa;

import controller.AlertMessage;
import controller.App;
import exceptions.DatabaseException;
import exceptions.ProdottoException;
import exceptions.ScontrinoException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Cassa implements Initializable {

  private static Scontrino scontrino;
  @FXML private TextField codiceProd;
  @FXML private TextArea scontrinoTextField;
  @FXML private TextField sommaVersataTextField;
  @FXML private Label totaleLabel;
  @FXML private TextArea riepilogoTextArea;
  @FXML private Label restoLabel;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    String nomeFile =
        url.toString().substring(url.toString().lastIndexOf('/') + 1, url.toString().length());

    if (nomeFile.equals("Cassa.fxml")) {
      if (scontrino != null) {
        scontrinoTextField.appendText(scontrino.getRiepilogo());
      } else {
        System.out.println();
      }
    } else {
      System.out.println();
    }

    if (nomeFile.equals("CassaTotale.fxml")) {
      scontrino.calcolaTot();
      totaleLabel.setText(Double.toString(scontrino.getTot()));
    } else {
      System.out.println();
    }

    if (nomeFile.equals("CassaRiepilogo.fxml")) {
      if (scontrino != null) {
        scontrino.calcolaResto();
        restoLabel.setText(String.format("%.2f", scontrino.getResto()));
        riepilogoTextArea.setText(scontrino.getRiepilogo());
      } else {
        System.out.println();
      }
    } else {
      System.out.println();
    }
  }

  // Cassa
  public void openDashboard(MouseEvent mouseEvent) throws IOException {
    App.setRoot("Dashboard");
  }

  public void openCassaInsProdotto(MouseEvent mouseEvent) throws IOException {
    App.setRoot("CassaInsProdotto");
  }

  public void openCassaTotale(MouseEvent mouseEvent) throws IOException {
    if (scontrino != null && scontrino.getList() != null) {
      App.setRoot("CassaTotale");
    } else {
      AlertMessage.showError("Per procedere, aggiungere almeno un prodotto allo scontrino");
    }
  }

  public void annullaInsProd(MouseEvent mouseEvent) {
    scontrino = new Scontrino();
    scontrinoTextField.setText("");
  }
  //////////////////////////////////////////////////////////////////

  // inserimento prodotto

  public void openCassa(MouseEvent mouseEvent) throws IOException {
    App.setRoot("Cassa");
  }

  public void inserimentoProdotto(MouseEvent mouseEvent)
      throws ProdottoException, DatabaseException {
    try {
      if (scontrino == null) {
        scontrino = new Scontrino();
      } else {
        System.out.println();
      }
      ;
      scontrino.addProdotto(Long.parseLong(codiceProd.getText()));
      AlertMessage.showInformation("Prodotto inserito correttamente");
    } catch (NumberFormatException excpt) {
      excpt.printStackTrace();
      AlertMessage.showError("Compilare i campi in modo corretto");
    }
  }

  ////////////////////////////////////////////////////////////////

  // Totale cassa
  public void openCassaRiepilogo(MouseEvent mouseEvent) throws IOException, ScontrinoException {
    if (sommaVersataTextField.getText().matches("[0-9]+(\\.[0-9][0-9]?)?")) {
      try {
        scontrino.setVersato(Double.parseDouble(sommaVersataTextField.getText()));
        App.setRoot("CassaRiepilogo");
      } catch (NumberFormatException exception) {
        exception.printStackTrace();
        AlertMessage.showError("Compila i campi in modo corretto");
      }
    } else {
      AlertMessage.showError("Inserisci una somma valida");
    }
  }

  ////////////////////////////////////////////////////////////////

  // Riepilogo Cassa
  public void confermaScontrino(MouseEvent mouseEvent) throws DatabaseException, IOException {
    scontrino.save();
    AlertMessage.showInformation("Scontrino salvato");
    scontrino = null;
    App.setRoot("Cassa");
  }

  /////////////////////////////////////////////////////////////////

  // GET E SETTER PER TEST

  public static Scontrino getScontrino() {
    return scontrino;
  }

  public static void setScontrino(Scontrino scontrino) {
    Cassa.scontrino = scontrino;
  }

  public TextField getCodiceProd() {
    return codiceProd;
  }

  public void setCodiceProd(TextField codiceProd) {
    this.codiceProd = codiceProd;
  }

  public TextArea getScontrinoTextField() {
    return scontrinoTextField;
  }

  public void setScontrinoTextField(TextArea scontrinoTextField) {
    this.scontrinoTextField = scontrinoTextField;
  }

  public TextField getSommaVersataTextField() {
    return sommaVersataTextField;
  }

  public void setSommaVersataTextField(TextField sommaVersataTextField) {
    this.sommaVersataTextField = sommaVersataTextField;
  }

  public Label getTotaleLabel() {
    return totaleLabel;
  }

  public void setTotaleLabel(Label totaleLabel) {
    this.totaleLabel = totaleLabel;
  }

  public TextArea getRiepilogoTextArea() {
    return riepilogoTextArea;
  }

  public void setRiepilogoTextArea(TextArea riepilogoTextArea) {
    this.riepilogoTextArea = riepilogoTextArea;
  }

  public Label getRestoLabel() {
    return restoLabel;
  }

  public void setRestoLabel(Label restoLabel) {
    this.restoLabel = restoLabel;
  }
}
