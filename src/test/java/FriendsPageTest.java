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
import static Pages.InvitationToGroupLayer.INVITATION_TO_GROUP_TEXT;
import static org.junit.Assert.*;

public class FriendsPageTest extends TestBase {
    private FriendsPage friendsPage;
    private FriendsCard technopolisBot12Card;

    @Before
    public void setUp() {
        new LoginPage(driver).login(IVAN_LOGIN, IVAN_PASSWORD);
        new UserMainPage(driver).openPage(FRIENDS);
        friendsPage = new FriendsPage(driver);
    }

    @Test
    public void sendMessageTest() {
        String message = "Hello there!" + new Random().nextInt();
        MessageLayer messagesWithRuslan = friendsPage.getFriendCard(RUSLAN_SULTAN).openMessageLayer();
        messagesWithRuslan.sendMessage(message);
        assertEquals(message, messagesWithRuslan.getLastMessage());
        messagesWithRuslan.closeMessageLayer();
        MessageLayer messagesWithVlad = friendsPage.getFriendCard(VLADISLAV_SENKO).openMessageLayer();
        messagesWithVlad.sendMessage(message);
        assertEquals(message, messagesWithVlad.getLastMessage());
        messagesWithVlad.closeMessageLayer();
    }

    @Test
    public void inviteToGroupTest() {
        InvitationToGroupLayer ruslanInvitation = friendsPage.getFriendCard(RUSLAN_SULTAN).openInvitationToGroupLayer();
        assertEquals(INVITATION_TO_GROUP_TEXT, ruslanInvitation.getInvitationText());
        ruslanInvitation.closeInvitationLayer();
    }

    @Test
    public void searchFriendsTestNegative() {
        friendsPage.searchFriend("Петр Петров");
        assertFalse(friendsPage.isFriendFound());
    }

    @Test
    public void searchFriendsTestPositive() {
        friendsPage.searchFriend(RUSLAN_SULTAN.toString());
        assertTrue(friendsPage.isFriendFound());
    }

    @Test
    public void specifyRelationsTest1() {
        technopolisBot12Card = friendsPage.getFriendCard(TECHNOPOLIS_BOT12);
        Relations[] relations = {COLLEAGUE, GROUPMATE};
        technopolisBot12Card.openSpecifyRelationLayer().specifyRelation(relations).
                checkRelations(relations).saveSpecifiedRelations();
        WebDriver driver1 = new ChromeDriver();
        driver1.get(LOGIN_PAGE_URL.toString());
        new LoginPage(driver1).login(BOT_LOGIN, BOT_PASSWORD);
        new UserMainPage(driver1).openPage(NOTIFICATIONS);
        new NotificationPage(driver1).declineRelations(IVAN_IVANOV);
        technopolisBot12Card.openSpecifyRelationLayer().specifyRelation().
                checkRelations().closeSpecifyRelationLayer();
        driver1.close();
    }

    @Test
    public void specifyRelationsTest2() {
        technopolisBot12Card = friendsPage.getFriendCard(TECHNOPOLIS_BOT12);
        Relations[] relations = {BEST_FRIEND, GAME_FRIEND, CLASSMATE};
        technopolisBot12Card.openSpecifyRelationLayer().specifyRelation(relations).
                checkRelations(relations).saveSpecifiedRelations();
        WebDriver driver1 = new ChromeDriver();
        driver1.get(LOGIN_PAGE_URL.toString());
        new LoginPage(driver1).login(BOT_LOGIN, BOT_PASSWORD);
        new UserMainPage(driver1).openPage(NOTIFICATIONS);
        new NotificationPage(driver1).declineRelations(IVAN_IVANOV);
        technopolisBot12Card.openSpecifyRelationLayer().specifyRelation().
                checkRelations().closeSpecifyRelationLayer();
        driver1.close();
    }
}
