package driver;

import database.DatabaseConnection;
import utenza.Utente;
import exceptions.DatabaseException;
import exceptions.UtenteNotFoundException;

import java.util.Scanner;

public class UtenteDriver {

  public static void main(String[] args) throws DatabaseException, UtenteNotFoundException {
    DatabaseConnection.connect();
    int i;
    Scanner in = new Scanner(System.in);
    do {
      System.out.println("1 : Login");
      System.out.println("2 : Logout");
      System.out.println("3 : Visualizza utente");
      System.out.println("-1 : Esci");
      i = in.nextInt();
      in.nextLine();
      switch (i) {
        case 1:
          System.out.println("Inserisci username");
          System.out.println("Inserisci password");
          Utente.login(in.next(), in.next());
          break;

        case 2:
          Utente.logout();
          break;

        case 3:
          System.out.println(
              Utente.getUsername() + " " + Utente.getNome() + " " + Utente.getCognome());
          break;
      }

    } while (i != -1);
  }
}
