package ranbot.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class InstantFormatterUtils {
  private static final String FULL_DATE_FORMAT = "MMMM dd yyyy, kk:mm z";
  private static final DateTimeFormatter UTC_FULL_DATE_FORMATTER = DateTimeFormatter.ofPattern(FULL_DATE_FORMAT)
      .withZone(ZoneId.of("UTC"));

  /**
   * Formats the given instant to display the following pattern:
   * "Month day year, 24:00 UTC".
   * 
   * @param date
   *          The date to format.
   * @return The formatted date.
   */
  public static String formatFullDateUTC(Instant instant) {
    return UTC_FULL_DATE_FORMATTER.format(instant);
  }
}
