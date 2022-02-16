package de.commands;


import java.awt.Color;

import de.classes.Prefix;
import de.commands.types.ServerCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class HelpCommand implements ServerCommand {
	
	public HelpCommand() {
		System.out.println(Prefix.prefix + "HelpCommand aktiviert");
	}

	@Override
	public void performCommand(Guild g, Member m, TextChannel channel, Message message) {
		
		if(m.hasPermission(channel, Permission.BAN_MEMBERS)) {
		EmbedBuilder builder = new EmbedBuilder();
		builder.setDescription("————— Info-Board —————\n"
				             + "\n"
				             + "**Commands:**\n"
				             + "!clear [number]  -->  Cleart die Anzahl der Messages!\n"
				             + "!status  -->  Zeigt euch alle Informationen über den Server!\n"
				             + "!help  -->  zeigt euch dieses Menu!");
		
		builder.setFooter("Gamestation Gaming for everyone");
		builder.setColor(new Color(255, 135, 0));
		channel.sendMessageEmbeds(builder.build()).queue();
		
		
	}
	}
	

}
