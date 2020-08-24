package framework.base;

import framework.extentfactory.ReportFactory;
import framework.listener.Listener;
import framework.utilities.Config;
import framework.utilities.DriverManager;
import framework.utilities.ReTryTestCase;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import static framework.base.Browsers.prepareDriver;
import static framework.extentfactory.ReportFactory.createReportFile;

/**
 * This is test base where testNG annotation sequence are defined and
 * which controls the flow of scripts
 */

@Listeners({Listener.class})
public class TestBase {

    static String browserName = null;
    static String gridurl = null;
    static String baseurl = null;
    static String remote = null;
    static String gridname = null;
    public static int retry;
    String testNameFromXML = null;
    public static final Logger log = Logger.getLogger("rootLogger");

    private static Properties loadPropertyFile(String filePath) throws IOException {
        Properties properties = new Properties();
        File file = new File(filePath);
        InputStream inputStream = new FileInputStream(file);
        properties.load(inputStream);

        return properties;
    }

    public static void initializeConfig(int reTry,String browser, String gridUrl) throws IOException {

        Properties properties = loadPropertyFile(System.getProperty("user.dir") + "/src/main/java/resources/config.properties");

        browserName = browser;
        gridurl = gridUrl;
        retry = reTry;
        baseurl = Config.getInstance().getBaseUrl();
        remote = properties.getProperty("remote");
    }

    @Parameters(value = {"reTry", "browser", "gridUrl"})
    @BeforeSuite
    public void beforeSuite(ITestContext context,
                            @Optional int reTry,
                            @Optional String browser,
                            @Optional String gridUrl) throws IOException {

        initializeConfig(reTry, browser, gridUrl);
        log.info("before creating report");
        createReportFile();
        log.info("after creating report");

        for(ITestNGMethod method : context.getSuite().getAllMethods()) {
            method.setRetryAnalyzer(new ReTryTestCase());
        }
    }

    @BeforeClass
    public void beforeClass() {
        org.apache.log4j.PropertyConfigurator.configure("log4j.properties");
        testNameFromXML = this.getClass().getName();
        ReportFactory.createTest(testNameFromXML);
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        DriverManager.setDriver(prepareDriver());
        getDriver().navigate().to(baseurl);
        ReportFactory.createChildTest(testNameFromXML, method.getName());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        getDriver().quit();
        IRetryAnalyzer retryAnalyzer = result.getMethod().getRetryAnalyzer();
        if (retryAnalyzer == null) {
            return;
        }
        result.getTestContext().getSkippedTests().removeResult(result.getMethod());
    }

    public synchronized static WebDriver getDriver()
    {
        return DriverManager.getDriver();
    }
}