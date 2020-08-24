package framework.listener;

import com.aventstack.extentreports.MediaEntityBuilder;
import framework.utilities.ScreenshotTaker;
import framework.base.TestBase;
import framework.extentfactory.ReportFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static framework.extentfactory.ReportFactory.getChildTest;

/**
 * This is the Listener class which verifies the success or failure of tests
 *
 */

public class Listener extends TestBase implements ITestListener {

    public void onTestStart(ITestResult iTestResult) {
        log.info("I am on TestStart method " + getTestMethodName(iTestResult) + " start");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        log.info("I am on Test Success method " + getTestMethodName(iTestResult) + " succeed");
    }

    public void onTestFailure(ITestResult iTestResult) {
        log.info("I am on TestFailure method " + getTestMethodName(iTestResult) + " failed");
        WebDriver driver = getDriver();
        if (driver != null) {
            try {
                getChildTest().fail(iTestResult.getThrowable().getMessage(),
                        MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotTaker.addScreenshot()).build());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                driver.quit();
            }
        } else {
            getChildTest().fail(iTestResult.getThrowable().getCause());
        }
    }

    public void onTestSkipped(ITestResult iTestResult) {
        log.info("I am on TestSkipped method " + getTestMethodName(iTestResult) + " skipped");
        iTestResult.setStatus(ITestResult.SKIP);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }

    public void onStart(ITestContext iTestContext) {
        log.info("I am on Start method " + iTestContext.getName());
    }

    public void onFinish(ITestContext iTestContext) {
        log.info("I am on Finish method " + iTestContext.getName());
        ReportFactory.saveReport();
    }

    public static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
}
