package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertTrue;

public class InvitationToGroupLayer extends BasePage {
    public static final String INVITATION_TO_GROUP_TEXT = "Сначала нужно создать группу," +
            " а потом можно приглашать в неё друзей.";
    @FindBy(id = "hook_Block_InviteUserToGroup2GroupsList")
    private WebElement invitationText;
    @FindBy(xpath = "//a[@id='nohook_modal_close']")
    private WebElement closeInvitation;
    @FindBy(css = ".modal-new_hld")
    private WebElement mainFrame;

    @Override
    void check() {
        assertTrue("mainFrame", isElementDisplayed(mainFrame));
        assertTrue("invitationText", isElementDisplayed(invitationText));
    }

    public InvitationToGroupLayer(WebDriver driver) {
        super(driver);
    }

    public String getInvitationText() {
        assertTrue("invitationText", isElementDisplayed(invitationText));
        return invitationText.getText();
    }

    public void closeInvitationLayer() {
        closeInvitation.click();
    }
}
