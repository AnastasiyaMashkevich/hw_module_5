package com.epam.yandex.pageobjects.pages;

import com.epam.yandex.pageobjects.pages.forms.LogInForm;
import com.epam.yandex.util.constant.ProjectConstant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class YandexMainPage extends BasePage {

    private LogInForm logInForm;

    public YandexMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    @FindBy(xpath = "//div[@class = 'home-logo']")
    private WebElement logo;

    @FindBy(xpath = "*//input[@id ='text']")
    private WebElement requestInput;

    @FindBy(xpath = "//div[@class = 'search2__button']/button[@type='submit']")
    private WebElement searchButton;
    
    public void openPage() {
        driver.navigate().to(ProjectConstant.BASE_URL);
    }

    public boolean isOpened() {
        return logo.isDisplayed();
    }

    public YandexMailPage logIn(String login, String psw) {
        logInForm.singIn(login, psw);
        return new YandexMailPage(driver);
    }

    public boolean isSubmitVisible() {
        return logInForm.subminIsVisible();
    }
}
