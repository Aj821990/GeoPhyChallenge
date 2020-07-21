package Frontend.pages;

import framework.base.BasePageMethods;
import org.openqa.selenium.By;

public class ValuationRun extends BasePageMethods {

    /******* Page objects *******/
    By addressText = By.xpath("//*[@id='introjsAddressInputForm']");
    By addressInput = By.xpath("//*[@id='address_input']");
    By netOperatingIncomeText = By.xpath("//*[@id='introjsNoiInput']");
    By netOperatingIncomeDollarSign = By.xpath("//p[contains(text(), '$')]");
    By netOperatingIncomeInput = By.xpath("//*[@id='noi']");
    By numberOfUnitsText = By.xpath("//*[@id='introjsNumberOfUnits']");
    By numberOfUnitsInput = By.xpath("//*[@name='number_of_units']");
    By unitSizeText = By.xpath("//*[@id='introjsUnitSize']");
    By unitSizeInput = By.xpath("//*[@name='unit_size']");
    By yearOfConstructionText = By.xpath("//*[@id='introjsYearOfConstruction']");
    By yearOfConstructionInput = By.xpath("//*[@name='year_built']");
    By occupanceText = By.xpath("//*[@for='occupancy']");
    By occupancyInput = By.xpath("//*[@name='occupancy']");
    By welcomeTitle = By.xpath("//span[contains(text(), 'Welcome')]");
    By welcomeTitleUser = By.xpath("//span[contains(text(), 'QA Geophy')]");
    By valuationExplanation = By.xpath("//*[@class='leading-tight']");


    /******* Page objects *******/
    public void valuationElements()
    {
        isElementPresent(addressText,1);
        isElementPresent(addressInput,1);
        isElementPresent(netOperatingIncomeText,1);
        isElementPresent(netOperatingIncomeDollarSign,1);
        isElementPresent(netOperatingIncomeInput,1);
        isElementPresent(numberOfUnitsText,1);
        isElementPresent(numberOfUnitsInput,1);
        isElementPresent(unitSizeText,1);
        isElementPresent(unitSizeInput,1);
        isElementPresent(yearOfConstructionText,1);
        isElementPresent(yearOfConstructionInput,1);
        isElementPresent(occupanceText,1);
        isElementPresent(occupancyInput,1);
        isElementPresent(welcomeTitle,1);
        isElementPresent(welcomeTitleUser,1);
        isElementPresent(valuationExplanation,1);
    }

    public void valuationValidation()
    {
        
    }
}
