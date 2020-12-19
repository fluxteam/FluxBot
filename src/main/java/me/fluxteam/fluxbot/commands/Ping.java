package me.fluxteam.fluxbot.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.concurrent.TimeUnit;

public class Ping extends FluxCommand {

    public Ping(MessageReceivedEvent event) {
        super(event);
    }

    @Override
    public void dispatch() {
        long time = System.currentTimeMillis();
        event.getChannel().sendMessage("Pong!") /* => RestAction<Message> */
                .queue(message -> {
                    message.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
                    clearUp(5, message);
                });

    }
}
