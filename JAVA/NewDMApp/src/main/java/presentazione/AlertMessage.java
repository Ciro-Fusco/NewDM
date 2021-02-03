package presentazione;

import javafx.scene.control.Alert;

/**
 * Crea i popup con gli errori e le info
 */
public class AlertMessage {

  private static Alert alert;

    /**
     * Crea un popup con il messaggio di errore, interrompendo il flusso
     * @param errore il messaggio di errore da mostrare
     */
  public static void showError(String errore) {
   alert = new Alert(Alert.AlertType.ERROR, errore);
    alert.show();
  }

    /**
     * Crea un popup con il messaggio di info.
     * @param info il messaggio di informazione da mostrare
     */
  public static void showInformation(String info) {
    alert = new Alert(Alert.AlertType.INFORMATION, info);
    alert.show();
  }
}
