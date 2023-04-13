package steps;

import com.aventstack.extentreports.ExtentReports;
import core.CommonWeb;
import hooks.myhook;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.MainPage;
import pages.SignInPage;

public class LoginStepdefs {
    @Given("I am on the Countdown page")
    public void iAmOnTheCountdownPage() {
        CommonWeb.openBrowser("chrome", "https://www.countdown.co.nz/");
    }


    @When("I click Sign in link")
    public void iClickSignInLink() {
        CommonWeb.click((MainPage.SignInOrRegister));
        CommonWeb.click((MainPage.SignIn));
    }


    @And("I input right {string} and {string}")
    public void iInputRightAnd(String email, String password) {
        CommonWeb.sendKeys(SignInPage.Email, email);
        CommonWeb.sendKeys(SignInPage.Password, password);
    }


    @And("I click login button")
    public void iClickLoginButton() {
        CommonWeb.click(SignInPage.SignIn);
    }

    @Then("I can login my account")
    public void iCanLoginMyAccount() {
        Assert.assertTrue(CommonWeb.isDisplayed(MainPage.DownArrow));
    }

    @And("I input wrong {string} and wrong {string}")
    public void iInputWrongAndWrong(String email, String password) {
        CommonWeb.sendKeys(SignInPage.Email, email);
        CommonWeb.sendKeys(SignInPage.Password, password);
    }

    @Then("I can see the {string}")
    public void iCanSeeThe(String label) {
        Assert.assertEquals(CommonWeb.getText(SignInPage.Label), label);
    }

    @And("I  do not input username and passwords")
    public void iDoNotInputUsernameAndPasswords() {
        CommonWeb.click(SignInPage.SignIn);
    }

    @Then("I can see “This field is required\"")
    public void iCanSeeThisFieldIsRequired() throws InterruptedException {
        Thread.sleep(2000);
        String errorText = CommonWeb.getText(SignInPage.ErrorText);
        Assert.assertEquals(errorText, "This field is required");
    }

    @When("I click Sign out link")
    public void iClickSignOutLink() {
        CommonWeb.click(MainPage.DownArrow);
        CommonWeb.click(MainPage.SignOut);
    }


    @Then("I can logout my account")
    public void iCanLogoutMyAccount() {
        Assert.assertTrue(CommonWeb.isDisplayed(MainPage.SignInOrRegister));
    }

    @Before
    public void beforeScenario(Scenario scenario) {

    }


    @AfterStep
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = CommonWeb.takeScreenshotAsBytes();
                scenario.attach(screenshot, "image/png", "image");
//                CommonWeb.takeScreenshotAsFile(scenario.getName() + "_error");
            } catch (Exception e) {
                e.printStackTrace();
            }
            CommonWeb.quit();
        }
    }

}

