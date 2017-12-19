package com.epam.yandex.test;

import com.epam.yandex.page.YandexMailPage;
import com.epam.yandex.page.YandexMainPage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class YandexTest extends BaseTest {

    private static final Logger LOG = Logger.getLogger(YandexTest.class);
    public YandexMainPage yandexMainPage;
    public YandexMailPage yandexMailPage;

    @BeforeTest(description = "Init browser")
    public void setUp() throws Exception {
        LOG.info("BeforeTest: start browser");
        yandexMainPage = new YandexMainPage(driver);
        yandexMailPage = new YandexMailPage(driver);
    }

    @Test
    public void assertOneCanOpenMainPage () {
        LOG.info("start test method assertOneCanOpenMainPage");
        yandexMainPage.openPage();
        Assert.assertTrue(yandexMainPage.isNeedPageOpen());
    }

    @Test
    public void assertOneCanSingIn () {
        LOG.info("start test method assertOneCanSingIn");
        yandexMainPage.openPage();
        yandexMainPage.singInYandex();
        Assert.assertTrue(yandexMailPage.singInIsSuccess());
    }
}
