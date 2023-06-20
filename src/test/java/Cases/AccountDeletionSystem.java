package Cases;

import Base.AccountControl;
import Base.SetUp;
import Data.GeneralMessages;
import Data.LogInMessages;
import Data.MailSubjects;
import Data.UserData;
import Locators.*;
import Methods.*;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AccountDeletionSystem {
    private static AndroidDriver driver;
    private static String appPkg;
    private String tempMail;
    private String token;

    @BeforeSuite
    public void setUp() {
        SetUp setUp = new SetUp();
        driver = setUp.setUp();
        appPkg = driver.getCurrentPackage();
    }

    @BeforeMethod
    public void beforeEach() {
        AccountControl accountControl = new AccountControl(driver);
        MailMethods mailMeth = new MailMethods();
        UserData userData = new UserData();
        this.tempMail = userData.getTempMail();
        accountControl.registerTempAccountAndLogin(userData.getValidFullName(), this.tempMail, userData.getPassword());
        this.token = mailMeth.createAccountReturnToken(this.tempMail);
    }

    @AfterMethod
    public void deleteAccount() {
        AccountControl accControl = new AccountControl(driver);
        accControl.deleteAccountFromDB();
    }

    @Test
    public void accountDeletion() {
        GeneralMethods genMeth = new GeneralMethods(driver);
        RegistrationLocators regLoc = new RegistrationLocators();
        RegistrationMethods regMeth = new RegistrationMethods(driver);
        UserData userData = new UserData();
        MailMethods mailMeth = new MailMethods();
        MailSubjects mailSub = new  MailSubjects();
        LogInLocators loginLoc = new LogInLocators();
        AssertMethods assertMeth = new AssertMethods(driver);
        RequestMethods reqMeth = new RequestMethods();
        HomePageLocators homeLock = new HomePageLocators();
        ProfileLocators profileLoc = new ProfileLocators();
        GeneralMessages genMes = new GeneralMessages();
        LogInMessages logInMessages = new LogInMessages();
        ProfileMethods profileMeth = new ProfileMethods(driver);
        BottomMenuLocators menuLoc = new BottomMenuLocators();
        genMeth.click(menuLoc.profile);
        genMeth.scrollFromTo(700, 1500, 700, 700);
        genMeth.click(profileLoc.deleteAccount);
        genMeth.type(genMes.lorem, profileLoc.deleteReasonField);
        genMeth.click(profileLoc.nextButton);
        profileMeth.typeDeletionPassword(userData.getPassword());
        genMeth.click(profileLoc.nextButton);
        genMeth.click(profileLoc.cancelButton);
        String userToken = mailMeth.getTokenFromEmail(mailSub.deletion, this.token);
        System.out.println(userToken);
        String a = reqMeth.patchReq("https://sisprogress.online/verify/delete", "{\n" +
                "\"token\": \""+userToken+"\"\n" +
                "}");
        System.out.println(a);
        genMeth.click(loginLoc.logInButton);
        genMeth.type(this.tempMail, loginLoc.emailField);
        genMeth.type(userData.getPassword(), loginLoc.passwordField);
        genMeth.click(loginLoc.logInButton);
        assertMeth.assertThatElementExists(loginLoc.invalidMailOrPasswordError, logInMessages.invalidPasswordMessage);
    }
}
