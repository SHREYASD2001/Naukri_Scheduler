package steps;

import naukri.businessLayer.LoginBL;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import naukri.utils.CreateDriver;
import org.openqa.selenium.WebDriver;

public class LoginSteps {

    @Given("User signs in to the portal")
    public void user_signs_in_to_the_portal() {
        new LoginBL().UserLoginToThePortal();
        // Write code here that turns the phrase above into concrete actions
    }

    @And("User Validates the home page")
    public void user_validates_the_home_page() {
        new LoginBL().userValidatesTheHomePage();
        // Write code here that turns the phrase above into concrete actions

    }

    @And("User selects the view section")
    public void userSelectsTheViewSection() {
        new LoginBL().userSelectsViewSection();
    }

    @And("User Updates the status for today")
    public void userUpdatesTheStatusForToday() {
        new LoginBL().userUpdateTheStatusOnNaukri();
    }
}
