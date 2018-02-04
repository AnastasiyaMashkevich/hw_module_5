package com.epam.yandex.uitests.cucumbersteps;

import com.epam.yandex.common.driver.DriverFactory;
import com.epam.yandex.model.User;
import com.epam.yandex.pageobjects.pages.YandexMailPage;
import com.epam.yandex.pageobjects.pages.YandexMainPage;
import com.epam.yandex.pageobjects.pages.blocks.EmailFormBlock;
import com.epam.yandex.pageobjects.pages.blocks.EmailListBlock;
import com.epam.yandex.pageobjects.pages.blocks.HeaderBlock;
import com.epam.yandex.service.UserService;
import com.epam.yandex.uitests.constant.ProjectConstant;
import com.epam.yandex.uitests.pagecreator.MailPageCreator;
import com.epam.yandex.uitests.pagecreator.MainPageCreator;
import com.epam.yandex.uitests.pagecreator.PageCreator;

import com.epam.yandex.utils.RandomGenerateUtil;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class YandexStepdefs {
    private static final String BODY = RandomGenerateUtil.randomString();
    private static final String SUBJECT = RandomGenerateUtil.randomString();
    private WebDriver driver = DriverFactory.getDriver("chrome");
    private PageCreator pageCreator = new MainPageCreator();
    private YandexMainPage yandexMainPage = (YandexMainPage) pageCreator.createPage(driver);
    private User user = new UserService().getUserList().get(0);
    private YandexMailPage yandexMailPage = new MailPageCreator().createPage(driver);
    private HeaderBlock headerBlock = yandexMailPage.headerBlock();
    String firstItemSubject;
    private EmailFormBlock emailForm = yandexMailPage.emailFormBlock();
    private EmailListBlock emailList = yandexMailPage.emailListBlock();

    @Given("^user navigates to yandex main page$")
    public void userNavigatesToYandexMainPage() {
        yandexMainPage.openPage();
        Assert.assertTrue(yandexMainPage.isOpened(), "Yandex Main pageobjects is not opened.");
    }

    @When("^enters user credentials and submits login form$")
    public void entersUserCredentialsAndSubmitsLoginForm()  {
        yandexMailPage = yandexMainPage.logIn(user.getLogin(), user.getPsw());
    }

    @Then("^user loged in$")
    public void yandexMailPageIsDisplayed() {
        headerBlock = yandexMailPage.headerBlock();
        Assert.assertTrue(headerBlock.singInIsSuccess(user.getLogin()), "Sing In did not execute.");
    }

    @Given("^user opens new form for email$")
    public void userOpensNewFormForEmail() {
        new HeaderBlock(driver).openNewFormLetter();
        emailForm = yandexMailPage.emailFormBlock();
        emailForm.waitForNewEmailFormIsOpened();
    }

    @And("^enters body, subject, addressee of email$")
    public void entersBodySubjectAddresseeOfEmail() {
        emailForm.setAddresseeEmail(ProjectConstant.ADDRESSEE);
        emailForm.setEmailBody(BODY);
        emailForm.setEmailSubject(SUBJECT);
    }

    @When("^clicks close email$")
    public void clicksCloseEmail() {
        emailForm.clickCloseEmail();
        yandexMailPage.saveEmailAsDraft();
    }

    @And("^opens draft folder$")
    public void opensDraftFolder() {
        yandexMailPage.openDraftFolder();
    }

    @Then("^created deaft email displays within draft email list$")
    public void createdDeaftEmailDisplaysWithinDraftEmailList() {
        EmailListBlock emailList = yandexMailPage.emailListBlock();
        Assert.assertTrue(emailList.isEmailExist(SUBJECT),
                "Nearly created draft email didn't save within Draft folder.");
    }

    @Given("^user sees draft emails$")
    public void userOpensDraftEmailFolder() throws Throwable {
        emailList = yandexMailPage.emailListBlock();
    }

    @When("^clicks on nearly created email$")
    public void clicksOnNearlyCreatedEmail() {
        yandexMailPage.emailListBlock().clickOnEmail(0);
        emailForm = yandexMailPage.emailFormBlock();
        emailForm.waitForNewEmailFormIsOpened();
    }

    @Then("^filled fields are displayed$")
    public void filledFieldsAreDisplayed() throws Throwable {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(emailForm.getAddresseeEmail().contains(user.getLogin()),
                String.format("Addressee email is not correct. Email should be as %s", ProjectConstant.ADDRESSEE));
        softAssert.assertEquals(emailForm.getEmailSubject(), SUBJECT,
                String.format("Email SUBJECT is not correct. Subject should be as %s", SUBJECT));
        softAssert.assertEquals(emailForm.getBodyText(), BODY,
                String.format("Email BODY is not correct. Body should be as %s", BODY));
        softAssert.assertAll();
    }

    @Given("^draft email is opened$")
    public void draftEmailIsOpened() {
    }

    @When("^clicks on send button$")
    public void clicksOnSendButton() {
        emailForm.clickSendEmail();
    }

    @Then("^sent email is absent$")
    public void sentEmailIsAbsent() {
        emailList = yandexMailPage.emailListBlock();
        Assert.assertFalse(emailList.isEmailExist(SUBJECT),
                "Draft email exist within Draft folder after sending.");
    }

    @When("^opens sent folder$")
    public void opensSentFolder() {
        yandexMailPage.openSentFolder();
    }

    @Then("^sent email is present$")
    public void sentEmailIsPresent() {
        Assert.assertTrue(emailList.isEmailExist(SUBJECT),
                "Sent email didn't save within Sent folder.");
    }

    @When("^drag sent email to draft folder$")
    public void dragSentEmailToDraftFolder() {
        emailList = new YandexMailPage(driver).emailListBlock();
        String firstItemSubject = emailList.getSubjectList().get(0);
        yandexMailPage.dragSentEmailToDraftFolder(0);

    }

    @Then("^email displays within folder$")
    public void movedEmailDisplaysWithinDraftFolder() {
        emailList = new YandexMailPage(driver).emailListBlock();
        firstItemSubject = emailList.getSubjectList().get(0);
        Assert.assertTrue(emailList.getSubjectList().contains(firstItemSubject),
                "Draft folder does not contain moved item.");
    }

    @Then("^email does not display within folder$")
    public void movedEmailDoesNotDisplayWithinSentFolder() {
        Assert.assertFalse(emailList.getSubjectList().contains(firstItemSubject),
                "Item did not move from Sent folder.");
    }

    @When("^user does context click on any email$")
    public void userDoesContextClickOnAnyEmail() {
        yandexMailPage.contextClickOnEmailByIndex(0);
    }

    @And("^click Delete$")
    public void clickDelete() {
        yandexMailPage.clickDelete();;
    }
}
