package me.fluxteam.fluxbot.commands;

import me.fluxteam.fluxbot.utils.FirestoreUtilities;
import me.fluxteam.fluxbot.utils.GeneralUtilities;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RoleHere extends FluxCommand{

    public RoleHere(MessageReceivedEvent event, List<String> args, int permissionID) {
        super(event, args, permissionID);
    }

    @Override
    void dispatch () {

        EmbedBuilder eb = GeneralUtilities.getDefaultEmbedBuilder();
        MessageEmbed me;

        if(args.get(1).equalsIgnoreCase("bot")){

            me = eb.setTitle(" ")
                    .setAuthor("İlgilendiğiniz botun rolünü almak için, ilgili emojiye tıklayın.")
                    .addField("For the bot you want to track, add a reaction to the related emote.", "", false)
                    .addField("WordBot", "<:wordbot:788471599706931220>", true)
                    .addField("Postcard", "<:postcard:788428969862103042>", true)
                    .build();

            event.getChannel().sendMessage(me).queue(message -> {

                for(Emote e : GeneralUtilities.getBotEmotes(event.getGuild()))
                    message.addReaction(e).queue();

                try {
                    FirestoreUtilities.setBotRoleMessageID(message.getId());
                } catch (ExecutionException e) {
                    System.out.println("ID: 99 > RoleHere.java");
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    System.out.println("ID: 98 > RoleHere.java");
                    e.printStackTrace();
                }

            });

        }
        else if(args.get(1).equalsIgnoreCase("language")){
            me = eb.setTitle(" ")
                    .setAuthor("Tercih ettiğiniz dil için, ilgili emojiye tıklayın.")
                    .addField("For your preferred language, add a reaction to the related emote.", "", false)
                    .addField("Türkçe", "\uD83C\uDDF9\uD83C\uDDF7", true)
                    .addField("English", "\uD83C\uDDFA\uD83C\uDDF8", true)
                    .setColor(Color.PINK)
                    .setFooter("FluxTeam * 2020")
                    .setThumbnail("https://cdn.discordapp.com/attachments/788854303212175451/789200767864668220/flux.png")
                    .build();

            event.getChannel().sendMessage(me).queue(message -> {

                for(String id : GeneralUtilities.getLangEmotes())
                    message.addReaction(id).queue();
                try {
                    FirestoreUtilities.setLangRoleMessageID(message.getId());
                } catch (ExecutionException e) {
                    System.out.println("ID: 97 > RoleHere.java");
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    System.out.println("ID: 96 > RoleHere.java");
                    e.printStackTrace();
                }
            });

        }
        else {
            event.getChannel().sendMessage("Doğru kullanım: fl!rolehere <language|bot>")
                    .queue(message -> clearUp(5, message));
        }

    }


}
