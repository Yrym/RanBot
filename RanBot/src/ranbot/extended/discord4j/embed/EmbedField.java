package ranbot.extended.discord4j.embed;

public class EmbedField {
  private final String name;
  private final Object value;
  private final boolean inline;

  public EmbedField(String name, Object value, boolean inline) {
    this.name = name;
    this.value = value.toString();
    this.inline = inline;
  }
}
