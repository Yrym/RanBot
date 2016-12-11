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
  WAR_STATS_NON_PARTICIPANT("war.stats.non_participant"),
  WAR_DAILIES_PARTICIPANT("war.dailies.participant"),
  WAR_DAILES_PARTICIPANT_NO_AVAILABLE_PROVISIONS("war.dailies.participant.no_available_provisions"),
  WAR_DAILIES_NON_PARTICIPANT("war.dailies.non_participant"),
  WAR_PROVISIONS_MISSING_REQUIREMENTS("war.provisions.missing_requirements"),
  WAR_PROVISIONS_MISSING_REQUIREMENTS_TIME("war.provisions.missing_requirements.time"),
  WAR_PROVISIONS_NONE("war.provisions.nothing"),
  WAR_PROVISIONS_MONEY("war.provisions.money");

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
