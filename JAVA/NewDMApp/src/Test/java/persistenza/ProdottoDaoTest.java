package persistenza;

import business.inventario.Prodotto;
import exceptions.DatabaseException;
import exceptions.ProdottoException;
import exceptions.ProdottoNotFoundException;
import org.junit.AfterClass;
import org.junit.Test;
import persistenza.dao.ProdottoDao;

import static org.junit.Assert.*;

public class ProdottoDaoTest {

    @Test
    public void searchCorretto() throws DatabaseException, ProdottoException {
        
        Prodotto p = ProdottoDao.search(1000000000001L);
        assertNotEquals(null,p);
        DatabaseConnection.close();
    }


    @Test
    public void searchSbagliatoProdottoNonPresente() throws DatabaseException, ProdottoException {
        
        Exception ex = assertThrows(ProdottoNotFoundException.class,()->{
            Prodotto p =ProdottoDao.search(1L);
        });
        String expectedMessage = "Prodotto non trovato";
        String actualMessage = ex.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        DatabaseConnection.close();
    }

    @Test
    public void leavedbquantityCorretto() throws DatabaseException, ProdottoException {
        
        Prodotto p = Prodotto.search(1000000000001L);
        p.setAcquistato(1);
        p.leavedbquantity();
        Prodotto pPost = Prodotto.search(1000000000001L);
        assertEquals(p.getQuantity()-1,pPost.getQuantity());
        DatabaseConnection.close();
    }

    @Test
    public void adddbquantityCorretto() throws DatabaseException, ProdottoException {
        
        Prodotto p = Prodotto.search(1000000000001L);
        ProdottoDao.adddbquantity(1,p);
        Prodotto pPost = Prodotto.search(1000000000001L);
        assertEquals(p.getQuantity()+1,pPost.getQuantity());
        DatabaseConnection.close();
    }


    @Test
    public void createProdottoCorretto() throws DatabaseException, ProdottoException {
        
        Prodotto p = new Prodotto(10,2000000000002L,"Prova",10,"media","lunga","Alimentare");
        ProdottoDao.createProdotto(p);
        Prodotto pPost = Prodotto.search(2000000000002L);
        assertEquals(pPost,p);
        DatabaseConnection.close();
    }

    @Test
    public void createProdottoConnesioneErrata() throws DatabaseException, ProdottoException {
        
        Prodotto p = new Prodotto(10,3000000000003L,"Prova",10,"media","lunga","Alimentare");
        DatabaseConnection.close();
        Exception ex = assertThrows(DatabaseException.class,()->{
            ProdottoDao.createProdotto(p);
        });
    String expectedMessage = ("Errore nel salvataggio del Prodotto");
        String actualMessage = ex.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void modificaPrezzoCorretto() throws DatabaseException, ProdottoException {
        
        Prodotto p = Prodotto.search(1000000000001L);
        ProdottoDao.modificaPrezzo(p,2);
        Prodotto pPost = Prodotto.search(1000000000001L);
        assertEquals(2,pPost.getPrezzo(),0);
        DatabaseConnection.close();
    }

    @AfterClass
    public static void pulisciDb() throws DatabaseException {
        
        Prodotto p = new Prodotto();
        p.setCodice(2000000000002L);
        ProdottoDao.eliminaProdotto(p);
    }
}