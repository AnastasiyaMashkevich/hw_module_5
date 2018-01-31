package com.epam.yandex.pageobjects.pages.blocks;

import com.epam.yandex.pageobjects.pages.BasePage;
import com.epam.yandex.utils.WaitUtil;
import com.epam.yandex.uitests.constant.ProjectConstant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderBlock extends BasePage {

	public HeaderBlock(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(xpath = "//div[@class = 'mail-User-Name']")
	private WebElement userMail;

	@FindBy (xpath = "//a[contains(@class, 'mail-ComposeButton')]")
	private WebElement createNew;

	@FindBy (xpath = "//div[@class = 'mail-App-Header']")
	private WebElement mailHeader;

	public boolean singInIsSuccess(String login) {
		WaitUtil.waitForElementIsDisplayed(driver, mailHeader, ProjectConstant.TimeConstant.TIME_20_SEC);
		return userMail.getText().equals(login);
	}

	public void openNewFormLetter() {
		createNew.click();
	}

	public void openUserSettings() {
		userMail.click();
	}

}
