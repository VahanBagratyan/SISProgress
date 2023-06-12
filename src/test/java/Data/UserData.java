package Data;

import java.util.HashMap;

public class UserData {
    private static String tempTaskName;
    private String validMail = "usermobile@test.com";
    private String validFullName = "Mobile User Test";
    private String validPassword = "Test1234*";
    private String invalidFormatMail = "wrongMailFormat123.*";
    private String invalidEmail = "invalidUser@example.com";
    private String invalidPassword = "invalidPassword";
    private String fullName = "Matnaqash Gev";
    private String mail = "gevorik@matnaqash.zuzu";
    private String password = "Test123*";
    private String number = "0987654321";
    private static String tempMail = "a" + (int) (Math.random()*10590)+"a"+
            (int)(Math.random()*10700) +"@internetkeno.com";
    private String birthDate = "1999-03-22T05:06:07+01:00";
    private HashMap<String, String> paramToDelete;

    public String getBirthDate(){
        return this.birthDate;
    }
    public String getFullName(){return this.fullName;}
    public String  getMail(){return this.mail;}
    public String getPassword(){return this.password;}
    public String getNumber(){return this.number;}
    public String getValidMail(){return this.validMail;}
    public String getValidPassword(){return this.validPassword;}
    public String getValidFullName(){return this.validFullName;}
    public String getInvalidFormatEmail(){return this.invalidFormatMail;}
    public String getInvalidEmail(){return this.invalidEmail;}
    public String getInvalidPassword(){return this.invalidPassword;}
    public String getTempMail(){
        return this.tempMail;
    }
    public void setTempMail(String text){
        this.tempMail = text;
    }
    public String getTempTaskName() {
        return this.tempTaskName;
    }
    public void setTempTaskName(String tempTaskName) {
        this.tempTaskName = tempTaskName;
    }
}
