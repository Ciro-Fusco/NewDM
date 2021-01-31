package exceptions;

import presentazione.AlertMessage;

public class ProdottoException extends Exception {
  public ProdottoException(String message) {
    super(message);
    AlertMessage.showError(message);
  }
}
