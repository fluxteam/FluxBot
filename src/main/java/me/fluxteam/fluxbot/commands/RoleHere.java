package me.fluxteam.fluxbot.commands;

import me.fluxteam.fluxbot.utils.ConfigUtilities;
import me.fluxteam.fluxbot.utils.GeneralUtilities;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.IOException;

public class RoleHere extends FluxCommand{

    public RoleHere(MessageReceivedEvent event, int permissionID) {
        super(event, permissionID);
    }

    @Override
    void dispatch () {
        MessageEmbed eb = new EmbedBuilder()
                .setTitle(" ")
                .setAuthor("İlgilendiğiniz botun rolünü almak için, ilgili emojiye tıklayın.")
                .addField("For the bot you want to track, add a reaction to the related emote.", "", false)
                .addField("WordBot", "<:wordbot:788471599706931220>", true)
                .addField("Postcard", "<:postcard:788428969862103042>", true)
                .setColor(Color.PINK)
                .setFooter("FluxTeam * 2020")
                .setThumbnail("https://cdn.discordapp.com/attachments/788854303212175451/789200767864668220/flux.png")
                .build();

        event.getChannel().sendMessage(eb).queue(message -> {

            for(Emote e : GeneralUtilities.getEmotes(event.getGuild()))
                    message.addReaction(e).queue();

            try {
                ConfigUtilities.setRoleMessageID(message.getId());
            } catch (IOException e) {
                System.out.println("RoleHere.java > ID: 0");
                e.printStackTrace();
            }
        });

    }
}
