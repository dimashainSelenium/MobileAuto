package src;

import lib.*;
import org.junit.Test;
import org.openqa.selenium.By;

public class Test_Ex8 extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception{
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testEx2(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSkipIntroPopUp();
        searchPageObject.initSearchInput();
        searchPageObject.checkSearchLine("Search Wikipedia");
    }
    @Test
    public void testEx3(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSkipIntroPopUp();
        searchPageObject.initSearchInput();
        searchPageObject.checkSearchLine("Search Wikipedia");
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult();
        searchPageObject.pressClearButton();
        searchPageObject.checkClearButtonIsDissapear();
    }

    @Test
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
