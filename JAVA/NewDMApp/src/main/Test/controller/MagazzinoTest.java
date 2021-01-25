package controller;

import db.DatabaseConnection;
import de.saxsys.javafx.test.JfxRunner;
import entity.Prodotto;
import exceptions.DatabaseException;
import exceptions.ProdottoException;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

@RunWith(JfxRunner.class)
public class MagazzinoTest {

    @Test
    public void initializeCorrettoOrdinaProdottoQuantitaSugg() throws DatabaseException, MalformedURLException {
        DatabaseConnection.connect();
        Magazzino c = new Magazzino();
        URL url =
                new File(System.getProperty("user.dir") + "\\src\\main\\resouncers\\OrdinaProdottoQuantitaSugg.fxml")
                        .toURI()
                        .toURL();
        Prodotto p = new Prodotto();
        p.setNome("Prova");
        Magazzino.setProdotto(p);
        c.setLabelNomeProdSugg(new Label());
        c.setLabelQuantitaProdSugg(new Label());
        c.setLabelOrdineCalcSugg(new Label());
        c.initialize(url,null);
        assertEquals(p.getNome(),c.getLabelNomeProdSugg().getText());
        DatabaseConnection.close();
    }

    @Test
    public void initializeCorrettoInserisciNuovoProdottoRiepilogo() throws DatabaseException, MalformedURLException {
        DatabaseConnection.connect();
        Magazzino c = new Magazzino();
        URL url =
                new File(System.getProperty("user.dir") + "\\src\\main\\resouncers\\InserisciNuovoProdottoRiepilogo.fxml")
                        .toURI()
                        .toURL();
        Prodotto p = new Prodotto();
        p.setNome("Prova");
        Magazzino.setProdotto(p);
        c.setRiepilogoNuovoProdotto(new TextField());
        c.initialize(url,null);
        assertEquals(p.toString(),c.getRiepilogoNuovoProdotto().getText());
        DatabaseConnection.close();
    }

    @Test
    public void initializeCorrettoInserisciProdottoRiepilogo() throws DatabaseException, MalformedURLException {
        DatabaseConnection.connect();
        Magazzino c = new Magazzino();
        URL url =
                new File(System.getProperty("user.dir") + "\\src\\main\\resouncers\\InserisciProdottoRiepilogo.fxml")
                        .toURI()
                        .toURL();
        Prodotto p = new Prodotto();
        p.setNome("Prova");
        Magazzino.setTempProdotto(p);
        c.setRiepilogoProdotto(new TextField());
        c.initialize(url,null);
        assertEquals(p.toString(),c.getRiepilogoProdotto().getText());
        DatabaseConnection.close();
    }

    @Test
    public void initializeCorrettoIModPrezzoProdottoPopUp() throws DatabaseException, MalformedURLException {
        DatabaseConnection.connect();
        Magazzino c = new Magazzino();
        URL url =
                new File(System.getProperty("user.dir") + "\\src\\main\\resouncers\\ModPrezzoProdottoPopUp.fxml")
                        .toURI()
                        .toURL();
        Prodotto p = new Prodotto();
        p.setNome("Prova");
        Magazzino.setTempProdotto(p);
        c.setLabelPrezzoProd(new Label());
        c.setLabelNomeProd(new Label());
        c.initialize(url,null);
        assertEquals(p.getNome(),c.getLabelNomeProd().getText());
        DatabaseConnection.close();
    }

    @Test
    public void openInserisciProdottoRiepilogoCorretto() throws DatabaseException {
        DatabaseConnection.connect();
        Magazzino m = new Magazzino();
        m.setCodiceProd(new TextField());
        m.setCodiceProd("1000000000001");
        m.setQuantitaProd(new TextField());
        m.setQuantitaProd("19");
        Exception ex = assertThrows(NullPointerException.class,()->{
            m.openInserisciProdottoRiepilogo(null);
        });
        assertEquals("java.lang.NullPointerException", ex.getClass().getName());
    }

    @Test
    public void openInserisciProdottoRiepilogoCodiceSbagliato() throws DatabaseException {
        DatabaseConnection.connect();
        Magazzino m = new Magazzino();
        m.setCodiceProd(new TextField());
        m.setCodiceProd("1000000");
        m.setQuantitaProd(new TextField());
        m.setQuantitaProd("19");
        try{
            m.openInserisciProdottoRiepilogo(null);
        }catch (NullPointerException | IOException | ProdottoException ex){
            ex.printStackTrace();
            assertFalse(true);
        }
    }

    @Test
    public void openInserisciProdottoRiepilogoQuantitaSbagliata() throws DatabaseException {
        DatabaseConnection.connect();
        Magazzino m = new Magazzino();
        m.setCodiceProd(new TextField());
        m.setCodiceProd("1000000000001");
        m.setQuantitaProd(new TextField());
        m.setQuantitaProd("-5");
        try{
            m.openInserisciProdottoRiepilogo(null);
        }catch (NullPointerException | IOException | ProdottoException ex){
            ex.printStackTrace();
            assertFalse(true);
        }
    }
}