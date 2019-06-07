import Bases.TestBase;
import Pages.LoginPage;
import Pages.UserMainPage;
import Pages.VideoCard;
import Pages.VideoPage;
import org.junit.Before;
import org.junit.Test;

import static Enums.LoginInfo.IVAN_LOGIN;
import static Enums.LoginInfo.IVAN_PASSWORD;
import static Enums.Pages.VIDEO;
import static Enums.VideoSideMenuItems.MY_SUBSCRIPTIONS;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class VideoPageTest extends TestBase {
    private LoginPage loginPage;
    private UserMainPage userMainPage;
    private VideoPage videoPage;

    @Before
    public void setUp() {
        new LoginPage(driver).login(IVAN_LOGIN, IVAN_PASSWORD);
        new UserMainPage(driver).openPage(VIDEO);
        videoPage = new VideoPage(driver);
    }

    @Test
    public void createChannel() {
        videoPage.createChannel("testChannel");
        assertTrue(videoPage.channelExist("testChannel"));
    }

    @Test
    public void deleteChannel() {
        videoPage.deleteChannel("testChannel1");
        assertFalse(videoPage.channelExist("testChannel1"));
    }

    @Test
    public void likeTest() {
        videoPage.searchVideo("cats");
        VideoCard firstVideo = videoPage.openFirstVideo();
        firstVideo.stopVideo();
        firstVideo.likeVideo();
        driver.navigate().refresh();
        assertTrue(firstVideo.isLiked());
    }

    @Test
    public void searchVideoTest() {
        videoPage.searchVideo("cats");
        videoPage.assertVideos();
    }

    @Test
    public void subscribeTest() {
        videoPage.searchVideo("cats");
        VideoCard firstVideo = videoPage.openFirstVideo();
        String channelName = firstVideo.getChannelName();
        System.out.println(channelName);
        firstVideo.subscribe();
        firstVideo.closeVideo();
        videoPage.clickItemByName(MY_SUBSCRIPTIONS.toString());
        videoPage.unSubscribeFromChannel(channelName);
        videoPage.clickItemByName(MY_SUBSCRIPTIONS.toString());
        assertFalse(videoPage.channelExistInMySubscriptions(channelName));
    }

    @Test
    public void browsingHistory() {
        videoPage.searchVideo("dogs");
        VideoCard firstVideo = videoPage.openFirstVideo();
        String videoName = firstVideo.getVideoName();
        firstVideo.closeVideo();
        videoPage.clickMyVideoSubMenu("История просмотров");
        videoPage.videoWithSameName(videoName);
        videoPage.cleanHistory();
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
// ivanIvanov.
// }
}