package hooks;


import com.aventstack.extentreports.ExtentReports;
import core.CommonWeb;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class myhook {
    @Before
    public  void beforeScenario() {
        System.out.println("Before Scenario");
    }


    @After
    public  void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = CommonWeb.takeScreenshotAsBytes();
                scenario.attach(screenshot, "image/png", scenario.getName());
                CommonWeb.takeScreenshotAsFile(scenario.getName() + "_error");

            } catch (Exception e) {
                e.printStackTrace();
            }
            CommonWeb.quit();
        }
    }

}





