package com.epam.yandex.common.driver.driverfactory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;


public class ChromeDriver implements DriverFactory {

	private static WebDriver driver;

	private static final String WEBDRIVER_CHROME = "webdriver.chrome.driver";
	private static final String CHROME_DRIVER = "./src/test/resources/chromedriver";
	private static final String LOCALHOST = "http://localhost:4444/wd/hub";

	@Override
	public WebDriver getDriver() {
		if (driver == null) {
			System.setProperty(WEBDRIVER_CHROME, CHROME_DRIVER);
			DesiredCapabilities capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome" );
			capability.setPlatform(Platform.MAC);
			try {
				driver = new RemoteWebDriver(new URL(LOCALHOST), capability);
				driver.manage().timeouts().pageLoadTimeout(35, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
				driver.manage().window().maximize();
			} catch (Exception e) {
				System.out.println("Web Driver was not created.");
				e.printStackTrace();
			}

		}
		return driver;
	}
}
