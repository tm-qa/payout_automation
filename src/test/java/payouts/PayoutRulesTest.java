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

@Test(groups = {"payout"})
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

    @Test(priority = 1, enabled = true, retryAnalyzer = RetryAnalyser.class)
    public void verifyUploadRuleFile() {
        payoutRulesPage.toUploadLatestRuleFile("UniqueIdRuleFile.csv");
        db.ValidatePayoutRules();
    }

    @Test(priority = 2, enabled = true, retryAnalyzer = RetryAnalyser.class)
    public void verifyUploadRuleWithMasterDataFile() {
        payoutRulesPage.toUploadRuleFileWithMasterDataFile("UniqueIdRuleFile.csv","materTestDataFile.csv");
        payoutRulesPage.downloadReportFrmHistory();
    }
//
   @Test(priority = 3, enabled = true, retryAnalyzer = RetryAnalyser.class)
        public void verifyUploadRuleWithDeleteRule() {
       payoutRulesPage.toUploadLatestRuleFile("DeleteRulesFile.csv");
    }

    @Test(priority = 4, enabled = true, retryAnalyzer = RetryAnalyser.class)
    public void verifyUploadRuleWithNewRule() {
        payoutRulesPage.toUploadLatestRuleFile("AddNewRules.csv");
    }

    @Test(priority = 5, enabled = true, retryAnalyzer = RetryAnalyser.class)
    public void verifyUploadRuleWithChangeInRule() {
        payoutRulesPage.toUploadLatestRuleFile("ChangeInRules.csv");
    }

    @Test(priority = 6, enabled = true, retryAnalyzer = RetryAnalyser.class)
    public void verifyUploadRuleWithDuplicateID() {
        payoutRulesPage.uploadDuplicateIdRuleFile("DuplicateRuleIds.csv");
    }

    @Test(priority = 7, enabled = true, retryAnalyzer = RetryAnalyser.class)
    public void UploadeLatestRuleFile() throws InterruptedException {
        payoutRulesPage.toUploadLatestRuleFile("SanityRuleFileMain.csv");
    }

    @AfterTest()
    public void close() throws Exception {
        driver.close();
    }

}
