package Locators;

import org.openqa.selenium.By;

public class GoalsLocators {
    public By extracurricular = By.xpath("//android.widget.Button[@content-desc=\"Extracurricular\"]");

    public By getExtracurricularByText(String extracurricular) {
        return By.xpath("//android.widget.ImageView[@content-desc=\"" + extracurricular + "\"]");
    }
}
