package me.fluxteam.fluxbot.commands;

import me.fluxteam.fluxbot.PublicVars;
import me.fluxteam.fluxbot.objs.FluxMember;
import me.fluxteam.fluxbot.objs.Language;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class Admin extends FluxCommand{

    /*
    fl!admin reset 1234567894356
    fl!admin addlanguage 1243223543 turkish||english
     */


    public Admin(MessageReceivedEvent event, List<String> args, int permissionID) {
        super(event, args, permissionID);
    }

    @Override
    void dispatch() {

        if(args.size() == 3){
            if(args.get(1).equalsIgnoreCase("reset")){
                Member m = null;
                for(Member mm : PublicVars.FLUXGUILD.getMembers()){
                    if(mm.getId().equals(args.get(2))){
                        m = mm;
                    }
                }
                if(m == null) {
                    event.getChannel().sendMessage("Şu ID ile bir kullanıcı bulunamadı: " + args.get(2))
                            .queue(message -> clearUp(7, message));
                    return;
                }
                FluxMember fm = FluxMember.getFluxMember(args.get(2));
                fm.removeLanguages();
                fm.removeBots();
                fm.removeMemberRole();
                event.getChannel().sendMessage(fm.getMember().getEffectiveName() + " adlı kullanıcı sıfırlandı.").queue();
                return;
            }
            else {
                sendUsage();
            }
        }else if(args.size() == 4){
            if(args.get(1).equalsIgnoreCase("addlanguage")) {
                Member m = null;
                for(Member mm : PublicVars.FLUXGUILD.getMembers()){
                    if(mm.getId().equals(args.get(2))){
                        m = mm;
                    }
                }
                if(m == null) {
                    event.getChannel().sendMessage("Şu ID ile bir kullanıcı bulunamadı: " + args.get(2))
                            .queue(message -> clearUp(7, message));
                    return;
                }
                FluxMember fm = FluxMember.getFluxMember(args.get(2));
                if(args.get(3).equalsIgnoreCase("turkish")){
                    fm.addLanguage(Language.TURKISH);
                    event.getChannel().sendMessage(fm.getMember().getEffectiveName() + " adlı kullanıcıya \"Türkçe\" dili eklendi.").queue();
                }else if(args.get(3).equalsIgnoreCase("english")){
                    fm.addLanguage(Language.ENGLISH);
                    event.getChannel().sendMessage(fm.getMember().getEffectiveName() + " adlı kullanıcıya \"İngilizce\" dili eklendi.").queue();
                }else {
                    event.getChannel().sendMessage("Şu dil bulunamadı: " + args.get(3) + ". Kullanabileceğiniz diller: Turkish, English")
                            .queue(message -> clearUp(7, message));
                    return;
                }
                fm.addMemberRole();
            }else {
                sendUsage();
            }
        }else {
            sendUsage();
        }
    }

    private void sendUsage(){
        event.getChannel().sendMessage("Doğru kullanım: fl!admin <reset>")
                .queue(message -> clearUp(5, message));
    }
}
