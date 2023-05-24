package Methods;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitMethods {
    private AndroidDriver driver;
    public WaitMethods(AndroidDriver driver){
        this.driver = driver;
    }

    public void waitUntilVisible(By locator, int waitTime){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
    }

}