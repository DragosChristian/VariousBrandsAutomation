package Utils;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Properties;


public class OtherUtils {


    public static void logInfoStatus(ExtentTest test, String message) {
        if (test != null)
            test.log(Status.INFO, message);
        System.out.println(message);
    }


    public static String sanitizeNullDbString (String dbResult) {
        if (dbResult == null) {
            return "";
        }
        return dbResult;
    }

    public static boolean sanitizeNullDbBoolean(String dbResult){
        if(dbResult.isEmpty()){
            return true;
        }
        return false;
    }



    public static Properties readPropertiesFile(String path) throws IOException {
        InputStream input = new FileInputStream(path);
        Properties prop = new Properties();
        prop.load(input);
        return prop;
    }

    public static void takeScreenShot(WebDriver driver) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File finalFile = new File(SeleniumUtils.screenShotPath + "\\screenshot-" + timestamp.getTime() + ".png");
        try {
            FileUtils.copyFile(screenshotFile, finalFile);
        } catch (IOException e) {
            System.out.println("File could not be saved");
        }
    }

    public static void jsContactScroll(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("scrollBy(0, 1000)");
    }



}

