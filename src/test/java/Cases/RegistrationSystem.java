package Cases;

import Base.AccountControl;
import Base.SetUp;
import Data.LogInMessages;
import Data.MailSubjects;
import Data.UserData;
import Locators.HomePageLocators;
import Locators.LogInLocators;
import Locators.RegistrationLocators;
import Methods.*;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class RegistrationSystem {
    private AndroidDriver driver;
   // private static String appPkg;
    String tempMail;
    @BeforeSuite
    public void setUp() {
        SetUp setUp = new SetUp();
        driver = setUp.setUp();
        //appPkg = driver.getCurrentPackage();
    }

    @AfterMethod
    public void deleteAccount() {
        AccountControl accControl = new AccountControl(driver);
        accControl.deleteAccountFromDB();
    }

    @Test
    public void registrationNineGradeWithValidData() {
        GeneralMethods genMeth = new GeneralMethods(driver);
        RegistrationLocators regLoc = new RegistrationLocators();
        RegistrationMethods regMeth = new RegistrationMethods(driver);
        UserData userData = new UserData();
        MailMethods mailMeth = new MailMethods();
        MailSubjects mailSub = new MailSubjects();
        LogInLocators loginLoc = new LogInLocators();
        AssertMethods assertMeth = new AssertMethods(driver);
        LogInMessages logMes = new LogInMessages();
        HomePageLocators homeLock = new HomePageLocators();
        UtilsMethods utilMeth = new UtilsMethods(driver);
        String tempMail = userData.getTempMail();
        String token = mailMeth.createAccountReturnToken(tempMail);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        genMeth.click(regLoc.registrationButton);
        genMeth.type(userData.getFullName(), regLoc.fullNameField);
        genMeth.type(tempMail, regLoc.emailField);
        genMeth.type(userData.getPassword(), regLoc.passwordField);
        genMeth.type(userData.getPassword(), regLoc.confirmPasswordField);
        genMeth.scrollFromTo(700, 1500, 700, 700);
        genMeth.click(regLoc.countryNumSelector);
        genMeth.click(regLoc.countryNum);
        genMeth.type(userData.getNumber(), regLoc.numberField);
        genMeth.selectFromFancyDropdown(regLoc.countryDropdown, regLoc.country);
        genMeth.selectFromFancyDropdown(regLoc.gradeDropdown, regLoc.grade);
        regMeth.selectDate(regLoc.date, userData.getBirthDate());
        genMeth.selectFromFancyDropdown(regLoc.uniDropdown, regLoc.uni);
        genMeth.scrollFromTo(700, 1500, 700, 700);
        //will change after bug fix
        genMeth.clickByCoordinate(1214, 2591);
        genMeth.clickByCoordinate(241, 2745);
        genMeth.clickByCoordinate(559, 2583);
        //
        genMeth.click(regLoc.nextButton);
        genMeth.click(regLoc.sendLink);
        //verification
        mailMeth.verifyMailBySubject(mailSub.registration, token, "/verify");
        //Login to be sure that user is verified
        genMeth.click(loginLoc.logInButtonAfterReg);
        genMeth.type(tempMail, loginLoc.emailField);
        genMeth.type(userData.getPassword(), loginLoc.passwordField);
        genMeth.click(loginLoc.logInButton);
        genMeth.click(regLoc.termStart);
        genMeth.click(regLoc.admissionPlan);
        genMeth.click(regLoc.financeAID);
        genMeth.click(regLoc.legacy);
        genMeth.scrollFromTo(700, 1500, 700, 700);
        genMeth.type("asdfg", regLoc.experience);
        utilMeth.hideKeyboard();
        genMeth.click(regLoc.submit);
        assertMeth.waitForElementAndAssertThatAttributeContains(
                userData.getFullName(),
                homeLock.welcomeText,
                "content-desc",
                20,
                logMes.wrongUserName
        );
    }

    @Test
    public void registrationLeavingRequiredFieldEmpty() {
        GeneralMethods genMeth = new GeneralMethods(driver);
        RegistrationLocators regLoc = new RegistrationLocators();
        RegistrationMethods regMeth = new RegistrationMethods(driver);
        UserData userData = new UserData();
        MailMethods mailMeth = new MailMethods();
        MailSubjects mailSub = new MailSubjects();
        LogInLocators loginLoc = new LogInLocators();
        AssertMethods assertMeth = new AssertMethods(driver);
        LogInMessages logMes = new LogInMessages();
        HomePageLocators homeLock = new HomePageLocators();
        UtilsMethods utilMeth = new UtilsMethods(driver);
        this.tempMail = userData.getTempMail();
        String token = mailMeth.createAccountReturnToken(this.tempMail);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        genMeth.click(regLoc.registrationButton);
        genMeth.scrollFromTo(700, 1500, 700, 700);
        genMeth.click(regLoc.nextButton);
    }
}
