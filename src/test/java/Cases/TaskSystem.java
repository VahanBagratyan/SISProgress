package Cases;

import Base.AccountControl;
import Base.SetUp;
import Data.TaskMessages;
import Data.UserData;
import Locators.BottomMenuLocators;
import Locators.CalendarLocators;
import Locators.MyTasksLocators;
import Methods.*;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TaskSystem {
    private static AndroidDriver driver;
    private static String appPkg;
    @BeforeSuite
    public void setUp() {
        SetUp setUp = new SetUp();
        driver = setUp.setUp();
        appPkg = driver.getCurrentPackage();
    }
    @BeforeMethod
    public void beforeEach(){
        AccountControl accountControl = new AccountControl(driver);
        accountControl.logIn();
    }
    @AfterMethod
    public void afterEach() {
       // String appPkg = driver.getCurrentPackage();
        AccountControl accountControl = new AccountControl(driver);
        accountControl.logOut();
        driver.terminateApp(appPkg);
        driver.activateApp(appPkg);
    }
    @Test
    public void addTaskFromCalendarForToday(){
        BottomMenuLocators menuLoc = new BottomMenuLocators();
        GeneralMethods genMeth = new GeneralMethods(driver);
        CalendarLocators calLoc = new CalendarLocators();
        CalendarMethods calMeth = new CalendarMethods(driver);
        AssertMethods assertMeth = new AssertMethods(driver);
        WaitMethods waitMeth = new WaitMethods(driver);
        TaskMessages taskMes = new TaskMessages();
        UserData userData = new UserData();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        genMeth.click(menuLoc.calendar);
        genMeth.click(calLoc.plusTask);
        waitMeth.waitUntilVisible(calLoc.tasksWindow, 20);
        userData.setTempTaskName(calMeth.selectRandomTask());
        genMeth.click(calLoc.addTask);
        assertMeth.assertThatElementExists(calLoc.addedTaskByTextToday(userData.getTempTaskName()),
                taskMes.cantAddTask);
    }
    @Test
    public void addTaskFromCalendarForNextDays(){
        UserData userData = new UserData();
        BottomMenuLocators menuLoc = new BottomMenuLocators();
        GeneralMethods genMeth = new GeneralMethods(driver);
        CalendarLocators calLoc = new CalendarLocators();
        CalendarMethods calMeth = new CalendarMethods(driver);
        AssertMethods assertMeth = new AssertMethods(driver);
        WaitMethods waitMeth = new WaitMethods(driver);
        TaskMessages taskMes = new TaskMessages();
        Random random = new Random();
        int randomNumber = random.nextInt(20)+1;
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        genMeth.click(menuLoc.calendar);
        calMeth.clickCalendarDay(randomNumber);
        genMeth.click(calLoc.plusTask);
        waitMeth.waitUntilVisible(calLoc.tasksWindow, 20);
        userData.setTempTaskName(calMeth.selectRandomTask());
        genMeth.click(calLoc.addTask);
        assertMeth.assertThatElementExists(calLoc.addedTaskByTextTomorrow(userData.getTempTaskName()),
                taskMes.cantAddTask);
    }
    @Test
    public void addTaskFromCalendarForNextDaysThenDelete(){
        BottomMenuLocators menuLoc = new BottomMenuLocators();
        GeneralMethods genMeth = new GeneralMethods(driver);
        CalendarLocators calLoc = new CalendarLocators();
        CalendarMethods calMeth = new CalendarMethods(driver);
        AssertMethods assertMeth = new AssertMethods(driver);
        WaitMethods waitMeth = new WaitMethods(driver);
        TaskMessages taskMes = new TaskMessages();
        UserData userData = new UserData();
        Random random = new Random();
        int randomNumber = random.nextInt(20)+1;
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        genMeth.click(menuLoc.calendar);
        calMeth.clickCalendarDay(randomNumber);
        genMeth.click(calLoc.plusTask);
        waitMeth.waitUntilVisible(calLoc.tasksWindow, 20);
        userData.setTempTaskName(calMeth.selectRandomTask());
        genMeth.click(calLoc.addTask);
        assertMeth.assertThatElementExists(calLoc.addedTaskByTextTomorrow(userData.getTempTaskName()),
                taskMes.cantAddTask);
        genMeth.click(calLoc.addedTaskByTextTomorrow(userData.getTempTaskName()));
        genMeth.click(calLoc.deleteTask);
        waitMeth.waitUntilInvisible(calLoc.deleteTask, 20);
        assertMeth.assertThatElementDoesNotExists(calLoc.addedTaskByTextTomorrow(userData.getTempTaskName()),
                taskMes.cantDeleteTask);
    }
    @Test
    public void completeOneSubtaskAndCheckTaskStatus(){
        BottomMenuLocators menuLoc = new BottomMenuLocators();
        GeneralMethods genMeth = new GeneralMethods(driver);
        CalendarLocators calLoc = new CalendarLocators();
        CalendarMethods calMeth = new CalendarMethods(driver);
        AssertMethods assertMeth = new AssertMethods(driver);
        WaitMethods waitMeth = new WaitMethods(driver);
        TaskMessages taskMes = new TaskMessages();
        UserData userData = new UserData();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        genMeth.click(menuLoc.calendar);
        genMeth.click(calLoc.plusTask);
        waitMeth.waitUntilVisible(calLoc.tasksWindow, 20);
        userData.setTempTaskName(calMeth.selectRandomTask());
        genMeth.click(calLoc.addTask);
        assertMeth.assertThatElementExists(calLoc.addedTaskByTextToday(userData.getTempTaskName()),
                taskMes.cantAddTask);
        genMeth.click(calLoc.addedTaskByTextToday(userData.getTempTaskName()));
        genMeth.click(calLoc.subtaskDropDown);
        genMeth.click(calLoc.subtask);
        genMeth.click(calLoc.submit);
        assertMeth.waitForElementAndAssertThatAttributeContains("In Progress",
                calLoc.addedTaskByTextToday(userData.getTempTaskName()),
                "content-desc",
                10,
                taskMes.isNotInProgress);
    }
    @Test
    public void completeTaskAndCheckTaskStatus(){
        BottomMenuLocators menuLoc = new BottomMenuLocators();
        GeneralMethods genMeth = new GeneralMethods(driver);
        CalendarLocators calLoc = new CalendarLocators();
        CalendarMethods calMeth = new CalendarMethods(driver);
        AssertMethods assertMeth = new AssertMethods(driver);
        WaitMethods waitMeth = new WaitMethods(driver);
        TaskMessages taskMes = new TaskMessages();
        UserData userData = new UserData();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        genMeth.click(menuLoc.calendar);
        genMeth.click(calLoc.plusTask);
        waitMeth.waitUntilVisible(calLoc.tasksWindow, 20);
        userData.setTempTaskName(calMeth.selectRandomTask());
        genMeth.click(calLoc.addTask);
        assertMeth.assertThatElementExists(calLoc.addedTaskByTextToday(userData.getTempTaskName()),
                taskMes.cantAddTask);
        genMeth.click(calLoc.addedTaskByTextToday(userData.getTempTaskName()));
        genMeth.click(calLoc.subtaskDropDown);
        calMeth.completeTask();
        genMeth.click(calLoc.submit);
        assertMeth.waitForElementAndAssertThatAttributeContains("Completed",
                calLoc.addedTaskByTextToday(userData.getTempTaskName()),
                "content-desc",
                20,
                taskMes.isNotCompleted);
    }
    @Test
    public void addTaskTodayAndCheckAppearanceTaskSection(){
        BottomMenuLocators menuLoc = new BottomMenuLocators();
        GeneralMethods genMeth = new GeneralMethods(driver);
        CalendarLocators calLoc = new CalendarLocators();
        CalendarMethods calMeth = new CalendarMethods(driver);
        AssertMethods assertMeth = new AssertMethods(driver);
        WaitMethods waitMeth = new WaitMethods(driver);
        TaskMessages taskMes = new TaskMessages();
        UserData userData = new UserData();
        MyTasksLocators myTaskLoc = new MyTasksLocators();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        genMeth.click(menuLoc.calendar);
        genMeth.click(calLoc.plusTask);
        waitMeth.waitUntilVisible(calLoc.tasksWindow, 20);
        userData.setTempTaskName(calMeth.selectRandomTask());
        genMeth.click(calLoc.addTask);
        assertMeth.assertThatElementExists(calLoc.addedTaskByTextToday(userData.getTempTaskName()),
                taskMes.cantAddTask);
        genMeth.click(menuLoc.myTasks);
        waitMeth.waitUntilVisible(myTaskLoc.myTask, 10);
        waitMeth.waitUntilVisible(myTaskLoc.getAddedTask(userData.getTempTaskName()), 10);
        assertMeth.assertThatElementExists(myTaskLoc.getAddedTask(userData.getTempTaskName()),
                taskMes.isNotInMyTasks);
    }
    @Test
    public void addTaskForPreviousDays(){
        BottomMenuLocators menuLoc = new BottomMenuLocators();
        GeneralMethods genMeth = new GeneralMethods(driver);
        CalendarLocators calLoc = new CalendarLocators();
        CalendarMethods calMeth = new CalendarMethods(driver);
        AssertMethods assertMeth = new AssertMethods(driver);
        TaskMessages taskMes = new TaskMessages();
        Random random = new Random();
        int randomNumber = random.nextInt(20)+1;
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        genMeth.click(menuLoc.calendar);
        calMeth.clickCalendarDay(-randomNumber);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertMeth.assertThatElementDoesNotExists(calLoc.plusTask,taskMes.canAddTaskYesterday);
    }
    @Test
    public void addTaskFromHomepageToday(){
        BottomMenuLocators menuLoc = new BottomMenuLocators();
        GeneralMethods genMeth = new GeneralMethods(driver);
        CalendarLocators calLoc = new CalendarLocators();
        CalendarMethods calMeth = new CalendarMethods(driver);
        UserData userData = new UserData();
        AssertMethods assertMeth = new AssertMethods(driver);
        TaskMessages taskMes = new TaskMessages();
        HomeMethods homeMeth = new HomeMethods(driver);
        WaitMethods waitMeth = new WaitMethods(driver);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        homeMeth.clickRandomDayFromHome(0);
        waitMeth.waitUntilVisible(calLoc.tasksWindow, 20);
        userData.setTempTaskName(calMeth.selectRandomTask());
        genMeth.click(calLoc.addTask);
        genMeth.click(menuLoc.calendar);
        calMeth.clickCalendarDay(0);
        assertMeth.assertThatElementExists(calLoc.addedTaskByTextToday(userData.getTempTaskName()),
                taskMes.cantAddTaskFromHomePageToday);
    }
    @Test
    public void addTaskFromHomepageNextDays(){
        GeneralMethods genMeth = new GeneralMethods(driver);
        CalendarLocators calLoc = new CalendarLocators();
        CalendarMethods calMeth = new CalendarMethods(driver);
        UserData userData = new UserData();
        BottomMenuLocators menuLoc = new BottomMenuLocators();
        AssertMethods assertMeth = new AssertMethods(driver);
        TaskMessages taskMes = new TaskMessages();
        HomeMethods homeMeth = new HomeMethods(driver);
        Random random = new Random();
        WaitMethods waitMeth = new WaitMethods(driver);
        int randomNumber = random.nextInt(5)+1;
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        homeMeth.clickRandomDayFromHome(randomNumber);
        waitMeth.waitUntilVisible(calLoc.tasksWindow, 20);
        userData.setTempTaskName(calMeth.selectRandomTask());
        genMeth.click(calLoc.addTask);
        genMeth.click(menuLoc.calendar);
        calMeth.clickCalendarDay(randomNumber);
        assertMeth.assertThatElementExists(calLoc.addedTaskByTextTomorrow(userData.getTempTaskName()),
                taskMes.cantAddTaskFromHomePageTomorrow);
    }

    @Test
    public void addTaskFromHomepagePreviousDays(){
        CalendarLocators calLoc = new CalendarLocators();
        AssertMethods assertMeth = new AssertMethods(driver);
        TaskMessages taskMes = new TaskMessages();
        HomeMethods homeMeth = new HomeMethods(driver);
        Random random = new Random();
        int randomNumber = random.nextInt(5)+1;
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        homeMeth.clickRandomDayFromHome(-randomNumber);
        assertMeth.assertThatElementDoesNotExists(calLoc.tasksWindow, taskMes.canAddTaskYesterdayFromHomepage);
    }

}

