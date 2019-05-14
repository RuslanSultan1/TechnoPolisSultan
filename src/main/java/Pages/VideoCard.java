package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        check();
    }

    @FindBy(css = ".vp_video")
    private WebElement videoPlayer;
//    @FindBy(css = "vp-layer-info")
//    private WebElement videoInfo;
    @FindBy(xpath = "//div[@class='html5-vpl_panel_btn html5-vpl_play']")
    private WebElement playButton;
    @FindBy(xpath = "//span[@class='widget_cnt controls-list_lk h-mod']")
    private WebElement likeButton;
    @FindBy(xpath = "//div[@class='vp-layer-description']")
    private WebElement videoDescription;

    public void stopVideo() {
        Actions actions = new Actions(driver);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        actions.moveToElement(likeButton).perform();
        new Actions(driver).moveToElement(videoPlayer).perform();
        new Actions(driver).moveToElement(playButton).perform();
        playButton.click();
    }

}
