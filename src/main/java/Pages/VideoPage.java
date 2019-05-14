package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

import static Enums.AssertsTexts.VIDEO_PAGE_URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.or;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class VideoPage extends BasePage {
    public VideoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "field_sq")
    private WebElement searchBar;
    @FindBy(css = "#listBlockPanelVideoSearchResultAlbumsBlock .portlet_h_name_t")
    private WebElement channelsFound;
    @FindBy(css = "#listBlockPanelVideoSearchResultMoviesBlock .portlet_h_name_t")
    private WebElement videosFound;
    @FindBy(className = "stub-empty_t")
    private WebElement nothingFound;
    @FindBy(css = "#listBlockPanelVideoSearchResultMoviesBlock .ugrid_i:first-child .video-card_lk")
    private WebElement firstVideo;


    @Override
    public void check() {
        assertEquals(VIDEO_PAGE_URL.toString(), driver.getCurrentUrl());
        assertTrue(navigationToolbar.isDisplayed());
        assertTrue(middleColumn.isDisplayed());
        assertTrue(leftColumn.isDisplayed());
    }

    public void searchVideo(String videoName) {
        searchBar.clear();
        searchBar.sendKeys(videoName);
//        new WebDriverWait(driver, 15).until(or(visibilityOf(channelsFound), visibilityOf(videosFound),
//                visibilityOf(nothingFound)));
        assertTrue(channelsFound.isDisplayed() || videosFound.isDisplayed() || nothingFound.isDisplayed());
    }

    public VideoCard searchAndOpenVideo(String videoName) {
        searchBar.clear();
        searchBar.sendKeys(videoName);
//        new WebDriverWait(driver, 15).until(or(visibilityOf(channelsFound), visibilityOf(videosFound),
//                visibilityOf(nothingFound)));
        assertTrue(channelsFound.isDisplayed() || videosFound.isDisplayed() || nothingFound.isDisplayed());
        if (firstVideo.isDisplayed()) {
            firstVideo.click();
            return new VideoCard(driver);
        }
        throw new NoSuchElementException("No videos were found!");
    }

}
