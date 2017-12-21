package com.epam.yandex.page;

import com.epam.yandex.util.ProjectConstant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YandexMainPage extends BasePage {

    @FindBy(xpath = "//div[@class = 'home-logo__default']")
    private WebElement logo;

    @FindBy (xpath = "//input[@name = 'login']")
    private WebElement fieldLogin;

    @FindBy (xpath = "//input[@name = 'passwd']")
    private WebElement fieldPassword;

    @FindBy (xpath = "//button[contains(@class, 'auth__button')]")
    private WebElement submit;

    @FindBy(xpath = "*//input[@id ='text']")
    private WebElement requestInput;

    @FindBy(xpath = "//div[@class = 'search2__button']/button[@type='submit']")
    private WebElement searchButton;


    @FindBy (xpath = "//div/a[contains(@data-metric, 'Выход')]") //understand that is not a good idea use Russian letters here, but I did not have another way
    private WebElement logOutBtn;


    public YandexMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage () {
        driver.navigate().to(ProjectConstant.BASE_URL);
    }

    public void singIn (String login, String psw) {
        fieldLogin.sendKeys(login);
        fieldPassword.sendKeys(psw);
        submit.click();
    }

    public void logOut() {
        logOutBtn.click();
    }

    public boolean subminIsVisible() {
        return submit.isDisplayed();
    }

}
