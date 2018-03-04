package com.epam.yandex.utils;

import com.epam.yandex.pageobjects.pages.forms.LogInForm;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {
    private static final String SCREENSHOTS_NAME_TPL = "screenshots/failure_";
    private static Logger log = Logger.getLogger(com.epam.yandex.pageobjects.pages.forms.LogInForm.class);

    public static void takeScreenshot(WebDriver driver, String name) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotName = SCREENSHOTS_NAME_TPL + name;
            File copy = new File(screenshotName + ".png");
            FileUtils.copyFile(screenshot, copy);
            log.info("Saved screenshot: " + screenshotName);
        } catch (IOException e) {
            log.error("Failed to make screenshot");
        }
    }
}
