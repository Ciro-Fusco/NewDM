package exceptions;

import controller.AlertMessage;

public class ScontrinoException extends Exception {
    public ScontrinoException(String message) {
        super(message);
        AlertMessage.showError(message);
    }
}
