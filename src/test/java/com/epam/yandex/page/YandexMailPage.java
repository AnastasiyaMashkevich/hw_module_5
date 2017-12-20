package com.epam.yandex.page;

import com.epam.yandex.util.WaitUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YandexMailPage extends AbstractPage{

    @FindBy (xpath = "//div[@class = 'mail-User-Name']")
    private WebElement userMail;

    @FindBy (xpath = "//div[@class = 'mail-App-Header']")
    private WebElement mailHeader;

    public YandexMailPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);

    }

    public void openPage () {
        driver.navigate().to(StaticParamClass.BASE_URL);
    }

    public boolean singInIsSuccess () {
        WaitUtil.waitForElementIsDisplayed(driver, mailHeader, 20);
        return userMail.getText().equals(StaticParamClass.LOGIN);
    }
}
