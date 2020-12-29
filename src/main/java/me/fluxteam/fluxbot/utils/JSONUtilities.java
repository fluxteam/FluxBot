package me.fluxteam.fluxbot.utils;

import me.fluxteam.fluxbot.Private;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;

import java.io.*;

public class JSONUtilities {

    public static InputStream getFirebaseJSON() throws IOException {

        JSONObject json = new JSONObject();
        json.put("type","service_account");
        json.put("project_id", "fluxbotbyft");
        json.put("private_key_id", Private.private_key_id);
        json.put("private_key", Private.private_key);
        json.put("client_email", Private.client_email);
        json.put("client_id", Private.client_id);
        json.put("auth_uri", "https://accounts.google.com/o/oauth2/auth");
        json.put("token_uri", "https://oauth2.googleapis.com/token");
        json.put("auth_provider_x509_cert_url", "https://www.googleapis.com/oauth2/v1/certs");
        json.put("client_x509_cert_url", "https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-nex87%40fluxbotbyft.iam.gserviceaccount.com");

        return IOUtils.toInputStream(json.toJSONString(), "UTF-8");//ByteArrayInputStream(json.toJSONString().getBytes(StandardCharsets.UTF_8));

    }

}
