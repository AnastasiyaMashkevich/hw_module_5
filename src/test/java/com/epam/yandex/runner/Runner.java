package com.epam.yandex.runner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.TestNG;
import org.testng.collections.Lists;

import java.util.List;

public class Runner {

    private static final Logger LOG = LogManager.getLogger(Runner.class);

    public static void main (String[] args) throws Exception {



        PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/test/resources/log_file.properties");
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
        LOG.info("Start Runner Class");
        TestNG testng = new TestNG();
        List<String> suites = Lists.newArrayList();
        suites.add("./src/test/resources/testng.xml");
        testng.setTestSuites(suites);
        testng.run();

      Runtime.getRuntime().addShutdownHook(new Thread("Shutdown thread") {
          public void run() {
              LOG.info("Shutting down app");
          }
      });
    }
}
