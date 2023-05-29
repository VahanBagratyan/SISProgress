package Data;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;

public class TaskMessages {
    public String cantAddTask = "When the user adds a task it is not visible in calendar";
    public String cantDeleteTask = "The user can't delete a task";
    public String isNotInProgress = "When one subtask is completed the status is not changed to \" In Progress\"";
    public String isNotCompleted = "When task is completed status is not changed";
    public String isNotInMyTasks = "The added task is not visible in \"My Tasks\" section";
    public String canAddTaskYesterday = "The user can add task for yesterday";
}
