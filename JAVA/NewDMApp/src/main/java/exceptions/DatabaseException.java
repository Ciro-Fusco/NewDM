package exceptions;

import controller.AlertMessage;

public class DatabaseException extends Exception {

  public DatabaseException(String message) {
    super(message);
    AlertMessage.showError(message);
  }
}
