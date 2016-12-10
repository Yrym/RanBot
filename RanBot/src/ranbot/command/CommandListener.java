
package ranbot.command;

import java.util.HashMap;
import java.util.Map;

import ranbot.command.chooser.BiasedRandomChooser;
import ranbot.command.chooser.ChooserCommandHandler;
import ranbot.command.war.WarCommandHandler;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class CommandListener implements IListener<MessageReceivedEvent> {

  private final Map<String, CommandHandler> actionCommandHandlerMap;

  public CommandListener() {
    actionCommandHandlerMap = new HashMap<>();

    final ChooserCommandHandler chooseChooser = new ChooserCommandHandler(new BiasedRandomChooser());

    actionCommandHandlerMap.put("choose", chooseChooser);
    actionCommandHandlerMap.put("help", new HelpCommandHandler());
    actionCommandHandlerMap.put("war", new WarCommandHandler());
  }

  @Override
  public void handle(MessageReceivedEvent event) {
    IMessage message = event.getMessage();
    MessageCommand command = CommandUtils.getCommand(message);

    if (command == null) {
      System.out.println("Not a command.");
      return;
    }

    System.out.println("Received command: " + command);
    CommandHandler handler = actionCommandHandlerMap.get(command.getAction());
    if (handler == null) {
      System.out.println("Unsupported.");
      return;
    }

    try {
      System.out.println("Handling command with action: " + command.getAction());
      try {
        handler.processCommand(command);
      } catch (Exception e) {
        message.getChannel().sendMessage("Something went wrong: " + e.getMessage() + "\n\nPlease report to admin.");
        throw e;
      }
    } catch (DiscordException | MissingPermissionsException | RateLimitException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

    // if (command.getAction().equalsIgnoreCase("fucfuc")) {
    // try {
    // if (message.getAuthor().getName().toLowerCase().contains("yrym")) {
    // message.getChannel().sendMessage("I want it 2000 times with you
    // **Yrym**;)");
    // } else {
    // message.getChannel().sendMessage("Fuck off! I only want **Yrym!**
    // Teehee~");
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // } else if (command.equalsIgnoreCase("strip")) {
    // try {
    // message.getChannel().sendMessage("Stop being naughty, boi. Soon ;)");
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
  }
}
