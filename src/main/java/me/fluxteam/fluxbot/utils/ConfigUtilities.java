package me.fluxteam.fluxbot.utils;

import com.esotericsoftware.yamlbeans.YamlConfig;
import com.esotericsoftware.yamlbeans.YamlWriter;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;

public class ConfigUtilities {

    static Yaml yaml;
    static InputStream iS = ConfigUtilities.class.getResourceAsStream("/config.yml");
    static HashMap<String, Object> confData = new HashMap<>();

    public static void init(){

        final DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        yaml = new Yaml(options);
        confData = yaml.load(iS);

    }

    public static HashMap<String, Object> getConfig(){

        return confData;
    }

    public static void editConfig(String key, String value) throws IOException {

        HashMap<String, Object> newData = getConfig();

        if(newData.containsKey(key))
            newData.replace(key, value);
        else
            newData.put(key, value);


        final FileWriter writer = new FileWriter("src/main/resources/config.yml");
        yaml.dump(newData, writer);

    }

    //------------------------------------------------------------------------------------

    public static String getRoleMessageID(){
        return String.valueOf(getConfig().get("rolemessageid"));
    }

    public static void setRoleMessageID(String newID) throws IOException {
        editConfig("rolemessageid", newID);
    }



}
