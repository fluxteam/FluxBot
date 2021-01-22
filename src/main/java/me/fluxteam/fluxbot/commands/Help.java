package me.fluxteam.fluxbot.commands;

import me.fluxteam.fluxbot.utils.GeneralUtilities;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Help extends FluxCommand{

    public Help(MessageReceivedEvent event, int permissionID) {
        super(event, permissionID);
    }

    @Override
    void dispatch() {
        EmbedBuilder eb = GeneralUtilities.getDefaultEmbedBuilder();
        eb.setAuthor("Kullanılmaya o kadar alıştım ki, nasıl kullanacağınızı gösteriyorum;");
        eb.addField("fl!rolehere <bot | language>", "Yazıldığı kanala yeni rol mesajı atar. Aynı anda aynı türün sadece bir rol mesajı olabilir. Gerekli izin: Yönetici.", false);
        eb.addField("fl!admin <reset> <ID>", "Verdiğiniz ID'li kullanıcının bütün rolleri sıfırlanır. Gerekli izin: Yönetici.", false);
        eb.addField("fl!admin <addlanguage> <ID> <turkish | english>", "Verdiğiniz ID'li kullanıcıya belirttiğiniz dil rolü atanır. Gerekli izin: Yönetici.", false);
        event.getChannel().sendMessage(eb.build()).queue();
    }
}
