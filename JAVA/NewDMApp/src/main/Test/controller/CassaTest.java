package controller;

import db.DatabaseConnection;
import de.saxsys.javafx.test.JfxRunner;
import entity.Scontrino;
import exceptions.DatabaseException;
import exceptions.ProdottoException;
import exceptions.ScontrinoException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

@RunWith(JfxRunner.class)
public class CassaTest {

  @Test
  public void initializeCorrettoCassa() throws IOException, DatabaseException, ProdottoException {
    DatabaseConnection.connect();
    Cassa c = new Cassa();
    URL url =
        new File(System.getProperty("user.dir") + "\\src\\main\\resouncers\\Cassa.fxml")
            .toURI()
            .toURL();
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    Cassa.setScontrino(s);
    c.setScontrinoTextField(new TextArea());
    c.initialize(url, null);
    assertEquals(s.getRiepilogo(), c.getScontrinoTextField().getText());
    DatabaseConnection.close();
  }

  @Test
  public void initializeScontrinoNullCassa()
      throws IOException, DatabaseException, ProdottoException {
    DatabaseConnection.connect();
    Cassa c = new Cassa();
    URL url =
        new File(System.getProperty("user.dir") + "\\src\\main\\resouncers\\Cassa.fxml")
            .toURI()
            .toURL();
    Cassa.setScontrino(null);
    c.setScontrinoTextField(new TextArea());
    c.initialize(url, null);
    assertEquals("", c.getScontrinoTextField().getText());
    DatabaseConnection.close();
  }

  @Test
  public void initializeCorrettoCassaTotale()
      throws IOException, DatabaseException, ProdottoException {
    DatabaseConnection.connect();
    Cassa c = new Cassa();
    URL url =
        new File(System.getProperty("user.dir") + "\\src\\main\\resouncers\\CassaTotale.fxml")
            .toURI()
            .toURL();
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    Cassa.setScontrino(s);
    c.setTotaleLabel(new Label());
    c.initialize(url, null);
    assertEquals(Double.toString(s.getTot()), c.getTotaleLabel().getText());
    DatabaseConnection.close();
  }

  @Test
  public void initializeCorrettoCassaRiepilogo()
      throws IOException, DatabaseException, ProdottoException {
    DatabaseConnection.connect();
    Cassa c = new Cassa();
    URL url =
        new File(System.getProperty("user.dir") + "\\src\\main\\resouncers\\CassaRiepilogo.fxml")
            .toURI()
            .toURL();
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    Cassa.setScontrino(s);
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
    Cassa c = new Cassa();
    URL url =
        new File(System.getProperty("user.dir") + "\\src\\main\\resouncers\\CassaRiepilogo.fxml")
            .toURI()
            .toURL();
    Cassa.setScontrino(null);
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
    Cassa c = new Cassa();
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    Cassa.setScontrino(s);
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
    Cassa c = new Cassa();
    Cassa.setScontrino(null);
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
    Cassa c = new Cassa();
    Scontrino s = new Scontrino();
    Cassa.setScontrino(s);
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
    Cassa c = new Cassa();
    Scontrino s = new Scontrino();
    Cassa.setScontrino(s);
    TextField t = new TextField();
    t.setText("1000000000001");
    c.setCodiceProd(t);
    c.inserimentoProdotto(null);
    assertEquals(1, Cassa.getScontrino().getList().size());
    DatabaseConnection.close();
  }

  @Test
  public void inserimentoProdottoScontrinoNull() throws DatabaseException, ProdottoException {
    DatabaseConnection.connect();
    Cassa c = new Cassa();
    Cassa.setScontrino(null);
    TextField t = new TextField();
    t.setText("1000000000001");
    c.setCodiceProd(t);
    c.inserimentoProdotto(null);
    assertEquals(1, Cassa.getScontrino().getList().size());
    DatabaseConnection.close();
  }

  @Test
  public void openCassaRiepilogoCorretto()
      throws DatabaseException, ProdottoException, IOException, ScontrinoException {
    DatabaseConnection.connect();
    Cassa c = new Cassa();
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    Cassa.setScontrino(s);
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
    Cassa c = new Cassa();
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    Cassa.setScontrino(s);
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