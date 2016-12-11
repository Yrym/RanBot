package ranbot.extended.discord4j.embed;

public class EmbedImage {
  private final String url;
  private final String proxy_url;
  private final int width;
  private final int height;

  public EmbedImage(String url, String proxyUrl, int width, int height) {
    this.url = url;
    this.proxy_url = proxyUrl;
    this.width = width;
    this.height = height;
  }
}
