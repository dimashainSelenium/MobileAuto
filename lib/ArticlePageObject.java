package lib;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
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

    private String[] articleTitle;
    private String[] articleSubText = new String[100];;
    private int size;
    List<WebElement> webElements;

    public void saveArticleText(SearchPageObject searchPageObject, int count){
        webElements = searchPageObject.waitForSearchResultandReturn();
        for (int i =0;i<count;i++){
           articleSubText[i] = webElements.get(i).getText();
       }
    }

    public String getArticleText(int i){
        return articleSubText[i];
    }

    public void clickOnArticleBySubText(String subText){
        this.waitForElementAndClick(By.xpath(getResultSearchElement(subText)),"Some error with click art",5);
    }

    public void clickOnArticleCloseButton(){
        this.tryWaitForElementAndClick(By.id(ARTICLE_CLOSE_BUTTON),"Some error with close button",5);
    }

    public void clickOnArticleSaveButton(){
        this.waitForElementAndClick(By.id(ARTICLE_SAVE_BUTTON),"Some error with save button",15);
    }

    public void clickOnArticleBackButton(){
        this.waitForElementAndClick(AppiumBy.accessibilityId(ARTICLE_BACK_BUTTON),"Some error with navigate button",15);
    }

    public void checkArticleText(int i){
        this.waitForElementPresent(By.xpath(getResultSearchElement(getArticleText(i))),"Article not same",5);
    }

    public String getResultSearchElement(String subText){
        return ARTICLE_LOCATOR_SUBTEXT.replace("{SUBSTRING}",subText);
    }

    public void assertTitlePresent(String substring){
        assertElementPresent(By.xpath(ARTICLE_TITLE_LOCATOR.replace("{SUBSTRING}",substring)),
                "Article title is not present");
    }
}
