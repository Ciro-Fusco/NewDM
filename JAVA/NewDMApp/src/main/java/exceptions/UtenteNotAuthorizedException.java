package exceptions;

/**
 * Eccezione lanciata quando un utente prova ad accedere ad un'area in cui non è autorizzato
 */
public class UtenteNotAuthorizedException extends UtenteException{
  public UtenteNotAuthorizedException(String message) {
    super(message);
  }
}
