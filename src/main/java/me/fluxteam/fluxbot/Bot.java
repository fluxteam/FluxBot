package me.fluxteam.fluxbot;


import me.fluxteam.fluxbot.events.MessageEvents;
import me.fluxteam.fluxbot.utils.ConfigUtilities;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Bot {

    //TODO YAML BEANS VERSION

    public static JDA jda;

    private Bot() throws LoginException{
        //EnumSet.allOf(GatewayIntent.class)
        jda = JDABuilder.createDefault(Private.token, GatewayIntent.GUILD_EMOJIS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGE_REACTIONS)
                .setActivity(Activity.watching("its development."))
                .addEventListeners(new MessageEvents())
                .build();

        init();

    }

    public static void init(){

        ConfigUtilities.init();

    }

    public static void main(String[] args)  {

        try {
            new Bot();
        } catch (LoginException e) {
            e.printStackTrace();
        }

    }

}
