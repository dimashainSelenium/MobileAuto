package src;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class Test_Ex2_Ex3 {
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
        capabilities.setCapability("appium:app", "C:/IdeaProjects/MobileAuto/apks/app-alpha-universal-release.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:9999/wd/hub"),capabilities);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void TestEx2(){
        waitForElementAndClick(By.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button"),"Some error with skip button",5);
        waitForElementAndClick(By.id("org.wikipedia.alpha:id/search_container"),"Search container does not found!",15);
        assertElementHasText(By.id("org.wikipedia.alpha:id/search_src_text"),"Search Wikipedia");
    }
    @Test
    public void TestEx3(){
        waitForElementAndClick(By.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button"),"Some error with skip button",5);
        waitForElementAndClick(By.id("org.wikipedia.alpha:id/search_container"),"Search container does not found!",15);
        waitForElementAndSendKeys(By.id("org.wikipedia.alpha:id/search_src_text"),"Java","Keys does not sent",15);
        assertCountElement(waitForElementsPresent(By.id("org.wikipedia.alpha:id/page_list_item_title"),"Do not found enaff elements",15),1);
        waitForElementAndClick(By.id("org.wikipedia.alpha:id/search_close_btn"),"Close button does not found!",15);
        waitForElementNotPresent("org.wikipedia.alpha:id/search_close_btn","Result still on screen!",15);
    }

    public WebElement waitForElementPresent(By by, String error_message, long timeOutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSec));
        wait.withMessage(error_message + "\n");
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
        return element;
    }

    public WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message,5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(String id, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public List<WebElement> waitForElementsPresent(By by, String error_message, long timeOutInSec){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSec));
        wait.withMessage(error_message + "\n");
        List<WebElement> elements = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
        return elements;
    }

    public void assertElementHasText(By by, String text){
        Assert.assertEquals(text,waitForElementPresent(by,text + " is not present").getText());
    }

    public void assertCountElement(List<WebElement> elements, int count){
        Assert.assertTrue("Is not provide " + count + " elements",elements.size()>=count);
    }

}
