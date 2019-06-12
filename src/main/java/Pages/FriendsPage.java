package Pages;

import Enums.Friends;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class FriendsPage extends BasePage {

    @FindBy(id = "mainContentLeftColumn")
    private WebElement leftColumn;
    @FindBy(id = "mainContentContentColumn")
    private WebElement mainContentColumn;
    @FindBy(css = ".user-grid-card")
    List<WebElement> friendCards;
    @FindBy(id = "search")
    private WebElement searchLine;
    @FindBy(css = "#hook_Block_MyFriendsFriendSearchPagingB #searchResults")
    private WebElement friendsFound;
    @FindBy(css = ".stub-empty.__friends .stub-empty_t")
    private WebElement friendsNotFound;
    @FindBy(css = ".user-grid-card")
    private List<WebElement> friendCardList;

    public FriendsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    void check() {
        assertTrue("navigationToolbar", isElementDisplayed(navigationToolbar));
        assertTrue("leftColumn", isElementDisplayed(leftColumn));
        assertTrue("mainContentColumn", isElementDisplayed(mainContentColumn));
        assertTrue(driver.getCurrentUrl().contains("friends"));
    }

    public void searchFriend(String name) {
        assertTrue("searchLine", isElementDisplayed(searchLine));
        searchLine.clear();
        searchLine.sendKeys(name);
    }

    public boolean isFriendFound() {
        try {
            return friendsFound.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public FriendsCard getFriendCard(Friends friend) {
        for (WebElement friendCard : friendCardList) {
            if (friendCard.getText().contains(friend.toString())) {
                return new FriendsCard(driver, friendCard);
            }
        }
        throw new NoSuchElementException("FriendCard wasn't found");
    }
}
