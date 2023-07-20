package Base;

import Data.*;
import Locators.*;
import Methods.*;
import io.appium.java_client.android.AndroidDriver;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class AccountControl {
    AndroidDriver driver;

    public AccountControl(AndroidDriver driver) {
        this.driver = driver;
    }

    public void logIn() {
        GeneralMethods genMeth = new GeneralMethods(driver);
        LogInLocators loginLoc = new LogInLocators();
        UserData userData = new UserData();
        AssertMethods assertMeth = new AssertMethods(driver);
        HomePageLocators homeLock = new HomePageLocators();
        RequestMethods reqMeth = new RequestMethods();
        LogInMessages logMes = new LogInMessages();
        //  reqMeth.postReq("https://sisprogress.online/register/ForTest", "{\"type\":\"mobile\"}");
        reqMeth.registerRequest();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        genMeth.click(loginLoc.logInButton);
        genMeth.type(userData.getValidMail(), loginLoc.emailField);
        genMeth.type(userData.getPassword(), loginLoc.passwordField);
        genMeth.click(loginLoc.logInButton);
        assertMeth.waitForElementAndAssertThatAttributeContains(userData.getValidFullName(), homeLock.welcomeText, "content-desc", 20, logMes.wrongUserName);
    }

    public void logIn(String password, String mail ) {
        GeneralMethods genMeth = new GeneralMethods(driver);
        LogInLocators loginLoc = new LogInLocators();
        UserData userData = new UserData();
        AssertMethods assertMeth = new AssertMethods(driver);
        HomePageLocators homeLock = new HomePageLocators();
        RequestMethods reqMeth = new RequestMethods();
        LogInMessages logMes = new LogInMessages();
        //  reqMeth.postReq("https://sisprogress.online/register/ForTest", "{\"type\":\"mobile\"}");
        reqMeth.registerRequest();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        genMeth.click(loginLoc.logInButton);
        genMeth.type(mail, loginLoc.emailField);
        genMeth.type(password, loginLoc.passwordField);
        genMeth.click(loginLoc.logInButton);
        assertMeth.waitForElementAndAssertThatAttributeContains(userData.getValidFullName(),
                homeLock.welcomeText,
                "content-desc",
                20,
                logMes.wrongUserName);
    }

    public void logOut() {
        GeneralMethods genMeth = new GeneralMethods(driver);
        BottomMenuLocators menuLoc = new BottomMenuLocators();
        ProfileLocators profileLoc = new ProfileLocators();
        genMeth.click(menuLoc.profile);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        genMeth.scrollToElementAndClick(profileLoc.logOut, 5);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        genMeth.click(profileLoc.logOutYes);
    }

    public void deleteAccountFromDB() {
        RequestMethods reqMeth = new RequestMethods();
        HashMap<String, String> params = new HashMap<>();
        UserData userData = new UserData();
        params.put("email", userData.getTempMail());
        params.put("password", userData.getPassword());
        params.put("text", "$2b$10$5yjnqNn/RxYamiu0ZhhZzuL9SztPRwSpq4tzpojToQl.WHRJvguf6");
        reqMeth.deleteReq("https://sisprogress.online/user/deleteForTesting", "", params);
    }
    public void registerTempAccountAndLogin(String username, String mail, String password){
        MailMethods mailMeth = new MailMethods();
        Endpoints endpoints = new Endpoints();
        JsonData jsonData = new JsonData();
        RequestMethods reqMeth = new RequestMethods();
        MailSubjects mailSub = new MailSubjects();
        GeneralMethods genMeth = new GeneralMethods(driver);
        RegistrationLocators regLoc = new RegistrationLocators();
        UtilsMethods utilMeth = new UtilsMethods(driver);
        LogInLocators loginLoc= new LogInLocators();
        AssertMethods assertMeth = new AssertMethods(driver);
        UserData userData = new UserData();
        HomePageLocators homeLock = new HomePageLocators();
        LogInMessages logMes = new LogInMessages();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        String token = mailMeth.createAccountReturnToken(mail);
        reqMeth.postReq(endpoints.register, jsonData.registerBody(username, mail, password));
        reqMeth.postReq("https://sisprogress.online/sendMail","{\n" +
                "  \"email\": \""+mail+"\"\n" +
                "}");
        mailMeth.verifyMailBySubject(mailSub.registration, token, "/verify");
        System.out.println("banananananna");

        genMeth.click(loginLoc.logInButton);
        genMeth.type(mail, loginLoc.emailField);
        genMeth.type(password, loginLoc.passwordField);
        genMeth.click(loginLoc.logInButton);
        genMeth.click(regLoc.termStart);
        genMeth.click(regLoc.admissionPlan);
        genMeth.click(regLoc.financeAID);
        genMeth.click(regLoc.legacy);
        genMeth.scrollFromTo(700, 1500, 700, 700);
        genMeth.type("asdfg", regLoc.experience);
        utilMeth.hideKeyboard();
        genMeth.click(regLoc.submit);
        assertMeth.waitForElementAndAssertThatAttributeContains(userData.getValidFullName(),
                homeLock.welcomeText,
                "content-desc",
                20,
                logMes.wrongUserName);
    }
}
