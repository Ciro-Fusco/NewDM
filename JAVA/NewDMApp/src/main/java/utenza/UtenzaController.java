package utenza;

import exceptions.UtenteNotAuthorizedException;
import start.App;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class UtenzaController {

  public TextField us;
  public PasswordField pass;
  public static boolean cliccatoMagazzino = false;
  public static boolean cliccatoAssistenza = false;
  public static boolean cliccatoCassa = false;

  /////////////////////////// LOGIN
  /**
   * Esegue il login controllando se le credenziali inserite sono valide
   *
   * @param mouseEvent
   * @throws IOException
   */
  @FXML
  public void checkLogin(MouseEvent mouseEvent) throws Exception {

    Utente.login(us.getText(), pass.getText());

    if (Utente.isAssistenza() && cliccatoAssistenza) {
      App.setRoot("AssistenzaForm");
      return;
    }
    if (Utente.isCassa() && cliccatoCassa) {
      App.setRoot("Cassa");
      return;
    }

    if (Utente.isMagazzino() && cliccatoMagazzino) {
      App.setRoot("DashboardMagazzino");
      return;
    }

    throw new UtenteNotAuthorizedException("Non hai i permessi per accedere a quest'area");
  }

  /**
   * Apre la dashboard iniziale
   *
   * @param mouseEvent
   * @throws IOException
   */
  @FXML
  public void openDashboard(MouseEvent mouseEvent) throws Exception {
    App.setRoot("Dashboard");
  }
  ////////////////////////////////////////////

  /////////////////////////// Dashboard
  /**
   * Apre la schermata di login per il magazzino
   *
   * @param mouseEvent
   * @throws IOException
   */
  @FXML
  public void openMagazzino(MouseEvent mouseEvent) throws Exception {
    this.cliccatoMagazzino = true;
    this.cliccatoCassa = false;
    this.cliccatoAssistenza = false;
    App.setRoot("LoginForm");
  }

  /**
   * Apre la schermata di login per l'assistenza
   *
   * @param mouseEvent
   * @throws IOException
   */
  @FXML
  public void openAssistenza(MouseEvent mouseEvent) throws Exception {
    this.cliccatoAssistenza = true;
    this.cliccatoCassa = false;
    this.cliccatoMagazzino = false;
    App.setRoot("LoginForm");
  }

  /**
   * Apre la schermata di login per la cassa
   *
   * @param mouseEvent
   * @throws IOException
   */
  @FXML
  public void openCassa(MouseEvent mouseEvent) throws Exception {
    this.cliccatoCassa = true;
    this.cliccatoMagazzino = false;
    this.cliccatoAssistenza = false;
    App.setRoot("LoginForm");
  }

  /////////////////////////////////////////////// 77

  // GET E SETTER PER TESTING

  public TextField getUs() {
    return us;
  }

  public void setUs(TextField us) {
    this.us = us;
  }

  public PasswordField getPass() {
    return pass;
  }

  public void setPass(PasswordField pass) {
    this.pass = pass;
  }
}
