package net.froihofer.dsfinance.exceptions;

public class UnauthorizedException extends Exception {
  
  /**
   * Creates a new instance of <code>BankServerException</code> without detail message.
   */
  public UnauthorizedException() {
  }
  
  
  /**
   * Constructs an instance of <code>BankServerException</code> with the specified detail message.
   * @param msg the detail message.
   */
  public UnauthorizedException(String msg) {
    super(msg);
  }
  
  /**
   * Creates a new instance of <code>BankServerException</code>
   * with the specified detail message and cause.
   * @param msg the detail message (which is saved for later retrieval by the
   *            <code>getMessage()</code> method).
   * @param cause the cause (which is saved for later retrieval by the
   *              <code>getCause()</code> method).
   *              (A <code>null</code> value is permitted, and indicates that
   *              the cause is nonexistent or unknown.)
   */
  public UnauthorizedException(String msg, Throwable cause) {
    super(msg,cause);
  }
  
  /**
   * Creates a new instance of <code>BankServerException</code>
   * with the specified cause.
   * @param cause the cause (which is saved for later retrieval by the
   *              <code>getCause()</code> method).
   *              (A <code>null</code> value is permitted, and indicates that
   *              the cause is nonexistent or unknown.)
   */
  public UnauthorizedException(Throwable cause) {
    super(cause);
  }
}
