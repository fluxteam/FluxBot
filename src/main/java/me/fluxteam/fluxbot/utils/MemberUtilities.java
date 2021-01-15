package me.fluxteam.fluxbot.utils;

import me.fluxteam.fluxbot.PublicVars;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

public class MemberUtilities {

    public static void addLangRole(Guild g, Member m, int languageID){

        try {
            //1: Turkish
            //2: English
            if (languageID == 1) {
                g.addRoleToMember(m, g.getRoleById(PublicVars.TURKISHROLEID)).queue();
            } else if (languageID == 2) {
                g.addRoleToMember(m, g.getRoleById(PublicVars.ENGLISHROLEID)).queue();
            }
        }catch(Exception e) {
            ErrorUtilities.sendErrorMessage(g, m, e.getLocalizedMessage());
        }
    }

    public static void removeLangRole(Guild g, Member m, int languageID){

        try {
            //1: Turkish
            //2: English
            if (languageID == 1) {
                g.removeRoleFromMember(m, g.getRoleById(PublicVars.TURKISHROLEID)).queue();
            } else if (languageID == 2) {
                g.removeRoleFromMember(m, g.getRoleById(PublicVars.ENGLISHROLEID)).queue();
            }
        }catch(Exception e) {
            ErrorUtilities.sendErrorMessage(g, m, e.getLocalizedMessage());
        }
    }

    public static int getMembersLanguageID(Guild g, Member m){
        //0: No language
        //1: Turkish
        //2: English
        //3: Turkish + English
        if(m.getRoles().contains(g.getRoleById(PublicVars.TURKISHROLEID)) && m.getRoles().contains(g.getRoleById(PublicVars.ENGLISHROLEID)))
            return 3;
        if(m.getRoles().contains(g.getRoleById(PublicVars.TURKISHROLEID)))
            return 1;
        if(m.getRoles().contains(g.getRoleById(PublicVars.ENGLISHROLEID)))
            return 2;

        return 0;
    }

    public static void addBotRole(Guild g, Member m, int botID){

        try {
            //1: WordBot
            //2: KoiosBot
            if(getMembersLanguageID(g, m) == 1) {
                if (botID == 1) {
                    g.addRoleToMember(m, g.getRoleById(PublicVars.TRWBROLEID)).queue();
                } else if (botID == 2) {
                    g.addRoleToMember(m, g.getRoleById(PublicVars.TRKBROLEID)).queue();
                }
            }else if(getMembersLanguageID(g, m) == 2) {
                if (botID == 1) {
                    g.addRoleToMember(m, g.getRoleById(PublicVars.ENWBROLEID)).queue();
                } else if (botID == 2) {
                    g.addRoleToMember(m, g.getRoleById(PublicVars.ENKBROLEID)).queue();
                }
            }else if(getMembersLanguageID(g, m) == 3) {
                if (botID == 1) {
                    g.addRoleToMember(m, g.getRoleById(PublicVars.TRWBROLEID)).queue();
                    g.addRoleToMember(m, g.getRoleById(PublicVars.ENWBROLEID)).queue();
                } else if (botID == 2) {
                    g.addRoleToMember(m, g.getRoleById(PublicVars.TRKBROLEID)).queue();
                    g.addRoleToMember(m, g.getRoleById(PublicVars.ENKBROLEID)).queue();
                }
            }else {
                throw new Exception("Language ID hatalı!");
            }
        }catch(Exception e) {
            ErrorUtilities.sendErrorMessage(g, m, e.getLocalizedMessage());
        }
    }

    public static void removeBotRole(Guild g, Member m, int botID){

        try {
            //1: WordBot
            //2: KoiosBot
            if(getMembersLanguageID(g, m) == 1) {
                if (botID == 1) {
                    g.removeRoleFromMember(m, g.getRoleById(PublicVars.TRWBROLEID)).queue();
                } else if (botID == 2) {
                    g.removeRoleFromMember(m, g.getRoleById(PublicVars.TRKBROLEID)).queue();
                }
            }else if(getMembersLanguageID(g, m) == 2) {
                if (botID == 1) {
                    g.removeRoleFromMember(m, g.getRoleById(PublicVars.ENWBROLEID)).queue();
                } else if (botID == 2) {
                    g.removeRoleFromMember(m, g.getRoleById(PublicVars.ENKBROLEID)).queue();
                }
            }else if(getMembersLanguageID(g, m) == 3) {
                if (botID == 1) {
                    g.removeRoleFromMember(m, g.getRoleById(PublicVars.TRWBROLEID)).queue();
                    g.removeRoleFromMember(m, g.getRoleById(PublicVars.ENWBROLEID)).queue();
                } else if (botID == 2) {
                    g.removeRoleFromMember(m, g.getRoleById(PublicVars.TRKBROLEID)).queue();
                    g.removeRoleFromMember(m, g.getRoleById(PublicVars.ENKBROLEID)).queue();
                }
            }else {
                ErrorUtilities.sendErrorMessage(g, m, "Language ID Hatali!");
            }
        }catch(Exception e) {
            ErrorUtilities.sendErrorMessage(g, m, e.getLocalizedMessage());
        }
    }

    public static boolean hasBotAssigned(Guild g, Member m, int botID){

        try {
            if (botID == 1)
                return m.getRoles().contains(g.getRoleById(PublicVars.TRWBROLEID)) || m.getRoles().contains(g.getRoleById(PublicVars.ENWBROLEID));
            else if (botID == 2)
                return m.getRoles().contains(g.getRoleById(PublicVars.TRKBROLEID)) || m.getRoles().contains(g.getRoleById(PublicVars.ENKBROLEID));
            else
                ErrorUtilities.sendErrorMessage(g, m, "Bot ID Hatali!");
        } catch(Exception e){
            ErrorUtilities.sendErrorMessage(g, m, e.getLocalizedMessage());
        }

        return false;
    }

    public static boolean hasAnyLanguageAssigned(Guild g, Member m){
        return m.getRoles().contains(g.getRoleById(PublicVars.TURKISHROLEID))
                || m.getRoles().contains(g.getRoleById(PublicVars.ENGLISHROLEID));
    }

}
