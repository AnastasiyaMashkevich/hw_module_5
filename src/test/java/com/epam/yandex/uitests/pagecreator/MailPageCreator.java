package com.epam.yandex.uitests.pagecreator;

import com.epam.yandex.pageobjects.pages.YandexMailPage;
import org.openqa.selenium.WebDriver;

public class MailPageCreator extends PageCreator {

    @Override
    public YandexMailPage createPage(WebDriver webDriver) {
       return new YandexMailPage(webDriver);
    }
}
