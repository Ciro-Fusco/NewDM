package exceptions;

import controller.AlertMessage;

public class ProdottoException extends Exception {
  public ProdottoException(String message) {
    super(message);
    AlertMessage.showError(message);
  }
}
