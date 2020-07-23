package Frontend.testCases;

import Frontend.pages.Login;
import framework.Utilities.Constants;
import framework.base.TestBase;
import org.testng.annotations.Test;

public class ScenarioLogin extends TestBase {

    @Test(description = "LoginValidation")
    public void loginValidation()
    {
        Login login = new Login();
        log.info("blank username and blank password");
        login.loginValidation("","");
        log.info("blank username and some password");
        login.loginValidation("","abcd");
        log.info("some username and blank password");
        login.loginValidation("abcd@xyz.com","");
        log.info("invalid username and valid password");
        login.loginValidation("abcd@xyz.com", Constants.PASSWORD);
        log.info("valid username and invalid password");
        login.loginValidation(Constants.USER, "abcd");
        log.info("valid username and valid password");
        login.loginValidation(Constants.USER,Constants.PASSWORD);
    }
}
