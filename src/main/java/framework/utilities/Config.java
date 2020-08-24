package framework.utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static framework.base.TestBase.log;

/**
 * This is the config class which configures the base instances for the process
 */

public class Config {

    private String baseUrl;
    private static Config instance;
    private static final Object lockInstance = new Object();
    private static Properties configuration = new Properties();

    private Config() throws IOException {
        loadConfigFileFromResource();
        baseUrl = getConfigValue("baseUrl");
    }

    public static Config getInstance() throws IOException {
        synchronized (lockInstance) {
            if (instance == null) {
                instance = new Config();
            }
        }
        return instance;
    }

    private void loadConfigFileFromResource() throws IOException {
        final InputStream in = this.getClass().getResourceAsStream("/resources/config.properties");

        if (in == null) {
            throw new FileNotFoundException("Resource file \"config.properties\" not found");
        }

        configuration.load(in);
        in.close();
    }

    private String getConfigValue(final String keyPath) {
        final String completeKeyName = keyPath;
        final String completeKeyValue = configuration.getProperty(completeKeyName);

        if (completeKeyValue == null) {
            throw new IllegalArgumentException("The requested " + completeKeyName + " could not be found");
        }
        return completeKeyValue;
    }

    public String getBaseUrl(){
        log.info("Returning base url * " + baseUrl + " * from config.java class");
        return baseUrl;
    }
}
