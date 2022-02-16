package de.classes;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Channel;
import net.dv8tion.jda.api.entities.Invite.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.LoginException;

import de.listener.JoinListener;
import de.listener.Talks;
import de.listener.TrioTalks;
import de.commands.CommandManager;
import de.listener.CommandListener;
import de.listener.DuoTalks;

public class Main {

	public static Main INSTANCE;
	public ShardManager shardMan;
	public static JDA jda;
	private CommandManager cmdMan;
	public static void main(String[] args) {

		try {
			new Main();
		} catch (LoginException | IllegalArgumentException e) {
		}

	}

	public Main() throws LoginException, IllegalArgumentException {
		

		JDABuilder builder = JDABuilder.createDefault(Token.token);
        INSTANCE = this;
		builder.setActivity(Activity.playing("auf Gamestation"));
		builder.setMemberCachePolicy(MemberCachePolicy.ALL);
		builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES);
		builder.setStatus(OnlineStatus.ONLINE);		
		this.cmdMan = new CommandManager();
		


		// -------Listener-------
		builder.addEventListeners(new DuoTalks());
		builder.addEventListeners(new TrioTalks());
		builder.addEventListeners(new Talks());
		builder.addEventListeners(new JoinListener());
		builder.addEventListeners(new CommandListener());
		// ----------------------

		jda = builder.build();
		System.out.println(Prefix.prefix + "Der Bot ist nun Online");
//		jda.getTextChannelById(940726089792245780l).sendMessage("!d bump").queueAfter(2, TimeUnit.HOURS);
		

		
		shutdown();

	}
    public void shutdown() {

        new Thread(() -> {

            String line = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                while ((line = reader.readLine()) != null) {

                    if (line.equalsIgnoreCase("!shutdown") && jda != null) {
                        for (int i = 3; i > 0; i--) {
                            if (i != 1)
                                System.out.println(Prefix.prefix + "Bot stops in " + i + " seconds.");
                            else if (i == 1)
                                System.out.println(Prefix.prefix + "Bot stops in " + i + " second.");
                            Thread.sleep(1000);
                        }
                        jda.getPresence().setStatus(OnlineStatus.OFFLINE);
                        jda.shutdown();
                        System.out.println(Prefix.prefix + "Bot offline.");
                        System.exit(0);
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    public CommandManager getcmdMan() {
		return cmdMan;
    	
    }
    
    
}
