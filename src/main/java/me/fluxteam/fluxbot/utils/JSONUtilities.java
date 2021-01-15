package me.fluxteam.fluxbot.utils;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.*;

public class JSONUtilities {

    public static InputStream getFirebaseJSON() throws IOException {
        JSONObject json = new JSONObject(System.getenv("FIRESTOREKEY"));
        return IOUtils.toInputStream(json.toString(), "UTF-8");//ByteArrayInputStream(json.toJSONString().getBytes(StandardCharsets.UTF_8));

    }

}
