package Locators;

import Data.UserData;
import org.openqa.selenium.By;
import java.util.Random;

public class HomePageLocators {
    Random random = new Random();
    public By welcomeText = By.xpath("//android.view.View[contains(@content-desc, 'Good')]");
}
