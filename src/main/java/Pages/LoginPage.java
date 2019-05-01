package Pages;

import Enums.LoginInfo;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @Override
    boolean check() {
        return false;
    }

    @FindBy(xpath = ".//input[@id='field_email']")
    WebElement field_email;
    @FindBy(xpath = ".//input[@id='field_password']")
    WebElement field_password;

    @FindBy(xpath = ".//input[@value='Войти']")
    WebElement submitButton;

    public void login(LoginInfo login, LoginInfo password) {
        field_email.clear();
        field_email.sendKeys(login.toString());
        field_password.clear();
        field_password.sendKeys(password.toString());
        submitButton.click();
    }
}