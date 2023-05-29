package Methods;

import Data.UserData;
import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.lang.String.valueOf;


public class RequestMethods {

    public void registerRequest(){
        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://sisprogress.online/register/ForTest"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"type\":\"mobile\"}"))
                .build();
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public  String postReq(String endpoint, String body){
        HttpClient httpClient = HttpClient.newBuilder().build();
        try {
        String response = httpClient.send(HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build(), HttpResponse.BodyHandlers.ofString()).body();

            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public  String postReqWithToken(String endpoint, String body, String token){
        HttpClient httpClient = HttpClient.newBuilder().build();
        try {
            String response = httpClient.send(HttpRequest.newBuilder()
                    .uri(URI.create(endpoint))
                    .header("Content-Type", "application/json")
                    .header("Authorization","Bearer "+ token)
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build(), HttpResponse.BodyHandlers.ofString()).body();
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public  String getReqWithToken(String endpoint, String token){
        HttpClient httpClient = HttpClient.newBuilder().build();
        try {
            String response = httpClient.send(HttpRequest.newBuilder()
                    .uri(URI.create(endpoint))
                    .header("Content-Type", "application/json")
                    .header("Authorization","Bearer "+token)
                    .GET()
                    .build(), HttpResponse.BodyHandlers.ofString()).body();
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void tempMeth(String json, String value){

    }
    public String getMessageIntroBySubject(String subject, String json) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonArray messages = jsonObject.getAsJsonArray("hydra:member");
        String intro = null;

        for (int i = messages.size() - 1; i >= 0; i--) {
            JsonObject message = messages.get(i).getAsJsonObject();
            String messageSubject = message.get("subject").getAsString();

            if (subject.equals(messageSubject)) {
                intro = message.get("intro").getAsString();
                break;
            }
        }

        return intro;
    }

    public void verifyBySubject(){
        UserData userData = new UserData();
        String tempMail = userData.getTempMail();
        postReq("https://api.mail.tm/accounts", "{\n" +
                "    \"address\" : \""+tempMail+"@internetkeno.com\",\n" +
                "    \"password\": \"password\"\n" +
                "}");
        System.out.println(tempMail);
        String tokenJson = postReq("https://api.mail.tm/token", "{\n" +
                "    \"address\" : \""+tempMail+"@internetkeno.com\",\n" +
                "    \"password\": \"password\"\n" +
                "}");
        JsonObject jsonResponseToken = new Gson().fromJson(tokenJson, JsonObject.class);
        String token = jsonResponseToken.get("token").toString();
        token = token.replace("\"", "");
        while (true){
            String messageFull = getReqWithToken("https://api.mail.tm/messages", token);
            String intro = getMessageIntroBySubject("dffcx", messageFull);
            System.out.println(intro);
            if(intro==null){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else {return;}

        }
    }
}
