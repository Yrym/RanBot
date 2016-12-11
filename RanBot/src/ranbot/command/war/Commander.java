package ranbot.command.war;

import java.time.Instant;

public class Commander {
  private final String id;
  private int rankId;
  private long exp;
  private long money;
  private Instant lastDailiesTime;
  private final Instant joinTime;

  public Commander(String id, Instant joinDate) {
    this.id = id;
    this.joinTime = joinDate;

    rankId = -1;
    exp = -1;
    money = -1;
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

  public void setRankId(int rank) {
    this.rankId = rank;
  }

  /**
   * Retrieves the id of the commander's rank.
   * 
   * @return The commander's rank.
   * @see
   */
  public int getRankId() {
    return rankId;
  }

  public String getRankTitle() {
    // TODO: get somewhere. Probably change to an enum or something. Do not
    // hardcode.
    switch (rankId) {
    case 1:
      return "Class 10";
    case 0:
      return "Newbie";
    default:
      // FIXME Do something about this bad thing.
      return "Unknown (Error)";
    }
  }

  public void setExp(long exp) {
    this.exp = exp;
  }

  /**
   * Retrieves the exp of the commander.
   * 
   * @return The commander's exp.
   */
  public long getExp() {
    return exp;
  }

  /**
   * Retrieves the Instant that the user has started being a commander.
   * 
   * @return the Instant that the user has joined.
   */
  public Instant getJoinTime() {
    return joinTime;
  }

  public void setLastDailiesTime(Instant lastDailiesTime) {
    this.lastDailiesTime = lastDailiesTime;
  }

  /**
   * Retrieves the Instant that the user has retrieved their dailies. If this is
   * {@code null}, then the user has not initiated the dailies yet.
   * 
   * @return The Instant that the user had last retrieved dailies.
   */
  public Instant getLastDailiesTime() {
    return lastDailiesTime;
  }
}
