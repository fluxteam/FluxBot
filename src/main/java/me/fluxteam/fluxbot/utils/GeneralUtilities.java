package me.fluxteam.fluxbot.utils;


import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;

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

    public static List<Emote> getEmotes(Guild guild){

        /*
        WordBot
        Postcard
        TO.d
         */

        List<Emote> emotes = new ArrayList<>();
        emotes.add(guild.getEmotesByName("wordbot", true).get(0));
        emotes.add(guild.getEmotesByName("postcard", true).get(0));

        return emotes;

    }

    public static HashMap<String, Role> getEmoteRolesByID(Guild guild){

        HashMap<String, Role> rt = new HashMap<>();
        rt.put("788471599706931220", guild.getRoleById("789972260214538260"));
        rt.put("788428969862103042", guild.getRoleById("788864238391263233"));

        return rt;


    }


}
