package payouts;
import com.qa.turtlemint.base.TestBase;
import com.qa.turtlemint.pages.DB_Assertions.UploadPayoutsDB;
import com.qa.turtlemint.pages.Ninja.ninja;
import com.qa.turtlemint.pages.payouts.UploadPayoutsPage;
import com.qa.turtlemint.util.LogUtils;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.RetryAnalyser;

@Test(groups = {"Upload_Payouts_Test","Whole_Payouts"})
public class UploadPayoutsTest extends TestBase {
    ninja ninj;
    UploadPayoutsPage uploadPayoutsPage;
    UploadPayoutsDB uploadPayoutsDB;
    public   String cu;

    public UploadPayoutsTest() {
        super();
    }

    @BeforeClass()
    public void start() throws Exception {
        initialization();
        ninj = new ninja();
        uploadPayoutsPage = new UploadPayoutsPage();
        uploadPayoutsDB = new UploadPayoutsDB();
        driver.get(System.getProperty("ninjaurl"));
//        driver.get(prop.getProperty("sanityninjaurl"));
        ninj.NinjaLogin(prop.getProperty("NinjaEmail"), prop.getProperty("NinjaPassword"));
        driver.findElement(By.xpath("//a[@data-auto='payouts-module']")).click();
        cu = driver.getCurrentUrl();
        LogUtils.info(cu);
    }

    @BeforeMethod()
    public void loginLess() throws Exception {
        driver.get(cu);
    }

    @Test(enabled = false, retryAnalyzer = RetryAnalyser.class)
    public void a_verifyManualUploads() throws InterruptedException {
        uploadPayoutsPage.manualUpload("ManualUpload.csv", "Dec 2025 C2");
        uploadPayoutsPage.verifyVia_BulkSearch("ManualUploadBulkSearch.csv");
        uploadPayoutsDB.deleteEntitriesFromLedgerEntity("LedgerEntity","202512C2"); // Clear Uploaded Data From DB
        uploadPayoutsDB.deleteEntitriesFromPolicyCommissions("PolicyCommissions","202512C2");
    }
    @Test(enabled = false, retryAnalyzer = RetryAnalyser.class)
    public void b_verifyManualCorrection() throws InterruptedException {
        uploadPayoutsPage.manualCorrection("ManualCorrection.csv");
        uploadPayoutsPage.verifyVia_BulkSearch("ManualCorrectionBulkSearch.csv");
//        uploadPayoutsDB.deleteEntitriesFromLedgerEntity("LedgerEntity","202512C2"); // Clear Uploaded Data From DB
//        uploadPayoutsDB.deleteEntitriesFromPolicyCommissions("PolicyCommissions","202512C2");
    }
    @Test(enabled = false, retryAnalyzer = RetryAnalyser.class)
    public void c_verifyDeviationsUpload() throws Exception {
        ninj.punch_TW_Policy();
        uploadPayoutsPage.validate_MIS_EntryAtPayouts();
                    //    Upload INCORRECT_RULES Deviation Type
        uploadPayoutsPage.uploadDeviation("INCORRECT_RULES");
        uploadPayoutsPage.downloadDeviationQuickSearchResult();
        uploadPayoutsPage.validateDeviationQuickSearchResult("INCORRECT_RULES");
                    //    Upload SPECIAL_REQUEST Deviation Type
        uploadPayoutsPage.uploadDeviation("SPECIAL_REQUEST");
        uploadPayoutsPage.downloadDeviationQuickSearchResult();
        uploadPayoutsPage.validateDeviationQuickSearchResult("SPECIAL_REQUEST");
                    //    Upload NOMINAL_DEVIATION Deviation Type
        uploadPayoutsPage.uploadDeviation("NOMINAL_DEVIATION");
        uploadPayoutsPage.downloadDeviationQuickSearchResult();
        uploadPayoutsPage.validateDeviationQuickSearchResult("NOMINAL_DEVIATION");
                    //    Upload Invalid Deviation Type
        uploadPayoutsPage.uploadDeviation("INCORRECT_RULESS");
                    //    Upload Split Deviation for Non Split Partner
        uploadPayoutsPage.uploadSplitDeviations("NonSplitPartner_SPLIT_DEVIATIONS");
    }

    @Test(enabled = false, retryAnalyzer = RetryAnalyser.class)
    public void d_verifySplitDeviationsUpload() throws Exception {
        ninj.punch_TW_Policy();
        uploadPayoutsPage.validate_MIS_EntryAtPayouts();
        uploadPayoutsPage.uploadSplitDeviations("SPLIT_DEVIATIONS");
        uploadPayoutsPage.downloadDeviationQuickSearchResult();
        uploadPayoutsPage.validateDeviationQuickSearchResult("SPLIT_DEVIATIONS");
    }

    @Test(retryAnalyzer = RetryAnalyser.class)
    public void e_verifyAdjustmentsUpload() throws Exception {
//        uploadPayoutsPage.selectAdjustments();
        uploadPayoutsPage.uploadAdjustment("AdjustmentsInvalid.csv","Dec 2025 C2");
        uploadPayoutsPage.uploadAdjustment("Adjustments.csv","Dec 2025 C2");
        uploadPayoutsPage.downloadAdjustmentsQuickSearchResult();
        uploadPayoutsPage.validateDeviationQuickSearchResult("Adjustments");
    }

    @Test(enabled = false, retryAnalyzer = RetryAnalyser.class)
    public void f_verifyPayoutQCUpload() throws Exception {
        uploadPayoutsPage.uploadAdjustment("AdjustmentsInvalid.csv","Dec 2025 C2");
        uploadPayoutsPage.uploadAdjustment("Adjustments.csv","Dec 2025 C2");
        uploadPayoutsPage.downloadAdjustmentsQuickSearchResult();
        uploadPayoutsPage.validateDeviationQuickSearchResult("Adjustments");
    }


}
