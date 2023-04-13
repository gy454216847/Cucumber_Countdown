import hooks.myhook;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:src/test/java/reports/cucumber_report", "json:target/cucumber.json", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, features = "src/test/java/features",
        monochrome = true, glue = {"steps"}, tags = "@LoginWrongPassword"
)
public class TestRunner  {


}
