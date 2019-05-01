import Bases.TestBase;
import Pages.FriendsPage;
import Pages.LoginPage;
import Pages.UserMainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static Enums.Friends.RUSLAN_SULTAN;
import static Enums.Friends.VLADISLAV_SENKO;
import static Enums.LoginInfo.LOGIN;
import static Enums.LoginInfo.PASSWORD;

public class SimpleTest5 extends TestBase {
    private String baseUrl;
    private LoginPage loginPage;
    private UserMainPage userMainPage;
    private FriendsPage friendsPage;

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        userMainPage = PageFactory.initElements(driver, UserMainPage.class);
        friendsPage = new FriendsPage(driver);
        baseUrl = "https://ok.ru/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl + "/dk?st.cmd=anonymMain&st.layer.cmd=PopLayerClose");
    }

    @Test()
    public void simpleTest() throws Exception {
        loginPage.login(LOGIN, PASSWORD);
        userMainPage.openFriendsPage();
        friendsPage.getFriendCard(VLADISLAV_SENKO).write("Привет, Влад!");
        friendsPage.getFriendCard(RUSLAN_SULTAN).write("Привет, Руслан!");
    }

//    @After
//    public void afterClass() {
//        driver.close();
//    }
}
