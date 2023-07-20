package Locators;

import org.openqa.selenium.By;

public class ProfileLocators {
    public By logOut = By.xpath("//android.view.View[@content-desc=\"Log out\"]");
    public By logOutYes = By.xpath("//android.widget.Button[@content-desc=\"Yes\"]");
    public By deleteAccount = By.xpath("//android.widget.Button[@content-desc=\"Delete My account\"]");
    public By deactivateAccount = By.xpath("//android.widget.Button[@content-desc=\"Deactivate My account\"]");
    public By deleteReasonField = By.xpath("//android.widget.EditText");
    public By nextButton = By.xpath("//android.widget.Button[@content-desc=\"Next\"]");
    public By cancelButton = By.xpath("//android.widget.Button[@content-desc=\"Cancel\"]");
}
