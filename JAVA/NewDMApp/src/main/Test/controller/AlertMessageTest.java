package controller;

import javafx.application.Platform;
import org.junit.Test;

public class AlertMessageTest {

  @Test
  public void showError() {
    Platform.startup(
        () -> {
          AlertMessage.showError("Errore");
        });
  }
}
