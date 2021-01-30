package exceptions;

public class TicketException extends Exception {
  public TicketException(String message) {
    super(message);
    //AlertMessage.showError(message);
  }
}
