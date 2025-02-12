package stepDefinitions;

import base.BaseTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pages.PageObjects;
import pages.PageObjectsTest2;
import pages.PageObjectsTest3;
import utils.ExtentReportManager;

import static base.BaseTest.driver;

public class Hooks {
    BaseTest test;
    public static PageObjects pgObj;
    public static PageObjectsTest2 pgObj2;
    public static PageObjectsTest3 pgObj3;

    @Before
    public void startTest(Scenario scenario) {
        System.out.println("test started");
        test = new BaseTest();
        test.setup();
        pgObj = new PageObjects(driver);
        pgObj2 = new PageObjectsTest2(driver);
        pgObj3 = new PageObjectsTest3(driver);
        System.out.println("Starting test: " + scenario.getName());
        ExtentReportManager.createInstance();
        ExtentReportManager.startTest(scenario.getName());
    }

    @After
    public void endTest(Scenario scenario) {
        if (scenario.isFailed()) {
            ExtentReportManager.logFail("Test Failed: " + scenario.getName());
        } else {
            ExtentReportManager.logPass("Test Passed: " + scenario.getName());
        }
        ExtentReportManager.endTest();
        test.teardown();
    }
}
