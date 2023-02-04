/***
 * created naive test cases using testng. will optimise one by one from scratch
 */


package com.mobile;

import com.beust.ah.A;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Tests {

    AppiumDriver driver;
    WebDriverWait wait;

    public WebElement wait(WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitClickable(WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "pixel");
        desiredCapabilities.setCapability("appium:appPackage", "com.saucelabs.mydemoapp.rn");
        desiredCapabilities.setCapability("appium:appActivity", ".MainActivity");
        // creating url object with the url of appium server which is started manually using appium cli
        URL url = new URL("http://0.0.0.0:4723");
        // creating driver session using url and desired capabilities
        driver = new AndroidDriver(url, desiredCapabilities);

    }

    @Test(priority = 1)
    public void invalidUsername() throws InterruptedException {

        WebElement menuButton = wait(driver.findElement(AppiumBy.accessibilityId("open menu")));
        menuButton.click();
        WebElement loginButton = wait(driver.findElement(AppiumBy.accessibilityId("menu item log in")));
        loginButton.click();
        WebElement usernameField = wait(driver.findElement(AppiumBy.accessibilityId("Username input field")));
        usernameField.sendKeys("invalidUser");
        WebElement passwordField = wait(driver.findElement(AppiumBy.accessibilityId("Password input field")));
        passwordField.sendKeys("10203040");
        WebElement submitButton = wait(driver.findElement(AppiumBy.accessibilityId("Login button")));
        submitButton.click();
        WebElement errorText = wait(driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[" +
                "@content-desc=\"generic-error-message\"]/android.widget.TextView")));
        String expectedText = errorText.getAttribute("text");
        String actualText = "Provided credentials do not match any user in this service.";
        Assert.assertEquals(actualText, expectedText);

    }

    @Test(priority = 2)
    public void invalidPassword() {
        WebElement usernameField = driver.findElement(AppiumBy.accessibilityId("Username input field"));
        usernameField.clear();
        usernameField.sendKeys("bob@example.com");
        WebElement passwordField = wait(driver.findElement(AppiumBy.accessibilityId("Password input field")));
        passwordField.clear();
        passwordField.sendKeys("10203040n");
        WebElement submitButton = wait(driver.findElement(AppiumBy.accessibilityId("Login button")));
        submitButton.click();
        WebElement errorText = wait(driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[" +
                "@content-desc=\"generic-error-message\"]/android.widget.TextView")));

        String expectedText = errorText.getAttribute("text");
        String actualText = "Provided credentials do not match any user in this service.";
        Assert.assertEquals(actualText, expectedText);
    }

    @Test(priority = 3)
    public void validUsernameAndPassword() {

        WebElement usernameField = wait(driver.findElement(AppiumBy.accessibilityId("Username input field")));
        usernameField.clear();
        usernameField.sendKeys("bob@example.com");
        WebElement passwordField = wait(driver.findElement(AppiumBy.accessibilityId("Password input field")));
        passwordField.clear();
        passwordField.sendKeys("10203040");
        WebElement submitButton = wait(driver.findElement(AppiumBy.accessibilityId("Login button")));
        submitButton.click();
        WebElement productsHeader = wait(driver.findElement(AppiumBy.accessibilityId("container header")));
        WebElement menuButton = wait(driver.findElement(AppiumBy.accessibilityId("open menu")));
        menuButton.click();
        WebElement loginButton = wait(driver.findElement(AppiumBy.accessibilityId("menu item log in")));
        loginButton.click();
        WebElement goShoppingButton = wait(driver.findElement(AppiumBy.accessibilityId("Go Shopping button")));
        Assert.assertTrue(goShoppingButton.isDisplayed());

    }

    @AfterClass
    public void afterClass() {
        System.out.println("logging out");
        logOut();
        System.out.println("quitting the driver");
        driver.quit();
    }

    public void logOut() {
        wait(driver.findElement(AppiumBy.accessibilityId("open menu"))).click();
        wait(driver.findElement(AppiumBy.accessibilityId("menu item log out"))).click();
        wait(driver.findElement(AppiumBy.id("android:id/button1"))).click();
        wait(driver.findElement(AppiumBy.xpath("//*[@text='You are successfully logged out.']")));
        wait(driver.findElement(AppiumBy.id("android:id/button1"))).click();

    }
}
