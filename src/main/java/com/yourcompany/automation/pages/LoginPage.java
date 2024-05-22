package com.yourcompany.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    // Define locators
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By submitButton = By.id("submit");
    private By successMessageLocator = By.className("post-title");
    private By errorMessageLocator = By.xpath("//div[@id='error' and contains(@class, 'show')]");

    // Constructor to initialize the driver
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to enter username
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    // Method to enter password
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    // Method to click submit button
    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    // Method to perform login
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSubmit();
    }

    public String getSuccessMessage() {
        try {
            WebElement successElement = driver.findElement(By.className("post-title"));
            return successElement.getText();
        } catch (NoSuchElementException e) {
            return "Element not found. CSS Selector: .post-title";
        }
    }
    public boolean isSuccessMessagePresent() {
        try {
            // Attempt to find the success message element
            driver.findElement(successMessageLocator);
            return true; // Element found, login was successful
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false; // Element not found, login was unsuccessful
        }
    }

    public String getErrorMessageText() {
        WebElement errorMessageElement = driver.findElement(errorMessageLocator);
        return errorMessageElement.getText();
    }

    // Method to check if error message element is displayed
    public boolean isErrorMessageDisplayed() {
        try {
            return driver.findElement(errorMessageLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
