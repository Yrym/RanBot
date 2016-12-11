package ranbot.command.mtg;

import ranbot.command.CommandHandler;
import ranbot.command.MessageCommand;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class MtgCommandHandler implements CommandHandler {
  @Override
  public void processCommand(MessageCommand messageCommand) throws DiscordException, MissingPermissionsException, RateLimitException {
    IChannel channel = messageCommand.getChannel();
    String card = messageCommand.getArg().replace(' ','+');
    // TODO use magiccards for higher quality cards, maybe add some more info to msg?
    String message = String.format("http://gatherer.wizards.com/Handlers/Image.ashx?name=%s&type=card",card);
    channel.sendMessage(message);
  }
}
