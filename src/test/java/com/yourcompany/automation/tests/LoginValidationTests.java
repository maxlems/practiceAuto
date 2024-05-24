package com.yourcompany.automation.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.yourcompany.automation.pages.LoginPage;
import com.yourcompany.automation.utils.DataProviderUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.io.IOException;

import static com.yourcompany.automation.utils.ExtentReportListener.getTest;
import static com.yourcompany.automation.utils.TestUtils.captureScreenShot;
import static org.testng.Assert.assertEquals;

public class LoginValidationTests extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = DataProviderUtils.class)
    @Parameters("browser")
    public void testLogin(String username, String password, boolean isSuccessExpected, String expectedMessage, String testDescription) throws IOException {
        ExtentTest test = getTest();

        // Navigate to the login page
        test.info(testDescription + " on " + Webbrowser);
        test.info("Navigate to the login page: https://practicetestautomation.com/practice-test-login/");
        driver.get("https://practicetestautomation.com/practice-test-login/");

        // Perform login
        LoginPage loginPage = new LoginPage(driver);
        test.info("Verify login with username: " + username + " and password: " + password);
        loginPage.login(username, password);

        if (isSuccessExpected) {
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

                assertEquals(successMessage, "Logged In Successfully", "Incorrect login success message");
            } else {
                // Login was unsuccessful, handle accordingly
                test.fail("Login unsuccessful: Error message displayed");
                String path = captureScreenShot(driver, "Login_Unsuccessful_");
                test.pass(MediaEntityBuilder.createScreenCaptureFromPath(path, "Login Unsuccessful").build());
            }
        } else {
            String path = captureScreenShot(driver, "Login_");

            if (loginPage.isErrorMessageDisplayed()) {
                String actualErrorMessage = loginPage.getErrorMessageText();
                test.info("Expected error message : " + expectedMessage);
                test.info("Actual error message : " + actualErrorMessage);
                try {
                    assertEquals(actualErrorMessage, expectedMessage, "Incorrect error message");
                } catch (AssertionError e) {
                    // Handle assertion error and provide a user-friendly message
                    test.fail("Login failed: " + e.getMessage());
                }

                test.pass("Login unsuccessful as expected: " + actualErrorMessage);
                test.pass(MediaEntityBuilder.createScreenCaptureFromPath(path, "Login Unsuccessful").build());

            } else {
                test.fail("Login successful: expected error message but it was not displayed");
                test.fail(MediaEntityBuilder.createScreenCaptureFromPath(path, "Login Unsuccessful").build());
            }
        }
    }
}
