package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject{

    private static final String
            SEARCH_SKIP_ELEMENT = "org.wikipedia.alpha:id/fragment_onboarding_skip_button",
            SEARCH_INIT_ELEMENT = "org.wikipedia.alpha:id/search_container",
            SEARCH_INPUT = "org.wikipedia.alpha:id/search_src_text",
            SEARCH_CLEARBUTTON = "org.wikipedia.alpha:id/search_close_btn",
            SEARCH_RESULT = "org.wikipedia.alpha:id/page_list_item_title",
            SEARCH_SUBTEXT = "page_list_item_description";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }
    @Step("Click skip button")
    public void initSkipIntroPopUp(){
        this.waitForElementAndClick(By.id(SEARCH_SKIP_ELEMENT),"Some error with skip button",5);
    }
    @Step("Click search element")
    public void initSearchInput(){
        this.waitForElementPresent(By.id(SEARCH_INIT_ELEMENT),"Search container does not found!",10);
        this.waitForElementAndClick(By.id(SEARCH_INIT_ELEMENT),"Search input does not found!",10);
    }
    @Step("Send {searchString} to seach element")
    public void typeSearchLine(String searchString){
        this.waitForElementAndSendKeys(By.id(SEARCH_INPUT),searchString,"Keys does not sent",15);
    }
    @Step("Wait results")
    public void waitForSearchResult(){
        this.waitForElementsPresent(By.id(SEARCH_RESULT),"Cannot find results",15);
        screenshot(this.takeScreenshot("Result list"));
    }

    public List<WebElement> waitForSearchResultandReturn(){
         return this.waitForElementsPresent(By.id(SEARCH_SUBTEXT),"Cannot find results",15);
    }
    @Step("Check search line")
    public void checkSearchLine(String defualtString){
        this.assertElementHasText(By.id(SEARCH_INPUT),defualtString);
    }
    @Step("Click clear button")
    public void pressClearButton(){
        this.waitForElementAndClick(By.id(SEARCH_CLEARBUTTON),"Clear button does not found!",10);
    }
    @Step("Check clear button dissapear")
    public void checkClearButtonIsDissapear(){
        this.waitForElementNotPresent(SEARCH_CLEARBUTTON,"Clear button still on screen!",15);
    }



}
