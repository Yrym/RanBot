package ranbot.command.war.provisions;

import ranbot.resources.Messages;

public class WarProvisions {

  private final long money;

  /**
   * Creates a war provision that contains the given money.
   * 
   * @param money
   *          The amount of money in the provision.
   */
  public WarProvisions(long money) {
    if (money < 0) {
      throw new IllegalArgumentException("War provisions money must be greater than 0.");
    }
    this.money = money;
  }

  /**
   * Retrieves the money in the provisions. This is never negative.
   * 
   * @return The amount of money provided in the provisions.
   */
  public long getMoney() {
    return money;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (money != 0) {
      sb.append(Messages.WAR_PROVISIONS_MONEY.getMessage(money));
    }

    if (sb.length() == 0) {
      sb.append(Messages.WAR_PROVISIONS_NONE.getMessage());
    }

    return sb.toString();
  }
}
