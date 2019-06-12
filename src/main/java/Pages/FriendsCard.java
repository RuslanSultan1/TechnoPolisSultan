package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static Pages.BasePage.isElementDisplayed;
import static org.junit.Assert.assertTrue;

public class FriendsCard {
    private WebDriver driver;
    private WebElement friendCard;

    @FindBy(xpath = "//span[text()='Написать сообщение']")
    private WebElement writeMessage;
    @FindBy(xpath = "//span[text()='Указать, кто он вам']")
    private WebElement specifyRelation;
    @FindBy(xpath = "//span[text()='Пригласить в группу']")
    private WebElement inviteToGroup;


    FriendsCard(WebDriver driver, WebElement friendcard) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.friendCard = friendcard;
    }

    private void moveToCard() {
        Actions actions = new Actions(driver);
        assertTrue("friendCard", isElementDisplayed(friendCard));
        actions.moveToElement(friendCard).perform();
    }

    public MessageLayer openMessageLayer() {
        moveToCard();
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(writeMessage));
        assertTrue("writeMessage", isElementDisplayed(writeMessage));
        writeMessage.click();
        return new MessageLayer(driver);
    }

    public SpecifyRelationLayer openSpecifyRelationLayer() {
        moveToCard();
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(specifyRelation));
        assertTrue("specifyRelation", isElementDisplayed(specifyRelation));
        specifyRelation.click();
        return new SpecifyRelationLayer(driver);
    }


    public InvitationToGroupLayer openInvitationToGroupLayer() {
        moveToCard();
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(inviteToGroup));
        assertTrue("inviteToGroup",isElementDisplayed(inviteToGroup));
        inviteToGroup.click();
        return new InvitationToGroupLayer(driver);
    }
}
