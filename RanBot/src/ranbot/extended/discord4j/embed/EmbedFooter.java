package ranbot.extended.discord4j.embed;

public class EmbedFooter {
  private final String text;
  private final String icon_url;
  private final String proxy_icon_url;

  public EmbedFooter(String text, String iconUrl, String proxyIconUrl) {
    this.text = text;
    this.icon_url = iconUrl;
    this.proxy_icon_url = proxyIconUrl;
  }
}
