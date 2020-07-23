package framework.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static framework.base.TestBase.getDriver;
import static java.util.Collections.emptyList;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.testng.FileAssert.fail;

/**
 * This is base page which covers all the common methods that are declared once
 * and used in all the other classes
 */

public class BasePageMethods {

    protected WebDriver driver = getDriver();
    private WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
    private JavascriptExecutor jsExec = (JavascriptExecutor) driver;
    //XLUtils xlread = new XLUtils(); // can be used when we get data from testdata file
    public Logger log = Logger.getLogger("rootLogger");

    // Element click method by By locator
    public void clickWebElement(By locator) {
        WebElement element = waitUntilVisibleByLocator(locator);
        scrollTo(element,100);
        waitUntilClickableByListOfElement(element).click();
    }

    //wait element until visible
    public WebElement waitUntilVisibleByLocator(By locator) {
        WebElement element = null;
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(60))
                    .pollingEvery(Duration.ofMillis(100))
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class);
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Throwable t) {
            Assert.fail("waitUntilVisibleByLocator fail", t);
        }
        return element;
    }

    //waits till the element is present and fails if element not present
    protected WebElement waitUntilPresenceOfElementByLocator(By locator) {
        WebElement element = null;

        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofMillis(100))
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class);
            element = wait.until(presenceOfElementLocated(locator));
        } catch (Throwable t) {
            Assert.fail("waitUntilPresenceOfElementByLocator fail", t);
        }
        return element;
    }

/*
    //wait for search results
    protected void waitForSearchResults(By locator) {
        try {
            new WebDriverWait(driver, 30)
                    .until(ExpectedConditions
                    .numberOfElementsToBeMoreThan(locator, 0));
        } catch (TimeoutException e) {
            e.printStackTrace();
            Assert.fail("Timeout: The element couldn't be found in " + WAIT_MEDIUM + " seconds!");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Something went wrong");
        }
    }
*/

    // scroll to particular element
    protected void scrollTo(WebElement element, int margin) {
        scrollTo(element.getLocation().x, element.getLocation().y - margin);
    }

    // scroll to particular element based on co-ordinates
    protected By scrollTo(int x, int y) {
        jsExec.executeScript("scrollTo(" + x + "," + y + ");");
        return null;
    }

    // check if element is present without giving any error
    public boolean isElementPresent(By by, int timeOutInSeconds) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeOutInSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NotFoundException.class)
                .ignoring(NoSuchElementException.class);
        try {
            wait.until(presenceOfElementLocated(by));
            return true;
        } catch (TimeoutException t) {
            return false;
        }
    }

    // checks for visibility of elements
    protected List<WebElement> visibilityOfAllWait(By by, int timeOutInSeconds) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeOutInSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NotFoundException.class)
                .ignoring(NoSuchElementException.class);

        return wait.until(visibilityOfAllElementsLocatedBy(by));
    }

/*

    // checks for visibility of multiple elements
    protected List<WebElement> visibilitiesWaitNested(WebElement element, By by, int timeoutInSeconds) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NotFoundException.class)
                .ignoring(NoSuchElementException.class);
        return wait.until(visibilityOfAllElementsLocatedByIn(by, element));
    }
*/


    protected static ExpectedCondition<List<WebElement>> visibilityOfAllElementsLocatedByIn
            (final By locator, final WebElement parent) {
        return new ExpectedCondition<List<WebElement>>() {
            @Override
            public List<WebElement> apply(WebDriver driver) {
                List<WebElement> elements;
                if (parent != null) {
                    elements = parent.findElements(locator);
                } else {
                    elements = driver.findElements(locator);
                }
                for (WebElement element : elements) {
                    if (!element.isDisplayed()) {
                        return emptyList();
                    }
                }
                return !elements.isEmpty() ? elements : null;
            }

            @Override
            public String toString() {
                return "visibility of all elements located by " + locator;
            }
        };
    }
/*

    // wait for element till it gets the attribute value
    protected void waitForElementToGetAttribute(int seconds, By elementLocator, String attribute, String value) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(attributeContains(elementLocator, attribute, value));
    }
*/

    // press enter by sendkeys
    protected void pressEnter() {
        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.ENTER).build().perform();
    }

    // mouse hover to identify elements
    public WebElement mouseHover(By locator) {
        WebElement element = null;
        try {
            element = driver.findElement(locator);
            Actions builder = new Actions(driver);
            builder.moveToElement(element).build().perform();
        } catch (Throwable t) {
            Assert.fail("Couldn't find element", t);
        }
        return element;
    }

    // waits for elements untill it is visible and clickable
    public WebElement waitUntilClickableByLocator(By locator) {
        WebElement element = null;
        try {
            Wait<WebDriver> wait = new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofMillis(100))
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class);
            element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            Assert.fail("Element is not clickable", e);
        }

        return element;
    }

/*
    // wait for visibility of element and throws error if element no visible
    public WebElement waitUntilVisibleByLocatorSafely(By locator, int time) {
        WebElement element = null;
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(time))
                    .pollingEvery(Duration.ofMillis(100))
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class);
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            Assert.fail("Element is not visible", e);
        }
        return element;
    }
*/

    protected WebElement waitUntilClickableByListOfElement(WebElement webElement) {
        WebElement element = null;
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofMillis(100))
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class);
            element = wait.until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (Exception e) {
            Assert.fail("Webelement is not clickable", e);
        }

        return element;
    }

    protected boolean waitUntilUrlContains(String expectedValue) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(30))
                    .withTimeout(Duration.ofMillis(100))
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class);
            boolean urlExists = wait.until(ExpectedConditions.urlContains(expectedValue));
            if (urlExists) {
                log.info("Waited until for URL and contains expected value: " + expectedValue);
            }
        } catch (Exception e) {
            fail("Waited until for URL and NOT contains expected value.");
            Assert.fail("Waited until for URL and NOT contains expected value.", e);
            return false;
        }
        return true;
    }

    // sendkeys to provide input to text fields
    public void passArgument (By locator, String text)
    {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    // dropdown selection after passing value and saving the selected value
    public String selectDropdownByValueAndIndex(By locator, String inputText, String valueToBeSelected)
    {
        //driver.findElement(locator).click();
        //passArgument(locator,inputText);


        List<WebElement> dpListValues=driver.findElements(locator);
        passArgument(locator,inputText);


        for (int i=0; i<dpListValues.size();i++)
        {
            if ((dpListValues.get(i).getText()).contains(valueToBeSelected))
            {
                dpListValues.get(i).click();
                break;
            }
        }


        //WebElement option = dropdown.getFirstSelectedOption();
        //dropdown.selectByIndex(index);
        String selectedItem = dpListValues.get(1).getText();
        return selectedItem;
    }

    // compares the value from element to the expected value
    public void textValidation(By locator, String value) {
        WebElement element = waitUntilVisibleByLocator(locator);
        try {
            if (element.getText() == value)
                log.info(element.getText() + " is equal to " + value);
        } catch (Exception e) {
            fail("Texts are not equal.");
            Assert.fail("Texts are not equal.", e);
        }
    }
}
