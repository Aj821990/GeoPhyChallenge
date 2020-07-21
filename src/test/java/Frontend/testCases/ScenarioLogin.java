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
        login.loginValidation("","");
        login.loginValidation("","abcd");
        login.loginValidation("abcd@xyz.com","");
        login.loginValidation("abcd@xyz.com", Constants.PASSWORD);
        login.loginValidation(Constants.USER, "abcd");
        login.loginValidation(Constants.USER,Constants.PASSWORD);
    }
}
