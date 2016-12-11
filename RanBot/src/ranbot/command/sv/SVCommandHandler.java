package ranbot.command.sv;

import ranbot.command.CommandHandler;
import ranbot.command.MessageCommand;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

import java.io.IOException;
import java.lang.StringBuilder;

public class SVCommandHandler implements CommandHandler{
  @Override
  public void processCommand(MessageCommand messageCommand) throws DiscordException, MissingPermissionsException, RateLimitException {
    IChannel channel = messageCommand.getChannel();
    String searchQuery = messageCommand.getArg();
    String searchURL = String.format("https://shadowverse-portal.com/cards?card_name=%s&lang=en",searchQuery.replace(' ', '+'));
    try {
    	Document doc = Jsoup.connect(searchURL).get();
    	Element elem = doc.getElementById("displayDetail");
        String rawID = elem.select("a").attr("href");

        StringBuilder message = new StringBuilder();
        if(rawID == "") {
          message.append("<:thingken:256411188936704000> No results found.");
        } else {
          String cardID = rawID.substring(6);
          message.append(String.format("https://shadowverse-portal.com/image/card/en/C_%s.png",cardID)+"\n") //unevolved
                .append(String.format("https://shadowverse-portal.com/image/card/en/E_%s.png",cardID)+"\n") //evolved
                .append("**U:** "+elem.getElementsByClass("el-card-detail-skill-description").first().text()+"\n")
                .append("**E:** "+elem.getElementsByClass("el-card-detail-skill-description").last().text());
        }
        channel.sendMessage(message.toString());
    } catch (IOException e) {
    	e.printStackTrace();
    }
  }
}
