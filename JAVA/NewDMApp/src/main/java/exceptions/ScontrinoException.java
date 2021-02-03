package exceptions;

import presentazione.AlertMessage;

/** Eccezione lanciata dai metodi che gestiscono gli scontrini */
public class ScontrinoException extends Exception {
  public ScontrinoException(String message) {
    super(message);
    AlertMessage.showError(message);
  }
}
