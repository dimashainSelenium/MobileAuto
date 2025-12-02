
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class FirstTestEx1 {
    private AppiumDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "AndroidTestDevice");
        capabilities.setCapability("appium:platformVersion", "16.0");
        capabilities.setCapability("appium:automationName", "Appium");
        capabilities.setCapability("appium:appPackage", "org.wikipedia.alpha");
        capabilities.setCapability("appium:appActivity", "org.wikipedia.DefaultIcon");
        capabilities.setCapability("appium:app", "/Users/ruasido/Developer/MobileAuto/apks/app-alpha-universal-release.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:9999/wd/hub"),capabilities);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void firstTestEx1(){
        System.out.println("First test run!");
    }
}
