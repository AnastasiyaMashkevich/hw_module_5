package com.epam.yandex.pageobjects.pages.blocks;

import com.epam.yandex.pageobjects.pages.BasePage;
import com.epam.yandex.util.WaitUtil;
import com.epam.yandex.util.constant.ProjectConstant;
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

	public boolean singInIsSuccess() {
		WaitUtil.waitForElementIsDisplayed(driver, mailHeader, ProjectConstant.TIME_20_SEC);
		return userMail.getText().equals(ProjectConstant.LOGIN);
	}

	public void openNewFormLetter() {
		createNew.click();
	}

	public void openUserSettings() {
		userMail.click();
	}

}
