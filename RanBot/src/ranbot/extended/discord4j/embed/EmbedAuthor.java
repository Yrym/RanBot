package ranbot.extended.discord4j.embed;

public class EmbedAuthor {
  private final String name;
  private final String url;
  private final String icon_url;
  private final String proxy_icon_url;

  public EmbedAuthor(String name, String url, String iconUrl, String proxyIconUrl) {
    this.name = name;
    this.url = url;
    this.icon_url = iconUrl;
    this.proxy_icon_url = proxyIconUrl;
  }
}
