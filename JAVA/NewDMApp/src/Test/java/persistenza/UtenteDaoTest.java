package persistenza;

import exceptions.DatabaseException;
import exceptions.UtenteNotFoundException;
import org.junit.Test;
import business.utenza.Utente;
import persistenza.dao.UtenteDao;

import static org.junit.Assert.*;

public class UtenteDaoTest {

  @Test
  public void loginCorretta() throws DatabaseException, UtenteNotFoundException {
    
    UtenteDao.login("cirofu", "cirofu");
    assertEquals("cirofu", Utente.getUsername());
    DatabaseConnection.close();
  }

  @Test
  public void loginSbagliato() throws DatabaseException, UtenteNotFoundException {
    
    Exception ex =
        assertThrows(
            UtenteNotFoundException.class,
            () -> {
              UtenteDao.login("cirofu", "ciro");
            });
    String expectedMessage = "Utente non trovato\nControlla username e password";
    String actualMessage = ex.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
    DatabaseConnection.close();
  }
}
