package me.fluxteam.fluxbot.events;

import me.fluxteam.fluxbot.PublicVars;
import me.fluxteam.fluxbot.commands.Ping;
import me.fluxteam.fluxbot.commands.RoleHere;
import me.fluxteam.fluxbot.utils.ConfigUtilities;
import me.fluxteam.fluxbot.utils.GeneralUtilities;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class MessageEvents extends ListenerAdapter {

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        Message msg = event.getMessage();

        if(!msg.getContentRaw().startsWith("fl!"))
            return;

        MessageChannel channel = event.getChannel();

        List<String> args = new ArrayList<String>();
        args.addAll(Arrays.asList(msg.getContentRaw().split(" ")));

        if (args.get(0).equals("fl!ping")) {
            new Ping(channel, msg);
        }else if(args.get(0).equals("fl!rolehere")) {
            new RoleHere(channel, msg);
        }else if(args.get(0).equals("fl!clear")){
            if(args.size() != 2 || !GeneralUtilities.isInteger(args.get(1)))
                channel.sendMessage("Doğru kullanım: fl!clear <silmek istediğiniz mesaj sayısı>").queue();
            else
                channel.getHistory().retrievePast(Integer.parseInt(args.get(1))+1).complete().forEach(message -> message.delete().queue());

        }
    }

    @Override
    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event) {

        if(event.getUser().isBot())
            return;

        if(event.getMessageId().equals(ConfigUtilities.getRoleMessageID())){
            Member m = event.getMember();

            if(!m.getRoles().contains(event.getGuild().getRoleById(PublicVars.POSTCARDROLEID))){ //TODO DUMP TO CONF
                event.getGuild().addRoleToMember(m, event.getGuild().getRoleById(PublicVars.POSTCARDROLEID)).queue();
            }else {
                event.getGuild().removeRoleFromMember(m, event.getGuild().getRoleById(PublicVars.POSTCARDROLEID)).queue();
            }
        }

    }

    @Override
    public void onMessageReactionRemove(@Nonnull MessageReactionRemoveEvent event) {

        if(Objects.requireNonNull(event.getUser()).isBot())
            return;

        if(event.getMessageId().equals(ConfigUtilities.getRoleMessageID())){
            Member m = event.getMember();

            if(!m.getRoles().contains(event.getGuild().getRoleById(PublicVars.POSTCARDROLEID))){ //TODO DUMP TO CONF
                event.getGuild().addRoleToMember(m, event.getGuild().getRoleById(PublicVars.POSTCARDROLEID)).queue();
            }else {
                event.getGuild().removeRoleFromMember(m, event.getGuild().getRoleById(PublicVars.POSTCARDROLEID)).queue();
            }
        }

    }
}
