package Cases;

import Base.AccountControl;
import Base.SetUp;
import Data.TaskMessages;
import Locators.BottomMenuLocators;
import Locators.CalendarLocators;
import Methods.AssertMethods;
import Methods.CalendarMethods;
import Methods.GeneralMethods;
import Methods.WaitMethods;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

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
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        genMeth.click(menuLoc.calendar);
        genMeth.click(calLoc.plusTask);
        waitMeth.waitUntilVisible(calLoc.tasksWindow, 20);
        calMeth.selectRandomTask();
        genMeth.click(calLoc.addTask);
        assertMeth.assertThatElementExists(calLoc.addedTask, taskMes.cantAddTask);
    }
    @Test
    public void addTaskFromCalendarForTomorrow(){
        BottomMenuLocators menuLoc = new BottomMenuLocators();
        GeneralMethods genMeth = new GeneralMethods(driver);
        CalendarLocators calLoc = new CalendarLocators();
        CalendarMethods calMeth = new CalendarMethods(driver);
        AssertMethods assertMeth = new AssertMethods(driver);
        WaitMethods waitMeth = new WaitMethods(driver);
        TaskMessages taskMes = new TaskMessages();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        genMeth.click(menuLoc.calendar);
        calMeth.clickTomorrowCalendar();
        genMeth.click(calLoc.plusTask);
        waitMeth.waitUntilVisible(calLoc.tasksWindow, 20);
        calMeth.selectRandomTask();
        genMeth.click(calLoc.addTask);
        assertMeth.assertThatElementExists(calLoc.addedTaskTomorrow, taskMes.cantAddTask);
    }

    @Test
    public void addTaskFromCalendarForTomorrowThenDelete(){
        BottomMenuLocators menuLoc = new BottomMenuLocators();
        GeneralMethods genMeth = new GeneralMethods(driver);
        CalendarLocators calLoc = new CalendarLocators();
        CalendarMethods calMeth = new CalendarMethods(driver);
        AssertMethods assertMeth = new AssertMethods(driver);
        WaitMethods waitMeth = new WaitMethods(driver);
        TaskMessages taskMes = new TaskMessages();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        genMeth.click(menuLoc.calendar);
        calMeth.clickTomorrowCalendar();
        genMeth.click(calLoc.plusTask);
        waitMeth.waitUntilVisible(calLoc.tasksWindow, 20);
        calMeth.selectRandomTask();
        genMeth.click(calLoc.addTask);
        assertMeth.assertThatElementExists(calLoc.addedTaskTomorrow, taskMes.cantAddTask);
        genMeth.click(calLoc.addedTaskTomorrow);
        genMeth.click(calLoc.deleteTask);
        assertMeth.assertThatElementDoesNotExists(calLoc.addedTaskTomorrow, taskMes.cantDeleteTask);
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
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        genMeth.click(menuLoc.calendar);
        genMeth.click(calLoc.plusTask);
        waitMeth.waitUntilVisible(calLoc.tasksWindow, 20);
        calMeth.selectRandomTask();
        genMeth.click(calLoc.addTask);
        assertMeth.assertThatElementExists(calLoc.addedTask, taskMes.cantAddTask);
        genMeth.click(calLoc.addedTask);
        genMeth.click(calLoc.subtaskDropDown);
        genMeth.click(calLoc.subtask);
        genMeth.click(calLoc.submit);
        assertMeth.waitForElementAndAssertThatAttributeContains("In Progress",calLoc.addedTask,"content-desc", 10, taskMes.isNotInProgress);
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
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        genMeth.click(menuLoc.calendar);
        genMeth.click(calLoc.plusTask);
        waitMeth.waitUntilVisible(calLoc.tasksWindow, 20);
        calMeth.selectRandomTask();
        genMeth.click(calLoc.addTask);
        assertMeth.assertThatElementExists(calLoc.addedTask, taskMes.cantAddTask);
        genMeth.click(calLoc.addedTask);
        genMeth.click(calLoc.subtaskDropDown);
        calMeth.completeTask();
        genMeth.click(calLoc.submit);
        assertMeth.waitForElementAndAssertThatAttributeContains("Completed",calLoc.addedTask,"content-desc", 10, taskMes.isNotCompleted);
    }
}
