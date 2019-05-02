package Pages;

import Enums.Relations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FriendsCard {
    private WebDriver driver;
    @FindBy(xpath = "//span[text()='Написать сообщение']")
    private WebElement writeMessage;
    @FindBy(name = "st.txt")
    private WebElement messageField;
    @FindBy(xpath = "//button[@class='button-pro comments_add-controls_save']")
    private WebElement sendButton;
    @FindBy(xpath = "//div[@class='toolbar-layer_close h-mod']//*[@class='svg-ic svg-ico_close_16']")
    private WebElement closeButton;
    @FindBy(xpath = "//span[text()='Указать, кто он вам']")
    private WebElement specifyRelation;
    @FindBy(css = "li.form_ul_li")
    private List<WebElement> relations;
    @FindBy(id = "hook_FormButton_button_save_rlshp")
    private WebElement saveButton;
    private static By checkbox = By.xpath(".//input[@type='checkbox']");

    FriendsCard(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void writeMessage(String message) {
        writeMessage.click();
        messageField.sendKeys(message);
//        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(sendButton));
        sendButton.click();
        closeButton.click();
    }

    //    public void specifyRelation(Relations... relations1) {
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
    public void specifyRelation(Relations... relations1) {
        specifyRelation.click();
        //new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(saveButton));
        for (WebElement relation : relations) {
            for (Relations relation1 : relations1) {
                if (relation.getText().equals(relation1.toString())) relation.findElement(checkbox).click();
            }
        }
        saveButton.click();
    }

}
