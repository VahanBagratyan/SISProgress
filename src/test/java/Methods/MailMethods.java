package Methods;

import Data.UserData;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailMethods {
    public String getMessageTextBySubject(String subject, String json, String token) {
        Gson gson = new Gson();
        RequestMethods reqMeth = new RequestMethods();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonArray messages = jsonObject.getAsJsonArray("hydra:member");
        String intro = null;
        String text = null;
        for (int i = messages.size() - 1; i >= 0; i--) {
            JsonObject message = messages.get(i).getAsJsonObject();
            String messageSubject = message.get("subject").getAsString();
            if (subject.equals(messageSubject)) {
                String id = message.get("id").getAsString();
                System.out.println(id);
                String textResponse = reqMeth.getReqWithToken("https://api.mail.tm/messages/"+ id, token);
                JsonObject textResponseJson = gson.fromJson(textResponse, JsonObject.class);
                text = textResponseJson.get("text").getAsString();
                break;
            }
        }

        return text;
    }

    public String createAccountReturnToken(String tempMail){
        UserData userData = new UserData();
        RequestMethods reqMeth = new RequestMethods();
        reqMeth.postReq("https://api.mail.tm/accounts", "{\n" +
                "    \"address\" : \""+tempMail+"\",\n" +
                "    \"password\": \"password\"\n" +
                "}");
        String tokenJson = reqMeth.postReq("https://api.mail.tm/token", "{\n" +
                "    \"address\" : \""+tempMail+"\",\n" +
                "    \"password\": \"password\"\n" +
                "}");

        JsonObject jsonResponseToken = new Gson().fromJson(tokenJson, JsonObject.class);
        String token = jsonResponseToken.get("token").toString();
        token = token.replace("\"", "");
        System.out.println(token);
        return token;
    }

    public void verifyMailBySubject(String subject, String token){
        RequestMethods reqMeth = new RequestMethods();
        while (true){
            String messageFull = reqMeth.getReqWithToken("https://api.mail.tm/messages", token);
            String intro = getMessageTextBySubject(subject, messageFull, token);
            if(intro==null){
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else {
                String regex = "token=[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(intro);
                System.out.println(intro);
                if (matcher.find()) {
                    String userToken = matcher.group();
                    System.out.println("https://sisprogress.online/verify?" + userToken);
                    reqMeth.getReq("https://sisprogress.online/verify?" + userToken);
                }
                return;
            }
        }
    }
}
