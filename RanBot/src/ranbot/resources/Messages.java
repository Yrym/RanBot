package ranbot.resources;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public enum Messages {
	HELP_MESSAGE("help_message"),
	SV_SEARCH_FAILED("sv_search_failed"),
	SV_SEARCH_RESULT("sv_search_result");

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
