package UI.tests;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import UI.pageObjects.LoginPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class TestBase {
    public AndroidDriver driver;
    LoginPage loginPage;

    public void initialiseDriver(String deviceName, String udid) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/Data.properties");
        prop.load(fis);

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("DeviceName", deviceName);
        cap.setCapability("udid", udid);
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "13");

        cap.setCapability("appPackage", prop.getProperty("appName"));
        cap.setCapability("appActivity", prop.getProperty("appActivity"));

        URL url = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(url, cap);
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"deviceName", "udid"})
    public void initApp(String deviceName, String udid) throws IOException {
        initialiseDriver(deviceName, udid);
        loginPage = new LoginPage(driver);
    }

}
