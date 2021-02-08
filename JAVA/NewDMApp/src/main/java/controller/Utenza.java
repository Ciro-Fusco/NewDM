package controller;

import entity.Utente;
import db.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Utenza {

  public TextField us;
  public PasswordField pass;

    @FXML
  public void checkLogin(MouseEvent mouseEvent) throws Exception {
      if (Utente.login(us.getText(), pass.getText())) {
        App.setRoot("Dashboard");
    }
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
}
