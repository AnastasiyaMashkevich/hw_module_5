package com.epam.yandex.common.driver.driverfactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class ChromeDriver implements DriverCreator {

	private static Logger log = Logger.getLogger(ChromeDriver.class);
	private static WebDriver driver;

	private static final String WEBDRIVER_CHROME = "webdriver.chrome.driver";
	private static final String CHROME_DRIVER = "./src/test/resources/chromedriver";
	private static final String LOCALHOST = "http://localhost:4444/wd/hub";

	@Override
	public WebDriver getDriver() {
		System.setProperty(WEBDRIVER_CHROME, CHROME_DRIVER);
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		capability.setBrowserName("chrome");
		capability.setPlatform(Platform.MAC);
		try {
			driver = new RemoteWebDriver(new URL(LOCALHOST), capability);
		} catch (Exception e) {
			log.info("Web Driver was not created.");
			e.printStackTrace();
		}
		return driver;
	}
}
