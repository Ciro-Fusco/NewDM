package exceptions;

/**
 * Eccezione lanciata quando non è possibile autenticare l'utente nel sistema
 */
public class UtenteNotFoundException extends UtenteException {
  public UtenteNotFoundException(String message) {
    super(message);
  }
}
