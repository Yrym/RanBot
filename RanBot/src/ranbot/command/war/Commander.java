package ranbot.command.war;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Commander {
  private final String id;
  private int rankId;
  private long exp;
  private long money;
  private final long joinTime;

  public Commander(String id, long joinDate) {
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
   * Retrieves the time the user has started being a commander. This is in UNIX
   * time.
   * 
   * @return The time the user has joined.
   */
  public long getJoinTime() {
    return joinTime;
  }

  /**
   * Retrieves the date when the user has started being a commander. This is in
   * UTC.
   * 
   * @return The date the user has joined.
   */
  public Date getJoinDate() {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    calendar.setTimeInMillis(getJoinTime());
    return calendar.getTime();
  }
}
