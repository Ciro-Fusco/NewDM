package start;

import cassa.CassaController;
import database.DatabaseConnection;
import de.saxsys.javafx.test.JfxRunner;
import cassa.Scontrino;
import exceptions.DatabaseException;
import exceptions.ProdottoException;
import exceptions.ScontrinoException;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.*;

@RunWith(JfxRunner.class)
public class CassaControllerTest {

  @Test
  public void initializeCorrettoCassa() throws IOException, DatabaseException, ProdottoException {
    DatabaseConnection.connect();
    CassaController c = new CassaController();
    URL url =
        new File(System.getProperty("user.dir") + "\\src\\main\\resouncers\\CassaController.fxml")
            .toURI()
            .toURL();
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    CassaController.setScontrino(s);
    c.setScontrinoTextField(new TextArea());
    c.initialize(url, null);
    assertEquals(s.getRiepilogo(), c.getScontrinoTextField().getText());
    DatabaseConnection.close();
  }

  @Test
  public void initializeScontrinoNullCassa()
      throws IOException, DatabaseException, ProdottoException {
    DatabaseConnection.connect();
    CassaController c = new CassaController();
    URL url =
        new File(System.getProperty("user.dir") + "\\src\\main\\resouncers\\CassaController.fxml")
            .toURI()
            .toURL();
    CassaController.setScontrino(null);
    c.setScontrinoTextField(new TextArea());
    c.initialize(url, null);
    assertEquals("", c.getScontrinoTextField().getText());
    DatabaseConnection.close();
  }

  @Test
  public void initializeCorrettoCassaTotale()
      throws IOException, DatabaseException, ProdottoException {
    DatabaseConnection.connect();
    CassaController c = new CassaController();
    URL url =
        new File(System.getProperty("user.dir") + "\\src\\main\\resouncers\\CassaTotale.fxml")
            .toURI()
            .toURL();
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    CassaController.setScontrino(s);
    c.setTotaleLabel(new Label());
    c.initialize(url, null);
    assertEquals(Double.toString(s.getTot()), c.getTotaleLabel().getText());
    DatabaseConnection.close();
  }

  @Test
  public void initializeCorrettoCassaRiepilogo()
      throws IOException, DatabaseException, ProdottoException {
    DatabaseConnection.connect();
    CassaController c = new CassaController();
    URL url =
        new File(System.getProperty("user.dir") + "\\src\\main\\resouncers\\CassaRiepilogo.fxml")
            .toURI()
            .toURL();
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    CassaController.setScontrino(s);
    c.setRestoLabel(new Label());
    c.setRiepilogoTextArea(new TextArea());
    c.initialize(url, null);
    assertEquals(s.getRiepilogo(), c.getRiepilogoTextArea().getText());
    assertEquals(String.format("%.2f", s.getResto()), c.getRestoLabel().getText());
    DatabaseConnection.close();
  }

  @Test
  public void initializeSbagliatoScontrinoNullCassaRiepilogo()
      throws IOException, DatabaseException, ProdottoException {
    DatabaseConnection.connect();
    CassaController c = new CassaController();
    URL url =
        new File(System.getProperty("user.dir") + "\\src\\main\\resouncers\\CassaRiepilogo.fxml")
            .toURI()
            .toURL();
    CassaController.setScontrino(null);
    c.setRestoLabel(new Label());
    c.setRiepilogoTextArea(new TextArea());
    c.initialize(url, null);
    assertEquals("", c.getRiepilogoTextArea().getText());
    assertEquals("", c.getRestoLabel().getText());
    DatabaseConnection.close();
  }

  @Test
  public void openCassaTotaleCorretto() throws DatabaseException, ProdottoException, IOException {
    // uso l'eccezzione per controllare se il test è andato a buon fine perchè App.setRoot(..) manda
    // nullPointerException
    DatabaseConnection.connect();
    CassaController c = new CassaController();
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    CassaController.setScontrino(s);
    Exception ex =
        assertThrows(
            NullPointerException.class,
            () -> {
              c.openCassaTotale(null);
            });
    String expectedMessage = "NullPointerException";
    String actualMessage = ex.getClass().getName();
    assertTrue(actualMessage.contains(expectedMessage));
    DatabaseConnection.close();
  }

  @Test
  public void openCassaTotaleSbagliatoScontrinoNull()
      throws DatabaseException, ProdottoException, IOException {
    DatabaseConnection.connect();
    CassaController c = new CassaController();
    CassaController.setScontrino(null);
    try {
      c.openCassaTotale(null);
    } catch (NullPointerException ex) {
      ex.printStackTrace();
      assertFalse(true);
    } finally {
      DatabaseConnection.close();
    }
  }

  @Test
  public void openCassaTotaleSbagliatoScontrinoVuoto()
      throws DatabaseException, ProdottoException, IOException {
    DatabaseConnection.connect();
    CassaController c = new CassaController();
    Scontrino s = new Scontrino();
    CassaController.setScontrino(s);
    try {
      c.openCassaTotale(null);
    } catch (NullPointerException ex) {
      ex.printStackTrace();
      assertFalse(true);
    } finally {
      DatabaseConnection.close();
    }
  }

  @Test
  public void inserimentoProdottoCorretto() throws DatabaseException, ProdottoException {
    DatabaseConnection.connect();
    CassaController c = new CassaController();
    Scontrino s = new Scontrino();
    CassaController.setScontrino(s);
    TextField t = new TextField();
    t.setText("1000000000001");
    c.setCodiceProd(t);
    c.inserimentoProdotto(null);
    assertEquals(1, CassaController.getScontrino().getList().size());
    DatabaseConnection.close();
  }

  @Test
  public void inserimentoProdottoScontrinoNull() throws DatabaseException, ProdottoException {
    DatabaseConnection.connect();
    CassaController c = new CassaController();
    CassaController.setScontrino(null);
    TextField t = new TextField();
    t.setText("1000000000001");
    c.setCodiceProd(t);
    c.inserimentoProdotto(null);
    assertEquals(1, CassaController.getScontrino().getList().size());
    DatabaseConnection.close();
  }

  @Test
  public void openCassaRiepilogoCorretto()
      throws DatabaseException, ProdottoException, IOException, ScontrinoException {
    DatabaseConnection.connect();
    CassaController c = new CassaController();
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    CassaController.setScontrino(s);
    TextField t = new TextField();
    t.setText("50");
    c.setSommaVersataTextField(t);
    Exception ex =
        assertThrows(
            NullPointerException.class,
            () -> {
              c.openCassaRiepilogo(null);
              ;
            });
    String expectedMessage = "NullPointerException";
    String actualMessage = ex.getClass().getName();
    assertTrue(actualMessage.contains(expectedMessage));
    DatabaseConnection.close();
  }

  @Test
  public void openCassaRiepilogoSommaVersataSbagliata()
      throws DatabaseException, ProdottoException, IOException, ScontrinoException {
    DatabaseConnection.connect();
    CassaController c = new CassaController();
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    CassaController.setScontrino(s);
    TextField t = new TextField();
    t.setText("sjdk");
    c.setSommaVersataTextField(t);
    try {
      c.openCassaRiepilogo(null);
    } catch (NullPointerException ex) {
      ex.printStackTrace();
      assertFalse(true);
    } finally {
      DatabaseConnection.close();
    }
  }
}
