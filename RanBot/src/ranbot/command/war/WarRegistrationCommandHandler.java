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

public class WarRegistrationCommandHandler implements CommandHandler {

  @Override
  public void processCommand(MessageCommand messageCommand)
      throws DiscordException, MissingPermissionsException, RateLimitException {
    try {
      IChannel channel = messageCommand.getChannel();
      IUser author = messageCommand.getAuthor();

      Commander commander = WarDBUtils.retrieveCommander(author.getID());
      if (commander != null) {
        channel.sendMessage(Messages.WAR_REGISTER_EXISTING.getMessage(author.getName()));
        return;
      }

      WarDBUtils.insertDefaultCommander(author.getID());
      commander = WarDBUtils.retrieveCommander(author.getID());
      channel.sendMessage(Messages.WAR_REGISTER_NEW.getMessage(author.getName(), commander.getMoney()));
    } catch (SQLException e) {
      throw new DiscordException("Failed to do DB operation on war registration. Cause: " + e.getMessage());
    }
  }
}
