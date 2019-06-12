package Pages;

import Enums.Pages;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.junit.Assert.assertTrue;

public abstract class BasePage {
    protected WebDriver driver;
    @FindBy(className = "toolbar_c")
    protected WebElement navigationToolbar;
    @FindBy(id = "middleColumn")
    protected WebElement middleColumn;
    @FindBy(id = "leftColumn")
    protected WebElement leftColumn;
    @FindBy(css = ".toolbar_nav_i_tx-w")
    List<WebElement> pages;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        check();
    }

    abstract void check();

    public static boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isElementPresent(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void openPage(Pages pageName) {
        for (WebElement page : pages) {
            if (page.getText().contains(pageName.toString())) {
                assertTrue(isElementDisplayed(page));
                page.click();
            }
        }
    }
}
