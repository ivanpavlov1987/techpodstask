package UI.pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class PageBase {
    AppiumDriver driver;

    public PageBase(AppiumDriver driver) {
        this.driver = driver;
    }

    private static final String pageTitleXpath =
            "//android.view.ViewGroup[@resource-id='android:id/action_bar']/android.widget.TextView";

    public String getPageTitleText() {
        return driver.findElement(By.xpath(pageTitleXpath)).getText();
    }
}
