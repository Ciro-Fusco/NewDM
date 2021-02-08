package controller;

import entity.Ticket;
import exceptions.DatabaseException;
import exceptions.ElencaException;
import exceptions.ProdottoException;
import exceptions.ScontrinoException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.regex.Pattern;

public class Assistenza {

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

  // Assistenza
  public void openDashboard(MouseEvent mouseEvent) throws IOException {
    App.setRoot("Dashboard");
  }

  @FXML //DIVIDI NOME E COGNOME IN DUE CAMPI SEPARATI,ANCHE NELLA UI
  public void openAssistenzaDettagliProb(MouseEvent mouseEvent)
      throws IOException, ScontrinoException, ElencaException, ProdottoException,
          DatabaseException {

    // controllo che i campi non siano vuoti
    if (!nomeCognCli.getText().equals("")
        && !codFiscCli.getText().equals("")
        && !indirizzoResiCli.getText().equals("")
        && !tipoProdotto.getText().equals("")
        && !nomeProdotto.getText().equals("")
        && !numSerieProd.getText().equals("")
        && !telefonoCli.getText().equals("")
        && !numScontrino.getText().equals("")
        && !dataScontrino.getText().equals("")
        && !codProdotto.getText().equals("")) {

      // controllo la lunghezza del nome del cliente
      if (nomeCognCli.getText().length() >= 2 && nomeCognCli.getText().length() <= 255) {
        // controllo la lunghezza dell'indirizzo
        if (indirizzoResiCli.getText().length() >= 2
            && indirizzoResiCli.getText().length() <= 255) {
          // controllo che il numero di telefono sia scritto correttamente
          if (telefonoCli.getText().matches("^[0-9]{10,11}$")) {
            // controllo la lunghezza del nome del prodotto
            if (nomeProdotto.getText().length() >= 2 && nomeProdotto.getText().length() <= 255) {
              // controllo la lunghezza del tipo del prodotto
              if (tipoProdotto.getText().length() >= 2 && tipoProdotto.getText().length() <= 255) {
                // controllo che il codice del prodotto sia scritto correttamente
                if (codProdotto.getText().matches("^[0-9]{13}$")) {
                  // controllo la lunghezza del numero di serie del prodotto
                  if (numSerieProd.getText().length() >= 2
                      && numSerieProd.getText().length() <= 255) {
                    // controllo se la data è scritta correttamente
                    if (dataScontrino
                        .getText()
                        .matches(
                            "^([0]?[1-9]|[1|2][0-9]|[3][0|1])[-]([0]?[1-9]|[1][0-2])[-]([0-9]{4}|[0-9]{2})$")) {
                      // controllo che il numero delo scontrino sia scritto correttamente
                      if (numScontrino.getText().matches("^[1-9][0-9]*$")) {
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
                          App.setRoot("AssistenzaDettagliProb");
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
        AlertMessage.showError("Inserire un nome valido");
      }
    } else {
      AlertMessage.showError("Compilare tutti i campi");
    }
  }
  ////////////////////////////////////////////////////////////

  // Assistenza dettagli prob

  public void openAssistenza(MouseEvent mouseEvent) throws IOException {
    ticket = null;
    App.setRoot("Assistenza");
  }

  public void salvaTicket(MouseEvent mouseEvent) throws DatabaseException, IOException {
    if (!dettagliProb.getText().equals("")) {
      ticket.setProblema(dettagliProb.getText());
      ticket.save();
      AlertMessage.showInformation("Ticket salvato con successo");
      App.setRoot("Assistenza");
      ticket = null;
    } else {
      AlertMessage.showError("Inserire i dettagli del problema");
    }
  }
  /////////////////////////////////////////////////////
}
