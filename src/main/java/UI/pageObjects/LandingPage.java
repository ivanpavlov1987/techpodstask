package UI.pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LandingPage extends PageBase {

    public LandingPage(AppiumDriver driver) {
        super(driver);
    }

    private static final String appLinkXpath = "//android.widget.TextView[@content-desc='App']";
    public WebElement appLink() {
        return driver.findElement(By.xpath(appLinkXpath));
    }

    public AppPage clickAppLink() {
        appLink().click();
        return new AppPage(driver);
    }
}
