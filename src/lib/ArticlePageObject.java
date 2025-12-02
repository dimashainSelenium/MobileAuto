package lib;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ArticlePageObject extends MainPageObject{
    private static final String
            ARTICLE_LOCATOR_SUBTEXT = "//*[contains(@text,'{SUBSTRING}')]",
            ARTICLE_SAVE_BUTTON = "org.wikipedia.alpha:id/page_save",
            ARTICLE_BACK_BUTTON = "Navigate up",
            ARTICLE_CLOSE_BUTTON = "org.wikipedia.alpha:id/closeButton",
            ARTICLE_TITLE_LOCATOR = "//android.webkit.WebView//android.widget.TextView[@text='Java']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private String[] articleSubText = new String[100];;
    List<WebElement> webElements;

    @Step("Save article title")
    public void saveArticleText(SearchPageObject searchPageObject, int count){
        webElements = searchPageObject.waitForSearchResultandReturn();
        for (int i =0;i<count;i++){
           articleSubText[i] = webElements.get(i).getText();
       }
    }

    public String getArticleText(int i){
        return articleSubText[i];
    }
    @Step("Click on article")
    public void clickOnArticleBySubText(String subText){
        this.waitForElementAndClick(By.xpath(getResultSearchElement(subText)),"Some error with click art",5);
    }
    @Step("Close popUp")
    public void clickOnArticleCloseButton(){
        this.tryWaitForElementAndClick(By.id(ARTICLE_CLOSE_BUTTON),"Some error with close button",5);
    }
    @Step("Save article to favorite")
    public void clickOnArticleSaveButton(){
        this.waitForElementAndClick(By.id(ARTICLE_SAVE_BUTTON),"Some error with save button",15);
    }
    @Step("Click back")
    public void clickOnArticleBackButton(){
        this.waitForElementAndClick(AppiumBy.accessibilityId(ARTICLE_BACK_BUTTON),"Some error with navigate button",15);
    }
    @Step("Check article text")
    public void checkArticleText(int i){
        this.waitForElementPresent(By.xpath(getResultSearchElement(getArticleText(i))),"Article not same",5);
    }

    public String getResultSearchElement(String subText){
        return ARTICLE_LOCATOR_SUBTEXT.replace("{SUBSTRING}",subText);
    }
    @Step("Check article title")
    public void assertTitlePresent(String substring){
        assertElementPresent(By.xpath(ARTICLE_TITLE_LOCATOR.replace("{SUBSTRING}",substring)),
                "Article title is not present");
    }
}
