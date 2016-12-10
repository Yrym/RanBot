package ranbot.command;

import sx.blah.discord.handle.obj.IMessage;

public class CommandUtils {
  public static MessageCommand getCommand(IMessage message) {
    String content = message.getContent().trim();

    // FIXME Command keyword should always be at index 0. Keyword should be
    // set by admin instead of hardcoded.
    int spaceIndex = content.indexOf(" ");
    if (spaceIndex == -1 || content.substring(0, spaceIndex).indexOf(getCommandKeyword()) != 1) {
      System.out.println("message not a command: " + content);
      return null;
    }

    String actionArg = content.substring(spaceIndex + 1);
    int actionSpaceIndex = actionArg.indexOf(" ");
    if (actionSpaceIndex == -1) {
      return new MessageCommand(message, actionArg, null);
    } else {
      String action = actionArg.substring(0, actionSpaceIndex);
      String arg = actionArg.substring(actionSpaceIndex + 1);
      return new MessageCommand(message, action, arg);
    }
  }

  /**
   * Retrieves the command keyword that the this listener accepts.
   * 
   * @return The command keyword
   */
  public static String getCommandKeyword() {
    // FIXME This should not be here, and should not be hardcoded.
    return ":ran:";
  }
}
