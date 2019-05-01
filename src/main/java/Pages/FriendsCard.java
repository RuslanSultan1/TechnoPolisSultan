package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FriendsCard {
    WebDriver driver;
    @FindBy(xpath = "//*[text()='Написать сообщение']")
    WebElement writeMessage;
    @FindBy(name = "st.txt")
    WebElement messageField;
    @FindBy(xpath = "//button[@class='button-pro comments_add-controls_save']")
    WebElement sendButton;
    @FindBy(xpath = "//div[@class='toolbar-layer_close h-mod']//*[@class='svg-ic svg-ico_close_16']")
    WebElement closeButton;

    public FriendsCard(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void write(String message){
        writeMessage.click();
        messageField.sendKeys(message);
//        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(sendButton));
        sendButton.click();
        closeButton.click();
    }

}
