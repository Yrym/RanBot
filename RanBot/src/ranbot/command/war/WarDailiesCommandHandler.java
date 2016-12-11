package ranbot.command.war;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

import ranbot.command.CommandHandler;
import ranbot.command.MessageCommand;
import ranbot.command.war.provisions.DailyWarProvisionsManager;
import ranbot.command.war.provisions.WarProvisions;
import ranbot.database.DatabaseManager;
import ranbot.resources.Messages;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class WarDailiesCommandHandler implements CommandHandler {

  private final DailyWarProvisionsManager provisionsManager;

  public WarDailiesCommandHandler() {
    provisionsManager = new DailyWarProvisionsManager();
  }

  @Override
  public void processCommand(MessageCommand messageCommand)
      throws DiscordException, MissingPermissionsException, RateLimitException {
    try {
      IChannel channel = messageCommand.getChannel();
      IUser author = messageCommand.getAuthor();

      Commander commander = WarDBUtils.retrieveCommander(author.getID());
      if (commander == null) {
        channel.sendMessage(Messages.WAR_DAILIES_NON_PARTICIPANT.getMessage());
      } else if (provisionsManager.canReceiveProvisions(commander)) {
        WarProvisions provisions = provisionsManager.getProvisions(commander);
        addMoney(commander.getId(), provisions.getMoney());
        updateLastDailiesTime(commander.getId());
        channel.sendMessage(Messages.WAR_DAILIES_PARTICIPANT.getMessage(provisions));
      } else {
        String missingRequirements = provisionsManager.getMissingRequirements(commander);
        channel.sendMessage(Messages.WAR_PROVISIONS_MISSING_REQUIREMENTS.getMessage(author.getName(), missingRequirements));
      }
    } catch (SQLException e) {
      throw new DiscordException("Failed to retrieve data for dailies. Cause: " + e.getMessage());
    }
  }

  // TODO externalize queries
  private void addMoney(String commanderId, long additionalMoney) throws SQLException {
    try (Connection conn = DatabaseManager.getInstance().getWriteConnection()) {
      try {
        String retrieveMoneySql = "SELECT money FROM commanders WHERE id = ?";
        long money;
        try (PreparedStatement pstmt = conn.prepareStatement(retrieveMoneySql)) {
          pstmt.setString(1, commanderId);
          try (ResultSet rs = pstmt.executeQuery()) {
            rs.next();
            money = rs.getLong("money");
          }
        }

        String updateMoneySql = "UPDATE commanders SET money=? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(updateMoneySql)) {
          pstmt.setLong(1, money + additionalMoney);
          pstmt.setString(2, commanderId);
          pstmt.executeUpdate();
        }
        conn.commit();
      } catch (SQLException e) {
        conn.rollback();
        throw e;
      }
    }
  }

  // TODO externalize query
  private void updateLastDailiesTime(String commanderId) throws SQLException {
    try (Connection conn = DatabaseManager.getInstance().getWriteConnection()) {
      try {
        String sql = "UPDATE commanders SET last_dailies_time = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
          pstmt.setLong(1, Instant.now().getEpochSecond() * 1000);
          pstmt.setString(2, commanderId);
          pstmt.executeUpdate();
        }
        conn.commit();
      } catch (SQLException e) {
        conn.rollback();
        throw e;
      }
    }
  }
}
