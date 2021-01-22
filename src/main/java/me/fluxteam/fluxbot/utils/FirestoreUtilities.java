package me.fluxteam.fluxbot.utils;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import me.fluxteam.fluxbot.Bot;
import me.fluxteam.fluxbot.PublicVars;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirestoreUtilities {

    public static Firestore db;

    public static String cacheLANGUAGEROLEMSGID = "";
    public static String cacheBOTROLEMSGID = "";

    public static void init() throws IOException, ExecutionException, InterruptedException {

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(JSONUtilities.getFirebaseJSON()))
                .setDatabaseUrl("https://fluxbotbyft-default-rtdb.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);

        db = FirestoreClient.getFirestore();
        cacheLANGUAGEROLEMSGID = getFSLangRoleMessageID();
        cacheBOTROLEMSGID = getFSBotRoleMessageID();

    }

    public static void add(String collection, String document, String key, String value) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection(collection).document(document);
        Map<String, Object> data = new HashMap<>();
        data.put(key, value);
        ApiFuture<WriteResult> result = docRef.update(data);
        System.out.println("Update time : " + result.get().getUpdateTime());
    }

    public static QueryDocumentSnapshot getDocument(String collection, String documentName) throws ExecutionException, InterruptedException {

        ApiFuture<QuerySnapshot> query = db.collection(collection).get();
        QuerySnapshot querySnapshot = query.get();

        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

        for (QueryDocumentSnapshot document : documents) {
            /*System.out.println("User: " + document.getId());
            System.out.println("First: " + document.getString("first"));
            if (document.contains("middle")) {
                System.out.println("Middle: " + document.getString("middle"));
            }
            System.out.println("Last: " + document.getString("last"));
            System.out.println("Born: " + document.getLong("born"));*/

            if(document.getId().equals(documentName))
                return document;

        }

        return null;
    }

    public static void setLangRoleMessageID(String messageID) throws ExecutionException, InterruptedException {

        add("config", "flux", "langmsg", messageID);
        cacheLANGUAGEROLEMSGID = messageID;

    }

    public static void setBotRoleMessageID(String messageID) throws ExecutionException, InterruptedException {
        add("config", "flux", "botmsg", messageID);
        cacheBOTROLEMSGID = messageID;
    }

    private static String getFSLangRoleMessageID() throws ExecutionException, InterruptedException {
        QueryDocumentSnapshot doc = getDocument("config", "flux");

        return doc.getString("langmsg");

    }

    private static String getFSBotRoleMessageID() throws ExecutionException, InterruptedException {
        QueryDocumentSnapshot doc = getDocument("config", "flux");

        return doc.getString("botmsg");
    }

    public static String getLangRoleMessageID(){
        if(cacheLANGUAGEROLEMSGID == ""){
            try {
                cacheLANGUAGEROLEMSGID = getFSLangRoleMessageID();
            } catch (ExecutionException | InterruptedException e) {
                ErrorUtilities.sendErrorMessage(null, e);
            }
        }
        return cacheLANGUAGEROLEMSGID;
    }

    public static String getBotRoleMessageID(){
        if(cacheBOTROLEMSGID == ""){
            try {
                cacheBOTROLEMSGID = getFSBotRoleMessageID();
            } catch (ExecutionException | InterruptedException e) {
                ErrorUtilities.sendErrorMessage(null, e);
            }
        }
        return cacheBOTROLEMSGID;
    }

    public static int getLastIdeaID() throws ExecutionException, InterruptedException {
        return Integer.parseInt(getDocument("config", "flux").getString("lastideaid"));
    }

    public static void incLastIdeaID() throws ExecutionException, InterruptedException {

        add("config", "flux", "lastideaid", String.valueOf(getLastIdeaID()+1));
    }

    /*public static void addIdea(String idea, String messageID) throws ExecutionException, InterruptedException {

        incLastIdeaID();
        add("idea", String.valueOf(getLastIdeaID()), "idea", idea);
        add("idea", String.valueOf(getLastIdeaID()), "msgid", messageID);

    }*/

}
