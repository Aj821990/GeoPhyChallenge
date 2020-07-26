package Frontend.pages;

import framework.Utilities.Constants;
import framework.base.BasePageMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * This is the class where all valuation page objects and valuation
 * page related methods are defined. Any specific method which is
 * only related to valuation page should be defined here.
 */


public class ValuationRun extends BasePageMethods {

    /******* Page objects *******/
    By welcomeTitle = By.xpath("//span[contains(text(), 'Welcome')]");
    By welcomeTitleUser = By.xpath("//span[contains(text(), 'QA Geophy')]");
    By valuationExplanation = By.xpath("//*[@class='leading-tight']");
    By addressText = By.xpath("//*[@id='introjsAddressInputForm']");
    By addressInput = By.xpath("//*[@id='address_input']");
    By addressNotCorrectError = By.xpath("//span[contains(text(), 'Please re-enter the property address')]");
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
    By runValuationButton = By.xpath("//*[@id='introjsRunValuationButton']");
    By viewSampleReportLink = By.xpath("//*[@id='introjsSampleReport']blablabla");
    private String value;

    /******* Page objects *******/
    public void valuationElements()
    {
        waitUntilUrlContains("search");
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
        //recentSearchValidation();
    }

    public void valuationValidation(boolean NOI, String value) throws InterruptedException {
        valuationElements();
        textValidation(welcomeTitleUser,"QA Geophy");
        WebElement address = driver.findElement(addressInput);
        address.sendKeys(Constants.ADDRESS);
        waitUntilVisibleByLocator(addressInput);
        clickWebElement(addressInput);
        address.sendKeys(Keys.DOWN);

        if(NOI==true)
        {
            passArgument(netOperatingIncomeInput, value);
        }
        else
        {
            passArgument(unitSizeInput, value);
        }
        passArgument(numberOfUnitsInput, Constants.NumberOfUnits);
        passArgument(yearOfConstructionInput, Constants.YearOfConstruction);
        passArgument(occupancyInput,Constants.OCCUPANCY);
        clickWebElement(runValuationButton);
    }

    public void recentSearchValidation()
    {
        /* this can be carried out if we have proper
           database connection and query to figure
           the number of total searches, dates and other details
         */
        /* if it is first time user then there may not be
           any recent searches. in this case we can query the count
           for a specific user and if the count > 0 then call this
           function else skip it
         */
        /* i am not sure the total count of recent search shown on
           the screen. And also if it has any conditions like not
           more than 1 week old or so.
         */
    }
}
