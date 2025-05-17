package steps.authorization;

import org.testng.Assert;
import pages.authorization.AuthorizationPage;

public class AuthorizationPageSteps {
    private static final AuthorizationPage authorizationPage = new AuthorizationPage();

    private AuthorizationPageSteps() {
    }

    public static void authorizationLoginPassword(String login, String password) {
        authorizationPage
                .inputLoginTxtBox(login)
                .inputPasswordTxtBox(password)
                .clickLoginBtn();
    }

    public static void assertIsOpen() {
        Assert.assertTrue(authorizationPage.isFormOpen(), authorizationPage.getFormName().concat(" isn't open"));
    }
}
