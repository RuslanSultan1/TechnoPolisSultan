import Bases.TestBase;
import Pages.*;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static Enums.LoginInfo.*;
import static Enums.Pages.VIDEO;
import static Enums.VideoSideMenuItems.MY_SUBSCRIPTIONS;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;

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
        loginPage.login(IVAN_LOGIN, IVAN_PASSWORD);
        userMainPage = new UserMainPage(driver);
        userMainPage.openPage(VIDEO);
        videoPage = new VideoPage(driver);
    }


    //@Test
    public void createChannel() {
        videoPage.createChannel("testChannel");
        assertTrue(videoPage.channelExist("testChannel"));
    }

// @Test
// public void createChannelAndAddVideo(){
// videoPage.createChannel("testChannel1");
// videoPage.addVideoByUrl();
// }

    //@Test
    public void deleteChannel() {
        videoPage.deleteChannel("testChannel1");
        assertFalse(videoPage.channelExist("testChannel1"));
    }

    //@Test()
    public void likeTest() {
        videoPage.searchVideo("cats");
        VideoCard firstVideo = videoPage.openFirstVideo();
        firstVideo.stopVideo();
        firstVideo.likeVideo();
        driver.navigate().refresh();
        assertTrue(firstVideo.isLiked());
    }

    //@Test()
    public void searchVideoTest() {
        videoPage.searchVideo("cats");
        videoPage.assertVideos();
    }

    //@Test()
    public void subscribeTest() {
        videoPage.searchVideo("cats");
        VideoCard firstVideo = videoPage.openFirstVideo();
        String channelName = firstVideo.getChannelName();
        firstVideo.subscribe();
        firstVideo.closeVideo();
        videoPage.clickItemByName(MY_SUBSCRIPTIONS.toString());
        videoPage.unSubscribeFromChannel(channelName);
        videoPage.clickItemByName(MY_SUBSCRIPTIONS.toString());
        assertFalse(videoPage.channelExistInMySubscriptions(channelName));
    }

    //@Test
    public void browsingHistory() {
        videoPage.clickMyVideoSubMenu("История просмотров");
        videoPage.cleanHistory();
        videoPage.searchVideo("dogs");
        VideoCard firstVideo = videoPage.openFirstVideo();
        String videoName = firstVideo.getVideoName();
        firstVideo.closeVideo();
        videoPage.clickMyVideoSubMenu("История просмотров");
        videoPage.videoWithSameName(videoName);
        assertFalse(videoPage.videoWithSameName(videoName));
    }

// @Test
// public void checkComments() {
// videoPage.searchVideo("cats");
// VideoCard firstVideo = videoPage.openFirstVideo();
// String videoURL = driver.getCurrentUrl();
// firstVideo.closeVideo();
// videoPage.addVideoByUrl(videoURL, LINK.toString());
// WebDriver secondDriver = new ChromeDriver();
// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
// driver.manage().window().maximize();
// driver.get(LOGIN_PAGE_URL.toString());
// new LoginPage(secondDriver).login(BOT_LOGIN, BOT_PASSWORD);
// new UserMainPage(secondDriver).openPage(FRIENDS);
// FriendsCard ivanIvanov = new FriendsPage(secondDriver).getFriendCard(Friends.IVAN_IVANOV);
// }
}
