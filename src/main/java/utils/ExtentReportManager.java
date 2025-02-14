package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extent = new ExtentReports();
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    // Initialize ExtentReports once
    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = "target/ExtentReports/" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "/index.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Test Execution Report");

            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

    // Start test and associate it with the current thread
    public static synchronized void startTest(String testName) {
        ExtentTest test = getInstance().createTest(testName);
        testThread.set(test);
    }

    // Get test instance
    public static ExtentTest getTest() {
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
