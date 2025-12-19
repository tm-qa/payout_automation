
package payouts;
import com.qa.turtlemint.base.TestBase;
import com.qa.turtlemint.pages.Ninja.ninja;
import com.qa.turtlemint.pages.payouts.CycleMovePage;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import util.RetryAnalyser;

@Test(groups = {"Cycle_Move_Test","Whole_Payouts"})
public class CycleMoveTest extends TestBase {

    ninja ninj;

    CycleMovePage cycleMovePage;
  public   String cu;

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
        cu= driver.getCurrentUrl();
    }

    @BeforeMethod()
    public void loginless() {
       driver.get(cu);
    }

    @Test
    public void verifyEarlyCycleMove() throws Exception {
        cycleMovePage.move_CyclePayments("EarlyCycleMove_C2_C1.csv", "202511C2", "202511C1");
        cycleMovePage.verifyMoveEntryCycle_ViaBulkSearch("EarlyCycleMove_C2_C1.csv","MoveCycleAssert.csv");
    }

    @Test
    public void verifyLaterCycleMove() throws Exception {
        cycleMovePage.move_CyclePayments("LaterCycleMove_C1_C2.csv", "202511C1", "202511C2");
        cycleMovePage.verifyMoveEntryCycle_ViaBulkSearch("LaterCycleMove_C1_C2.csv","MoveCycleAssert.csv");
    }

    @Test
    public void verifyQuickPayCycleMove() throws Exception {
        cycleMovePage.move_CyclePayments("QuickPayCycleMove_C2_QP.csv", "202511C2", "20251126");
        cycleMovePage.verifyMoveEntryCycle_ViaBulkSearch("QuickPayCycleMove_C2_QP.csv","MoveCycleAssert.csv");
    }

    @Test(retryAnalyzer = RetryAnalyser.class)
    public void verifyTestMoveBackInEarlyCycle() throws InterruptedException {
        cycleMovePage.moveBackInCycle_ToContinueFlow("MoveBackCycle_QP_C2.csv", "20251126", "202511C2");
    }

    @AfterClass()
    public void closeBrowser(){
        driver.quit();
    }
}