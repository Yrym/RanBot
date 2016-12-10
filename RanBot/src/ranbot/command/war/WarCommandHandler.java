package ranbot.command.war;

import java.util.HashMap;
import java.util.Map;

import ranbot.command.CommandHandler;
import ranbot.command.MessageCommand;
import ranbot.resources.Messages;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class WarCommandHandler implements CommandHandler {

  private final Map<String, CommandHandler> warCommandHandlerMap;

  public WarCommandHandler() {
    warCommandHandlerMap = new HashMap<>();
    warCommandHandlerMap.put("join", new WarRegistrationCommandHandler());
    warCommandHandlerMap.put("stats", new WarStatsCommandHandler());
    warCommandHandlerMap.put("leave", new WarDeregistrationCommandHandler());
  }

  @Override
  public void processCommand(MessageCommand messageCommand)
      throws DiscordException, MissingPermissionsException, RateLimitException {
    IChannel channel = messageCommand.getChannel();

    MessageCommand subCommand = messageCommand.getSubCommand();
    if (subCommand == null) {
      channel.sendMessage(Messages.WAR_MESSAGE.getMessage());
    } else {
      CommandHandler commandHandler = warCommandHandlerMap.get(subCommand.getAction());
      if (commandHandler == null) {
        System.out.println("Unsupported war command.");
        return;
      }
      commandHandler.processCommand(subCommand);
    }
  }

}
