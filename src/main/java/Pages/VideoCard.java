package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import static Enums.AssertsTexts.*;
import static org.junit.Assert.assertTrue;

public class VideoCard extends BasePage {

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
    @FindBy(xpath = "//a[@class='js-video-album-link']")
    private WebElement channelName;

    public void subscribe() {
        if (!isSubscribed()) {
            subcribeButton.click();
        }
    }

    public void closeVideo() {
        if (isElementDisplayed(closeLayer)) {
            closeLayer.click();
        }
    }

// public void unSubscribe() {
// if (isSubscribed()) {
// subcribeButton.click();
// }
// }

    public String getVideoName() {
        return videoName.getText();
    }

    public String getChannelName() {
        if (isElementDisplayed(channelName)){
            return channelName.getText();
        }
        return null;
    }

    public boolean isSubscribed() {
        return subcribeButton.getText().equals(SUBSCRIBED.toString());
    }

    public boolean isPlaying() {
        try {
            return playButtonState.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void stopVideo() {
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
        if (!isPlaying()) {
            playButton.click();
        }
    }

    public void likeVideo() {
        if (!isLiked()) {
            likeButton.click();
        }
    }

    public void removeLike() {
        if (isLiked()) {
            likeButton.click();
        }
    }

    public boolean isLiked() {
        return likeButton.getAttribute("data-react") != null;
    }
}