package entity;

import cassa.Scontrino;
import database.DatabaseConnection;
import exceptions.DatabaseException;
import exceptions.ProdottoException;
import exceptions.ScontrinoException;
import magazzino.Prodotto;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScontrinoTest {

    @Test
    public void addProdottoListNull() throws DatabaseException, ProdottoException {
        DatabaseConnection.connect();
        Scontrino s = new Scontrino();
        s.addProdotto(1000000000001L);
        assertEquals(Prodotto.search(1000000000001L),s.getList().get(s.getList().size()-1));
        DatabaseConnection.close();
    }

    @Test
    public void addProdottoListNotNull() throws DatabaseException, ProdottoException {
        DatabaseConnection.connect();
        Scontrino s = new Scontrino();
        s.addProdotto(1000000000001L);
        s.addProdotto(1000000000002L);
        assertEquals(Prodotto.search(1000000000002L),s.getList().get(s.getList().size()-1));
        DatabaseConnection.close();
    }

    @Test
    public void addProdottoListContains() throws DatabaseException, ProdottoException {
        DatabaseConnection.connect();
        Scontrino s = new Scontrino();
        s.addProdotto(1000000000001L);
        s.addProdotto(1000000000001L);
        assertEquals(2,s.getList().get(0).getAcquistato());
        DatabaseConnection.close();
    }

    @Test
    public void addProdottoListNotContains() throws DatabaseException, ProdottoException {
        DatabaseConnection.connect();
        Scontrino s = new Scontrino();
        s.addProdotto(1000000000001L);
        assertEquals(1,s.getList().get(0).getAcquistato());
        DatabaseConnection.close();
    }



    @Test
    public void setVersatoCorretto() throws DatabaseException, ProdottoException, ScontrinoException {
        DatabaseConnection.connect();
        Scontrino s = new Scontrino();
        s.addProdotto(1000000000001L);
        s.setVersato(500);
        assertEquals(500,s.getVersato(),0);
        DatabaseConnection.close();
    }

    @Test
    public void setVersatoSbagliata() throws DatabaseException, ProdottoException, ScontrinoException {
        DatabaseConnection.connect();
        Scontrino s = new Scontrino();
        s.addProdotto(1000000000001L);
        Exception ex =assertThrows(ScontrinoException.class,()->{
            s.setVersato(s.getTot()-0.01);
        });
        String expectedMessage = "Importo versato non sufficiente";
        String actualMessage = ex.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        DatabaseConnection.close();
    }
}