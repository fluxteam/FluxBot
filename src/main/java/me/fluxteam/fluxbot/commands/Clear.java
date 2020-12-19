package me.fluxteam.fluxbot.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class Clear extends FluxCommand{

    public Clear(MessageReceivedEvent event, List<String> args) {
        super(event, args);
    }

    @Override
    void dispatch() {
        event.getChannel().getHistory().retrievePast(Integer.parseInt(args.get(0))).complete().forEach(message1 -> message1.delete().queue());
    }
}
