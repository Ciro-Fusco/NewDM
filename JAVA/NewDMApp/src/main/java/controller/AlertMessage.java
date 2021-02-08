package controller;

import javafx.scene.control.Alert;

public class AlertMessage {

  private static Alert alert;

  public static void showError(String errore) {
   /* alert = new Alert(Alert.AlertType.ERROR, errore);
    alert.show();*/
  }

  public static void showInformation(String info) {
    /*alert = new Alert(Alert.AlertType.INFORMATION, info);
    alert.show();*/
  }
}
