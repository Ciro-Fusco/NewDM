package exceptions;

import start.AlertMessage;

public class ElencaException extends Exception {
  public ElencaException(String message) {
    super(message);
    //AlertMessage.showError(message);
  }
}
