package naukri.screen.web;

import naukri.screen.LoginScreen;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.time.Duration;

public class LoginScreenWeb extends LoginScreen {
    private WebDriver driver;
    public LoginScreenWeb(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public LoginScreen userLoginsToPortal(String userId, String password) {
        performsExplicitClick(By.cssSelector("a#login_Layer"));
        waitTillPageIsLoaded(By.cssSelector("div.drawer-wrapper >div > div.head"));
        driver.findElement(By.cssSelector("div.form-row > input[type='text']")).sendKeys(userId);
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(password);
        performsExplicitClick(By.cssSelector("button[type='submit']"));
        return this;
    }

    @Override
    public String userFetchesTheDefaultname() {
        waitTillPageIsLoaded(By.cssSelector("div.middle-section-visible"));
        String text = waitTillElementIsPresent(By.cssSelector("div.info__heading")).getText();
        return text;
    }

    @Override
    public LoginScreen userSelectsviewProfileSection() {
        performsExplicitClick(By.cssSelector("div.view-profile-wrapper > a"));
        waitTillPageIsLoaded(By.cssSelector("div.col.s12"));
        return this;
    }

    @Override
    public LoginScreen userEditsTheProfile() {
        performsExplicitClick(By.cssSelector("em.icon.edit"));
        waitTillPageIsLoaded(By.cssSelector("form#editBasicDetailsForm"));
        return this;
    }

    @Override
    public LoginScreen userSavesTheDetails() {
        WebElement element = waitTillElementIsPresent(By.cssSelector("button#saveBasicDetailsBtn"));
        Point point = element.getLocation();
        int x = point.getX();
        int y = point.getY();
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollBy("+x+","+y+")");
        File ts = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File target = new File("src/test/resources/features");
        try {
            FileUtils.moveFile(ts, target);
        } catch (Exception e) {
            System.out.println("Facing issue while moving file into the different location");
        }
        element.click();
        return null;
    }

    @Override
    public String userFetchesTheUpdatedDetails() {
        WebElement element = waitTillElementIsPresent(By.cssSelector("span.mode-date-wrap"));
        String text = element.getText();
        if(text.contains("Profile last updated")) {
            return element.findElement(By.cssSelector("span.mod-date-val")).getText();
        }
        return "";
    }


    private WebElement waitTillElementIsPresent(By locator) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }

    private void performsExplicitClick(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private void waitTillPageIsLoaded(By locator) {
        waitTillElementIsPresent(locator);
    }
}