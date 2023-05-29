package org.example;
import io.appium.java_client.android.AndroidDriver;
import io.ous.jtoml.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int randomNumber = random.nextInt(7);
        System.out.println(randomNumber);
    }
}
