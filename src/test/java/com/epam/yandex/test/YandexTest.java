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

    @Test()
    public void singInTest () {
        System.out.println("Sing In Test");
        yandexMainPage.openPage();
        yandexMainPage.singInYandex();
        Assert.assertTrue(yandexMailPage.singInIsSuccess(), "Sing In did not execute.");
    }

    @Test(dependsOnMethods = "singInTest", priority = 1)
    public void createNewDraftEmailTest() {
        System.out.println("Create New Draft Email Test");
        yandexMailPage.openNewFormLetter();
        yandexMailPage.setAddresseeEmail(ProjectConstant.ADDRESSEE);
        yandexMailPage.setEmailBody(BODY);
        yandexMailPage.setEmailSubject(SUBJECT);
        yandexMailPage.clickCloseEmail();
        yandexMailPage.saveEmailAsDraft();
        yandexMailPage.openDraftFolder();
        Assert.assertTrue(yandexMailPage.isEmailExist(SUBJECT),
                "Nearly created draft email didn't save within Draft folder.");
    }

    @Test(dependsOnMethods = "createNewDraftEmailTest", priority = 2)
    public void elementsFromDraftEmailTest() {
        System.out.println("Elements From Draft Email Test");
        SoftAssert softAssert = new SoftAssert();
        yandexMailPage.clickOnEmail(FIRST_ELEMENT);
        softAssert.assertEquals(yandexMailPage.getAddresseeEmail(), ProjectConstant.ADDRESSEE,
                String.format("Addressee email is not correct. Email should be as %s", ProjectConstant.ADDRESSEE));
        softAssert.assertEquals(yandexMailPage.getEmailSubject(), SUBJECT,
                String.format("Email SUBJECT is not correct. Subject should be as %s", SUBJECT));
        softAssert.assertEquals(yandexMailPage.getBodyText(), BODY,
                String.format("Email BODY is not correct. Body should be as %s", BODY));
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "elementsFromDraftEmailTest", priority = 3)
    public void sendEmail() {
        System.out.println("Elements From Draft Email Test");
        SoftAssert softAssert = new SoftAssert();
        yandexMailPage.clickSendEmail();
        yandexMailPage.openDraftFolder();
        softAssert.assertFalse(yandexMailPage.isEmailExist(SUBJECT),
                "Draft email exist within Draft folder after sending.");
        yandexMailPage.openSentFolder();
        softAssert.assertTrue(yandexMailPage.isEmailExist(SUBJECT),
                "Sent email didn't save within Sent folder.");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "singInTest", priority = 4)
    public void logOut() {
        System.out.println("Log Out");
        yandexMailPage.openUserSettings();
        yandexMainPage.logOut();
        Assert.assertTrue(yandexMainPage.subminIsVisible(), "Log Out did not execute.");
    }
}
