package com.epam.yandex.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YandexMailPage extends AbstractPage{

    @FindBy (xpath = "//div[@class = 'mail-User-Name']")
    private WebElement userMail;

    public YandexMailPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);

    }

    public YandexMailPage () {}

    public void openPage () {
        driver.navigate().to(StaticParamClass.BASE_URL);
    }

    public boolean singInIsSuccess () {
        return userMail.getText().equals(StaticParamClass.LOGIN);
    }
}
