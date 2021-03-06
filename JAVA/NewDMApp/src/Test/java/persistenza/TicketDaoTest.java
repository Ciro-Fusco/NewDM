package persistenza;

import business.cassa.Scontrino;
import business.assistenza.Ticket;
import exceptions.*;
import org.junit.Test;
import persistenza.dao.TicketDao;

import static org.junit.Assert.*;

public class TicketDaoTest {

    @Test
    public void getTicketCorretto() throws DatabaseException, ProdottoException, ElencaException, ScontrinoException, TicketNotFoundException {
        
        Scontrino s = new Scontrino();
        s.addProdotto(1000000000001L);
        s.save();
        Ticket t = new Ticket("Gigi Cirillo","ggg","Via giardinetti n 10","Bevanda","acqua San BENEDETTO","0001121",340432l,s.getId(),s.getData().substring(0,10),1000000000001l);
        t.setProblema("non è buono");
        t.save();
        Ticket ticket = TicketDao.getTicket(t.getDataApertura(),t.getCf(),t.getNumeroDiSerie());
        assertNotEquals(null,ticket);
        DatabaseConnection.close();
    }

    @Test
    public void getTicketNonTrovato() throws DatabaseException, ProdottoException, ElencaException, ScontrinoException, TicketNotFoundException {
        
        Scontrino s = new Scontrino();
        s.addProdotto(1000000000001L);
        s.save();
        Ticket t = new Ticket("Gigi Cirillo","ggg","Via giardinetti n 10","Bevanda","acqua San BENEDETTO","22",340432l,s.getId(),s.getData().substring(0,10),1000000000001l);
        t.setProblema("non è buono");
        Exception ex = assertThrows(TicketNotFoundException.class,()->{
            Ticket ticket = TicketDao.getTicket(t.getDataApertura(),t.getCf(),t.getNumeroDiSerie());
        });
        String expectedMessage = "Ticket non trovato. Controllare i campi";
        String actualMessage = ex.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        DatabaseConnection.close();
    }

}