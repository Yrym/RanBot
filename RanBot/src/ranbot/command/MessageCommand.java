package ranbot.command;

import static java.util.Objects.requireNonNull;

import sx.blah.discord.handle.obj.IChannel;
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

	@Override
	public String toString() {
		return "Command [action=" + action + ", arg=" + arg + "]";
	}
}
