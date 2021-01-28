package database;

import exceptions.DatabaseException;
import exceptions.UtenteNotFoundException;
import org.junit.Test;
import utenza.Utente;
import utenza.UtenteDao;

import static org.junit.Assert.*;

public class UtenteDaoTest {

  @Test
  public void loginCorretta() throws DatabaseException, UtenteNotFoundException {
    DatabaseConnection.connect();
    UtenteDao.login("cirofu", "cirofu");
    assertEquals("cirofu", Utente.getUsername());
    DatabaseConnection.close();
  }

  @Test
  public void loginSbagliato() throws DatabaseException, UtenteNotFoundException {
    DatabaseConnection.connect();
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
