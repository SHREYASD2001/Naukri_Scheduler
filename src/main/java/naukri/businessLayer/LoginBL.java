package naukri.businessLayer;

import naukri.screen.LoginScreen;
import naukri.utils.ContextDataStore;
import org.apache.http.util.Asserts;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class LoginBL {
    private Logger logger = Logger.getLogger(String.valueOf(LoginBL.class));

    public LoginBL() {
        logger.info("INSIDE THE LOGIN BL Class");
    }

    public LoginBL UserLoginToThePortal() {
        User user = (User) ContextDataStore.getData("gmailUser");
        String firstName = user.getUserId();
        String lastName = user.getPassword();
        LoginScreen.get()
                .userLoginsToPortal(firstName, lastName);
        return this;
    }

    public LoginBL userValidatesTheHomePage() {
        User user = (User) ContextDataStore.getData("gmailUser");
        String expectedName = user.getUsername();
        String actualName = LoginScreen.get()
                .userFetchesTheDefaultname();

        if(expectedName.equals(actualName)) {
            System.out.println("NAME is mismatching");
        } else {
            System.out.println("Name is not mismatching");
        }

        return this;
    }

    public LoginBL userSelectsViewSection() {
        LoginScreen.get()
                .userSelectsviewProfileSection();
        return this;
    }

    public LoginBL userUpdateTheStatusOnNaukri() {
        LoginScreen.get()
                .userEditsTheProfile()
                .userSavesTheDetails();

        String actual = LoginScreen.get()
                .userFetchesTheUpdatedDetails();
        if(actual.equals("Today")) {
            System.out.println("Details is updated successfully");
        } else {
            System.out.println("Details is not updated successfully");
            logger.info("actual Details is not as expected");
            throw new RuntimeException("Unable to update profile today on Naukri");
        }
        return this;
    }
}
