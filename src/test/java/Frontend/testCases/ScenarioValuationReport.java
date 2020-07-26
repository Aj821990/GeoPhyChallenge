package Frontend.testCases;

import Frontend.pages.Login;
import Frontend.pages.ValuationReport;
import Frontend.pages.ValuationRun;
import framework.Utilities.Constants;
import framework.base.TestBase;
import org.testng.annotations.Test;

public class ScenarioValuationReport extends TestBase {

    @Test(description = "Valuation report scenarios with NOI")
    public void valuationReportWithNOI() throws InterruptedException {
        Login login = new Login();
        ValuationRun valuation = new ValuationRun();
        ValuationReport valuationReport = new ValuationReport();

        login.loginValidation(Constants.USER, Constants.PASSWORD);
        valuation.valuationValidation(true, Constants.NetOperatingIncome);
        valuationReport.valuationValuesValidation(true);
    }

    @Test(description = "Valuation report scenarios without NOI")
    public void valuationReportWithoutNOI() throws InterruptedException {
        Login login = new Login();
        ValuationRun valuation = new ValuationRun();
        ValuationReport valuationReport = new ValuationReport();

        login.loginValidation(Constants.USER, Constants.PASSWORD);
        valuation.valuationValidation(false, Constants.NetOperatingIncome);
        valuationReport.valuationValuesValidation(false);
    }

}
