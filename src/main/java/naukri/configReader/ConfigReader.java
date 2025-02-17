package naukri.configReader;

import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class ConfigReader {

    // Encapsulation: private field with controlled access
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ConfigReader.class);
    private Properties properties;
    private Logger logger = Logger.getLogger(String.valueOf(ConfigReader.class));

    // Constructor to initialize properties object
    public ConfigReader() {
        this.properties = new Properties();
        logger.info("created properties variable");
        logger.info("Constructor has been called. means object of class has been created");
    }

    // Abstraction: Method to initialize properties (hides implementation details)
    public Properties init_properties() {
        logger.info("initiating the init_properties method");
        this
                .readConfigProperties()
                .readUrlViaConfigJson()
                .readTestDataJson();
        logger.info("Properties has been laoded");
        return properties;
    }

    // Method Overloading (Compile-Time Polymorphism): Different ways to initialize properties
    public Properties init_properties(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            getProperties().load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load properties from " + filePath, e);
        }
        return properties;
    }

    public Properties init_properties(String... filePaths) {
        for (String filePath : filePaths) {
            try {
                FileInputStream fileInputStream = new FileInputStream(filePath);
                getProperties().load(fileInputStream);
            } catch (IOException e) {
                throw new RuntimeException("Unable to load properties from " + filePath, e);
            }
        }
        return properties;
    }

    // Chaining methods for streamlined workflow
    protected ConfigReader readConfigProperties() {
        logger.info("Inside the readConfigProperties.");
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/config/config.properties");
            logger.info("Reading the config.properties.");
            properties.load(fileInputStream);
            logger.info("Loaded the properties from config.properties");
        } catch (Exception e) {
            e.printStackTrace(); //To debug and identify the exact location and sequence of events leading to an exception.
        }
        return this;
    }

    protected ConfigReader readUrlViaConfigJson() {
        logger.info("Inside the readUrlViaConfigJson.");
        try {
            FileInputStream fs = new FileInputStream("src/test/resources/config/environments.json");
            logger.info("Reading the environments.json.");
            properties.load(fs);
            logger.info("Loaded the properties from environments.json");
        } catch (Exception e) {
            throw new RuntimeException("Unable to upload the file"); //It'll throw the exception and execution will stops
        }
        return this;
    }

    private void readTestDataJson() {
        try {
            FileInputStream fs = new FileInputStream("src/test/resources/config/testData.json");
            properties.load((fs));
        } catch (Exception e) {
            System.out.println("Throws the exception");
        }

    }
    // Getter for properties (Encapsulation)
    public Properties getProperties() {
        logger.info("Calling getter method. This is getting used to access the private variable of any method");
        logger.info("properties are" + properties);
        return properties;
    }
}


//Theory
// Encapsulation   ->   in this class encapsulation occurs as we declared private/public method and variable in same class
// init_properties ->   this one wil hide the implementation details.
// Method Chaining ->	this.readConfigProperties().readUrlViaConfigJson(); for streamlined operations.