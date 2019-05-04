package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserMainPage extends BasePage {
    @Override
    public void check() {
        throw new UnsupportedOperationException();
    }

    @FindBy(xpath = ".//div[@class='tico_txt'][text()='Друзья']")
    WebElement friends;

    public void openFriendsPage() {
        friends.click();
    }
}
