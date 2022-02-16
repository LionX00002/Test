package de.commands;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.classes.Prefix;
import de.commands.types.ServerCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class StatusCommand implements ServerCommand {
	
	public StatusCommand() {
		System.out.println(Prefix.prefix + "StatusCommand aktiviert");
	}

	@Override
	public void performCommand(Guild g, Member m, TextChannel channel, Message message) {

		if (m.hasPermission(channel, Permission.BAN_MEMBERS)) {
			EmbedBuilder builder = new EmbedBuilder();
			builder.setDescription("————— Status-Board —————\n" 
			+ "\n" + "**Funktionen:**\n" 
					+ "JoinMessage [aktiv]\n"
					+ "TempChannel [aktiv]\n" 
					+ "Commands [aktiv]\n" + "  \n" 
					+ "**Infos**\n"
					+ "Owner " + g.getOwner().getUser().getAsMention() + "\n"
					+ getOnlie(g) + " Member online\n" 
					+ g.getMemberCount() + " Member\n"
					+ "5 Jan. 19:13 erstellt!");

			builder.setColor(new Color(5, 211, 139));
			builder.setFooter("Gamestation Gaming for everyone");
			channel.sendMessageEmbeds(builder.build()).queue();

		}
	}

	public int getOnlie(Guild g) {
		List<Member> list = new ArrayList<>();
		list = g.getMembers();
		return list.size();
		
	}

}
