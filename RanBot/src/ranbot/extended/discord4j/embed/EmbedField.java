package ranbot.extended.discord4j.embed;

public class EmbedField {
  private final String name;
  private final String value;
  private final boolean inline;

  public EmbedField(String name, String value, boolean inline) {
    this.name = name;
    this.value = value;
    this.inline = inline;
  }
}
