package testRunner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/java/features",  // Path to your feature files
        glue = "stepDefinitions",                  // Path to your step definitions
        plugin = {"pretty", "html:target/cucumber-reports.html"},  // Reporting
        monochrome = true,
        tags = "@test1"
)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

    // This method allows TestNG to execute Cucumber scenarios with DataProvider
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    // You can define any additional TestNG annotations here if needed (e.g., @BeforeClass)
}
