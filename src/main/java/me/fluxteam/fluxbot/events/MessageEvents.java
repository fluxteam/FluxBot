package me.fluxteam.fluxbot.events;

import me.fluxteam.fluxbot.PublicVars;
import me.fluxteam.fluxbot.commands.Admin;
import me.fluxteam.fluxbot.commands.Help;
import me.fluxteam.fluxbot.commands.Ping;
import me.fluxteam.fluxbot.commands.RoleHere;
import me.fluxteam.fluxbot.objs.FluxBot;
import me.fluxteam.fluxbot.objs.FluxMember;
import me.fluxteam.fluxbot.objs.Language;
import me.fluxteam.fluxbot.utils.ErrorUtilities;
import me.fluxteam.fluxbot.utils.FirestoreUtilities;
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
            //new Clear(event, args);
        }else if(args.get(0).equals("fl!idea")){
            //new Idea(event, args);
        }else if(args.get(0).equals("fl!admin")){
            new Admin(event, args, 2);
        }else if(args.get(0).equals("fl!help")){
            new Help(event, 2);
        }
    }

    @Override
    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event) {

        // BOT CHECK ++
        if(event.getUser().isBot())
            return;
        // BOT CHECK --

        Member m = event.getMember();
        FluxMember fm = FluxMember.getFluxMember(m.getId());

        try {
            if(FirestoreUtilities.getBotRoleMessageID().equals(event.getMessageId())){
                if(event.getReactionEmote().getEmote().getId().equals(PublicVars.WBEMOJIID)) {
                    fm.botReaction(FluxBot.WORDBOT);
                }else if(event.getReactionEmote().getEmote().getId().equals(PublicVars.KBEMOJIID)) {
                    fm.botReaction(FluxBot.KOIOSBOT);
                }else {
                    ErrorUtilities.sendErrorMessage(m, "Bilinmeyen bot!");;
                }
            }else if(FirestoreUtilities.getLangRoleMessageID().equals(event.getMessageId())) {
                if(event.getReactionEmote().getEmoji().equals(PublicVars.TREMOJITEXT)) {
                    fm.languageReaction(Language.TURKISH);
                }else if(event.getReactionEmote().getEmoji().equals(PublicVars.ENEMOJITEXT)) {
                    fm.languageReaction(Language.ENGLISH);
                }else {
                    ErrorUtilities.sendErrorMessage(m, "Bilinmeyen dil!");;
                }
            }else {
                return;
            }
        } catch(Exception e){
            ErrorUtilities.sendErrorMessage(m, e);
            e.printStackTrace();
        }

    }

    @Override
    public void onMessageReactionRemove(@Nonnull MessageReactionRemoveEvent event) {

        // BOT CHECK ++
        if(event.getUser().isBot())
            return;
        // BOT CHECK --

        Member m = event.getMember();
        FluxMember fm = FluxMember.getFluxMember(m.getId());

        try {
            if(FirestoreUtilities.getBotRoleMessageID().equals(event.getMessageId())){
                if(event.getReactionEmote().getEmote().getId().equals(PublicVars.WBEMOJIID)) {
                    fm.botReaction(FluxBot.WORDBOT);
                }else if(event.getReactionEmote().getEmote().getId().equals(PublicVars.KBEMOJIID)) {
                    fm.botReaction(FluxBot.KOIOSBOT);
                }else {
                    ErrorUtilities.sendErrorMessage(m, "Bilinmeyen bot!");;
                }
            }else if(FirestoreUtilities.getLangRoleMessageID().equals(event.getMessageId())) {
                if(event.getReactionEmote().getEmoji().equals(PublicVars.TREMOJITEXT)) {
                    fm.languageReaction(Language.TURKISH);
                }else if(event.getReactionEmote().getEmoji().equals(PublicVars.ENEMOJITEXT)) {
                    fm.languageReaction(Language.ENGLISH);
                }else {
                    ErrorUtilities.sendErrorMessage(m, "Bilinmeyen dil!");;
                }
            }else {
                return;
            }
        } catch(Exception e){
            ErrorUtilities.sendErrorMessage(m, e);
            e.printStackTrace();
        }


    }

}

