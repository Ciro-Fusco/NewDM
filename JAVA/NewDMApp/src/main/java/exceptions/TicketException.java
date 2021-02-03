package exceptions;

import presentazione.AlertMessage;

/**
 * Eccezione lanciata dai metodi che gestiscono i Ticket
 */
public class TicketException extends Exception {
  public TicketException(String message) {
    super(message);
    AlertMessage.showError(message);
  }
}
