package exceptions;

import controller.AlertMessage;

public class ProdottoNotFoundException extends Exception {

  public ProdottoNotFoundException(String message) {
    super(message);
    AlertMessage.showError(message);
  }
}
