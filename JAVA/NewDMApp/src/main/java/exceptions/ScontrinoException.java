package exceptions;

import start.AlertMessage;

public class ScontrinoException extends Exception {
  public ScontrinoException(String message) {
    super(message);
    AlertMessage.showError(message);
  }
}
