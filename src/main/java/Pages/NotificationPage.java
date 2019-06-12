package Pages;

import Enums.Friends;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NotificationPage extends BasePage {
    public NotificationPage(WebDriver driver) {
        super(driver);
    }

    private static final By closeButton = By.xpath(".//button[@class='button-pro __sec __small']");
    private static final String NOTIFICATIONS_PAGE_URL = "https://ok.ru/notifications";
    private static final String NOTIFICATIONS_PART = " указал, что вы";

    @FindBy(xpath = "//form[contains(@id,'confirmn')]")
    List<WebElement> notifications;
    @FindBy(id = "ntf_layer_content_inner")
    WebElement content;
    @FindBy(css = ".notifs_cnt .toolbar-layer_menu")
    WebElement notifToolbar;

    @Override
    void check() {
        assertTrue(isElementDisplayed(navigationToolbar));
//        assertTrue(isElementDisplayed(content));
        assertTrue(isElementDisplayed(notifToolbar));
        assertEquals(NOTIFICATIONS_PAGE_URL, driver.getCurrentUrl());
    }

    public void declineRelations(Friends friend) {
        for (WebElement notification : notifications) {
            if (notification.getText().contains(friend.toString() + NOTIFICATIONS_PART)) {
                notification.findElement(closeButton).click();
            }
        }
    }

}
