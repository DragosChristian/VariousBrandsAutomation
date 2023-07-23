package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class HomePageTests extends BaseTest {

    @Test
    public void HomePageTitlesCheck() {
        HomePage hp = new HomePage(driver);
        hp.openHomePage(hostname);
        Assert.assertEquals(driver.getTitle(), "Sportexperience - Various Brands");
        Assert.assertEquals(hp.verifyLogoURL(), "https://www.various-brands.ro/Logo-uvr.png");
        Assert.assertTrue(hp.verifySearchField());
        Assert.assertEquals(hp.verifySearchButton(), "Cauta");
        Assert.assertEquals(hp.verifyCategoriaNew(), "NEW");
        Assert.assertEquals(hp.verifyCategoriaFemei(), "FEMEI");
        Assert.assertEquals(hp.verifyCategoriaCopii(), "COPII");
        Assert.assertEquals(hp.verifyCategoriaBarbati(), "BARBATI");
        Assert.assertEquals(hp.verifyCategoriaAccesorii(), "ACCESORII");
        Assert.assertEquals(hp.verifyCategoriaPromo(), "PROMO");
        Assert.assertEquals(hp.verifyPhoneNumber(), "Tel. : 0374 490 671");
        Assert.assertEquals(hp.verifyContulMeu(), "Contul meu");
        Assert.assertEquals(hp.verifyFavorites(), "Lista de favorite");
        Assert.assertEquals(hp.verifyCosulMeu(), "Cos de cumparaturi");
        Assert.assertEquals(hp.verifyContactEmail(), "E-mail: comenzi@various-brands.ro");
    }

    @Test
    public void checkBrokenLinks() {
        HomePage hp = new HomePage(driver);
        hp.openHomePage(hostname);
        List<WebElement> links = driver.findElements(By.tagName("a"));

        for (WebElement link : links) {
            String url = link.getAttribute("href");
            if (url != null && !url.isEmpty()) {
                checkLinkStatus(url);
                delay(1500);
            }
        }
        driver.quit(); }

    public static void checkLinkStatus(String url) {
        try {
            URL linkUrl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) linkUrl.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("HEAD");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println(url + " - " + "Status: " + responseCode);
            } else {
                System.err.println(url + " - " + "Status: " + responseCode);
            }
        } catch (Exception e) {
            System.err.println(url + " - " + "Error: " + e.getMessage());
        }
    }

    public static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


