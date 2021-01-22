package entity;

import db.DatabaseConnection;
import exceptions.DatabaseException;
import exceptions.ProdottoException;
import exceptions.ScontrinoException;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

public class ScontrinoTest {

    @Test
    public void addProdottoListNull() throws DatabaseException, ProdottoException {
        DatabaseConnection.connect();
        Scontrino s = new Scontrino();
        s.addProdotto(1L);
        assertEquals(Prodotto.search(1L),s.getList().get(s.getList().size()-1));
        DatabaseConnection.close();
    }

    @Test
    public void addProdottoListNotNull() throws DatabaseException, ProdottoException {
        DatabaseConnection.connect();
        Scontrino s = new Scontrino();
        s.addProdotto(1L);
        s.addProdotto(2L);
        assertEquals(Prodotto.search(2L),s.getList().get(s.getList().size()-1));
        DatabaseConnection.close();
    }

    @Test
    public void addProdottoListContains() throws DatabaseException, ProdottoException {
        DatabaseConnection.connect();
        Scontrino s = new Scontrino();
        s.addProdotto(1L);
        s.addProdotto(1L);
        assertEquals(2,s.getList().get(0).getAcquistato());
        DatabaseConnection.close();
    }

    @Test
    public void addProdottoListNotContains() throws DatabaseException, ProdottoException {
        DatabaseConnection.connect();
        Scontrino s = new Scontrino();
        s.addProdotto(1L);
        assertEquals(1,s.getList().get(0).getAcquistato());
        DatabaseConnection.close();
    }



    @Test
    public void setVersatoCorretto() throws DatabaseException, ProdottoException, ScontrinoException {
        DatabaseConnection.connect();
        Scontrino s = new Scontrino();
        s.addProdotto(1L);
        s.setVersato(500);
        assertEquals(500,s.getVersato(),0);
        DatabaseConnection.close();
    }

    @Test
    public void setVersatoSbagliata() throws DatabaseException, ProdottoException, ScontrinoException {
        DatabaseConnection.connect();
        Scontrino s = new Scontrino();
        s.addProdotto(1L);
        Exception ex =assertThrows(ScontrinoException.class,()->{
            s.setVersato(-5);
        });
        String expectedMessage = "Importo versato non sufficiente";
        String actualMessage = ex.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        DatabaseConnection.close();
    }
}