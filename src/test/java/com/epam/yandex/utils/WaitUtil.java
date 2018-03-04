package com.epam.yandex.utils;

import com.google.common.base.Function;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Sleeper;

import java.util.concurrent.TimeUnit;

public class WaitUtil {
	private static Logger log = Logger.getLogger(WaitUtil.class);

	public static void waitForElementIsDisplayed(WebDriver driver, WebElement element, int timeoutSec) {
		FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
		fluentWait.withTimeout(timeoutSec, TimeUnit.SECONDS).pollingEvery(60, TimeUnit.MILLISECONDS)
				.withMessage("No element was not visible for " + timeoutSec + " seconds.").until(
				(Function<? super WebDriver, Boolean>) input -> {
					log.info("▼ Wait until an element is visible");
					return element.isDisplayed();
				});
	}

	public static void sleep(int timeoutInSec) {
		try {
			Sleeper.SYSTEM_SLEEPER.sleep(new Duration(timeoutInSec, TimeUnit.SECONDS));
		} catch (InterruptedException e) {
			log.error("The method was stopped and was not finished.");
			e.printStackTrace();
		}
	}
}
