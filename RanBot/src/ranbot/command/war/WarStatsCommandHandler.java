package ranbot.command.war;

import java.sql.SQLException;

import org.apache.commons.lang3.time.DateFormatUtils;

import ranbot.command.CommandHandler;
import ranbot.command.MessageCommand;
import ranbot.resources.Messages;
import ranbot.utils.DateFormats;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class WarStatsCommandHandler implements CommandHandler {

  @Override
  public void processCommand(MessageCommand messageCommand)
      throws DiscordException, MissingPermissionsException, RateLimitException {
    try {
      IChannel channel = messageCommand.getChannel();
      IUser author = messageCommand.getAuthor();

      Commander commander = WarDBUtils.retrieveCommander(author.getID());
      if (commander == null) {
        channel.sendMessage(Messages.WAR_STATS_NON_PARTICIPANT.getMessage(author.getName()));
      } else {
        String formattedJoinDate = DateFormatUtils.formatUTC(commander.getJoinTime(), DateFormats.FULL_DATE_FORMAT);
        channel.sendMessage(Messages.WAR_STATS_PARTICIPANT.getMessage(author.getName(), commander.getRankTitle(),
            formattedJoinDate, commander.getExp(), commander.getMoney()));
      }
    } catch (SQLException e) {
      new DiscordException("Unable to retrieve user war stats.");
    }
  }

}
