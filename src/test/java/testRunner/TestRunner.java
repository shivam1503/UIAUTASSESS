package testRunner;

import org.testng.annotations.Test;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources/features", // Path to your feature files
        glue = "stepDefinitions", // Path to your step definition package
        tags = "@test1 or @test2 or @test3", // You can specify which scenarios to run
        plugin = {
                "pretty", // Generates readable output
                "html:target/cucumber-reports/cucumber.html", // Generates HTML report
                "json:target/cucumber-reports/cucumber.json" // Generates JSON report
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Test
    public void runCucumber() {
        // This method can be used for any additional setup or teardown if required.
    }
}
