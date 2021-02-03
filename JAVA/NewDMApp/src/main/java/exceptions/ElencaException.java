package exceptions;

import presentazione.AlertMessage;

/**
 * Eccezione riguardo la relazione tra un prodotto e uno scontrino
 */
public class ElencaException extends Exception {
  public ElencaException(String message) {
    super(message);
    AlertMessage.showError(message);
  }
}
