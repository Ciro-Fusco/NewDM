package exceptions;

import presentazione.AlertMessage;

/** Eccezione lanciata dai metodi che gestiscono le eccezioni. */
public class ProdottoException extends Exception {
  public ProdottoException(String message) {
    super(message);
    AlertMessage.showError(message);
  }
}
