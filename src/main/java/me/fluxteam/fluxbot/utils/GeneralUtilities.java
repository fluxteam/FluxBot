package me.fluxteam.fluxbot.utils;

public class GeneralUtilities {

    public static boolean isInteger(String test){

        try {
            Integer.parseInt(test);
        }catch (NumberFormatException e) {
            return false;
        }

        return true;

    }

}
