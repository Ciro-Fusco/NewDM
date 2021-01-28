package start;

import database.DatabaseConnection;
import de.saxsys.javafx.test.JfxRunner;
import exceptions.UtenteNotFoundException;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.Test;
import org.junit.runner.RunWith;
import utenza.UtenzaController;

import static org.junit.Assert.*;

@RunWith(JfxRunner.class)
public class UtenzaControllerTest {

  @Test
  public void checkLoginCorretto() throws Exception {
    DatabaseConnection.connect();
    UtenzaController u = new UtenzaController();
    TextField user = new TextField();
    user.setText("cirofu");
    u.setUs(user);
    PasswordField pass = new PasswordField();
    pass.setText("cirofu");
    u.setPass(pass);
    Exception ex =
        assertThrows(
            NullPointerException.class,
            () -> {
              u.checkLogin(null);
            });
    String expectedMessage = "NullPointerException";
    String actualMessage = ex.getClass().getName();
    assertTrue(actualMessage.contains(expectedMessage));
    DatabaseConnection.close();
  }

  @Test
  public void checkLoginSbagliato() throws Exception {
    DatabaseConnection.connect();
    UtenzaController u = new UtenzaController();
    TextField user = new TextField();
    user.setText("cirofu");
    u.setUs(user);
    PasswordField pass = new PasswordField();
    pass.setText("ddjdjdj");
    u.setPass(pass);
      Exception ex =
              assertThrows(
                      UtenteNotFoundException.class,
                      () -> {
                          u.checkLogin(null);
                      });
      String expectedMessage = "Utente non trovato";
      String actualMessage = ex.getMessage();
      assertTrue(actualMessage.contains(expectedMessage));
      DatabaseConnection.close();
  }
}
