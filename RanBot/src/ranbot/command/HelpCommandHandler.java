package ranbot.command;

import ranbot.resources.Messages;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class HelpCommandHandler implements CommandHandler {

	@Override
	public void processCommand(MessageCommand messageCommand)
			throws DiscordException, MissingPermissionsException, RateLimitException {
		IChannel channel = messageCommand.getChannel();
		channel.sendMessage(Messages.HELP_MESSAGE.getMessage(CommandListener.getCommandKeyword()));
	}
}
