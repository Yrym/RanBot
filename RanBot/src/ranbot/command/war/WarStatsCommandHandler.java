package ranbot.command.war;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.StringJoiner;

import org.apache.commons.lang3.StringUtils;

import ranbot.command.CommandHandler;
import ranbot.command.MessageCommand;
import ranbot.extended.discord4j.embed.Embed;
import ranbot.extended.discord4j.embed.EmbedAuthor;
import ranbot.extended.discord4j.embed.EmbedField;
import ranbot.extended.discord4j.embed.EmbedFooter;
import ranbot.extended.discord4j.embed.EmbedThumbnail;
import ranbot.extended.discord4j.embed.EmbedUtils;
import ranbot.resources.Messages;
import ranbot.utils.TimeFormatUtils;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class WarStatsCommandHandler implements CommandHandler {

  @Override
  public void processCommand(MessageCommand messageCommand)
      throws DiscordException, MissingPermissionsException, RateLimitException {
    try {
      IChannel channel = messageCommand.getChannel();
      IUser author = messageCommand.getAuthor();
      IUser botClient = messageCommand.getClient().getOurUser();

      Embed embed = new Embed();
      embed.setAuthor(new EmbedAuthor(author.getName(), null, author.getAvatarURL(), null));
      embed.setThumbnail(new EmbedThumbnail(author.getAvatarURL(), null, 100, 100));
      embed.setFooter(new EmbedFooter("Powered by: " + botClient.getName(), botClient.getAvatarURL(), null));
      embed.setTimestamp(LocalDateTime.now(ZoneId.of("UTC")));
      embed.addField(new EmbedField("ID", author.getID(), true));
      embed.addField(new EmbedField("Nickname", author.getNicknameForGuild(channel.getGuild()).orElse("None"), true));
      embed.addField(new EmbedField("Account Creation Date",
          author.getCreationDate().format(TimeFormatUtils.UTC_FULL_DATE_FORMATTER), true));

      Commander commander = WarDBUtils.retrieveCommander(author.getID());
      if (commander == null) {
        embed.setDescription("Citizen");
        embed.setColor(50, 50, 50);
      } else {
        embed.setDescription("Commander");
        embed.setColor(255, 50, 50);
        embed.addField(new EmbedField("Participation Date",
            TimeFormatUtils.UTC_FULL_DATE_FORMATTER.format(commander.getJoinTime()), true));

        StatsDescription sd = new StatsDescription();
        sd.setRankTitle(commander.getRankTitle());
        sd.setExp(commander.getExp());
        sd.setMoney(commander.getMoney());
        embed.addField(new EmbedField("Stats", sd, false));
      }

      EmbedUtils.sendEmbed(channel, embed);
    } catch (SQLException e) {
      throw new DiscordException("Unable to retrieve user war stats. Cause: " + e.getMessage());
    }
  }

  private class StatsDescription {
    private String rankTitle;
    private long exp;
    private long money;

    private void setRankTitle(String rankTitle) {
      this.rankTitle = rankTitle;
    }

    private void setExp(long exp) {
      this.exp = exp;
    }

    private void setMoney(long money) {
      this.money = money;
    }

    @Override
    public String toString() {
      StringJoiner sj = new StringJoiner(StringUtils.LF);
      sj.add(Messages.WAR_STATS_RANK.getMessage(rankTitle));
      sj.add(StringUtils.EMPTY);
      sj.add(Messages.WAR_STATS_EXP.getMessage(exp));
      sj.add(Messages.WAR_STATS_MONEY.getMessage(money));
      return sj.toString();
    }
  }

}
