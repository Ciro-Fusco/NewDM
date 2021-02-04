package exceptions;

/** Eccezione lanciata quando non è possibile trovare il ticket a database. */
public class TicketNotFoundException extends TicketException {
  public TicketNotFoundException(String message) {
    super(message);
  }
}
