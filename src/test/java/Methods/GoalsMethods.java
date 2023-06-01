package Methods;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;

import java.util.List;
import java.util.Random;

public class GoalsMethods {
    AndroidDriver driver;

    public GoalsMethods(AndroidDriver driver) {
        this.driver = driver;
    }

    public void clickRandomExtracurricular() {
        Random random = new Random();
        GeneralMethods genMeth = new GeneralMethods(driver);
        int scrollNumber = random.nextInt(4);
        for (int i = 0; i <= scrollNumber; i++) {
            System.out.println(scrollNumber);
            if (i == scrollNumber) {
                List<WebElement> elements = driver.findElements(By.xpath("//android.widget.ImageView"));
                System.out.println(elements.size() - 2);
                int index = random.nextInt(elements.size() - 3);
                System.out.println("//android.widget.ImageView[" + index + "]");
                elements.get(index).click();
                return;  // Exit the method after clicking the random element
            }
            genMeth.scrollFromTo(700, 2000, 700, 200);
            System.out.println("scrolled");
        }
    }

    public void clickExtracurricularAdd() {
        try {
            driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"ADD\"]")).click();
        } catch (NoSuchElementException e) {
            GeneralMethods genMeth = new GeneralMethods(driver);
            genMeth.scrollFromTo(700, 1000, 700, 200);
            clickExtracurricularAdd();
        }
    }
}
