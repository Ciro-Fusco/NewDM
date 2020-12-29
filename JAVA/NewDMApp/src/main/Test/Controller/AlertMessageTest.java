package Controller;

import javafx.application.Platform;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlertMessageTest {

    @Test
    public void showError() {
        Platform.startup(()->{
            AlertMessage.showError("Errore");
        });
    }
}