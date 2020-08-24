package framework.base;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.TestException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static framework.base.TestBase.*;
import static org.openqa.selenium.remote.CapabilityType.*;

/**
 * This call is used to configure driver and browsers.
 *
 * @return
 */


public class Browsers {

    private Browsers() {
    }

    public static WebDriver prepareDriver() {
        WebDriver driver;

        if(remote == null || remote.equals("false")) {
            loadBrowsers(); // i have added only chrome driver path for now. other browser path can be added based on requirements
            driver = getLocalDriver();
        } else {
            driver = runRemote(getGridUrl());
        }

        if(System.getProperty("os.name").toLowerCase().contains("mac")) {
            driver.manage().window().setSize(new Dimension(1680, 1050)); // this was set to make sure the script runs in mac also
        } else if(!browserName.equalsIgnoreCase("mobileweb")) {
            driver.manage().window().maximize(); // have not configured mobileweb.
        }

        return driver;
    }

    public static WebDriver runRemote(String gridUrl) {
        WebDriver webDriver;
        DesiredCapabilities cap = getRemoteCapabilities();
        cap.setCapability(APPLICATION_NAME, gridname);
        try {
            log.info(gridUrl);
            webDriver = new RemoteWebDriver(new URL(gridUrl), cap);
            ((RemoteWebDriver) webDriver).setFileDetector(new LocalFileDetector());
        } catch (MalformedURLException mue) {
            throw new TestException(gridUrl);
        }

        return webDriver;
    }

    private static DesiredCapabilities getRemoteCapabilities() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();

        Proxy proxy = new Proxy();
        proxy.setProxyAutoconfigUrl("https://google.com");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.addArguments("-disable-cache");
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--disable-dev-shm-usage");

        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        capabilities.setCapability(CapabilityType.PROXY, proxy);

        return capabilities;
    }

    private static WebDriver getLocalDriver() {
        switch (browserName.toLowerCase()) {
            default:
            case "chrome":
                return new ChromeDriver();
            case "firefox":
                return new FirefoxDriver(); // not configured
        }
    }

    private static String getGridUrl() {
        log.info("getting the gridurl");
        if(gridname != null &&
                (gridname.equals("node0") || gridname.equals("node1") || gridname.equals("node2"))) {
            return "http://10.214.111.107:80/wd/hub";
        }
        return gridurl;
    }

    private static void loadBrowsers() {
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("win")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/resources/chromedriver.exe");
        }
        else if(os.contains("mac")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/resources/chromedriver");
        }
    }
}
