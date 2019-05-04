import Bases.TestBase;
import Pages.FriendsPage;
import Pages.LoginPage;
import Pages.UserMainPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static Enums.Friends.RUSLAN_SULTAN;
import static Enums.Friends.VLADISLAV_SENKO;
import static Enums.LoginInfo.LOGIN;
import static Enums.LoginInfo.LOGIN_PAGE_URL;
import static Enums.LoginInfo.PASSWORD;
import static Enums.Pages.FRIENDS;
import static Enums.Relations.COLLEAGUE;
import static Enums.Relations.GROUPMATE;

public class FriendsPageTest extends TestBase {
    private String baseUrl;
    private LoginPage loginPage;
    private UserMainPage userMainPage;
    private FriendsPage friendsPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        userMainPage = PageFactory.initElements(driver, UserMainPage.class);
        friendsPage = new FriendsPage(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(LOGIN_PAGE_URL.toString());
    }

    @Test()
    public void friendsPageTest() throws InterruptedException {
        loginPage.login(LOGIN, PASSWORD);
        userMainPage.openPage(FRIENDS);
        friendsPage.check();
        friendsPage.getFriendCard(VLADISLAV_SENKO).sendMessage("Привет, Влад!");
        friendsPage.getFriendCard(RUSLAN_SULTAN).sendMessage("Привет, Руслан!");
        friendsPage.getFriendCard(RUSLAN_SULTAN).sendRandomMessage();
        friendsPage.getFriendCard(RUSLAN_SULTAN).specifyRelation(COLLEAGUE,GROUPMATE);
    }

//    @After
//    public void afterClass() {
//        driver.close();
//    }
}
