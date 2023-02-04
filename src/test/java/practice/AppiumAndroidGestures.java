package practice;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

public class AppiumAndroidGestures {

    static WebDriverWait wait;
    static AppiumDriver driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        driver = CreateDriverSession.initializeDriver();
        longClickGesture(driver);
    }

    public static WebElement wait(WebElement element){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return  wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitClickable(WebElement element){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return  wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void longClickGesture(AppiumDriver driver) throws InterruptedException {
        WebElement viewsButton = wait(driver.findElement(AppiumBy.accessibilityId("Views")));
        // using click gesture
        clickGesture(driver, viewsButton);
        wait(driver.findElement(AppiumBy.accessibilityId("Drag and Drop"))).click();
        // double click gesture using java script
        // used thread.sleep will optimise later
        WebElement element = waitClickable(driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1")));
        // here using id as accessibility id is not therre for the element.
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "duration", 2000
                //"x", 209, "y", 544a
        ));
    }

    public static void clickGesture(AppiumDriver driver, WebElement element){
        // Java
        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId()
        ));
    }


}
