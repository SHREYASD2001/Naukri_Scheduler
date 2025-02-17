package naukri.businessLayer;


import org.openqa.selenium.WebDriver;

public class LoginPageBL {
    private WebDriver driver;
    private String pageTitle;

    public LoginPageBL(WebDriver driver) {
        this.driver = driver;
    }


    public LoginPageBL userFetchesPageTItle() {
//        pageTitle = new LoginScreen(driver).getPageTitle();
        return this;
    }

    public void userValidatesPageTItle(String string) {
        System.out.println(this.pageTitle);
        System.out.println(this.pageTitle.equals(string));
    }
}
