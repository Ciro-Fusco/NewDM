package presentazione;

import business.cassa.Scontrino;
import exceptions.DatabaseException;
import exceptions.ProdottoException;
import exceptions.ScontrinoException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import business.utenza.Utente;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CassaController implements Initializable {

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

    if (nomeFile.equals("CassaTotaleForm.fxml")) {
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

  // CassaController

  /**
   * Apre la dashboard
   *
   * @param mouseEvent
   * @throws IOException
   */
  public void openDashboard(MouseEvent mouseEvent) throws IOException, DatabaseException {
    Utente.logout();
    App.setRoot("Dashboard");
  }

  /**
   * Apre la schermata di inserimento del codice Prodotto
   *
   * @param mouseEvent
   * @throws IOException
   */
  public void openCassaInsProdotto(MouseEvent mouseEvent) throws IOException {
    App.setRoot("CassaInsProdottoForm");
  }

  /**
   * Apre la schermata di riepilogo dello Scontrino
   *
   * @param mouseEvent
   * @throws IOException
   */
  public void openCassaTotale(MouseEvent mouseEvent) throws IOException {
    if (scontrino != null && scontrino.getList() != null) {
      App.setRoot("CassaTotaleForm");
    } else {
      AlertMessage.showError("Per procedere, aggiungere almeno un prodotto allo scontrino");
    }
  }

  /**
   * Elimina lo Scontrino attuale
   *
   * @param mouseEvent
   */
  public void annullaInsProd(MouseEvent mouseEvent) {
    scontrino = new Scontrino();
    scontrinoTextField.setText("");
  }
  //////////////////////////////////////////////////////////////////

  // inserimento prodotto

  /**
   * Apre la schermata CassaController
   *
   * @param mouseEvent
   * @throws IOException
   */
  public void openCassa(MouseEvent mouseEvent) throws IOException {
    App.setRoot("Cassa");
  }

  /**
   * Inserisce il Prodotto nello scontrino
   *
   * @param mouseEvent
   * @throws ProdottoException Prodotto non trovato
   * @throws DatabaseException Errore nel Database
   */
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

  /**
   * Gestisce l'inserimento della somma versata
   *
   * @param mouseEvent
   * @throws IOException
   * @throws ScontrinoException
   */
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

  // Riepilogo CassaController

  /**
   * Salva lo Scontrino
   *
   * @param mouseEvent
   * @throws DatabaseException
   * @throws IOException
   */
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
    CassaController.scontrino = scontrino;
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
