package ranbot.database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.sqlite.SQLiteConfig;

public class DatabaseManager {

  private static class InstanceHolder {
    private static final String RANBOT_DB_URL = "jdbc:sqlite:ranbotData.db";
    private static final DatabaseManager databaseManager = new DatabaseManager(RANBOT_DB_URL);
  }

  private final SQLiteConfig readConfig;
  private final SQLiteConfig writeConfig;
  private final String dbUrl;

  private DatabaseManager(String dbUrl) {
    this.dbUrl = dbUrl;

    readConfig = new SQLiteConfig();
    readConfig.setReadOnly(true);
    writeConfig = new SQLiteConfig();
    writeConfig.setReadOnly(false);
  }

  public static DatabaseManager getInstance() {
    return InstanceHolder.databaseManager;
  }

  public void initialize() {
    try {
      Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Failed to load sqlite driver.", e);
    }
    upgradeDatabase();
  }

  private void upgradeDatabase() {

    final int databaseVersion;
    try {
      File f = new File("ranbotData.db");
      if (!f.exists()) {
        databaseVersion = 0;
      } else {
        databaseVersion = getUserVersion();
      }
      System.out.println("Retrieved database version: " + databaseVersion);
    } catch (SQLException e) {
      throw new DatabaseUpgradeException("Failed to retrieve user version of the database.", e);
    }

    final TreeMap<Integer, List<UpgradeScript>> upgradeScripts;
    try {
      upgradeScripts = getUpgradeScripts();
    } catch (IOException e) {
      throw new DatabaseUpgradeException("Failed to retrieve upgrade scripts.", e);
    }

    for (Entry<Integer, List<UpgradeScript>> entry : upgradeScripts.entrySet()) {
      try (Connection conn = getWriteConnection(); Statement stmt = conn.createStatement()) {
        if (databaseVersion >= entry.getKey()) {
          continue;
        }

        try {
          for (UpgradeScript script : entry.getValue()) {
            System.out.println("Applying script: " + script.getName());
            stmt.executeUpdate(script.getSqlScript());
          }
          stmt.execute("PRAGMA user_version = " + entry.getKey());
          conn.commit();
        } catch (SQLException e) {
          conn.rollback();
          throw e;
        }
      } catch (SQLException e) {
        String message = String.format("Failed to execute upgrade script on version %s", entry.getKey());
        throw new DatabaseUpgradeException(message, e);
      }
    }
  }

  /**
   * Retrieves the user version of the database.
   * 
   * @return The database user version.
   * @throws SQLException
   *           If something went wrong while retrieving the user version.
   */
  private int getUserVersion() throws SQLException {
    try (Connection conn = getReadConnection(); Statement stmt = conn.createStatement()) {
      try (ResultSet rs = stmt.executeQuery("PRAGMA user_version")) {
        rs.next();
        return rs.getInt("user_version");
      }
    }
  }

  /**
   * Returns a TreeMap of all the upgrade scripts found on the source directory
   * {@code "upgrade-scripts"}, where each upgrade scripts are mapped to their
   * corresponding db version.
   * 
   * @return A TreeMap of upgrade scripts.
   * @throws IOException
   *           If reading the upgrade script failed.
   */
  private TreeMap<Integer, List<UpgradeScript>> getUpgradeScripts() throws IOException {
    File upgradeScriptDirectory = new File("upgrade-scripts");
    File[] files = upgradeScriptDirectory.listFiles();

    TreeMap<Integer, List<UpgradeScript>> map = new TreeMap<>();
    for (File file : files) {
      UpgradeScript script = UpgradeScript.getUpgradeScipt(file);
      if (map.containsKey(script.getVersion())) {
        map.get(script.getVersion()).add(script);
      } else {
        List<UpgradeScript> list = new ArrayList<>();
        list.add(script);
        map.put(script.getVersion(), list);
      }
    }
    return map;
  }

  public Connection getReadConnection() throws SQLException {
    Connection c = DriverManager.getConnection(dbUrl, readConfig.toProperties());
    c.setReadOnly(true);
    return c;
  }

  public Connection getWriteConnection() throws SQLException {
    Connection c = DriverManager.getConnection(dbUrl, writeConfig.toProperties());
    c.setAutoCommit(false);
    return c;
  }
}
