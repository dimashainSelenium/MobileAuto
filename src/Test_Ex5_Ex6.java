package src;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class Test_Ex5_Ex6 {
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
    public void TestEx5(){
        int countOfArt = 2;
        String attributeName[] = new String[countOfArt];
        waitForElementAndClick(By.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button"),"Some error with skip button",5);
        waitForElementAndClick(By.id("org.wikipedia.alpha:id/search_container"),"Search container does not found!",15);
        waitForElementAndSendKeys(By.id("org.wikipedia.alpha:id/search_src_text"),"Java","Keys does not sent",15);
        for (int i=0;i<countOfArt;i++){
            attributeName[i] = waitForElementsPresent(By.id("org.wikipedia.alpha:id/page_list_item_description"),"",15).get(i).getText();
        }
        for (int i=0;i<countOfArt;i++){
            waitForElementAndClick(By.xpath("//*[contains(@text,'"+attributeName[i]+"')]"),"Some error with click art",5);
            tryWaitForElementAndClick(By.id("org.wikipedia.alpha:id/closeButton"),"Some error with close button",5);
            waitForElementAndClick(By.id("org.wikipedia.alpha:id/page_save"),"Some error with save button",15);
            waitForElementAndClick(AppiumBy.accessibilityId("Navigate up"),"Some error with navigate button",15);
        }
        waitForElementAndClick(AppiumBy.accessibilityId("Navigate up"),"Some error with navigate button",15);
        tryWaitForElementAndClick(By.id("android:id/button2"),"Some error with popup",5);
        waitForElementAndClick(By.id("org.wikipedia.alpha:id/nav_tab_reading_lists"),"Some error with favorite button",15);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Default list for your saved articles')]"),"Some error with favorite list",5);

        for (int i=0;i<countOfArt-1;i++){
            tryWaitForElementAndClick(By.id("org.wikipedia.alpha:id/buttonView"),"Some error with popup",5);
            swipeElementToLeft(By.xpath("//*[contains(@text,'"+attributeName[i]+"')]"),"Some error with click art");
        }
        waitForElementAndClick(By.xpath("//*[contains(@text,'"+attributeName[countOfArt-1]+"')]"),"Some error with favorite list",5);
        waitForElementsPresent(By.xpath("//*[contains(@text,'"+attributeName[countOfArt-1]+"')]"),"Art not same",5);
    }

    public void swipeElementToLeft(By by, String errorMessage) {
        WebElement element = waitForElementPresent(by, errorMessage, 15);

        int leftX   = element.getLocation().getX();
        int rightX  = leftX + element.getSize().getWidth();
        int upperY  = element.getLocation().getY();
        int lowerY  = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        int startX = rightX - 10;
        int endX   = leftX + 10;

        new TouchAction<>((PerformsTouchActions) driver)
                .press(PointOption.point(startX, middleY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                .moveTo(PointOption.point(endX, middleY))
                .release()
                .perform();
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

    private boolean tryWaitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        try {
            WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
            element.click();
            return true;
        }
        catch (TimeoutException| NoSuchElementException e){
            return false;
        }
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

    public String getElementAttribite(By by, String error, long timeOutInSec){
       return waitForElementPresent(by,error,timeOutInSec).getAttribute("text");
    }

    public void assertElementHasText(By by, String text){
        Assert.assertEquals(text,waitForElementPresent(by,text + " is not present").getText());
    }

    public void assertCountElement(List<WebElement> elements, int count){
        Assert.assertTrue("Is not provide " + count + " elements",elements.size()>=count);
    }

}
