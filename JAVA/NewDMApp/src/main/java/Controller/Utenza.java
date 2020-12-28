package Controller;

import java.io.IOException;

import Entity.Utente;
import db.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Utenza {

  public TextField us;
  public PasswordField pass;

  @FXML
  public void checkLogin(MouseEvent mouseEvent) throws Exception {
    if (DatabaseConnection.Connect())
      if (Utente.Login(us.getText(), pass.getText())) App.setRoot("Dashboard");
      else AlertMessage.ShowError("Inserire delle credenziali valide"); /*  Da inserire popup di errore a cura di Vincenzo*/
    ;
  }

  public void Logout(MouseEvent mouseEvent) throws Exception {

    DatabaseConnection.Close();
    Utente.clear();
    App.setRoot("Login");
  }

  @FXML
  public void OpenMagazzino(MouseEvent mouseEvent) throws Exception {
    App.setRoot("DashboardMagazzino");
  }

  @FXML
  public void OpenAssistenza(MouseEvent mouseEvent) throws Exception {
    App.setRoot("Assistenza");
  }

  @FXML
  public void OpenCassa(MouseEvent mouseEvent) throws Exception {
    App.setRoot("Cassa");
  }
}
