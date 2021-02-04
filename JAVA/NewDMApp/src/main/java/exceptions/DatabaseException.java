package exceptions;

import presentazione.AlertMessage;

/** Eccezione del database, accorpa tutte le eccezioni generate dal database. */
public class DatabaseException extends Exception {
  public DatabaseException(String message) {
    super(message);
    AlertMessage.showError(message);
  }
}
