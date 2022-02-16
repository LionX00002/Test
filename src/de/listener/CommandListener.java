package de.listener;

import java.util.concurrent.TimeUnit;

import de.classes.Main;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {

		String message = event.getMessage().getContentDisplay();

		if (event.isFromType(ChannelType.TEXT)) {
			TextChannel channel = event.getTextChannel();
			Guild guild = event.getGuild();

			if (message.startsWith("+")) {
				String[] args = message.substring(1).split(" ");

				if (!args[0].equalsIgnoreCase("!help") || !args[0].equalsIgnoreCase("!clear")) {
					if (!Main.INSTANCE.getcmdMan().perform(args[0], guild, event.getMember(), channel, event.getMessage())) {
						channel.sendMessage("``Unbekanntes Commando! Benutze !help``").complete().delete().queueAfter(3, TimeUnit.SECONDS);
					}

				}
			}

		}
	}

}
