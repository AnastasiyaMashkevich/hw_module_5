package com.epam.yandex.common.driver.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class FirefoxDriver implements DriverFactory {

	private static final String WEBDRIVER_GECKO_DRIVER = "webdriver.gecko.driver";
	private static final String GECKODRIVER_PATH = "./src/test/resources/geckodriver";
	private static final String LOCALHOST = "http://localhost:4444/wd/hub";

	private static WebDriver driver;

	@Override
	public WebDriver getDriver() {
		if (driver == null) {
			System.setProperty(WEBDRIVER_GECKO_DRIVER, GECKODRIVER_PATH);
			try {
				driver = new RemoteWebDriver(new URL(LOCALHOST), new FirefoxOptions());
			} catch (MalformedURLException e) {
				System.out.println("Web Driver was not created.");
				e.printStackTrace();
			}

			driver.manage().timeouts().pageLoadTimeout(35, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		return driver;
	}
}
