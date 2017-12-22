package com.epam.yandex.test;

import com.epam.yandex.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {
	protected WebDriver driver;

	@BeforeTest(description = "Init driver")
	public WebDriver initDriver() {
		System.out.println("BeforeTest: Init driver");
		return driver = DriverFactory.getDriver("chrome");
	}

	@AfterTest(description = "Stop Browser")
	public void stopBrowser() {
		driver.quit();
		System.out.println("AfterTest: close browser");
	}
}
