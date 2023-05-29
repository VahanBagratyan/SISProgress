package Methods;

import Data.UserData;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MailMethods {
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
    public void verifyBySubject(String subject){
        UserData userData = new UserData();
        RequestMethods reqMeth = new RequestMethods();
        String tempMail = userData.getTempMail();
        reqMeth.postReq("https://api.mail.tm/accounts", "{\n" +
                "    \"address\" : \""+tempMail+"@internetkeno.com\",\n" +
                "    \"password\": \"password\"\n" +
                "}");
        System.out.println(tempMail);
        String tokenJson = reqMeth.postReq("https://api.mail.tm/token", "{\n" +
                "    \"address\" : \""+tempMail+"@internetkeno.com\",\n" +
                "    \"password\": \"password\"\n" +
                "}");
        JsonObject jsonResponseToken = new Gson().fromJson(tokenJson, JsonObject.class);
        String token = jsonResponseToken.get("token").toString();
        token = token.replace("\"", "");
        while (true){
            String messageFull = reqMeth.getReqWithToken("https://api.mail.tm/messages", token);
            String intro = getMessageIntroBySubject(subject, messageFull);
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
