package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class NavigationPageObject extends MainPageObject{
    private static final String
            NAVIGATION_TAB_READ = "org.wikipedia.alpha:id/nav_tab_reading_lists",
            NAVIGATION_CLOSE_BUTTON = "org.wikipedia.alpha:id/closeButton";
    public NavigationPageObject(AppiumDriver driver) {
        super(driver);
    }

    @Step("Close popUp")
    public void clickOnNavigationCloseButton(){
        this.tryWaitForElementAndClick(By.id(NAVIGATION_CLOSE_BUTTON),"Some error with popup",5);
    }
    @Step("Click on tap read")
    public void clickOnTabRead(){
        this.waitForElementAndClick(By.id(NAVIGATION_TAB_READ),"Some error with favorite button",15);
    }
}
