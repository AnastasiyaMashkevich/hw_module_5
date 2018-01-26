package com.epam.yandex.uitests.utils.jscommand.command;

import com.epam.yandex.uitests.utils.jscommand.Commands;
import org.apache.commons.lang3.StringEscapeUtils;
import org.openqa.selenium.WebDriver;

public class GetInvisibleElementsText extends JSCommand<String> {

	public GetInvisibleElementsText(WebDriver driver) {
		super(driver);
	}

	@Override
	public String execute(Object... args) {
		return StringEscapeUtils.unescapeHtml4(super.execute(args));
	}

	@Override
	public String build() {
		return Commands.GET_INVISIBLE_ELEMENTS_TEXT.getCommandString();
	}
}
