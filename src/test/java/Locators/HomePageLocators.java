package Locators;

import Data.UserData;
import org.openqa.selenium.By;
import java.util.Random;

public class HomePageLocators {
    Random random = new Random();
    int randomNumber = random.nextInt(6);
    UserData userData = new UserData();
    public By helloText = By.xpath("//android.view.View[contains(@content-desc, 'Good')]");
}
