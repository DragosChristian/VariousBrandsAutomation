package Tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.cucumber.junit.Cucumber.*;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/data",
        glue = "pageObjects",
        plugin = { "pretty", "html:target/cucumber-reports" }
)
public class TestRunner {

}

