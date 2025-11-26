package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver){
        this.driver = driver;
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

    public void assertElementPresent(By by, String errorMessage) {
        List<WebElement> elements = driver.findElements(by);
        if (elements.isEmpty()) {
            Assert.fail(errorMessage);
        }
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

    public WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public boolean tryWaitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        try {
            WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
            element.click();
            return true;
        }
        catch (TimeoutException | NoSuchElementException e){
            return false;
        }
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String id, String error_message, long timeoutInSeconds) {
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
