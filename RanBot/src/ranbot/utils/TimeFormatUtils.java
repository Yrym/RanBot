package ranbot.utils;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeFormatUtils {
  private static final String FULL_DATE_FORMAT = "MMMM dd yyyy, kk:mm z";
  /**
   * A formatter that formats the given to display the following pattern:
   * "Month day year, 24:00 UTC".
   */
  public static final DateTimeFormatter UTC_FULL_DATE_FORMATTER = DateTimeFormatter.ofPattern(FULL_DATE_FORMAT)
      .withZone(ZoneId.of("UTC"));
}
