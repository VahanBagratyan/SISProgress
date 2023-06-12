package Locators;

import org.openqa.selenium.By;

public class RegistrationLocators {
    public By registrationButton = By.xpath("//android.widget.Button[@content-desc=\"Registration\"]");
    public By fullNameField = By.xpath("//android.widget.EditText[@index = \"1\"]");
    public By emailField = By.xpath("//android.widget.EditText[@index = \"2\"]");
    public By passwordField = By.xpath("//android.widget.EditText[@index = \"3\"]");
    public By confirmPasswordField = By.xpath("//android.widget.EditText[@index = \"4\"]");
    public By countryNumSelector = By.xpath("//android.widget.ImageView[@content-desc=\"+1         \"]");
    public By countryNum = By.xpath("//android.widget.ImageView[@content-desc=\"+44      \"]");
    public By numberField = By.xpath("//android.widget.EditText[@index = \"6\"]");
    public By date = By.xpath("//android.view.View[@index = \"9\"]");
    public By monthArrow = By.xpath("//android.widget.Button[1]");
    public By yearArrow = By.xpath("//android.widget.Button[2]");
    public By yearDropdown = By.xpath("//android.widget.ScrollView");
    public By monthDropdown = By.xpath("//android.widget.ScrollView");
    public By month = By.xpath("//android.widget.Button[@content-desc=\"February\"]");
    public By year = By.xpath("//android.widget.Button[@content-desc=\"2004\"]");
    public By day = By.xpath("//android.view.View[@content-desc=\"Monday, February 16, 2004\"]");
    public By countryDropdown = By.xpath("//android.widget.Button[@content-desc=\"Country\"]");
    public By country = By.xpath("//android.view.View[@content-desc=\"Albania\"]");
    public By gradeDropdown = By.xpath("//android.widget.Button[@content-desc=\"Grade\"]");
    public By grade = By.xpath("//android.view.View[@content-desc=\"Up to 9th grade\"]");
    public By nextButton = By.xpath("//android.widget.Button[@content-desc=\"Next\"]");
    public By uniDropdown = By.xpath("//android.widget.Button[@content-desc=\"Pick your dream university\"]");
    public By uni = By.xpath("//android.view.View[@content-desc=\"Stanford University\"]");
    public By sendLink = By.xpath("//android.widget.Button[@content-desc=\"Send verification link\"]");
    By termOption = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ScrollView/android.view.View[4]");
    public By admissionOption  = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ScrollView/android.view.View[13]");
    public By financialAidOption = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ScrollView/android.view.View[18]");
    public By legacyOption = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ScrollView/android.view.View[23]");
}
