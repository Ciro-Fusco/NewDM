package exceptions;

/**
 * Eccezione lanciata quando non è possibile trovare un prodotto a database.
 * Estende ProdottoException
 */
public class ProdottoNotFoundException extends ProdottoException {

  public ProdottoNotFoundException(String message) {
    super(message);
  }
}
