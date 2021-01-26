package driver;

import db.DatabaseConnection;
import cassa.Scontrino;
import exceptions.*;

import java.util.Scanner;

public class ScontrinoDriver {

  public static void main(String[] args) throws DatabaseException, ProdottoException, ElencaException, ScontrinoException, TicketException {
    DatabaseConnection.connect();
    Scontrino s = null;
    int i;
    Scanner in = new Scanner(System.in);
    do {
      System.out.println("1 : Crea nuovo scontrino");
      System.out.println("2 : Aggiungi un prodotto allo scontrino");
      System.out.println("3 : Salva lo scontrino");
      System.out.println("4 : Verifica uno scontrino");
      System.out.println("-1 : Esci");
      i = in.nextInt();
      in.nextLine();
      switch (i) {

        case 1:
          s= new Scontrino();
          break;

        case 2:
          if (s == null) {
            System.out.println("Crea prima lo scontrino");
          } else {
            System.out.println("Inserire codice prodotto");
            s.addProdotto(in.nextLong());
          }
          break;

        case 3:
          if (s == null){
            System.out.println("Crea prima lo scontrino");
          }else{
          s.save();
          }
          break;

        case 4:
            System.out.println("Inserire codice scontrino");
            Long cod=in.nextLong();
            in.nextLine();
            System.out.println("Inserire data scontrino");
            String data = in.next();
            Scontrino.checkScontrino(cod,data);
            System.out.println("Scontrino trovato!");
            break;
      }

    } while (i != -1);

  }

}
