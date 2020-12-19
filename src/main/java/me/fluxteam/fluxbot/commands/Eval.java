package me.fluxteam.fluxbot.commands;

import groovy.lang.GroovyShell;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class Eval extends FluxCommand{

    private final GroovyShell engine = new GroovyShell();
    private final String imports;

    public Eval(MessageReceivedEvent event, List<String> args, int permissionID) {
        super(event, args, permissionID);

        this.imports = "import java.io.*\n" +
                "import java.lang.*\n" +
                "import java.util.*\n" +
                "import java.util.concurrent.*\n" +
                "import net.dv8tion.jda.core.*\n" +
                "import net.dv8tion.jda.core.entities.*\n" +
                "import net.dv8tion.jda.core.entities.impl.*\n" +
                "import net.dv8tion.jda.core.managers.*\n" +
                "import net.dv8tion.jda.core.managers.impl.*\n" +
                "import net.dv8tion.jda.core.utils.*\n";

    }

    @Override
    void dispatch() {

        try {
            engine.setProperty("args", args);
            engine.setProperty("event", event);
            engine.setProperty("message", event.getMessage());
            engine.setProperty("channel", event.getChannel());
            engine.setProperty("jda", event.getJDA());
            engine.setProperty("guild", event.getGuild());
            engine.setProperty("member", event.getMember());

            String script = imports + event.getMessage().getContentRaw().split("```")[1];
            Object out = engine.evaluate(script);

            event.getChannel().sendMessage(out == null ? "Kod başarıyla çalıştırıldı." : out.toString()).queue();

        }catch(Exception e){
            event.getChannel().sendMessage("```Bir hata olustu\n" +
                    e.getMessage() + "```").queue();
        }


    }
}
