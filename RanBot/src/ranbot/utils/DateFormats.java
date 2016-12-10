package ranbot.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormats {

  public static final String FULL_DATE_FORMAT = "MMMM dd yyyy, kk:mm z";

  private DateFormats() {
    // Hidden.
  }

  /**
   * Formats the given date to a full "Month day year, 24:00 GMT" display.
   * 
   * @param date
   *          The date to format.
   * @return The formatted date.
   */
  public static String formatAsFullDate(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat(FULL_DATE_FORMAT);
    return sdf.format(date);
  }
}
