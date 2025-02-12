package base;

import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.PageObjects;
import pages.PageObjectsTest2;
import utils.ConfigReader;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import utils.ExtentReportManager;

import static java.sql.DriverManager.getDriver;

public class BaseTest {
    public static WebDriver driver;
    //private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

//    public static WebDriver getDriver() {
//        return driver.get();  // This will return the WebDriver instance for the current thread
//    }

    public void setup() {
        String browser = ConfigReader.getProperty("browser");
        boolean headless = ConfigReader.getBooleanProperty("headless");
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                if (headless) options.addArguments("--headless");
                driver = new ChromeDriver(options);
                WebDriverManager.chromedriver().setup();
                //driver.set(new ChromeDriver(options));
                break;
            case "firefox":
                driver = new FirefoxDriver();
                WebDriverManager.firefoxdriver().setup();  // Set up FirefoxDriver
                //driver.set(new FirefoxDriver());
                break;
            case "edge":
                driver = new EdgeDriver();
                WebDriverManager.edgedriver().setup();  // Set up EdgeDriver
                //driver.set(new EdgeDriver());
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        System.out.println(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.get(ConfigReader.getProperty("app.url"));

        //initializePageObjects();
    }

    public void captureScreenshot(WebDriver driver, String screenshotName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotPath = "target/ExtentReports/screenshots/" + screenshotName + ".png";
            FileUtils.copyFile(srcFile, new File(screenshotPath));
            ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void teardown() {
        if (driver != null) {
            driver.quit();
            //driver.remove();  // Ensure session is reset
        }
    }

//    public void initializePageObjects() {
//        pgObj = new PageObjects(driver);
//        pgObj2 = new PageObjectsTest2(driver);
//    }
}
