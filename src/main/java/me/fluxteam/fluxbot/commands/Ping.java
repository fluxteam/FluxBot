package me.fluxteam.fluxbot.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.util.concurrent.TimeUnit;

public class Ping extends FluxCommand {

    public Ping(MessageChannel channel, Message commandMessage) {
        super(channel, commandMessage);
    }

    @Override
    public void dispatch() {
        message.delete().queueAfter(3, TimeUnit.SECONDS);
        long time = System.currentTimeMillis();
        channel.sendMessage("Pong!") /* => RestAction<Message> */
                .queue(response /* => Message */ -> {
                    response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
                });

    }
}
