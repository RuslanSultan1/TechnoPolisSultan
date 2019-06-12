package Pages;

import Enums.LoginInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static Enums.LoginInfo.LOGIN_PAGE_URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    void check() {
        assertTrue("field_email",isElementDisplayed(field_email));
        assertTrue("field_password",isElementDisplayed(field_password));
        assertTrue("submitButton",isElementDisplayed(submitButton));
        assertEquals(LOGIN_PAGE_URL.toString(),driver.getCurrentUrl());
    }

    @FindBy(xpath = "//input[@id='field_email']")
    WebElement field_email;
    @FindBy(xpath = "//input[@id='field_password']")
    WebElement field_password;
    @FindBy(xpath = "//input[@value='Войти']")
    WebElement submitButton;

    public void login(LoginInfo login, LoginInfo password) {
        assertTrue("field_email",isElementDisplayed(field_email));
        field_email.clear();
        field_email.sendKeys(login.toString());
        assertTrue("field_password",isElementDisplayed(field_password));
        field_password.clear();
        field_password.sendKeys(password.toString());
        assertTrue("submitButton",isElementDisplayed(submitButton));
        submitButton.click();
    }
}