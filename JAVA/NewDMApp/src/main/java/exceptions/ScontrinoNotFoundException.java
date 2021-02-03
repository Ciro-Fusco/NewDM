package exceptions;

/** Eccezione lanciata quando non è possibile trovare lo scontrino a database */
public class ScontrinoNotFoundException extends ScontrinoException {

  public ScontrinoNotFoundException(String message) {
    super(message);
  }
}
