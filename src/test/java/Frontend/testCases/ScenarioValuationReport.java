package Frontend.testCases;

import Frontend.pages.Login;
import Frontend.pages.ValuationReport;
import Frontend.pages.ValuationRun;
import framework.Utilities.Constants;
import framework.base.TestBase;
import org.testng.annotations.Test;

public class ScenarioValuationReport extends TestBase {

    @Test(description = "Valuation report scenarios")
    public void valuationReport()
    {
        Login login = new Login();
        ValuationRun valuation = new ValuationRun();
        ValuationReport valuationReport = new ValuationReport();

        login.loginValidation(Constants.USER, Constants.PASSWORD);
        valuation.valuationValidation(true, 2000000);
        valuationReport.valuationValuesValidation();
    }
}
