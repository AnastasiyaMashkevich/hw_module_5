package com.epam.yandex.page;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public abstract void openPage();

}