package me.fluxteam.fluxbot.utils;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ErrorUtilities {

    private static final String errorChannelID = "788397826274623488";

    public static void sendErrorMessage(Guild g, Member trigger, String em){

        g.getTextChannelById(errorChannelID).sendMessage("Bir hata oluştu. -> " + (new SimpleDateFormat("dd-MM-yyyy / HH:mm")).format(new Date())).queue();
        if(trigger != null){
            g.getTextChannelById(errorChannelID).sendMessage("Tetikleyen kişi -> " + trigger.getId() + ": " + trigger
                    .getNickname()).queue();
        }
        g.getTextChannelById(errorChannelID).sendMessage("```" + em + "```").queue();

    }

}
