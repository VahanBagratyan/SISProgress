package Locators;

import org.openqa.selenium.By;

public class MyTasksLocators {
    public By myTask = By.xpath("//android.view.View[@content-desc=\"My Tasks\"]");
    public By getAddedTask(String text){
        return By.xpath("//android.view.View[contains(@content-desc, \""+text+"\")]");
    }
}
