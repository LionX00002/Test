package de.listener;

import java.awt.Color;

import de.classes.Prefix;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JoinListener extends ListenerAdapter {
	
	public JoinListener() {
		System.out.println(Prefix.prefix + "JoinMessage aktiviert");
	}
	

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Member member = event.getMember();
        TextChannel channel;
                
                
        if((channel = event.getGuild().getTextChannelById("930835219811008633")) != null) {
            channel.sendMessage("Hi " + member.getAsMention() + " viel Spaß auf dem Gamestation Discord!! Willkommen ❤️").queue();
    
        }
    }
}
