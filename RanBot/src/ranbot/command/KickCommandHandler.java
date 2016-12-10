package ranbot.command;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class KickCommandHandler implements CommandHandler  {

	@Override
	public void processCommand(MessageCommand messageCommand)
			throws DiscordException, MissingPermissionsException, RateLimitException {
		final IChannel channel = messageCommand.getChannel();
		if (messageCommand.getAuthor().getID().contains("252959293240901644") 
				|| messageCommand.getAuthor().getID().contains("101207043343409152"))
		{
			channel.sendMessage("🔨 How about I kik you for lood instead 🔨 <:awoo:254769700653891584>");
			return;
		} 
		String user = messageCommand.getArg();
		if (user == null)
		{
			channel.sendMessage(messageCommand.getAuthor() + " Gimme someone to kik! 🔨🔨🔨🔨🔨 <:awoo:254769700653891584>");
			return;
		}
		System.out.println("Kicked user: " + user);
		if (user.toLowerCase().contains("squish") || user.contains("209462982441172993"))
		{
			channel.sendMessage("Wahhhh dont kik Squishhh sama~~~ Take this instead");
			channel.sendMessage("<:ran:255597650877874176> lood");
		}else if (user.toLowerCase().contains("ran") || user.contains("256434336226738176"))
		{
			channel.sendMessage("Wahhhh dont kik me~~~~~");
			channel.sendMessage("<:ran:255597650877874176> lood");
		} else if(user.length() > 0)
		{
			channel.sendMessage("Hehe 🔨 " + user + " 🔨 fuk off~");
		} 
	}

}
