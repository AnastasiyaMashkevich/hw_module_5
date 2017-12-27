package com.epam.yandex.test;

import com.epam.yandex.pageobjects.blocks.EmailFormBlock;
import com.epam.yandex.pageobjects.blocks.EmailListBlock;
import com.epam.yandex.util.ProjectConstant;
import com.epam.yandex.pageobjects.pages.YandexMailPage;
import com.epam.yandex.pageobjects.pages.YandexMainPage;
import org.apache.commons.lang.RandomStringUtils;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class YandexTest extends BaseTest {

    private static final String BODY = RandomStringUtils.random(5, true, false);
    private static final String SUBJECT = RandomStringUtils.random(5, true, false);
    private static final int FIRST_ELEMENT = 0;

    private YandexMainPage yandexMainPage;
    private YandexMailPage yandexMailPage;

    @BeforeTest(description = "Init pages")
    public void setUp() {
        System.out.println("Init pages");
        yandexMainPage = new YandexMainPage(driver);
        yandexMailPage = new YandexMailPage(driver);
    }

    @Test
    public void createAndSendNewEmailTest() {
        System.out.println("Create And Send New Email Test");

        yandexMainPage.openPage();
        Assert.assertTrue(yandexMainPage.isOpened(), "Yandex Main pageobjects is not opened.");
        yandexMainPage.singIn(ProjectConstant.LOGIN, ProjectConstant.PASSWORD);
        Assert.assertTrue(yandexMailPage.singInIsSuccess(), "Sing In did not execute.");

        yandexMailPage.openNewFormLetter();
        EmailFormBlock emailForm = yandexMailPage.emailFormBlock();
        emailForm.waitForNewEmailFormIsOpened();
        emailForm.setAddresseeEmail(ProjectConstant.ADDRESSEE);
        emailForm.setEmailBody(BODY);
        emailForm.setEmailSubject(SUBJECT);
        emailForm.clickCloseEmail();
        yandexMailPage.saveEmailAsDraft();
        yandexMailPage.openDraftFolder();

        SoftAssert softAssert = new SoftAssert();
        EmailListBlock emailList = yandexMailPage.emailListBlock();
        softAssert.assertTrue(emailList.isEmailExist(SUBJECT),
                "Nearly created draft email didn't save within Draft folder.");

        emailList.clickOnEmail(FIRST_ELEMENT);
        emailForm.waitForNewEmailFormIsOpened();
        softAssert.assertTrue(emailForm.getAddresseeEmail().contains(ProjectConstant.LOGIN),
                String.format("Addressee email is not correct. Email should be as %s", ProjectConstant.ADDRESSEE));
        softAssert.assertEquals(emailForm.getEmailSubject(), SUBJECT,
                String.format("Email SUBJECT is not correct. Subject should be as %s", SUBJECT));
        softAssert.assertEquals(emailForm.getBodyText(), BODY,
                String.format("Email BODY is not correct. Body should be as %s", BODY));

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
