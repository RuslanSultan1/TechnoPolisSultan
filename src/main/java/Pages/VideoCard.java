package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertTrue;

public class VideoCard extends BasePage {
    @Override
    public void check() {
        assertTrue(driver.getCurrentUrl().contains("video"));
        assertTrue(videoPlayer.isDisplayed());
//        assertTrue(videoInfo.isDisplayed());
    }

    public VideoCard(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".vp_video")
    private WebElement videoPlayer;
//    @FindBy(css = "vp-layer-info")
//    private WebElement videoInfo;
    @FindBy(css = ".html5-vpl_panel_btn.html5-vpl_play")
    private WebElement playButton;
    @FindBy(css = ".controls-list_lk.h-mod")
    private WebElement likeButton;
    @FindBy(css = ".html5-vpl_panel_btn.html5-vpl_play g")
    private WebElement playButtonState;
    @FindBy(xpath = "//div[@class='vp-layer-description']")
    private WebElement videoDescription;

    public boolean isPlaying() {
        try {
            return playButtonState.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void stopVideo() {
        if (isPlaying()) {
//            Actions actions = new Actions(driver);
            try {
                Thread.sleep(1000);
//                actions.moveToElement(videoPlayer).perform();
//                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            playButton.click();
        }
    }

    public void playVideo() {
        if (!isPlaying()) {
//            Actions actions = new Actions(driver);
//            try {
//                actions.moveToElement(videoPlayer).perform();
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
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
