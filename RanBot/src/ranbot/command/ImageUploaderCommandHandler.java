package ranbot.command;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Random;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class ImageUploaderCommandHandler implements CommandHandler {

	final File file;
	final FilenameFilter filter;

	/**
	 * Instantiates an image uploader that chooses a random image from the file,
	 * given the FilenameFilter (if any).
	 * 
	 * @param file
	 *            The directory to upload a file from.
	 * @param filter
	 *            The filter to use.
	 */
	public ImageUploaderCommandHandler(File file, FilenameFilter filter) {
		this.file = requireNonNull(file);
		if (!file.isDirectory()) {
			throw new IllegalArgumentException("File must be a directory.");
		}

		this.filter = filter;
	}

	@Override
	public void processCommand(MessageCommand messageCommand)
			throws DiscordException, MissingPermissionsException, RateLimitException {

		IUser author = messageCommand.getAuthor();
		IChannel channel = messageCommand.getChannel();

		try {
			File chosen = null;
			if (file.isDirectory()) {
				file.listFiles(filter);
				File[] ranFiles = file.listFiles(filter);
				System.out.println("Found files: " + ranFiles.length);
				chosen = ranFiles[new Random().nextInt(ranFiles.length)];
			}
			if (chosen == null) {
				System.out.println("Nothing was chosen.");
				return;
			}

			if (author.getName().toLowerCase().contains("squish")) {
				channel.sendMessage("Please wait while I take a picture of myself. ;)");
				channel.sendFile(chosen);
			} else if (author.getName().toLowerCase().contains("yrym")) {
				channel.sendMessage("Let me show what we did last night Yrym ;)");
				channel.sendFile(chosen);
			} else {
				channel.sendMessage("Who do you think you are? Go back to #caps");
			}
			// message.getChannel().sendMessage("Actually, no time for
			// taking a picture ;) Yrym come here~");
			// message.getChannel().sendMessage("No time for looding, it's
			// time to sleep with Yrym now. :)");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
