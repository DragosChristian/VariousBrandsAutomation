package pageObjects;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginStepDefinitions {
    private WebDriver driver;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.various-brands.ro/login");
        driver.findElement(By.xpath("//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']")).click();
    }

    @When("I enter valid credentials")
    public void i_enter_valid_credentials() {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("input.textInput-input-3vj")).sendKeys("exemplumail1@gmail.com");
        driver.findElement(By.name("password")).sendKeys("paRolatestare123$");
    }

    @When("I click the login button")
    public void i_click_the_login_button() {
        driver.findElement(By.xpath("//div[@id='root']/main/div[2]/div/div/div/div/aside/div[2]/form/div[2]/button/span")).click();
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        boolean loggedInSuccessfully = driver.findElement(By.cssSelector(".column:nth-child(2) .title > a")).isDisplayed();
        assert(loggedInSuccessfully);
        driver.quit();
    }
}

