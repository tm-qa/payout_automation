package payouts;

import com.qa.turtlemint.base.TestBase;
import com.qa.turtlemint.pages.Ninja.ninja;
import com.qa.turtlemint.pages.payouts.DownloadPayoutsCyclePage;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.RetryAnalyser;

public class DownloadPayoutsCycleTest extends TestBase {

    DownloadPayoutsCyclePage downloadPayoutsCyclePage;
    ninja ninj;

    public DownloadPayoutsCycleTest() {
        super();
    }

    @BeforeClass()
    public void start() throws Exception {
        initialization();
        downloadPayoutsCyclePage = new DownloadPayoutsCyclePage();
        ninj = new ninja();
        ninj.NinjaLogin(prop.getProperty("NinjaEmail"), prop.getProperty("NinjaPassword"));
        driver.findElement(By.xpath("//a[@data-auto='payouts-module']")).click();
    }

    @Test(priority = 0, enabled = true, retryAnalyzer = RetryAnalyser.class)
    public void verify_Regular_CyclePayoutsDumpDownload() {
        downloadPayoutsCyclePage.downloadPayouts();
        downloadPayoutsCyclePage.selectPaymentCycle("Nov 2025 C1","202511C1");
        downloadPayoutsCyclePage.selectPaymentCycle("Nov 2025 C2","202511C2");
        downloadPayoutsCyclePage.selectPaymentCycle("Oct 2025 C1","202510C1");
        downloadPayoutsCyclePage.selectPaymentCycle("Oct 2025 C2","202510C2");
        downloadPayoutsCyclePage.downloadClick();
        downloadPayoutsCyclePage.clearDropdown();
    }

    @Test(priority = 1, enabled = true, retryAnalyzer = RetryAnalyser.class)
    public void verify_QuickPay_CyclePayoutsDumpDownload() {
        downloadPayoutsCyclePage.downloadPayouts();
        downloadPayoutsCyclePage.selectPaymentCycle("20th Nov 2025","20251120");
        downloadPayoutsCyclePage.selectPaymentCycle("21st Nov 2025","20251121");
        downloadPayoutsCyclePage.selectPaymentCycle("22nd Nov 2025","20251122");
        downloadPayoutsCyclePage.selectPaymentCycle("23rd Nov 2025","20251123");
        downloadPayoutsCyclePage.downloadClick();
        downloadPayoutsCyclePage.clearDropdown();
    }

    @Test(priority = 2, enabled = true, retryAnalyzer = RetryAnalyser.class)
    public void verify_QuickPay_Regular_CyclePayoutsDumpDownload() {
        downloadPayoutsCyclePage.downloadPayouts();
        downloadPayoutsCyclePage.selectPaymentCycle("Nov 2025 C1","202511C1");
        downloadPayoutsCyclePage.selectPaymentCycle("Nov 2025 C2","202511C2");
        downloadPayoutsCyclePage.selectPaymentCycle("20th Nov 2025","20251120");
        downloadPayoutsCyclePage.selectPaymentCycle("21st Nov 2025","20251121");
        downloadPayoutsCyclePage.downloadClick();
        downloadPayoutsCyclePage.clearDropdown();
    }
}
