package ranbot.command.war.provisions;

import ranbot.command.war.Commander;

/**
 * A manager that handles provisions for commanders.
 */
public interface WarProvisionsManager {
  /**
   * Checks if the commander is eligible for this war provisions.
   * 
   * @param commander
   *          The commander to check on.
   * @return {@code true} if {@code commander} can receive provisions.
   */
  boolean canReceiveProvisions(Commander commander);

  /**
   * Retrieves the provisions for the given {@code commander}.
   * 
   * @param commander
   *          The commander to retrieve the provisions of.
   * @return The provisions for the commander.
   */
  WarProvisions getProvisions(Commander commander);

  /**
   * Retrieves the missing requirements of the given {@code commander} for
   * retrieving the provisions.
   * 
   * @param commander
   *          The commander to check the missing requirements of.
   * @return A string that describes the missing requirements.
   */
  String getMissingRequirements(Commander commander);
}
