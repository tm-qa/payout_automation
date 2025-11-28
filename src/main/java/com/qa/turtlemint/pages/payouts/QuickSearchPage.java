package com.qa.turtlemint.pages.payouts;

import com.qa.turtlemint.base.TestBase;
import com.qa.turtlemint.commands.WebCommands;
import com.qa.turtlemint.pages.CSV_Validatator.CsvUtils;
import com.qa.turtlemint.util.LogUtils;
import com.qa.turtlemint.util.TestUtil;
import junit.framework.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FilenameFilter;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class QuickSearchPage extends TestBase {

    @FindBy(xpath = "//div[text()='Quick Search']")
    WebElement quickSearchPageTitle;

    @FindBy(xpath = "//input[@placeholder='Enter Partner ID']")
    WebElement partnerIdTxtbox;

    @FindBy(xpath = "//input[@placeholder='Enter MIS ID']")
    public WebElement misIdTxtbox;

    @FindBy(xpath = "//b[text()='From Payment Cycle:']//parent::div/div")
    WebElement frmPymntCycleDrpdwn;

    @FindBy(xpath = "//b[text()='To Payment Cycle:']//parent::div/div")
    WebElement toPymntCycleDrpdwn;

    @FindBy(xpath = "//span[text()='Search']")
    WebElement searchButton;

    @FindBy(xpath = "//span[text()='Reset']")
    WebElement resetButton;

    @FindBy(xpath = "//h3[text()='Search Result']")
    WebElement resultTitleText;

    @FindBy(xpath = "//div[text()='No items']")
    WebElement noResultText;

    CycleMovePage cmp = new CycleMovePage();
    DownloadPayoutsCyclePage dpc = new DownloadPayoutsCyclePage();

    public QuickSearchPage() {
        PageFactory.initElements(driver, this);
    }

    public void quickSearchClick() {
        TestUtil.click(dpc.ledgerBtn, "Payout Ledger Button Clicked");
        TestUtil.click(cmp.quickSearchSectnBtn, "Quick search Module Clicked");
    }

    public void searchByValid_Partner_ID(String partnerID) {
        LogUtils.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~Verifying Valid Partner ID Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        quickSearchPageAssertion();
        TestUtil.sendKeys(partnerIdTxtbox, partnerID, partnerID +" :Valid Partner ID Entered");
        TestUtil.click(searchButton, "Search Button Clicked");
        Assert.assertEquals("Search Result Not Displayed", "Search Result", resultTitleText.getText());
        TestUtil.click(cmp.downloadBtn, "Download button clicked");
        cmp.commentEnter();
        TestUtil.click(cmp.resultDownloadBtn, "Download button clicked to download result file");
        WebCommands.staticSleep(5000);
        validateQuickSearchResult("6290f07ed35ae3058a14b495", "");
    }

    public void searchByValid_MIS_ID(String misID){
        LogUtils.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~Verifying Valid MIS ID Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        TestUtil.sendKeys(misIdTxtbox, misID, "Valid MIS ID Entered");
        TestUtil.click(searchButton, "Search Button Clicked");
        Assert.assertEquals("Search Result Displayed", "Search Result", resultTitleText.getText());
        TestUtil.click(cmp.downloadBtn, "Download button clicked");
        cmp.commentEnter();
        TestUtil.click(cmp.resultDownloadBtn, "Download button clicked to download result file");
        WebCommands.staticSleep(1000);
        validateQuickSearchResult("", "MIS_AHSBF7UN56P");
    }

    public void searchByInvalid_Partner_ID(String partnerID){
        LogUtils.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~Verifying Invalid Partner ID Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        TestUtil.sendKeys(partnerIdTxtbox, partnerID, partnerID +" :Invalid Partner ID Entered");
        TestUtil.click(searchButton, "Search Button Clicked");
        Assert.assertEquals("Case Failed Result is found!","No items",noResultText.getText());
        LogUtils.info("Result Not Search Cause of INVALID PARTNER ID");
        TestUtil.click(resetButton,"Reset button clicked");
        LogUtils.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~Validated Invalid Partner ID Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void searchByInvalid_MIS_ID(String misID){
        LogUtils.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~Verifying Invalid MIS ID Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        TestUtil.sendKeys(misIdTxtbox, misID, misID+" :Invalid MIS ID Entered");
        TestUtil.click(searchButton, "Search Button Clicked");
        Assert.assertEquals("Case Failed Result is found!","No items",noResultText.getText());
        LogUtils.info("Result Not Search Cause of INVALID MIS ID");
        TestUtil.click(resetButton,"Reset button clicked");
        LogUtils.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~Validated Invalid MIS ID Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void searchByValid_Partner_MIS_ID(String partnerID,String misID){
        LogUtils.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~Verifying Valid PartnerID & MIS_ID Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        TestUtil.sendKeys(partnerIdTxtbox, partnerID, partnerID +" :Valid Partner ID Entered");
        TestUtil.sendKeys(misIdTxtbox, misID, misID + " :Valid MIS ID Entered");
        TestUtil.click(searchButton, "Search Button Clicked");
        Assert.assertEquals("Search Result Displayed", "Search Result", resultTitleText.getText());
        TestUtil.click(cmp.downloadBtn, "Download button clicked");
        cmp.commentEnter();
        TestUtil.click(cmp.resultDownloadBtn, "Download button clicked to download result file");
        WebCommands.staticSleep(1000);
        validateQuickSearchResult("63b54bb9ee10470001250bb6", "MIS_AHSBF7UN56P");
    }

    public void searchByInvalid_PartnerID_MIS_ID(String partnerID, String misID){
        LogUtils.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~Verifying Invalid PartnerID & MIS_ID Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        TestUtil.sendKeys(partnerIdTxtbox, partnerID, partnerID +" :Invalid Partner ID Entered");
        TestUtil.sendKeys(misIdTxtbox, misID, misID + " :Invalid MIS ID Entered");
        TestUtil.click(searchButton, "Search Button Clicked");
        Assert.assertEquals("Case Failed Result is found!","No items",noResultText.getText());
        TestUtil.click(resetButton,"Reset button clicked");
        LogUtils.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~Validated Invalid PartnerID & MIS_ID Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void searchByValid_PartnerID_Invalid_MIS_ID(String partnerID, String misID){
        LogUtils.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~Verifying Valid PartnerID & Invalid MIS_ID Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        TestUtil.sendKeys(partnerIdTxtbox, partnerID, partnerID +" :Valid Partner ID Entered");
        TestUtil.sendKeys(misIdTxtbox, misID, misID +" :Invalid MIS ID Entered");
        TestUtil.click(searchButton, "Search Button Clicked");
        Assert.assertEquals("Case Failed Result is found!","No items",noResultText.getText());
        TestUtil.click(resetButton,"Reset button clicked");
        LogUtils.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~Validated Valid PartnerID & Invalid MIS_ID Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void searchByInvalid_PartnerID_Valid_MIS_ID(String partnerID, String misID){
        LogUtils.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~Verifying Invalid PartnerID & Valid MIS_ID Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        TestUtil.sendKeys(partnerIdTxtbox, partnerID, partnerID +" :Invalid Partner ID Entered");
        TestUtil.sendKeys(misIdTxtbox, misID, misID +" :Valid MIS ID Entered");
        TestUtil.click(searchButton, "Search Button Clicked");
        Assert.assertEquals("Case Failed Result is found!","No items",noResultText.getText());
        TestUtil.click(resetButton,"Reset button clicked");
        LogUtils.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~Validated Invalid PartnerID & Valid MIS_ID Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void searchByValid_From_To_Cycles(String partnerID, String misID, String fromPaymentCycle, String toPaymentCycle) throws InterruptedException {
        LogUtils.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~Verifying Valid PartnerID & Valid MIS_ID By Cycle Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        TestUtil.sendKeys(partnerIdTxtbox, partnerID, partnerID +" :Invalid Partner ID Entered");
        TestUtil.sendKeys(misIdTxtbox, misID, misID +" :Valid MIS ID Entered");
        selectFromPaymentCycle(fromPaymentCycle);
        LogUtils.info(fromPaymentCycle + " :From Payment Cycle Selected");
        selectToPaymentCycle(toPaymentCycle);
        LogUtils.info(toPaymentCycle + " :To Payment Cycle Selected");
        TestUtil.click(searchButton, "Search Button Clicked");
        Assert.assertEquals("Search Result Displayed", "Search Result", resultTitleText.getText());
        TestUtil.click(cmp.downloadBtn, "Download button clicked");
        cmp.commentEnter();
        TestUtil.click(cmp.resultDownloadBtn, "Download button clicked to download result file");
        WebCommands.staticSleep(1000);
        validateQuickSearchResult("63b54bb9ee10470001250bb6", "MIS_AHSBF7UN56P");
        LogUtils.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~Validated Valid PartnerID & Valid MIS_ID By Cycle Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }

    public void searchByValid_From_To_InvalidCycleRange(String partnerID, String misID, String fromPaymentCycle, String toPaymentCycle) throws InterruptedException {
        LogUtils.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~Verifying Valid PartnerID & Valid MIS_ID By Cycle Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        TestUtil.sendKeys(partnerIdTxtbox, partnerID, partnerID +" :Invalid Partner ID Entered");
        TestUtil.sendKeys(misIdTxtbox, misID, misID +" :Valid MIS ID Entered");
        selectFromPaymentCycle(fromPaymentCycle);
        LogUtils.info(fromPaymentCycle + " :From Payment Cycle Selected");
        selectToPaymentCycle(toPaymentCycle);
        LogUtils.info(toPaymentCycle + " :To Payment Cycle Selected");
        selectFromPaymentCycle("Dec 2025 C2");
        LogUtils.info("Dec 2025 C2 :From Payment Cycle Selected");
        TestUtil.click(searchButton, "Search Button Clicked");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Invalid fromPaymentCycle or toPaymentCycle']"))
        );
        Assert.assertEquals(errorMsg.getText(), "Invalid fromPaymentCycle or toPaymentCycle");
        TestUtil.click(resetButton,"Reset button clicked");
        LogUtils.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~Validated Valid PartnerID & Valid MIS_ID By Cycle Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }

    public void searchByValid_From_To_Cycles_But_DataNotPresent(String partnerID, String misID, String fromPaymentCycle, String toPaymentCycle) throws InterruptedException {
        LogUtils.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~Verifying Valid PartnerID & Valid MIS_ID By Cycle Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        TestUtil.sendKeys(partnerIdTxtbox, partnerID, partnerID +" :Invalid Partner ID Entered");
        TestUtil.sendKeys(misIdTxtbox, misID, misID +" :Valid MIS ID Entered");
        selectFromPaymentCycle(fromPaymentCycle);
        LogUtils.info(fromPaymentCycle + " :From Payment Cycle Selected");
        selectToPaymentCycle(toPaymentCycle);
        LogUtils.info(toPaymentCycle + " :To Payment Cycle Selected");
        TestUtil.click(searchButton, "Search Button Clicked");
        Assert.assertEquals("Case Failed Result is found!","No items",noResultText.getText());
        TestUtil.click(resetButton,"Reset button clicked");
        LogUtils.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~Validated Valid PartnerID & Valid MIS_ID By Cycle Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }

    public void selectFromPaymentCycle(String fromPaymentCycle) throws InterruptedException {
        TestUtil.click(frmPymntCycleDrpdwn,"From payment Cycle Dropdown Click");
        WebElement scrollBox = driver.findElement(By.xpath("(//div[@class='rc-virtual-list-holder'])[1]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String fromCycle = fromPaymentCycle;   // the value you want to select
        boolean found = false;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        for (int i = 0; i < 30 && !found; i++) {
            List<WebElement> options = scrollBox.findElements(
                    By.xpath(".//div[text()='" + fromCycle + "']"));
            if (!options.isEmpty()) {
                WebElement cycle = options.get(0);
                cycle.click();
                found = true;
                System.out.println("Cycle found and clicked");
                break;
            }
            js.executeScript(
                    "arguments[0].scrollTop = arguments[0].scrollTop + arguments[0].offsetHeight;",scrollBox);
            Thread.sleep(150);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        if (!found) {
            throw new RuntimeException("Cycle '" + fromCycle + "' not found in dropdown after scrolling");
        }
    }

    public void selectToPaymentCycle(String toPaymentCycle) throws InterruptedException {
        TestUtil.click(toPymntCycleDrpdwn,"To payment Cycle Dropdown Click");
        WebElement scrollBox = driver.findElement(By.xpath("(//div[@class='rc-virtual-list-holder'])[2]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String toCycle = toPaymentCycle;   // the value you want to select
        boolean found = false;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        for (int i = 0; i < 30 && !found; i++) {
            List<WebElement> options = scrollBox.findElements(
                    By.xpath(".//div[text()='" + toCycle + "']"));
            if (!options.isEmpty()) {
                WebElement cycle = options.get(0);
                cycle.click();
                found = true;
                System.out.println("Cycle found and clicked");
                break;
            }
            js.executeScript("arguments[0].scrollTop = arguments[0].scrollTop + arguments[0].offsetHeight;",scrollBox);
            Thread.sleep(150);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        if (!found) {
            throw new RuntimeException("Cycle '" + toCycle + "' not found in dropdown after scrolling");
        }
    }

    public void quickSearchPageAssertion(){
        Assert.assertEquals(quickSearchPageTitle.getText(), "Quick Search");
        Assert.assertTrue("Partner ID text box is not enabled", partnerIdTxtbox.isEnabled());
        Assert.assertTrue("MIS ID text box is not enabled", misIdTxtbox.isEnabled());
        Assert.assertFalse("From Payment Cycle Dropdown is enabled",frmPymntCycleDrpdwn.isEnabled());
        Assert.assertFalse("To Payment Cycle Dropdown is enabled",toPymntCycleDrpdwn.isEnabled());
        Assert.assertFalse("Search button is enabled",isButtonDisabled(driver,searchButton));
        Assert.assertFalse("Reset button is enabled",isButtonDisabled(driver,resetButton));
    }

    public static boolean isButtonDisabled(WebDriver driver, WebElement button) {
        if (!button.isEnabled()) return true;
        return false;
    }

    public void validateQuickSearchResult(String partnerID, String misID){
        try {
            String downloadDirectory = "//Users//rahulpatil//Downloads";
            File[] files = new File(downloadDirectory).listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".csv");
                }
            });
            if (files != null && files.length > 0) {
                File mostRecentFile = null;
                long lastModified = 0;
                for (File file : files) {
                    if (file.lastModified() > lastModified) {
                        lastModified = file.lastModified();
                        mostRecentFile = file;
                    }
                }
                if (mostRecentFile != null) {
                    WebCommands.staticSleep(1000);
                    CsvUtils csvAssert = new CsvUtils();
                    List<String[]> data = csvAssert.readCsv(mostRecentFile);

                    csvAssert.assertRow(data, 0, Arrays.asList("policyCommissionId", "policyDetailsId", "policyPaymentScheduleId", "commissionSource", "deviationType", "Payout Policy Type", "version", "Policy No.", "Master Policy No.", "Application No.",
                            "MIS / Data entry owner", "PI CreatedBy", "Booking/Issued Date", "Booking/Issued Month", "Product category", "Product subcategory", "Vehicle type", "Vehicle subtype", "CarrierType", "Intermediary Name",
                            "DP No", "DP Login Id", "Relationship Manager", "City Head", "Circle Head", "Business Head", "Super Franchisees", "Super Franchisees ID", "Super Franchisees DP No", "DP Level", "Partner Parent Subtype",
                            "Parent DP Name", "Parent DP Id", "Parent DP No", "Parent DP Level", "DP Branch Location", "Business Type", "Customer Title", "Customer First Name", "Customer Last Name", "Registration no.", "RTO code",
                            "Make", "Model", "Variant", "Cubic Capacity", "Fuel Type", "Mfg. Year", "GVW/Tonnage", "Seating capacity", "ZeroDepreciation", "Discount Percentage", "Case Status", "Case Sub Status", "Insurer", "Plan type",
                            "Plan name", "Plan Variant", "product name", "Payment Frequency", "Upcoming Payment Due Date", "Recent Payment Paid Date", "Installment UniqueId", "Policy term", "Policy premium term", "Risk Start Date",
                            "Risk End Date", "odRiskStartDate", "odRiskEndDate", "tpRiskStartDate", "tpRiskEndDate", "multiyear", "IDV/SA", "TM Plan ID", "Cover Type", "Option Name", "Age", "Registration City", "Registration State",
                            "Renewal Year", "creation source", "Creation Date", "Comments", "Basic OD Premium", "Net OD Premium", "Basic TP Premium", "TP Premium", "Net Premium", "Annualised Net Premium", "CPA Premium", "NCB",
                            "Record Status", "Insurer Record Status", "Channel Type", "manualQCStatus", "irdaRuleId", "insurerRuleId", "conflicts", "Payout AutoQC", "Payout QC Done By", "Payout QC", "First Payout QC Date",
                            "Final Payout QC Date", "Post QC Review Status", "Post QC Review Done By", "Post QC Review Date", "Payout Type", "Payouts %", "Payouts absolute", "Payouts Deviation %", "Payouts Deviation absolute",
                            "Final Payout Total", "Partner cohort", "Partner club", "Partner slab", "QuickPay Eligible", "Skip Differential deductions", "Deduction% for differential", "Deduction% for QP", "Deduction% for campaign",
                            "Deduction absolute for differential", "Deduction absolute for QP", "Deduction absolute for campaign", "Initial Effective Percentages", "Initial Effective Payout absolute", "Effective Percentages",
                            "Effective Payout absolute", "Remark", "Ledger_Comment", "System_Comment", "Ledger_Id", "Payment_Schedule_Year", "Payment Cycle", "Partner_Id", "Payable_Partner_Id", "Ledger_Entity_Type",
                            "Partner_Level_Activity_Type", "Payout Disbursal Type", "Hold And Reject", "Ledger_Updated_At", "Ledger_Created_At", "Description", "Customer_Name", "Asset_No", "Ref_No", "First Finalized in Payment Cycle",
                            "First Payment Attempted in Payment Cycle", "First Payment Approved in Payment Cycle", "Ledger_Remarks", "Points", "Deductions", "NetPoints", "Ledger_Status", "MIS_Creation_Date", "PC_CreatedAt",
                            "MIS_Issuance_Date", "Payment_Finalize_Status", "TDS_IRDA_C", "TDS_IRDA_R", "TDS_FBS", "TDS_TMF", "Net_Amount_IRDA_C", "Net_Amount_IRDA_R", "Net_Amount_FBS", "Net_Amount_TMF", "UTR_IRDA_C", "UTR_IRDA_R",
                            "UTR_FBS", "UTR_TMF", "Payment_Date_IRDA_C", "Payment_Date_IRDA_R", "Payment_Date_FBS", "Payment_Date_TMF", "IRDA_C_Payment_Status", "IRDA_R_Payment_Status", "FBS_Payment_Status", "TMF_Payment_Status",
                            "Payment_Status", "Payment_Remarks"));

                    if (partnerID.equalsIgnoreCase("63b54bb9ee10470001250bb6")&&misID.equalsIgnoreCase("MIS_AHSBF7UN56P")) {
                        LogUtils.info("Validating CSV Result For Valid PartnerID & MIS_ID");
                        csvAssert.assertRow(data, 1, Arrays.asList("69258d93e855424104e09fa0", "MIS_AHSBF7UN56P" ,"", "SYSTEM_GENERATED", "", "", "2", "'dfhngngnjjdkks", "", "", "rahul.patil1@turtlemint.com", "", "25-Nov-2025", "Nov",
                                "Two Wheeler", "", "TW", "Bike", "", "rahul patil", "DP - 1586333", "63b54bb9ee10470001250bb6", "","city head", "circle head mix1", "", "","", "", "partner_level_1", "", "", "", "", "", "Mira Road","NEW", "mr",
                                "Sanity", "Test", "MH-01-WW-2164", "MH01", "Hero Honda", "Splendor Pro", "","100", "Petrol", "2023", "", "2", "false", "", "Issued", "", "Bajaj Allianz", "", "", "", "Comprehensive", "", "", "", "","1", "",
                                "25-Nov-2025", "25-Nov-2026", "25-Nov-2025", "25-Nov-2026", "25-Nov-2025", "25-Nov-2026", "false", "10000", "", "", "", "", "Mumbai", "Maharashtra", "", "opsmanual", "25-Nov-2025", "", "1003", "2000", "3000", "4000", "6000",
                                "", "0.0", "25", "PENDING", "Present", "Partner", "DONE", "0", "128061", "", "true", "SYSTEM", "DONE", "25-Nov-2025", "25-Nov-2025", "", "", "", "Insurer", "1.95xN", "", "", "", "1170.0", "fs_motor_bharat", "Star",
                                "slab 3", "true", "false", "", "0.2xN", "", "", "", "", "1.75xN", "", "1.75xN", "", "", "", "", "69258d93e855424104e09fa1", "", "20251125", "63b54bb9ee10470001250bb6", "63b54bb9ee10470001250bb6", "POLICY_COMMISSION",
                                "", "Regular", "", "25-Nov-2025", "25-Nov-2025", "COMP_BJ", "Sanity Test", "MH/2164", "jdkks", "", "", "", "", "1050.0", "0.0", "1050.0", "PENDING", "25-Nov-2025", "25-Nov-2025", "25-Nov-2025", "PENDING", "0.0", "0.0", "0.0",
                                "0.0", "0.0", "0.0", "0.0", "0.0", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
                        LogUtils.info("Validated CSV Result For Valid PartnerID & MIS_ID");
                        LogUtils.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~Validated Valid PartnerID & MIS_ID Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                    else if (partnerID.equalsIgnoreCase("6290f07ed35ae3058a14b495")) {
                        LogUtils.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~Validated Valid Partner ID Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                    else if (misID.equalsIgnoreCase("MIS_AHSBF7UN56P")) {
                        LogUtils.info("Validating CSV Result For Valid MIS ID");
                        csvAssert.assertRow(data, 1, Arrays.asList("69258d93e855424104e09fa0", "MIS_AHSBF7UN56P" ,"", "SYSTEM_GENERATED", "", "", "2", "'dfhngngnjjdkks", "", "", "rahul.patil1@turtlemint.com", "", "25-Nov-2025", "Nov",
                                  "Two Wheeler", "", "TW", "Bike", "", "rahul patil", "DP - 1586333", "63b54bb9ee10470001250bb6", "","city head", "circle head mix1", "", "","", "", "partner_level_1", "", "", "", "", "", "Mira Road","NEW", "mr",
                                  "Sanity", "Test", "MH-01-WW-2164", "MH01", "Hero Honda", "Splendor Pro", "","100", "Petrol", "2023", "", "2", "false", "", "Issued", "", "Bajaj Allianz", "", "", "", "Comprehensive", "", "", "", "","1", "",
                                  "25-Nov-2025", "25-Nov-2026", "25-Nov-2025", "25-Nov-2026", "25-Nov-2025", "25-Nov-2026", "false", "10000", "", "", "", "", "Mumbai", "Maharashtra", "", "opsmanual", "25-Nov-2025", "", "1003", "2000", "3000", "4000", "6000",
                                  "", "0.0", "25", "PENDING", "Present", "Partner", "DONE", "0", "128061", "", "true", "SYSTEM", "DONE", "25-Nov-2025", "25-Nov-2025", "", "", "", "Insurer", "1.95xN", "", "", "", "1170.0", "fs_motor_bharat", "Star",
                                  "slab 3", "true", "false", "", "0.2xN", "", "", "", "", "1.75xN", "", "1.75xN", "", "", "", "", "69258d93e855424104e09fa1", "", "20251125", "63b54bb9ee10470001250bb6", "63b54bb9ee10470001250bb6", "POLICY_COMMISSION",
                                  "", "Regular", "", "25-Nov-2025", "25-Nov-2025", "COMP_BJ", "Sanity Test", "MH/2164", "jdkks", "", "", "", "", "1050.0", "0.0", "1050.0", "PENDING", "25-Nov-2025", "25-Nov-2025", "25-Nov-2025", "PENDING", "0.0", "0.0", "0.0",
                                  "0.0", "0.0", "0.0", "0.0", "0.0", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
                        LogUtils.info("Validated CSV Result For Valid MIS ID");
                        LogUtils.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~Validated Valid MIS ID Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                    TestUtil.click(resetButton, "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("An error occurred during the CSV file validation.", e);
        }
    }

}
