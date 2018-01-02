package com.epam.yandex.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static WebDriver driver;

    private static final String WEBDRIVER_GECKO_DRIVER = "webdriver.gecko.driver";
    private static final String WEBDRIVER_CHROME = "webdriver.chrome.driver";
    private static final String GECKODRIVER_PATH = "./src/test/resources/geckodriver";
    private static final String CHROME_DRIVER = "./src/test/resources/chromedriver.exe";

    private DriverFactory() {}

    public static  WebDriver getDriver(String browser) {
        if (driver == null) {
            {
                switch (browser) {
                    case "chrome": {
                        synchronized (DriverFactory.class) {
                            if (driver == null) {
                                System.setProperty(WEBDRIVER_CHROME, CHROME_DRIVER);
                                try {
                                    driver = new RemoteWebDriver(new URL("http://192.168.100.5:4444/wd/hub"), new ChromeOptions());
                                } catch (Exception e) {
                                    System.out.println("Web Driver was not created.");
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    case "firefox": {
                        synchronized (DriverFactory.class) {
                            if (driver == null) {
                                System.setProperty(WEBDRIVER_GECKO_DRIVER, GECKODRIVER_PATH);
                                driver = new FirefoxDriver();
                            }
                        }
                    }
                }
            }
        }
        driver.manage().timeouts().pageLoadTimeout(35, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }
}
