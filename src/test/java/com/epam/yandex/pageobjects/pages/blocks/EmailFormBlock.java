package com.epam.yandex.pageobjects.pages.blocks;

import com.epam.yandex.pageobjects.pages.BasePage;
import com.epam.yandex.uitests.constant.ProjectConstant;
import com.epam.yandex.utils.WaitUtil;
import com.epam.yandex.utils.JSCommandsHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmailFormBlock extends BasePage {
	private static Logger log = Logger.getLogger(EmailFormBlock.class);

	public EmailFormBlock(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

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

	public boolean isOpened() {
		JSCommandsHelper.highlightElement(sendEmailBtn, driver);
		return sendEmailBtn.isDisplayed();
	}

	public void setEmailSubject(String subject) {
		JSCommandsHelper.highlightElement(emailSubject, driver);
		emailSubject.sendKeys(subject);
	}

	public void setEmailBody(String body) {
		JSCommandsHelper.highlightElement(emailBody, driver);
		emailBody.sendKeys(body);
	}

	public void clickCloseEmail(){
		JSCommandsHelper.highlightElement(closeEmail, driver);
		closeEmail.click();
	}

	public String getEmailSubject() {
		return emailSubject.getAttribute(ProjectConstant.ATTRIBUTE_VALUE);
	}

	public String getBodyText() {
		return JSCommandsHelper.getInvisibleElementsText(emailBodyText, driver);
	}

	public String getAddresseeEmail() {
		JSCommandsHelper.highlightElement(addresseeEmail, driver);
		return addresseeEmail.getText();
	}

	public void setAddresseeEmail(String email) {
		addresseeEmailField.sendKeys(email);
	}

	public void clickSendEmail() {
		log.info("Sending an email. ");
		JSCommandsHelper.highlightElement(sendEmailBtn, driver);
		sendEmailBtn.click();
	}

	public void waitForNewEmailFormIsOpened() {
		log.info("Waiting for a new email form opened. ");
		WaitUtil.waitForElementIsDisplayed(driver, sendEmailBtn, ProjectConstant.TimeConstant.TIME_20_SEC);
	}
}
