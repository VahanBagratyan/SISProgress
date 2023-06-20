package Methods;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

public class ProfileMethods {
    private final AndroidDriver driver;

    public ProfileMethods(AndroidDriver driver) {
        this.driver = driver;
    }

    public void typeDeletionPassword(String text){
        TouchAction action = new TouchAction(driver);
        Point size = driver.findElement(By.xpath("//android.widget.Button")).getLocation();
        action.tap(PointOption.point(size.x-20,size.y+20)).perform();
        driver.findElement(By.xpath("//android.widget.EditText")).sendKeys(text);

    }

}
