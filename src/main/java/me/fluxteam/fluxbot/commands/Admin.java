package me.fluxteam.fluxbot.commands;

import me.fluxteam.fluxbot.Bot;
import me.fluxteam.fluxbot.PublicVars;
import me.fluxteam.fluxbot.objs.FluxMember;
import me.fluxteam.fluxbot.objs.Language;
import me.fluxteam.fluxbot.utils.GeneralUtilities;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.*;
import java.util.concurrent.TimeUnit;

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

        if (args.size() == 3) {
            if (args.get(1).equalsIgnoreCase("reset")) {
                Member m = null;
                for (Member mm : PublicVars.FLUXGUILD.getMembers()) {
                    if (mm.getId().equals(args.get(2))) {
                        m = mm;
                    }
                }
                if (m == null) {
                    event.getChannel().sendMessage("Şu ID ile bir kullanıcı bulunamadı: " + args.get(2))
                            .queue(message -> clearUp(7, message));
                    return;
                }
                FluxMember fm = FluxMember.getFluxMember(args.get(2));
                fm.removeBots();
                fm.removeLanguages();
                fm.removeMemberRole();
                event.getChannel().sendMessage(fm.getMember().getEffectiveName() + " adlı kullanıcı sıfırlandı.").queue();
                return;
            } else {
                sendUsage();
            }
        } else if (args.size() == 4) {
            if (args.get(1).equalsIgnoreCase("addlanguage")) {
                Member m = null;
                for (Member mm : PublicVars.FLUXGUILD.getMembers()) {
                    if (mm.getId().equals(args.get(2))) {
                        m = mm;
                    }
                }
                if (m == null) {
                    event.getChannel().sendMessage("Şu ID ile bir kullanıcı bulunamadı: " + args.get(2))
                            .queue(message -> clearUp(7, message));
                    return;
                }
                FluxMember fm = FluxMember.getFluxMember(args.get(2));
                if (args.get(3).equalsIgnoreCase("turkish")) {
                    fm.addLanguage(Language.TURKISH);
                    event.getChannel().sendMessage(fm.getMember().getEffectiveName() + " adlı kullanıcıya \"Türkçe\" dili eklendi.").queue();
                } else if (args.get(3).equalsIgnoreCase("english")) {
                    fm.addLanguage(Language.ENGLISH);
                    event.getChannel().sendMessage(fm.getMember().getEffectiveName() + " adlı kullanıcıya \"İngilizce\" dili eklendi.").queue();
                } else {
                    event.getChannel().sendMessage("Şu dil bulunamadı: " + args.get(3) + ". Kullanabileceğiniz diller: Turkish, English")
                            .queue(message -> clearUp(7, message));
                    return;
                }
                fm.addMemberRole();
            } else {
                sendUsage();
            }
        }/*else if(args.size() == 2){

            if(args.get(1).equalsIgnoreCase("sendinvite")){
                List<String> IDs = new ArrayList<>();
                IDs.addAll(Arrays.asList("466288781918732308",
                    "602950100414890019",
                    "695256041369370634",
                    "339690807437295618",
                    "625755359117836299",
                    "518805124613799946",
                    "512983899677392926",
                    "354650451167150081",
                    "799351524459413535",
                    "750651489801994270",
                    "378239542567895041",
                    "769288354604384256",
                    "478134549650604053",
                    "520869418880729109",
                    "693451182617657394",
                    "246709176250662912",
                    "606069009913348096", //Yusuf
                    "129630025254174720" //Ben
                ));
                event.getChannel().sendMessage("```Davet Kodu | Kullanıcı ID | Kullanıcı Adı | Davet Gönderildi mi?```").queue();
                for(String id : IDs){
                    try {
                        PublicVars.FLUXGUILD.getTextChannelById("793557104870555709").createInvite().setMaxUses(1).setMaxAge(432000L + IDs.indexOf(id), TimeUnit.SECONDS)
                                .queue(inv -> {

                                    EmbedBuilder eb = GeneralUtilities.getDefaultEmbedBuilder();
                                    eb.setTitle("FluxTeam Discord sunucusu davetiniz;");
                                    eb.setDescription("Merhaba! Tester başvurunuzun onaylandığını görüyorum. Aşağıda, lab çalışmalarını yürüteceğimiz discord sunucusunun davet linki yer almakta. Lütfen oraya giriş yapın ve dil seçiminde Türkçe'yi seçin. Dil seçtikten sonra Tester rolünüz otomatik olarak verilecektir. \nHerhangi bir sorununuz olursa Teoman00#1445 ile iletişime geçebilirsiniz.");
                                    eb.addField("Davet linkiniz;", inv.getUrl(), false);
                                    eb.addField("Önemli;", "Davet linkiniz tek kullanımlık olduğundan, lütfen başka biri ile paylaşmayın.", false);
                                    eb.addField("", "Saygılarımızla;", false);
                                    Bot.jda.getUserById(id).openPrivateChannel().submit()
                                            .thenCompose(channel -> channel.sendMessage(eb.build()).submit())
                                            .whenComplete((msg, err) -> {
                                                if (err != null) {
                                                    event.getChannel().sendMessage("```"
                                                            + inv.getCode() + " | " + Bot.jda.getUserById(id).getAsTag() + " | " + id + " | HAYIR" + "```").queue();
                                                } else {
                                                    event.getChannel().sendMessage("```"
                                                            + inv.getCode() + " | " + Bot.jda.getUserById(id).getAsTag() + " | " + id + " | EVET" + "```").queue();
                                                }
                                            });
                                });
                    } catch(Exception e){
                        System.out.println("ID: " + id);
                        e.printStackTrace();
                    }
                }
            }

        }*/else {
            sendUsage();
        }
    }

    private void sendUsage(){
        event.getChannel().sendMessage("Doğru kullanım: fl!admin <reset>")
                .queue(message -> clearUp(5, message));
    }
}
