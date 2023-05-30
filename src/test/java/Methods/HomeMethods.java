package Methods;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HomeMethods {
    AndroidDriver driver;
    public HomeMethods(AndroidDriver driver){
        this.driver = driver;
    }
    public void clickRandomDayFromHome( int dayFromToday){
        String currentDate = driver.getDeviceTime();
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.ENGLISH);
        String date = LocalDate.parse(currentDate, inputFormat).plusDays(dayFromToday).format(outputFormat);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        try{
            driver.findElement(By.xpath("//android.view.View[@content-desc=\""+date+"\"]")).click();
        }catch (NoSuchElementException e){
            if(dayFromToday>0){
            driver.findElement(By.xpath("//android.view.View[@index = \"13\"]")).click();
            }
            else {
                driver.findElement(By.xpath("//android.view.View[@index = \"11\"]")).click();
            }
            driver.findElement(By.xpath("//android.view.View[@content-desc=\""+date+"\"]")).click();
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
}
