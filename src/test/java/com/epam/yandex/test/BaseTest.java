package com.epam.yandex.test;

import com.epam.yandex.driver.DriverFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


public class BaseTest {
	private static final Logger LOG = Logger.getLogger(BaseTest.class);
	protected WebDriver driver;

	@BeforeTest(description = "Init browser")
	public WebDriver initBrowser() throws Exception {
		return driver = DriverFactory.getDriver("chrome");
	}

	@AfterTest(description = "Stop Browser")
	public void stopBrowser() {
		driver.quit();
		LOG.info("AfterTest: close browser");
	}
}
