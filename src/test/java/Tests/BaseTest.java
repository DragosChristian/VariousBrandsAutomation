package Tests;

import Utils.OtherUtils;
import Utils.SeleniumUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;
    String hostname;
    String dbHostname;
    String dbPort;
    String dbSchema;
    String dbUsername;
    String dbPassword;
    ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("test-results/ExtendReports");
    ExtentTest test;

    @BeforeClass
    public void setUp() {
        String browserType = null;
        //Method 1 -D cmd line parameters
        System.out.println(System.getProperty("browser"));
        browserType = System.getProperty("browser");

        try {
            Properties prop = OtherUtils.readPropertiesFile("src\\test\\java\\framework.properties");

//            read default value from config
            if (browserType == null)
                browserType = prop.getProperty("browser");

            System.out.println("Run test with browser:" + browserType);
            driver = SeleniumUtils.getDriver(browserType);

            hostname = prop.getProperty("hostname");
            System.out.println("Use the next hostname:" + hostname);
            dbHostname = prop.getProperty("dbHostname");
            System.out.println("Using DB hostname: " + dbHostname);
            dbPort = prop.getProperty("dbPort");
            System.out.println("Using DB port: " + dbPort);
            dbSchema = prop.getProperty("dbSchema");
            System.out.println("Using DB Schema: " + dbSchema);
            dbUsername = prop.getProperty("dbUser");
            dbPassword = prop.getProperty("dbPassword");
            System.out.println("Using DB credentials: " + dbUsername + "/" + dbPassword);

//            setUp expected reports
            extent.attachReporter(spark);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterClass
    public void cleanUp() {
        System.out.println("Close driver at end of class test");
        driver.quit();
    }

    @AfterMethod
    public synchronized void afterMethod(ITestResult result) {
        if (test != null) {
            switch (result.getStatus()) {
                case ITestResult.FAILURE:
                    String errorMsg = result.getThrowable().getMessage();
                    String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
                    test.fail("TEST FAILED:" + errorMsg, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
                    break;
                case ITestResult.SUCCESS:
                    test.log(Status.PASS, "TEST PASSED");
                    break;
                case ITestResult.SKIP:
                    test.log(Status.SKIP, "TEST SKIPP");
            }
            extent.flush();
        }
    }

    public void logInfoStatus(String message) {
        if (test != null)
            test.log(Status.INFO, message);
        System.out.println(message);
    }


}

