package Cases;

import Base.AccountControl;
import Base.SetUp;
import Data.*;
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
        UserData userData = new UserData();
        MailMethods mailMeth = new MailMethods();
        MailSubjects mailSub = new MailSubjects();
        LogInLocators loginLoc = new LogInLocators();
        AssertMethods assertMeth = new AssertMethods(driver);
        RequestMethods reqMeth = new RequestMethods();
        ProfileLocators profileLoc = new ProfileLocators();
        GeneralMessages genMes = new GeneralMessages();
        LogInMessages logInMessages = new LogInMessages();
        ProfileMethods profileMeth = new ProfileMethods(driver);
        BottomMenuLocators menuLoc = new BottomMenuLocators();
        Endpoints endpoints = new Endpoints();
        JsonData jsonData = new JsonData();
        genMeth.click(menuLoc.profile);
        genMeth.scrollFromTo(700, 1500, 700, 700);
        genMeth.click(profileLoc.deleteAccount);
        genMeth.type(genMes.lorem, profileLoc.deleteReasonField);
        genMeth.click(profileLoc.nextButton);
        profileMeth.typeDeletionPassword(userData.getPassword());
        genMeth.click(profileLoc.nextButton);
        genMeth.click(profileLoc.cancelButton);
        String userToken = mailMeth.getTokenFromEmail(mailSub.deletion, this.token);
        System.out.println("mek");
        reqMeth.patchReq(endpoints.accountDeletion, jsonData.accountDeletion(userToken));
        System.out.println("erku");
        genMeth.click(loginLoc.logInButton);
        genMeth.type(this.tempMail, loginLoc.emailField);
        genMeth.type(userData.getPassword(), loginLoc.passwordField);
        genMeth.click(loginLoc.logInButton);
        assertMeth.assertThatElementExists(loginLoc.invalidMailOrPasswordError, logInMessages.invalidPasswordMessage);
    }
}
