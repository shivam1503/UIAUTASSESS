package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.ConfigReader;
import java.time.Duration;


public class BaseTest {
    public static WebDriver driver;

    public void setup() {
        String browser = ConfigReader.getProperty("browser");
        boolean headless = ConfigReader.getBooleanProperty("headless");
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                if (headless) options.addArguments("--headless");
                driver = new ChromeDriver(options);
                WebDriverManager.chromedriver().setup();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                WebDriverManager.firefoxdriver().setup();
                break;
            case "edge":
                driver = new EdgeDriver();
                WebDriverManager.edgedriver().setup();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        System.out.println(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfigReader.getProperty("app.url"));
    }

    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
