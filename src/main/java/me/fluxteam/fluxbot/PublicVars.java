package me.fluxteam.fluxbot;

import me.fluxteam.fluxbot.utils.ErrorUtilities;
import net.dv8tion.jda.api.entities.Guild;

public class PublicVars {

    public static String FLUXGUILDID = "788392350043996190";
    public static Guild FLUXGUILD;

    public static String WBEMOJIID = "788471599706931220";
    public static String KBEMOJIID = "795277951599509514";
    public static String TREMOJITEXT = "\uD83C\uDDF9\uD83C\uDDF7";
    public static String ENEMOJITEXT = "\uD83C\uDDFA\uD83C\uDDF8";

    public static String MEMBERROLEID = "788392432609656872";
    public static String ENWBROLEID = "789972260214538260";
    public static String TRWBROLEID = "795295727349268490";
    public static String ENKBROLEID = "795276308962607144";
    public static String TRKBROLEID = "795295585376272414";
    public static String TESTERROLEID = "798314806112813097";


    public static String TURKISHROLEID = "790536228902535208";
    public static String ENGLISHROLEID = "790536289057112084";

    public static void init(){
        try {
            FLUXGUILD = Bot.jda.getGuildById(FLUXGUILDID);
        }catch (NullPointerException e){
            e.printStackTrace();
            Bot.jda.shutdownNow();
        }
    }


}
