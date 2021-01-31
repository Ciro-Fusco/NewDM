package exceptions;

import presentazione.AlertMessage;

public class DatabaseException extends Exception {

  public DatabaseException(String message) {
    super(message);
    AlertMessage.showError(message);
  }
}
