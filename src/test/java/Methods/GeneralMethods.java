package Methods;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GeneralMethods {
    AndroidDriver driver;
    public GeneralMethods(AndroidDriver driver){
     this.driver = driver;
    }

    public void click(By locator){
        driver.findElement(locator).click();
    }
    public void type(String text,By locator){
        WebElement element = driver.findElement(locator);
        element.click();
        element.sendKeys(text);
    }

    public void scrollToElementAndClick(By locator, int scrollLimit) {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        if (scrollLimit <= 0) {
            Assert.assertTrue(driver.findElement(locator).isDisplayed());
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            return;
        }
        try {
            WebElement element = driver.findElement(locator);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            element.click();
        }catch (NoSuchElementException a) {
            scrollFromTo( 750, 1700, 750, 200);
            scrollToElementAndClick(locator, scrollLimit-1);
        }
    }

    public void scrollFromTo(int fromX, int fromY, int toX, int toY){
        TouchAction touchAction = new TouchAction(driver);
        touchAction.longPress(PointOption.point(fromX,fromY))
                .moveTo(PointOption.point(toX, toY))
                .release()
                .perform();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void clickByCoordinate(int x, int y){
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(PointOption.point(x,y)).perform();
    }
    public void closeKeyboard(){
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(PointOption.point(154, 3020));
    }
    public void selectFromFancyDropdown(By dropdown, By option){
        click(dropdown);
        click(option);
    }

    public String getColour(int x,int y){
        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage screenshotImage = null;
            screenshotImage = ImageIO.read(screenshotFile);
            Color pixelColor = new Color(screenshotImage.getRGB(x, y));
            String hexColor = String.format("#%06X", pixelColor.getRGB() & 0xFFFFFF);
            return hexColor;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
