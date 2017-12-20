package com.epam.yandex.test;

import com.epam.yandex.page.StaticParamClass;
import com.epam.yandex.page.YandexMailPage;
import com.epam.yandex.page.YandexMainPage;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class YandexTest extends BaseTest {

    private static final Logger LOG = Logger.getLogger(YandexTest.class);
    String body = RandomStringUtils.random(5, true, false);
    String subject = RandomStringUtils.random(5, true, false);

    public YandexMainPage yandexMainPage;
    public YandexMailPage yandexMailPage;

    @BeforeTest(description = "Init browser")
    public void setUp() throws Exception {
        LOG.info("BeforeTest: start browser");
        yandexMainPage = new YandexMainPage(driver);
        yandexMailPage = new YandexMailPage(driver);
    }

    @Test(priority = 0)
    public void singInTest () {
        LOG.info("start test method assertOneCanSingIn");
        yandexMainPage.openPage();
        yandexMainPage.singInYandex();
        Assert.assertTrue(yandexMailPage.singInIsSuccess());
    }

    @Test(dependsOnMethods = "singInTest", priority = 1)
    public void createNewDraftEmailTest() {
        yandexMailPage.openNewFormLetter();
        yandexMailPage.setAddresseeEmail(StaticParamClass.ADDRESSEE);
        yandexMailPage.setEmailBody(body);
        yandexMailPage.setEmailSubject(subject);
        yandexMailPage.clickCloseEmail();
        yandexMailPage.saveEmailAsDraft();
        yandexMailPage.openDraftFolder();
        Assert.assertTrue(yandexMailPage.isEmailExist(subject));
    }

    @Test(dependsOnMethods = "createNewDraftEmailTest", priority = 2)
    public void elementsFromDraftEmailTest() {
        SoftAssert softAssert = new SoftAssert();
        yandexMailPage.clickOnEmail(0);
        softAssert.assertEquals(yandexMailPage.getAddresseeEmail(),StaticParamClass.ADDRESSEE );
        softAssert.assertEquals(yandexMailPage.getEmailSubject(), subject);
        softAssert.assertEquals(yandexMailPage.getBodyText(), body);
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "elementsFromDraftEmailTest", priority = 3)
    public void sendEmail() {
        SoftAssert softAssert = new SoftAssert();
        yandexMailPage.clickSendEmail();
        yandexMailPage.openDraftFolder();
        softAssert.assertFalse(yandexMailPage.isEmailExist(subject));
        yandexMailPage.openSentFolder();
        softAssert.assertTrue(yandexMailPage.isEmailExist(subject));
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "singInTest", priority = 4)
    public void logOut() {
        yandexMailPage.openUserSettings();
        yandexMainPage.logOut();
        Assert.assertTrue(yandexMainPage.subminIsVisible());
    }

}
