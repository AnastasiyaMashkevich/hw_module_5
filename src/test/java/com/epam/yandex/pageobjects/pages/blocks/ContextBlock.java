package com.epam.yandex.pageobjects.pages.blocks;

import com.epam.yandex.util.WaitUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Context form")
@FindBy(xpath = "//div[contains(@class, 'context-menu-content')]")
public class ContextBlock extends HtmlElement {

    @FindBy(xpath = "//div[contains(@class, 'context-menu-content')]")
    WebElement contextMenu;

    @FindBy(xpath = "//span[contains(@class, 'context-menu-item__icon_delete')]/..")
    WebElement deleteItem;

    public void clickDeleteItem() {
        deleteItem.click();
    }

    public void waitForContexMenuVisible(WebDriver webDriver) {
        WaitUtil.waitForElementIsDisplayed(webDriver, contextMenu, 5);
    }
}
