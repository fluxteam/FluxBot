package me.fluxteam.fluxbot;


import me.fluxteam.fluxbot.events.MessageEvents;
import me.fluxteam.fluxbot.utils.ErrorUtilities;
import me.fluxteam.fluxbot.utils.FirestoreUtilities;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Bot {

    //TODO YAML BEANS VERSION

    public static JDA jda;

    private Bot() throws LoginException{

        jda = JDABuilder.create(System.getenv("FLBOTTOKEN"), //TODO  Private.token / System.getenv("TOKEN")
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_BANS,
                GatewayIntent.GUILD_EMOJIS,
                GatewayIntent.GUILD_WEBHOOKS,
                GatewayIntent.GUILD_INVITES,
                GatewayIntent.GUILD_VOICE_STATES,
                GatewayIntent.GUILD_PRESENCES,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                GatewayIntent.GUILD_MESSAGE_TYPING,
                GatewayIntent.DIRECT_MESSAGES,
                GatewayIntent.DIRECT_MESSAGE_REACTIONS,
                GatewayIntent.DIRECT_MESSAGE_TYPING)
                .setActivity(Activity.watching("its development."))
                .addEventListeners(new MessageEvents())
                .build();

        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public static void init() throws IOException, ExecutionException, InterruptedException {

        FirestoreUtilities.init();
        PublicVars.init();

    }

    public static void main(String[] args)  {

        try {
            new Bot();
        } catch (LoginException e) {
            e.printStackTrace();
        }

    }

}
