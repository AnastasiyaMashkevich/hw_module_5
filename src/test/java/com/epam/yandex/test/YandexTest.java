package com.epam.yandex.test;

import com.epam.yandex.bean.User;
import com.epam.yandex.pageobjects.pages.YandexMailPage;
import com.epam.yandex.pageobjects.pages.YandexMainPage;
import com.epam.yandex.pageobjects.pages.blocks.EmailFormBlock;
import com.epam.yandex.pageobjects.pages.blocks.EmailListBlock;
import com.epam.yandex.pageobjects.pages.blocks.HeaderBlock;
import com.epam.yandex.util.RandomGenerateUtil;
import com.epam.yandex.util.UserService;
import com.epam.yandex.util.constant.ProjectConstant;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class YandexTest extends BaseTest {
    private static final String BODY = RandomGenerateUtil.generateString();
    private static final String SUBJECT = RandomGenerateUtil.generateString();
    private static final int FIRST_ELEMENT = 0;

    private YandexMainPage yandexMainPage;
    private YandexMailPage yandexMailPage;
    private EmailFormBlock emailForm;
    private EmailListBlock emailList;
    private HeaderBlock headerBlock;
    private User user;

    @BeforeClass(description = "Init page")
    public void setUp() {
        System.out.println("Init page");
        yandexMainPage = new YandexMainPage(driver);
    }

    @Test(description = "singIn")
    public void singIn() {
        System.out.println("Sing In Test");
        user = new UserService().getUserList().get(0);
        yandexMainPage.openPage();
        Assert.assertTrue(yandexMainPage.isOpened(), "Yandex Main pageobjects is not opened.");
        yandexMailPage = yandexMainPage.logIn(user.getLogin(), user.getPsw());
        headerBlock = yandexMailPage.headerBlock();
        Assert.assertTrue(headerBlock.singInIsSuccess(), "Sing In did not execute.");
    }

    @Test(description = "createEmail", priority = 1)
    public void createEmailTest() {
        System.out.println("Create New Email Test");
        headerBlock.openNewFormLetter();
        emailForm = yandexMailPage.emailFormBlock();
        emailForm.waitForNewEmailFormIsOpened();
        emailForm.setAddresseeEmail(ProjectConstant.ADDRESSEE);
        emailForm.setEmailBody(BODY);
        emailForm.setEmailSubject(SUBJECT);
        emailForm.clickCloseEmail();
        yandexMailPage.saveEmailAsDraft();
        yandexMailPage.openDraftFolder();

        EmailListBlock emailList = yandexMailPage.emailListBlock();
        Assert.assertTrue(emailList.isEmailExist(SUBJECT),
                "Nearly created draft email didn't save within Draft folder.");
    }

    @Test(description = "emailVerification", priority = 2)
    public void emailVerificationTest() {
        System.out.println("Email Verification Test");
        SoftAssert softAssert = new SoftAssert();

        emailList = yandexMailPage.emailListBlock();
        emailList.clickOnEmail(FIRST_ELEMENT);
        emailForm.waitForNewEmailFormIsOpened();

        softAssert.assertTrue(emailForm.getAddresseeEmail().contains(user.getLogin()),
                String.format("Addressee email is not correct. Email should be as %s", ProjectConstant.ADDRESSEE));
        softAssert.assertEquals(emailForm.getEmailSubject(), SUBJECT,
                String.format("Email SUBJECT is not correct. Subject should be as %s", SUBJECT));
        softAssert.assertEquals(emailForm.getBodyText(), BODY,
                String.format("Email BODY is not correct. Body should be as %s", BODY));
        softAssert.assertAll();
    }

    @Test(description = "sendingEmail", priority = 3)
    public void sendingEmailTest() {
        System.out.println("Sending Email Test");
        SoftAssert softAssert = new SoftAssert();

        emailForm.clickSendEmail();
        yandexMailPage.openDraftFolder();
        softAssert.assertFalse(emailList.isEmailExist(SUBJECT),
                "Draft email exist within Draft folder after sending.");

        yandexMailPage.openSentFolder();
        softAssert.assertTrue(emailList.isEmailExist(SUBJECT),
                "Sent email didn't save within Sent folder.");

        headerBlock.openUserSettings();
        yandexMailPage.logOut();
        softAssert.assertTrue(yandexMainPage.isSubmitVisible(), "Log Out did not execute.");
        softAssert.assertAll();
    }

}
