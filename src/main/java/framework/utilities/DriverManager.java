package framework.utilities;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private DriverManager() {
    }

    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static WebDriver getDriver()
    {
        return driverThread.get();
    }

    public static void setDriver(WebDriver driver)
    {
        driverThread.set(driver);
    }

    public static void deleteDriver()
    {
        driverThread.remove();
    }
}
