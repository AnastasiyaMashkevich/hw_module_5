package com.epam.yandex.uitests.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

    private static final String CLICK = "arguments[0].click();";
    private static final String INNER = "return arguments[0].innerHTML;";

    public static void jsClickOnElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor)driver).executeScript(CLICK, element);
    }

    public static String getElementTextValue(WebDriver driver, WebElement element) {
       return (String)((JavascriptExecutor)driver).executeScript(INNER, element);
    }
}
