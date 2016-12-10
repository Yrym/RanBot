package ranbot.command.war;

public class Commander {
  private final String id;
  private long money;
  private final long joinDate;

  public Commander(String id, long joinDate) {
    this.id = id;
    this.joinDate = joinDate;
  }

  /**
   * The ID of the commander. This is the same as the user's discord ID.
   * 
   * @return The id of the commander.
   */
  public String getId() {
    return id;
  }

  public void setMoney(long money) {
    this.money = money;
  }

  /**
   * The amount of money the commander has.
   * 
   * @return The commander's money.
   */
  public long getMoney() {
    return money;
  }

  /**
   * Retrieves the time the user has started being a commander. This is in UNIX
   * time.
   * 
   * @return The time the user has joined.
   */
  public long getJoinDate() {
    return joinDate;
  }
}
