package com.epam.yandex.uitests.tests;

import com.epam.yandex.model.User;
import com.epam.yandex.pageobjects.pages.YandexMailPage;
import com.epam.yandex.pageobjects.pages.YandexMainPage;
import com.epam.yandex.pageobjects.pages.blocks.EmailListBlock;
import com.epam.yandex.service.UserService;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class YandexAdditionTest extends BaseTest {

    private static final int FIRST_ITEM = 0;
    private YandexMailPage yandexMailPage;
    private EmailListBlock emailList;

    @BeforeClass(description = "Log In")
    public void setUp() {
        System.out.println("Log In");

        User user = new UserService().getUserList().get(FIRST_ITEM);
        YandexMainPage yandexMainPage = new YandexMainPage(driver);

        yandexMainPage.openPage();
        yandexMailPage = yandexMainPage.logIn(user.getLogin(), user.getPsw());
    }

    @Test(description = "moveEmailToDraftFolder", priority = 1)
    public void moveEmailToDraftFolderTest() {
        System.out.println("Move Email To Draft Folder Test");

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
        System.out.println("Delete Draft Email Test");

        String firstItemSubject = emailList.getSubjectList().get(FIRST_ITEM);
        yandexMailPage.contextClickOnEmailByIndex(FIRST_ITEM);

        yandexMailPage.clickDelete();
        Assert.assertFalse(emailList.getSubjectList().contains(firstItemSubject),
                "Draft folder contains deleted item.");
    }

    @AfterClass(description = "Log Out")
    public void logOut() {
        System.out.println("Log Out");

        yandexMailPage.headerBlock().openUserSettings();
        yandexMailPage.logOut();
    }
}
