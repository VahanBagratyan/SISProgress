package Cases;

import Base.SetUp;
import Data.LogInMessages;
import Data.UserData;
import Locators.HomePageLocators;
import Locators.LogInLocators;
import Methods.AssertMethods;
import Methods.GeneralMethods;
import Methods.RequestMethods;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class LogInSystem {
    private static AndroidDriver driver;

    @BeforeSuite
    public void setUp() {
        SetUp setUp = new SetUp();
        driver = setUp.setUp();
    }

    @AfterMethod
    public void afterEach() {
        String appPkg = driver.getCurrentPackage();
        driver.terminateApp(appPkg);
        driver.activateApp(appPkg);
    }

    @DataProvider(name = "credentials")
    public Object[][] credentialsProvider() {
        return new Object[][]{
                {"username", "password"},
                {"username@com", "password"},
                {"userna@", "password"},
                {"username3@df", "password"},
                {"username3.com", "password"}
        };
    }

    @Test
    public void logInWithValidData() {
        GeneralMethods genMeth = new GeneralMethods(driver);
        LogInLocators loginLoc = new LogInLocators();
        UserData userData = new UserData();
        AssertMethods assertMeth = new AssertMethods(driver);
        HomePageLocators homeLock = new HomePageLocators();
        RequestMethods reqMeth = new RequestMethods();
        LogInMessages logMes = new LogInMessages();
        reqMeth.registerRequest();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        genMeth.click(loginLoc.logInButton);
        genMeth.type(userData.getValidMail(), loginLoc.emailField);
        genMeth.type(userData.getPassword(), loginLoc.passwordField);
        genMeth.click(loginLoc.logInButton);
        assertMeth.waitForElementAndAssertThatAttributeContains(
                userData.getValidFullName(),
                homeLock.welcomeText,
                "content-desc",
                20,
                logMes.wrongUserName
        );
    }

    @Test(dataProvider = "credentials")
    public void logInWithInvalidFormatMail(String userEmail, String password) {
        GeneralMethods genMeth = new GeneralMethods(driver);
        LogInLocators loginLoc = new LogInLocators();
        AssertMethods assertMeth = new AssertMethods(driver);
        LogInMessages logInMessages = new LogInMessages();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        genMeth.click(loginLoc.logInButton);
        genMeth.type(userEmail, loginLoc.emailField);
        genMeth.type(password, loginLoc.passwordField);
        genMeth.click(loginLoc.logInButton);
        assertMeth.assertThatElementExists(loginLoc.wrongFormatMailError, logInMessages.wrongEmailFormatMessage);
        assertMeth.assertThatElementExists(loginLoc.invalidMailOrPasswordError, logInMessages.invalidMailMessage);
    }

    @Test
    public void logInWithInvalidEmail() {
        GeneralMethods genMeth = new GeneralMethods(driver);
        LogInLocators loginLoc = new LogInLocators();
        UserData userData = new UserData();
        AssertMethods assertMeth = new AssertMethods(driver);
        LogInMessages logInMessages = new LogInMessages();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        genMeth.click(loginLoc.logInButton);
        genMeth.type(userData.getInvalidEmail(), loginLoc.emailField);
        genMeth.type(userData.getPassword(), loginLoc.passwordField);
        genMeth.click(loginLoc.logInButton);
        assertMeth.assertThatElementExists(loginLoc.invalidMailOrPasswordError, logInMessages.invalidMailMessage);
    }

    @Test
    public void logInWithInvalidPassword() {
        GeneralMethods genMeth = new GeneralMethods(driver);
        LogInLocators loginLoc = new LogInLocators();
        UserData userData = new UserData();
        AssertMethods assertMeth = new AssertMethods(driver);
        LogInMessages logInMessages = new LogInMessages();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        genMeth.click(loginLoc.logInButton);
        genMeth.type(userData.getValidMail(), loginLoc.emailField);
        genMeth.type(userData.getInvalidPassword(), loginLoc.passwordField);
        genMeth.click(loginLoc.logInButton);
        assertMeth.assertThatElementExists(loginLoc.invalidMailOrPasswordError, logInMessages.invalidPasswordMessage);
    }
}
