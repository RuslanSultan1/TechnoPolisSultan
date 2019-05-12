package Pages;

import Enums.Friends;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FriendsPage extends BasePage {
    public FriendsPage(WebDriver driver) {
        init(driver);
    }

    @FindBy(id = "mainContentLeftColumn")
    private WebElement leftColumn;
    @FindBy(id = "mainContentContentColumn")
    private WebElement mainContentColumn;
    @FindBy(css = ".user-grid-card")
    List<WebElement> friendCards;

    @Override
    public void check() {
        assertTrue(driver.getCurrentUrl().contains("friends"));
        assertTrue(navigationToolbar.isDisplayed());
        assertTrue(leftColumn.isDisplayed());
        assertTrue(mainContentColumn.isDisplayed());
    }

    public FriendsCard getFriendCard(Friends friend) {
        WebElement friendCard = driver.findElement(By.xpath("//a[@class='n-t bold'][text()='" +
                friend.toString() + "']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(friendCard).perform();
        assertEquals(friend.toString(), friendCard.getText());
        return new FriendsCard(driver);
    }

    public void openFriendPage(Friends friend) {
        WebElement friendCard = driver.findElement(By.xpath("//a[@class='n-t bold'][text()='" +
                friend.toString() + "']"));
        friendCard.click();
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
