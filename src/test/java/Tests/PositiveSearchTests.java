package Tests;
import Models.SearchModel;
import com.opencsv.CSVReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.SearchPage;
import pageObjects.SearchResultsPage;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class PositiveSearchTests extends BaseTest {

    @DataProvider(name = "positiveSearchCsvDp")
    public Iterator<Object[]> csvDpCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
        File f = new File("src\\test\\resources\\data\\positiveSearchTestData.csv");
        Reader reader = Files.newBufferedReader(Paths.get(f.getAbsolutePath()));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> csvData = csvReader.readAll();
        for (int i = 0; i < csvData.size(); i++) {
            dp.add(new Object[]{new SearchModel(csvData.get(i)[0],csvData.get(i)[1])});
        }
        return dp.iterator();
    }

    public void printPositiveSearchData(SearchModel sm) {
        System.out.println(sm.getKeyword());
        System.out.println(sm.getSearchResults());
    }


    public void searchPositiveActions(SearchModel sm)  {
        SearchPage sp = new SearchPage(driver);
        sp.openSearchPage(hostname);
        sp.search(sm.getKeyword());
    }


    @Test(dataProvider = "positiveSearchCsvDp")
    public void csvTest(SearchModel sm) {
        printPositiveSearchData(sm);
        searchPositiveActions(sm);
        SearchResultsPage srp = new SearchResultsPage(driver);
        srp.openSearchResultsPage(hostname);
    }


}
