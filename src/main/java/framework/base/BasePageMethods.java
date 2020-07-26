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
            Assert.fail("waitUntilPresenceOfElementByLocator" +locator +"fail", t);
        }
        return element;
    }


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

    //
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

    // compares the value from element to the expected value
    public void textValidation(By locator, String value) {
        WebElement element = driver.findElement(locator);
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(30))
                    .withTimeout(Duration.ofMillis(100))
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class);
            boolean matchText = wait.until(ExpectedConditions.textToBePresentInElementLocated(locator,value));
            if (matchText) {
                log.info("Texts are matching.");
            }
        } catch (Exception e) {
            fail("Mismatch in texts");
            Assert.fail("Mismatch in texts", e);
        }
    }
}
