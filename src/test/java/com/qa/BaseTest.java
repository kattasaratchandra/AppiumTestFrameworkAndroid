/***
 * 1. using base test to intialize the driver, quit the driver, intialize properties using before
 * and after tests
 * 2. using try catch block so that if driver is null throws exception immediately
 * 3. we kept device related capabilities in testng.xml as parameter such that in case if we want to run
 * in other devices as well we simply can add new test with different parameters.
 * 4. Have created common methods waitforvisibility, click, sendkeys and getAttribute
 * 5. In constructor we intialise the page factory using appium page decorator so that no need to initialise every
 * page as this will be super class for all the page objects
 */
package com.qa;

import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    protected static AppiumDriver driver;
    protected static Properties props;
    InputStream inputStream;
    WebDriverWait wait;

    public  BaseTest(){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Parameters({"PLATFORM_NAME","DEVICE_NAME" })
    @BeforeTest
    public void beforeTest(String platformName, String deviceName) throws IOException {
        try {
            props = new Properties();
            //since the file is in resources no need to give complete path if not use below
            // System.getProperty("user.dir") + src/test/resources/config.properties
            String propFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            props.load(inputStream);
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("androidAutomationName"));
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
//            String urlPath = System.getProperty("user.dir") + File.separator + "src" + File.separator +
//                    "test" + File.separator + "resources" + File.separator + "app" + File.separator
//                    + "Android-MyDemoAppRN.1.3.0.build-244.apk";
//            desiredCapabilities.setCapability(MobileCapabilityType.APP, urlPath);
            desiredCapabilities.setCapability("appium:appPackage", props.getProperty("androidAppPackage"));
            desiredCapabilities.setCapability("appium:appActivity", props.getProperty("androidActivity"));
            URL url = new URL(props.getProperty("androidUrl"));
            driver = new AndroidDriver(url, desiredCapabilities);
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void waitForVisibility(WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void click(WebElement element){
        waitForVisibility(element);
        element.click();
    }

    public void sendKeys(WebElement element, String text){
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    public String getAttribute(WebElement element, String Attribute){
        waitForVisibility(element);
        return element.getAttribute(Attribute);
    }

    public Boolean isElementPresent(WebElement element){
        waitForVisibility(element);
        return element.isDisplayed();
    }

    @AfterTest
    public void afterTest(){
        driver.quit();
    }
}
