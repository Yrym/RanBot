package ranbot.command.war;

import java.sql.SQLException;

import ranbot.command.CommandHandler;
import ranbot.command.MessageCommand;
import ranbot.resources.Messages;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class WarDeregistrationCommandHandler implements CommandHandler {

  @Override
  public void processCommand(MessageCommand messageCommand)
      throws DiscordException, MissingPermissionsException, RateLimitException {
    try {
      IChannel channel = messageCommand.getChannel();
      IUser author = messageCommand.getAuthor();

      Commander commander = WarDBUtils.retrieveCommander(author.getID());
      if (commander == null) {
        channel.sendMessage(Messages.WAR_DEREGISTER_NEW.getMessage(author.getName()));
      } else {
        channel.sendMessage(Messages.WAR_DEREGISTER_EXISTING.getMessage(author.getName()));
      }
    } catch (SQLException e) {
      throw new DiscordException("Failed to do DB operation on war deregistration. Cause: " + e.getMessage());
    }
  }

}
