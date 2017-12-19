package com.epam.yandex.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YandexResultSearchPage extends AbstractPage{

    public YandexResultSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy (xpath = "*//li[1][contains (@class,'serp-item')]")
    private WebElement result;

    public boolean assertResultSearch ()
    {
        return result.getText().contains(StaticParamClass.QUERY);
    }

    public void openPage() {
        driver.navigate().to(StaticParamClass.BASE_URL);
    }

}
