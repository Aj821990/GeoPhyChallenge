package Frontend.testCases;

import Frontend.pages.Login;
import Frontend.pages.ValuationRun;
import framework.Utilities.Constants;
import framework.base.TestBase;
import org.testng.annotations.Test;

public class ScenarioValuation extends TestBase {

    @Test(description = "Valuation scenario with noi")
    public void valuationWithNOI()
    {
        Login login = new Login();
        ValuationRun valuation = new ValuationRun();

        login.loginValidation(Constants.USER, Constants.PASSWORD);
        valuation.valuationValidation(true, 2000000);
    }

    @Test(description = "valuation scenario without noi")
    public void valuationWithOutNOI()
    {
        Login login = new Login();
        ValuationRun valuation = new ValuationRun();

        login.loginValidation(Constants.USER, Constants.PASSWORD);
        valuation.valuationValidation(false, 2000000);
    }
}
