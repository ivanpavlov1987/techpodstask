package UI.tests;

import UI.pageObjects.AppPage;
import UI.pageObjects.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UITests extends TestBase{

    @Test
    public void loginTest(){
        AppPage appPage = landingPage.clickAppLink();
        SearchPage searchPage= appPage.clickSearchLink()
                .clickInvokeSearchLink();

        Assert.assertEquals(searchPage.getPageTitleText(),"App/Search/Invoke Search");
    }
}
