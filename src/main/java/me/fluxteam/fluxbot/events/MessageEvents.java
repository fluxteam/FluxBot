package me.fluxteam.fluxbot.events;

import me.fluxteam.fluxbot.commands.Idea;
import me.fluxteam.fluxbot.commands.Ping;
import me.fluxteam.fluxbot.commands.RoleHere;
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

            if (args.size() != 2 || !GeneralUtilities.isInteger(args.get(1)))
                channel.sendMessage("Doğru kullanım: fl!clear <silmek istediğiniz mesaj sayısı>").queue();
            else
                channel.getHistory().retrievePast(Integer.parseInt(args.get(1)) + 1).complete().forEach(message -> message.delete().queue());

        }else if(args.get(0).equals("fl!idea")){
            new Idea(event, args);
        }
    }

    @Override
    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event) {

        // BOT CHECK ++
        if(event.getUser().isBot())
            return;
        // BOT CHECK --

        Member m = event.getMember();

        if(event.getReaction().getReactionEmote().isEmote()
                && GeneralUtilities.getBotEmotes(event.getGuild())
                .contains(event.getReactionEmote().getEmote())){

            Role r = GeneralUtilities.getEmoteRolesByID(event.getGuild())
                    .get(event.getReactionEmote().getEmote().getId());

            if(!m.getRoles().contains(r))
                event.getGuild().addRoleToMember(m, r).queue();
            else
                event.getGuild().removeRoleFromMember(m, r).queue();

        }
        //TODO CHECK THE MSG ID FROM FIREBASE
        else if(event.getReaction().getReactionEmote().isEmoji()
                && GeneralUtilities.getLangEmotes()
                .contains(event.getReactionEmote().getEmoji())){

            Role r = GeneralUtilities.getLangRolesByID(event.getGuild())
                    .get(event.getReactionEmote().getEmoji());

            if(!m.getRoles().contains(r))
                event.getGuild().addRoleToMember(m, r).queue();
            else
                event.getGuild().removeRoleFromMember(m, r).queue();

        }

    }

    @Override
    public void onMessageReactionRemove(@Nonnull MessageReactionRemoveEvent event) {

        // BOT CHECK ++
        if(event.getUser().isBot())
            return;
        // BOT CHECK --

        Member m = event.getMember();

        if(event.getReaction().getReactionEmote().isEmote()
                && GeneralUtilities.getBotEmotes(event.getGuild())
                .contains(event.getReactionEmote().getEmote())){

            Role r = GeneralUtilities.getEmoteRolesByID(event.getGuild())
                    .get(event.getReactionEmote().getEmote().getId());

            if(!m.getRoles().contains(r))
                event.getGuild().addRoleToMember(m, r).queue();
            else
                event.getGuild().removeRoleFromMember(m, r).queue();

        }

        else if(event.getReaction().getReactionEmote().isEmoji()
                && GeneralUtilities.getLangEmotes()
                .contains(event.getReactionEmote().getEmoji())){

            Role r = GeneralUtilities.getLangRolesByID(event.getGuild())
                    .get(event.getReactionEmote().getEmoji());

            if(!m.getRoles().contains(r))
                event.getGuild().addRoleToMember(m, r).queue();
            else
                event.getGuild().removeRoleFromMember(m, r).queue();

        }

    }

}

