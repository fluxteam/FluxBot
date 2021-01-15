package me.fluxteam.fluxbot.events;

import me.fluxteam.fluxbot.PublicVars;
import me.fluxteam.fluxbot.commands.Ping;
import me.fluxteam.fluxbot.commands.RoleHere;
import me.fluxteam.fluxbot.utils.ErrorUtilities;
import me.fluxteam.fluxbot.utils.FirestoreUtilities;
import me.fluxteam.fluxbot.utils.GeneralUtilities;
import me.fluxteam.fluxbot.utils.MemberUtilities;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageEvents extends ListenerAdapter {

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        Message msg = event.getMessage();

        if (!msg.getContentRaw().startsWith("fl!"))
            return;

        MessageChannel channel = event.getChannel();

        List<String> args = new ArrayList<String>();
        args.addAll(Arrays.asList(msg.getContentRaw().split(" ")));

        if (args.get(0).equals("fl!ping")) {
            new Ping(event);
        } else if (args.get(0).equals("fl!rolehere")) {
            new RoleHere(event, args, 2);
        } else if (args.get(0).equals("fl!clear")) {

            if (args.size() != 2 || !GeneralUtilities.isInteger(args.get(1)))
                channel.sendMessage("Doğru kullanım: fl!clear <silmek istediğiniz mesaj sayısı>").queue();
            else
                channel.getHistory().retrievePast(Integer.parseInt(args.get(1)) + 1).complete().forEach(message -> message.delete().queue());

        }else if(args.get(0).equals("fl!idea")){
            //new Idea(event, args);
        }
    }

    @Override
    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event) {

        // BOT CHECK ++
        if(event.getUser().isBot())
            return;
        // BOT CHECK --

        Guild g = event.getGuild();
        Member m = event.getMember();

        try {
            if(FirestoreUtilities.getBotRoleMessageID().equals(event.getMessageId())){
                if(event.getReactionEmote().getEmote().getId().equals(PublicVars.WBEMOJIID)) {
                    if(!MemberUtilities.hasBotAssigned(g, m, 1))
                        MemberUtilities.addBotRole(event.getGuild(), m, 1);
                    else
                        MemberUtilities.removeBotRole(g, m, 1);
                }else if(event.getReactionEmote().getEmote().getId().equals(PublicVars.KBEMOJIID)) {
                    if(!MemberUtilities.hasBotAssigned(g, m, 2))
                        MemberUtilities.addBotRole(event.getGuild(), m, 2);
                    else
                        MemberUtilities.removeBotRole(g, m, 2);
                }else {
                    ErrorUtilities.sendErrorMessage(g, m, "Bilinmeyen bot!");;
                }
            }else if(FirestoreUtilities.getLangRoleMessageID().equals(event.getMessageId())) {
                if (MemberUtilities.hasAnyLanguageAssigned(event.getGuild(), m)){
                    return;
                }if(event.getReactionEmote().getEmoji().equals(PublicVars.TREMOJITEXT)) {
                    MemberUtilities.addLangRole(event.getGuild(), m, 1);
                }else if(event.getReactionEmote().getEmoji().equals(PublicVars.ENEMOJITEXT)) {
                    MemberUtilities.addLangRole(event.getGuild(), m, 2);
                }else {
                    ErrorUtilities.sendErrorMessage(g, m, "Bilinmeyen dil!");;
                }
            }else {
                return;
            }
        } catch(Exception e){
            ErrorUtilities.sendErrorMessage(event.getGuild(), m, e.getLocalizedMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void onMessageReactionRemove(@Nonnull MessageReactionRemoveEvent event) {

        // BOT CHECK ++
        if(event.getUser().isBot())
            return;
        // BOT CHECK --

        Guild g = event.getGuild();
        Member m = event.getMember();

        try {
            if(FirestoreUtilities.getBotRoleMessageID().equals(event.getMessageId())){
                if(event.getReactionEmote().getEmote().getId().equals(PublicVars.WBEMOJIID)) {
                    if(!MemberUtilities.hasBotAssigned(g, m, 1))
                        MemberUtilities.addBotRole(event.getGuild(), m, 1);
                    else
                        MemberUtilities.removeBotRole(g, m, 1);
                }else if(event.getReactionEmote().getEmote().getId().equals(PublicVars.KBEMOJIID)) {
                    if(!MemberUtilities.hasBotAssigned(g, m, 2))
                        MemberUtilities.addBotRole(event.getGuild(), m, 2);
                    else
                        MemberUtilities.removeBotRole(g, m, 2);
                }else {
                    throw new Exception("Bilinmeyen bot.");
                }
            }else if(FirestoreUtilities.getLangRoleMessageID().equals(event.getMessageId())){
                if(MemberUtilities.hasAnyLanguageAssigned(event.getGuild(), m))
                    return;
                if(event.getReactionEmote().getEmoji().equals(PublicVars.TREMOJITEXT)) {
                    MemberUtilities.addLangRole(event.getGuild(), m, 1);
                }else if(event.getReactionEmote().getEmoji().equals(PublicVars.ENEMOJITEXT)) {
                    MemberUtilities.addLangRole(event.getGuild(), m, 2);
                }else {
                    ErrorUtilities.sendErrorMessage(g, m, "Language hatali!");
                }
            }else {
                return;
            }
        } catch(Exception e){
            ErrorUtilities.sendErrorMessage(event.getGuild(), m, e.getLocalizedMessage());
        }


    }

}

