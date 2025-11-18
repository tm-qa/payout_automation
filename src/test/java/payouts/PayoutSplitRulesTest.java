package payouts;

import com.qa.turtlemint.base.TestBase;
import com.qa.turtlemint.pages.DB_Assertions.ValidatePayoutRules;
import com.qa.turtlemint.pages.Ninja.ninja;
import com.qa.turtlemint.pages.payouts.PayoutSplitRulesPage;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.RetryAnalyser;

@Test(groups = {"payout split rule upload"})
public class PayoutSplitRulesTest extends TestBase {

    ninja ninj;

    PayoutSplitRulesPage payoutSplitRulesPage;

    ValidatePayoutRules db;

    public PayoutSplitRulesTest() {
        super();
    }

    @BeforeMethod()
    public void start() throws Exception {
        initialization();
        payoutSplitRulesPage = new PayoutSplitRulesPage();
        ninj = new ninja();
        db = new ValidatePayoutRules();
        ninj.NinjaLogin(prop.getProperty("NinjaEmail"), prop.getProperty("NinjaPassword"));
        driver.findElement(By.xpath("//a[@data-auto='payouts-module']")).click();
    }

    @Test(priority = 1)//, enabled = true, retryAnalyzer = RetryAnalyser.class)
    public void verifyUpload_SplitRuleFile() {
        payoutSplitRulesPage.toUploadLatestSPlitRuleFile("SplitRuleFileMain.csv");
        db.ValidatePayoutSplitRules();
    }

    @Test(priority = 2, enabled = true, retryAnalyzer = RetryAnalyser.class)
    public void verifyUpload_SplitRuleWithDeleteRule() {
        payoutSplitRulesPage.toUploadLatestSPlitRuleFile("DeleteSplitRules.csv");
    }

    @Test(priority = 3, enabled = true, retryAnalyzer = RetryAnalyser.class)
    public void verifyUpload_SplitRuleWithNewRule() {
        payoutSplitRulesPage.toUploadLatestSPlitRuleFile("AddNewSplitRules.csv");
    }

    @Test(priority = 4, enabled = true, retryAnalyzer = RetryAnalyser.class)
    public void verifyUpload_SplitRuleWithChangeInRule() {
        payoutSplitRulesPage.toUploadLatestSPlitRuleFile("ChangeInSplitRules.csv");
        payoutSplitRulesPage.downloadReportFrmHistory();
    }

    @Test(priority = 5, enabled = true, retryAnalyzer = RetryAnalyser.class)
    public void verifyUpload_SplitRuleWithDuplicateID() {
        payoutSplitRulesPage.uploadDuplicateIdRuleFile("DuplicateIdSplitRules.csv");
    }

    @AfterTest()
    public void close() throws Exception {
        driver.close();
    }

}
