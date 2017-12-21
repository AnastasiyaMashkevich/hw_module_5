package com.epam.yandex.test;

import com.epam.yandex.util.ProjectConstant;
import com.epam.yandex.page.YandexMailPage;
import com.epam.yandex.page.YandexMainPage;
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
    public void setUp() throws Exception {
        System.out.println("Init pages");
        yandexMainPage = new YandexMainPage(driver);
        yandexMailPage = new YandexMailPage(driver);
    }

    @Test
    public void createAndSendNewEmailTest() {
        System.out.println("Create And Send New Email Test");

        yandexMainPage.openPage();
        yandexMainPage.singIn(ProjectConstant.LOGIN, ProjectConstant.PASSWORD);
        Assert.assertTrue(yandexMailPage.singInIsSuccess(), "Sing In did not execute.");

        yandexMailPage.openNewFormLetter();
        yandexMailPage.setAddresseeEmail(ProjectConstant.ADDRESSEE);
        yandexMailPage.setEmailBody(BODY);
        yandexMailPage.setEmailSubject(SUBJECT);
        yandexMailPage.clickCloseEmail();
        yandexMailPage.saveEmailAsDraft();
        yandexMailPage.openDraftFolder();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(yandexMailPage.isEmailExist(SUBJECT),
                "Nearly created draft email didn't save within Draft folder.");

        yandexMailPage.clickOnEmail(FIRST_ELEMENT);
        softAssert.assertEquals(yandexMailPage.getAddresseeEmail(), ProjectConstant.ADDRESSEE,
                String.format("Addressee email is not correct. Email should be as %s", ProjectConstant.ADDRESSEE));
        softAssert.assertEquals(yandexMailPage.getEmailSubject(), SUBJECT,
                String.format("Email SUBJECT is not correct. Subject should be as %s", SUBJECT));
        softAssert.assertEquals(yandexMailPage.getBodyText(), BODY,
                String.format("Email BODY is not correct. Body should be as %s", BODY));

        yandexMailPage.clickSendEmail();
        yandexMailPage.openDraftFolder();
        softAssert.assertFalse(yandexMailPage.isEmailExist(SUBJECT),
                "Draft email exist within Draft folder after sending.");

        yandexMailPage.openSentFolder();
        softAssert.assertTrue(yandexMailPage.isEmailExist(SUBJECT),
                "Sent email didn't save within Sent folder.");

        yandexMailPage.openUserSettings();
        yandexMainPage.logOut();
        softAssert.assertTrue(yandexMainPage.subminIsVisible(), "Log Out did not execute.");

        softAssert.assertAll();
    }
}
