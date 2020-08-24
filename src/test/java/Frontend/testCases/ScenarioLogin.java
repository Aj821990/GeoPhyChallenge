package Frontend.testCases;

import Frontend.pages.Login;
import framework.utilities.Constants;
import framework.base.TestBase;
import org.testng.annotations.Test;

public class ScenarioLogin extends TestBase {

    @Test(description = "LoginValidation")
    public void loginValidation()
    {
        Login login = new Login();
        log.info("blank username and blank password");
        login.loginValidation("","");
        log.info("blank username and valid password");
        login.loginValidation("",Constants.PASSWORD);
        log.info("valid username and blank password");
        login.loginValidation(Constants.USER,"");
        log.info("invalid username and invalid password");
        login.loginValidation(Constants.INVALID_USER, Constants.INVALID_PASSWORD);
        log.info("invalid username and valid password");
        login.loginValidation(Constants.INVALID_USER, Constants.PASSWORD);
        log.info("valid username and invalid password");
        login.loginValidation(Constants.USER, Constants.INVALID_PASSWORD);
        log.info("valid username and valid password");
        login.loginValidation(Constants.USER,Constants.PASSWORD);
    }
}
