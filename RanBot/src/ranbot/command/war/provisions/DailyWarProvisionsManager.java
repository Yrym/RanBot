package ranbot.command.war.provisions;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;

import ranbot.command.war.Commander;
import ranbot.resources.Messages;
import ranbot.resources.TimeMessages;

public class DailyWarProvisionsManager implements WarProvisionsManager {

  private static final long BASE_MONEY = 100;
  private static final Duration DAILY_DURATION = Duration.ofHours(24);

  @Override
  public boolean canReceiveProvisions(Commander commander) {
    Instant lastDailiesTime = commander.getLastDailiesTime();
    Duration lastDailiesDuration = Duration.between(lastDailiesTime, Instant.now());
    return lastDailiesDuration.compareTo(DAILY_DURATION) > 0;
  }

  @Override
  public WarProvisions getProvisions(Commander commander) {
    return new WarProvisions(BASE_MONEY);
  }

  @Override
  public String getMissingRequirements(Commander commander) {
    // TODO determine if commander is really missing a requirement.
    Instant lastDailiesTime = commander.getLastDailiesTime();
    Instant nextDailiesTime = lastDailiesTime.plus(DAILY_DURATION);
    Duration lastDailiesDuration = Duration.between(Instant.now(), nextDailiesTime);
    LocalTime localTime = LocalTime.ofSecondOfDay(lastDailiesDuration.getSeconds());
    String time = TimeMessages.getFormattedTime(localTime.getHour(), localTime.getMinute(), localTime.getSecond());
    return Messages.WAR_PROVISIONS_MISSING_REQUIREMENTS_TIME.getMessage(time);
  }

}
