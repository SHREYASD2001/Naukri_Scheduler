package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

//@RunWith(Cucumber.class)
@CucumberOptions(
        features ="src/test/resources/features/", // Path to your feature files
        glue = {"steps"}, // to link the stepd define for this
        monochrome = true, //monochrome - wil remove unncecassry character form window
        tags="@test", //tags- to run the test with tag
        dryRun = true, //dryrun - check whether every feature steps has corresponding step define or not
        publish = true, //publish - it generates a public link to a detailed online report of your Cucumber test run.
        plugin = {
                "pretty", // for the report format
                "json:target/cucumber-reports/Cucumber.json", //report directory
                "html:target/cucumber-reports/report.html", //report directory,
                "rerun:target/rerun_scenario.txt", // to run the failed scenarios
        } //plugin - pretty means it means output will be in clearly format and it'll genrate html report under test-ouptut'

)

@Test
public class Runner extends AbstractTestNGCucumberTests {
}