package de.commands;

import java.util.concurrent.ConcurrentHashMap;

import de.classes.Prefix;
import de.commands.types.ServerCommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class CommandManager {
	
	public ConcurrentHashMap<String, ServerCommand> commands;
	
	public CommandManager() {
		System.out.println(Prefix.prefix + "CommandManager aktiviert");
		this.commands = new ConcurrentHashMap<>();
		
		this.commands.put("clear", new ClearCommand());
		this.commands.put("help", new HelpCommand());
		this.commands.put("status", new StatusCommand());
	}
	
	
	public boolean perform(String command, Guild g, Member m, TextChannel channel, Message message) {
		
		ServerCommand cmd;
		this.commands.get(command.toLowerCase());
		if((cmd = this.commands.get(command.toLowerCase())) != null) {
			cmd.performCommand(g, m, channel, message);
			return true;
		}
		
		
		
		
		return false;
	}

}
