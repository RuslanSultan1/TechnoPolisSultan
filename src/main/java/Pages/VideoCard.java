package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertTrue;

public class VideoCard extends BasePage {

    private final static String SUBSCRIBED = ("Подписаны");
    private final static String UNSUBSCRIBED = ("Подписаться");


    @Override
    public void check() {
        assertTrue(driver.getCurrentUrl().contains("video"));
        assertTrue(videoPlayer.isDisplayed());
    }

    public VideoCard(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".vp_video")
    private WebElement videoPlayer;
    @FindBy(css = ".html5-vpl_panel_btn.html5-vpl_play")
    private WebElement playButton;
    @FindBy(css = ".controls-list_lk.h-mod")
    private WebElement likeButton;
    @FindBy(css = ".html5-vpl_panel_btn.html5-vpl_play g")
    private WebElement playButtonState;
    @FindBy(xpath = "//div[@class='vp-layer-description']")
    private WebElement videoDescription;
    @FindBy(xpath = "//a[@class='button-pro __sec __small']")
    private WebElement subcribeButton;
    @FindBy(xpath = "//div[@id='vpl_close']//div[@class='ic media-layer_close_ico']")
    private WebElement closeLayer;
    @FindBy(css = ".vp-layer-info_h")
    private WebElement videoName;
    @FindBy(xpath = ".//a[@class='js-video-album-link']")
    private WebElement channelName;

    public void subscribe() {
        assertTrue("subscribeButton", isElementPresent(subcribeButton));
        if (!isSubscribed()) {
            subcribeButton.click();
        }
    }

    public void closeVideo() {
        assertTrue("closeLayer", isElementPresent(closeLayer));
        closeLayer.click();
    }

// public void unSubscribe() {
// if (isSubscribed()) {
// subcribeButton.click();
// }
// }

    public String getVideoName() {
        assertTrue("videoName", isElementPresent(videoName));
        return videoName.getText();
    }

    public String getChannelName() {
        assertTrue("channelName", isElementPresent(channelName));
        return channelName.getText();
    }

    public boolean isSubscribed() {
        assertTrue("subscribeButton", isElementPresent(subcribeButton));
        return subcribeButton.getText().equals(SUBSCRIBED);
    }

    public boolean isPlaying() {
        assertTrue("playButtonState", isElementPresent(playButtonState));
        return playButtonState.isDisplayed();
    }

    public void stopVideo() {
        playBtnAssert();
        if (isPlaying()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            playButton.click();
        }
    }

    public void playVideo() {
        playBtnAssert();
        if (!isPlaying()) {
            playButton.click();
        }
    }

    public void playBtnAssert() {
        assertTrue("playButton", isElementPresent(playButton));
    }

    public void likeBtnAssert() {
        assertTrue("likeButton", isElementPresent(likeButton));
    }

    public void likeVideo() {
        likeBtnAssert();
        if (!isLiked()) {
            likeButton.click();
        }
    }

    public void removeLike() {
        likeBtnAssert();
        if (isLiked()) {
            likeButton.click();
        }
    }

    public boolean isLiked() {
        likeBtnAssert();
        return likeButton.getAttribute("data-react") != null;
    }
}