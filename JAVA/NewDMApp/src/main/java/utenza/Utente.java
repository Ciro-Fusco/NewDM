package utenza;

import database.DatabaseConnection;
import exceptions.DatabaseException;
import exceptions.UtenteNotFoundException;

/**
 * Classe statica. Può contenere una sola istanza di utente alla volta. Non possiede costruttori.
 */
public class Utente {

  private static String nome;
  private static String cognome;
  private static String username;
  private static boolean cassa,magazzino,assistenza;

  /**
   * @param nome nome dell'utente
   * @param cognome cognome dell'utente
   * @param username username dell'utente
   *     <p>Inserisce le informazioni riguardo l'utente nella classe.
   */
  public static void setUtente(String nome, String cognome, String username,boolean cassa, boolean magazzino,boolean assistenza) {
    Utente.nome = nome;
    Utente.cognome = cognome;
    Utente.username = username;
    Utente.cassa = cassa;
    Utente.magazzino = magazzino;
    Utente.assistenza = assistenza;
  }

  /** Elimina le informazioni circa l'utente correntemente autenticato */
  private static void clear() {
    setUtente(null, null, null,false,false,false);
  }

  /**
   * @return nome -- il nome dell'utente autenticato; null -- se non è autenticato nessun utente;
   */
  public static String getNome() {
    return nome;
  }

  /**
   * @return cognome -- il cognome dell'utente autenticato; null -- se non è autenticato nessun
   *     utente;
   */
  public static String getCognome() {
    return cognome;
  }

  /**
   * @return Username-- Nome utente dell'utente autenticato; null -- se non è autenticato nessun
   *     utente;
   */
  public static String getUsername() {
    return username;
  }

  public static boolean isCassa() {
    return cassa;
  }

  public static void setCassa(boolean cassa) {
    Utente.cassa = cassa;
  }

  public static boolean isMagazzino() {
    return magazzino;
  }

  public static void setMagazzino(boolean magazzino) {
    Utente.magazzino = magazzino;
  }

  public static boolean isAssistenza() {
    return assistenza;
  }

  public static void setAssistenza(boolean assistenza) {
    Utente.assistenza = assistenza;
  }

  @Override
  public String toString() {
    return "Utente{"
        + "nome='"
        + nome
        + '\''
        + ", cognome='"
        + cognome
        + '\''
        + ", username='"
        + username
        + '\''
        + '}';
  }

  /**
   * @param us Nome utente
   * @param pass Password in chiaro
   * @return true se login è effettuato
   * @throws UtenteNotFoundException Utente non trovato
   * @throws DatabaseException Errore generico del Database
   */
  public static boolean login(String us, String pass)
      throws UtenteNotFoundException, DatabaseException {
    DatabaseConnection.connect();
    return UtenteDao.login(us, pass);
  }

  public static void logout() throws DatabaseException {
    clear();
    DatabaseConnection.close();
  }
}
