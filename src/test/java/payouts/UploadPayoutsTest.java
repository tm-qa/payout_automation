package payouts;
import com.qa.turtlemint.base.TestBase;
import com.qa.turtlemint.pages.DB_Assertions.UploadPayoutsDB;
import com.qa.turtlemint.pages.DB_Assertions.UploadPayoutsDB;
import com.qa.turtlemint.pages.Ninja.ninja;
import com.qa.turtlemint.pages.payouts.CycleMovePage;
import com.qa.turtlemint.pages.payouts.DownloadPayoutsCyclePage;
import com.qa.turtlemint.pages.payouts.QuickSearchPage;
import com.qa.turtlemint.pages.payouts.UploadPayoutsPage;
import com.qa.turtlemint.util.LogUtils;
import com.qa.turtlemint.util.TestUtil;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@Test(groups = {"Upload Payouts"})
public class UploadPayoutsTest extends TestBase {
    ninja ninj;
    CycleMovePage cycleMovePage;
    DownloadPayoutsCyclePage downloadPayoutsCyclePage;
    UploadPayoutsPage uploadPayoutsPage;
    UploadPayoutsDB uploadPayoutsDB;
    String pcid;
    public   String cu;
    QuickSearchPage quickSearchPage;

    public UploadPayoutsTest() {
        super();
    }

//    @BeforeClass()
//    public void start() throws Exception {
//        initialization();
//        cycleMovePage = new CycleMovePage();
//        ninj = new ninja();
//        downloadPayoutsCyclePage = new DownloadPayoutsCyclePage();
//        uploadPayoutsPage = new UploadPayoutsPage();
//        uploadPayoutsDB = new UploadPayoutsDB();
//        ninj.NinjaLogin(prop.getProperty("NinjaEmail"), prop.getProperty("NinjaPassword"));
//        driver.findElement(By.xpath("//a[@data-auto='payouts-module']")).click();
//        TestUtil.click(downloadPayoutsCyclePage.ledgerBtn, "Payout Ledger Button Clicked");
//    }

    @BeforeClass()
    public void start() throws Exception {
        initialization();
        quickSearchPage = new QuickSearchPage();
        ninj = new ninja();
        cycleMovePage = new CycleMovePage();
        ninj = new ninja();
        downloadPayoutsCyclePage = new DownloadPayoutsCyclePage();
        uploadPayoutsPage = new UploadPayoutsPage();
        uploadPayoutsDB = new UploadPayoutsDB();
//        driver.get(System.getProperty("ninjaurl"));
        driver.get(prop.getProperty("sanityninjaurl"));
        ninj.NinjaLogin(prop.getProperty("NinjaEmail"), prop.getProperty("NinjaPassword"));
        driver.findElement(By.xpath("//a[@data-auto='payouts-module']")).click();
//        quickSearchPage.quickSearchClick();
        cu = driver.getCurrentUrl();
        LogUtils.info(cu);
    }

    @BeforeMethod()
    public void loginLess() throws Exception {
        driver.get(cu);
    }

    @Test(priority = 1)
    public void verifyManualUploads() throws InterruptedException {
        uploadPayoutsPage.manualUpload("ManualUpload.csv", "Dec 2025 C2");
        uploadPayoutsPage.verifyVia_BulkSearch("ManualUploadBulkSearch.csv");
        uploadPayoutsDB.deleteEntitriesFromLedgerEntity("LedgerEntity","202512C2"); // Clear Uploaded Data From DB
        uploadPayoutsDB.deleteEntitriesFromPolicyCommissions("PolicyCommissions","202512C2");
    }
    @Test(priority = 2)
    public void verifyManualCorrection() throws InterruptedException {
        uploadPayoutsPage.manualCorrection("ManualCorrection.csv");
        uploadPayoutsPage.verifyVia_BulkSearch("ManualCorrectionBulkSearch.csv");
//        uploadPayoutsDB.deleteEntitriesFromLedgerEntity("LedgerEntity","202512C2"); // Clear Uploaded Data From DB
//        uploadPayoutsDB.deleteEntitriesFromPolicyCommissions("PolicyCommissions","202512C2");
    }
    @Test(priority = 3)
    public void verifyDeviations() throws Exception {
//        ninj.punch_TW_Policy();
        uploadPayoutsPage.validate_MIS_EntryAtPayouts();

        uploadPayoutsPage.uploadDeviation("INCORRECT_RULES");
        uploadPayoutsPage.downloadDeviationQuickSearchResult();
        uploadPayoutsPage.validateDeviationQuickSearchResult("INCORRECT_RULES");

        uploadPayoutsPage.uploadDeviation("SPECIAL_REQUEST");
        uploadPayoutsPage.downloadDeviationQuickSearchResult();
        uploadPayoutsPage.validateDeviationQuickSearchResult("SPECIAL_REQUEST");

        uploadPayoutsPage.uploadDeviation("NOMINAL_DEVIATION");
        uploadPayoutsPage.downloadDeviationQuickSearchResult();
        uploadPayoutsPage.validateDeviationQuickSearchResult("NOMINAL_DEVIATION");
    }
}
