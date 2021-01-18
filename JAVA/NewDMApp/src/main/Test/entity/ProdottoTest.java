package entity;

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
  public void getCodice() {}

  @Test
  public void setCodice() {}

  @Test
  public void setNome() {}

  @Test
  public void getQuantity() {}

  @Test
  public void setQuantity() {}

  @Test
  public void search() {}

  @Test
  public void leavedbquantity() {}

  @Test
  public void adddbquantity() {}

  @Test
  public void createProdotto() {}

  @Test
  public void modificaPrezzo() {}
}
