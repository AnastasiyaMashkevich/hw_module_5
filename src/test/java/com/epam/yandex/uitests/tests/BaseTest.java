package com.epam.yandex.uitests.tests;

import com.epam.yandex.common.driver.driverfactory.DriverFactory;
import com.epam.yandex.model.User;
import com.epam.yandex.service.UserService;
import com.epam.yandex.uitests.pagecreator.PageCreator;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {
	private static Logger log = Logger.getLogger(BaseTest.class);

	protected static final int FIRST_ITEM = 0;

	protected User user;
	protected WebDriver driver;
	protected PageCreator pageCreator;

	@BeforeClass(description = "Init driver and user")
	@Parameters("driver")
	public void init(String driverName) {
		log.info("BeforeClass: Init driver and user");
		driver = DriverFactory.getDriver(driverName);
		user = new UserService().getUserList().get(FIRST_ITEM);
	}

	@AfterClass(description = "Stop Browser")
	public void stopBrowser() {
		driver.quit();
		log.info("AfterClass: close browser");
	}
}
