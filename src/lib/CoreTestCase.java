package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class CoreTestCase {
    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:9999/wd/hub";

    @Before
    @Step("Run driver and session")
    public void setUp() throws MalformedURLException {

        //super.setUp();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "AndroidTestDevice");
        capabilities.setCapability("appium:platformVersion", "16.0");
        capabilities.setCapability("appium:automationName", "Appium");
        capabilities.setCapability("appium:appPackage", "org.wikipedia.alpha");
        capabilities.setCapability("appium:appActivity", "org.wikipedia.DefaultIcon");
        capabilities.setCapability("appium:app", "C:/IdeaProjects/MobileAuto/apks/app-alpha-universal-release.apk");

        driver = new AndroidDriver(new URL(AppiumURL),capabilities);
    }
    @After
    @Step("Stop driver and session")
    public void tearDown() {

        driver.quit();
        //super.tearDown();
    }
}
