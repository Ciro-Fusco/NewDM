package persistenza;

import business.cassa.Scontrino;
import exceptions.DatabaseException;
import exceptions.ProdottoException;
import exceptions.ScontrinoException;
import exceptions.ScontrinoNotFoundException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScontrinoDaoTest {

  @Test
  public void checkScontrinoCorretto() throws DatabaseException, ProdottoException {
    
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001L);
    s.save();
    try {
      ScontrinoDao.checkScontrino(s.getId(), s.getData().substring(0,10));
    } catch (ScontrinoNotFoundException ex) {
      ex.printStackTrace();
      assertFalse(true);
    } catch (ScontrinoException e) {
      e.printStackTrace();
      assertFalse(true);
    } finally {
      DatabaseConnection.close();
    }
  }

  @Test
  public void checkScontrinoNonTrovato() throws DatabaseException, ProdottoException {
    
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001L);
    Exception ex =
        assertThrows(
            ScontrinoNotFoundException.class,
            () -> {
              ScontrinoDao.checkScontrino(s.getId(), s.getData());
            });
    String expectedMessage = "Scontrino non trovato\nControlla il codice";
    String actualMessage = ex.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
    DatabaseConnection.close();
  }

  @Test
  public void checkScontrinoNonPiuInGaranzia() throws DatabaseException, ProdottoException {
    
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001L);
    s.setDataSbagliataTEST();
    s.save();
    Exception ex =
            assertThrows(
                    ScontrinoException.class,
                    () -> {
                      ScontrinoDao.checkScontrino(s.getId(), s.getData().substring(0,10));
                    });
    String expectedMessage =
        "Inserire una data valida, non precedente a 2 anni fa e non successiva alla data odierna";
    String actualMessage = ex.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
    DatabaseConnection.close();
  }
}
