package me.fluxteam.fluxbot.objs;

import me.fluxteam.fluxbot.PublicVars;
import net.dv8tion.jda.api.entities.Role;

public enum Language {

    ENGLISH, TURKISH;

    public Role getRole(){

        if(this == ENGLISH){
            return PublicVars.FLUXGUILD.getRoleById(PublicVars.ENGLISHROLEID);
        }else if(this == TURKISH){
            return PublicVars.FLUXGUILD.getRoleById(PublicVars.TURKISHROLEID);
        }

        return null;
    }

}
