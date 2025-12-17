package payouts;

import com.qa.turtlemint.base.TestBase;
import com.qa.turtlemint.pages.Ninja.ninja;
import com.qa.turtlemint.pages.payouts.QuickSearchPage;
import com.qa.turtlemint.util.LogUtils;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import util.RetryAnalyser;
import util.iTestListener;

@Listeners(iTestListener.class)
@Test(groups = {"Quick_Search_Test","Whole_Payouts"})
public class QuickSearchTest extends TestBase {

    QuickSearchPage quickSearchPage;

    ninja ninj;

    String cu;

    public QuickSearchTest() {
        super();
    }

    @BeforeMethod()
    public void start() throws Exception {
        initialization();
        quickSearchPage = new QuickSearchPage();
        ninj = new ninja();
        driver.get(System.getProperty("ninjaurl"));
//        driver.get(prop.getProperty("sanityurl"));
        ninj.NinjaLogin(prop.getProperty("NinjaEmail"), prop.getProperty("NinjaPassword"));
        driver.findElement(By.xpath("//a[@data-auto='payouts-module']")).click();
        quickSearchPage.quickSearchClick();
        cu = driver.getCurrentUrl();
        LogUtils.info(cu);
        driver.get(cu);
    }

//    @BeforeMethod()
//    public void loginLess() throws Exception {
//        driver.get(cu);
//    }


    @Test(priority = 1)
    public void verifyValid_QuickSearch() throws InterruptedException {
        quickSearchPage.searchByValid_Partner_ID("6290f07ed35ae3058a14b495");
        quickSearchPage.searchByValid_MIS_ID("MIS_MHQS4PFOT1K");
        quickSearchPage.searchByValid_Partner_MIS_ID("6290f07ed35ae3058a14b495","MIS_MHQS4PFOT1K");
        quickSearchPage.searchByValid_From_To_Cycles("6290f07ed35ae3058a14b495","MIS_MHQS4PFOT1K","Oct 2025 C1","Nov 2025 C2");
        quickSearchPage.searchByValid_From_To_Cycles_But_DataNotPresent("6290f07ed35ae3058a14b495","MIS_MHQS4PFOT1K","Aug 2025 C1","Sep 2025 C2");
    }

    @Test(priority = 2)
    public void verifyInvalid_QuickSearch() throws InterruptedException {
        quickSearchPage.searchByInvalid_Partner_ID("6290f07ed35ae3058a14b");
        quickSearchPage.searchByInvalid_MIS_ID("MIS_AHSBF7U1234");
        quickSearchPage.searchByInvalid_PartnerID_MIS_ID("6290f07ed35ae3058a14b", "MIS_AHSBF7U1234");
        quickSearchPage.searchByValid_PartnerID_Invalid_MIS_ID("63b54bb9ee10470001250bb6", "MIS_AHSBF7U1234");
        quickSearchPage.searchByInvalid_PartnerID_Valid_MIS_ID("6290f07ed35ae3058a14b", "MIS_MHQS4PFOT1K");
        quickSearchPage.searchByValid_From_To_InvalidCycleRange("6290f07ed35ae3058a14b495","MIS_MHQS4PFOT1K","Oct 2025 C1","Oct 2025 C1");
    }

    @Test(priority = 3)
    public void verifyValidMIS_BulkSearch() throws InterruptedException {
        quickSearchPage.bulkSearchByMIS_ID("Valid_MIS_BulkSearch.csv");
        quickSearchPage.bulkSearchByMIS_ID("Valid_Invalid_MIS_BulkSearch.csv");
        quickSearchPage.bulkSearchBy_Invalid_MIS_ID("Invalid_MIS_BulkSearch.csv");
        quickSearchPage.bulkSearchByMIS_ID_Cycle("Valid_MIS_BulkSearch.csv","Oct 2025 C2","Nov 2025 C2");
        quickSearchPage.bulkSearchInvalidCycleRange("Valid_MIS_BulkSearch.csv","Aug 2025 C1","Aug 2025 C1");
    }

    @AfterMethod()
    public void close(){
        driver.close();
    }
}
