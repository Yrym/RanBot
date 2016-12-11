package ranbot.resources;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.StringJoiner;

import org.apache.commons.lang3.StringUtils;

public enum TimeMessages {
  SECOND("time.second"),
  MINUTE("time.minute"),
  HOUR("time.hour");

  private static final String KEY_VALUE = "value";
  private static final ResourceBundle BUNDLE;
  static {
    BUNDLE = ResourceBundle.getBundle("ranbot.resources.timeMessages");
  }

  private String singularKey;
  private String singularWithValueKey;
  private String pluralKey;
  private String pluralWithValueKey;

  private TimeMessages(String messageKey) {
    singularKey = messageKey;
    singularWithValueKey = singularKey + "." + KEY_VALUE;
    pluralKey = messageKey + "s";
    pluralWithValueKey = pluralKey + "." + KEY_VALUE;
  }

  /**
   * Retrieves a singular noun of the time.
   * 
   * @return The singular noun of the time.
   */
  public String getSingular() {
    return BUNDLE.getString(singularKey);
  }

  /**
   * Retrieves a plural noun of the time.
   * 
   * @return The plural noun of the time.
   */
  public String getPlural() {
    return BUNDLE.getString(pluralKey);
  }

  /**
   * Retrieves an appropriate time with the given value.
   * 
   * @param value
   *          The amount of time.
   * @return The appropriate description of the time.
   */
  public String getWithValue(int value) {
    if (value == 1) {
      return MessageFormat.format(BUNDLE.getString(singularWithValueKey), value);
    } else {
      return MessageFormat.format(BUNDLE.getString(pluralWithValueKey), value);
    }
  }

  /**
   * Retrieves a time in the format of <nn> Hours <nn> Minutes <nn> Seconds. The
   * units are automatically changed to singular if {@code value} is 1. If
   * {@code value} is 0, then that unit is ommitted.
   * 
   * @param hours
   *          The amount of hours.
   * @param minutes
   *          The amount of minutes.
   * @param seconds
   *          The amount of seconds.
   * @return A formatted time.
   * @see #getFormattedFullTime(int, int, int)
   */
  public static String getFormattedTime(int hours, int minutes, int seconds) {
    StringJoiner sj = new StringJoiner(StringUtils.SPACE);
    if (hours > 0) {
      sj.add(HOUR.getWithValue(hours));
    }
    if (minutes > 0) {
      sj.add(MINUTE.getWithValue(minutes));
    }
    if (seconds > 0) {
      sj.add(SECOND.getWithValue(seconds));
    }
    return sj.toString();
  }

  /**
   * Retrieves a time in the format of <nn> Hours <nn> Minutes <nn> Seconds. The
   * units are automatically changed to singular if {@code value} is 1.
   * 
   * @param hours
   *          The amount of hours.
   * @param minutes
   *          The amount of minutes.
   * @param seconds
   *          The amount of seconds.
   * @return A formatted time.
   * @see #getFormattedTime(int, int, int)
   */
  public static String getFormattedFullTime(int hours, int minutes, int seconds) {
    StringJoiner sj = new StringJoiner(StringUtils.SPACE);
    sj.add(HOUR.getWithValue(hours));
    sj.add(MINUTE.getWithValue(minutes));
    sj.add(SECOND.getWithValue(seconds));
    return sj.toString();
  }
}
