package entity;

import db.DatabaseConnection;
import exceptions.DatabaseException;
import exceptions.ProdottoException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProdottoTest {

  @Test
  public void creazioneProdottoCorretta() throws ProdottoException {

      Prodotto p = new Prodotto(4.56, 1010, "Gigia",400,"Grande","Media","Frutta");
      assertNotEquals(null,p);
  }

  @Test
  public void creazioneProdottoQuantNonCorrretta() {
    Exception ex =assertThrows(ProdottoException.class,()->{
       Prodotto p= new Prodotto(4.56, 1010L, "Gigia", -2,"Grande","Media","Frutta");
    });
    String expectedMessage = "Prezzo e quantità devono essere entrambi positivi";
    String actualMessage = ex.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  public void creazioneProdottoPrezzoNonCorrretta() {
    Exception ex =assertThrows(ProdottoException.class,()->{
      Prodotto p= new Prodotto(0, 1010L, "Gigia", 2,"Grande","Media","Frutta");
    });
    String expectedMessage = "Prezzo e quantità devono essere entrambi positivi";
    String actualMessage = ex.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

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
    Prodotto p= new Prodotto(4, 1010L, "Gigia", 5000,"Grande","Media","Frutta");
    Exception ex =assertThrows(ProdottoException.class,()->{
      p.setPrezzo(-20000);
    });
    String expectedMessage = "Il prezzo del prodotto deve essere positivo";
    String actualMessage = ex.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  public void setPrezzoCorretto() throws ProdottoException {
    Prodotto p= new Prodotto(4, 1010L, "Gigia", 5000,"Grande","Media","Frutta");
      p.setPrezzo(200);
      assertEquals(200,p.getPrezzo(),0);
  }

  @Test
  public void setQuantityCorretto() throws ProdottoException {
    Prodotto p= new Prodotto(4, 1010L, "Gigia", 5000,"Grande","Media","Frutta");
    p.setQuantity(1000);
    assertEquals(1000,p.getQuantity(),0);
  }

  @Test
  public void setQuantitySbagliato() throws ProdottoException {
    Prodotto p= new Prodotto(4, 1010L, "Gigia", 5000,"Grande","Media","Frutta");
    Exception ex =assertThrows(ProdottoException.class,()->{
      p.setQuantity(-20000);
    });
    String expectedMessage = "La quantità deve essere maggiore di 0";
    String actualMessage = ex.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  public void adddbquantityCorretto() throws ProdottoException, DatabaseException {
    //se da errore significa che è gia presente questo prodotto nel db, quindi eliminalo e riesegui
    DatabaseConnection.connect();
    Prodotto p= new Prodotto(4, 1010L, "Gigia", 5000,"Grande","Media","Frutta");
    p.createProdotto();
    p.adddbquantity(5);
    p=Prodotto.search(1010L);
    assertEquals(5005,p.getQuantity());
  }

  @Test
  public void adddbquantitySbagliata() throws ProdottoException, DatabaseException {
    //se da errore significa che è gia presente questo prodotto nel db, quindi eliminalo e riesegui
    DatabaseConnection.connect();
    Prodotto p= new Prodotto(4, 1011L, "Gigia", 5000,"Grande","Media","Frutta");
    p.createProdotto();
    Exception ex =assertThrows(ProdottoException.class,()->{
      p.adddbquantity(-5);
    });
    String expectedMessage = "Inserire un valore maggiore di 0";
    String actualMessage = ex.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }


  @Test
  public void modificaPrezzoCorretto() throws DatabaseException, ProdottoException {
    //se da errore significa che è gia presente questo prodotto nel db, quindi eliminalo e riesegui
    DatabaseConnection.connect();
    Prodotto p= new Prodotto(4, 1012L, "Gigia", 5000,"Grande","Media","Frutta");
    p.createProdotto();
    p.modificaPrezzo(5);
    p=Prodotto.search(1012L);
    assertEquals(5,p.getPrezzo(),0);
  }

  @Test
  public void modificaPrezzoSbagliata() throws ProdottoException, DatabaseException {
    //se da errore significa che è gia presente questo prodotto nel db, quindi eliminalo e riesegui
    DatabaseConnection.connect();
    Prodotto p= new Prodotto(4, 1013L, "Gigia", 5000,"Grande","Media","Frutta");
    p.createProdotto();
    Exception ex =assertThrows(ProdottoException.class,()->{
      p.modificaPrezzo(-5);
    });
    String expectedMessage = "Prezzo nuovo negativo.";
    String actualMessage = ex.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

}
