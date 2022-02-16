package de.listener;

import java.util.ArrayList;
import java.util.List;

import de.classes.Prefix;
import net.dv8tion.jda.api.entities.Category;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DuoTalks extends ListenerAdapter {

	public ArrayList<VoiceChannel> vcList = new ArrayList<>();

	public DuoTalks() {
		this.vcList = new ArrayList<>();
		System.out.println(Prefix.prefix + "Duo Talks aktiviert");
	}

	@Override
	public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
		onJoin((VoiceChannel) event.getChannelJoined(), event.getEntity());
	}

	@Override
	public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
		onLeave((VoiceChannel) event.getChannelLeft());
	}

	@Override
	public void onGuildVoiceMove(GuildVoiceMoveEvent event) {
		onLeave((VoiceChannel) event.getChannelLeft());
		onJoin((VoiceChannel) event.getChannelJoined(), event.getEntity());

	}

	public void onJoin(VoiceChannel joined, Member memb) {
		if (joined.getIdLong() == 942392525862371399l) {
			Category cat = joined.getParentCategory();
			VoiceChannel vc = cat.createVoiceChannel("Duo Talk | " + (this.vcList.size() + 1)).complete();
			vc.getManager().setUserLimit(joined.getUserLimit()).queue();
			vc.getGuild().moveVoiceMember(memb, vc).queue();

			vcList.add(vc);
		}
	}

	public void onLeave(VoiceChannel channel) {

		if (channel.getMembers().size() <= 0) {

			if(this.vcList.contains(channel)) {
				this.vcList.remove(channel);
				channel.delete().queue();

			}
			
			for(int i = 0; i < this.vcList.size(); i++){
				this.vcList.get(i).getManager().setName("Duo Talk | " + (i + 1)).queue();
			}

		}
	}
	

}