package naukri.configReader;

import java.io.FileInputStream;
import java.io.IOException;

public class CustomConfigReader extends ConfigReader{
    @Override
    protected ConfigReader readConfigProperties() {
        try {
            FileInputStream fileInputStream = new FileInputStream("custom/path/to/config.properties");
            getProperties().load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Custom loading failed", e);
        }
        return this;
    }

    @Override
    protected ConfigReader readUrlViaConfigJson() {
        try {
            FileInputStream fs = new FileInputStream("custom/path/to/environments.json");
            getProperties().load(fs);
            String url = getProperties().getProperty("BASE_URL");
            System.out.println("Custom URL Loaded: " + url);
        } catch (Exception e) {
            throw new RuntimeException("Custom file loading failed", e);
        }
        return this;
    }
}