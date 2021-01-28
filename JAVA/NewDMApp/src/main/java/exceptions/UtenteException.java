package exceptions;

import start.AlertMessage;

public class UtenteException extends Exception{

  public UtenteException(String message){
    super(message);
    AlertMessage.showError(message);
  }

}
