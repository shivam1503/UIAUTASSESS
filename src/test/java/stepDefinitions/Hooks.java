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
        System.out.println("Test started: " + scenario.getName());
        test = new BaseTest();
        test.setup();
        pgObj = new PageObjects(driver);
        pgObj2 = new PageObjectsTest2(driver);
        pgObj3 = new PageObjectsTest3(driver);

        // Ensure ExtentReport is initialized
        ExtentReportManager.getInstance();
        ExtentReportManager.startTest(scenario.getName());
    }

    @After
    public void endTest(Scenario scenario) {
        if (ExtentReportManager.getTest() == null) {
            System.out.println("Error: ExtentTest instance is null for " + scenario.getName());
            return;
        }

        if (scenario.isFailed()) {
            ExtentReportManager.logFail("Test Failed: " + scenario.getName());
        } else {
            ExtentReportManager.logPass("Test Passed: " + scenario.getName());
        }
        test.teardown();
    }

    @io.cucumber.java.AfterAll
    public static void flushReport() {
        ExtentReportManager.flushReports();
    }
}
