package driver;

import db.DatabaseConnection;
import assistenza.Ticket;
import exceptions.*;

import java.util.Scanner;

public class TicketDriver {

  public static void main(String[] args)
      throws DatabaseException, ProdottoException, ElencaException, ScontrinoException,
          TicketException {
    DatabaseConnection.connect();
    Ticket t = null;
    int i;
    Scanner in = new Scanner(System.in);
    do {
      System.out.println("1 : Crea nuovo ticket");
      System.out.println("2 : Salva il ticket");
      System.out.println("3 : Cerca un ticket");
      System.out.println("-1 : Esci");
      i = in.nextInt();
      in.nextLine();
      switch (i) {
        case 1:
          System.out.println("Inserire nome e cognome");
          String nome = in.next() + " " + in.next();
          System.out.println("Inserire codice fiscale");
          String cf = in.next();
          System.out.println("Inserire indirizzo");
          String indirizzo = in.nextLine();
          System.out.println("Inserire numero di telefono");
          in.nextLine();
          Long tel = in.nextLong();
          System.out.println("Inserire categoria prodotto");
          String cat = in.next();
          System.out.println("Inserire nome prodotto");
          String nomeProd = in.next();
          System.out.println("Inserire numero di serie del Prodotto");
          String serie = in.next();
          System.out.println("Inserire numero dello scontrino");
          in.nextLine();
          Long scon = in.nextLong();
          System.out.println("Inserire data dello scontrino");
          String data = in.next();
          System.out.println("Inserire codice prodotto");
          in.nextLine();
          Long prod = in.nextLong();

          t = new Ticket(nome, cf, indirizzo, cat, nomeProd, serie, tel, scon, data, prod);
          System.out.println("Inserisci il problema riscontrato");
          String prob = in.nextLine();
          t.setProblema(prob);
          System.out.println(t);
          break;

        case 2:
          if (t == null) {
            System.out.println("Crea prima il prodotto");
          } else {
            t.save();
          }
          break;

        case 3:
          System.out.println("Inserisci data di apertura");
          String data3 = in.next();
          System.out.println("Inserisci codice fiscale");
          String cf3 = in.next();
          System.out.println("Inserisci numero di serie");
          String serie3 = in.next();
          System.out.println(Ticket.getTicket(data3, cf3, serie3));
          break;
      }

    } while (i != -1);
  }
}
