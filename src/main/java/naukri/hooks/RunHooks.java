package naukri.hooks;

import naukri.businessLayer.User;
import naukri.configReader.ConfigReader;
import io.cucumber.java.*;
import kong.unirest.Unirest;
import naukri.utils.ContextDataStore;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;
import naukri.utils.CreateDriver;

import java.util.Properties;
import java.util.logging.Logger;

public class RunHooks {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(RunHooks.class);
    private CreateDriver createDriver;
    private WebDriver driver;
    private Properties properties;
    private ConfigReader configReader;
    private Logger logger = Logger.getLogger(String.valueOf(ConfigReader.class));
    private int i =0;

    @Before(order=-2)
    public void updateUnirest(Scenario sc) {
        Unirest.config().verifySsl(false);
    }

    @Before(order=-1)
    public void getProperty(Scenario sc) {
        logger.info("Current order is -1");
        logger.info("Triggering getProperty method");
        configReader = new ConfigReader();
        logger.info("Reading files");
        properties = configReader.init_properties();
        logger.info("Files has been reads successfully");
    }

    @Before(order=0) // we are not passing order here so it will be consider as zero
    public void beforeHooksMethod(Scenario sc) {
        logger.info("Current order is 0");
        String browserName = properties.getProperty("browser");
        logger.info("current browser is" + browserName);
        createDriver = new CreateDriver();
        logger.info("Created an object of createDriver class for "+ browserName + " window");
        driver = createDriver.instantiate_create_driver(browserName);
        logger.info(" Driver has been created. handle ID is" + driver.getWindowHandle());
    }

    @Before(order=1) // we are not passing order here so it will be consider as zero
    public void redirectToPortal() {
        String url = properties.getProperty("\"BASE_URL\"");
        logger.info("CURRENT URL IS "+ url);
        this.loadUserData();
        driver.get(url.substring(1,url.length()-1));
    }

    private void loadUserData() {
        String userId = properties.get("\"GMAIL_ID\"").toString();
        String password = properties.get("\"GMAIL_PASSWORD\"").toString();
        String name = properties.get("\"USER_NAME\"").toString();
        User gmailUser = new User(userId.substring(1,userId.length()-2),password.substring(1,password.length()-2),name.substring(1,name.length()-2));
        ContextDataStore.setData("gmailUser", gmailUser);
    }

    @After
    public void afterHooks() {
        driver.quit();
    }

    @After(order = 1)
    public void screenshot(Scenario sc) {
        if (sc.isFailed()) {
            // For jenkins this one is ideal approach
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] src = ts.getScreenshotAs(OutputType.BYTES);
            sc.attach(src,  "image/png", sc.getName());

//            Alternative approach to above one. if we don't have to save the images just need to add in portal then above one we can use
//            TakesScreenshot ts = (TakesScreenshot) driver;
//            File src = ts.getScreenshotAs(OutputType.FILE);
//            File newLocation = new File("AnotherPath");
//            Files.copy(src.toPath(), newLocation.toPath());

        }
    }

    //THis one will specifically for the scenario wher ethe below tag is
    @After("@endEventViaAPI")
    public void before1() {
        logger.info("This is After hooks for @endEventViaAPI tag");
        System.out.println("This is end Event via API before suite");
    }

    @Before("@endEventViaAPI")
    public void after1() {
        logger.info("This is Before hooks for @endEventViaAPI tag");
        System.out.println("This is end Event via API after suite");
    }

    @BeforeStep
    public void beforeStep() {
        logger.info("This is before step" + i++);
    }

    @AfterStep
    public void afterStep() {
        logger.info("This is After step"+ i++);
    }

    @BeforeStep("@shreyas")
    public void beforeStepForSpecificTag() {
        logger.info("This is before step Shreyas" + i++);
    }

    @AfterStep("@shreyas") //If we are passing the tag then only this step will execute. along with normal one step
    public void afterStepForSpecificTag() {
        logger.info("This is After step Shreyas"+ i++);
    }
}
