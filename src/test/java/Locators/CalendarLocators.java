package Locators;
import org.openqa.selenium.By;

public class CalendarLocators {
    public By plusTask = By.xpath("//android.widget.Button");
    public By tasksWindow = By.xpath("//android.view.View[@content-desc=\"Tasks\"]");
    public By task = By.xpath("//android.view.View[@content-desc=\"Student Researcher\"]");
    public By addTask = By.xpath("//android.widget.Button[@content-desc=\"Add\"]");
   // public By addedTask = By.xpath("//android.view.View[@long-clickable=\"false\" and @index = \"7\"]");
    //public By addedTaskTomorrow = By.xpath("//android.widget.ImageView[@long-clickable=\"false\" and @index = \"7\"]") ;
    public By deleteTask = By.xpath("//android.widget.Button[@content-desc=\"Delete\"]");
    public By subtaskDropDown = By.xpath("//android.view.View[@content-desc=\"Subtasks\"]");
    public By subtask = By.xpath("//android.view.View[@focusable = \"true\" and not(@content-desc)]");
    public By submit = By.xpath("//android.widget.Button[@content-desc=\"Submit\"]");
    public By lastAddedTask = By.xpath("//android.view.View[contains(@content-desc, \"Planned\") and @index = \"7\"]");

    public By addedTaskByTextToday(String text) {
        return By.xpath("//android.view.View[contains(@content-desc, \""+text+"\") and  @index = \"7\"]");
        }

    public By addedTaskByTextTomorrow(String text) {
        return By.xpath("//android.widget.ImageView[contains(@content-desc, \""+text+"\") and  @index = \"7\"]");
        }
    }
