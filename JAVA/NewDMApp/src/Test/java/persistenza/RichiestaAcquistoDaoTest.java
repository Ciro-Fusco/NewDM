package persistenza;

import business.fornitura.RichiestaAcquisto;
import exceptions.DatabaseException;
import org.junit.Test;

import static org.junit.Assert.*;

public class RichiestaAcquistoDaoTest {

  @Test
  public void saveCorretto() throws DatabaseException {
    DatabaseConnection.connect();
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

    @Test
    public void saveConnessioneErrata() throws DatabaseException {
        DatabaseConnection.connect();
        RichiestaAcquisto ra = new RichiestaAcquisto();
        ra.setQuantity(5);
        ra.setCodice(1000000000001L);
        DatabaseConnection.close();
        Exception ex = assertThrows(DatabaseException.class,()->{
            RichiestaAcquistoDao.save(ra);
        });
        String expectedMessage =
                ("Errore nel salvataggio della richiesta d'acquisto");
        String actualMessage = ex.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
