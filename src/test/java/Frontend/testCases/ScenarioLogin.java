package Frontend.testCases;

import Frontend.pages.Login;
import framework.Utilities.Constants;
import framework.base.BasePageMethods;
import org.testng.annotations.Test;

public class ScenarioLogin extends BasePageMethods {

    @Test(groups={"regression"},description = "LoginValidation")
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
