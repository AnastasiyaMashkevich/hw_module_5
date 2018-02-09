package com.epam.yandex.common.driver.driverfactory;


import com.epam.yandex.common.driver.driver.Driver;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static WebDriver driver;
    private static Driver driverDecorator;

    private DriverFactory() {}

    public static  WebDriver getDriver(String browser) {
        if (driver == null) {
            {
                switch (browser) {
                case "chrome": {
                    synchronized (DriverFactory.class) {
                        DriverCreator creator = new ChromeDriver();
                        driver = creator.getDriver();
                        break;
                    }
                }
                case "firefox": {
                    synchronized (DriverFactory.class) {
                        if (driver == null) {
                            DriverCreator creator = new FirefoxDriver();
                            driver = creator.getDriver();
                            break;
                        }
                    }
                }
                }
            }
        }
        driver.manage().timeouts().pageLoadTimeout(35, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driverDecorator = new Driver(driver);

        return driver;
    }

}
