package ranbot.database;

/**
 * An exception that indicates an invalid upgrade script. This could mean one or
 * a combination of the following:
 * <ul>
 * <li>The filename of the script does not match the expected pattern.</li>
 * </ul>
 */
public class InvalidUpgradeScriptException extends RuntimeException {

  private static final long serialVersionUID = 711296281906504230L;

  /**
   * Constructs a new exception with the specified reason.
   *
   * @param message
   *          The reason why the upgrade script is invalid.
   */
  public InvalidUpgradeScriptException(String message) {
    super(message);
  }
}
