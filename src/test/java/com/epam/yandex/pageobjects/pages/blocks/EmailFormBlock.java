package com.epam.yandex.pageobjects.pages.blocks;

import com.epam.yandex.pageobjects.BasePage;
import com.epam.yandex.util.JavaScriptUtil;
import com.epam.yandex.util.constant.ProjectConstant;
import com.epam.yandex.util.WaitUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmailFormBlock extends BasePage {

	@FindBy(xpath = "//div[contains(@name, 'to')]")
	private WebElement addresseeEmailField;

	@FindBy (xpath = "//input[contains(@name, 'subj')]")
	private WebElement emailSubject;

	@FindBy (xpath = "//div/div[@role = 'textbox']")
	private WebElement emailBody;

	@FindBy (xpath = "//div/div[@role = 'textbox']/div")
	private WebElement emailBodyText;

	@FindBy (xpath = "//div[contains(@data-key, 'view=compose-cancel-button')]")
	private WebElement closeEmail;

	@FindBy (xpath = "//span[contains(@class, 'mail-Bubble-Block_text')]")
	private WebElement addresseeEmail;

	@FindBy (xpath = "//button[contains(@class, 'js-send-button')]")
	private WebElement sendEmailBtn;

	public EmailFormBlock(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public boolean isOpened() {
		return sendEmailBtn.isDisplayed();
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

	public String getEmailSubject() {
		return emailSubject.getAttribute(ProjectConstant.ATTRIBUTE_VALUE);
	}

	public String getBodyText() {
		return JavaScriptUtil.getElementTextValue(driver, emailBodyText);
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

	public void waitForNewEmailFormIsOpened() {
		WaitUtil.waitForElementIsDisplayed(driver, sendEmailBtn, ProjectConstant.TIME_20_SEC);
	}
}
