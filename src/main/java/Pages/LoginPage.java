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
    public void check() {
        assertEquals(LOGIN_PAGE_URL.toString(),driver.getCurrentUrl());
        assertTrue(field_email.isDisplayed());
        assertTrue(field_password.isDisplayed());
        assertTrue(submitButton.isDisplayed());
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