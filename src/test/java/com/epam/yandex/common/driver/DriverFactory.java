package com.epam.yandex.common.driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static WebDriver driver;

    private static final String WEBDRIVER_GECKO_DRIVER = "webdriver.gecko.driver";
    private static final String WEBDRIVER_CHROME = "webdriver.chrome.driver";
    private static final String GECKODRIVER_PATH = "./src/test/resources/geckodriver";
    private static final String CHROME_DRIVER = "./src/test/resources/chromedriver.exe";
    private static final String LOCALHOST = "http://localhost:4444/wd/hub";

    private DriverFactory() {}

    public static  WebDriver getDriver(String browser) {
        if (driver == null) {
            {
                switch (browser) {
                    case "chrome": {
                        synchronized (DriverFactory.class) {
                            if (driver == null) {
                                System.setProperty(WEBDRIVER_CHROME, CHROME_DRIVER);
                                DesiredCapabilities capability = DesiredCapabilities.chrome();
                                capability.setBrowserName("chrome" );
                                //capability.setPlatform(Platform.MAC);
                                try {
                                    //driver = new RemoteWebDriver(new URL(LOCALHOST), capability);
                                    driver = new ChromeDriver(capability);
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
                                try {
                                    driver = new RemoteWebDriver(new URL(LOCALHOST), new FirefoxOptions());
                                } catch (MalformedURLException e) {
                                    System.out.println("Web Driver was not created.");
                                    e.printStackTrace();
                                }
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
