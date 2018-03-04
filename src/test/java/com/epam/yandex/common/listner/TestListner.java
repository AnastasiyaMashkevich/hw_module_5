package com.epam.yandex.common.listner;

import com.epam.yandex.common.driver.driverfactory.DriverFactory;
import com.epam.yandex.utils.ScreenshotUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListner implements ITestListener {

	private static final Logger log = LogManager.getLogger(TestListner.class);

	@Override
	public void onTestStart(ITestResult iTestResult) {
		log.info("Test  /"+ iTestResult.getMethod().getMethodName()+"/ is start. ");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		log.info(("Test  /"+ iTestResult.getMethod().getMethodName()+"/ is success. "));
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		String driverName = iTestResult.getTestClass().getXmlTest().getSuite().getParameter("driver");
		String screenShortName = iTestResult.getMethod().getMethodName();
		ScreenshotUtils.takeScreenshot(DriverFactory.getDriver(driverName), screenShortName);
		log.info(("Test  /"+ iTestResult.getMethod().getMethodName()+"/ is fail. "));
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

	}

	@Override
	public void onStart(ITestContext iTestContext) {
		log.info("Test started at " + iTestContext.getStartDate());
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		log.info("Test finished at " + iTestContext.getEndDate());
	}
}
