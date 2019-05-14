package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static Enums.AssertsTexts.MAIN_URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserMainPage extends BasePage {
    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void check() {
        assertEquals(MAIN_URL.toString(), driver.getCurrentUrl());
        assertTrue(navigationToolbar.isDisplayed());
        assertTrue(middleColumn.isDisplayed());
        assertTrue(leftColumn.isDisplayed());
    }
}
