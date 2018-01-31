package com.epam.yandex.pageobjects.pages.forms;


import com.epam.yandex.uitests.constant.ProjectConstant;
import com.epam.yandex.utils.WaitUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Context form")
@FindBy(xpath = "//div[contains(@class, 'context-menu-content')]")
public class ContextForm extends HtmlElement {

    @FindBy(xpath = "//div[contains(@class, 'context-menu-content')]")
    private WebElement contextMenu;

    @FindBy(xpath = "//span[contains(@class, 'context-menu-item__icon_delete')]/..")
    private WebElement deleteItem;

    public void clickDeleteItem() {
        deleteItem.click();
    }

    public void waitForContexMenuVisible(WebDriver webDriver) {
        WaitUtil.waitForElementIsDisplayed(webDriver, contextMenu, ProjectConstant.TimeConstant.TIME_10_SEC);
    }
}
