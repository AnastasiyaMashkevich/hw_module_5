package com.epam.yandex.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class YandexMainPage extends AbstractPage {

    @FindBy(xpath = "//div[@aria-label = 'Яндекс']")
    private WebElement logo;

    @FindBy (xpath = "//input[@placeholder = 'Логин']")
    private WebElement fieldLogin;

    @FindBy (xpath = "//input[@placeholder = 'Пароль']")
    private WebElement fieldPassword;

    @FindBy (xpath = "//button[contains(@class, 'auth__button')]")
    private WebElement submit;

    @FindBy(xpath = "*//input[@id ='text']")
    private WebElement requestInput;

    @FindBy(xpath = "//div[@class = 'search2__button']/button[@type='submit']")
    private WebElement searchButton;


    @FindBy (xpath = "//div/a[contains(@data-metric, 'Выход')]")
    private WebElement logOutBtn;


    public YandexMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void openPage () {
        driver.navigate().to(StaticParamClass.BASE_URL);
    }

    public void search(String query) {
        requestInput.sendKeys(query);
        searchButton.click();
    }

    public void search() {
        search(StaticParamClass.QUERY);
    }

    public void singInYandex () {
        singIn(StaticParamClass.LOGIN,StaticParamClass.PASSWORD);
    }

    public void singIn (String login, String psw) {
        fieldLogin.sendKeys(login);
        fieldPassword.sendKeys(psw);
        submit.click();
    }

    public boolean isNeedPageOpen () {
        return logo.isDisplayed();
    }

    public void logOut() {
        logOutBtn.click();
    }

    public boolean subminIsVisible() {
        return submit.isDisplayed();
    }

}
