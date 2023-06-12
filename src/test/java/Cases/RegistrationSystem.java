package Cases;

import Base.SetUp;
import Data.UserData;
import Locators.RegistrationLocators;
import Methods.GeneralMethods;
import Methods.MailMethods;
import Methods.RegistrationMethods;
import Methods.RequestMethods;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class RegistrationSystem {
    private AndroidDriver driver;
    private static String appPkg;

    @BeforeSuite
    public void setUp() {
        SetUp setUp = new SetUp();
        driver = setUp.setUp();
        appPkg = driver.getCurrentPackage();
    }
    @AfterMethod
    public void deleteAccount(){
        RequestMethods reqMeth = new RequestMethods();
        HashMap<String, String> params = new HashMap<>();
        UserData userData = new UserData();
        params.put("email", userData.getTempMail());
        params.put("password", userData.getPassword());
        params.put("text", "$2b$10$5yjnqNn/RxYamiu0ZhhZzuL9SztPRwSpq4tzpojToQl.WHRJvguf6");
        reqMeth.deleteReq("https://sisprogress.online/user/deleteForTesting", "", params);
    }

    @Test
    public void registrationWithValidData() {
        GeneralMethods genMeth = new GeneralMethods(driver);
        RegistrationLocators regLoc = new RegistrationLocators();
        RegistrationMethods regMeth = new RegistrationMethods(driver);
        UserData userData = new UserData();
        MailMethods mailMeth = new MailMethods();
        String tempMail = userData.getTempMail();
        String token = mailMeth.createAccountReturnToken(tempMail);
        System.out.println(tempMail);
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
        //change after bug fix
        genMeth.clickByCoordinate(1214, 2591);
        genMeth.clickByCoordinate(241, 2745);
        genMeth.clickByCoordinate(559, 2583);
        //
        genMeth.click(regLoc.nextButton);
        genMeth.click(regLoc.sendLink);
        //verification
        mailMeth.verifyMailBySubject("Action Required: Verify your email address", token);
    }
}











