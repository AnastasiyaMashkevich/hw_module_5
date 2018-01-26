package com.epam.yandex.uitests.utils.jscommand.command;

import com.epam.yandex.uitests.utils.jscommand.Commands;
import org.openqa.selenium.WebDriver;


public class ClickOnElement extends JSCommand<Boolean> {

	public ClickOnElement(WebDriver driver) {
		super(driver);
	}

	@Override
	public String build() {
		return Commands.CLICK_ON_ELEMENT.getCommandString();
	}
}
