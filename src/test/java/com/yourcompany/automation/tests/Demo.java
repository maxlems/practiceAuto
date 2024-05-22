//package com.yourcompany.automation.tests;
//
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import org.openqa.selenium.*;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import static com.yourcompany.automation.utils.TestUtils.captureScreenShot;
//import com.aventstack.extentreports.MediaEntityBuilder;
//
//import java.io.IOException;
//
//public class Demo extends BaseTest {
//
//    @Test
//    public void Testcase001() throws IOException {
//        driver.get("https://www.google.com");
//
//        ExtentTest test = extent.createTest("Verify the pagetitle").assignAuthor("wel").assignCategory("functional test").assignDevice("Windows");
//        test.info("i am capturing the page title");
//        String pagetitle = driver.getTitle();
//        String base64Code = captureScreenShot(driver);
//        String path = captureScreenShot(driver, "Google_");
//        System.out.println(path);
//
//        test.info("capture page title is : " + pagetitle);
//        test.addScreenCaptureFromBase64String(base64Code, "Google home");
//        extent
//                .createTest("screenshot 2", "this is for attaching screen")
//                .info("This is info message")
//                .addScreenCaptureFromPath(path);
//        test.addScreenCaptureFromPath(path);
//
//        if (pagetitle.equals("Google")) {
//            test.pass("Page title is verified : title captured " + pagetitle);
//            test.log(Status.PASS, pagetitle);
//            test.fail(MediaEntityBuilder.createScreenCaptureFromPath(path, "Google home").build());
//        } else {
//            test.fail("Page title is not matched with expected result " + pagetitle);
//            test.log(Status.FAIL, "FAQs button clicked");
//        }
//    }
//}