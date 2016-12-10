package ranbot.database;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class UpgradeScript implements Comparable<UpgradeScript> {
  private static final Matcher FILENAME_MATCHER = Pattern.compile("^[\\d+]+?_[a-zA-Z0-9_]+\\.sql$")
      .matcher(StringUtils.EMPTY);
  private static final Matcher VERSION_MATCHER = Pattern.compile("^[\\d+]+").matcher(StringUtils.EMPTY);

  private final int version;
  private final String name;
  private final String sqlScript;

  public UpgradeScript(int version, String name, String sqlScript) {
    this.version = version;
    this.name = name;
    this.sqlScript = sqlScript;
  }

  public int getVersion() {
    return version;
  }

  public String getName() {
    return name;
  }

  public String getSqlScript() {
    return sqlScript;
  }

  @Override
  public int compareTo(UpgradeScript that) {
    return Integer.compare(this.version, that.version);
  }

  static UpgradeScript getUpgradeScipt(File upgradeScriptFile) throws IOException {
    /*
     * Filename of the script must be in the following pattern:
     * <number>_<alphanumeric_>.sql e.g. "001_ran_yakumo.sql".
     */
    String fileName = upgradeScriptFile.getName();
    if (!FILENAME_MATCHER.reset(fileName).matches()) {
      String message = String.format("The filename %s does not match the expected pattern for upgrade scripts.",
          fileName);
      throw new InvalidUpgradeScriptException(message);
    }

    VERSION_MATCHER.reset(fileName).find();
    int version = Integer.parseInt(VERSION_MATCHER.group());
    System.out.println("Retrieved version: " + version);
    String sql = new String(Files.readAllBytes(upgradeScriptFile.toPath()));

    return new UpgradeScript(version, fileName, sql);
  }
}
