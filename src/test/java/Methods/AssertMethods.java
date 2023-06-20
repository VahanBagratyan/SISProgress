package Methods;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AssertMethods  {
    private final AndroidDriver driver;
    public AssertMethods(AndroidDriver driver){
        this.driver = driver;
    }
    public void waitForElementAndAssertThatAttributeContains(String text, By elementLocator, String attribute, int waitTime, String message){
        WebElement element = driver.findElement(elementLocator);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(waitTime));
        try {
            wait.until(ExpectedConditions.attributeContains(element, attribute, text));
        }
        catch(TimeoutException e){
                Assert.assertTrue(element.getAttribute(attribute).contains(text) , message);
        }
    }

    public void assertThatElementExists(By locator, String message){
        boolean elementExist = driver.findElement(locator).isDisplayed();
        Assert.assertTrue(elementExist, message);
    }
    public void assertThatElementDoesNotExists(By locator, String message){
        driver.manage().timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS);
        try{
        boolean elementExist = driver.findElement(locator).isDisplayed();
        Assert.assertFalse(elementExist, message);
            driver.manage().timeouts()
                    .implicitlyWait(20, TimeUnit.SECONDS);
        }catch (NoSuchElementException e) {
            driver.manage().timeouts()
                    .implicitlyWait(20, TimeUnit.SECONDS);
        }
    }
}
