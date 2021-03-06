package de.commands;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.classes.Prefix;
import de.commands.types.ServerCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;

public class ClearCommand implements ServerCommand {
	
	public ClearCommand() {
		System.out.println(Prefix.prefix + "ClearCommand aktiviert");
	}

	@Override
	public void performCommand(Guild g, Member m, TextChannel channel, Message message) {
		
		if(m.hasPermission(channel, Permission.MESSAGE_MANAGE)) {
			String[] args = message.getContentDisplay().split(" ");
			
			if(args.length == 2) {
				
				try {
					int amount = Integer.parseInt(args[1]);
					channel.purgeMessages(get(channel, amount));
					channel.sendMessage(amount + " Nachrichten wurden entfernt").complete().delete().queueAfter(5, TimeUnit.SECONDS);
					return;
				} catch (NumberFormatException e) {
					e.printStackTrace();
					
				}
				
				
				
			}
		}
	
		
	}
	
	public List<Message> get(MessageChannel channel, int amout) {
		List<Message> messages = new ArrayList<>();
		int i = amout + 1;
		
		for(Message message : channel.getIterableHistory().cache(false)) {
			if(!message.isPinned()) {
				messages.add(message);
				
			}
			if(--i <= 0) break;
		}
		
		return messages;
		
	}

}
