package com.epam.yandex.test;

import com.epam.yandex.pageobjects.blocks.ContextBlock;
import com.epam.yandex.pageobjects.blocks.EmailListBlock;
import com.epam.yandex.pageobjects.pages.YandexMailPage;
import com.epam.yandex.pageobjects.pages.YandexMainPage;
import com.epam.yandex.util.constant.ProjectConstant;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class YandexAdditionTest extends BaseTest {

    private static final int FIRST_ITEM = 0;
    private YandexMainPage yandexMainPage;
    private YandexMailPage yandexMailPage;
    private EmailListBlock emailList;

    @BeforeClass(description = "Init page")
    public void setUp() {
        System.out.println("Init page");
        yandexMainPage = new YandexMainPage(driver);
        yandexMainPage.openPage();
        yandexMailPage = yandexMainPage.singIn(ProjectConstant.LOGIN, ProjectConstant.PASSWORD);
    }

    @Test(description = "moveEmailToDraftFolder", priority = 1)
    public void moveEmailToDraftFolderTest() {
        System.out.println("Move Email To Draft Folder Test");
        yandexMailPage.openSentFolder();
        emailList = yandexMailPage.emailListBlock();
        String firstItemSubject = emailList.getSubjectList().get(FIRST_ITEM);

        yandexMailPage.dragSentEmailToDraftFolder(FIRST_ITEM);
        Assert.assertFalse(emailList.getSubjectList().contains(firstItemSubject),
                "Item did not moved from Sent folder.");

        yandexMailPage.openDraftFolder();
        Assert.assertTrue(emailList.getSubjectList().contains(firstItemSubject),
                "Draft folder does not contain moved item.");
    }

    @Test(description = "deleteDraftEmail", priority = 2)
    public void deleteDraftEmailTest() {
        System.out.println("Delete Draft Email Test");
        String firstItemSubject = emailList.getSubjectList().get(FIRST_ITEM);
        yandexMailPage.contextClickOnEmailByIndex(FIRST_ITEM);
        ContextBlock contextBlock = yandexMailPage.contextBlock();
        contextBlock.clickDeleteItem();
        Assert.assertFalse(emailList.getSubjectList().contains(firstItemSubject),
                "Draft folder contains deleted item.");
    }

    @AfterClass
    public void logOut() {
        yandexMailPage.openUserSettings();
        yandexMainPage.logOut();
    }
}
