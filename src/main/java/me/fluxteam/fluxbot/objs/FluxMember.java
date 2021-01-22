package me.fluxteam.fluxbot.objs;

import me.fluxteam.fluxbot.Bot;
import me.fluxteam.fluxbot.PublicVars;
import me.fluxteam.fluxbot.utils.ErrorUtilities;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FluxMember {

    String ID;
    Member member;
    User user;
    List<Language> lang;
    List<FluxBot> bot = new ArrayList<>();


    public FluxMember(String userID){

        try {
            this.ID = userID;
            this.user = Bot.jda.getUserById(userID);
            this.member = PublicVars.FLUXGUILD.getMember(user);
            initLanguage();
            initBots();
        }catch(NullPointerException e){
            ErrorUtilities.sendErrorMessage(null, e);
        }

    }

    private void initLanguage(){
        List<Language> set = new ArrayList<>();

        List<Role> roles = member.getRoles();
        boolean eng = roles.contains(Language.ENGLISH.getRole());
        boolean tur = roles.contains(Language.TURKISH.getRole());
        if(tur)
            set.add(Language.TURKISH);
        if(eng)
            set.add(Language.ENGLISH);

        lang = set;


    }

    public void setLanguage(Language... newLanguage){
        this.lang = Arrays.asList(newLanguage);
        for(Language l : newLanguage){
            PublicVars.FLUXGUILD.addRoleToMember(member, l.getRole()).queue();
        }
    }

    public void addLanguage(Language language){
        this.lang.add(language);
        PublicVars.FLUXGUILD.addRoleToMember(member, language.getRole()).queue();
    }

    public void removeLanguage(Language language){
        if(!this.lang.contains(language))
            return;

        PublicVars.FLUXGUILD.removeRoleFromMember(this.member, language.getRole()).queue();

        this.lang.remove(language);
    }

    public void removeLanguages(){
        for(Language l : this.lang){
            PublicVars.FLUXGUILD.removeRoleFromMember(this.member, l.getRole()).queue();
        }

        this.lang.clear();
    }

    public void languageReaction(Language lang){
        if(this.lang.size() != 0)
            return;
        setLanguage(lang);
        addMemberRole();
    }

    private void initBots(){
        List<FluxBot> set = new ArrayList<>();
        List<Role> roles = member.getRoles();
        boolean wb = roles.contains(FluxBot.WORDBOT.getRole(Language.ENGLISH)) || roles.contains(FluxBot.WORDBOT.getRole(Language.TURKISH));
        boolean kb = roles.contains(FluxBot.KOIOSBOT.getRole(Language.ENGLISH)) || roles.contains(FluxBot.KOIOSBOT.getRole(Language.TURKISH));
        if(wb)
            set.add(FluxBot.WORDBOT);
        if(kb)
            set.add(FluxBot.KOIOSBOT);
        this.bot = set;
    }

    public void addBot(FluxBot... newBot){
        for(FluxBot b : newBot){
            if(this.bot.contains(b))
                continue;
            this.bot.add(b);
            for(Language l : lang)
                PublicVars.FLUXGUILD.addRoleToMember(member, b.getRole(l)).queue();
        }
    }

    public void removeBot(FluxBot bot){
        if(!this.bot.contains(bot))
            return;
        for(Language l : lang){
            PublicVars.FLUXGUILD.removeRoleFromMember(member, bot.getRole(l)).queue();
        }
        this.bot.remove(bot);
    }

    public void botReaction(FluxBot bot){
        if(!this.bot.contains(bot))
            addBot(bot);
        else
            removeBot(bot);
    }

    public void removeBots(){
        for(FluxBot b : this.bot){
            for(Language l : lang){
                PublicVars.FLUXGUILD.removeRoleFromMember(member, b.getRole(l)).queue();
            }
        }
        this.bot.clear();
    }

    public void addMemberRole(){
        PublicVars.FLUXGUILD.addRoleToMember(member, PublicVars.FLUXGUILD.getRoleById(PublicVars.MEMBERROLEID)).queue();
    }

    public void removeMemberRole(){
        PublicVars.FLUXGUILD.removeRoleFromMember(member, PublicVars.FLUXGUILD.getRoleById(PublicVars.MEMBERROLEID)).queue();
    }

    public static FluxMember getFluxMember(String ID){
        return new FluxMember(ID);
    }

    public Member getMember(){ return member; }

}
