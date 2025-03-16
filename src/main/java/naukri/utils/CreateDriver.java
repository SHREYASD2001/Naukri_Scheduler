package naukri.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

public class CreateDriver {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(CreateDriver.class);
    public WebDriver driver;
    private final Logger logger = Logger.getLogger(String.valueOf(CreateDriver.class));
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();


    public WebDriver instantiate_create_driver(String browserName) {
        logger.info("inside the instantiate_create_driver Checking and creating the browser");
        switch (browserName.toLowerCase()) {
            case "chrome":
                logger.info("Current browserName is chrome.");
                logger.info("Creating chrome browser");
//                WebDriverManager.chromedriver().setup();
                logger.info("Completed initial setup of chrome");
                logger.info("Created Chrome instance");
                tlDriver.set(new ChromeDriver());
                break;

            case "firefox":
                logger.info("Current browserName is firefox.");
                logger.info("Creating firefox browser");
                WebDriverManager.firefoxdriver().setup();
                logger.info("Completed initial setup of firefox");
                tlDriver.set(new FirefoxDriver());
                logger.info("Created firefox instance");
                break;

            case "safari":
                // SafariDriver comes pre-installed on macOS, no WebDriverManager setup needed
                tlDriver.set(new SafariDriver());
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                tlDriver.set(new EdgeDriver());
                //This is deprecated in current release
//                WebDriver driver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
        logger.info("Created "+browserName+" instance");
        tlDriver.get().manage().window().maximize();
        return this.getDriver();
    }

    public synchronized WebDriver getDriver() {
        return tlDriver.get();
    }
}
