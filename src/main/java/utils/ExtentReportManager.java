package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    // Initialize ExtentReports once
    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = "target/ExtentReports/" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "/index.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

    // Start test and associate it with the current thread
    public static void startTest(String testName) {
        ExtentTest test = getInstance().createTest(testName);
        testThread.set(test);
    }

    // Log messages using ThreadLocal
    public static void logInfo(String message) {
        testThread.get().info(message);
    }

    public static void logPass(String message) {
        testThread.get().pass(message);
    }

    public static void logFail(String message) {
        testThread.get().fail(message);
    }

    public static void logError(Throwable e) {
        testThread.get().fail(e);
    }

    public static ExtentTest getTest() {
        return testThread.get();
    }

    // Flush reports only once at the end
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
