package business.utenza;

import persistenza.DatabaseConnection;
import exceptions.DatabaseException;
import exceptions.UtenteException;
import exceptions.UtenteNotFoundException;
import persistenza.UtenteDao;

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
   * @param cassa flag autorizzazione cassa
   * @param magazzino flag autorizzazione magazzino
   * @param assistenza flag autorizzazione assistenza
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

  /**
   * Restituisce il valore del flag cassa
   * @return il valore del flag cassa
   */
  public static boolean isCassa() {
    return cassa;
  }

  /**
   * Imposta il valore del flag cassa
   * @param cassa Il valore del flag cassa
   */
  public static void setCassa(boolean cassa) {
    Utente.cassa = cassa;
  }

  /**+
   * Restituisce il valore del flag magazzino
   * @return il valore del flag magazzino
   */
  public static boolean isMagazzino() {
    return magazzino;
  }

  /**
   * Imposta il valore del flag magazzino
   * @param magazzino Il valore da assegnare a magazzino
   */
  public static void setMagazzino(boolean magazzino) {
    Utente.magazzino = magazzino;
  }

  /**
   * Restituisce il valore del flag assistenza
   * @return il valore del flag assistenza
   */
  public static boolean isAssistenza() {
    return assistenza;
  }

  /**
   * Imposta il valore del flag assistenza
   * @param assistenza Il valore del flag assistenza
   */
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
   * Crea una connessione con il persistenza e verifica se un utente esiste
   * @param us Nome utente
   * @param pass Password in chiaro
   * @throws UtenteNotFoundException Utente non trovato
   * @throws DatabaseException Errore generico del Database
   */
  public static void login(String us, String pass)
      throws UtenteException, DatabaseException {
    DatabaseConnection.getInstance();
    UtenteDao.login(us, pass);
  }

  /**
   * Elimina le informazioni dell'utente dal sistema e chiude la connessione con il persistenza
   * @throws DatabaseException Errore nel persistenza
   */
  public static void logout() throws DatabaseException {
    clear();
    DatabaseConnection.close();
  }
}
