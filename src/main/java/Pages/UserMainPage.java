package Pages;

import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserMainPage extends BasePage {
    private static final String MAIN_URL = "https://ok.ru/";

    @Override
    void check() {
        assertEquals(MAIN_URL.toString(), driver.getCurrentUrl());
        assertTrue(navigationToolbar.isDisplayed());
        assertTrue(middleColumn.isDisplayed());
        assertTrue(leftColumn.isDisplayed());
    }

    public UserMainPage(WebDriver driver) {
        super(driver);
    }
}
