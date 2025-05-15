package pages.authorization;

import framework.elements.Button;
import framework.elements.TextBox;
import framework.forms.BaseForm;
import org.openqa.selenium.By;

public class AuthorizationPage extends BaseForm {
    private final String LOGIN_TXT_BOX = "//input[@name='login']";
    private final String PASSWORD_TXT_BOX = "//input[@name='password']";
    private static final String LOGIN_BTN = "//button[@type='submit']";

    public AuthorizationPage() {
        super(new Button(By.xpath(LOGIN_BTN), "'Login' button"), "Authorization page");
    }

    public AuthorizationPage inputLoginTxtBox(String login) {
        getLoginTxtBox().inputText(login);
        return this;
    }

    public AuthorizationPage inputPasswordTxtBox(String password) {
        getPasswordTxtBox().inputText(password);
        return this;
    }

    public void clickLoginBtn() {
        getLoginBtn().click();
    }

    private TextBox getLoginTxtBox() {
        return new TextBox(By.xpath(LOGIN_TXT_BOX), "'Login' text box");
    }

    private TextBox getPasswordTxtBox() {
        return new TextBox(By.xpath(PASSWORD_TXT_BOX), "'Password' text box");
    }

    private Button getLoginBtn() {
        return new Button(By.xpath(LOGIN_BTN), "'Login' button");
    }
}
