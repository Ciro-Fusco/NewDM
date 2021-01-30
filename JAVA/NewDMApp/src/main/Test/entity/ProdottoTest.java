package entity;

import persistenza.DatabaseConnection;
import business.inventario.Prodotto;
import persistenza.ProdottoDao;
import exceptions.DatabaseException;
import exceptions.ProdottoException;
import org.junit.AfterClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProdottoTest {

  @Test
  public void creazioneProdottoCorretta() throws ProdottoException {

    Prodotto p = new Prodotto(4.56, 1010, "Gigia", 400, "Grande", "Media", "Frutta");
    assertNotEquals(null, p);
  }

/*  @Test
  public void creazioneProdottoQuantNonCorrretta() {
    Exception ex =
        assertThrows(
            ProdottoException.class,
            () -> {
              Prodotto p = new Prodotto(4.56, 1010L, "Gigia", -2, "Grande", "Media", "Frutta");
            });
    String expectedMessage = "Prezzo e quantità devono essere entrambi positivi";
    String actualMessage = ex.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }*/

 /* @Test
  public void creazioneProdottoPrezzoNonCorretta() {
    Exception ex =
        assertThrows(
            ProdottoException.class,
            () -> {
              Prodotto p = new Prodotto(0, 1010L, "Gigia", 2, "Grande", "Media", "Frutta");
            });
    String expectedMessage = "Prezzo e quantità devono essere entrambi positivi";
    String actualMessage = ex.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }*/

  @Test
  public void getPrezzo() {}

  @Test
  public void getNome() {}

  @Test
  public void getAcquistato() {}

  @Test
  public void updateAcquistato() {}

  @Test
  public void setAcquistato() {}

  @Test
  public void setPrezzoSbagliato() throws ProdottoException {
    Prodotto p = new Prodotto(4, 1010L, "Gigia", 5000, "Grande", "Media", "Frutta");
    Exception ex =
        assertThrows(
            ProdottoException.class,
            () -> {
              p.setPrezzo(-20000);
            });
    String expectedMessage = "Il prezzo del prodotto deve essere positivo";
    String actualMessage = ex.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  public void setPrezzoCorretto() throws ProdottoException {
    Prodotto p = new Prodotto(4, 1010L, "Gigia", 5000, "Grande", "Media", "Frutta");
    p.setPrezzo(200);
    assertEquals(200, p.getPrezzo(), 0);
  }

  @Test
  public void setQuantityCorretto() throws ProdottoException {
    Prodotto p = new Prodotto(4, 1010L, "Gigia", 5000, "Grande", "Media", "Frutta");
    p.setQuantity(1000);
    assertEquals(1000, p.getQuantity(), 0);
  }

  @Test
  public void setQuantitySbagliato() throws ProdottoException {
    Prodotto p = new Prodotto(4, 1010L, "Gigia", 5000, "Grande", "Media", "Frutta");
    Exception ex =
        assertThrows(
            ProdottoException.class,
            () -> {
              p.setQuantity(-20000);
            });
    String expectedMessage = "La quantità deve essere maggiore di 0";
    String actualMessage = ex.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  public void adddbquantityCorretto() throws ProdottoException, DatabaseException {
    DatabaseConnection.connect();
    Prodotto p = new Prodotto(4, 1010L, "Gigia", 5000, "Grande", "Media", "Frutta");
    p.createProdotto();
    p.adddbquantity(5);
    p = Prodotto.search(1010L);
    assertEquals(5005, p.getQuantity());
    DatabaseConnection.close();
  }

  @Test
  public void adddbquantitySbagliata() throws ProdottoException, DatabaseException {
    DatabaseConnection.connect();
    Prodotto p = new Prodotto(4, 1011L, "Gigia", 5000, "Grande", "Media", "Frutta");
    p.createProdotto();
    Exception ex =
        assertThrows(
            ProdottoException.class,
            () -> {
              p.adddbquantity(-5);
            });
    String expectedMessage = "Inserire un valore maggiore di 0";
    String actualMessage = ex.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
    DatabaseConnection.close();
  }

  @Test
  public void modificaPrezzoCorretto() throws DatabaseException, ProdottoException {
    DatabaseConnection.connect();
    Prodotto p = new Prodotto(4, 1012L, "Gigia", 5000, "Grande", "Media", "Frutta");
    p.createProdotto();
    p.modificaPrezzo(5);
    p = Prodotto.search(1012L);
    assertEquals(5, p.getPrezzo(), 0);
  }

  @Test
  public void modificaPrezzoSbagliata() throws ProdottoException, DatabaseException {
    DatabaseConnection.connect();
    Prodotto p = new Prodotto(4, 1013L, "Gigia", 5000, "Grande", "Media", "Frutta");
    p.createProdotto();
    Exception ex =
        assertThrows(
            ProdottoException.class,
            () -> {
              p.modificaPrezzo(-5);
            });
    String expectedMessage = "Prezzo nuovo negativo.";
    String actualMessage = ex.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  public void equalsCorretto() {
    Prodotto e = new Prodotto();
    boolean b = e.equals(e);
    assertEquals(true, b);
  }

  @Test
  public void equalsSbagliatoNull() {
    Prodotto e = new Prodotto();
    Prodotto f=null;
    boolean b = e.equals(f);
    assertEquals(false, b);
  }

  @Test
  public void equalsClasseSbagliata() {
    Prodotto e = new Prodotto();
    String f="p";
    boolean b = e.equals(f);
    assertEquals(false, b);
  }

  @Test
  public void equalsCorrettoOggettiDiversi() {
    Prodotto e = new Prodotto();
    e.setCodice(1);
    Prodotto c = new Prodotto();
    c.setCodice(1);
    boolean b = e.equals(c);
    assertEquals(true, b);
  }

  @Test
  public void equalsSbagliatoOggettiDiversi() {
    Prodotto e = new Prodotto();
    e.setCodice(1);
    Prodotto c = new Prodotto();
    c.setCodice(2);
    boolean b = e.equals(c);
    assertEquals(false, b);
  }

  @Test
  public void createProdottoCorretto() throws DatabaseException, ProdottoException {
    DatabaseConnection.connect();
    Prodotto p = new Prodotto(4, 1017L, "Gigia", 5000, "Grande", "Media", "Frutta");
    try{
      p.createProdotto();
    }catch (ProdottoException d){
      d.printStackTrace();
      assertFalse(true);
    }finally{
      DatabaseConnection.close();
    }
  }

  @Test
  public void createProdottoPrezzoNegativo() throws DatabaseException, ProdottoException {
   DatabaseConnection.connect();
    Prodotto p = new Prodotto(-4, 1018L, "Gigia", 5000, "Grande", "Media", "Frutta");
    Exception ex = assertThrows(ProdottoException.class,()->{
      p.createProdotto();
    });
    assertEquals("Prezzo e quantità devono essere entrambi positivi", ex.getMessage());
  DatabaseConnection.close();
  }

  @Test
  public void createProdottoQuantitaNegativa() throws DatabaseException, ProdottoException {
   DatabaseConnection.connect();
    Prodotto p = new Prodotto(4, 1019L, "Gigia", -5000, "Grande", "Media", "Frutta");
    Exception ex = assertThrows(ProdottoException.class,()->{
      p.createProdotto();
    });
    assertEquals("Prezzo e quantità devono essere entrambi positivi", ex.getMessage());
  DatabaseConnection.close();
  }

  @Test
  public void createProdottoQuantitaPrezzoNegativi() throws DatabaseException, ProdottoException {
   DatabaseConnection.connect();
    Prodotto p = new Prodotto(-4, 1019L, "Gigia", -5000, "Grande", "Media", "Frutta");
    Exception ex = assertThrows(ProdottoException.class,()->{
      p.createProdotto();
    });
    assertEquals("Prezzo e quantità devono essere entrambi positivi", ex.getMessage());
  DatabaseConnection.close();
  }

  @Test
  public void leaveDBquantityCorretto() throws DatabaseException, ProdottoException {
    DatabaseConnection.connect();
    Prodotto p = Prodotto.search(1000000000001l);
    try {
      p.leavedbquantity();
    }catch (ProdottoException ex ){
      assertFalse(true);
      ex.printStackTrace();
    }finally{
      DatabaseConnection.close();
    }

  }

  @Test
  public void leaveDBQuantitySbagliata() throws DatabaseException, ProdottoException {
    DatabaseConnection.connect();
    Prodotto p = Prodotto.search(1000000000001l);
    p.setQuantitySenzaControllo(0);
    Exception ex = assertThrows(ProdottoException.class,()->{
      p.leavedbquantity();
    });
    assertEquals("Prodotto esaurito", ex.getMessage());
    DatabaseConnection.close();
  }

  @AfterClass
  public static void pulisciDb() throws DatabaseException {
    DatabaseConnection.connect();
    Prodotto p = new Prodotto();
    p.setCodice(1010L);
    ProdottoDao.eliminaProdotto(p);
    p.setCodice(1011l);
    ProdottoDao.eliminaProdotto(p);
    p.setCodice(1012l);
    ProdottoDao.eliminaProdotto(p);
    p.setCodice(1013l);
    ProdottoDao.eliminaProdotto(p);
    p.setCodice(1017l);
    ProdottoDao.eliminaProdotto(p);
    p.setCodice(1018l);
    ProdottoDao.eliminaProdotto(p);
    p.setCodice(1019l);
    ProdottoDao.eliminaProdotto(p);
  }
}
