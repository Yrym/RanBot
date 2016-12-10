package ranbot.command;

import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public interface CommandHandler {
	void processCommand(MessageCommand messageCommand) throws DiscordException, MissingPermissionsException, RateLimitException;
	// aidora wuz heer xd
}
