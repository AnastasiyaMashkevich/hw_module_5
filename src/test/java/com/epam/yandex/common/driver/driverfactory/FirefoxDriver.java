package com.epam.yandex.common.driver.driverfactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


public class FirefoxDriver implements DriverCreator {

	private static Logger log = Logger.getLogger(FirefoxDriver.class);
	private static WebDriver driver;

	private static final String WEBDRIVER_GECKO_DRIVER = "webdriver.gecko.driver";
	private static final String GECKODRIVER_PATH = "./src/test/resources/geckodriver";
	private static final String LOCALHOST = "http://localhost:4444/wd/hub";

	@Override
	public WebDriver getDriver() {
		System.setProperty(WEBDRIVER_GECKO_DRIVER, GECKODRIVER_PATH);
		try {
			driver = new RemoteWebDriver(new URL(LOCALHOST), new FirefoxOptions());
		} catch (MalformedURLException e) {
			log.info("Web Driver was not created.");
			e.printStackTrace();
		}
		return driver;
	}
}
