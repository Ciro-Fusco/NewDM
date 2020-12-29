package exceptions;

import controller.AlertMessage;

public class UtenteNotFoundException extends Exception {

  public UtenteNotFoundException(String message) {
    super(message);
    AlertMessage.showError(message);
  }
}
