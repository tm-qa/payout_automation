package payouts;


import com.qa.turtlemint.base.TestBase;
import com.qa.turtlemint.pages.DB_Assertions.ValidatePayoutRules;
import com.qa.turtlemint.pages.Ninja.ninja;
import com.qa.turtlemint.pages.payouts.PayoutRulesPage;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.RetryAnalyser;

@Test(groups = {"payout rule upload"})
public class PayoutRulesTest extends TestBase {

    ninja ninj;

    PayoutRulesPage payoutRulesPage;

    ValidatePayoutRules db;

    public PayoutRulesTest() {
        super();
    }

    @BeforeMethod()
    public void start() throws Exception {
        initialization();
        payoutRulesPage = new PayoutRulesPage();
        ninj = new ninja();
        db = new ValidatePayoutRules();
        ninj.NinjaLogin(prop.getProperty("NinjaEmail"), prop.getProperty("NinjaPassword"));
        driver.findElement(By.xpath("//a[@data-auto='payouts-module']")).click();
    }

    @Test(retryAnalyzer = RetryAnalyser.class)
    public void a_verifyUploadRuleFile() {
        payoutRulesPage.toUploadLatestRuleFile("UniqueIdRuleFile.csv");
        db.ValidatePayoutRules();
    }

    @Test(retryAnalyzer = RetryAnalyser.class)
    public void b_verifyUploadRuleWithMasterDataFile() {
        payoutRulesPage.toUploadRuleFileWithMasterDataFile("UniqueIdRuleFile.csv","materTestDataFile.csv");
        payoutRulesPage.downloadReportFrmHistory();
    }
//
   @Test(retryAnalyzer = RetryAnalyser.class)
        public void c_verifyUploadRuleWithDeleteRule() {
       payoutRulesPage.toUploadLatestRuleFile("DeleteRulesFile.csv");
    }

    @Test(retryAnalyzer = RetryAnalyser.class)
    public void d_verifyUploadRuleWithNewRule() {
        payoutRulesPage.toUploadLatestRuleFile("AddNewRules.csv");
    }

    @Test(retryAnalyzer = RetryAnalyser.class)
    public void e_verifyUploadRuleWithChangeInRule() {
        payoutRulesPage.toUploadLatestRuleFile("ChangeInRules.csv");
    }

    @Test(retryAnalyzer = RetryAnalyser.class)
    public void f_verifyUploadRuleWithDuplicateID() {
        payoutRulesPage.uploadDuplicateIdRuleFile("DuplicateRuleIds.csv");
    }

    @Test(retryAnalyzer = RetryAnalyser.class)
    public void g_UploadeLatestRuleFile() throws InterruptedException {
        payoutRulesPage.toUploadLatestRuleFile("SanityRuleFileMain.csv");
    }

    @AfterTest()
    public void close() throws Exception {
        driver.close();
    }

}
