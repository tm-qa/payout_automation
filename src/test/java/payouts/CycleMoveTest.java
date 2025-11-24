package payouts;

import com.qa.turtlemint.base.TestBase;
import com.qa.turtlemint.pages.Ninja.ninja;
import com.qa.turtlemint.pages.payouts.CycleMovePage;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.RetryAnalyser;

public class CycleMoveTest extends TestBase {

    ninja ninj;

    CycleMovePage cycleMovePage;

    public CycleMoveTest() {

        super();
    }

    @BeforeClass()
    public void start() throws Exception {
        initialization();
        cycleMovePage = new CycleMovePage();
        ninj = new ninja();
        ninj.NinjaLogin(prop.getProperty("NinjaEmail"), prop.getProperty("NinjaPassword"));
        driver.findElement(By.xpath("//a[@data-auto='payouts-module']")).click();
    }

    @Test(priority = 1, enabled = true, retryAnalyzer = RetryAnalyser.class)//retryAnalyzer = RetryAnalyser.class
    public void verifyEarlyCycleMove() throws Exception {
        cycleMovePage.move_CyclePayments("EarlyCycleMove.csv", "202511C2", "202511C1");
        cycleMovePage.verifyMoveEntryCycle_ViaBulkSearch("EarlyCycleMove.csv");
    }

    @Test(priority = 2, enabled = true, retryAnalyzer = RetryAnalyser.class)//retryAnalyzer = RetryAnalyser.class
    public void verifyLaterCycleMove() throws Exception {
        cycleMovePage.move_CyclePayments("LaterCycleMove.csv", "202511C1", "202511C2");
        cycleMovePage.verifyMoveEntryCycle_ViaBulkSearch("LaterCycleMove.csv");
    }

    @Test(priority = 3, enabled = true, retryAnalyzer = RetryAnalyser.class)//retryAnalyzer = RetryAnalyser.class
    public void verifyQuickPayCycleMove() throws Exception {
        cycleMovePage.move_CyclePayments("QuickPayCycleMove.csv", "202511C2", "20251125");
        cycleMovePage.verifyMoveEntryCycle_ViaBulkSearch("QuickPayCycleMove.csv");
    }

    @Test(priority = 4, enabled = true, retryAnalyzer = RetryAnalyser.class)//retryAnalyzer = RetryAnalyser.class
    public void moveBackInEarlyCycle() throws Exception {
        cycleMovePage.moveBackInCycle_ToContinueFlow("MoveBackCycle.csv", "20251125", "202511C2");
    }

    @AfterTest()
    public void close() throws Exception {
        driver.close();
    }
}
