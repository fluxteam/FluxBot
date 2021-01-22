package me.fluxteam.fluxbot.objs;

import me.fluxteam.fluxbot.PublicVars;
import net.dv8tion.jda.api.entities.Role;


public enum FluxBot {

    WORDBOT, KOIOSBOT;

    public Role getRole(Language userLang){

        if(this == WORDBOT){
            if(userLang == Language.ENGLISH) {
                return PublicVars.FLUXGUILD.getRoleById(PublicVars.ENWBROLEID);
            } else if(userLang == Language.TURKISH){
                return PublicVars.FLUXGUILD.getRoleById(PublicVars.TRWBROLEID);
            }
        }else if(this == KOIOSBOT){
            if(userLang == Language.ENGLISH) {
                return PublicVars.FLUXGUILD.getRoleById(PublicVars.ENKBROLEID);
            } else if(userLang == Language.TURKISH){
                return PublicVars.FLUXGUILD.getRoleById(PublicVars.TRKBROLEID);
            }
        }

        return null;
    }

}
