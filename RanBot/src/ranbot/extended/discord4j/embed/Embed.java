package ranbot.extended.discord4j.embed;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import sx.blah.discord.handle.impl.obj.Embedded.EmbedProvider;

public class Embed {
  private String title;
  private String description;
  // private String type; // Unnecessary. Always rich according to discord api.
  private String url;
  private String timestamp;
  private int color;
  private EmbedFooter footer;
  private EmbedImage image;
  private EmbedThumbnail thumbnail;
  private EmbedVideo video;
  private EmbedProvider provider;
  private EmbedAuthor author;
  private List<EmbedField> fields;

  public Embed() {
    this.fields = new ArrayList<>();
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(timestamp);
  }

  public void setColor(int r, int g, int b) {
    color = Integer.parseInt(String.format("%02X%02X%02X", verifyColor(r), verifyColor(g), verifyColor(b)), 16);
  }

  private int verifyColor(int value) {
    if (value < 0 || value > 255) {
      throw new RuntimeException("Illegal color value");
    }
    return value;
  }

  public void setFooter(EmbedFooter footer) {
    this.footer = footer;
  }

  public void setImage(EmbedImage image) {
    this.image = image;
  }

  public void setThumbnail(EmbedThumbnail thumbnail) {
    this.thumbnail = thumbnail;
  }

  public void setVideo(EmbedVideo video) {
    this.video = video;
  }

  public void setProvider(EmbedProvider provider) {
    this.provider = provider;
  }

  public void setAuthor(EmbedAuthor author) {
    this.author = author;
  }

  public void addField(EmbedField field) {
    fields.add(field);
  }

}
