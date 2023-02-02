import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class CreateDriverSessionUsingOptions {
    public static void main(String[] args) throws MalformedURLException {
        String urlPath = System.getProperty("user.dir") + File.separator + "src" + File.separator +
                "resources" + File.separator + "ApiDemos-debug.apk";
        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options()
                // have not used automation name coz we using respective options class for that
                .setPlatformName("Android")
                .setDeviceName("pixel")
//                .setApp(urlPath)
                /*
                1. using app package and app activity to open particular page without installing
                2. when we install app it automatically retrieves app package and app activity and then opens
                the app sometime it fails, so we give along with app, app package and app activity
                */
                .setAppPackage("io.appium.android.apis")
                .setAppActivity(".accessibility.CustomViewAccessibilityActivity");

        URL url = new URL("http://0.0.0.0:4723");
        // creating driver session using url and desired capabilities
        AppiumDriver driver = new AndroidDriver(url, uiAutomator2Options);

    }
}
