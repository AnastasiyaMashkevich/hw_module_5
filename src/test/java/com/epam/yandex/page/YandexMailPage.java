package com.epam.yandex.page;

import com.epam.yandex.util.WaitUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.Sleeper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class YandexMailPage extends AbstractPage{

    @FindBy (xpath = "//div[@class = 'mail-User-Name']")
    private WebElement userMail;

    @FindBy (xpath = "//div[@class = 'mail-App-Header']")
    private WebElement mailHeader;

    @FindBy (xpath = "//a[contains(@class, 'mail-ComposeButton')]")
    private WebElement createNew;

    @FindBy (xpath = "//button[contains(@class, 'js-send-button')]")
    private WebElement sendEmailBtn;

    @FindBy (xpath = "//span[contains(@class, 'mail-Bubble-Block_text')]")
    private WebElement addresseeEmail;

    @FindBy (xpath = "//div[contains(@name, 'to')]")
    private WebElement addresseeEmailField;

    @FindBy (xpath = "//input[contains(@name, 'subj')]")
    private WebElement emailSubject;

    @FindBy (xpath = "//div/div[@role = 'textbox']")
    private WebElement emailBody;

    @FindBy (xpath = "//div/div[@role = 'textbox']/div")
    private WebElement emailBodyText;

    @FindBy (xpath = "//div[contains(@data-key, 'view=compose-cancel-button')]")
    private WebElement closeEmail;

    @FindBy (xpath = "//div[contains(@class, 'ui-dialog-fixed')]")
    private WebElement saveChangesPopup;

    @FindBy (xpath = "//button[contains(@data-action, 'save')]")
    private WebElement saveAsDraftBtn;

    @FindBy (xpath = "//a[contains(@data-fid, '6')]")
    private WebElement draftFolder;

    @FindBy (xpath = "//a[contains(@data-fid, '4')]")
    private WebElement sentFolder;

    @FindBy (xpath = "//span[contains(@class, 'mail-MessageSnippet-Item_subjectWrapper')]/span")
    private List<WebElement> subjectList;

    @FindBy (xpath = "//a[contains(@class, 'mail-MessageSnippet')]")
    private List<WebElement> emailList;

    public YandexMailPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);

    }

    public void openPage () {
        driver.navigate().to(StaticParamClass.BASE_URL);
    }

    public boolean singInIsSuccess () {
        WaitUtil.waitForElementIsDisplayed(driver, mailHeader, 20);
        return userMail.getText().equals(StaticParamClass.LOGIN);
    }

    public void openNewFormLetter() {
        createNew.click();
        WaitUtil.waitForElementIsDisplayed(driver, sendEmailBtn, 20);
    }

    public void setEmailSubject(String subject) {
        emailSubject.sendKeys(subject);
    }

    public void setEmailBody(String body) {
        emailBody.sendKeys(body);
    }

    public void clickCloseEmail(){
        closeEmail.click();
    }

    public void saveEmailAsDraft() {
        WaitUtil.waitForElementIsDisplayed(driver, saveChangesPopup, 20);
        saveAsDraftBtn.click();
    }

    public List<String> getSubjectList() {
        return subjectList.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void clickOnEmail(int index) {
        emailList.get(index).click();
    }

    public String getEmailSubject() {
        return emailSubject.getAttribute("value");
    }

    public String getBodyText() {
        return emailBodyText.getText();
    }

    public String getAddresseeEmail() {
        return addresseeEmail.getText();
    }

    public void setAddresseeEmail(String email) {
        addresseeEmailField.sendKeys(email);
    }

    public void clickSendEmail() {
        sendEmailBtn.click();
    }

    public void openDraftFolder() {
        draftFolder.click();
    }

    public void openSentFolder() {
        sentFolder.click();
    }

    public boolean isEmailExist(String query) {
        try {
            Sleeper.SYSTEM_SLEEPER.sleep(new Duration(10, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getSubjectList().contains(query);
    }

    public void openUserSettings() {
        userMail.click();
    }


}
