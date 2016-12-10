package ranbot.command.war;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ranbot.database.DatabaseManager;

public class WarDBUtils {

  /**
   * Retrieves the full data of the commander with the given {@code id} from the
   * database. If the commander does not exists, this returns {@code null}.
   * 
   * @param id
   *          The id of the commander.
   * @return The retrieved commander.
   * @throws SQLException
   *           If something went wrong while retrieving the commander.
   */
  public static Commander retrieveCommander(String id) throws SQLException {
    String sql = "SELECT * FROM commanders WHERE id = ?";
    try (Connection conn = DatabaseManager.getInstance().getReadConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, id);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        } else {
          long joinDate = rs.getLong("join_date");
          long money = rs.getLong("money");
          Commander commander = new Commander(id, joinDate);
          commander.setMoney(money);
          return commander;
        }
      }
    }
  }

  /**
   * Creates a commander with the default values.
   * 
   * @param id
   *          The id of the commander.
   * @throws SQLException
   *           If something went wrong while creating a default commander.
   */
  public static void insertDefaultCommander(String id) throws SQLException {
    String sql = "INSERT INTO commanders(id) VALUES(?)";
    try (Connection conn = DatabaseManager.getInstance().getWriteConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      try {
        pstmt.setString(1, id);
        pstmt.executeUpdate();
        conn.commit();
      } catch (SQLException e) {
        conn.rollback();
        throw e;
      }
    }
  }
}