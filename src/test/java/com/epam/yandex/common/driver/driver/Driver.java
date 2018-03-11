package com.epam.yandex.common.driver.driver;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class Driver implements WebDriver {

	private static Logger log = Logger.getLogger(Driver.class);

	private WebDriver driver;

	public Driver(WebDriver driver) {
		this.driver = driver;
	}

	public void get(String url) {
		log.info("Browser goes to " + url);
		driver.get(url);
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public List<WebElement> findElements(By by) {
		return driver.findElements(by);
	}

	public WebElement findElement(By by) {
		return driver.findElement(by);
	}

	public String getPageSource() {
		log.info("Browser gets page source.");
		return driver.getPageSource();
	}

	public void close() {
		driver.close();
	}

	public void quit() {
		log.info("Browser will be closed.");
		driver.quit();
	}

	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	public TargetLocator switchTo() {
		return driver.switchTo();
	}

	public Navigation navigate() {
		return driver.navigate();
	}

	public Options manage() {
		return driver.manage();
	}
}
