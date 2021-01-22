package db;

import entity.Scontrino;
import exceptions.DatabaseException;
import exceptions.ElencaException;
import exceptions.ProdottoException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ElencaDaoTest {

  @Test
  public void checkCorrispondenzaCorretta()
      throws DatabaseException, ProdottoException, ElencaException {
    DatabaseConnection.connect();
    Scontrino s = new Scontrino();
    s.addProdotto(1L);
    s.save();
      try {
          ElencaDao.checkCorrispondenza(s.getId(), s.getData(), 1L);
      } catch (ElencaException e) {
          e.printStackTrace();
          assertFalse(true);
      }
  }

    @Test
    public void checkCorrispondenzaSbagliato()
            throws DatabaseException, ProdottoException {
        DatabaseConnection.connect();
        Scontrino s = new Scontrino();
        Exception ex =assertThrows(ElencaException.class,()->{
            ElencaDao.checkCorrispondenza(s.getId(),s.getData(),1L);
        });
        String expectedMessage = "Prodotto non correlato allo Scontrino immesso";
        String actualMessage = ex.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
