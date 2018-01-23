package com.epam.yandex.pageobjects.pages.forms;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name(value = "User Settings Form")
@FindBy(xpath = "//div[contains(@class, 'b-user-dropdown-content')]")
public class UserSettingsPopUpForm extends HtmlElement {

	@FindBy (xpath = "/div/a[contains(@data-metric, 'Выход')]") //understand that is not a good idea use Russian letters here, but I did not have another way
	private WebElement logOutBtn;

	public void logOut() {
		logOutBtn.click();
	}
}
