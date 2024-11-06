package UI.pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AppPage extends PageBase{

    public AppPage(AppiumDriver driver) {
        super(driver);
    }

    private static final String searchLinkXpath = "//android.widget.TextView[@content-desc='Search']";
    public WebElement searchLink() {
        return driver.findElement(By.xpath(searchLinkXpath));
    }

    public AppPage clickSearchLink() {
        searchLink().click();
        return this;
    }

    private static final String invokeSearchLinkXpath = "//android.widget.TextView[@content-desc='Invoke Search']";
    public WebElement invokeSearchLink() {
        return driver.findElement(By.xpath(invokeSearchLinkXpath));
    }

    public SearchPage clickInvokeSearchLink() {
        invokeSearchLink().click();
        return new SearchPage(driver);
    }
}
