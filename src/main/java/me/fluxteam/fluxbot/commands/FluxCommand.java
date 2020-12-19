package me.fluxteam.fluxbot.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class FluxCommand {

    int permissionID = 0;
    /*
    0: everyone
    2: admin
     */

    List<String> args;
    MessageReceivedEvent event;

    public FluxCommand(MessageReceivedEvent event){

        this.event = event;

        dispatch();
        clearUp(5, event.getMessage());

    }

    public FluxCommand(MessageReceivedEvent event, List<String> args){

        this.args = args;
        this.event = event;

        dispatch();
        clearUp(5, event.getMessage());

    }

    public FluxCommand(MessageReceivedEvent event, int permissionID){

        this.event = event;
        this.permissionID = permissionID;

        if (!checkPermission()) {
            clearUp(5, event.getMessage());
            event.getChannel().sendMessage("Yeterli yetkiniz yok!").queue(message1 -> clearUp(5, message1));
        }else{
            dispatch();
            clearUp(5, event.getMessage());
        }

    }

    public FluxCommand(MessageReceivedEvent event, List<String> args, int permissionID){

        this.args = args;
        this.event = event;
        this.permissionID = permissionID;

        if (!checkPermission()) {
            clearUp(5, event.getMessage());
            event.getChannel().sendMessage("Yeterli yetkiniz yok!").queue(message1 -> clearUp(5, message1));
        }else{
            dispatch();
            clearUp(5, event.getMessage());
        }

    }

    public FluxCommand(MessageReceivedEvent event, List<String> args, int permissionID, boolean cleanUp){

        this.args = args;
        this.event = event;
        this.permissionID = permissionID;

        if (!checkPermission()) {
            clearUp(5, event.getMessage());
            event.getChannel().sendMessage("Yeterli yetkiniz yok!").queue(message1 -> clearUp(5, message1));
        }else if(cleanUp){
            dispatch();
            clearUp(5, event.getMessage());
        }else {
            dispatch();
        }

    }

    public boolean checkPermission(){
        if(permissionID != 0)
            if(permissionID == 2 && event.getMember().hasPermission(Permission.ADMINISTRATOR))
                return true;
            else if(permissionID == 3 && event.getMember().getId()
                    .equalsIgnoreCase("129630025254174720"))
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
