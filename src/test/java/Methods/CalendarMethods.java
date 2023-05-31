package Methods;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import Locators.CalendarLocators;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;
public class CalendarMethods {
    AndroidDriver driver;
    Random random = new Random();
    private int scrollLimit = random.nextInt(6);
    private static int temp = 0;
    public CalendarMethods(AndroidDriver driver){
        this.driver = driver;
    }
    public String getDate( int dayFromToday){
        String currentDate = driver.getDeviceTime();
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(currentDate, inputFormat).plusDays(dayFromToday);
        return date.format(outputFormat);
    }
    public void clickCalendarDay(int day){
        String currentDate = driver.getDeviceTime();
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(currentDate, inputFormat);
        LocalDate nextDay = date.plusDays(day);
        String month = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String nextMonth = nextDay.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        if(!month.equals(nextMonth)){
            driver.findElement(By.xpath("//android.widget.ImageView[@index = \"2\"]")).click();
            driver.findElement(By.xpath("//android.widget.Button[@content-desc=\""+nextMonth+"\"]")).click();
        }
        String formattedDate = nextDay.format(outputFormat);
        By vardan = By.xpath("//android.view.View[@content-desc=\"" + formattedDate + "\"]");
        driver.findElement(vardan).click();
    }
    public String selectRandomTask() {
        TouchAction touchAction = new TouchAction(driver);
        if (scrollLimit <= 0) {
            WebElement element = driver.findElements(By.xpath("//android.view.View[@index=\"0\" and @focusable= \"true\" and @clickable = \"true\"]"))
                    .get(2);
            Dimension size = element.getSize();
            int width = (size.getWidth() / 4) * 3;
            int height = size.getHeight() / 2;
            Point startPoints = element.getLocation();
            int x = startPoints.x + width;
            int y = startPoints.y + height;
            touchAction.tap(PointOption.point(x, y)).perform();
            String taskName = element.getAttribute("content-desc");
            return taskName;
        } else {
            GeneralMethods genMeth = new GeneralMethods(driver);
            genMeth.scrollFromTo(530, 1400, 530, 800);
            scrollLimit -= 1;
            return selectRandomTask();
        }
    }
    public void completeTask(){
        GeneralMethods genMeth = new GeneralMethods(driver);
        CalendarLocators calLoc = new CalendarLocators();
        try {
            if(temp>=2){
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                return;
            }
            //WebElement checkElement = driver.findElement(calLoc.subtask);
            List<WebElement> elements = driver.findElements(calLoc.subtask);
            for (int i = 0; i < elements.size()+1; i++) {
                Point taskPoints = elements.get(i).getLocation();
                Dimension size1 = elements.get(i).getSize();
                int width1 = (size1.getWidth())/2;
                int height1 = size1.getHeight()/2;
                int xTask = taskPoints.x+width1;
                int yTask = taskPoints.y+height1;
                String color = genMeth.getColour(xTask, yTask);
                if(color.equals("#FFFFFF")){
                    temp=0;
                    elements.get(i).click();
                }
                if(i == elements.size()-1){
                temp++;
                WebElement element = driver.findElement(By.xpath("//android.widget.ScrollView"));
                Dimension size = element.getSize();
                int width = (size.getWidth())/2;
                int height = size.getHeight();
                Point startPoints = element.getLocation();
                int x = startPoints.x;
                int y = startPoints.y;
                genMeth.scrollFromTo(x+width,y+height-30, x+width,y+30);
                completeTask();
                }
            }
        }
        catch (IndexOutOfBoundsException e){
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        }
    }
}
