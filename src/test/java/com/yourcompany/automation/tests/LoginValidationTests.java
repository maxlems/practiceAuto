package com.yourcompany.automation.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.yourcompany.automation.pages.LoginPage;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.yourcompany.automation.utils.ExtentReportListener.getTest;
import static com.yourcompany.automation.utils.TestUtils.captureScreenShot;
import static org.testng.Assert.assertEquals;

public class LoginValidationTests extends BaseTest {
    @Test
    public void TC01_testValidLogin() throws IOException {
        ExtentTest test = getTest();
        // Navigate to the login page
        driver.get("https://practicetestautomation.com/practice-test-login/");
        test.info("Verify that the user can log in with valid credentials");
        test.info("Navigated to the login page: https://practicetestautomation.com/practice-test-login/");

        // Perform login
        LoginPage loginPage = new LoginPage(driver);
        test.info("Entering username: student");
        test.info("Entering password: Password123");
        loginPage.login("student", "Password123");

        // Check if login was successful
        if (loginPage.isSuccessMessagePresent()) {
            // Verify login success message
            String successMessage = loginPage.getSuccessMessage();
            test.info("Verifying login success message");

            String path = captureScreenShot(driver, "Login_");

            if (successMessage.equals("Logged In Successfully")) {
                test.pass("Login successful: " + successMessage);
            } else {
                test.fail("Login failed: expected 'Logged In Successfully' but got '" + successMessage + "'");
            }
            test.pass(MediaEntityBuilder.createScreenCaptureFromPath(path, "Login Successful").build());

            // Example of including an assertion in the test case
            assertEquals(successMessage, "Logged In Successfully", "Incorrect login success message");
        } else {
            // Login was unsuccessful, handle accordingly
            test.fail("Login unsuccessful: Error message displayed");
            String path = captureScreenShot(driver, "Login_Unsuccessful_");
            test.pass(MediaEntityBuilder.createScreenCaptureFromPath(path, "Login Unsuccessful").build());
            // Additional steps to handle unsuccessful login
        }
    }

    @Test
    public void TC02_testInvalidUsername() throws IOException {
        // Navigate to the login page
        driver.get("https://practicetestautomation.com/practice-test-login/");
        ExtentTest test = getTest();
        test.info("Verify that the user cannot log in with an incorrect username but a correct password.");
        String base64Code = captureScreenShot(driver);
        test.addScreenCaptureFromBase64String(base64Code, "Landing Page");

        // Verify that the login page is loaded
        test.info("Navigated to the login page: https://practicetestautomation.com/practice-test-login/");

        // Perform login
        LoginPage loginPage = new LoginPage(driver);
        test.info("Entering username: invalidstudent");
        test.info("Entering password: 'Password123'");
        loginPage.login("invalidstudent", "Password123");

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
    public void TC03_testInvalidPassword() throws IOException {
        // Navigate to the login page
        driver.get("https://practicetestautomation.com/practice-test-login/");
        ExtentTest test = getTest();
        test.info("Verify that the user cannot log in with an incorrect password but a correct username.");
        String base64Code = captureScreenShot(driver);
        test.addScreenCaptureFromBase64String(base64Code, "Landing Page");

        // Verify that the login page is loaded
        test.info("Navigated to the login page: https://practicetestautomation.com/practice-test-login/");

        // Perform login
        LoginPage loginPage = new LoginPage(driver);
        test.info("Entering username: student");
        test.info("Entering password: 'InvalidPassword'");
        loginPage.login("student", "InvalidPassword");

        String path = captureScreenShot(driver, "Login_");
        if (loginPage.isSuccessMessagePresent()) {
            // If success message is present, fail the test
            test.fail("Unexpectedly logged in successfully with invalid credentials");
            test.fail(MediaEntityBuilder.createScreenCaptureFromPath(path, "Login Unsuccessful").build());
        } else if (loginPage.isErrorMessageDisplayed()) {
            String actualErrorMessage = loginPage.getErrorMessageText();
            String expectedErrorMessage = "Your password is invalid!";
            assertEquals(actualErrorMessage, expectedErrorMessage, "Invalid password error message doesn't match");

            test.pass("Login unsuccessful as expected for invalid credentials");
            test.pass(MediaEntityBuilder.createScreenCaptureFromPath(path, "Login Unsuccessful").build());
        }
    }
}
