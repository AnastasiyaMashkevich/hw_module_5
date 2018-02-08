package com.epam.yandex.uitests.tests;

import com.epam.yandex.common.driver.DriverFactory;
import com.epam.yandex.model.User;
import com.epam.yandex.pageobjects.pages.YandexMainPage;
import com.epam.yandex.service.UserService;
import cucumber.api.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(strict = true, plugin = { "json:target/cucumber-report.json",
		"html:target/cucumber-report" },
		features = {"./src/test/resources/cucumber_features/yandex_test.feature",
		 "./src/test/resources/cucumber_features/yandex_addition_test.feature"},
		glue = {"com.epam.yandex.uitests.cucumbersteps" })

public class YandexCucumberTest extends AbstractTestNGCucumberTests {

	protected User user;
	protected WebDriver driver= DriverFactory.getDriver("chrome");;

	@BeforeClass(description = "Init driver and user")
	public void init() {
		System.out.println("BeforeClass: Init driver and user");
		user = new UserService().getUserList().get(0);
		YandexMainPage yandexMainPage = new YandexMainPage(driver);
		yandexMainPage.openPage();
		yandexMainPage.logIn(user.getLogin(), user.getPsw());
	}

	@AfterClass(description = "Stop Browser")
	public void stopBrowser() {
		driver.quit();
		System.out.println("AfterClass: close browser");
	}

}
