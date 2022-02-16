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

public class TrioTalks extends ListenerAdapter {

	public ArrayList<VoiceChannel> vcList1 = new ArrayList<>();

	public TrioTalks() {
		this.vcList1 = new ArrayList<>();
		System.out.println(Prefix.prefix + "10er Talks aktiviert");
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
		if (joined.getIdLong() == 939976005358063687l) {
			Category cat = joined.getParentCategory();
			VoiceChannel vc = cat.createVoiceChannel("Trio Talk | " + (this.vcList1.size() + 1)).complete();
			vc.getManager().setUserLimit(joined.getUserLimit()).queue();
			vc.getGuild().moveVoiceMember(memb, vc).queue();

			vcList1.add(vc);
		}
	}

	public void onLeave(VoiceChannel channel) {

		if (channel.getMembers().size() <= 0) {

			if(this.vcList1.contains(channel)) {
				this.vcList1.remove(channel);
				channel.delete().queue();

			}
			
			for(int i = 0; i < this.vcList1.size(); i++){
				this.vcList1.get(i).getManager().setName("Trio Talk | " + (i + 1)).queue();
			}

		}
	}
	

}