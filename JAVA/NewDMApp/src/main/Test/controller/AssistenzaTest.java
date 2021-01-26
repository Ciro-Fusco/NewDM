package controller;

import db.DatabaseConnection;
import de.saxsys.javafx.test.JfxRunner;
import entity.Scontrino;
import entity.Ticket;
import exceptions.*;
import javafx.scene.control.TextField;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

@RunWith(JfxRunner.class)
public class AssistenzaTest {

  @Test
  public void openAssistenzaDettagliProbCorretto()
      throws DatabaseException, ProdottoException, ElencaException, IOException,
          ScontrinoException {
    DatabaseConnection.connect();
    Assistenza.setTicket(null);
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    s.save();
    Assistenza a = new Assistenza();
    TextField t = new TextField();
    t.setText("Vincenzo Aiello");
    a.setNomeCognCli(t);
    TextField c = new TextField();
    c.setText("LLAVCN99H05C129K");
    a.setCodFiscCli(c);
    TextField d = new TextField();
    d.setText("Via Pasquale Vitiello n 10");
    a.setIndirizzoResiCli(d);
    TextField e = new TextField();
    e.setText("Bevanda");
    a.setTipoProdotto(e);
    TextField f = new TextField();
    f.setText("Barbabietole");
    a.setNomeProdotto(f);
    TextField g = new TextField();
    g.setText("3404231112");
    a.setTelefonoCli(g);
    TextField h = new TextField();
    h.setText("1000000000001");
    a.setNumSerieProd(h);
    TextField i = new TextField();
    i.setText(Long.toString(s.getId()));
    a.setNumScontrino(i);
    TextField l = new TextField();
    l.setText(s.getData().substring(0, s.getData().indexOf(" ")));
    a.setDataScontrino(l);
    TextField m = new TextField();
    m.setText("1000000000001");
    a.setCodProdotto(m);
    Exception ex =
        assertThrows(
            NullPointerException.class,
            () -> {
              a.openAssistenzaDettagliProb(null);
            });
    assertEquals("java.lang.NullPointerException", ex.getClass().getName());
    DatabaseConnection.close();
  }

  @Test
  public void openAssistenzaDettagliProbCFSbagliato()
      throws DatabaseException, ProdottoException, ElencaException, IOException,
          ScontrinoException {
    DatabaseConnection.connect();
    Assistenza.setTicket(null);
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    s.save();
    Assistenza a = new Assistenza();
    TextField t = new TextField();
    t.setText("Vincenzo Aiello");
    a.setNomeCognCli(t);
    TextField c = new TextField();
    c.setText("lggggg");
    a.setCodFiscCli(c);
    TextField d = new TextField();
    d.setText("Via Pasquale Vitiello n 10");
    a.setIndirizzoResiCli(d);
    TextField e = new TextField();
    e.setText("Bevanda");
    a.setTipoProdotto(e);
    TextField f = new TextField();
    f.setText("Barbabietole");
    a.setNomeProdotto(f);
    TextField g = new TextField();
    g.setText("3404231112");
    a.setTelefonoCli(g);
    TextField h = new TextField();
    h.setText("1000000000001");
    a.setNumSerieProd(h);
    TextField i = new TextField();
    i.setText(Long.toString(s.getId()));
    a.setNumScontrino(i);
    TextField l = new TextField();
    l.setText(s.getData().substring(0, s.getData().indexOf(" ")));
    a.setDataScontrino(l);
    TextField m = new TextField();
    m.setText("1000000000001");
    a.setCodProdotto(m);
    a.openAssistenzaDettagliProb(null);
    assertEquals(null, Assistenza.getTicket());
    DatabaseConnection.close();
  }

  @Test
  public void openAssistenzaDettagliProbNomeSbagliato()
      throws DatabaseException, ProdottoException, ElencaException, IOException,
          ScontrinoException {
    DatabaseConnection.connect();
    Assistenza.setTicket(null);
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    s.save();
    Assistenza a = new Assistenza();
    TextField t = new TextField();
    t.setText("V");
    a.setNomeCognCli(t);
    TextField c = new TextField();
    c.setText("LLAVCN99H05C129K");
    a.setCodFiscCli(c);
    TextField d = new TextField();
    d.setText("Via Pasquale Vitiello n 10");
    a.setIndirizzoResiCli(d);
    TextField e = new TextField();
    e.setText("Bevanda");
    a.setTipoProdotto(e);
    TextField f = new TextField();
    f.setText("Barbabietole");
    a.setNomeProdotto(f);
    TextField g = new TextField();
    g.setText("3404231112");
    a.setTelefonoCli(g);
    TextField h = new TextField();
    h.setText("1000000000001");
    a.setNumSerieProd(h);
    TextField i = new TextField();
    i.setText(Long.toString(s.getId()));
    a.setNumScontrino(i);
    TextField l = new TextField();
    l.setText(s.getData().substring(0, s.getData().indexOf(" ")));
    a.setDataScontrino(l);
    TextField m = new TextField();
    m.setText("1000000000001");
    a.setCodProdotto(m);
    a.openAssistenzaDettagliProb(null);
    assertEquals(null, Assistenza.getTicket());
    DatabaseConnection.close();
  }

  @Test
  public void openAssistenzaDettagliProbNomeSbagliato2()
      throws DatabaseException, ProdottoException, ElencaException, IOException,
          ScontrinoException {
    DatabaseConnection.connect();
    Assistenza.setTicket(null);
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    s.save();
    Assistenza a = new Assistenza();
    TextField t = new TextField();
    t.setText(
        "VSJAKDSJDNSJDDSAJDJLSAJDLASDJSKLDNSJLDNSJLDNDSJLNDDJNCDLNCDLNLDNFDLNLDNFDLNDFLNDALNDALFNDALFNADLFNADFLADNFLADNFADNFDLFNALFNADLFNADLFNDALFDANFLDANFDLAKKKKKKKKKNDALFNDALFNADLFNDFNDLANCLKDAKLADNCKLDNCLDANCNDANCADCNLDANCLADNCLADNFCADNFADNFADKNFDLAKNFDKANFDKANFDJANFLDANFKDFNKDNFKLDNFLADNFLADNFLADNFLADNFADLFNALFNADLFNADLFNDAL");
    a.setNomeCognCli(t);
    TextField c = new TextField();
    c.setText("LLAVCN99H05C129K");
    a.setCodFiscCli(c);
    TextField d = new TextField();
    d.setText("Via Pasquale Vitiello n 10");
    a.setIndirizzoResiCli(d);
    TextField e = new TextField();
    e.setText("Bevanda");
    a.setTipoProdotto(e);
    TextField f = new TextField();
    f.setText("Barbabietole");
    a.setNomeProdotto(f);
    TextField g = new TextField();
    g.setText("3404231112");
    a.setTelefonoCli(g);
    TextField h = new TextField();
    h.setText("1000000000001");
    a.setNumSerieProd(h);
    TextField i = new TextField();
    i.setText(Long.toString(s.getId()));
    a.setNumScontrino(i);
    TextField l = new TextField();
    l.setText(s.getData().substring(0, s.getData().indexOf(" ")));
    a.setDataScontrino(l);
    TextField m = new TextField();
    m.setText("1000000000001");
    a.setCodProdotto(m);
    a.openAssistenzaDettagliProb(null);
    assertEquals(null, Assistenza.getTicket());
    DatabaseConnection.close();
  }

  @Test
  public void openAssistenzaDettagliProbNomeProdottoSbagliato2()
      throws DatabaseException, ProdottoException, ElencaException, IOException,
          ScontrinoException {
    DatabaseConnection.connect();
    Assistenza.setTicket(null);
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    s.save();
    Assistenza a = new Assistenza();
    TextField t = new TextField();
    t.setText("Vincenzo Aiello");
    a.setNomeCognCli(t);
    TextField c = new TextField();
    c.setText("LLAVCN99H05C129K");
    a.setCodFiscCli(c);
    TextField d = new TextField();
    d.setText("Via Pasquale Vitiello n 10");
    a.setIndirizzoResiCli(d);
    TextField e = new TextField();
    e.setText("Bevanda");
    a.setTipoProdotto(e);
    TextField f = new TextField();
    f.setText(
        "VSJAKDSJDNSJDDSAJDJLSAJDLASDJSKLDNSJLDNSJLDNDSJLNDDJNCDLNCDLNLDNFDLNLDNFDLNDFLNDALNDALFNDALFNADLFNADFLADNFLADNFADNFDLFNALFNADLFNADLFNDALFDANFLDANFDLAKKKKKKKKKNDALFNDALFNADLFNDFNDLANCLKDAKLADNCKLDNCLDANCNDANCADCNLDANCLADNCLADNFCADNFADNFADKNFDLAKNFDKANFDKANFDJANFLDANFKDFNKDNFKLDNFLADNFLADNFLADNFLADNFADLFNALFNADLFNADLFNDAL");
    a.setNomeProdotto(f);
    TextField g = new TextField();
    g.setText("3404231112");
    a.setTelefonoCli(g);
    TextField h = new TextField();
    h.setText("1000000000001");
    a.setNumSerieProd(h);
    TextField i = new TextField();
    i.setText(Long.toString(s.getId()));
    a.setNumScontrino(i);
    TextField l = new TextField();
    l.setText(s.getData().substring(0, s.getData().indexOf(" ")));
    a.setDataScontrino(l);
    TextField m = new TextField();
    m.setText("1000000000001");
    a.setCodProdotto(m);
    a.openAssistenzaDettagliProb(null);
    assertEquals(null, Assistenza.getTicket());
    DatabaseConnection.close();
  }

  @Test
  public void openAssistenzaDettagliProbNomeProdottoSbagliato()
      throws DatabaseException, ProdottoException, ElencaException, IOException,
          ScontrinoException {
    DatabaseConnection.connect();
    Assistenza.setTicket(null);
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    s.save();
    Assistenza a = new Assistenza();
    TextField t = new TextField();
    t.setText("Vincenzo Aiello");
    a.setNomeCognCli(t);
    TextField c = new TextField();
    c.setText("LLAVCN99H05C129K");
    a.setCodFiscCli(c);
    TextField d = new TextField();
    d.setText("Via Pasquale Vitiello n 10");
    a.setIndirizzoResiCli(d);
    TextField e = new TextField();
    e.setText("Bevanda");
    a.setTipoProdotto(e);
    TextField f = new TextField();
    f.setText("B");
    a.setNomeProdotto(f);
    TextField g = new TextField();
    g.setText("3404231112");
    a.setTelefonoCli(g);
    TextField h = new TextField();
    h.setText("1000000000001");
    a.setNumSerieProd(h);
    TextField i = new TextField();
    i.setText(Long.toString(s.getId()));
    a.setNumScontrino(i);
    TextField l = new TextField();
    l.setText(s.getData().substring(0, s.getData().indexOf(" ")));
    a.setDataScontrino(l);
    TextField m = new TextField();
    m.setText("1000000000001");
    a.setCodProdotto(m);
    a.openAssistenzaDettagliProb(null);
    assertEquals(null, Assistenza.getTicket());
    DatabaseConnection.close();
  }

  @Test
  public void openAssistenzaDettagliProbIndirizzoSbagliato2()
      throws DatabaseException, ProdottoException, ElencaException, IOException,
          ScontrinoException {
    DatabaseConnection.connect();
    Assistenza.setTicket(null);
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    s.save();
    Assistenza a = new Assistenza();
    TextField t = new TextField();
    t.setText("Vincenzo Aiello");
    a.setNomeCognCli(t);
    TextField c = new TextField();
    c.setText("LLAVCN99H05C129K");
    a.setCodFiscCli(c);
    TextField d = new TextField();
    d.setText(
        "VSJAKDSJDNSJDDSAJDJLSAJDLASDJSKLDNSJLDNSJLDNDSJLNDDJNCDLNCDLNLDNFDLNLDNFDLNDFLNDALNDALFNDALFNADLFNADFLADNFLADNFADNFDLFNALFNADLFNADLFNDALFDANFLDANFDLAKKKKKKKKKNDALFNDALFNADLFNDFNDLANCLKDAKLADNCKLDNCLDANCNDANCADCNLDANCLADNCLADNFCADNFADNFADKNFDLAKNFDKANFDKANFDJANFLDANFKDFNKDNFKLDNFLADNFLADNFLADNFLADNFADLFNALFNADLFNADLFNDAL");
    a.setIndirizzoResiCli(d);
    TextField e = new TextField();
    e.setText("Bevanda");
    a.setTipoProdotto(e);
    TextField f = new TextField();
    f.setText("Barbabietole");
    a.setNomeProdotto(f);
    TextField g = new TextField();
    g.setText("3404231112");
    a.setTelefonoCli(g);
    TextField h = new TextField();
    h.setText("1000000000001");
    a.setNumSerieProd(h);
    TextField i = new TextField();
    i.setText(Long.toString(s.getId()));
    a.setNumScontrino(i);
    TextField l = new TextField();
    l.setText(s.getData().substring(0, s.getData().indexOf(" ")));
    a.setDataScontrino(l);
    TextField m = new TextField();
    m.setText("1000000000001");
    a.setCodProdotto(m);
    a.openAssistenzaDettagliProb(null);
    assertEquals(null, Assistenza.getTicket());
    DatabaseConnection.close();
  }

  @Test
  public void openAssistenzaDettagliProbIndirizzoSbagliato()
      throws DatabaseException, ProdottoException, ElencaException, IOException,
          ScontrinoException {
    DatabaseConnection.connect();
    Assistenza.setTicket(null);
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    s.save();
    Assistenza a = new Assistenza();
    TextField t = new TextField();
    t.setText("Vincenzo Aiello");
    a.setNomeCognCli(t);
    TextField c = new TextField();
    c.setText("LLAVCN99H05C129K");
    a.setCodFiscCli(c);
    TextField d = new TextField();
    d.setText("V");
    a.setIndirizzoResiCli(d);
    TextField e = new TextField();
    e.setText("Bevanda");
    a.setTipoProdotto(e);
    TextField f = new TextField();
    f.setText("Barbabietole");
    a.setNomeProdotto(f);
    TextField g = new TextField();
    g.setText("3404231112");
    a.setTelefonoCli(g);
    TextField h = new TextField();
    h.setText("1000000000001");
    a.setNumSerieProd(h);
    TextField i = new TextField();
    i.setText(Long.toString(s.getId()));
    a.setNumScontrino(i);
    TextField l = new TextField();
    l.setText(s.getData().substring(0, s.getData().indexOf(" ")));
    a.setDataScontrino(l);
    TextField m = new TextField();
    m.setText("1000000000001");
    a.setCodProdotto(m);
    a.openAssistenzaDettagliProb(null);
    assertEquals(null, Assistenza.getTicket());
    DatabaseConnection.close();
  }

  @Test
  public void openAssistenzaDettagliProbTipologiaProdottoSbagliato()
      throws DatabaseException, ProdottoException, ElencaException, IOException,
          ScontrinoException {
    DatabaseConnection.connect();
    Assistenza.setTicket(null);
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    s.save();
    Assistenza a = new Assistenza();
    TextField t = new TextField();
    t.setText("Vincenzo Aiello");
    a.setNomeCognCli(t);
    TextField c = new TextField();
    c.setText("LLAVCN99H05C129K");
    a.setCodFiscCli(c);
    TextField d = new TextField();
    d.setText("Via Pasquale Vitiello n 10");
    a.setIndirizzoResiCli(d);
    TextField e = new TextField();
    e.setText("B");
    a.setTipoProdotto(e);
    TextField f = new TextField();
    f.setText("Barbabietole");
    a.setNomeProdotto(f);
    TextField g = new TextField();
    g.setText("3404231112");
    a.setTelefonoCli(g);
    TextField h = new TextField();
    h.setText("1000000000001");
    a.setNumSerieProd(h);
    TextField i = new TextField();
    i.setText(Long.toString(s.getId()));
    a.setNumScontrino(i);
    TextField l = new TextField();
    l.setText(s.getData().substring(0, s.getData().indexOf(" ")));
    a.setDataScontrino(l);
    TextField m = new TextField();
    m.setText("1000000000001");
    a.setCodProdotto(m);
    a.openAssistenzaDettagliProb(null);
    assertEquals(null, Assistenza.getTicket());
    DatabaseConnection.close();
  }

  @Test
  public void openAssistenzaDettagliProbTipologiaProdottoSbagliato2()
          throws DatabaseException, ProdottoException, ElencaException, IOException,
          ScontrinoException {
    DatabaseConnection.connect();
    Assistenza.setTicket(null);
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    s.save();
    Assistenza a = new Assistenza();
    TextField t = new TextField();
    t.setText("Vincenzo Aiello");
    a.setNomeCognCli(t);
    TextField c = new TextField();
    c.setText("LLAVCN99H05C129K");
    a.setCodFiscCli(c);
    TextField d = new TextField();
    d.setText("Via Pasquale Vitiello n 10");
    a.setIndirizzoResiCli(d);
    TextField e = new TextField();
    e.setText("VSJAKDSJDNSJDDSAJDJLSAJDLASDJSKLDNSJLDNSJLDNDSJLNDDJNCDLNCDLNLDNFDLNLDNFDLNDFLNDALNDALFNDALFNADLFNADFLADNFLADNFADNFDLFNALFNADLFNADLFNDALFDANFLDANFDLAKKKKKKKKKNDALFNDALFNADLFNDFNDLANCLKDAKLADNCKLDNCLDANCNDANCADCNLDANCLADNCLADNFCADNFADNFADKNFDLAKNFDKANFDKANFDJANFLDANFKDFNKDNFKLDNFLADNFLADNFLADNFLADNFADLFNALFNADLFNADLFNDAL");
    a.setTipoProdotto(e);
    TextField f = new TextField();
    f.setText("Barbabietole");
    a.setNomeProdotto(f);
    TextField g = new TextField();
    g.setText("3404231112");
    a.setTelefonoCli(g);
    TextField h = new TextField();
    h.setText("1000000000001");
    a.setNumSerieProd(h);
    TextField i = new TextField();
    i.setText(Long.toString(s.getId()));
    a.setNumScontrino(i);
    TextField l = new TextField();
    l.setText(s.getData().substring(0, s.getData().indexOf(" ")));
    a.setDataScontrino(l);
    TextField m = new TextField();
    m.setText("1000000000001");
    a.setCodProdotto(m);
    a.openAssistenzaDettagliProb(null);
    assertEquals(null, Assistenza.getTicket());
    DatabaseConnection.close();
  }

  @Test
  public void openAssistenzaDettagliNumeroDiTelefonoSbagliato()
      throws DatabaseException, ProdottoException, ElencaException, IOException,
          ScontrinoException {
    DatabaseConnection.connect();
    Assistenza.setTicket(null);
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    s.save();
    Assistenza a = new Assistenza();
    TextField t = new TextField();
    t.setText("Vincenzo Aiello");
    a.setNomeCognCli(t);
    TextField c = new TextField();
    c.setText("LLAVCN99H05C129K");
    a.setCodFiscCli(c);
    TextField d = new TextField();
    d.setText("Via Pasquale Vitiello n 10");
    a.setIndirizzoResiCli(d);
    TextField e = new TextField();
    e.setText("Bevanda");
    a.setTipoProdotto(e);
    TextField f = new TextField();
    f.setText("Barbabietole");
    a.setNomeProdotto(f);
    TextField g = new TextField();
    g.setText("3404231");
    a.setTelefonoCli(g);
    TextField h = new TextField();
    h.setText("1000000000001");
    a.setNumSerieProd(h);
    TextField i = new TextField();
    i.setText(Long.toString(s.getId()));
    a.setNumScontrino(i);
    TextField l = new TextField();
    l.setText(s.getData().substring(0, s.getData().indexOf(" ")));
    a.setDataScontrino(l);
    TextField m = new TextField();
    m.setText("1000000000001");
    a.setCodProdotto(m);
    a.openAssistenzaDettagliProb(null);
    assertEquals(null, Assistenza.getTicket());
    DatabaseConnection.close();
  }

  @Test
  public void openAssistenzaDettagliProbNumeroDiserieSbagliato()
      throws DatabaseException, ProdottoException, ElencaException, IOException,
          ScontrinoException {
    DatabaseConnection.connect();
    Assistenza.setTicket(null);
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    s.save();
    Assistenza a = new Assistenza();
    TextField t = new TextField();
    t.setText("Vincenzo Aiello");
    a.setNomeCognCli(t);
    TextField c = new TextField();
    c.setText("LLAVCN99H05C129K");
    a.setCodFiscCli(c);
    TextField d = new TextField();
    d.setText("Via Pasquale Vitiello n 10");
    a.setIndirizzoResiCli(d);
    TextField e = new TextField();
    e.setText("Bevanda");
    a.setTipoProdotto(e);
    TextField f = new TextField();
    f.setText("Barbabietole");
    a.setNomeProdotto(f);
    TextField g = new TextField();
    g.setText("3404231112");
    a.setTelefonoCli(g);
    TextField h = new TextField();
    h.setText("1");
    a.setNumSerieProd(h);
    TextField i = new TextField();
    i.setText(Long.toString(s.getId()));
    a.setNumScontrino(i);
    TextField l = new TextField();
    l.setText(s.getData().substring(0, s.getData().indexOf(" ")));
    a.setDataScontrino(l);
    TextField m = new TextField();
    m.setText("1000000000001");
    a.setCodProdotto(m);
    a.openAssistenzaDettagliProb(null);
    assertEquals(null, Assistenza.getTicket());
    DatabaseConnection.close();
  }

  @Test
  public void openAssistenzaDettagliProbNumeroDiserieSbagliato2()
          throws DatabaseException, ProdottoException, ElencaException, IOException,
          ScontrinoException {
    DatabaseConnection.connect();
    Assistenza.setTicket(null);
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    s.save();
    Assistenza a = new Assistenza();
    TextField t = new TextField();
    t.setText("Vincenzo Aiello");
    a.setNomeCognCli(t);
    TextField c = new TextField();
    c.setText("LLAVCN99H05C129K");
    a.setCodFiscCli(c);
    TextField d = new TextField();
    d.setText("Via Pasquale Vitiello n 10");
    a.setIndirizzoResiCli(d);
    TextField e = new TextField();
    e.setText("Bevanda");
    a.setTipoProdotto(e);
    TextField f = new TextField();
    f.setText("Barbabietole");
    a.setNomeProdotto(f);
    TextField g = new TextField();
    g.setText("3404231112");
    a.setTelefonoCli(g);
    TextField h = new TextField();
    h.setText("VSJAKDSJDNSJDDSAJDJLSAJDLASDJSKLDNSJLDNSJLDNDSJLNDDJNCDLNCDLNLDNFDLNLDNFDLNDFLNDALNDALFNDALFNADLFNADFLADNFLADNFADNFDLFNALFNADLFNADLFNDALFDANFLDANFDLAKKKKKKKKKNDALFNDALFNADLFNDFNDLANCLKDAKLADNCKLDNCLDANCNDANCADCNLDANCLADNCLADNFCADNFADNFADKNFDLAKNFDKANFDKANFDJANFLDANFKDFNKDNFKLDNFLADNFLADNFLADNFLADNFADLFNALFNADLFNADLFNDAL");
    a.setNumSerieProd(h);
    TextField i = new TextField();
    i.setText(Long.toString(s.getId()));
    a.setNumScontrino(i);
    TextField l = new TextField();
    l.setText(s.getData().substring(0, s.getData().indexOf(" ")));
    a.setDataScontrino(l);
    TextField m = new TextField();
    m.setText("1000000000001");
    a.setCodProdotto(m);
    a.openAssistenzaDettagliProb(null);
    assertEquals(null, Assistenza.getTicket());
    DatabaseConnection.close();
  }

  @Test
  public void openAssistenzaDettagliProbCodiceScontrinoSbagliato()
      throws DatabaseException, ProdottoException, ElencaException, IOException,
          ScontrinoException {
    DatabaseConnection.connect();
    Assistenza.setTicket(null);
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    s.save();
    Assistenza a = new Assistenza();
    TextField t = new TextField();
    t.setText("Vincenzo Aiello");
    a.setNomeCognCli(t);
    TextField c = new TextField();
    c.setText("LLAVCN99H05C129K");
    a.setCodFiscCli(c);
    TextField d = new TextField();
    d.setText("Via Pasquale Vitiello n 10");
    a.setIndirizzoResiCli(d);
    TextField e = new TextField();
    e.setText("Bevanda");
    a.setTipoProdotto(e);
    TextField f = new TextField();
    f.setText("Barbabietole");
    a.setNomeProdotto(f);
    TextField g = new TextField();
    g.setText("3404231112");
    a.setTelefonoCli(g);
    TextField h = new TextField();
    h.setText("1000000000001");
    a.setNumSerieProd(h);
    TextField i = new TextField();
    i.setText("nddk");
    a.setNumScontrino(i);
    TextField l = new TextField();
    l.setText(s.getData().substring(0, s.getData().indexOf(" ")));
    a.setDataScontrino(l);
    TextField m = new TextField();
    m.setText("1000000000001");
    a.setCodProdotto(m);
    a.openAssistenzaDettagliProb(null);
    assertEquals(null, Assistenza.getTicket());
    DatabaseConnection.close();
  }



  @Test
  public void openAssistenzaDettagliProbDataSBagliata()
      throws DatabaseException, ProdottoException, ElencaException, IOException,
          ScontrinoException {
    DatabaseConnection.connect();
    Assistenza.setTicket(null);
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    s.save();
    Assistenza a = new Assistenza();
    TextField t = new TextField();
    t.setText("Vincenzo Aiello");
    a.setNomeCognCli(t);
    TextField c = new TextField();
    c.setText("LLAVCN99H05C129K");
    a.setCodFiscCli(c);
    TextField d = new TextField();
    d.setText("Via Pasquale Vitiello n 10");
    a.setIndirizzoResiCli(d);
    TextField e = new TextField();
    e.setText("Bevanda");
    a.setTipoProdotto(e);
    TextField f = new TextField();
    f.setText("Barbabietole");
    a.setNomeProdotto(f);
    TextField g = new TextField();
    g.setText("3404231112");
    a.setTelefonoCli(g);
    TextField h = new TextField();
    h.setText("1000000000001");
    a.setNumSerieProd(h);
    TextField i = new TextField();
    i.setText(Long.toString(s.getId()));
    a.setNumScontrino(i);
    TextField l = new TextField();
    l.setText(s.getData());
    a.setDataScontrino(l);
    TextField m = new TextField();
    m.setText("1000000000001");
    a.setCodProdotto(m);
    a.openAssistenzaDettagliProb(null);
    assertEquals(null, Assistenza.getTicket());
    DatabaseConnection.close();
  }

  @Test
  public void openAssistenzaDettagliProbCodiceProdottoSbagliato()
      throws DatabaseException, ProdottoException, ElencaException, IOException,
          ScontrinoException {
    DatabaseConnection.connect();
    Assistenza.setTicket(null);
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    s.save();
    Assistenza a = new Assistenza();
    TextField t = new TextField();
    t.setText("Vincenzo Aiello");
    a.setNomeCognCli(t);
    TextField c = new TextField();
    c.setText("LLAVCN99H05C129K");
    a.setCodFiscCli(c);
    TextField d = new TextField();
    d.setText("Via Pasquale Vitiello n 10");
    a.setIndirizzoResiCli(d);
    TextField e = new TextField();
    e.setText("Bevanda");
    a.setTipoProdotto(e);
    TextField f = new TextField();
    f.setText("Barbabietole");
    a.setNomeProdotto(f);
    TextField g = new TextField();
    g.setText("3404231112");
    a.setTelefonoCli(g);
    TextField h = new TextField();
    h.setText("1000000000001");
    a.setNumSerieProd(h);
    TextField i = new TextField();
    i.setText(Long.toString(s.getId()));
    a.setNumScontrino(i);
    TextField l = new TextField();
    l.setText(s.getData().substring(0, s.getData().indexOf(" ")));
    a.setDataScontrino(l);
    TextField m = new TextField();
    m.setText("1");
    a.setCodProdotto(m);
    a.openAssistenzaDettagliProb(null);
    assertEquals(null, Assistenza.getTicket());
    DatabaseConnection.close();
  }

  @Test
  public void openAssistenzaDettagliProbCodiceProdottoSbagliato2()
          throws DatabaseException, ProdottoException, ElencaException, IOException,
          ScontrinoException {
    DatabaseConnection.connect();
    Assistenza.setTicket(null);
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    s.save();
    Assistenza a = new Assistenza();
    TextField t = new TextField();
    t.setText("Vincenzo Aiello");
    a.setNomeCognCli(t);
    TextField c = new TextField();
    c.setText("LLAVCN99H05C129K");
    a.setCodFiscCli(c);
    TextField d = new TextField();
    d.setText("Via Pasquale Vitiello n 10");
    a.setIndirizzoResiCli(d);
    TextField e = new TextField();
    e.setText("Bevanda");
    a.setTipoProdotto(e);
    TextField f = new TextField();
    f.setText("Barbabietole");
    a.setNomeProdotto(f);
    TextField g = new TextField();
    g.setText("3404231112");
    a.setTelefonoCli(g);
    TextField h = new TextField();
    h.setText("1000000000001");
    a.setNumSerieProd(h);
    TextField i = new TextField();
    i.setText(Long.toString(s.getId()));
    a.setNumScontrino(i);
    TextField l = new TextField();
    l.setText(s.getData().substring(0, s.getData().indexOf(" ")));
    a.setDataScontrino(l);
    TextField m = new TextField();
    m.setText("1000000000300");
    a.setCodProdotto(m);
    Exception ex = assertThrows(ProdottoNotFoundException.class,()->{
      a.openAssistenzaDettagliProb(null);
    }) ;
    assertEquals("Prodotto non trovato", ex.getMessage());
    DatabaseConnection.close();
  }


  @Test
  public void openAssistenzaDettagliNumeroScontrino()
      throws DatabaseException, ProdottoException, ElencaException, IOException,
          ScontrinoException {
    DatabaseConnection.connect();
    Assistenza.setTicket(null);
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    s.save();
    Assistenza a = new Assistenza();
    TextField t = new TextField();
    t.setText("Vincenzo Aiello");
    a.setNomeCognCli(t);
    TextField c = new TextField();
    c.setText("LLAVCN99H05C129K");
    a.setCodFiscCli(c);
    TextField d = new TextField();
    d.setText("Via Pasquale Vitiello n 10");
    a.setIndirizzoResiCli(d);
    TextField e = new TextField();
    e.setText("Bevanda");
    a.setTipoProdotto(e);
    TextField f = new TextField();
    f.setText("Barbabietole");
    a.setNomeProdotto(f);
    TextField g = new TextField();
    g.setText("3404231112");
    a.setTelefonoCli(g);
    TextField h = new TextField();
    h.setText("1000000000001");
    a.setNumSerieProd(h);
    TextField i = new TextField();
    i.setText("");
    a.setNumScontrino(i);
    TextField l = new TextField();
    l.setText(s.getData().substring(0, s.getData().indexOf(" ")));
    a.setDataScontrino(l);
    TextField m = new TextField();
    m.setText("1000000000001");
    a.setCodProdotto(m);
    a.openAssistenzaDettagliProb(null);
    assertEquals(null, Assistenza.getTicket());
    DatabaseConnection.close();
  }

  @Test
  public void salvaTicketCorretto()
      throws DatabaseException, ProdottoException, ElencaException, ScontrinoException,
          IOException {
    DatabaseConnection.connect();
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    s.save();
    Assistenza a = new Assistenza();
    TextField dettagli = new TextField();
    dettagli.setText("Non è buono");
    a.setDettagliProb(dettagli);
    Ticket t =
        new Ticket(
            "Vincenzo Aiello",
            "LLAVCN99H05C129K",
            "Via Pasquale vitiello",
            "Alimentare",
            "Barbabietole",
            "1000000000001",
            3404231112l,
            s.getId(),
            s.getData().substring(0, s.getData().indexOf(" ")),
            1000000000001l);
    Assistenza.setTicket(t);
    Exception ex =
        assertThrows(
            NullPointerException.class,
            () -> {
              a.salvaTicket(null);
            });
    assertEquals("java.lang.NullPointerException", ex.getClass().getName());
    DatabaseConnection.close();
  }

  @Test
  public void salvaTicketSbagliato()
      throws DatabaseException, ProdottoException, ElencaException, ScontrinoException,
          IOException {
    DatabaseConnection.connect();
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    s.save();
    Assistenza a = new Assistenza();
    TextField dettagli = new TextField();
    dettagli.setText("");
    a.setDettagliProb(dettagli);
    Ticket t =
        new Ticket(
            "Vincenzo Aiello",
            "LLAVCN99H05C129K",
            "Via Pasquale vitiello",
            "Alimentare",
            "Barbabietole",
            "1000000000001",
            3404231112l,
            s.getId(),
            s.getData().substring(0, s.getData().indexOf(" ")),
            1000000000001l);
    Assistenza.setTicket(t);
    try {
      a.salvaTicket(null);
    } catch (NullPointerException ex) {
      assertFalse(true);
    } finally {
      DatabaseConnection.close();
    }
  }
}
