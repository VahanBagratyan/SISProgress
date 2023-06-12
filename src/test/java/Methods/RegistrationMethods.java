package Methods;

import Data.UserData;
import Locators.RegistrationLocators;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class RegistrationMethods {
    private AndroidDriver driver;

    public RegistrationMethods(AndroidDriver driver) {
        this.driver = driver;
    }

    public void selectDate(By fieldLocator, String inputDate) {
        GeneralMethods genMeth = new GeneralMethods(driver);
        TouchAction touchAction = new TouchAction(driver);
        UserData userData = new UserData();
        RegistrationLocators regLoc = new RegistrationLocators();
        //open calendar
        WebElement dateField = driver.findElement(fieldLocator);
        Point elementPoint = dateField.getLocation();
        int dateFieldHeight = dateField.getSize().height;
        int xField = (elementPoint.x) * 9;
        int yField = (elementPoint.y) + (dateFieldHeight / 2);
        touchAction.tap(PointOption.point(xField, yField)).perform();

        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        DateTimeFormatter monthFormat = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH);

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        LocalDate date = LocalDate.parse(inputDate, inputFormat);
        genMeth.click(regLoc.yearArrow);
        WebElement yearDropdown = driver.findElement(regLoc.yearDropdown);
        Dimension yearSize = yearDropdown.getSize();
        int yearWidth = yearSize.width;
        int yearLength = yearSize.width;
        Point yearPoints = yearDropdown.getLocation();
        int xYear = yearPoints.x + (yearWidth / 2);
        int yYearFrom = yearPoints.y + (yearLength) - 20;
        int yYearTo = yYearFrom-500;
        System.out.println("x - " + xYear + "yFrom - " + yYearFrom + "yTo -" + yYearTo);
        while (true) {
            try {
                driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"" + date.getYear() + "\"]"))
                        .click();
                break;
            } catch (NoSuchElementException e) {
                genMeth.scrollFromTo(xYear, yYearFrom, xYear, yYearTo);
            }
        }

        genMeth.click(regLoc.monthArrow);
        System.out.println("//android.widget.Button[@content-desc=\""+date.format(monthFormat)+"\"]");
        genMeth.click(By.xpath("//android.widget.Button[@content-desc=\""+date.format(monthFormat)+"\"]"));

        genMeth.click(By.xpath("//android.view.View[@index = \""+date.getDayOfMonth()+"\"]"));
        touchAction.tap(PointOption.point(300, 300)).perform();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
}
