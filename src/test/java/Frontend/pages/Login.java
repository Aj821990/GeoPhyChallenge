package Frontend.pages;

import framework.Utilities.Constants;
import framework.base.BasePageMethods;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;

/**
 * This is the class where all login page objects and login page related
 * methods are defined. Any specific method which is only related to
 * login page should be defined here.
 */

public class Login extends BasePageMethods {

    /******* Page objects *******/
    By loginText = By.xpath("//h2[contains(text(), 'Login')]");
    By loginEmailID = By.xpath("//*[@id='email']");
    By loginPassword = By.xpath("//*[@id='password']");
    By loginPasswordShowIcon = By.xpath("//*[@id='password-icon']");
    By loginRememberMeCheckBox = By.xpath("//*[@class='checkmark']");
    By loginSubmitButton = By.xpath("//*[@type='submit']");
    By errorForEmptyCredentials = By.xpath("//*[@class='alert alert--error']");
    By errorForEmptyLoginEmailID = By.xpath("//li[contains(text(), 'email')]");
    By errorForEmptyLoginPassword = By.xpath("//li[contains(text(), 'password')]");
    By errorForInvalidID = By.xpath("//*[@class='alert alert--danger']");
    By errorForInvalidPassword = By.xpath("//*[@class='alert alert--warning']");

    /******* Page objects *******/
    public void loginElements(){
        waitUntilClickableByLocator(loginText);
        isElementPresent(loginText,1);
        waitUntilClickableByLocator(loginEmailID);
        waitUntilClickableByLocator(loginPassword);
        isElementPresent(loginPasswordShowIcon,1);
        isElementPresent(loginRememberMeCheckBox,1);
        waitUntilClickableByLocator(loginSubmitButton);
    }

    public void loginValidation(String userName, String password){
        loginElements();
        passArgument(loginEmailID, userName);
        passArgument(loginPassword, password);
        clickWebElement(loginRememberMeCheckBox);
        clickWebElement(loginSubmitButton);
        if (isElementPresent(errorForEmptyCredentials,1))
        {
            if(isElementPresent(errorForEmptyLoginEmailID,1))
            {
                log.info("Email id field is empty");
            }
            else log.info("Password field is empty");
        }
        else if (isElementPresent(errorForInvalidID,1))
        {
            log.info("Email id is incorrect");
        }
        else if (isElementPresent(errorForInvalidPassword,1))
        {
            log.info("Password is incorrect");
        }
        else {
            log.info("Login Successful");
        }
    }

    public void loginTimeOut() throws InterruptedException {
        loginValidation(Constants.USER, Constants.PASSWORD);
        /* after login in we wait for certain amount of time
           and refresh the page to confirm we are still logged
           in or not
         */
        TimeUnit.MINUTES.wait(10);
        driver.navigate().refresh();
    }

}
