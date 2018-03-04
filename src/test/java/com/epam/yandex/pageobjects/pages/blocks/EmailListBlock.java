package com.epam.yandex.pageobjects.pages.blocks;

import com.epam.yandex.pageobjects.pages.BasePage;
import com.epam.yandex.utils.JSCommandsHelper;
import com.epam.yandex.utils.WaitUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

//this block contains list of emails(sent, draft or received)
public class EmailListBlock extends BasePage {

	private static Logger log = Logger.getLogger(EmailListBlock.class);

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
		List<String> emailList = subjectList.stream().map(WebElement::getText).collect(Collectors.toList());
		if(emailList.isEmpty()) {
			log.warn("We did not find any emails with a subject.");
			throw new RuntimeException("There is no existing emails.");
		}
		return emailList;
	}

	public void clickOnEmail(int index) {
		log.info("Opening an email.");
		JSCommandsHelper.highlightElement(emailList.get(index), driver);
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
