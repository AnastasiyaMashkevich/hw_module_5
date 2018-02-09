package com.epam.yandex.uitests.tests;

import com.epam.yandex.common.driver.driverfactory.ChromeDriver;
import com.epam.yandex.model.User;
import com.epam.yandex.service.UserService;
import com.epam.yandex.uitests.pagecreator.PageCreator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

	protected static final int FIRST_ITEM = 0;

	protected User user;
	protected WebDriver driver;
	protected PageCreator pageCreator;

	@BeforeClass(description = "Init driver and user")
	public void init() {
		System.out.println("BeforeClass: Init driver and user");
		driver = new ChromeDriver().getDriver();
		user = new UserService().getUserList().get(FIRST_ITEM);
	}

	@AfterClass(description = "Stop Browser")
	public void stopBrowser() {
		driver.quit();
		System.out.println("AfterClass: close browser");
	}
}
