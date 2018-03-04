package com.epam.yandex.pageobjects.pages;

import com.epam.yandex.pageobjects.pages.blocks.HeaderBlock;
import com.epam.yandex.pageobjects.pages.forms.ContextForm;
import com.epam.yandex.pageobjects.pages.blocks.EmailFormBlock;
import com.epam.yandex.pageobjects.pages.blocks.EmailListBlock;
import com.epam.yandex.pageobjects.pages.forms.UserSettingsPopUpForm;
import com.epam.yandex.utils.ActionsUtil;
import com.epam.yandex.uitests.constant.ProjectConstant;
import com.epam.yandex.utils.WaitUtil;
import com.epam.yandex.utils.JSCommandsHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class YandexMailPage extends BasePage {

    private static Logger log = Logger.getLogger(YandexMailPage.class);

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
        log.info("Saving email as a draft.");
        WaitUtil.waitForElementIsDisplayed(driver, saveChangesPopup, ProjectConstant.TimeConstant.TIME_20_SEC);
        JSCommandsHelper.highlightElement(saveAsDraftBtn, driver);
        JSCommandsHelper.clickOnElement(saveAsDraftBtn, driver);
    }

    public void openDraftFolder() {
        log.info("Open draft folder. ");
        JSCommandsHelper.highlightElement(draftFolder, driver);
        draftFolder.click();
    }

    public void openSentFolder() {
        log.info("Open sent folder. ");
        JSCommandsHelper.highlightElement(sentFolder, driver);
        sentFolder.click();
    }

    public void dragSentEmailToDraftFolder(int emailIndex) {
        log.info("Drag a sent email to a draft folder.");
        new ActionsUtil(driver).dragAndDrop(new EmailListBlock(driver).getEmailList().get(emailIndex), draftFolder);
    }

    public void contextClickOnEmailByIndex(int index) {
        new ActionsUtil(driver).contextClick(new EmailListBlock(driver).getEmailList().get(index));
    }

    public void clickDelete() {
        log.info("Clicking on delete button.");
        contextForm.waitForContexMenuVisible(driver);
        JSCommandsHelper.highlightElement(contextForm, driver);
        contextForm.clickDeleteItem();
    }
    
    public void logOut() {
        settingsPopUpForm.waitForLogOutVisible(driver);
        JSCommandsHelper.highlightElement(settingsPopUpForm, driver);
        settingsPopUpForm.logOut();
    }
}
