package persistenza;

import business.cassa.Scontrino;
import exceptions.DatabaseException;
import exceptions.ElencaException;
import exceptions.ProdottoException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ElencaDaoTest {

  @Test
  public void checkCorrispondenzaCorretta()
      throws DatabaseException, ProdottoException, ElencaException {
    
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001L);
    s.save();
    try {
      ElencaDao.checkCorrispondenza(s.getId(), s.getData().substring(0,10), 1000000000001L);
    } catch (ElencaException e) {
      e.printStackTrace();
      assertFalse(true);
    } finally {
      DatabaseConnection.close();
    }
  }

  @Test
  public void checkCorrispondenzaSbagliato() throws DatabaseException, ProdottoException {
    
    Scontrino s = new Scontrino();
    Exception ex =
        assertThrows(
            ElencaException.class,
            () -> {
              ElencaDao.checkCorrispondenza(s.getId(), s.getData(), 1000000000001L);
            });
    String expectedMessage = "Prodotto non correlato allo Scontrino immesso";
    String actualMessage = ex.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
    DatabaseConnection.close();
  }

}
