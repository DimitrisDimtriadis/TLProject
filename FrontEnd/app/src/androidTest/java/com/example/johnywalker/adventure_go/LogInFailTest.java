package com.example.johnywalker.adventure_go;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Dimitriadis983 on 26-Nov-16.
 */

public class LogInFailTest extends InstrumentationTestCase
{
    private UiDevice device;

    @Before
    public void setUp() throws Exception
    {
        device = UiDevice.getInstance(getInstrumentation());
        device.pressHome();

        UiObject2 appsButton = device.findObject(By.desc("Apps"));
        appsButton.click();

        UiObject2 projectApp = device.findObject(By.desc("Adventure_GO"));
        projectApp.click();

    }
    @Test
    public void testLogIn() throws Exception
    {
        //Email
        UiObject2 emailSignIn = waitForObject(By.res("com.example.johnywalker.adventure_go:id/email"));
        emailSignIn.setText("WrongMail");
        //Password
        UiObject2 passwordSignIn = waitForObject(By.res("com.example.johnywalker.adventure_go:id/password"));
        passwordSignIn.setText("WrongPassword");

        UiObject2 signInButton = device.findObject(By.res("com.example.johnywalker.adventure_go:id/email_sign_in_button"));
        signInButton.click();

        //TODO check if it shows a window notification
        //waitForObject(By.res("android:id/content"));
        Thread.sleep(5000);
    }

    private UiObject2 waitForObject(BySelector selector) throws InterruptedException {
        UiObject2 object = null;
        int timeout = 30000;
        int delay = 1000;
        long time = System.currentTimeMillis();
        while (object == null) {
            object = device.findObject(selector);
            Thread.sleep(delay);
            if (System.currentTimeMillis() - timeout > time) {
                fail();
            }
        }
        return object;
    }
}

