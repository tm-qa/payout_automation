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


    @Test(priority = 1, enabled = true)
    public void verifyEarlyCycleMove() throws Exception {
        cycleMovePage.move_CyclePayments("EarlyCycleMove_C2_C1.csv", "202511C2", "202511C1");
        cycleMovePage.verifyMoveEntryCycle_ViaBulkSearch("EarlyCycleMove_C2_C1.csv");

    }

    @Test(priority = 2, enabled = true)
    public void verifyLaterCycleMove() throws Exception {
        cycleMovePage.move_CyclePayments("LaterCycleMove_C1_C2.csv", "202511C1", "202511C2");
        cycleMovePage.verifyMoveEntryCycle_ViaBulkSearch("LaterCycleMove_C1_C2.csv");
    }

    @Test(priority = 3, enabled = true)
    public void verifyQuickPayCycleMove() throws Exception {
        cycleMovePage.move_CyclePayments("QuickPayCycleMove_C2_QP.csv", "202511C2", "20251125");
        cycleMovePage.verifyMoveEntryCycle_ViaBulkSearch("QuickPayCycleMove_C2_QP.csv");
    }

    @Test(priority = 4, enabled = true, retryAnalyzer = RetryAnalyser.class)
    public void moveBackInEarlyCycle() throws InterruptedException {
        cycleMovePage.moveBackInCycle_ToContinueFlow("MoveBackCycle_QP_C2.csv", "20251125", "202511C2");
    }


    @AfterTest()
    public void close() {
        driver.close();
    }
}
