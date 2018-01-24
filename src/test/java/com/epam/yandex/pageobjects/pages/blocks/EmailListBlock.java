package com.epam.yandex.pageobjects.pages.blocks;

import com.epam.yandex.pageobjects.pages.BasePage;
import com.epam.yandex.uitests.utils.WaitUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

//this block contains list of emails(sent, draft or received)
public class EmailListBlock extends BasePage {

	public EmailListBlock(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@FindBy (xpath = "//span[contains(@class, 'mail-MessageSnippet-Item_subjectWrapper')]/span")
	private List<WebElement> subjectList;

	@FindBy (xpath = "//a[contains(@class, 'mail-MessageSnippet')]")
	private List<WebElement> emailList;

	@FindBy (xpath = "//div[contains(@class, 'mail-Layout-Content')]//div[contains(@class, 'mail-MessagesList')]")
	private WebElement allMessagesBlock;

	public boolean isOpened() {
		return allMessagesBlock.isDisplayed();
	}

	public List<String> getSubjectList() {
		WaitUtil.sleep(5); // here we can't use some kind of WebDriver Wait because we receive StaleElementReferenceException. Only sleep helps here.
		return subjectList.stream().map(WebElement::getText).collect(Collectors.toList());
	}

	public void clickOnEmail(int index) {
		emailList.get(index).click();
	}

	public int getDraftEmailNumber() {
		return getSubjectList().size();
	}

	public int getSentEmailNumber() {
		return getSubjectList().size();
	}

	public boolean isEmailExist(String query) {
		return getSubjectList().contains(query);
	}

	public List<WebElement> getEmailList() {
		return emailList;
	}
}
