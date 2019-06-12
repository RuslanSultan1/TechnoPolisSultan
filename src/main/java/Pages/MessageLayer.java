package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class MessageLayer extends BasePage {
    @Override
    void check() {
        assertTrue("chat", isElementDisplayed(chat));
        assertTrue("messageField", isElementDisplayed(messageField));
        assertTrue(driver.getCurrentUrl().contains("messages"));
    }

    public MessageLayer(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".chat_cnt_w.js-chat-wrapper")
    private WebElement chat;
    @FindBy(name = "st.txt")
    private WebElement messageField;
    @FindBy(xpath = "//button[@class='button-pro comments_add-controls_save']")
    private WebElement sendButton;
    @FindBy(xpath = "//div[@class='toolbar-layer_close h-mod']//*[@class='svg-ic svg-ico_close_16']")
    private WebElement closeMessage;
    @FindBy(css = ".js-msg-text.msg_tx_w")
    public List<WebElement> messages;

    public String getMessageByIndex(int i) {
        if (i >= 0 && i < messages.size()) {
            return messages.get(i).getText();
        }
        throw new IndexOutOfBoundsException("Message with this index wasn't found.");
    }

    public String getLastMessage() {
        return getMessageByIndex(messages.size() - 1);
    }

    public void sendMessage(String message) {
        assertTrue("messageField", isElementDisplayed(messageField));
        messageField.sendKeys(message);
        new WebDriverWait(driver, 15).until((ExpectedCondition<Boolean>) webDriver
                -> messageField.getText().equals(message));
        assertTrue("sendButton", isElementDisplayed(sendButton));
        sendButton.click();
        new WebDriverWait(driver, 15).until((ExpectedCondition<Boolean>) webDriver
                -> getLastMessage().equals(message));
    }

    public void closeMessageLayer() {
        assertTrue("closeMessage", isElementDisplayed(closeMessage));
        closeMessage.click();
    }
}
