package Pages;

import Enums.Friends;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FriendsPage extends BasePage {

    public FriendsPage(WebDriver driver) {
        init(driver);
    }

    @Override
    boolean check() {
        return false;
    }

    @FindBy(css = ".user-grid-card")
    List<WebElement> friendCards;

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
        public FriendsCard getFriendCard(Friends friend) {
            WebElement friendCard=driver.findElement(By.xpath("//a[@class='n-t bold'][contains(text(),'"+
                    friend.toString() +"')]"));
            Actions actions = new Actions(driver);
            actions.moveToElement(friendCard).perform();
            return new FriendsCard(driver);
    }


}
