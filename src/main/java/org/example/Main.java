package org.example;
import io.appium.java_client.android.AndroidDriver;
import io.ous.jtoml.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {
        System.out.println(Main.extractHttpLink("WELCOME TO SIS PROGRESS\n" +
                "\n" +
                "[cid:Frame]\n" +
                "\n" +
                "\n" +
                "PLEASE VERIFY YOUR EMAIL ADDRESS.\n" +
                "\n" +
                "In order to complete your registration and start preparing for college\n" +
                "admissions, you'll need to verify your email address.\n" +
                "\n" +
                "You've entered a10302a3806@internetkeno.com as the email address for your\n" +
                "account. Please verify this email address by clicking button below.\n" +
                "\n" +
                "Verify\n" +
                "[https://sisprogress.com/message?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjozMDQxLCJlbWFpbCI6ImExMDMwMmEzODA2QGludGVybmV0a2Vuby5jb20iLCJpYXQiOjE2ODY1NTQzMzJ9.mptOkc1n1fGfXLzN2zoV1yfMFdGJuh8pSFJ-pZk9yA0]\n" +
                "\n" +
                "If the button is not working please use the link below:\n" +
                "https://sisprogress.com/message?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjozMDQxLCJlbWFpbCI6ImExMDMwMmEzODA2QGludGVybmV0a2Vuby5jb20iLCJpYXQiOjE2ODY1NTQzMzJ9.mptOkc1n1fGfXLzN2zoV1yfMFdGJuh8pSFJ-pZk9yA0\n" +
                "[https://sisprogress.com/message?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjozMDQxLCJlbWFpbCI6ImExMDMwMmEzODA2QGludGVybmV0a2Vuby5jb20iLCJpYXQiOjE2ODY1NTQzMzJ9.mptOkc1n1fGfXLzN2zoV1yfMFdGJuh8pSFJ-pZk9yA0]\n" +
                "\n" +
                "Regards,\n" +
                "\n" +
                "[cid:SISlogo]\n" +
                "\n" +
                "You have expressed interest in or supported SIS Progress.\n" +
                "\n" +
                "Our mailing address is: info@sisprogress.com\n" +
                "[https://mail.google.com/mail/u/0/?tab=rm&ogbl#inbox?compose=GTvVlcSGLdqJpVPMGCFHQZXRljQjDczTJpzSxnxCrfjsQwRhFPPVRncHqjzjPlgcqRRZhgWPGZwJB]\n" +
                "\n" +
                "Want to change how you receive these emails?\n" +
                "\n" +
                "You can update your references and unsubscribe.\n" +
                "\n" +
                "© 2023 SIS Progress, All rights reserved\n"));
    }

    public static String extractHttpLink(String text) {
        String regex = "token=[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group();
        } else {
            return null; // or throw an exception if you prefer
        }
    }

}
