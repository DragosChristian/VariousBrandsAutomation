package pageObjects;

import Models.LoginModel;
import Utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchPage {

    public WebDriver driver;
    WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//img[@title='Cauta']")
    WebElement searchButton;

    @FindBy(how = How.NAME, using = "search_query")
    WebElement searchField;

    By searchError = By.xpath("//div[@class='searchPage-noResult-1tm']");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(this.driver, this);
    }

    public void search(String keyword) {
        searchField.clear();
        searchField.sendKeys(keyword);
        waitForSearchButton();
        Actions search = new Actions(driver);
        search.moveToElement(searchButton).click().build().perform();
    }

    public void waitForSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
    }

    public String getSearchError() {
        return SeleniumUtils.waitForGenericElement(driver, searchError, 15).getText();
    }


    public void openSearchPage(String hostname) {
        System.out.println("Open the next url:" + hostname + "product/search");
        driver.get(hostname + "product/search");
    }



}

