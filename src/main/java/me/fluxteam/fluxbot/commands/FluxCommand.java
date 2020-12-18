package me.fluxteam.fluxbot.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.concurrent.TimeUnit;

public abstract class FluxCommand {

    int permissionID = 0;
    /*
    0: everyone
    2: admin
     */

    MessageChannel channel;
    Message message;
    Member dispatcher;
    String[] args;

    public FluxCommand(MessageChannel channel, Message commandMessage, Member dispatcher){
        this.channel = channel;
        this.message = commandMessage;
        this.dispatcher = dispatcher;
        if (!checkPermission()) {
            clearUp(5, message);
            channel.sendMessage("Yeterli yetkiniz yok!").queue(message1 -> clearUp(5, message1));
        }else{
            dispatch();
            clearUp(5, message);
        }
    }

    public FluxCommand(MessageChannel channel, Message commandMessage, Member dispatcher, int permissionID){
        this.channel = channel;
        this.message = commandMessage;
        this.dispatcher = dispatcher;
        this.permissionID = permissionID;
        if (!checkPermission()) {
            clearUp(5, message);
            channel.sendMessage("Yeterli yetkiniz yok!").queue(message1 -> clearUp(5, message1));
        }else{
            dispatch();
            clearUp(5, message);
        }
    }

    public FluxCommand(MessageChannel channel, Message commandMessage, Member dispatcher, String[] args){
        this.channel = channel;
        this.message = commandMessage;
        this.dispatcher = dispatcher;
        if (!checkPermission()) {
            clearUp(5, message);
            channel.sendMessage("Yeterli yetkiniz yok!").queue(message1 -> clearUp(5, message1));
        }else{
            dispatch();
            clearUp(5, message);
        }
    }

    public FluxCommand(MessageChannel channel, Message commandMessage, Member dispatcher, int permissionID, String[] args) {
        this.channel = channel;
        this.message = commandMessage;
        this.dispatcher = dispatcher;
        this.permissionID = permissionID;
        this.args = args;

        if (!checkPermission()) {
            clearUp(5, message);
            channel.sendMessage("Yeterli yetkiniz yok!").queue(message1 -> clearUp(5, message1));
        }else{
            dispatch();
            clearUp(5, message);
        }
    }

    public boolean checkPermission(){
        if(permissionID != 0)
            if(permissionID == 2 && dispatcher.hasPermission(Permission.ADMINISTRATOR))
                return true;
            else
                return false;
        return true;
    }

    abstract void dispatch();

    public void clearUp(int sec, Message msg){
        msg.delete().queueAfter(sec, TimeUnit.SECONDS);
    }

}
