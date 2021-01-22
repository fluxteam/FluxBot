package me.fluxteam.fluxbot.utils;

import me.fluxteam.fluxbot.PublicVars;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ErrorUtilities {

    private static final String errorChannelID = "788397826274623488";

    public static void sendErrorMessage(Member trigger, Exception e){
        Guild g = PublicVars.FLUXGUILD;

        g.getTextChannelById(errorChannelID).sendMessage("Bir hata oluştu. -> " + (new SimpleDateFormat("dd-MM-yyyy / HH:mm")).format(new Date())).queue();
        if(trigger != null){
            g.getTextChannelById(errorChannelID).sendMessage("Tetikleyen kişi -> " + trigger.getId() + ": " + trigger
                    .getNickname()).queue();
        }
        g.getTextChannelById(errorChannelID).sendMessage("```" + e.getMessage() + "```").queue();

    }

    public static void sendErrorMessage(Member trigger, String em){
        Guild g = PublicVars.FLUXGUILD;

        g.getTextChannelById(errorChannelID).sendMessage("Bir hata oluştu. -> " + (new SimpleDateFormat("dd-MM-yyyy / HH:mm")).format(new Date())).queue();
        if(trigger != null){
            g.getTextChannelById(errorChannelID).sendMessage("Tetikleyen kişi -> " + trigger.getId() + ": " + trigger
                    .getNickname()).queue();
        }
        g.getTextChannelById(errorChannelID).sendMessage("```" + em + "```").queue();

    }

}
