package me.fluxteam.fluxbot.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;

public abstract class FluxCommand {

    MessageChannel channel;
    Message message;
    String[] args;

    public FluxCommand(MessageChannel channel, Message commandMessage){
        this.channel = channel;
        this.message = commandMessage;
        dispatch();
    }

    public FluxCommand(MessageChannel channel, Message commandMessage, String[] args){
        this.channel = channel;
        this.message = commandMessage;
        this.args = args;
        dispatch();
    }

    abstract void dispatch();

}
