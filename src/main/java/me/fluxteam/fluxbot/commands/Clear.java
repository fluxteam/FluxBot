package me.fluxteam.fluxbot.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;

public class Clear extends FluxCommand{

    public Clear(MessageChannel channel, Message commandMessage, String[] args) {
        super(channel, commandMessage, args);
    }

    @Override
    void dispatch() {
        channel.getHistory().retrievePast(Integer.parseInt(args[0])).complete().forEach(message1 -> message1.delete().queue());
    }
}
