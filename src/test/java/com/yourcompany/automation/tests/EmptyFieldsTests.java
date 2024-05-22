package com.yourcompany.automation.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.yourcompany.automation.pages.LoginPage;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.yourcompany.automation.utils.ExtentReportListener.getTest;
import static com.yourcompany.automation.utils.TestUtils.captureScreenShot;
import static org.testng.Assert.assertEquals;

public class EmptyFieldsTests extends BaseTest {

    @Test
    public void TC04_testEmptyUsername() throws IOException {
        ExtentTest test = getTest();
        // Navigate to the login page
        driver.get("https://practicetestautomation.com/practice-test-login/");
        test.info("Verify that the user cannot log in using invalid username and correct password");
        String base64Code = captureScreenShot(driver);
        test.addScreenCaptureFromBase64String(base64Code, "Landing Page");

        // Verify that the login page is loaded
        test.info("Navigated to the login page: https://practicetestautomation.com/practice-test-login/");

        // Perform login
        LoginPage loginPage = new LoginPage(driver);
        test.info("Entering username: ''");
        test.info("Entering password: 'Password123'");
        loginPage.login("", "Password123");

        String path = captureScreenShot(driver, "Login_");
        // Check if the success message is displayed
        if (loginPage.isSuccessMessagePresent()) {
            // If success message is present, fail the test
            test.fail("Unexpectedly logged in successfully with invalid credentials");
            test.fail(MediaEntityBuilder.createScreenCaptureFromPath(path, "Login Unsuccessful").build());
        } else if (loginPage.isErrorMessageDisplayed()) {
            String actualErrorMessage = loginPage.getErrorMessageText();
            String expectedErrorMessage = "Your username is invalid!";
            assertEquals(actualErrorMessage, expectedErrorMessage, "Invalid user error message doesn't match");

            test.pass("Login unsuccessful as expected for invalid credentials");
            test.pass(MediaEntityBuilder.createScreenCaptureFromPath(path, "Login Unsuccessful").build());
        }
    }

    @Test
    public void TC05_testEmptyPassword() throws IOException {
        ExtentTest test = getTest();
        // Navigate to the login page
        driver.get("https://practicetestautomation.com/practice-test-login/");
        test.info("Verify that the user cannot log in using invalid username and correct password");
        String base64Code = captureScreenShot(driver);
        test.addScreenCaptureFromBase64String(base64Code, "Landing Page");

        // Verify that the login page is loaded
        test.info("Navigated to the login page: https://practicetestautomation.com/practice-test-login/");

        // Perform login
        LoginPage loginPage = new LoginPage(driver);
        test.info("Entering username: student");
        test.info("Entering password: ''");
        loginPage.login("student", "");

        String path = captureScreenShot(driver, "Login_");
        // Check if the success message is displayed
        if (loginPage.isSuccessMessagePresent()) {
            // If success message is present, fail the test
            test.fail("Unexpectedly logged in successfully with invalid credentials");
            test.fail(MediaEntityBuilder.createScreenCaptureFromPath(path, "Login Unsuccessful").build());
        } else if (loginPage.isErrorMessageDisplayed()) {
            String actualErrorMessage = loginPage.getErrorMessageText();
            String expectedErrorMessage = "Your password is invalid!";
            assertEquals(actualErrorMessage, expectedErrorMessage, "Invalid user error message doesn't match");

            test.pass("Login unsuccessful as expected for invalid credentials");
            test.pass(MediaEntityBuilder.createScreenCaptureFromPath(path, "Login Unsuccessful").build());
        }
    }

    @Test
    public void TC07_verifyLoginAfterInvalidAttempt() {
        ExtentTest test = getTest();
        // Navigate to the login page
        driver.get("https://practicetestautomation.com/practice-test-login/");
        test.info("Verify that the login button remains disabled until both the username and password fields are filled.");
        String base64Code = captureScreenShot(driver);
        test.addScreenCaptureFromBase64String(base64Code, "Landing Page");

        // Verify that the login page is loaded
        test.info("Navigated to the login page: https://practicetestautomation.com/practice-test-login/");

        // Perform login
        LoginPage loginPage = new LoginPage(driver);
        test.info("Entering username: student");
        test.info("Entering password: ''");
        loginPage.login("student", "");

        String path = captureScreenShot(driver, "Login_");
        // Check if the success message is displayed

        if (loginPage.isSuccessMessagePresent()) {
            // If success message is present, fail the test
            test.fail("Unexpectedly logged in successfully with invalid credentials");
            test.fail(MediaEntityBuilder.createScreenCaptureFromPath(path, "Login Unsuccessful").build());
        } else if (loginPage.isErrorMessageDisplayed()) {
            // Perform login with correct credentials
            test.info(MediaEntityBuilder.createScreenCaptureFromPath(path,"Expected Error appeared").build());
            test.info("Entering Valid Username and Password");
            loginPage.login("student", "Password123");
            // Wait for a brief moment to allow page to refresh
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String newScreenshot = captureScreenShot(driver, "newScreenshot");
            // Check if the error message disappears
            if (!loginPage.isErrorMessageDisplayed()) {
                test.pass("Error message disappeared after correct login.");
                test.pass(MediaEntityBuilder.createScreenCaptureFromPath(newScreenshot).build());
            } else {
                test.fail("Error message did not disappear after correct login.");
                test.fail(MediaEntityBuilder.createScreenCaptureFromPath(newScreenshot, "Error Message Still Displayed").build());
            }
         } else {
            test.fail("Error message was not displayed during the failed login attempt.");
            test.fail(MediaEntityBuilder.createScreenCaptureFromPath(path, "Error Message Not Displayed").build());
        }
    }
}
