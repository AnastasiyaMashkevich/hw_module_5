package com.epam.yandex.util;

import com.epam.yandex.test.YandexTest;
import com.google.common.base.Function;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by anastasiya_mashkevic on 12/20/17.
 */
public class WaitUtil {

	private static final Logger LOG = Logger.getLogger(WaitUtil.class);
	//this static block needs to configure logger

	public static void waitForElementIsDisplayed(WebDriver driver, WebElement element, int timeoutSec) {
		FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
		fluentWait.withTimeout(timeoutSec, TimeUnit.SECONDS).pollingEvery(60, TimeUnit.MILLISECONDS).until(
				(Function<? super WebDriver, Boolean>) input -> {
					System.out.println("▼ Wait until an element is visible");
					return element.isDisplayed();
				});
	}
}
