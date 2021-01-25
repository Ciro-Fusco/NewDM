package controller;

import db.DatabaseConnection;
import de.saxsys.javafx.test.JfxRunner;
import entity.Scontrino;
import exceptions.DatabaseException;
import exceptions.ElencaException;
import exceptions.ProdottoException;
import exceptions.ScontrinoException;
import javafx.scene.control.TextField;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(JfxRunner.class)
public class AssistenzaTest {

  @Test
  public void openAssistenzaDettagliProbCorretto() throws DatabaseException, ProdottoException, ElencaException, IOException, ScontrinoException {
    DatabaseConnection.connect();
    Scontrino s = new Scontrino();
    s.addProdotto(1000000000001l);
    s.save();
    Assistenza a = new Assistenza();
    TextField t = new TextField();
    t.setText("Vincenzo Aiello");
    a.setNomeCognCli(t);
    t.setText("LLAVCN99H05C129K");
    a.setCodFiscCli(t);
    t.setText("Via Pasquale Vitiello n 10");
    a.setIndirizzoResiCli(t);
    t.setText("Bevanda");
    a.setTipoProdotto(t);
    t.setText("acqua San BENEDETTO");
    a.setNomeProdotto(t);
    t.setText("1000000000001");
    a.setNumSerieProd(t);
    t.setText("");
    a.setTelefonoCli(t);
    t.setText(Long.toString(s.getId()));
    a.setNumScontrino(t);
    t.setText(s.getData());
    a.setDataScontrino(t);
    t.setText("1000000000001");
    a.setCodProdotto(t);
    a.openAssistenzaDettagliProb(null);
    assertNotEquals(null,Assistenza.getTicket());
    DatabaseConnection.close();
  }

  @Test
  public void salvaTicket() {}

}
