package com.epam.yandex.util;

import com.google.common.base.Function;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Sleeper;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class WaitUtil {

	public static void waitForElementIsDisplayed(WebDriver driver, WebElement element, int timeoutSec) {
		FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
		fluentWait.withTimeout(timeoutSec, TimeUnit.SECONDS).pollingEvery(60, TimeUnit.MILLISECONDS)
				.withMessage("No element was not visible for " + timeoutSec + " seconds.").until(
				(Function<? super WebDriver, Boolean>) input -> {
					System.out.println("▼ Wait until an element is visible");
					return element.isDisplayed();
				});
	}

	public static void waitForElementsSizeAppear(WebDriver driver, List<WebElement> webElement, int elementsNumber, int timeoutSec) {
		FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
		fluentWait.withTimeout(timeoutSec, TimeUnit.SECONDS).pollingEvery(60, TimeUnit.MILLISECONDS).until((new Function<WebDriver, Boolean>() {
			@Override public Boolean apply(WebDriver driver) {
				int visibleSize = webElement.stream().map(WebElement::isDisplayed).filter(v -> v).collect(Collectors.toList()).size();
				return visibleSize == elementsNumber;
			}
		}));
	}

	public static void sleep(int timeoutInSec) {
		try {
			Sleeper.SYSTEM_SLEEPER.sleep(new Duration(timeoutInSec, TimeUnit.SECONDS));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
