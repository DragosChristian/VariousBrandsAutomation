package Tests;

import Models.AccountModel;
import Models.LoginModel;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.LoginPage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static Utils.OtherUtils.sanitizeNullDbString;

public class NegativeLoginTests extends BaseTest {

    @DataProvider(name = "sqlDp")
    public Iterator<Object[]> sqlDpCollection() {
        Collection<Object[]> dp = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://" + dbHostname + ":" + dbPort + "/" + dbSchema,
                    dbUsername, dbPassword);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM variousbrands.login;");
            while (results.next()) {
                AccountModel am = new AccountModel();
                am.setUsername(sanitizeNullDbString(results.getString("username")));
                am.setPassword(sanitizeNullDbString(results.getString("password")));
                LoginModel lm = new LoginModel();
                lm.setAccount(am);
                lm.setGeneralError(sanitizeNullDbString(results.getString("generalError")));
                dp.add(new Object[]{lm});
            }
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dp.iterator();
    }


    public void printData(LoginModel lm) {
        System.out.println(lm.getAccount().getUsername());
        System.out.println(lm.getAccount().getPassword());
        System.out.println(lm.getGeneralError());
    }

    public void loginActions(LoginModel lm) {
        LoginPage lp = new LoginPage(driver);
        lp.openLoginPage(hostname);
        lp.login(lm.getAccount().getUsername(), lm.getAccount().getPassword());
        Assert.assertTrue(lp.checkErr(lm.getGeneralError(), "generalErr"));
    }

    @Test(dataProvider = "sqlDp")
    public void sqlTest(LoginModel lm) {
        printData(lm);
        loginActions(lm);
    }


}
