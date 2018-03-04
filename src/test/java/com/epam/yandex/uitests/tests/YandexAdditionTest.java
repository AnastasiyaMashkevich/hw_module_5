package com.epam.yandex.uitests.tests;

import com.epam.yandex.pageobjects.pages.YandexMailPage;
import com.epam.yandex.pageobjects.pages.YandexMainPage;
import com.epam.yandex.pageobjects.pages.blocks.EmailListBlock;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class YandexAdditionTest extends BaseTest {
    private static Logger log = Logger.getLogger(YandexAdditionTest.class);

    private YandexMailPage yandexMailPage;
    private EmailListBlock emailList;

    @BeforeClass(description = "Log In")
    public void setUp() {
        log.info("Log In");

        YandexMainPage yandexMainPage = new YandexMainPage(driver);
        yandexMainPage.openPage();
        yandexMailPage = yandexMainPage.logIn(user.getLogin(), user.getPsw());
    }

    @Test(description = "moveEmailToDraftFolder", priority = 1)
    public void moveEmailToDraftFolderTest() {
       log.info("Move Email To Draft Folder Test");

        yandexMailPage.openSentFolder();
        emailList = yandexMailPage.emailListBlock();
        String firstItemSubject = emailList.getSubjectList().get(FIRST_ITEM);

        yandexMailPage.dragSentEmailToDraftFolder(FIRST_ITEM);
        Assert.assertFalse(emailList.getSubjectList().contains(firstItemSubject),
                "Item did not move from Sent folder.");

        yandexMailPage.openDraftFolder();
        Assert.assertTrue(emailList.getSubjectList().contains(firstItemSubject),
                "Draft folder does not contain moved item.");
    }

    @Test(description = "deleteDraftEmail", priority = 2)
    public void deleteDraftEmailTest() {
       log.info("Delete Draft Email Test");

        String firstItemSubject = emailList.getSubjectList().get(FIRST_ITEM);
        yandexMailPage.contextClickOnEmailByIndex(FIRST_ITEM);

        yandexMailPage.clickDelete();
        Assert.assertFalse(emailList.getSubjectList().contains(firstItemSubject),
                "Draft folder contains deleted item.");
    }

    @AfterClass(description = "Log Out")
    public void logOut() {
       log.info("Log Out");

        yandexMailPage.headerBlock().openUserSettings();
        yandexMailPage.logOut();
    }
}
