package pageObjects;

import Utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchResultsPage {

    WebDriver driver;
    WebDriverWait wait;


    @FindBy(how = How.XPATH, using = "//*[@id=\"content\"]/div[3]/div[1]/a[1]/i")
    WebElement productsGrid;
    By exampleCartProduct = By.xpath("//*[@id=\"content\"]/div[4]/div[12]/div/div[2]/div[5]/a/i");
    By anotherProduct = By.xpath("//*[@id=\"content\"]/div[4]/div[10]/div/div[2]/div[4]/a/i");
    By cartTotal = By.xpath("//*[@id=\"cart-total\"]");
    @FindBy(how = How.XPATH, using = "//*[@id=\"cart\"]/div[1]/a/i")
    WebElement cart;
    @FindBy(how = How.CLASS_NAME, using = "name")
    List<WebElement> products;
    By productFilterHeader = By.xpath("//*[@id=\"content\"]/h2[2]");


    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(this.driver, this);
    }

    public void openSearchResultsPage(String hostname) {
        System.out.println("Open the next url:" + hostname);
        driver.get(hostname);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.findElement(By.xpath("//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']")).click();
    }

    public String getProductNumber() {
        List<WebElement> list = driver.findElements(By.className("name"));
        return String.valueOf(list.size());
    }

    public String getTotalCart() {
        return SeleniumUtils.waitForGenericElement(driver, cartTotal, 15).getText();
    }

    public WebElement getProduct() {
        return SeleniumUtils.waitForGenericElement(driver, exampleCartProduct, 15);
    }

    public WebElement getAnotherProduct() {
        return SeleniumUtils.waitForGenericElement(driver, anotherProduct, 15);
    }

    public String filterHeaderText() {
        return SeleniumUtils.waitForGenericElement(driver, productFilterHeader, 15).getText();
    }



}
