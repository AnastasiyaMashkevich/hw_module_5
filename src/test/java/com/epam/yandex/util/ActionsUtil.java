package com.epam.yandex.util;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsUtil {
    private Actions builder;

    public ActionsUtil(WebDriver driver) {
        builder = new Actions(driver);
    }

    public void contextClick(WebElement element) {
        builder.contextClick(element).build().perform();
    }

    public void dragAndDrop(WebElement from, WebElement to) {
        builder.pause(1000).clickAndHold(from).moveToElement(to).release(to).build().perform();
    }
}
