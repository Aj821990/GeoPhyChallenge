package Frontend.pages;

import framework.Utilities.Constants;
import framework.base.BasePageMethods;
import org.openqa.selenium.By;

/**
 * This is the class where all valuation report page objects and valuation
 * report page related methods are defined. Any specific method which is
 * only related to valuation report page should be defined here.
 */

public class ValuationReport extends BasePageMethods {

    /******* Page objects *******/
    By valuationPriceAmount = By.xpath("//*[@id='transactionPrice']/div[1]/p");
    By valuationPriceText = By.xpath("//p[contains(text(),'GeoPhy Valuation ')]");
    By capRatePercent = By.xpath("//*[@id='transactionPrice']/div[2]/h1");
    By capRateText = By.xpath("//div[@id='transactionPrice']/div/p[contains(text(),'Cap Rate')]");
    By confiidenceIndicatorGreen = By.xpath("//*[@data-confidence-indicator='green']");
    By confiidenceIndicatorRed = By.xpath("//*[@data-confidence-indicator='red']");
    By saveReoprtSectionMain = By.xpath("//a[contains(@class, 'print:hidden button')]");
    By exportToCSVMain = By.xpath("//*[@id='downloadCSVLink']");
    By exportToPDF = By.xpath("//*[@type='button' and contains(@class,'print:hidden button')]");
    By linkEditInputDataFields = By.xpath("//a[contains(@href,'edit')]");
    By numberOfUnitsText = By.xpath("//td[text()='Number of units']");
    By numberOfUnitsValue = By.xpath("//table/tr[1]/td[2]");
    By averageUnitSizeText= By.xpath("//td[text()='Average unit size']");
    By averageUnitSizeValue= By.xpath("//table/tr[2]/td[2]");
    By yearOfConstructionText = By.xpath("//td[text()='Year of construction']");
    By yearOfConstructionValue = By.xpath("//table/tr[3]/td[2]");
    By NOIText = By.xpath("//td[text()='NOI']");
    By NOIValue = By.xpath("//table/tr[4]/td[2]");
    By NOIPerUnitText = By.xpath("//td[text()='NOI per unit']");
    By NOIPerUnitValue = By.xpath("//table/tr[5]/td[2]");
    By occupancyText = By.xpath("//td[contains(text(),'Occupancy')]");
    By occupancyValue = By.xpath("//table/tr[6]/td[2]");

    /******* Page objects *******/
    public void valuationReportElements()
    {
        waitUntilPresenceOfElementByLocator(valuationPriceAmount);
        waitUntilPresenceOfElementByLocator(valuationPriceText);
        if(isElementPresent(confiidenceIndicatorGreen,1))
        {
            waitUntilUrlContains("report");
            waitUntilPresenceOfElementByLocator(capRatePercent);
            waitUntilPresenceOfElementByLocator(capRateText);
            waitUntilPresenceOfElementByLocator(saveReoprtSectionMain);
            waitUntilPresenceOfElementByLocator(exportToCSVMain);
            waitUntilPresenceOfElementByLocator(exportToPDF);
            waitUntilPresenceOfElementByLocator(linkEditInputDataFields);
            waitUntilPresenceOfElementByLocator(numberOfUnitsText);
            waitUntilPresenceOfElementByLocator(numberOfUnitsValue);
            waitUntilPresenceOfElementByLocator(averageUnitSizeText);
            waitUntilPresenceOfElementByLocator(averageUnitSizeValue);
            waitUntilPresenceOfElementByLocator(yearOfConstructionText);
            waitUntilPresenceOfElementByLocator(yearOfConstructionValue);
            waitUntilPresenceOfElementByLocator(NOIText);
            waitUntilPresenceOfElementByLocator(NOIValue);
            waitUntilPresenceOfElementByLocator(NOIPerUnitText);
            waitUntilPresenceOfElementByLocator(NOIPerUnitValue);
            waitUntilPresenceOfElementByLocator(occupancyText);
            waitUntilPresenceOfElementByLocator(occupancyValue);
        }
        else if(isElementPresent(confiidenceIndicatorRed,1))
        {
            waitUntilPresenceOfElementByLocator(linkEditInputDataFields);
            waitUntilPresenceOfElementByLocator(numberOfUnitsText);
            waitUntilPresenceOfElementByLocator(numberOfUnitsValue);
            waitUntilPresenceOfElementByLocator(averageUnitSizeText);
            waitUntilPresenceOfElementByLocator(averageUnitSizeValue);
            waitUntilPresenceOfElementByLocator(yearOfConstructionText);
            waitUntilPresenceOfElementByLocator(yearOfConstructionValue);
        }
    }

    public void valuationValuesValidation()
    {
        valuationReportElements();
        textValidation(numberOfUnitsValue, Constants.NumberOfUnits);
        textValidation(yearOfConstructionValue, Constants.YearOfConstruction);
        textValidation(NOIValue, Constants.NetOperatingIncome);
        textValidation(occupancyValue, Constants.OCCUPANCY);
    }

}
