import Bases.TestBase;
import Pages.VideoCard;
import Pages.VideoPage;
import Pages.LoginPage;
import Pages.UserMainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static Enums.LoginInfo.*;
import static Enums.Pages.FRIENDS;
import static Enums.Pages.VIDEO;

public class VideoPageTest extends TestBase {
    private LoginPage loginPage;
    private UserMainPage userMainPage;
    private VideoPage videoPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        userMainPage = new UserMainPage(driver);
        videoPage = new VideoPage(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(LOGIN_PAGE_URL.toString());
    }

    @Test()
    public void videoPageTest() {
        loginPage.check();
        loginPage.login(LOGIN, PASSWORD);
        userMainPage.check();
        userMainPage.openPage(VIDEO);
        videoPage.check();
        videoPage.searchVideo("cats");
        videoPage.searchVideo("sfzgfhxgjhfjhhxdzfSAFSEhsthtxdzgfghzdrtggvgh");
        videoPage.searchAndOpenVideo("cats").stopVideo();
    }

//    @After
//    public void afterClass() {
//        driver.close();
//    }
}
