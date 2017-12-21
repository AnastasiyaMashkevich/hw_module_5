package com.epam.yandex.runner;

import org.testng.TestNG;
import org.testng.collections.Lists;
import java.util.List;

public class Runner {

    public static void main (String[] args) throws Exception {
        TestNG testng = new TestNG();
        List<String> suites = Lists.newArrayList();
        suites.add("./src/test/resources/testng.xml");
        testng.setTestSuites(suites);
        testng.run();

    }
}
