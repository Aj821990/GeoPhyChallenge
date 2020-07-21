package Frontend.pages;

import framework.base.BasePageMethods;
import org.openqa.selenium.By;

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
    By errorForInvalidCredentials = By.xpath("//*[@class='alert alert--danger']");

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
        if (isElementPresent(errorForInvalidCredentials,1))
        {
            log.info("Credentials are incorrect");
        }
        else log.info("Login Successful");
    }
}
