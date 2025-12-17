package payouts;

import com.qa.turtlemint.base.TestBase;
import com.qa.turtlemint.pages.Ninja.ninja;
import com.qa.turtlemint.pages.payouts.CycleMovePage;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.RetryAnalyser;

@Test(groups = {"Cycle_Move_Test","Whole_Payouts"})
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
         driver.get(System.getProperty("ninjaurl"));
//        driver.get(prop.getProperty("sanityurl"));
        ninj.NinjaLogin(prop.getProperty("NinjaEmail"), prop.getProperty("NinjaPassword"));
        driver.findElement(By.xpath("//a[@data-auto='payouts-module']")).click();
    }


    @Test(priority = 1, enabled = true)
    public void verifyEarlyCycleMove() throws Exception {
        cycleMovePage.move_CyclePayments("EarlyCycleMove_C2_C1.csv", "202511C2", "202511C1");
        cycleMovePage.verifyMoveEntryCycle_ViaBulkSearch("EarlyCycleMove_C2_C1.csv","MoveCycleAssert.csv");
    }

    @Test(priority = 2, enabled = true)
    public void verifyLaterCycleMove() throws Exception {
        cycleMovePage.move_CyclePayments("LaterCycleMove_C1_C2.csv", "202511C1", "202511C2");
        cycleMovePage.verifyMoveEntryCycle_ViaBulkSearch("LaterCycleMove_C1_C2.csv","MoveCycleAssert.csv");
    }

    @Test(priority = 3, enabled = true)
    public void verifyQuickPayCycleMove() throws Exception {
        cycleMovePage.move_CyclePayments("QuickPayCycleMove_C2_QP.csv", "202511C2", "20251126");
        cycleMovePage.verifyMoveEntryCycle_ViaBulkSearch("QuickPayCycleMove_C2_QP.csv","MoveCycleAssert.csv");
    }

    @Test(priority = 4, enabled = true, retryAnalyzer = RetryAnalyser.class)
    public void moveBackInEarlyCycle() throws InterruptedException {
        cycleMovePage.moveBackInCycle_ToContinueFlow("MoveBackCycle_QP_C2.csv", "20251126", "202511C2");
    }

    @AfterClass()
    public void close(){
        driver.close();
    }
}
