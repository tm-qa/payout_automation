package payouts;

import com.qa.turtlemint.base.TestBase;
import com.qa.turtlemint.pages.Ninja.ninja;
import com.qa.turtlemint.pages.payouts.QuickSearchPage;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.RetryAnalyser;

public class QuickSearchTest extends TestBase {

    QuickSearchPage quickSearchPage;

    ninja ninj;

    public QuickSearchTest() {
        super();
    }

    @BeforeClass()
    public void start() throws Exception {
        initialization();
        quickSearchPage = new QuickSearchPage();
        ninj = new ninja();
        ninj.NinjaLogin(prop.getProperty("NinjaEmail"), prop.getProperty("NinjaPassword"));
        driver.findElement(By.xpath("//a[@data-auto='payouts-module']")).click();
        quickSearchPage.quickSearchClick();
    }

    @Test(priority = 0, enabled = true, retryAnalyzer = RetryAnalyser.class)
    public void verifyValid_QuickSearch() throws InterruptedException {
        quickSearchPage.searchByValid_Partner_ID("6290f07ed35ae3058a14b495");
        quickSearchPage.searchByValid_MIS_ID("MIS_AHSBF7UN56P");
        quickSearchPage.searchByValid_Partner_MIS_ID("63b54bb9ee10470001250bb6","MIS_AHSBF7UN56P");
        quickSearchPage.searchByValid_From_To_Cycles("63b54bb9ee10470001250bb6","MIS_AHSBF7UN56P","Oct 2025 C2","Nov 2025 C2");
        quickSearchPage.searchByValid_From_To_Cycles_But_DataNotPresent("63b54bb9ee10470001250bb6","MIS_AHSBF7UN56P","Aug 2025 C1","Sep 2025 C2");
    }

    @Test(priority = 1, enabled = true, retryAnalyzer = RetryAnalyser.class)
    public void verifyInvalid_QuickSearch() throws InterruptedException {
        quickSearchPage.searchByInvalid_Partner_ID("6290f07ed35ae3058a14b");
        quickSearchPage.searchByInvalid_MIS_ID("MIS_AHSBF7U1234");
        quickSearchPage.searchByInvalid_PartnerID_MIS_ID("6290f07ed35ae3058a14b", "MIS_AHSBF7U1234");
        quickSearchPage.searchByValid_PartnerID_Invalid_MIS_ID("63b54bb9ee10470001250bb6", "MIS_AHSBF7U1234");
        quickSearchPage.searchByInvalid_PartnerID_Valid_MIS_ID("6290f07ed35ae3058a14b", "MIS_AHSBF7UN56P");
        quickSearchPage.searchByValid_From_To_InvalidCycleRange("63b54bb9ee10470001250bb6","MIS_AHSBF7UN56P","Nov 2025 C1","Nov 2025 C1");
    }

    @Test(priority = 1, enabled = true, retryAnalyzer = RetryAnalyser.class)
    public void verifyValidMIS_BulkSearch() throws InterruptedException {
        quickSearchPage.bulkSearchByMIS_ID("Valid_MIS_BulkSearch.csv");
        quickSearchPage.bulkSearchByMIS_ID("Valid_Invalid_MIS_BulkSearch.csv");
        quickSearchPage.bulkSearchBy_Invalid_MIS_ID("Invalid_MIS_BulkSearch.csv");
        quickSearchPage.bulkSearchByMIS_ID_Cycle("Valid_MIS_BulkSearch.csv","Oct 2025 C2","Nov 2025 C2");
        quickSearchPage.bulkSearchInvalidCycleRange("Valid_MIS_BulkSearch.csv","Aug 2025 C1","Aug 2025 C1");
    }

    @AfterTest()
    public void close() {
        driver.close();
    }
}
