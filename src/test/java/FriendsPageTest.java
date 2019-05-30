import Bases.TestBase;
import Enums.Relations;
import Pages.FriendsCard;
import Pages.FriendsPage;
import Pages.LoginPage;
import Pages.UserMainPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static Enums.Friends.RUSLAN_SULTAN;
import static Enums.Friends.VLADISLAV_SENKO;
import static Enums.LoginInfo.*;
import static Enums.Pages.FRIENDS;
import static Enums.Relations.*;
import static org.junit.Assert.assertEquals;

public class FriendsPageTest extends TestBase {
    private LoginPage loginPage;
    private UserMainPage userMainPage;
    private FriendsPage friendsPage;
    private FriendsCard vladSenko;
    private FriendsCard ruslanSultan;
    private FriendsCard technopolisBot12;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(LOGIN_PAGE_URL.toString());
        loginPage = new LoginPage(driver);
        loginPage.login(IVAN_LOGIN, IVAN_PASSWORD);
        userMainPage = new UserMainPage(driver);
        userMainPage.openPage(FRIENDS);
        friendsPage = new FriendsPage(driver);
        vladSenko = friendsPage.getFriendCard(VLADISLAV_SENKO);
        ruslanSultan = friendsPage.getFriendCard(RUSLAN_SULTAN);
    }

    @Test
    public void sendMessageTest() {
        String message = "Hello there!";
        ruslanSultan.sendMessage(message);
        assertEquals(message, ruslanSultan.getLastMessage());
//        vladSenko.sendMessage(message);
//        assertEquals(message, vladSenko.getLastMessage());
        ruslanSultan.sendAndCheckRandomMessage();
//        vladSenko.sendAndCheckRandomMessage();
//        friendsPage.getFriendCard(VLADISLAV_SENKO).sendMessage("Привет, Влад!");
//        friendsPage.getFriendCard(RUSLAN_SULTAN).sendMessage("Привет, Руслан!");
//        friendsPage.getFriendCard(RUSLAN_SULTAN).sendAndCheckRandomMessage();
//        friendsPage.getFriendCard(RUSLAN_SULTAN).specifyRelation(COLLEAGUE, GROUPMATE);
//        friendsPage.getFriendCard(VLADISLAV_SENKO).inviteToGroup();
    }

//    @Test
    public void specifyRelationsTest() {
        Relations[] relations = {COLLEAGUE, GROUPMATE};
        technopolisBot12.specifyRelation(relations);
        technopolisBot12.checkRelations(relations);
        Relations[] relations1 = {BEST_FRIEND, GAME_FRIEND, CLASSMATE};
        technopolisBot12.specifyRelation(relations1);
        technopolisBot12.checkRelations(relations1);
    }

    public void declineRelations() {
        ChromeDriver driver1 = new ChromeDriver();
        driver1.get(LOGIN_PAGE_URL.toString());
        new LoginPage(driver).login(BOT_LOGIN, BOT_PASSWORD);
        driver1.get("https://www.ok.ru/notifications");
        driver1.close();
    }

    @Test
    public void inviteToGroupTest() {
        ruslanSultan.inviteToGroup();
    }

//    @After
//    public void afterClass() {
//        driver.close();
//    }
}
