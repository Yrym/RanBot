package ranbot.extended.discord4j.embed;

public class EmbedVideo {
  private final String url;
  private final int width;
  private final int height;

  public EmbedVideo(String url, int width, int height) {
    this.url = url;
    this.width = width;
    this.height = height;
  }
}
