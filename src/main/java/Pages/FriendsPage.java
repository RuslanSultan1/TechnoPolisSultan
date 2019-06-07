package Pages;

import Enums.Friends;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static Enums.AssertsTexts.FRIENDS_NOT_FOUND_TEXT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FriendsPage extends BasePage {
    public FriendsPage(WebDriver driver) {
        super(driver);
    }

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

    @Override
    public void check() {
        assertTrue(driver.getCurrentUrl().contains("friends"));
        assertTrue(isElementDisplayed(navigationToolbar));
        assertTrue(isElementDisplayed(leftColumn));
        assertTrue(isElementDisplayed(mainContentColumn));
    }

    public void searchFriend(String name) {
        assertTrue(isElementDisplayed(searchLine));
        searchLine.clear();
        searchLine.sendKeys(name);
    }

    public void assertFriendsSearchResult() {
        try {
            assertTrue(friendsFound.isDisplayed());
        } catch (NoSuchElementException e) {
            assertTrue(friendsNotFound.isDisplayed());
            assertEquals(FRIENDS_NOT_FOUND_TEXT.toString(), friendsNotFound.getText());
        }
    }

    public boolean isFriendFound() {
        try {
            return friendsFound.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public FriendsCard getFriendCard(Friends friend) {
//        WebElement friendCard = driver.findElement(By.xpath("//a[@class='n-t bold'][text()='" +
//                friend.toString() + "']"));
        for (WebElement friendCard : friendCardList) {
            if (friendCard.getText().contains(friend.toString())) {
                return new FriendsCard(driver, friendCard);
            }
        }
        throw new NoSuchElementException("FriendCard wasn't found");
    }
//    public FriendsCard getFriendCard(Friends friend) {
//        for (WebElement friendCard : friendCards) {
//            if (friendCard.getText().contains(friend.toString())) {
//                Actions actions = new Actions(driver);
//                actions.moveToElement(friendCard).perform();
//                return new FriendsCard(driver);
//            }
//        }
//        throw new NoSuchElementException();
//    }
}
