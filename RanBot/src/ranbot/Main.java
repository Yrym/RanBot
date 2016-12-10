package ranbot;

import ranbot.command.CommandListener;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.DiscordDisconnectedEvent;
import sx.blah.discord.handle.impl.events.MentionEvent;
import sx.blah.discord.handle.obj.IMessage;

public class Main {
	
	public static void main(String[] args) throws Exception {
		ClientBuilder clientBuilder = new ClientBuilder();
		clientBuilder.withToken("INSERT TOKEN HERE");
		IDiscordClient client = clientBuilder.login();

		EventDispatcher dispatcher = client.getDispatcher();
		
		dispatcher.registerListener(new IListener<DiscordDisconnectedEvent>() {
			@Override
			public void handle(DiscordDisconnectedEvent event) {
				try {
					System.out.println("Have been disconnected. " + event.getReason());
					client.login();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		dispatcher.registerListener(new IListener<MentionEvent>() {
			@Override
			public void handle(MentionEvent event) {
				try {
					IMessage message = event.getMessage();
					System.out.println("Content: " + message.getContent());
					if (message.getContent().contains("logout")) {
						client.logout();
					} else if (message.getContent().toLowerCase().contains("knock knock")){
						message.getChannel().sendMessage("Who's there?");
					}  else if (message.getAuthor().getID().contains("252959293240901644")
						    || (message.getAuthor().getID().contains("101207043343409152"))){
					message.getChannel().sendMessage("H-h-help h-hentaiiii!");
					} else if (message.getContent().toLowerCase().contains("god")){
						message.getChannel().sendMessage("The only god of this server is S-Squishhh s-sama!");
					} else {
						message.getChannel().sendMessage("Y-You want loods don't you?");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		dispatcher.registerListener(new CommandListener());
	}

}
