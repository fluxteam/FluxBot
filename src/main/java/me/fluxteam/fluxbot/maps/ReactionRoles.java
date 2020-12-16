package me.fluxteam.fluxbot.maps;

import me.fluxteam.fluxbot.Bot;
import me.fluxteam.fluxbot.PublicVars;
import net.dv8tion.jda.api.entities.Role;

import java.util.HashMap;

public class ReactionRoles {

    public static HashMap<String, Role> emoteToRole = new HashMap<>();


    public static void init(){

        emoteToRole.put("postcard", Bot.jda.getRoleById(PublicVars.POSTCARDROLEID));

    }
}
