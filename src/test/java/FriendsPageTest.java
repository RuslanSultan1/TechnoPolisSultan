import Bases.TestBase;
import Enums.Relations;
import Pages.*;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

import static Enums.Friends.*;
import static Enums.LoginInfo.*;
import static Enums.Pages.FRIENDS;
import static Enums.Pages.NOTIFICATIONS;
import static Enums.Relations.*;
import static org.junit.Assert.*;

public class FriendsPageTest extends TestBase {
    private FriendsPage friendsPage;
    private FriendsCard vladSenkoCard;
    private FriendsCard ruslanSultanCard;
    private FriendsCard technopolisBot12Card;
    private WebDriver driver1;

    @Before
    public void setUp() {
        new LoginPage(driver).login(IVAN_LOGIN, IVAN_PASSWORD);
        new UserMainPage(driver).openPage(FRIENDS);
        friendsPage = new FriendsPage(driver);
    }

    @Test
    public void sendMessageTest() {
        vladSenkoCard = friendsPage.getFriendCard(VLADISLAV_SENKO);
        ruslanSultanCard = friendsPage.getFriendCard(RUSLAN_SULTAN);
        String message = "Hello there!" + new Random().nextInt();
        ruslanSultanCard.sendMessage(message);
        assertEquals(message, ruslanSultanCard.getLastMessage());
        vladSenkoCard.sendMessage(message);
        assertEquals(message, vladSenkoCard.getLastMessage());
    }

    @Test
    public void inviteToGroupTest() {
        ruslanSultanCard = friendsPage.getFriendCard(RUSLAN_SULTAN);
        ruslanSultanCard.inviteToGroup();
    }

    @Test
    public void searchFriendsTest1() {
        friendsPage.searchFriend("Петр Петров");
        assertFalse(friendsPage.isFriendFound());
    }

    @Test
    public void searchFriendsTest2() {
        friendsPage.searchFriend(RUSLAN_SULTAN.toString());
        assertTrue(friendsPage.isFriendFound());
    }

    @Test
    public void specifyRelationsTest1() {
        technopolisBot12Card = friendsPage.getFriendCard(TECHNOPOLIS_BOT12);
        Relations[] relations = {COLLEAGUE, GROUPMATE};
        technopolisBot12Card.specifyRelation(relations);
        technopolisBot12Card.checkRelations(relations);
        driver1 = new ChromeDriver();
        driver1.get(LOGIN_PAGE_URL.toString());
        new LoginPage(driver1).login(BOT_LOGIN, BOT_PASSWORD);
        new UserMainPage(driver1).openPage(NOTIFICATIONS);
        new NotificationPage(driver1).declineRelations(IVAN_IVANOV);
        technopolisBot12Card.checkRelations();
        driver1.close();
    }

    @Test
    public void specifyRelationsTest2() {
        technopolisBot12Card = friendsPage.getFriendCard(TECHNOPOLIS_BOT12);
        Relations[] relations1 = {BEST_FRIEND, GAME_FRIEND, CLASSMATE};
        technopolisBot12Card.specifyRelation(relations1);
        technopolisBot12Card.checkRelations(relations1);
        driver1 = new ChromeDriver();
        driver1.get(LOGIN_PAGE_URL.toString());
        new LoginPage(driver1).login(BOT_LOGIN, BOT_PASSWORD);
        new UserMainPage(driver1).openPage(NOTIFICATIONS);
        new NotificationPage(driver1).declineRelations(IVAN_IVANOV);
        technopolisBot12Card.checkRelations();
        driver1.close();
    }
}
