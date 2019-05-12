package Pages;

import Enums.Relations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

import static Enums.AssertsTexts.INVITATION_TO_GROUP_TEXT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FriendsCard {
    private WebDriver driver;
    @FindBy(xpath = "//span[text()='Написать сообщение']")
    private WebElement writeMessage;
    @FindBy(name = "st.txt")
    private WebElement messageField;
    @FindBy(xpath = "//button[@class='button-pro comments_add-controls_save']")
    private WebElement sendButton;
    @FindBy(xpath = "//div[@class='toolbar-layer_close h-mod']//*[@class='svg-ic svg-ico_close_16']")
    private WebElement closeMessage;
    @FindBy(xpath = "//span[text()='Указать, кто он вам']")
    private WebElement specifyRelation;
    @FindBy(css = "li.form_ul_li")
    private List<WebElement> relations;
    @FindBy(id = "hook_FormButton_button_save_rlshp")
    private WebElement saveButton;
    @FindBy(xpath = "//div[contains(@class, 'msg js-msg soh-s')][last()]//*[@class='msg_tx_cnt']")
    private WebElement lastMessage;
    private static By checkbox = By.xpath(".//input[@type='checkbox']");
    @FindBy(xpath = "//span[text()='Пригласить в группу']")
    private WebElement inviteToGroup;
    @FindBy(id = "hook_Block_InviteUserToGroup2GroupsList")
    private WebElement invitationText;
    @FindBy(xpath = "//a[@id='nohook_modal_close']")
    private WebElement closeInvitation;

    FriendsCard(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void sendMessage(String message) {
        writeMessage.click();
        messageField.sendKeys(message);
//        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(sendButton));
        sendButton.click();
//        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(closeMessage));
        new WebDriverWait(driver, 10).until((ExpectedCondition<Boolean>) webDriver
                -> lastMessage.getText().equals(message));
        assertEquals(lastMessage.getText(), message);
        closeMessage.click();
    }

    public void sendRandomMessage() {
        writeMessage.click();
        Random random = new Random();
        String message = "Message" + random.nextInt();
        messageField.sendKeys(message);
//        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(sendButton));
        sendButton.click();
        new WebDriverWait(driver, 10).until((ExpectedCondition<Boolean>) webDriver
                -> lastMessage.getText().equals(message));
//        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(closeMessage));
        assertEquals(lastMessage.getText(), message);
        closeMessage.click();
    }

    public void specifyRelation(Relations... relations1) {
        specifyRelation.click();
        //new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(saveButton));
        for (WebElement relation : relations) {
            for (Relations relation1 : relations1) {
                if (relation.getText().equals(relation1.toString())) {
                    relation.findElement(checkbox).click();
                    assertTrue(relation.findElement(checkbox).isSelected());
                }
            }
        }
        saveButton.click();
    }

    public void inviteToGroup() {
        inviteToGroup.click();
        assertEquals(INVITATION_TO_GROUP_TEXT.toString(), invitationText.getText());
        closeInvitation.click();
    }
//        public void specifyRelation(Relations... relations1) {
//        specifyRelation.click();
//        for (WebElement relation : relations) {
//            if (relation.findElement(checkbox).isSelected()) relation.findElement(checkbox).click();
//            for (Relations relation1 : relations1) {
//                if (relation.getText().equals(relation1.toString())) relation.findElement(checkbox).click();
//            }
//            System.out.println(relation.getText());
//            System.out.println(relation.findElement(checkbox).isSelected());
//        }
//        saveButton.click();
//    }

}
