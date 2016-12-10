package ranbot.command;

import static java.util.Objects.requireNonNull;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public class MessageCommand {
  private final IMessage message;
  private final String action;
  private final String arg;

  /**
   * Creates a message command
   * 
   * @param message
   * @param action
   * @param arg
   */
  public MessageCommand(IMessage message, String action, String arg) {
    this.message = requireNonNull(message);
    this.action = requireNonNull(action).toLowerCase();
    this.arg = arg;
  }

  /**
   * Retrieves the action specified by this command.
   * 
   * @return The specified action of the command.
   */
  public String getAction() {
    return action;
  }

  /**
   * Retrieves the argument for this particular command. Can be {@code null}.
   * 
   * @return The argument, if any.
   */
  public String getArg() {
    return arg;
  }

  /**
   * Retrieves the channel where the command came from.
   * 
   * @return The channel where the command came from.
   */
  public IChannel getChannel() {
    return message.getChannel();
  }

  /**
   * Retrieves the author who initiated the command.
   * 
   * @return The author.
   */
  public IUser getAuthor() {
    return message.getAuthor();
  }

  /**
   * Retrieves the details of the client who initiated the command.
   * 
   * @return The details of the client.
   */
  public IDiscordClient getClient() {
    return message.getClient();
  }

  /**
   * Retrieves the guild this command came from.
   * 
   * @return The guild where the command came from.
   */
  public IGuild getGuild() {
    return message.getGuild();
  }

  /**
   * Retrieves the sub command from this command. If no possible sub command can
   * be retrieved, then this returns null.
   * 
   * @return The sub command from this command.
   */
  public MessageCommand getSubCommand() {
    if (getArg() == null) {
      return null;
    }

    String subAction = null;
    String subArg = null;

    int index = getArg().indexOf(" ");
    if (index == -1) {
      subAction = getArg();
    } else {
      subAction = getArg().substring(0, index);
      subArg = getArg().substring(index + 1);
    }
    return new MessageCommand(message, subAction, subArg);
  }

  @Override
  public String toString() {
    return "Command [action=" + action + ", arg=" + arg + "]";
  }
}
