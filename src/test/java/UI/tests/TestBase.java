package UI.tests;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import UI.pageObjects.LandingPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class TestBase {
    public AndroidDriver driver;
    LandingPage landingPage;

    public void initialiseDriver(String deviceName, String udid) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/Data.properties");
        prop.load(fis);

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("appium:deviceName", deviceName);
        cap.setCapability("appium:udid", udid);
        cap.setCapability("appium:platformName", "Android");

        cap.setCapability("appium:appPackage", prop.getProperty("appPackage"));
        cap.setCapability("appium:appActivity", prop.getProperty("appActivity"));
        cap.setCapability("appium:automationName", "UiAutomator2");
        cap.setCapability("appium:noReset", true);

        URL url = new URL("http://127.0.0.1:4723");

        driver = new AndroidDriver(url, cap);
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"deviceName", "udid"})
    public void initApp(String deviceName, String udid) throws IOException {
        initialiseDriver(deviceName, udid);
        landingPage = new LandingPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
