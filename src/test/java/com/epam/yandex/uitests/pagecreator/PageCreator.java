package com.epam.yandex.uitests.pagecreator;

import com.epam.yandex.pageobjects.pages.BasePage;
import org.openqa.selenium.WebDriver;


public abstract class PageCreator {

    public abstract BasePage createPage(WebDriver webDriver);
}
