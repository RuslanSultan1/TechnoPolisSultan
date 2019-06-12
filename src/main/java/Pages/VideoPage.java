package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static Enums.VideoSideMenuItems.CATALOG;
import static Enums.VideoSideMenuItems.MY_VIDEO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VideoPage extends BasePage {

    private final static String VIDEO_PAGE_URL = ("https://ok.ru/video/top");
    private final static String VIDEOS_FOUND_REGEX = ("Найдено \\d+ видео");
    private final static String NOTHING_FOUND_TEXT = ("По вашему запросу ничего не найдено");

    public VideoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "field_sq")
    private WebElement searchBar;
    @FindBy(css = "#listBlockPanelVideoSearchResultAlbumsBlock .portlet_h_name_t")
    private WebElement channelsFound;
    @FindBy(css = "#listBlockPanelVideoSearchResultMoviesBlock .portlet_h_name_t")
    private WebElement videosFound;
    @FindBy(css = ".__video-card .stub-empty_t")
    private WebElement nothingFound;
    @FindBy(css = "#listBlockPanelVideoSearchResultMoviesBlock .ugrid_i:first-child .video-card_lk")
    private WebElement firstVideo;
    @FindBy(xpath = "//a[@class='portlet_add-stub button-pro __light __ic']")
    private WebElement createChannelButton;
    @FindBy(xpath = "//input[@id='field_albName']")
    private WebElement inputChannelForm;
    @FindBy(css = "#hook_FormButton_createAlbumConfirm_not_decorate")
    private WebElement createButton;
    @FindBy(xpath = "//div[@class='nav-side __with-covers']")
    private List<WebElement> myChannelsList;
    @FindBy(xpath = "//div[@id='mainContentContentColumn']//a[3]")
    private WebElement deleteChannel;
    @FindBy(css = "#hook_FormButton_createAlbumConfirm_not_decorate")
    private WebElement confirmDeleteChannel;
    @FindBy(css = ".vp-layer-info_h")
    private List<WebElement> videosNames;
    @FindBy(xpath = ".//span[@class='tico ']")
    private WebElement cleanHistoryBtn;
    @FindBy(xpath = ".//input[@id='hook_FormButton_clearViewHistoryConfirm']")
    private WebElement confirmCleanHistory;
    private String videoPageSideItems = ".navigation .nav-side_i";
    @FindBy(xpath = ".//a[@class='video-subsciption_n']")
    private List<WebElement> channelsNameList;
    @FindBy(xpath = ".//span[@class='tico album-info_subscribe-done']")
    private WebElement subscribeBtn;
    @FindBy(css = ".video-upload-menu_i")
    private List<WebElement> uploadMenuItems;
    @FindBy(xpath = ".//input[@class='it h-mod']")
    private WebElement addVideoForm;
    @FindBy(xpath = ".//input[@id='hook_FormButton_addVideoByLinkConfirm']")
    private WebElement addVideoButton;
//@FindBy(xpath = ".//div[@class='ucard-mini toolbar_ucard js-toolbar-menu']")
// private WebElement mainMenu;
// @FindBy(xpath = ".//a[contains(text(),'Выйти')]")
// private WebElement logOut;
// @FindBy(xpath = ".//input[@id='hook_FormButton_logoff.confirm_not_decorate']")
// private WebElement confirmLogOut;

    @Override
    public void check() {
        assertEquals(VIDEO_PAGE_URL, driver.getCurrentUrl());
        assertTrue(navigationToolbar.isDisplayed());
        assertTrue(middleColumn.isDisplayed());
        assertTrue(leftColumn.isDisplayed());
    }

    public void unSubscribeFromChannel(String channelName) {
        for (WebElement element : channelsNameList) {
            if (element.getText().equals(channelName)) {
                element.click();
            }
        }
        if (isElementPresent(subscribeBtn)) {
            subscribeBtn.click();
        }
    }

    private void upLoadMenuItemClick(String name) {
        for (WebElement element : uploadMenuItems) {
            if (element.getText().equals(name)) {
                element.click();
                break;
            }
        }
    }


// public void logOut() {
// mainMenu.click();
// logOut.click();
// confirmLogOut.click();
// }


    public void addVideoByUrl(String Url, String itemName) {
        upLoadMenuItemClick(itemName);
        assertTrue("addVideoFrom", isElementPresent(addVideoForm));
        addVideoForm.sendKeys(Url);
        assertTrue("addVideoButton", isElementPresent(addVideoButton));
        addVideoButton.click();
    }


    public void unSubscribeFromChannels(String... channelName) {
        for (int i = 0; i < channelName.length; i++) {
            unSubscribeFromChannel(channelName[i]);
        }

    }

    public boolean videoWithSameName(String name) {
        assertTrue("videoNames", videosNames.contains(name));
        return true;
    }


    public void cleanHistory() {
        assertTrue("cleanHistoryBtn", isElementPresent(cleanHistoryBtn));
        assertTrue("confirmCleanHistory", isElementPresent(confirmCleanHistory));
    }

    public void clickMyVideoSubMenu(String subMenuItem) {
        clickItemByName(MY_VIDEO.toString());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (WebElement subItem : driver.findElements(By.cssSelector(videoPageSideItems))) {
            if (subItem.getText().equals(subMenuItem)) {
                subItem.click();
            }
        }
    }

    public void clickItemByName(String name) {
        for (WebElement element : driver.findElements(By.cssSelector(videoPageSideItems))) {
            if (element.equals(name)) {
                element.click();
            }
        }
    }

    public boolean channelExistInMySubscriptions(String channelName) {
        for (WebElement element : myChannelsList) {
            if (element.getText().equals(channelName)) {
                return true;
            }
        }
        return false;
    }

    public void clickByCatalogSubMenu(String subMenuItem) {
        clickItemByName(CATALOG.toString());
        for (WebElement subItem : driver.findElements(By.cssSelector(videoPageSideItems))) {
            if (subItem.getText().equals(subMenuItem)) {
                subItem.click();
            }
        }
    }


    public void searchVideo(String videoName) {
        assertTrue("searchBar", isElementPresent(searchBar));
        searchBar.clear();
        searchBar.sendKeys(videoName);
    }

    public boolean channelExist(String channelName) {
        for (WebElement tmp : myChannelsList) {
            if (tmp.getText().equals(channelName)) {
                return true;
            }
        }
        return false;
    }


    public void createChannel(String channelName) {
        assertTrue("createChnanelButton", isElementPresent(createButton));
        assertTrue("inputChannelForm", isElementPresent(inputChannelForm));
        assertTrue("createButton", isElementPresent(createButton));
        if (!channelExist(channelName)) {
            createChannelButton.click();
            inputChannelForm.sendKeys(channelName);
            createButton.click();
        }
    }

    public void deleteChannel(String channelName) {
        assertTrue("deleteChannel", isElementPresent(deleteChannel));
        assertTrue("confirmDeleteChannel", isElementPresent(confirmDeleteChannel));
        if (channelExist(channelName)) {
            for (WebElement tmp : myChannelsList) {
                if (tmp.getText().equals(channelName)) {
                    tmp.click();
                    break;
                }
            }
            deleteChannel.click();
            confirmDeleteChannel.click();
        }
    }


    public void assertVideos() {
        try {
            assertTrue(videosFound.isDisplayed());
            assertTrue(videosFound.getText().matches(VIDEOS_FOUND_REGEX));
        } catch (NoSuchElementException e) {
            assertTrue(nothingFound.isDisplayed());
            assertEquals(NOTHING_FOUND_TEXT, nothingFound.getText());
        }
    }

    public VideoCard openFirstVideo() {
        assertTrue("firstVideo", isElementPresent(firstVideo));
        firstVideo.click();
        return new VideoCard(driver);
    }

}