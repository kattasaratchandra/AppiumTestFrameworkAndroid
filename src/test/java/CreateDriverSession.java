import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.net.UrlChecker;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class CreateDriverSession {
    public static void main(String[] args) throws MalformedURLException {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "pixel");
        // have not included udid coz I am running only on one device so it will take automatically
        String urlPath = System.getProperty("user.dir") + File.separator + "src" + File.separator +
                "resources" + File.separator + "ApiDemos-debug.apk";
        desiredCapabilities.setCapability(MobileCapabilityType.APP, urlPath);
        // creating url object with the url of appium server which is started manually using appium cli
        URL url = new URL("http://0.0.0.0:4723");
        // creating driver session using url and desired capabilities
        AppiumDriver driver = new AndroidDriver(url, desiredCapabilities);



    }
}
