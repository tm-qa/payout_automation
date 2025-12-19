package payouts;

import com.qa.turtlemint.base.TestBase;
import com.qa.turtlemint.pages.Ninja.ninja;
import com.qa.turtlemint.pages.payouts.DownloadPayoutsCyclePage;
import com.qa.turtlemint.util.LogUtils;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.RetryAnalyser;

@Test(groups = {"Download_Payouts_Cycle_Test", "Whole_Payouts"})
public class DownloadPayoutsCycleTest extends TestBase {

    DownloadPayoutsCyclePage downloadPayoutsCyclePage;
    ninja ninj;
   public String cu;

    public DownloadPayoutsCycleTest() {
        super();
    }

    @BeforeClass()
    public void start() throws Exception {
        initialization();
        downloadPayoutsCyclePage = new DownloadPayoutsCyclePage();
        ninj = new ninja();
        driver.get(System.getProperty("ninjaurl"));
//        driver.get(prop.getProperty("sanityurl"));
        ninj.NinjaLogin(prop.getProperty("NinjaEmail"), prop.getProperty("NinjaPassword"));
        driver.findElement(By.xpath("//a[@data-auto='payouts-module']")).click();
        cu = driver.getCurrentUrl();

    }

    @BeforeMethod()
    public void loginLess(){
        driver.get(cu);
    }


    @Test(retryAnalyzer = RetryAnalyser.class)
    public void a_verify_Regular_CyclePayoutsDumpDownload() {
        LogUtils.info("***- Test Case Started : verify_Regular_CyclePayoutsDumpDownload -***");
        downloadPayoutsCyclePage.downloadPayouts();
        downloadPayoutsCyclePage.selectPaymentCycle("Oct 2025 C1", "202510C1");
        downloadPayoutsCyclePage.selectPaymentCycle("Oct 2025 C2", "202510C2");
        downloadPayoutsCyclePage.selectPaymentCycle("Sep 2025 C1", "202509C1");
        downloadPayoutsCyclePage.selectPaymentCycle("Sep 2025 C2", "202509C2");
        downloadPayoutsCyclePage.downloadClick();
        downloadPayoutsCyclePage.validateDownloadedCycle("regularCycle");
        downloadPayoutsCyclePage.clearDropdown();
        LogUtils.info("***- Test Case Completed : verify_Regular_CyclePayoutsDumpDownload -***");
    }

    @Test(retryAnalyzer = RetryAnalyser.class)
    public void b_verify_QuickPay_CyclePayoutsDumpDownload() {
        LogUtils.info("***- Test Case Started : verify_QuickPay_CyclePayoutsDumpDownload -***");
        downloadPayoutsCyclePage.downloadPayouts();
        downloadPayoutsCyclePage.selectPaymentCycle("27th Oct 2025", "20251027");
        downloadPayoutsCyclePage.selectPaymentCycle("28th Oct 2025", "20251028");
        downloadPayoutsCyclePage.selectPaymentCycle("29th Oct 2025", "20251029");
        downloadPayoutsCyclePage.selectPaymentCycle("30th Oct 2025", "20251030");
        downloadPayoutsCyclePage.downloadClick();
        downloadPayoutsCyclePage.validateDownloadedCycle("quickpayCycle");
        downloadPayoutsCyclePage.clearDropdown();
        LogUtils.info("***- Test Case Completed : verify_QuickPay_CyclePayoutsDumpDownload -***");
    }

    @Test(retryAnalyzer = RetryAnalyser.class)
    public void c_verify_QuickPay_Regular_CyclePayoutsDumpDownload() {
        LogUtils.info("***- Test Case Started : verify_QuickPay_Regular_CyclePayoutsDumpDownload -***");
        downloadPayoutsCyclePage.downloadPayouts();
        downloadPayoutsCyclePage.selectPaymentCycle("Oct 2025 C1", "202510C1");
        downloadPayoutsCyclePage.selectPaymentCycle("Oct 2025 C2", "202510C2");
        downloadPayoutsCyclePage.selectPaymentCycle("27th Oct 2025", "20251027");
        downloadPayoutsCyclePage.selectPaymentCycle("28th Oct 2025", "20251028");
        downloadPayoutsCyclePage.downloadClick();
        downloadPayoutsCyclePage.validateDownloadedCycle("regular_quickpayCycle");
        downloadPayoutsCyclePage.clearDropdown();
        LogUtils.info("***- Test Case Completed : verify_QuickPay_Regular_CyclePayoutsDumpDownload -***");
    }

    @AfterClass()
    public void closeBrowser(){
        driver.quit();
    }
}
