package persistenza;

import business.fornitura.RichiestaAcquisto;
import exceptions.DatabaseException;
import org.junit.Test;
import persistenza.dao.RichiestaAcquistoDao;

import static org.junit.Assert.*;

public class RichiestaAcquistoDaoTest {

  @Test
  public void saveCorretto() throws DatabaseException {
    
    RichiestaAcquisto ra = new RichiestaAcquisto();
    ra.setQuantity(5);
    ra.setCodice(1000000000001L);
    try {
      RichiestaAcquistoDao.save(ra);
    } catch (DatabaseException ex) {
      ex.printStackTrace();
      assertFalse(true);
    } finally {
      DatabaseConnection.close();
    }
  }
}
