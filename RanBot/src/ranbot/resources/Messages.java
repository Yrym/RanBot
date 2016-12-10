package ranbot.resources;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public enum Messages {
  HELP_MESSAGE("help_message"),
  WAR_MESSAGE("war"),
  WAR_REGISTER_EXISTING("war.register.existing"),
  WAR_REGISTER_NEW("war.register.new"),
  WAR_DEREGISTER_EXISTING("war.deregister.existing"),
  WAR_DEREGISTER_NEW("war.deregister.new"),
  WAR_STATS_PARTICIPANT("war.stats.participant"),
  WAR_STATS_NON_PARTICIPANT("war.stats.non_participant");

  private static ResourceBundle bundle;
  static {
    bundle = ResourceBundle.getBundle("ranbot.resources.messages");
  }

  private String messageKey;

  private Messages(String messageKey) {
    this.messageKey = messageKey;
  }

  public String getMessage() {
    return bundle.getString(messageKey);
  }

  public String getMessage(Object... args) {
    return MessageFormat.format(getMessage(), args);
  }
}
