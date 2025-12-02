package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class FavoritePageObject extends MainPageObject{
    private static final String
            FAVORITE_DEFUALT_LIST = "//*[contains(@text,'Default list for your saved articles')]",
            FAVORITE_POPUP_CLOSE_BUTTON = "org.wikipedia.alpha:id/buttonView",
            FAVORITE_SWIPE_LOCATOR = "//*[contains(@text,'{SUBSTRING}')]";
    public FavoritePageObject(AppiumDriver driver) {
        super(driver);
    }

    @Step("Click on defualt favorite list")
    public void favoriteClickOnDefualtList(){
        this.waitForElementAndClick(By.xpath(FAVORITE_DEFUALT_LIST),"Some error with favorite list",5);
    }
    @Step("Close popUp")
    public void favoritePopUpClose(){
        this.tryWaitForElementAndClick(By.id(FAVORITE_POPUP_CLOSE_BUTTON),"Some error with popup",5);
    }
    @Step("Delete from favorite list")
    public void favoriteDeleteDefualtList(ArticlePageObject articlePageObject, int i){
        this.swipeElementToLeft(By.xpath(getFavoriteSwipeLocator(articlePageObject.getArticleText(i))),"Some error with delete");
    }
    @Step("Click on article in favorite list")
    public void favoriteClickOnArticle(ArticlePageObject articlePageObject, int i){
        this.waitForElementAndClick(By.xpath(getFavoriteSwipeLocator(articlePageObject.getArticleText(i))),"Some error with click on article",5);
    }

    public String getFavoriteSwipeLocator(String subText){
        return FAVORITE_SWIPE_LOCATOR.replace("{SUBSTRING}",subText);
    }

}
