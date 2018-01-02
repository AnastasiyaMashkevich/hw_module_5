package com.epam.yandex.pageobjects.blocks;

import com.epam.yandex.pageobjects.BasePage;
import com.epam.yandex.util.WaitUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContextBlock extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'context-menu-content')]")
    WebElement contextMenu;

    @FindBy(xpath = "//span[contains(@class, 'context-menu-item__icon_delete')]/..")
    WebElement deleteItem;

    public ContextBlock(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public boolean isOpened() {
        return contextMenu.isDisplayed();
    }

    public void clickDeleteItem() {
        WaitUtil.waitForElementIsDisplayed(driver, contextMenu, 30);
        deleteItem.click();
    }
}
