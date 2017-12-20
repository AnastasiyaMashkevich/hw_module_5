package com.epam.yandex.test;

import com.epam.yandex.driver.DriverFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {
	private static final Logger LOG = Logger.getLogger(BaseTest.class);
	protected WebDriver driver;

	@BeforeTest(description = "Init browser")
	public WebDriver initBrowser() throws Exception {
		return driver = DriverFactory.getDriver("chrome");
	}

	@AfterClass(description = "Stop Browser")
	public void stopBrowser() {
		driver.quit();
		LOG.info("AfterTest: close browser");
	}
}
