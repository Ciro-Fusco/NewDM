package start;

import persistenza.DatabaseConnection;
import de.saxsys.javafx.test.JfxRunner;
import exceptions.UtenteNotAuthorizedException;
import exceptions.UtenteNotFoundException;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.Test;
import org.junit.runner.RunWith;
import business.utenza.Utente;
import presentazione.UtenzaController;

import static org.junit.Assert.*;

@RunWith(JfxRunner.class)
public class UtenzaControllerTest {

  @Test
  public void checkLoginCorrettoAssistenza() throws Exception {
    
    UtenzaController u = new UtenzaController();
    TextField user = new TextField();
    user.setText("cirofu");
    u.setUs(user);
    PasswordField pass = new PasswordField();
    pass.setText("cirofu");
    u.setPass(pass);
    Utente.setAssistenza(true);
    UtenzaController.setCliccatoAssistenza(true);
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
  public void checkLoginCorrettoAssistenzaIsAssistenzaFalse() throws Exception {
    
    UtenzaController u = new UtenzaController();
    TextField user = new TextField();
    user.setText("test");
    u.setUs(user);
    PasswordField pass = new PasswordField();
    pass.setText("test");
    u.setPass(pass);
    UtenzaController.setCliccatoAssistenza(true);
    UtenzaController.setCliccatoCassa(false);
    UtenzaController.setCliccatoMagazzino(false);
    Exception ex =
            assertThrows(
                    UtenteNotAuthorizedException.class,
                    () -> {
                      u.checkLogin(null);
                    });
    String expectedMessage = "Non hai i permessi per accedere a quest'area";
    String actualMessage = ex.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
    DatabaseConnection.close();
  }

  @Test
  public void checkLoginCorrettoAssistenzaCliccatoAsssistenzaFalse() throws Exception {
    
    UtenzaController u = new UtenzaController();
    TextField user = new TextField();
    user.setText("cirofu");
    u.setUs(user);
    PasswordField pass = new PasswordField();
    pass.setText("cirofu");
    u.setPass(pass);
    UtenzaController.setCliccatoAssistenza(false);
    UtenzaController.setCliccatoCassa(false);
    UtenzaController.setCliccatoMagazzino(false);
    Exception ex =
            assertThrows(
                    UtenteNotAuthorizedException.class,
                    () -> {
                      u.checkLogin(null);
                    });
    String expectedMessage = "Non hai i permessi per accedere a quest'area";
    String actualMessage = ex.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
    DatabaseConnection.close();
  }

  @Test
  public void checkLoginCorrettoCassa() throws Exception {
    
    UtenzaController u = new UtenzaController();
    TextField user = new TextField();
    user.setText("cirofu");
    u.setUs(user);
    PasswordField pass = new PasswordField();
    pass.setText("cirofu");
    u.setPass(pass);
    UtenzaController.setCliccatoAssistenza(false);
    UtenzaController.setCliccatoCassa(true);
    UtenzaController.setCliccatoMagazzino(false);
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
  public void checkLoginSbaliatoCassaIsCassaFalse() throws Exception {
    
    UtenzaController u = new UtenzaController();
    TextField user = new TextField();
    user.setText("test");
    u.setUs(user);
    PasswordField pass = new PasswordField();
    pass.setText("test");
    u.setPass(pass);
    UtenzaController.setCliccatoAssistenza(false);
    UtenzaController.setCliccatoCassa(true);
    UtenzaController.setCliccatoMagazzino(false);
    Exception ex =
            assertThrows(
                    UtenteNotAuthorizedException.class,
                    () -> {
                      u.checkLogin(null);
                    });
    String expectedMessage = "Non hai i permessi per accedere a quest'area";
    String actualMessage = ex.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
    DatabaseConnection.close();
  }
  @Test
  public void checkLoginCorrettoCassaCassaFalse() throws Exception {
    
    UtenzaController u = new UtenzaController();
    TextField user = new TextField();
    user.setText("cirofu");
    u.setUs(user);
    PasswordField pass = new PasswordField();
    pass.setText("cirofu");
    u.setPass(pass);
    Utente.setAssistenza(false);
    Utente.setCassa(true);
    UtenzaController.setCliccatoAssistenza(false);
    UtenzaController.setCliccatoMagazzino(false);
    UtenzaController.setCliccatoCassa(false);
    Exception ex =
            assertThrows(
                    UtenteNotAuthorizedException.class,
                    () -> {
                      u.checkLogin(null);
                    });
    String expectedMessage = "Non hai i permessi per accedere a quest'area";
    String actualMessage = ex.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
    DatabaseConnection.close();
  }

  @Test
  public void checkLoginCorrettoMagazzino() throws Exception {
    
    UtenzaController u = new UtenzaController();
    TextField user = new TextField();
    user.setText("cirofu");
    u.setUs(user);
    PasswordField pass = new PasswordField();
    pass.setText("cirofu");
    u.setPass(pass);
    UtenzaController.setCliccatoAssistenza(false);
    UtenzaController.setCliccatoCassa(false);
    UtenzaController.setCliccatoMagazzino(true);
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
  public void checkLoginSbaliatoMagazziniIsMagazzinoFalse() throws Exception {
    
    UtenzaController u = new UtenzaController();
    TextField user = new TextField();
    user.setText("test");
    u.setUs(user);
    PasswordField pass = new PasswordField();
    pass.setText("test");
    u.setPass(pass);
    UtenzaController.setCliccatoAssistenza(false);
    UtenzaController.setCliccatoCassa(false);
    UtenzaController.setCliccatoMagazzino(true);
    Exception ex =
            assertThrows(
                    UtenteNotAuthorizedException.class,
                    () -> {
                      u.checkLogin(null);
                    });
    String expectedMessage = "Non hai i permessi per accedere a quest'area";
    String actualMessage = ex.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
    DatabaseConnection.close();
  }
  @Test
  public void checkLoginCorrettoMagazzinoMagazzinoFalse() throws Exception {
    
    UtenzaController u = new UtenzaController();
    TextField user = new TextField();
    user.setText("cirofu");
    u.setUs(user);
    PasswordField pass = new PasswordField();
    pass.setText("cirofu");
    u.setPass(pass);
    UtenzaController.setCliccatoAssistenza(false);
    UtenzaController.setCliccatoCassa(false);
    UtenzaController.setCliccatoMagazzino(false);
    Exception ex =
            assertThrows(
                    UtenteNotAuthorizedException.class,
                    () -> {
                      u.checkLogin(null);
                    });
    String expectedMessage = "Non hai i permessi per accedere a quest'area";
    String actualMessage = ex.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
    DatabaseConnection.close();
  }

  @Test
  public void checkLoginSbagliato() throws Exception {
    
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
