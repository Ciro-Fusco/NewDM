package Driver;

import db.DatabaseConnection;
import entity.Prodotto;
import exceptions.DatabaseException;
import exceptions.ProdottoException;

import java.util.Scanner;

public class ProdottoDriver {

  public static void main(String[] args) throws DatabaseException, ProdottoException {
    DatabaseConnection.connect();
    Prodotto p = null;
    int i;
    Scanner in = new Scanner(System.in);
    do {
      System.out.println("1 : Crea nuovo prodotto");
      System.out.println("2 : Salva un prodotto");
      System.out.println("3 : Cerca un prodotto");
      System.out.println("4 : Incrementa quantità prodotto");
      System.out.println("5 : Decrementa quantità prodotto");
      System.out.println("6 : Modifica prezzo di un prodotto");
      System.out.println("7 : Incrementa numero di prodotti acquistati");
      System.out.println("-1 : Esci");
      i = in.nextInt();
      in.nextLine();
      switch (i) {

        case 1:
          p = new Prodotto(2.50, 1L, "Driver", 50, "driverDim", "driverScad", "driverTip");
          break;

        case 2:
          if (p == null) {
            System.out.println("Crea prima il prodotto");
          } else {
            p.createProdotto();
          }
          break;

        case 3:
          System.out.println("Inserisci un codice prodotto");
          System.out.println(Prodotto.search(in.nextLong()));
          break;

        case 4:
          System.out.println("Inserisci la quantità da aggiungere al prodotto");
          Prodotto.search(1L).adddbquantity(in.nextInt());
          break;

        case 5:
          System.out.println("Inserisci la quantità da rimuovere al prodotto");
          if (p == null) {
            System.out.println("Crea prima il prodotto");
          } else {
            (p = Prodotto.search(1L)).setAcquistato(in.nextInt());
            p.leavedbquantity();
          }
          break;

        case 6:
          System.out.println("Inserisci il nuovo prezzo del prodotto");
          if (p == null) {
            System.out.println("Crea prima il prodotto");
          } else {
            p.modificaPrezzo(in.nextDouble());
          }
          break;

        case 7: System.out.println("Inserisci la quantità da incrementare");
          if (p == null) {
            System.out.println("Crea prima il prodotto");
          } else {
            p.updateAcquistato(in.nextInt());
            System.out.println(p);
          }
          break;


      }

    } while (i != -1);

  }


}
