package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;

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

}
