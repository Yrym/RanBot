package ranbot.database;

/**
 * An exception that indicates a failure to upgrade the database of the server.
 */
public class DatabaseUpgradeException extends RuntimeException {

  private static final long serialVersionUID = 4491790950223679909L;

  /**
   * Constructs a new DatabaseUpgradeException with the specified detail message
   * {@link #initCause}.
   *
   * @param message
   *          The details of the upgrade failure.
   */
  public DatabaseUpgradeException(String message) {
    super(message);
  }

  /**
   * Constructs a new DatabaseUpgradeException with the specified detail message
   * and cause.
   *
   * @param message
   *          The details of the upgrade failure.
   * @param cause
   *          the cause of the failure.
   */
  public DatabaseUpgradeException(String message, Throwable cause) {
    super(message, cause);
  }
}
