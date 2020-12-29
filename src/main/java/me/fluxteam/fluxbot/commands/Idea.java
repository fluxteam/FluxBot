package me.fluxteam.fluxbot.commands;

import me.fluxteam.fluxbot.utils.FirestoreUtilities;
import me.fluxteam.fluxbot.utils.GeneralUtilities;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class Idea extends FluxCommand{

    public Idea(MessageReceivedEvent event, List<String> args) {
        super(event, args);
    }

    @Override
    void dispatch() {

        if(!args.get(1).equals("postcard")
                && !args.get(1).equals("general")
                && !args.get(1).equals("wordbot")){

            event.getChannel().sendMessage("Yanlış bir bot girdiniz. Doğru kullanım: fl!idea <general|postcard|wordbot> <idea>")
                    .queue(m -> clearUp(5, m));
            return;
        }

        args.remove(0);
        String bot = args.get(0);
        args.remove(0);
        StringBuilder sb = new StringBuilder();
        args.forEach(s -> {
            sb.append(s);
            sb.append(" ");
        });
        String idea = sb.toString();

        try {
            int ideaID = FirestoreUtilities.getLastIdeaID()+1;
            EmbedBuilder eb = GeneralUtilities.getDefaultEmbedBuilder();
            eb.addField("Fikir: #"+ideaID, idea, false)
                    .addField("Bot:", bot, false)
                    .addField("Gönderen:", event.getAuthor().getAsTag(), false)
                    .addField("Gönderilme Zamanı:", (new SimpleDateFormat("dd-MM-yyyy '/' HH:mm")).format(new Date(System.currentTimeMillis())), false);
            event.getChannel().sendMessage(eb.build()).queue();
            FirestoreUtilities.incLastIdeaID();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
