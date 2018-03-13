package vehicleenquiry;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = { "src/test/resources" }, plugin = { "html:target/site/cucumber-pretty",
		"json:target/cucumber.json" }
)

public class CucumberTestRunner {
}
