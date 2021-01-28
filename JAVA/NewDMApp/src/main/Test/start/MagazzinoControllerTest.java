package start;

import database.DatabaseConnection;
import de.saxsys.javafx.test.JfxRunner;
import magazzino.Prodotto;
import exceptions.DatabaseException;
import exceptions.ProdottoException;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import magazzino.MagazzinoController;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

@RunWith(JfxRunner.class)
public class MagazzinoControllerTest {

    @Test
    public void initializeCorrettoOrdinaProdottoQuantitaSugg() throws DatabaseException, MalformedURLException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        URL url =
                new File(System.getProperty("user.dir") + "\\src\\main\\resouncers\\OrdinaProdottoQuantitaSugg.fxml")
                        .toURI()
                        .toURL();
        Prodotto p = new Prodotto();
        p.setNome("Prova");
        MagazzinoController.setProdotto(p);
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
        MagazzinoController c = new MagazzinoController();
        URL url =
                new File(System.getProperty("user.dir") + "\\src\\main\\resouncers\\InserisciNuovoProdottoRiepilogo.fxml")
                        .toURI()
                        .toURL();
        Prodotto p = new Prodotto();
        p.setNome("Prova");
        MagazzinoController.setProdotto(p);
        c.setRiepilogoNuovoProdotto(new TextField());
        c.initialize(url,null);
        assertEquals(p.toString(),c.getRiepilogoNuovoProdotto().getText());
        DatabaseConnection.close();
    }

    @Test
    public void initializeCorrettoInserisciProdottoRiepilogo() throws DatabaseException, MalformedURLException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        URL url =
                new File(System.getProperty("user.dir") + "\\src\\main\\resouncers\\InserisciProdottoRiepilogo.fxml")
                        .toURI()
                        .toURL();
        Prodotto p = new Prodotto();
        p.setNome("Prova");
        MagazzinoController.setTempProdotto(p);
        c.setRiepilogoProdotto(new TextField());
        c.initialize(url,null);
        assertEquals(p.toString(),c.getRiepilogoProdotto().getText());
        DatabaseConnection.close();
    }

    @Test
    public void initializeCorrettoIModPrezzoProdottoPopUp() throws DatabaseException, MalformedURLException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        URL url =
                new File(System.getProperty("user.dir") + "\\src\\main\\resouncers\\ModPrezzoProdottoPopUpForm.fxml")
                        .toURI()
                        .toURL();
        Prodotto p = new Prodotto();
        p.setNome("Prova");
        MagazzinoController.setTempProdotto(p);
        c.setLabelPrezzoProd(new Label());
        c.setLabelNomeProd(new Label());
        c.initialize(url,null);
        assertEquals(p.getNome(),c.getLabelNomeProd().getText());
        DatabaseConnection.close();
    }

    @Test
    public void openInserisciProdottoRiepilogoCorretto() throws DatabaseException {
        DatabaseConnection.connect();
        MagazzinoController m = new MagazzinoController();
        m.setCodiceProd("1000000000001");
        m.setQuantitaProd("19");
        Exception ex = assertThrows(NullPointerException.class,()->{
            m.openInserisciProdottoRiepilogo(null);
        });
        assertEquals("java.lang.NullPointerException", ex.getClass().getName());
    }

    @Test
    public void openInserisciProdottoRiepilogoCodiceSbagliato() throws DatabaseException {
        DatabaseConnection.connect();
        MagazzinoController m = new MagazzinoController();
        m.setCodiceProd("1000000");
        m.setQuantitaProd("19");
        try{
            m.openInserisciProdottoRiepilogo(null);
        }catch (NullPointerException | IOException | ProdottoException ex){
            ex.printStackTrace();
            assertFalse(true);
        }finally{
            DatabaseConnection.close();
        }
    }

    @Test
    public void openInserisciProdottoRiepilogoQuantitaSbagliata() throws DatabaseException {
        DatabaseConnection.connect();
        MagazzinoController m = new MagazzinoController();
        m.setCodiceProd("1000000000001");
        m.setQuantitaProd("-5");
        try{
            m.openInserisciProdottoRiepilogo(null);
        }catch (NullPointerException | IOException | ProdottoException ex){
            ex.printStackTrace();
            assertFalse(true);
        }finally{
            DatabaseConnection.close();
        }
    }

    @Test
    public void openNuovoProdottoRiepilogoCorretto() throws DatabaseException, IOException, ProdottoException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        c.setNomeProd("Shampoo");
        c.setQuantitaProd("50");
        c.setCodiceProd("5555555555555");
        c.setPrezzoProd("5");
        c.setTipologiaProd("Casa");
        RadioButton r = new RadioButton();
        r.setText("media");
        c.setDimensioni(r);
        RadioButton d = new RadioButton();
        d.setText("media");
        c.setScadenza(d);
        Exception ex = assertThrows(NullPointerException.class,()->{
            c.openNuovoProdottoRiepilogo(null);
        });
        assertEquals("java.lang.NullPointerException",ex.getClass().getName());
        DatabaseConnection.close();
    }

    @Test
    public void openNuovoProdottoRiepilogoNomeSbagliato() throws DatabaseException, IOException, ProdottoException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        c.setNomeProd("s");
        c.setQuantitaProd("50");
        c.setCodiceProd("5555555555555");
        c.setPrezzoProd("5");
        c.setTipologiaProd("Casa");
        RadioButton r = new RadioButton();
        r.setText("media");
        c.setDimensioni(r);
        RadioButton d = new RadioButton();
        d.setText("media");
        c.setScadenza(d);
        try{
            c.openNuovoProdottoRiepilogo(null);
        }catch (NullPointerException ex){
            ex.printStackTrace();
            assertFalse(true);
        }finally{
            DatabaseConnection.close();
        }
    }

    @Test
    public void openNuovoProdottoRiepilogoNomeLungo() throws DatabaseException, IOException, ProdottoException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        c.setNomeProd("VSJAKDSJDNSJDDSAJDJLSAJDLASDJSKLDNSJLDNSJLDNDSJLNDDJNCDLNCDLNLDNFDLNLDNFDLNDFLNDALNDALFNDALFNADLFNADFLADNFLADNFADNFDLFNALFNADLFNADLFNDALFDANFLDANFDLAKKKKKKKKKNDALFNDALFNADLFNDFNDLANCLKDAKLADNCKLDNCLDANCNDANCADCNLDANCLADNCLADNFCADNFADNFADKNFDLAKNFDKANFDKANFDJANFLDANFKDFNKDNFKLDNFLADNFLADNFLADNFLADNFADLFNALFNADLFNADLFNDAL");
        c.setQuantitaProd("50");
        c.setCodiceProd("5555555555555");
        c.setPrezzoProd("5");
        c.setTipologiaProd("Casa");
        RadioButton r = new RadioButton();
        r.setText("media");
        c.setDimensioni(r);
        RadioButton d = new RadioButton();
        d.setText("media");
        c.setScadenza(d);
        try{
            c.openNuovoProdottoRiepilogo(null);
        }catch (NullPointerException ex){
            ex.printStackTrace();
            assertFalse(true);
        }finally{
            DatabaseConnection.close();
        }
    }

    @Test
    public void openNuovoProdottoRiepilogoQuantitaSbagliata() throws DatabaseException, IOException, ProdottoException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        c.setNomeProd("Shampoo");
        c.setQuantitaProd("-50");
        c.setCodiceProd("5555555555555");
        c.setPrezzoProd("5");
        c.setTipologiaProd("Casa");
        RadioButton r = new RadioButton();
        r.setText("media");
        c.setDimensioni(r);
        RadioButton d = new RadioButton();
        d.setText("media");
        c.setScadenza(d);
        try{
            c.openNuovoProdottoRiepilogo(null);
        }catch (NullPointerException ex){
            ex.printStackTrace();
            assertFalse(true);
        }finally{
            DatabaseConnection.close();
        }
    }

    @Test
    public void openNuovoProdottoRiepilogoCodiceSbagliato() throws DatabaseException, IOException, ProdottoException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        c.setNomeProd("Shampoo");
        c.setQuantitaProd("50");
        c.setCodiceProd("5");
        c.setPrezzoProd("5");
        c.setTipologiaProd("Casa");
        RadioButton r = new RadioButton();
        r.setText("media");
        c.setDimensioni(r);
        RadioButton d = new RadioButton();
        d.setText("media");
        c.setScadenza(d);
        try{
            c.openNuovoProdottoRiepilogo(null);
        }catch (NullPointerException ex){
            ex.printStackTrace();
            assertFalse(true);
        }finally{
            DatabaseConnection.close();
        }
    }

    @Test
    public void openNuovoProdottoRiepilogoPrezzoSBgaliato() throws DatabaseException, IOException, ProdottoException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        c.setNomeProd("Shampoo");
        c.setQuantitaProd("50");
        c.setCodiceProd("5555555555555");
        c.setPrezzoProd("-5");
        c.setTipologiaProd("Casa");
        RadioButton r = new RadioButton();
        r.setText("media");
        c.setDimensioni(r);
        RadioButton d = new RadioButton();
        d.setText("media");
        c.setScadenza(d);
        try{
            c.openNuovoProdottoRiepilogo(null);
        }catch (NullPointerException ex){
            ex.printStackTrace();
            assertFalse(true);
        }finally{
            DatabaseConnection.close();
        }
    }

    @Test
    public void openNuovoProdottoRiepilogoTipologiaCorta() throws DatabaseException, IOException, ProdottoException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        c.setNomeProd("Shampoo");
        c.setQuantitaProd("50");
        c.setCodiceProd("5555555555555");
        c.setPrezzoProd("5");
        c.setTipologiaProd("C");
        RadioButton r = new RadioButton();
        r.setText("media");
        c.setDimensioni(r);
        RadioButton d = new RadioButton();
        d.setText("media");
        c.setScadenza(d);
        try{
            c.openNuovoProdottoRiepilogo(null);
        }catch (NullPointerException ex){
            ex.printStackTrace();
            assertFalse(true);
        }finally{
            DatabaseConnection.close();
        }
    }

    @Test
    public void openNuovoProdottoRiepilogoTipologiaLunga() throws DatabaseException, IOException, ProdottoException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        c.setNomeProd("Shampoo");
        c.setQuantitaProd("50");
        c.setCodiceProd("5555555555555");
        c.setPrezzoProd("5");
        c.setTipologiaProd("VSJAKDSJDNSJDDSAJDJLSAJDLASDJSKLDNSJLDNSJLDNDSJLNDDJNCDLNCDLNLDNFDLNLDNFDLNDFLNDALNDALFNDALFNADLFNADFLADNFLADNFADNFDLFNALFNADLFNADLFNDALFDANFLDANFDLAKKKKKKKKKNDALFNDALFNADLFNDFNDLANCLKDAKLADNCKLDNCLDANCNDANCADCNLDANCLADNCLADNFCADNFADNFADKNFDLAKNFDKANFDKANFDJANFLDANFKDFNKDNFKLDNFLADNFLADNFLADNFLADNFADLFNALFNADLFNADLFNDALfhchcvchchchccghchhchchcgcgcggucugcgchgchc");
        RadioButton r = new RadioButton();
        r.setText("media");
        c.setDimensioni(r);
        RadioButton d = new RadioButton();
        d.setText("media");
        c.setScadenza(d);
        try{
            c.openNuovoProdottoRiepilogo(null);
        }catch (NullPointerException ex){
            ex.printStackTrace();
            assertFalse(true);
        }finally{
            DatabaseConnection.close();
        }
    }

    @Test
    public void openNuovoProdottoRiepilogoDimensioniSbagliato() throws DatabaseException, IOException, ProdottoException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        c.setNomeProd("Shampoo");
        c.setQuantitaProd("50");
        c.setCodiceProd("5555555555555");
        c.setPrezzoProd("5");
        c.setTipologiaProd("Casa");
        c.setDimensioni(new ToggleGroup());
        RadioButton d = new RadioButton();
        d.setText("media");
        c.setScadenza(d);
        try{
            c.openNuovoProdottoRiepilogo(null);
        }catch (NullPointerException ex){
            ex.printStackTrace();
            assertFalse(true);
        }finally{
            DatabaseConnection.close();
        }
    }

    @Test
    public void openNuovoProdottoRiepilogoScadenzaSbagliata() throws DatabaseException, IOException, ProdottoException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        c.setNomeProd("Shampoo");
        c.setQuantitaProd("50");
        c.setCodiceProd("5555555555555");
        c.setPrezzoProd("5");
        c.setTipologiaProd("Casa");
        RadioButton r = new RadioButton();
        r.setText("media");
        c.setDimensioni(r);
        c.setScadenza(new ToggleGroup());
        try{
            c.openNuovoProdottoRiepilogo(null);
        }catch (NullPointerException ex){
            ex.printStackTrace();
            assertFalse(true);
        }finally{
            DatabaseConnection.close();
        }
    }

    @Test
    public void aggiornaPrezzoCorretto() throws DatabaseException, IOException, ProdottoException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        c.setPrezzoProd("5");
        Exception ex =assertThrows(NullPointerException.class,()->{
            c.aggiornaPrezzo(null);
        });
        assertEquals("java.lang.NullPointerException",ex.getClass().getName());
        DatabaseConnection.close();
    }

    @Test
    public void aggiornaPrezzoSbagliato() throws DatabaseException, IOException, ProdottoException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        c.setPrezzoProd("-5");
        try{
            c.aggiornaPrezzo(null);
        }catch (NullPointerException ex){
            ex.printStackTrace();
            assertFalse(true);
        }finally{
            DatabaseConnection.close();
        }
    }

    @Test
    public void openModificaPrezzoPopUpCorretto() throws DatabaseException, IOException, ProdottoException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        c.setCodiceProd("1000000000001");
        Exception ex =assertThrows(NullPointerException.class,()->{
            c.openModificaPrezzoPopUp(null);
        });
        assertEquals("java.lang.NullPointerException",ex.getClass().getName());
        DatabaseConnection.close();
    }

    @Test
    public void openModificaPrezzoPopUpSbagliato() throws DatabaseException, IOException, ProdottoException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        c.setCodiceProd("5");
        try{
            c.openModificaPrezzoPopUp(null);
        }catch (NullPointerException ex){
            ex.printStackTrace();
            assertFalse(true);
        }finally{
            DatabaseConnection.close();
        }
    }

    @Test
    public void cercaProdottoOrdinaProdCorretto() throws DatabaseException, IOException, ProdottoException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        c.setCodiceProd("1000000000001");
        c.setPrezzoSped("10");
        RadioButton r = new RadioButton();
        r.setText("Inverno");
        c.setStagione(r);
        RadioButton g = new RadioButton();
        g.setText("Residenziale");
        c.setTipoSupermerc(g);
        RadioButton f = new RadioButton();
        f.setText("Lavorativo");
        c.setFestivita(f);
        Exception ex =assertThrows(NullPointerException.class,()->{
            c.cercaProdottoOrdinaProd(null);
        });
        assertEquals("java.lang.NullPointerException",ex.getClass().getName());
        DatabaseConnection.close();
    }


    @Test
    public void cercaProdottoOrdinaCodiceSbagliato() throws DatabaseException, IOException, ProdottoException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        c.setCodiceProd("1");
        c.setPrezzoSped("10");
        RadioButton r = new RadioButton();
        r.setText("Inverno");
        c.setStagione(r);
        RadioButton g = new RadioButton();
        g.setText("Residenziale");
        c.setTipoSupermerc(g);
        RadioButton f = new RadioButton();
        f.setText("Lavorativo");
        c.setFestivita(f);
        try{
            c.cercaProdottoOrdinaProd(null);
        }catch (NullPointerException ex){
            ex.printStackTrace();
            assertFalse(true);
        }finally{
            DatabaseConnection.close();
        }
    }

    @Test
    public void cercaProdottoOrdinaPrezzoSbagliato() throws DatabaseException, IOException, ProdottoException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        c.setCodiceProd("1000000000001");
        c.setPrezzoSped("-10");
        RadioButton r = new RadioButton();
        r.setText("Inverno");
        c.setStagione(r);
        RadioButton g = new RadioButton();
        g.setText("Residenziale");
        c.setTipoSupermerc(g);
        RadioButton f = new RadioButton();
        f.setText("Lavorativo");
        c.setFestivita(f);
        try{
            c.cercaProdottoOrdinaProd(null);
        }catch (NullPointerException ex){
            ex.printStackTrace();
            assertFalse(true);
        }finally{
            DatabaseConnection.close();
        }
    }

    @Test
    public void cercaProdottoOrdinaStagioneSbagliato() throws DatabaseException, IOException, ProdottoException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        c.setCodiceProd("1000000000001");
        c.setPrezzoSped("10");
        c.setStagione(new ToggleGroup());
        RadioButton g = new RadioButton();
        g.setText("Residenziale");
        c.setTipoSupermerc(g);
        RadioButton f = new RadioButton();
        f.setText("Lavorativo");
        c.setFestivita(f);
        try{
            c.cercaProdottoOrdinaProd(null);
        }catch (NullPointerException ex){
            ex.printStackTrace();
            assertFalse(true);
        }finally{
            DatabaseConnection.close();
        }
    }

    @Test
    public void cercaProdottoOrdinaTipoSuperSbagliato() throws DatabaseException, IOException, ProdottoException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        c.setCodiceProd("1000000000001");
        c.setPrezzoSped("10");
        RadioButton r = new RadioButton();
        r.setText("Inverno");
        c.setStagione(r);
        c.setTipoSupermerc(new ToggleGroup());
        RadioButton f = new RadioButton();
        f.setText("Lavorativo");
        c.setFestivita(f);
        try{
            c.cercaProdottoOrdinaProd(null);
        }catch (NullPointerException ex){
            ex.printStackTrace();
            assertFalse(true);
        }finally{
            DatabaseConnection.close();
        }
    }

    @Test
    public void cercaProdottoOrdinaFestivitaSbagliato() throws DatabaseException, IOException, ProdottoException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        c.setCodiceProd("1000000000001");
        c.setPrezzoSped("10");
        RadioButton r = new RadioButton();
        r.setText("Inverno");
        c.setStagione(r);
        RadioButton g = new RadioButton();
        g.setText("Residenziale");
        c.setTipoSupermerc(g);
        c.setFestivita(new ToggleGroup());
        try{
            c.cercaProdottoOrdinaProd(null);
        }catch (NullPointerException ex){
            ex.printStackTrace();
            assertFalse(true);
        }finally{
            DatabaseConnection.close();
        }
    }

    @Test
    public void confermaOrdineManuCorretto() throws DatabaseException, IOException, ProdottoException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        c.setQuantitaProd("20");
        Exception ex =assertThrows(NullPointerException.class,()->{
            c.confermaOrdineManu(null);
        });
        assertEquals("java.lang.NullPointerException",ex.getClass().getName());
        DatabaseConnection.close();
    }

    @Test
    public void confermaOrdineManuSbagliato() throws DatabaseException, IOException, ProdottoException {
        DatabaseConnection.connect();
        MagazzinoController c = new MagazzinoController();
        c.setQuantitaProd("-20");
        try{
            c.confermaOrdineManu(null);
        }catch (NullPointerException ex){
            ex.printStackTrace();
            assertFalse(true);
        }finally{
            DatabaseConnection.close();
        }
    }
}