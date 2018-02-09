package com.epam.yandex.uitests.cucumbersteps;


import com.epam.yandex.common.driver.driverfactory.ChromeDriver;
import com.epam.yandex.common.driver.driverfactory.DriverFactory;
import com.epam.yandex.model.Email;
import com.epam.yandex.model.User;
import com.epam.yandex.pageobjects.pages.YandexMailPage;
import com.epam.yandex.pageobjects.pages.YandexMainPage;
import com.epam.yandex.pageobjects.pages.blocks.EmailFormBlock;
import com.epam.yandex.pageobjects.pages.blocks.EmailListBlock;
import com.epam.yandex.pageobjects.pages.blocks.HeaderBlock;
import com.epam.yandex.service.EmailService;
import com.epam.yandex.service.UserService;
import com.epam.yandex.uitests.pagecreator.MailPageCreator;
import com.epam.yandex.uitests.pagecreator.MainPageCreator;
import com.epam.yandex.uitests.pagecreator.PageCreator;
import com.epam.yandex.utils.RandomGenerateUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;


public class YandexStepDefinition {

    private static final String VALUE = RandomGenerateUtil.randomString();
    private WebDriver driver = DriverFactory.getDriver("chrome");
    private PageCreator pageCreator = new MainPageCreator();
    private YandexMainPage yandexMainPage = (YandexMainPage) pageCreator.createPage(driver);
    private User user = new UserService().getUserList().get(0);
    private YandexMailPage yandexMailPage = new MailPageCreator().createPage(driver);
    private String firstItemSubject;
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
        HeaderBlock headerBlock = yandexMailPage.headerBlock();
        Assert.assertTrue(headerBlock.singInIsSuccess(user.getLogin()), "Sing In did not execute.");
    }

    @And("^opens draft folder$")
    public void opensDraftFolder() {
        yandexMailPage.openDraftFolder();
    }

    @Then("^sent email with subject \"([^\"]*)\" is absent$")
    public void sentEmailIsAbsent(String subject) {
        emailList = yandexMailPage.emailListBlock();
        Assert.assertFalse(emailList.isEmailExist(subject + VALUE),
                "Draft email exist within Draft folder after sending.");
    }

    @When("^opens sent folder$")
    public void opensSentFolder() {
        yandexMailPage.openSentFolder();
    }

    @Then("^sent email with subject \"([^\"]*)\" is present$")
    public void sentEmailIsPresent(String subject) {
        Assert.assertTrue(emailList.isEmailExist(subject + VALUE),
                "Sent email didn't save within Sent folder.");
    }

    @Given("^user opens new form for email$")
    public void userOpensNewFormForEmail() {
        new HeaderBlock(driver).openNewFormLetter();
        emailForm = yandexMailPage.emailFormBlock();
        emailForm.waitForNewEmailFormIsOpened();
    }

    @And("^enters Body as \"([^\"]*)\", Subject as \"([^\"]*)\", Addressee as \"([^\"]*)\" of email$")
    public void entersBodySubjectAddresseeOfEmail(String body, String subject, String addressee) {
        emailForm.setAddresseeEmail(addressee);
        emailForm.setEmailBody(body + VALUE);
        emailForm.setEmailSubject(subject + VALUE);
    }

    @When("^clicks close email$")
    public void clicksCloseEmail() {
        emailForm.clickCloseEmail();
        yandexMailPage.saveEmailAsDraft();
    }

    @Then("^created deaft email \"([^\"]*)\" displays within draft email list$")
    public void createdDeaftEmailDisplaysWithinDraftEmailList(String subject) {
        EmailListBlock emailList = yandexMailPage.emailListBlock();
        Assert.assertTrue(emailList.isEmailExist(subject + VALUE),
                "Nearly created draft email didn't save within Draft folder.");
    }

    @When("^clicks on nearly created email$")
    public void clicksOnNearlyCreatedEmail() {
        yandexMailPage.emailListBlock().clickOnEmail(0);
        emailForm = yandexMailPage.emailFormBlock();
        emailForm.waitForNewEmailFormIsOpened();
    }

    @Then("^filled fields are displayed as:")
    public void filledFieldsAreDisplayed(String json) {
        SoftAssert softAssert = new SoftAssert();
        Email email = EmailService.getEmail(json);
        softAssert.assertTrue(emailForm.getAddresseeEmail().contains(email.getAddressee()),
                String.format("Addressee email is not correct. Email should be as %s", email.getAddressee()));
        softAssert.assertEquals(emailForm.getEmailSubject(), email.getSubject() +VALUE,
                String.format("Email SUBJECT is not correct. Subject should be as %s", email.getSubject() + VALUE));
        softAssert.assertEquals(emailForm.getBodyText(), email.getBody() + VALUE,
                String.format("Email BODY is not correct. Body should be as %s", email.getBody() + VALUE));
        softAssert.assertAll();
    }

    @When("^clicks on send button$")
    public void clicksOnSendButton() {
        emailForm.clickSendEmail();
    }

    @When("^drag (\\d+) sent email to draft folder$")
    public void dragSentEmailToDraftFolder(int emailNumber) {
        emailList = new YandexMailPage(driver).emailListBlock();
        firstItemSubject = emailList.getSubjectList().get(emailNumber - 1);
        yandexMailPage.dragSentEmailToDraftFolder(emailNumber - 1);

    }

    @Then("^email displays within folder$")
    public void movedEmailDisplaysWithinDraftFolder() {
        emailList = new YandexMailPage(driver).emailListBlock();
        Assert.assertTrue(emailList.getSubjectList().contains(firstItemSubject),
                "Draft folder does not contain moved item.");
    }

    @Then("^email does not display within folder$")
    public void movedEmailDoesNotDisplayWithinSentFolder() {
        Assert.assertFalse(emailList.getSubjectList().contains(firstItemSubject),
                "Item did not move from Sent folder.");
    }

    @When("^user does context click on (\\d+) email$")
    public void userDoesContextClickOnAnyEmail(int emailNumber) {
        yandexMailPage.contextClickOnEmailByIndex(emailNumber - 1);
    }

    @And("^click Delete$")
    public void clickDelete() {
        yandexMailPage.clickDelete();;
    }

}
