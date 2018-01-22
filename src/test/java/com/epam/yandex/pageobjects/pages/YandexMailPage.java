package com.epam.yandex.pageobjects.pages;

import com.epam.yandex.pageobjects.BasePage;
import com.epam.yandex.pageobjects.pages.blocks.ContextBlock;
import com.epam.yandex.pageobjects.pages.blocks.EmailFormBlock;
import com.epam.yandex.pageobjects.pages.blocks.EmailListBlock;
import com.epam.yandex.util.ActionsUtil;
import com.epam.yandex.util.constant.ProjectConstant;
import com.epam.yandex.util.WaitUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class YandexMailPage extends BasePage {

    @FindBy (xpath = "//div[@class = 'mail-User-Name']")
    private WebElement userMail;

    @FindBy (xpath = "//div[@class = 'mail-App-Header']")
    private WebElement mailHeader;

    @FindBy (xpath = "//a[contains(@class, 'mail-ComposeButton')]")
    private WebElement createNew;

    @FindBy (xpath = "//div[contains(@class, 'ui-dialog-fixed')]")
    private WebElement saveChangesPopup;

    @FindBy (xpath = "//button[contains(@data-action, 'save')]")
    private WebElement saveAsDraftBtn;

    @FindBy (xpath = "//div[contains(@class, 'ns-view-left-box')]//a[contains(@href, '#draft')]")
    private WebElement draftFolder;

    @FindBy (xpath = "//div[contains(@class, 'ns-view-left-box')]//a[contains(@href, '#sent')]")
    private WebElement sentFolder;

    private EmailListBlock emailListBlock;
    private EmailFormBlock emailFormBlock;
    private ContextBlock contextBlock;

    public YandexMailPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
        emailListBlock = new EmailListBlock(driver);
        emailFormBlock = new EmailFormBlock(driver);

    }

    public EmailListBlock emailListBlock() {
        return emailListBlock;
    }

    public EmailFormBlock emailFormBlock() {
        return emailFormBlock;
    }


    @Override
    public boolean isOpened() {
        return createNew.isDisplayed();
    }

    public boolean singInIsSuccess() {
        WaitUtil.waitForElementIsDisplayed(driver, mailHeader, ProjectConstant.TIME_20_SEC);
        return userMail.getText().equals(ProjectConstant.LOGIN);
    }

    public void openNewFormLetter() {
        createNew.click();
    }

    public void saveEmailAsDraft() {
        WaitUtil.waitForElementIsDisplayed(driver, saveChangesPopup, ProjectConstant.TIME_20_SEC);
        saveAsDraftBtn.click();
    }

    public void openDraftFolder() {
        draftFolder.click();
    }

    public void openSentFolder() {
        sentFolder.click();
    }

    public void openUserSettings() {
        userMail.click();
    }

    public void dragSentEmailToDraftFolder(int emailIndex) {
        new ActionsUtil(driver).dragAndDrop(new EmailListBlock(driver).getEmailList().get(emailIndex), draftFolder);
    }

    public void contextClickOnEmailByIndex(int index) {
        new ActionsUtil(driver).contextClick(new EmailListBlock(driver).getEmailList().get(index));
    }

    public void clickDelete() {
        contextBlock.waitForContexMenuVisible(driver);
        contextBlock.clickDeleteItem();
    }
}
