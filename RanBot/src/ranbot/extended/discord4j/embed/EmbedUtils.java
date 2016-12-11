package ranbot.extended.discord4j.embed;

import java.util.EnumSet;

import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;

import sx.blah.discord.Discord4J;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.internal.DiscordClientImpl;
import sx.blah.discord.api.internal.DiscordEndpoints;
import sx.blah.discord.api.internal.DiscordUtils;
import sx.blah.discord.api.internal.json.responses.MessageResponse;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.LogMarkers;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class EmbedUtils {

  /**
   * Utility for sending an Embed on the specified {@code channel}.
   * 
   * @param channel
   *          The channel to send the embed to.
   * @param embed
   *          The embed to send.
   * @return The response.
   * @throws MissingPermissionsException
   * @throws RateLimitException
   * @throws DiscordException
   */
  public static IMessage sendEmbed(IChannel channel, Embed embed)
      throws MissingPermissionsException, RateLimitException, DiscordException {
    IDiscordClient client = channel.getClient();
    DiscordUtils.checkPermissions(client, channel, EnumSet.of(Permissions.SEND_MESSAGES));

    if (client.isReady()) {
      MessageResponse response = DiscordUtils.GSON.fromJson(((DiscordClientImpl) client).REQUESTS.POST.makeRequest(
          DiscordEndpoints.CHANNELS + channel.getID() + "/messages",
          new StringEntity(DiscordUtils.GSON.toJson(new ExtendedMessageRequest(embed)), "UTF-8"),
          new BasicNameValuePair("authorization", client.getToken()),
          new BasicNameValuePair("content-type", "application/json")), MessageResponse.class);

      if (response == null || response.id == null) // Message didn't send
        throw new DiscordException("Message was unable to be sent.");

      return DiscordUtils.getMessageFromJSON(client, channel, response);
    } else {
      Discord4J.LOGGER.error(LogMarkers.HANDLE, "Bot has not signed in yet!");
      return null;
    }

  }
}
