package framework.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static framework.base.TestBase.retry;
import static framework.extentfactory.ReportFactory.setSuccessToChild;

/**
 * This class defines the number of retries which can be executed if the script fails
 */

public class ReTryTestCase implements IRetryAnalyzer {

    private int count = 0;

    private static int maxTry = retry; // retry is passed when running by terminal e.g: -DreTry=2

    public synchronized boolean retry(ITestResult result) {
        if ((!result.isSuccess()) && (count < maxTry)) {
            count++;
            result.setStatus(ITestResult.FAILURE);
            setSuccessToChild(false);
            return true;
        } else {
            result.setStatus(ITestResult.SUCCESS);
            return false;
        }
    }
}
