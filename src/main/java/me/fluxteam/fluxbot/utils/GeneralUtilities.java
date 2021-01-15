package me.fluxteam.fluxbot.utils;


import me.fluxteam.fluxbot.PublicVars;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GeneralUtilities {

    public static boolean isInteger(String test){

        try {
            Integer.parseInt(test);
        }catch (NumberFormatException e) {
            return false;
        }

        return true;

    }

    public static List<Emote> getBotEmotes(Guild guild){

        /*
        WordBot
        KoiosBot
         */

        List<Emote> emotes = new ArrayList<>();
        emotes.add(guild.getEmotesByName("wordbot", true).get(0));
        emotes.add(guild.getEmotesByName("koiosbot", true).get(0));

        return emotes;

    }

    public static List<String> getLangEmotes(){

        /*
        Turkish "\uD83C\uDDF9\uD83C\uDDF7"
        English "\uD83C\uDDFA\uD83C\uDDF8"
         */

        List<String> rt = new ArrayList<>();
        rt.add("\uD83C\uDDF9\uD83C\uDDF7");
        rt.add("\uD83C\uDDFA\uD83C\uDDF8");

        return rt;

    }

    public static HashMap<String, Role> getEmoteRolesByID(Guild guild, boolean isTurkish){

        HashMap<String, Role> rt = new HashMap<>();
        if(isTurkish) {
            rt.put(PublicVars.WBEMOJIID, guild.getRoleById(PublicVars.TRWBROLEID));
            rt.put(PublicVars.KBEMOJIID, guild.getRoleById(PublicVars.TRKBROLEID));
        } else {
            rt.put(PublicVars.WBEMOJIID, guild.getRoleById(PublicVars.ENWBROLEID));
            rt.put(PublicVars.KBEMOJIID, guild.getRoleById(PublicVars.ENKBROLEID));
        }
        return rt;

    }

    public static HashMap<String, Role> getLangRolesByID(Guild guild){

        HashMap<String, Role> rt = new HashMap<>();

        rt.put("\uD83C\uDDF9\uD83C\uDDF7", guild.getRoleById(PublicVars.TURKISHROLEID));
        rt.put("\uD83C\uDDFA\uD83C\uDDF8", guild.getRoleById(PublicVars.ENGLISHROLEID));

        return rt;


    }

    public static EmbedBuilder getDefaultEmbedBuilder(){

        EmbedBuilder eb = new EmbedBuilder();
                eb.setColor(Color.PINK)
                .setFooter("FluxTeam * 2020")
                .setThumbnail("https://cdn.discordapp.com/attachments/788854303212175451/789200767864668220/flux.png");

        return eb;

    }




}
