package pageObjects;

import Utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class HomePage {

    public WebDriver driver;
    WebDriverWait wait;

    By logo = By.cssSelector(".header-logo-32I.image-loaded-SHk");
    By searchButton = By.xpath("//img[@title='Cauta']");
    By newProduse = By.xpath("//a[contains(text(),'NEW')]");
    By meniuCategoriaBarbati = By.xpath("//a[@class='megaMenuItem-megaMenuLink-12G'][normalize-space()='BARBATI']");
    By meniuCategoriaCopii = By.xpath("//a[normalize-space()='COPII']");
    By meniuCategoriaFemei = By.xpath("//a[@class='megaMenuItem-megaMenuLink-12G'][normalize-space()='FEMEI']");
    By meniuCategoriaPromo = By.xpath("//a[contains(text(),'PROMO')]");
    By contulMeu = By.xpath("//button[@aria-label='Toggle My Account Menu']//img[@title='Contul meu']");
    By phoneNumber = By.xpath("//a[normalize-space()='Tel. : 0374 490 671']");
    By favorites = By.className("header-countnotshow-UJ6");
    By cosulMeu = By.xpath("//img[@title='Cos de cumparaturi']");
    By categoriaAccesorii = By.xpath("//a[normalize-space()='ACCESORII']");
    By contactEmail = By.xpath("//a[normalize-space()='E-mail: comenzi@various-brands.ro']");




    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(this.driver, this);
    }

    public void openHomePage(String hostname) {
        System.out.println("Open the next URL: " + hostname);
        driver.get(hostname);
        By cookieAccept = By.xpath("//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']");
        SeleniumUtils.waitForGenericElement(driver, cookieAccept, 15 ).click();
    }

    public String verifyLogoURL() {
        return SeleniumUtils.waitForGenericElement(driver, logo, 15).getAttribute("src");
    }

    public boolean verifySearchField()  {
        WebElement mySearchButton = driver.findElement(By.xpath("//img[@title='Cauta']"));
        mySearchButton.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500));
        WebElement searchField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("search_query")));
        return searchField.isDisplayed();
    }

    public String verifySearchButton() {
        return SeleniumUtils.waitForGenericElement(driver, searchButton, 15).getAttribute("title");
    }


    public String verifyCategoriaCopii() {
        return SeleniumUtils.waitForGenericElement(driver, meniuCategoriaCopii, 15).getText();
    }

    public String verifyCategoriaNew() {
        return SeleniumUtils.waitForGenericElement(driver, newProduse, 15).getText();
    }

    public String verifyCategoriaFemei() {
        return SeleniumUtils.waitForGenericElement(driver, meniuCategoriaFemei, 15).getText();
    }
    public String verifyCategoriaBarbati() {
        return SeleniumUtils.waitForGenericElement(driver, meniuCategoriaBarbati, 15).getText();
    }

    public String verifyCategoriaPromo() {
        return SeleniumUtils.waitForGenericElement(driver, meniuCategoriaPromo, 15).getText();
    }

    public String verifyContulMeu() {
        return SeleniumUtils.waitForGenericElement(driver, contulMeu, 15).getAttribute("title");
    }


    public String verifyPhoneNumber() {
        return  SeleniumUtils.waitForGenericElement(driver, phoneNumber, 15).getText();
    }

    public String verifyFavorites() {
        return  SeleniumUtils.waitForGenericElement(driver, favorites, 15).getAttribute("title");
    }

    public String verifyCosulMeu() {
        return  SeleniumUtils.waitForGenericElement(driver, cosulMeu, 15).getAttribute("title");
    }

    public String verifyCategoriaAccesorii() {
        return  SeleniumUtils.waitForGenericElement(driver, categoriaAccesorii, 15).getText();
    }

    public String verifyContactEmail() {
        return  SeleniumUtils.waitForGenericElement(driver, contactEmail, 15).getText();
    }

















}

