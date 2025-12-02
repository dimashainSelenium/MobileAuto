
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import lib.*;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class Test_Ex8 extends CoreTestCase {

    public void setUp() throws MalformedURLException {
        super.setUp();
    }

    @Test
    @DisplayName("Check searchline")
    @Description("Search defualt string in searchline")
    public void testEx2(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSkipIntroPopUp();
        searchPageObject.initSearchInput();
        searchPageObject.checkSearchLine("Search Wikipedia");
    }
    @Test
    @DisplayName("Check search clear")
    @Description("Open search and check after clear")
    public void testEx3(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSkipIntroPopUp();
        searchPageObject.initSearchInput();
        searchPageObject.checkSearchLine("Search Wikipedia");
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult();
        searchPageObject.pressClearButton();
        searchPageObject.checkClearButtonIsDissapear();
        //searchPageObject.takeScreenshot("Article");
    }

    @Test
    @DisplayName("Delete favorite article")
    @Description("Add some favorite articles and delete except one")
    public void testEx5(){
        int countOfArt = 2;
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        NavigationPageObject navigationPageObject = new NavigationPageObject(driver);
        FavoritePageObject favoritePageObject = new FavoritePageObject(driver);

        searchPageObject.initSkipIntroPopUp();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        articlePageObject.saveArticleText(searchPageObject,countOfArt);

        for (int i=0;i<countOfArt;i++){
            articlePageObject.clickOnArticleBySubText(articlePageObject.getArticleText(i));
            articlePageObject.clickOnArticleCloseButton();
            articlePageObject.clickOnArticleSaveButton();
            articlePageObject.clickOnArticleBackButton();
     }
        articlePageObject.clickOnArticleBackButton();
        navigationPageObject.clickOnNavigationCloseButton();
        navigationPageObject.clickOnTabRead();
        favoritePageObject.favoriteClickOnDefualtList();

        for (int i=0;i<countOfArt-1;i++){
            favoritePageObject.favoritePopUpClose();
            favoritePageObject.favoriteDeleteDefualtList(articlePageObject,i);
        }
        favoritePageObject.favoriteClickOnArticle(articlePageObject,countOfArt - 1);
        articlePageObject.checkArticleText(countOfArt - 1);
 }

    @Test
    @DisplayName("Check article title")
    @Description("Check tha title was present on page after open")
    public void testEx6(){
        int countOfArt = 1;

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        searchPageObject.initSkipIntroPopUp();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        articlePageObject.saveArticleText(searchPageObject,countOfArt);
        articlePageObject.clickOnArticleBySubText(articlePageObject.getArticleText(0));
        articlePageObject.assertTitlePresent("Java");
    }

}
