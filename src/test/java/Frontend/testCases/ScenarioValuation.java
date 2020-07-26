package Frontend.testCases;

import Frontend.pages.Login;
import Frontend.pages.ValuationRun;
import framework.Utilities.Constants;
import framework.base.TestBase;
import org.testng.annotations.Test;

public class ScenarioValuation extends TestBase {

    @Test(description = "Valuation scenario with noi")
    public void valuationWithNOI() throws InterruptedException {
        Login login = new Login();
        ValuationRun valuation = new ValuationRun();

        login.loginValidation(Constants.USER, Constants.PASSWORD);
        valuation.valuationValidation(true, Constants.NetOperatingIncome);
    }

    @Test(description = "valuation scenario without noi")
    public void valuationWithOutNOI() throws InterruptedException {
        Login login = new Login();
        ValuationRun valuation = new ValuationRun();

        login.loginValidation(Constants.USER, Constants.PASSWORD);
        valuation.valuationValidation(false, Constants.NetOperatingIncome);
    }
}
