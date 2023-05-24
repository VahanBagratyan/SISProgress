package Base;

import Data.LogInMessages;
import Data.OneTimeUsedMessages;
import Data.UserData;
import Locators.BottomMenuLocators;
import Locators.HomePageLocators;
import Locators.LogInLocators;
import Locators.ProfileLocators;
import Methods.AssertMethods;
import Methods.GeneralMethods;
import Methods.RequestMethods;
import io.appium.java_client.android.AndroidDriver;

import java.util.concurrent.TimeUnit;

public class AccountControl {
    AndroidDriver driver;
    public AccountControl(AndroidDriver driver){
        this.driver = driver;
    }
    public void logIn(){
        GeneralMethods genMeth = new GeneralMethods(driver);
        LogInLocators loginLoc = new LogInLocators();
        UserData userData = new UserData();
        AssertMethods assertMeth = new AssertMethods(driver);
        HomePageLocators homeLock = new HomePageLocators();
        RequestMethods reqMeth = new RequestMethods(driver);
        LogInMessages logMes = new LogInMessages();
        reqMeth.registerRequest();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        genMeth.click(loginLoc.logInButton);
        genMeth.type(userData.getValidMail(), loginLoc.emailField);
        genMeth.type(userData.getValidPassword(), loginLoc.passwordField);
        genMeth.click(loginLoc.logInButton);
        assertMeth.waitForElementAndAssertThatAttributeContains(userData.getValidFullName(), homeLock.helloText, "content-desc", 20, logMes.wrongUserName);
    }
    public void logOut(){
        GeneralMethods genMeth = new GeneralMethods(driver);
        BottomMenuLocators menuLoc = new BottomMenuLocators();
        ProfileLocators profileLoc = new ProfileLocators();
        OneTimeUsedMessages message = new OneTimeUsedMessages();
        genMeth.click(menuLoc.profile);
        genMeth.scrollToElementAndClick(profileLoc.logOut, 5);
        genMeth.click(profileLoc.logOutYes);
    }
}
