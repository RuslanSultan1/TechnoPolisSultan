package Pages;

import Enums.Relations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SpecifyRelationLayer extends BasePage {
    @FindBy(css = ".modal-new_hld")
    private WebElement mainFrame;
    @FindBy(css = "li.form_ul_li")
    private List<WebElement> relations;
    @FindBy(id = "hook_FormButton_button_save_rlshp")
    private WebElement saveButton;
    @FindBy(id = "nohook_modal_close")
    private WebElement closeSpecifyRelation;
    private final static By checkbox = By.xpath(".//input[@type='checkbox']");

    @Override
    void check() {
        assertTrue("mainFrame", isElementDisplayed(mainFrame));
        assertTrue("saveButton", isElementDisplayed(saveButton));
    }

    public SpecifyRelationLayer(WebDriver driver) {
        super(driver);
    }

    public SpecifyRelationLayer specifyRelation(Relations... relations1) {
        for (WebElement relation : relations) {
            for (Relations relation1 : relations1) {
                if (relation.getText().equals(relation1.toString()) &&
                        (!relation.findElement(checkbox).isSelected())) {
                    assertTrue("checkbox", isElementDisplayed(relation.findElement(checkbox)));
                    relation.findElement(checkbox).click();
                }
            }
        }
        return this;
    }

    public SpecifyRelationLayer checkRelations(Relations... relations1) {
        label:
        for (WebElement relation : relations) {
            for (Relations relation1 : relations1) {
                if (relation.getText().equals(relation1.toString())) {
                    assertTrue(relation.findElement(checkbox).isSelected());
                    continue label;
                }
            }
            assertFalse(relation.findElement(checkbox).isSelected());
        }
        return this;
    }

    public void saveSpecifiedRelations() {
        assertTrue("saveButton", isElementDisplayed(saveButton));
        saveButton.click();
    }

    public void closeSpecifyRelationLayer() {
        assertTrue("closeSpecifyRelation", isElementDisplayed(closeSpecifyRelation));
        closeSpecifyRelation.click();
    }
}
