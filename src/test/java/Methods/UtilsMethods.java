package Methods;

import io.appium.java_client.android.AndroidDriver;

public class UtilsMethods {
    private final AndroidDriver driver;
    public UtilsMethods(AndroidDriver driver){
        this.driver = driver;

    }

    public void hideKeyboard(){
        driver.hideKeyboard();
    }
}
