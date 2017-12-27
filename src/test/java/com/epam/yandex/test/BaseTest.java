package com.epam.yandex.test;

import com.epam.yandex.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class BaseTest {
	protected WebDriver driver;

	@BeforeClass(description = "Init driver")
	public WebDriver initDriver() {
		System.out.println("BeforeClass: Init driver");
		return driver = DriverFactory.getDriver("chrome");
	}

	@AfterClass(description = "Stop Browser")
	public void stopBrowser() {
		driver.quit();
		System.out.println("AfterClass: close browser");
	}
}
