package com.epam.yandex.pageobjects.pages;

import com.epam.yandex.pageobjects.pages.blocks.HeaderBlock;
import com.epam.yandex.pageobjects.pages.forms.ContextForm;
import com.epam.yandex.pageobjects.pages.blocks.EmailFormBlock;
import com.epam.yandex.pageobjects.pages.blocks.EmailListBlock;
import com.epam.yandex.pageobjects.pages.forms.UserSettingsPopUpForm;
import com.epam.yandex.uitests.utils.ActionsUtil;
import com.epam.yandex.uitests.constant.ProjectConstant;
import com.epam.yandex.uitests.utils.WaitUtil;
import com.epam.yandex.uitests.utils.JSCommandsHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class YandexMailPage extends BasePage {

    private EmailListBlock emailListBlock;
    private EmailFormBlock emailFormBlock;
    private HeaderBlock headerBlock;
    private ContextForm contextForm;
    private UserSettingsPopUpForm settingsPopUpForm;

    public YandexMailPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
        emailListBlock = new EmailListBlock(driver);
        emailFormBlock = new EmailFormBlock(driver);
        headerBlock = new HeaderBlock(driver);
    }

    @FindBy (xpath = "//div[contains(@class, 'ui-dialog-fixed')]")
    private WebElement saveChangesPopup;

    @FindBy (xpath = "//button[contains(@data-action, 'save')]")
    private WebElement saveAsDraftBtn;

    @FindBy (xpath = "//div[contains(@class, 'ns-view-left-box')]//a[contains(@href, '#draft')]")
    private WebElement draftFolder;

    @FindBy (xpath = "//div[contains(@class, 'ns-view-left-box')]//a[contains(@href, '#sent')]")
    private WebElement sentFolder;

    public EmailListBlock emailListBlock() {
        return emailListBlock;
    }

    public EmailFormBlock emailFormBlock() {
        return emailFormBlock;
    }

    public HeaderBlock headerBlock() {
        return headerBlock;
    }

    public void saveEmailAsDraft() {
        WaitUtil.waitForElementIsDisplayed(driver, saveChangesPopup, ProjectConstant.TimeConstant.TIME_20_SEC);
        JSCommandsHelper.clickOnElement(saveAsDraftBtn, driver);
    }

    public void openDraftFolder() {
        draftFolder.click();
    }

    public void openSentFolder() {
        sentFolder.click();
    }

    public void dragSentEmailToDraftFolder(int emailIndex) {
        new ActionsUtil(driver).dragAndDrop(new EmailListBlock(driver).getEmailList().get(emailIndex), draftFolder);
    }

    public void contextClickOnEmailByIndex(int index) {
        new ActionsUtil(driver).contextClick(new EmailListBlock(driver).getEmailList().get(index));
    }

    public void clickDelete() {
        contextForm.waitForContexMenuVisible(driver);
        contextForm.clickDeleteItem();
    }
    
    public void logOut() {
        settingsPopUpForm.waitForLogOutVisible(driver);
        settingsPopUpForm.logOut();
    }
}
