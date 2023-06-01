package Locators;

import org.openqa.selenium.By;

public class BottomMenuLocators {
    public By calendar = By.xpath("//android.view.View[@content-desc=\"Calendar\n" +
            "Tab 2 of 5\"]");
    public By profile = By.xpath("//android.view.View[@content-desc=\"Profile\n" +
            "Tab 5 of 5\"]");
    public By myTasks = By.xpath("//android.view.View[@content-desc=\"My tasks\n" +
            "Tab 4 of 5\"]");
    public By home = By.xpath("//android.view.View[@content-desc=\"Home\n" +
            "Tab 1 of 5\"]");
    public By goals = By.xpath("//android.view.View[@content-desc=\"Goals\n" +
            "Tab 3 of 5\"]");
}
