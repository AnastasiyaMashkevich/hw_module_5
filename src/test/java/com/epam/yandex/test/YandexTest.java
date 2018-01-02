package com.epam.yandex.test;

import com.epam.yandex.pageobjects.blocks.EmailFormBlock;
import com.epam.yandex.pageobjects.blocks.EmailListBlock;
import com.epam.yandex.util.constant.ProjectConstant;
import com.epam.yandex.pageobjects.pages.YandexMailPage;
import com.epam.yandex.pageobjects.pages.YandexMainPage;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class YandexTest extends BaseTest {

    private static final String BODY = RandomStringUtils.random(5, true, false);
    private static final String SUBJECT = RandomStringUtils.random(5, true, false);
    private static final int FIRST_ELEMENT = 0;

    private YandexMainPage yandexMainPage;
    private YandexMailPage yandexMailPage;
    private EmailFormBlock emailForm;
    private EmailListBlock emailList;

    @BeforeClass(description = "Init page")
    public void setUp() {
        System.out.println("Init page");
        yandexMainPage = new YandexMainPage(driver);
    }

    @Test(description = "singIn")
    public void singIn() {
        System.out.println("Sing In Test");
        yandexMainPage.openPage();
        Assert.assertTrue(yandexMainPage.isOpened(), "Yandex Main pageobjects is not opened.");
        yandexMailPage = yandexMainPage.singIn(ProjectConstant.LOGIN, ProjectConstant.PASSWORD);
        Assert.assertTrue(yandexMailPage.singInIsSuccess(), "Sing In did not execute.");
    }

    @Test(description = "createEmail", priority = 1)
    public void createEmailTest() {
        System.out.println("Create New Email Test");
        yandexMailPage.openNewFormLetter();
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

        softAssert.assertTrue(emailForm.getAddresseeEmail().contains(ProjectConstant.LOGIN),
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

        yandexMailPage.openUserSettings();
        yandexMainPage.logOut();
        softAssert.assertTrue(yandexMainPage.subminIsVisible(), "Log Out did not execute.");
        softAssert.assertAll();
    }

}
