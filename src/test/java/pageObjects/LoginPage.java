package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginPage {
    public WebDriver driver;
    WebDriverWait wait;

    @FindBy(how = How.NAME, using = "email']")
    WebElement usernameInput;
    @FindBy(how = How.XPATH, using = "password")
    WebElement passwordInput;
    @FindBy(how = How.XPATH, using = "//span[normalize-space()='Logheza-te']")
    WebElement submitButton;


    @FindBy(how = How.XPATH, using = "//span[@class='errorMessage-errorMessage-1nk']")
    WebElement errGeneral;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(this.driver, this);
    }

    public void login(String username, String password) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        submitButton.submit();
    }

    public void waitForLoginPage() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
    }

    public boolean checkErr(String error, String type) {
        if (type.equalsIgnoreCase("generalErr"))
            return error.equals(errGeneral.getText());
        return false;
    }

    public void openLoginPage(String hostname) {
        System.out.println("Open the next url:" + hostname + "login");
        driver.get(hostname + "login");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']")).click();
    }

}
