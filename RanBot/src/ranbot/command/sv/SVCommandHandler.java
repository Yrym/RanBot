package ranbot.command.sv;

import ranbot.command.CommandHandler;
import ranbot.command.MessageCommand;
import ranbot.resources.Messages;

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

      String message = "";
      if(rawID == "") {
        message = Messages.SV_SEARCH_FAILED.getMessage();
      } else {
        String cardID = rawID.substring(6);
        String USkill = elem.getElementsByClass("el-card-detail-skill-description").first().text();
        String ESkill = elem.getElementsByClass("el-card-detail-skill-description").last().text();
        message = Messages.SV_SEARCH_RESULT.getMessage(cardID,USkill,ESkill);
      }
      channel.sendMessage(message.toString());
    } catch (IOException e) {
    	e.printStackTrace();
    }
  }
}
