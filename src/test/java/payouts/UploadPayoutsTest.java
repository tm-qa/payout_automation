package payouts;
import com.qa.turtlemint.base.TestBase;
import com.qa.turtlemint.pages.DB_Assertions.UploadPayoutsDB;
import com.qa.turtlemint.pages.DB_Assertions.UploadPayoutsDB;
import com.qa.turtlemint.pages.Ninja.ninja;
import com.qa.turtlemint.pages.payouts.CycleMovePage;
import com.qa.turtlemint.pages.payouts.DownloadPayoutsCyclePage;
import com.qa.turtlemint.pages.payouts.UploadPayoutsPage;
import com.qa.turtlemint.util.TestUtil;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
@Test(groups = {"Upload Payouts"})
public class UploadPayoutsTest extends TestBase {
    ninja ninj;
    CycleMovePage cycleMovePage;
    DownloadPayoutsCyclePage downloadPayoutsCyclePage;
    UploadPayoutsPage uploadPayoutsPage;
    UploadPayoutsDB uploadPayoutsDB;
    String pcid;
    public UploadPayoutsTest() {
        super();
    }
    @BeforeClass()
    public void start() throws Exception {
        initialization();
        cycleMovePage = new CycleMovePage();
        ninj = new ninja();
        downloadPayoutsCyclePage = new DownloadPayoutsCyclePage();
        uploadPayoutsPage = new UploadPayoutsPage();
        uploadPayoutsDB = new UploadPayoutsDB();
        ninj.NinjaLogin(prop.getProperty("NinjaEmail"), prop.getProperty("NinjaPassword"));
        driver.findElement(By.xpath("//a[@data-auto='payouts-module']")).click();
        TestUtil.click(downloadPayoutsCyclePage.ledgerBtn, "Payout Ledger Button Clicked");
    }
    @Test(priority = 1, enabled = true)
    public void verifyManualUploads() throws InterruptedException {
        uploadPayoutsPage.manualUpload("ManualUpload.csv", "Dec 2025 C2");
        uploadPayoutsPage.verifyVia_BulkSearch("ManualUploadBulkSearch.csv");
        uploadPayoutsDB.deleteEntitriesFromLedgerEntity("LedgerEntity","202512C2"); // Clear Uploaded Data From DB
        uploadPayoutsDB.deleteEntitriesFromPolicyCommissions("PolicyCommissions","202512C2");
    }
    @Test(priority = 1, enabled = true)
    public void verifyManualCorrection() throws InterruptedException {
        uploadPayoutsPage.manualCorrection("ManualCorrection.csv");
        uploadPayoutsPage.verifyVia_BulkSearch("ManualCorrectionBulkSearch.csv");
//        uploadPayoutsDB.deleteEntitriesFromLedgerEntity("LedgerEntity","202512C2"); // Clear Uploaded Data From DB
//        uploadPayoutsDB.deleteEntitriesFromPolicyCommissions("PolicyCommissions","202512C2");
    }
    @Test(priority = 1, enabled = true)
    public void verifyDeviations() throws Exception {
        ninj.punch_TW_Policy();
        uploadPayoutsPage.validate_MIS_EntryAtPayouts();
        uploadPayoutsPage.uploadDeviation("INCORRECT_RULES.csv");
        uploadPayoutsPage.downloadDeviationQuickSearchResult();
        uploadPayoutsPage.validateDeviationQuickSearchResult("INCORRECT_RULES");
    }
}
