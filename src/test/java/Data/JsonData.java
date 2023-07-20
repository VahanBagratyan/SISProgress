package Data;

public class JsonData {
    public String registerBody(String username, String mail, String password){
        return "{\n" +
                "    \"fullName\": \""+username+"\",\n" +
                "    \"phone\": \"+19999999999999\",\n" +
                "    \"age\": \"1923-10-04 00:00:00+00\",\n" +
                "    \"password\": \""+password+"\",\n" +
                "    \"email\": \""+mail+"\",\n" +
                "    \"country\": \"Afghanistan\",\n" +
                "    \"grade\": 9,\n" +
                "    \"recentSchool\": \"\",\n" +
                "    \"university\": \"Yale University\",\n" +
                "    \"academicProgramFirst\": \"African American Studies\",\n" +
                "    \"academicProgramSecond\": \"African Studies\",\n" +
                "    \"activityName\": []\n" +
                "}";
    }

    public String accountDeletion(String userToken){
        return "{\n" +
                "\"token\": \"" + userToken + "\"\n" +
                "}";
    }

}
