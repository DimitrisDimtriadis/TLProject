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

public class RegisterTest extends InstrumentationTestCase
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
    public void testRegister() throws Exception
    {
        waitForObject(By.res("com.example.johnywalker.adventure_go:id/decor_content_parent"));

        UiObject2 registerInButton = device.findObject(By.text("Register"));
        registerInButton.click();

        //Username
        UiObject2 userRegister = waitForObject(By.res("com.example.johnywalker.adventure_go:id/username"));
        userRegister.setText("Dimitris");
        //Email
        UiObject2 emailRegister = waitForObject(By.res("com.example.johnywalker.adventure_go:id/email"));
        emailRegister.setText("UserMail");
        //Password
        UiObject2 passwordRegister = waitForObject(By.res("com.example.johnywalker.adventure_go:id/password"));
        passwordRegister.setText("123456");

        //TODO add register complete widnow and check if it will appear
        //registerInButton.click();
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

