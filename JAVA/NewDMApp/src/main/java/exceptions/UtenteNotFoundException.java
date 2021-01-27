package exceptions;

import start.AlertMessage;

public class UtenteNotFoundException extends Exception {

  public UtenteNotFoundException(String message) {
    super(message);
    AlertMessage.showError(message);
  }
}
