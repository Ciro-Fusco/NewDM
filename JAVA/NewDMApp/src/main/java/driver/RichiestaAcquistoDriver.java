package driver;

import persistenza.DatabaseConnection;
import business.fornitura.RichiestaAcquisto;
import exceptions.*;

import java.util.Scanner;

public class RichiestaAcquistoDriver {

  public static void main(String[] args)
      throws DatabaseException, ProdottoException, ElencaException, ScontrinoException,
          TicketException {
    DatabaseConnection.connect();
    RichiestaAcquisto r = null;
    int i;
    Scanner in = new Scanner(System.in);
    do {
      System.out.println("1 : Crea una nuova richiesta di acquisto");
      System.out.println("2 : Salva la richiesta");
      System.out.println("-1 : Esci");
      i = in.nextInt();
      in.nextLine();
      switch (i) {
        case 1:
          r = new RichiestaAcquisto();
          System.out.println("Inserire codice prodotto");
          r.setCodice(in.nextLong());
          System.out.println("Inserire quantità da acquistare");
          r.setQuantity(in.nextInt());
          System.out.println(r);
          break;

        case 2:
          if (r == null) {
            System.out.println("Crea prima la richiesta");
          } else {
            r.save();
            System.out.println("Richiesta salvata!");
          }
          break;
      }

    } while (i != -1);
  }
}
