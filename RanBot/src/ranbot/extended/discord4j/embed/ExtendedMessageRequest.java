package ranbot.extended.discord4j.embed;

import sx.blah.discord.api.internal.json.requests.MessageRequest;

public class ExtendedMessageRequest extends MessageRequest {

  public Embed embed;

  public ExtendedMessageRequest(String content, String[] mentions, boolean tts) {
    super(content, mentions, tts);
  }

  public ExtendedMessageRequest(Embed embed) {
    super(null, null, false);
    this.embed = embed;
  }
}
