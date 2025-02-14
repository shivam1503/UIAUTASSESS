package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extent;

    // ThreadLocal to maintain separate ExtentTest instances for each thread
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    // Initialize ExtentReports once
    public static ExtentReports getInstance() {
        if (extent == null) {
            // Setting up the report file path and date format
            String reportPath = "target/ExtentReports/" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "/index.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            // Configuring the report
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Test Execution Report");

            // Initializing ExtentReports and attaching the reporter
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent; // Return the single instance of ExtentReports
    }

    // Start test and associate it with the current thread
    public static void startTest(String testName) {
        // Check if the test has already been started
        if (testThread.get() == null) {
            ExtentTest test = getInstance().createTest(testName); // Create a new test
            testThread.set(test); // Associate with the current thread
        }
    }

    // Get test instance
    public static ExtentTest getTest() {
        if (testThread.get() == null) {
            throw new IllegalStateException("ExtentTest instance is null. Make sure startTest() is called.");
        }
        return testThread.get();
    }

    // Logging methods
    public static void logInfo(String message) {
        if (getTest() != null) {
            getTest().info(message);
        }
    }

    public static void logPass(String message) {
        if (getTest() != null) {
            getTest().pass(message);
        }
    }

    public static void logFail(String message) {
        if (getTest() != null) {
            getTest().fail(message);
        } else {
            System.out.println("ExtentTest instance is null for logFail: " + message);
        }
    }

    public static void logError(Throwable e) {
        if (getTest() != null) {
            getTest().fail(e);
        }
    }

    // Flush reports only once at the end
    public static synchronized void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
