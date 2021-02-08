package exceptions;

/** Eccezione lanciata quando uno scontrino non è più valido ai fini della garanzia. */
public class ScontrinoNonValidoException extends ScontrinoException {
  public ScontrinoNonValidoException(String message) {
    super(message);
  }
}
