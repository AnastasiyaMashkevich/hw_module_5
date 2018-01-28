package com.epam.yandex.uitests.pagecreator;

import com.epam.yandex.pageobjects.pages.YandexMainPage;
import org.openqa.selenium.WebDriver;


public class MainPageCreator extends PageCreator {

    @Override
    public YandexMainPage createPage(WebDriver webDriver) {
       return new YandexMainPage(webDriver);
    }
}
