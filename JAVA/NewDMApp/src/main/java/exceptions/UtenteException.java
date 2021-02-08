package exceptions;

import presentazione.AlertMessage;

/** Eccezione lanciata dalle classi che gestiscono gli utenti. */
public class UtenteException extends Exception {

  public UtenteException(String message) {
    super(message);
    AlertMessage.showError(message);
  }
}
