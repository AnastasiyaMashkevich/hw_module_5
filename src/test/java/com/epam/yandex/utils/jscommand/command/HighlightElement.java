package com.epam.yandex.utils.jscommand.command;

import com.epam.yandex.utils.jscommand.Commands;
import org.apache.commons.lang3.StringEscapeUtils;
import org.openqa.selenium.WebDriver;


public class HighlightElement extends JSCommand<Boolean> {

	public HighlightElement(WebDriver driver) {
		super(driver);
	}

	@Override
	public String build() {
		return Commands.HIGHLIGHT_ELEMENT.getCommandString();
	}
}
