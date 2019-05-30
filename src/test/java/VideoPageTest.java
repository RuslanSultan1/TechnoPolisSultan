import Bases.TestBase;
import Pages.VideoCard;
import Pages.VideoPage;
import Pages.LoginPage;
import Pages.UserMainPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static Enums.LoginInfo.*;
import static Enums.Pages.VIDEO;

public class VideoPageTest extends TestBase {
    private LoginPage loginPage;
    private UserMainPage userMainPage;
    private VideoPage videoPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(LOGIN_PAGE_URL.toString());
        loginPage = new LoginPage(driver);
    }

    @Test()
    public void videoPageTest() {
        loginPage.login(IVAN_LOGIN, IVAN_PASSWORD);
        userMainPage = new UserMainPage(driver);
        userMainPage.openPage(VIDEO);
        videoPage = new VideoPage(driver);
//        videoPage.searchVideo("sfzgfhxgjhfjhhxdzfSAFSEhsthtxdzgfghzdrtggvgh");
        videoPage.searchVideo("cats");
        VideoCard firstVideo = videoPage.openFirstVideo();
        firstVideo.stopVideo();
        System.out.println("gszfdhdxfhd");
//        firstVideo.playVideo();
//        firstVideo.stopVideo();
        firstVideo.likeVideo();
    }

//    @After
//    public void afterClass() {
//        driver.close();
//    }
}
