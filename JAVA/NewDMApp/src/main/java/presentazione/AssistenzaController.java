package presentazione;

import business.assistenza.Ticket;
import business.cassa.Scontrino;
import business.inventario.Prodotto;
import exceptions.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import business.utenza.Utente;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Controller per le interazioni della sezione Assistenza
 */
public class AssistenzaController {

  private static Ticket ticket;
  @FXML private TextField nomeCognCli;
  @FXML private TextField telefonoCli;
  @FXML private TextField codFiscCli;
  @FXML private TextField indirizzoResiCli;
  @FXML private TextField tipoProdotto;
  @FXML private TextField nomeProdotto;
  @FXML private TextField codProdotto;
  @FXML private TextField numSerieProd;
  @FXML private TextField numScontrino;
  @FXML private TextField dataScontrino;
  @FXML private TextField dettagliProb;

  /**
   * Apre la dashboard principale
   *
   * @param mouseEvent
   * @throws IOException
   */
  public void openDashboard(MouseEvent mouseEvent) throws IOException, DatabaseException {
    Utente.logout();
    App.setRoot("Dashboard");
  }

  /**
   * Crea un nuovo Ticket con i dati forniti a video dall'utente
   *
   * @param mouseEvent
   * @throws IOException
   * @throws ScontrinoException
   * @throws ElencaException
   * @throws ProdottoException
   * @throws DatabaseException
   */
  @FXML // DIVIDI NOME E COGNOME IN DUE CAMPI SEPARATI,ANCHE NELLA UI
  public void openAssistenzaDettagliProb(MouseEvent mouseEvent)
      throws IOException, ScontrinoException, ElencaException, ProdottoException,
          DatabaseException {

    // controllo la lunghezza del nome del cliente
    if (nomeCognCli.getText().length() >= 2 && nomeCognCli.getText().length() <= 255) {
      // controllo la lunghezza dell'indirizzo
      if (indirizzoResiCli.getText().length() >= 2 && indirizzoResiCli.getText().length() <= 255) {
        // controllo che il numero di telefono sia scritto correttamente
        if (telefonoCli.getText().matches("^[0-9]{10,11}$")) {
          // controllo la lunghezza del nome del prodotto
          if (nomeProdotto.getText().length() >= 2 && nomeProdotto.getText().length() <= 255) {
            // controllo la lunghezza del tipo del prodotto
            if (tipoProdotto.getText().length() >= 2 && tipoProdotto.getText().length() <= 255) {
              // controllo che il codice del prodotto sia scritto correttamente
              if (codProdotto.getText().matches("^[0-9]{13}$")) {
                Prodotto.search(Long.parseLong(codProdotto.getText()));
                // controllo la lunghezza del numero di serie del prodotto
                if (numSerieProd.getText().length() >= 2
                    && numSerieProd.getText().length() <= 255) {
                  // controllo se la data è scritta correttamente
                  if (dataScontrino
                      .getText()
                      .matches(
                          "^([0]?[1-9]|[1|2][0-9]|[3][0|1])[-]([0]?[1-9]|[1][0-2])[-]([0-9]{4})$")) {
                    String data_temp = dataScontrino.getText();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDateTime data_obj = LocalDate.parse(data_temp, formatter).atStartOfDay();
                    LocalDateTime data_2_years_ago = LocalDateTime.now().minusYears(2);
                    if (data_obj.isBefore(data_2_years_ago))
                      throw new ScontrinoNonValidoException(
                          "Inserire una data valida, non precedente a 2 anni fa e non successiva alla data odierna");
                    // controllo che il numero delo scontrino sia scritto correttamente
                    if (numScontrino.getText().matches("^[1-9][0-9]*$")) {
                      Scontrino.checkScontrino(
                          Long.parseLong(numScontrino.getText()), dataScontrino.getText());
                      // controllo che il codice fiscale sia scritto correttamente
                      if (codFiscCli
                          .getText()
                          .matches("^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$")) {
                        ticket =
                            new Ticket(
                                nomeCognCli.getText(),
                                codFiscCli.getText(),
                                indirizzoResiCli.getText(),
                                tipoProdotto.getText(),
                                nomeProdotto.getText(),
                                numSerieProd.getText(),
                                Long.parseLong(telefonoCli.getText()),
                                Integer.parseInt(numScontrino.getText()),
                                dataScontrino.getText(),
                                Long.parseLong(codProdotto.getText()));
                        App.setRoot("AssistenzaDettagliProbForm");
                      } else {
                        AlertMessage.showError("Inserire un codice fiscale valido");
                      }
                    } else {
                      AlertMessage.showError("Inserire un numero di scontrino valido");
                    }
                  } else {
                    AlertMessage.showError("Inserire una data valida");
                  }
                } else {
                  AlertMessage.showError("Inserire un numero di serie valido");
                }
              } else {
                AlertMessage.showError("Inserire un codice prodotto valido (13 cifre)");
              }
            } else {
              AlertMessage.showError("Inserire un tipo di prodotto valido");
            }
          } else {
            AlertMessage.showError("Inserire un nome prodotto valido");
          }
        } else {
          AlertMessage.showError("Inserire un numero di telefono valido");
        }
      } else {
        AlertMessage.showError("Inserire un indirizzo valido");
      }
    } else {
      AlertMessage.showError("Inserire nome e cognome validi");
    }
  }
  ////////////////////////////////////////////////////////////

  // AssistenzaController dettagli prob

  /**
   * Apre la schermata di assistenza
   *
   * @param mouseEvent
   * @throws IOException
   */
  public void openAssistenza(MouseEvent mouseEvent) throws IOException {
    ticket = null;
    App.setRoot("AssistenzaForm");
  }

  /**
   * Usa le informazioni inserite a video per impostare il problema per cui si chiede assistenza e
   * poi rende il Ticket persistente
   *
   * @param mouseEvent
   * @throws DatabaseException
   * @throws IOException
   */
  public void salvaTicket(MouseEvent mouseEvent) throws DatabaseException, IOException {
    if (!dettagliProb.getText().equals("")) {
      ticket.setProblema(dettagliProb.getText());
      ticket.save();
      AlertMessage.showInformation("Ticket salvato con successo");
      App.setRoot("AssistenzaForm");
      ticket = null;
    } else {
      AlertMessage.showError("Inserire i dettagli del problema");
    }
  }
  /////////////////////////////////////////////////////

  // GET E SETTER PER TESTING

  public static Ticket getTicket() {
    return ticket;
  }

  public static void setTicket(Ticket ticket) {
    AssistenzaController.ticket = ticket;
  }

  public TextField getNomeCognCli() {
    return nomeCognCli;
  }

  public void setNomeCognCli(TextField nomeCognCli) {
    this.nomeCognCli = nomeCognCli;
  }

  public TextField getTelefonoCli() {
    return telefonoCli;
  }

  public void setTelefonoCli(TextField telefonoCli) {
    this.telefonoCli = telefonoCli;
  }

  public TextField getCodFiscCli() {
    return codFiscCli;
  }

  public void setCodFiscCli(TextField codFiscCli) {
    this.codFiscCli = codFiscCli;
  }

  public TextField getIndirizzoResiCli() {
    return indirizzoResiCli;
  }

  public void setIndirizzoResiCli(TextField indirizzoResiCli) {
    this.indirizzoResiCli = indirizzoResiCli;
  }

  public TextField getTipoProdotto() {
    return tipoProdotto;
  }

  public void setTipoProdotto(TextField tipoProdotto) {
    this.tipoProdotto = tipoProdotto;
  }

  public TextField getNomeProdotto() {
    return nomeProdotto;
  }

  public void setNomeProdotto(TextField nomeProdotto) {
    this.nomeProdotto = nomeProdotto;
  }

  public TextField getCodProdotto() {
    return codProdotto;
  }

  public void setCodProdotto(TextField codProdotto) {
    this.codProdotto = codProdotto;
  }

  public TextField getNumSerieProd() {
    return numSerieProd;
  }

  public void setNumSerieProd(TextField numSerieProd) {
    this.numSerieProd = numSerieProd;
  }

  public TextField getNumScontrino() {
    return numScontrino;
  }

  public void setNumScontrino(TextField numScontrino) {
    this.numScontrino = numScontrino;
  }

  public TextField getDataScontrino() {
    return dataScontrino;
  }

  public void setDataScontrino(TextField dataScontrino) {
    this.dataScontrino = dataScontrino;
  }

  public TextField getDettagliProb() {
    return dettagliProb;
  }

  public void setDettagliProb(TextField dettagliProb) {
    this.dettagliProb = dettagliProb;
  }
}
