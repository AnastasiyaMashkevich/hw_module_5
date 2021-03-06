package com.epam.yandex.pageobjects.pages.forms;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name(value = "Log In Form")
@FindBy(xpath = "//div[contains(@role, 'form')]")
public class LogInForm extends HtmlElement {
	private static Logger log = Logger.getLogger(LogInForm.class);

	@FindBy(xpath = "//input[@name = 'login']")
	private WebElement fieldLogin;

	@FindBy (xpath = "//input[@name = 'passwd']")
	private WebElement fieldPassword;

	@FindBy (xpath = "//button[contains(@class, 'auth__button')]")
	private WebElement submit;

	public void singIn(String login, String psw) {
		log.info("Logging In.");
		fieldLogin.sendKeys(login);
		fieldPassword.sendKeys(psw);
		submit.click();
	}

	public boolean subminIsVisible() {
		return submit.isDisplayed();
	}
}
