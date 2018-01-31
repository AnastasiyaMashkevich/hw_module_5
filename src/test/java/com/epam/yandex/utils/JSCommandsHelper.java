package com.epam.yandex.utils;

import com.epam.yandex.utils.jscommand.command.ClickOnElement;
import com.epam.yandex.utils.jscommand.command.GetInvisibleElementsText;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSCommandsHelper {

	public static String getInvisibleElementsText(WebElement element, WebDriver webDriver) {
		return new GetInvisibleElementsText(webDriver).execute(element);
	}

	public static Boolean clickOnElement(WebElement element, WebDriver webDriver) {
		return new ClickOnElement(webDriver).execute(element);
	}
}
