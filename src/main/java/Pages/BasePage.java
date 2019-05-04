package Pages;

import Enums.Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public abstract class BasePage {
    protected WebDriver driver;

    protected void init(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".toolbar_nav_i_tx-w")
    List<WebElement> pages;

    public abstract void check();

    public void openPage(Pages pageName) {
        for (WebElement page : pages) {
            if (page.getText().contains(pageName.toString())) page.click();
        }
    }
}
