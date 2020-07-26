package Frontend.pages;

import framework.Utilities.Constants;
import framework.base.BasePageMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    By saveReoprtSectionMain = By.xpath("//a[contains(@class, 'print:hidden button')]");
    By exportToCSVMain = By.xpath("//*[@id='downloadCSVLink']");
    By exportToPDF = By.xpath("//*[@type='button' and contains(@class,'print:hidden button')]");
    By linkEditInputDataFields = By.xpath("//a[contains(@href,'edit')]");
    By inputFieldsTableCols = By.xpath("//table[@class='table table-striped']/tr/td");
    By numberOfUnitsText = By.xpath("//td[text()='Number of units']");
    By averageUnitSizeText= By.xpath("//td[text()='Average unit size']");
    By yearOfConstructionText = By.xpath("//td[text()='Year of construction']");
    By NOIText = By.xpath("//td[text()='NOI']");
    By NOIPerUnitText = By.xpath("//td[text()='NOI per unit']");
    By occupancyText = By.xpath("//td[contains(text(),'Occupancy')]");

    /******* Page objects *******/
    public void valuationReportElements(Boolean NOI)
    {
        waitUntilPresenceOfElementByLocator(valuationPriceAmount);
        waitUntilUrlContains("report");
        waitUntilPresenceOfElementByLocator(valuationPriceText);
        waitUntilPresenceOfElementByLocator(numberOfUnitsText);
        waitUntilPresenceOfElementByLocator(yearOfConstructionText);
        waitUntilPresenceOfElementByLocator(occupancyText);

        if(NOI == true)
        {
            waitUntilPresenceOfElementByLocator(capRatePercent);
            waitUntilPresenceOfElementByLocator(capRateText);
            waitUntilPresenceOfElementByLocator(saveReoprtSectionMain);
            waitUntilPresenceOfElementByLocator(exportToCSVMain);
            waitUntilPresenceOfElementByLocator(exportToPDF);
            waitUntilPresenceOfElementByLocator(linkEditInputDataFields);
            waitUntilPresenceOfElementByLocator(NOIText);
            waitUntilPresenceOfElementByLocator(NOIPerUnitText);
        }
        else
        {
            waitUntilPresenceOfElementByLocator(linkEditInputDataFields);
            waitUntilPresenceOfElementByLocator(averageUnitSizeText);
        }
    }

    public void valuationValuesValidation(Boolean NOI)
    {
        valuationReportElements(NOI);

        List<WebElement> cols = driver.findElements(inputFieldsTableCols);
        int colSize = cols.size();

        HashMap<Object, Object> tableValues = new HashMap<>();

        int j=1;
        for (int i=0; i<colSize; i++)
        {
            tableValues.put(cols.get(i).getText(),cols.get(j).getText());
            i=++i;
            j=j+2;
        }

        verifyValueInMap(tableValues,Constants.NumberOfUnits);
        verifyValueInMap(tableValues,Constants.OCCUPANCY);
        verifyValueInMap(tableValues,Constants.YearOfConstruction);
        if (NOI==true)
        verifyValueInMap(tableValues,Constants.NetOperatingIncome);
    }

    public boolean verifyValueInMap(Map<Object, Object> map1, String value)
    {
        if (map1 == null)
        {
            log.info(map1 + " Map is empty!");
            return false;
        }
        else
        {
            map1.containsValue(value);
            log.info(value + " is present on report screen.");
        }
        return true;
    }
}
