package naukri.screen;

import naukri.screen.web.LoginScreenWeb;
import naukri.utils.CreateDriver;
import org.openqa.selenium.WebDriver;

public abstract class LoginScreen {


    public static LoginScreen get() {
        WebDriver driver = (new CreateDriver()).getDriver();
        return new LoginScreenWeb(driver);
    }

    public abstract LoginScreen userLoginsToPortal(String userId, String password);

    public abstract String userFetchesTheDefaultname();
    public abstract LoginScreen userSelectsviewProfileSection();
    public abstract LoginScreen userEditsTheProfile();
    public abstract LoginScreen userSavesTheDetails();

    public abstract String userFetchesTheUpdatedDetails();
}
