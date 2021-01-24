package exceptions;

import controller.AlertMessage;

public class ProdottoNotFoundException extends ProdottoException {

  public ProdottoNotFoundException(String message) {
    super(message);
  }
}
