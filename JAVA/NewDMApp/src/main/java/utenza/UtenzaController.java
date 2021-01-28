package utenza;

import start.App;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class UtenzaController {

  public TextField us;
  public PasswordField pass;

  @FXML
  public void checkLogin(MouseEvent mouseEvent) throws Exception {
    Utente.login(us.getText(), pass.getText());
    App.setRoot("Dashboard");
  }

  public void logout(MouseEvent mouseEvent) throws Exception {

    Utente.logout();
    App.setRoot("Login");
  }

  @FXML
  public void openMagazzino(MouseEvent mouseEvent) throws Exception {
    App.setRoot("DashboardMagazzino");
  }

  @FXML
  public void openAssistenza(MouseEvent mouseEvent) throws Exception {
    App.setRoot("Assistenza");
  }

  @FXML
  public void openCassa(MouseEvent mouseEvent) throws Exception {
    App.setRoot("Cassa");
  }

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
