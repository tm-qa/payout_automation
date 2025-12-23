package com.qa.turtlemint.pages.payouts;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.qa.turtlemint.base.TestBase;
import com.qa.turtlemint.commands.WebCommands;
import com.qa.turtlemint.pages.CSV_Validatator.CsvUtils;
//import com.qa.turtlemint.pages.DB_Assertions.DBAssertion;
import com.qa.turtlemint.pages.DB_Assertions.DBAssertion;
import com.qa.turtlemint.pages.Ninja.ninja;
import com.qa.turtlemint.util.LogUtils;
import com.qa.turtlemint.util.TestUtil;
import com.qa.turtlemint.util.Utils;
import junit.framework.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class UploadPayoutsPage extends TestBase {

// Manual Uploads

    @FindBy(xpath = "//div[text()='Upload Payouts']")
    WebElement uploadPayoutBtn;

    @FindBy(xpath = "//span[text()='Download File Template']")
    WebElement templateDownloadBtn;

    @FindBy(xpath = "//h3[text()='Upload Payouts']")
    WebElement uploadPayoutTitle;

    @FindBy(xpath = "(//input[@type='search'])[1]//parent::span")
    WebElement uploadPayoutDrpdwn;

    @FindBy(xpath = "//div[text()='Manual Upload']")
    WebElement manualUploadBtn;

    @FindBy(xpath = "//b[text()='Payment Cycle']//parent::div//following-sibling::div//child::div[@class='ant-select-selector']")
    WebElement manualUploadPymntCycleDrpdwn;

    @FindBy(xpath = "(//span[text()='Upload'])[2]")
    WebElement finalUploadBtn;

    @FindBy(xpath = "(//div[@class='ant-table-body']//tbody//tr//following-sibling::tr//td)[3]")
    WebElement uploadeByUser;

    @FindBy(xpath = "(//div[@class='ant-table-body']//tbody//tr//following-sibling::tr//td)[4]")
    WebElement paymentCycle;

    @FindBy(xpath = "(//div[@class='ant-table-body']//tbody//tr//following-sibling::tr//td)[5]")
    WebElement enteredComment;

    @FindBy(xpath = "(//div[@class='ant-table-body']//tbody//tr//following-sibling::tr//td)[6]")
    WebElement uploadedType;

    @FindBy(xpath = "(//div[@class='ant-table-body']//tbody//tr//following-sibling::tr//td)[7]")
    WebElement successCount;

    @FindBy(xpath = "(//div[@class='ant-table-body']//tbody//tr//following-sibling::tr//td)[8]")
    WebElement failureCount;

    @FindBy(xpath = "(//span[text()='Output_File'])[1]")
    WebElement outputFileBtn;

// Manual Correction

    @FindBy(xpath = "//div[text()='Manual Correction']")
    WebElement manualCorrectionBtn;

// Deviations

    @FindBy(xpath = "//div[text()='Deviations']")
    WebElement deviationBtn;

    @FindBy(xpath = "(//input[@type='search'])[3]//parent::span")
    WebElement deviationTypeDrpdwn;

    @FindBy(xpath = "(//div[text()='INCORRECT_RULES'])[2]")
    WebElement IncretDeviationdeviation;

    @FindBy(xpath = "(//div[text()='SPECIAL_REQUEST'])[2]")
    WebElement SplReqstDeviationdeviation;

    @FindBy(xpath = "//div[@title='NOMINAL_DEVIATION']")
    WebElement NominalDeviationdeviation;

    @FindBy(xpath = "//label[text()='MIS ID']//..//..//input")
    WebElement misID;

    @FindBy(xpath = "//a[@data-auto='payouts-module']")
    WebElement payouts;

    @FindBy(xpath = "(//td[@class='ant-table-cell'])[4]")
    WebElement policyDetailID;

    // Split Deviations
    @FindBy(xpath = "//div[@title='Split Deviations']")
    WebElement splitDeviationBtn;

    // Adjustments
    @FindBy(xpath = "//div[@title='Adjustments']")
    WebElement adjustmentsBtn;

    // Payout QC
    @FindBy(xpath = "//div[@title='Payout QC']")
    WebElement payoutQCBtn;

    // Post QC Review
    @FindBy(xpath = "//div[@title='Post QC Review']")
    WebElement postQC_ReviewBtn;


    String policyCommissionId;
    String policyDetailsId;
    String ledger_Id;
    String PolicyPaymentScheduleId;
    String commissionSource;
    String DeviationType;
    String Payout_Policy_Type;
    String version;
    String Policy_No;
    String Master_Policy_No;
    String Application_No;
    String MIS_Data_entry_owner;
    String PI_CreatedBy;
    String Booking_Issued_Date;
    String Booking_Issued_Month;
    String Product_category;
    String Product_subcategory;
    String Vehicle_type;
    String Vehicle_subtype;
    String Carrier_Type;
    String Intermediary_Name;
    String DP_No;
    String DP_Login_Id;
    String Relationship_Manager;
    String City_Head;
    String Circle_Head;
    String Business_Head;
    String Super_Franchisees;
    String Super_Franchisees_ID;
    String Super_Franchisees_DP_No;
    String DP_Level;
    String Partner_Parent_Subtype;
    String Parent_DP_Name;
    String Parent_DP_Id;
    String Parent_DP_No;
    String Parent_DP_Level;
    String DP_Branch_Location;
    String Business_Type;
    String Customer_Title;
    String Customer_First_Name;
    String Customer_Last_Name;
    String Registration_no;
    String RTO_code;
    String make;
    String model;
    String variant;
    String Cubic_Capacity;
    String Fuel_Type;
    String Mfg_Year;
    String GVW_Tonnage;
    String Seating_capacity;
    String zeroDepreciation;
    String Discount_Percentage;
    String Case_Status;
    String Case_Sub_Status;
    String insurer;
    String Plan_type;
    String Plan_name;
    String Plan_Variant;
    String product_name;
    String Payment_Frequency;
    String Upcoming_Payment_Due_Date;
    String Recent_Payment_Paid_Date;
    String Installment_UniqueId;
    String Policy_term;
    String Policy_premium_term;
    String Risk_Start_Date;
    String Risk_End_Date;
    String ODRiskStartDate;
    String ODRiskEndDate;
    String TPRiskStartDate;
    String TPRiskEndDate;
    String Multiyear;
    String IDV_SA;
    String TM_Plan_ID;
    String Cover_Type;
    String Option_Name;
    String age;
    String Registration_City;
    String Registration_State;
    String Renewal_Year;
    String creation_source;
    String Creation_Date;
    String comments;
    String Basic_OD_Premium;
    String Net_OD_Premium;
    String Basic_TP_Premium;
    String TP_Premium;
    String Net_Premium;
    String Annualised_Net_Premium;
    String CPA_Premium;
    String ncb;
    String Record_Status;
    String Insurer_Record_Status;
    String Channel_Type;
    String ManualQCStatus;
    String IrdaRuleId;
    String InsurerRuleId;
    String Conflicts;
    String Payout_AutoQC;
    String Payout_QC_Done_By;
    String Payout_QC;
    String First_Payout_QC_Date;
    String Final_Payout_QC_Date;
    String Post_QC_Review_Status;
    String Post_QC_Review_Done_By;
    String Post_QC_Review_Date;
    String Payout_Type;
    String Payouts_Percentage;
    String Payouts_absolute;
    String Payouts_Deviation_Percentage;
    String Payouts_Deviation_absolute;
    String Final_Payout_Total;
    String Partner_cohort;
    String Partner_club;
    String Partner_slab;
    String QuickPay_Eligible;
    String Skip_Differential_deductions;
    String Deduction_Per_For_differential;
    String Deduction_Per_For_QP;
    String Deduction_Per_for_campaign;
    String Deduction_absolute_for_differential;
    String Deduction_absolute_for_QP;
    String Deduction_absolute_for_campaign;
    String Initial_Effective_Percentages;
    String Initial_Effective_Payout_absolute;
    String Effective_Percentages;
    String Effective_Payout_absolute;
    String remark;
//    String Post_QC_Review_Status;

    private String mID;
    private String pcid;
    public String CommissionId;
    String rawDate;
    Actions action = new Actions(driver);
    JavascriptExecutor js = (JavascriptExecutor) driver;
    DownloadPayoutsCyclePage dpc = new DownloadPayoutsCyclePage();
    CycleMovePage cmp = new CycleMovePage();
    QuickSearchPage qsp = new QuickSearchPage();
    CsvUtils csvAssert = new CsvUtils();

    public UploadPayoutsPage() {
        PageFactory.initElements(driver, this);
    }

    public void manualUpload(String fileName, String paymentCycle) throws Exception {
        TestUtil.click(uploadPayoutBtn, "Upload Payouts Button Clicked");
        Assert.assertEquals(uploadPayoutTitle.getText(), "Upload Payouts");
        TestUtil.click(uploadPayoutDrpdwn, "Upload Payouts dropdown Clicked");
        WebCommands.staticSleep(1000);
        TestUtil.getScreenShot();
        TestUtil.click(manualUploadBtn, "Manual Upload Clicked");
        TestUtil.click(templateDownloadBtn, "Manual Upload Template download");
        WebCommands.staticSleep(1000);
        validateDownloadTemplateFile(fileName);
        TestUtil.click(cmp.uploadBtn, "Upload Button Clicked");
        cmp.uploadFile(fileName);
        WebCommands.staticSleep(1000);
        cmp.commentEnter();
        TestUtil.click(manualUploadPymntCycleDrpdwn, "Payment Cycle dropdown Clicked");
        selectPaymentCycle(paymentCycle);
        WebCommands.staticSleep(2000);
        TestUtil.click(finalUploadBtn, "Clicked on upload button");
        UploadPayoutsHistoryAssertion(fileName);
        TestUtil.click(outputFileBtn, "Download output file");
        WebCommands.staticSleep(1000);
        validateOutputFile(fileName, "", "");
    }

    public void manualCorrection(String fileName) throws Exception {
        TestUtil.click(uploadPayoutBtn, "Upload Payouts Button Clicked");
        Assert.assertEquals(uploadPayoutTitle.getText(), "Upload Payouts");
        TestUtil.click(uploadPayoutDrpdwn, "Upload Payouts dropdown Clicked");
        WebCommands.staticSleep(1000);
        TestUtil.getScreenShot();
        TestUtil.click(manualCorrectionBtn, "Manual Correction Clicked");
        TestUtil.click(templateDownloadBtn, "Manual Correction Template download");
        WebCommands.staticSleep(1000);
        validateDownloadTemplateFile(fileName);
        TestUtil.click(cmp.uploadBtn, "Upload Button Clicked");
        cmp.uploadFile(fileName);
        WebCommands.staticSleep(1000);
        cmp.commentEnter();
        TestUtil.click(finalUploadBtn, "Clicked on upload button");
        WebCommands.staticSleep(1000);
        UploadPayoutsHistoryAssertion(fileName);
        TestUtil.click(outputFileBtn, "Download output file");
        WebCommands.staticSleep(1000);
        validateOutputFile(fileName, "", "");
    }

    public void deviations(String deviationType, String pcid) throws Exception {
        TestUtil.click(uploadPayoutBtn, "Upload Payouts Button Clicked");
        Assert.assertEquals(uploadPayoutTitle.getText(), "Upload Payouts");
        TestUtil.click(uploadPayoutDrpdwn, "Upload Payouts dropdown Clicked");
        WebCommands.staticSleep(1000);
        TestUtil.getScreenShot();
        if (deviationType.equalsIgnoreCase("INCORRECT_RULES") || deviationType.equalsIgnoreCase("SPECIAL_REQUEST") || deviationType.equalsIgnoreCase("NOMINAL_DEVIATION") || deviationType.equalsIgnoreCase("INCORRECT_RULESS")) {
            TestUtil.click(deviationBtn, "Deviations Clicked");
        } else if (deviationType.equalsIgnoreCase("SPLIT_DEVIATIONS") || (deviationType.equalsIgnoreCase("NonSplitPartner_SPLIT_DEVIATIONS"))) {
            TestUtil.click(splitDeviationBtn, "Split Deviations Clicked");
        }
        TestUtil.click(templateDownloadBtn, "Deviations Template download");
        WebCommands.staticSleep(1000);
        validateDownloadTemplateFile(deviationType);
        WebCommands.staticSleep(1000);
        if (deviationType.equalsIgnoreCase("INCORRECT_RULES")) {
            writeDeviationUploadCSV(pcid, deviationType);
        } else if (deviationType.equalsIgnoreCase("SPECIAL_REQUEST")) {
            writeDeviationUploadCSV(pcid, deviationType);
        } else if (deviationType.equalsIgnoreCase("NOMINAL_DEVIATION")) {
            writeDeviationUploadCSV(pcid, deviationType);
        } else if (deviationType.equalsIgnoreCase("INCORRECT_RULESS")) {
            writeDeviationUploadCSV(pcid, deviationType);
        } else if (deviationType.equalsIgnoreCase("SPLIT_DEVIATIONS") || (deviationType.equalsIgnoreCase("NonSplitPartner_SPLIT_DEVIATIONS"))) {
            writeDeviationUploadCSV(pcid, deviationType);
        }
        cmp.commentEnter();
        TestUtil.click(finalUploadBtn, "Clicked on upload button");
        WebCommands.staticSleep(1000);
        UploadPayoutsHistoryAssertion(deviationType);
        TestUtil.click(outputFileBtn, "Download output file");
        WebCommands.staticSleep(1000);
        if (deviationType.equalsIgnoreCase("INCORRECT_RULES") || deviationType.equalsIgnoreCase("SPECIAL_REQUEST") || deviationType.equalsIgnoreCase("NOMINAL_DEVIATION") || deviationType.equalsIgnoreCase("INCORRECT_RULESS")) {
            validateOutputFile("Deviations", deviationType, pcid);
        } else if (deviationType.equalsIgnoreCase("SPLIT_DEVIATIONS")) {
            validateOutputFile("SPLIT_DEVIATIONS", deviationType, pcid);
        } else if (deviationType.equalsIgnoreCase("NonSplitPartner_SPLIT_DEVIATIONS")) {
            validateOutputFile("NonSplitPartner_SPLIT_DEVIATIONS", deviationType, pcid);
        }
    }

    public void uploadDeviation(String deviationType) throws Exception {
        String getPcid = getPcid();
        System.out.println("commision ID :" + getPcid);
        if (deviationType.equalsIgnoreCase("INCORRECT_RULES")) {
            System.out.println("INCORRECT_RULES BLOCK");
            deviations("INCORRECT_RULES", getPcid);
        } else if (deviationType.equalsIgnoreCase("SPECIAL_REQUEST")) {
            System.out.println("SPECIAL_REQUEST BLOCK");
            deviations("SPECIAL_REQUEST", getPcid);
        } else if (deviationType.equalsIgnoreCase("NOMINAL_DEVIATION")) {
            System.out.println("NOMINAL_DEVIATION BLOCK");
            deviations("NOMINAL_DEVIATION", getPcid);
        } else {
            System.out.println("else BLOCK executed");
            deviations("INCORRECT_RULESS", getPcid);
        }
    }

    public void uploadSplitDeviations(String deviationType) throws Exception {
        String getPcid = getPcid();
        System.out.println("commision ID :" + getPcid);
        if (deviationType.equalsIgnoreCase("SPLIT_DEVIATIONS")) {
            System.out.println("SPLIT_DEVIATIONS BLOCK");
            deviations("SPLIT_DEVIATIONS", getPcid);
        } else {
            System.out.println("NonSplitPartner SPLIT_DEVIATIONS BLOCK");
            deviations("NonSplitPartner_SPLIT_DEVIATIONS", getPcid);
        }
    }

    public void selectAdjustments() {
        TestUtil.click(dpc.ledgerBtn, "");
        TestUtil.click(uploadPayoutBtn, "Upload Payouts Button Clicked");
    }

    public void uploadAdjustment(String fileName, String paymentCycle) throws Exception {
        TestUtil.click(dpc.ledgerBtn, "");
        TestUtil.click(uploadPayoutBtn, "Upload Payouts Button Clicked");
        TestUtil.click(uploadPayoutDrpdwn, "Upload Payouts dropdown Clicked");
        WebCommands.staticSleep(1000);
        TestUtil.getScreenShot();
        TestUtil.click(adjustmentsBtn, "Adjustments Clicked");
        TestUtil.click(templateDownloadBtn, "Adjustments Template download");
        WebCommands.staticSleep(1000);
        validateDownloadTemplateFile(fileName);
        WebCommands.staticSleep(1000);
        TestUtil.click(cmp.uploadBtn, "Upload button clicked");
        cmp.uploadFile(fileName);
        cmp.commentEnter();
        TestUtil.click(manualUploadPymntCycleDrpdwn, "Payment Cycle dropdown Clicked");
        selectPaymentCycle(paymentCycle);
        TestUtil.click(finalUploadBtn, "Clicked on upload button");
        WebCommands.staticSleep(1000);
        UploadPayoutsHistoryAssertion(fileName);
        TestUtil.click(outputFileBtn, "Download output file");
        WebCommands.staticSleep(1000);
        validateOutputFile(fileName, "", "");
        driver.navigate().refresh();
    }

    public void uploadPayoutQC(String fileName) throws Exception {
        storeDataForPayoutQCFile();
        TestUtil.click(uploadPayoutBtn, "Upload Payouts Button Clicked");
        TestUtil.click(uploadPayoutDrpdwn, "Upload Payouts dropdown Clicked");
        WebCommands.staticSleep(1000);
        TestUtil.getScreenShot();
        TestUtil.click(payoutQCBtn, "Payout QC Clicked");
        TestUtil.click(templateDownloadBtn, "Payout QC Template download");
        WebCommands.staticSleep(1000);
        validateDownloadTemplateFile(fileName);
        writeUploadCSV_PayoutQC();
        WebCommands.staticSleep(1000);
        cmp.commentEnter();
        TestUtil.click(finalUploadBtn, "Clicked on upload button");
        WebCommands.staticSleep(1000);
        UploadPayoutsHistoryAssertion(fileName);
        TestUtil.click(outputFileBtn, "Download output file");
        WebCommands.staticSleep(3000);
        validateOutputFile(fileName, "", "");
    }

    public void uploadPostQC_Review(String fileName) throws Exception {
        Actions action = new Actions(driver);
        action.moveToElement(uploadPayoutDrpdwn).click().perform();
        WebCommands.staticSleep(1000);
        TestUtil.getScreenShot();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", postQC_ReviewBtn);
        TestUtil.click(postQC_ReviewBtn, "Payout QC Clicked");
        TestUtil.click(templateDownloadBtn, "Post QC Review Template download");
        WebCommands.staticSleep(1000);
        validateDownloadTemplateFile(fileName);
        writeUploadCSV_PostQC_Review();
        WebCommands.staticSleep(1000);
        cmp.commentEnter();
        TestUtil.click(finalUploadBtn, "Clicked on upload button");
        WebCommands.staticSleep(1000);
        UploadPayoutsHistoryAssertion(fileName);
        TestUtil.click(outputFileBtn, "Download output file");
        WebCommands.staticSleep(3000);
        validateOutputFile(fileName, "", "");
        action.sendKeys(Keys.PAGE_UP).perform();
    }

    public void validate_MIS_EntryAtPayouts() throws Exception {
        WebCommands.staticSleep(5000); //uncomment me
        js.executeScript("arguments[0].scrollIntoView(true);", misID); //uncomment me
        WebCommands.staticSleep(2000); //uncomment me
        String mID = misID.getAttribute("value"); //uncomment me

//        String mID = "MIS_AHT61K9KEME"; // new added

        this.mID = mID;
        System.out.println("MIS ID : " + mID);
        WebCommands.staticSleep(5000);
        driver.navigate().to("https://ninja.sanity.turtle-feature.com");
        TestUtil.click(payouts, "");
        WebCommands.staticSleep(2000);
        TestUtil.click(dpc.ledgerBtn, "");
        WebCommands.staticSleep(2000);
        TestUtil.click(cmp.quickSearchSectnBtn, "Clicked on Quick Search");
        qsp.misIdTxtbox.sendKeys(mID);
        WebCommands.staticSleep(2000);
        TestUtil.click(qsp.searchButton, "");
        WebCommands.staticSleep(3000);
        org.testng.Assert.assertEquals(policyDetailID.getText(), mID);
        TestUtil.click(cmp.downloadBtn, "Download button clicked");
        cmp.commentEnter();
        TestUtil.click(cmp.resultDownloadBtn, "Download button clicked to download result file");
        TestUtil.getFullPageScreenShot();
        WebCommands.staticSleep(3000);
        String pcid = getPolicyCommisionID();
        System.out.println("Policy Commission ID : " + pcid);
        this.pcid = pcid;
    }

    public String getPcid() {
        return pcid;
    }

    public String getmID() {
        return mID;
    }

    public void downloadDeviationQuickSearchResult() {
        String misID = getmID();
        QuickSearchPage qsp = new QuickSearchPage();
        TestUtil.click(cmp.quickSearchSectnBtn, "Quick Search Selected");
        TestUtil.sendKeys(qsp.misIdTxtbox, misID, "Valid MIS ID Entered");
        LogUtils.info("MIS ID used: " + misID);
        TestUtil.click(qsp.searchButton, "Search Button Clicked");
        TestUtil.click(cmp.downloadBtn, "Download button clicked");
        cmp.commentEnter();
        TestUtil.click(cmp.resultDownloadBtn, "Download button clicked to download result file");
        WebCommands.staticSleep(3000);
        TestUtil.getScreenShot();
    }

    public void downloadAdjustmentsQuickSearchResult() throws Exception {
        TestUtil.click(dpc.ledgerBtn, "");
        TestUtil.click(cmp.quickSearchSectnBtn, "Quick Search Selected");
        TestUtil.sendKeys(qsp.misIdTxtbox, "MIS_AHSK7OHON57", "MIS ID Entered");
        TestUtil.click(qsp.searchButton, "Search Button Clicked");
        TestUtil.click(cmp.downloadBtn, "Download button clicked");
        cmp.commentEnter();
        TestUtil.click(cmp.resultDownloadBtn, "Download button clicked to download result file");
        WebCommands.staticSleep(1000);
        TestUtil.getScreenShot();
    }

    public void storeDataForPayoutQCFile() throws Exception {
        csvAssert.storeCSVdata();
        ledger_Id = CsvUtils.TestDataStore.get("Ledger_Id");
        policyCommissionId = CsvUtils.TestDataStore.get("policyCommissionId");
        policyDetailsId = CsvUtils.TestDataStore.get("policyDetailsId");
        PolicyPaymentScheduleId = CsvUtils.TestDataStore.get("policyPaymentScheduleId");
        commissionSource = CsvUtils.TestDataStore.get("commissionSource");
        DeviationType = CsvUtils.TestDataStore.get("deviationType");
        Payout_Policy_Type = CsvUtils.TestDataStore.get("Payout Policy Type");
        version = CsvUtils.TestDataStore.get("version");
        Policy_No = CsvUtils.TestDataStore.get("Policy No.");
        Master_Policy_No = CsvUtils.TestDataStore.get("Master Policy No.");
        Application_No = CsvUtils.TestDataStore.get("Application No.");
        MIS_Data_entry_owner = CsvUtils.TestDataStore.get("MIS / Data entry owner");
        PI_CreatedBy = CsvUtils.TestDataStore.get("PI CreatedBy");
        rawDate = CsvUtils.TestDataStore.get("Booking/Issued Date");
        Booking_Issued_Date = CsvUtils.toDdMmmYyyy(rawDate);

        Booking_Issued_Month = CsvUtils.TestDataStore.get("Booking/Issued Month");
        Product_category = CsvUtils.TestDataStore.get("Product category");
        Product_subcategory = CsvUtils.TestDataStore.get("Product subcategory");
        Vehicle_type = CsvUtils.TestDataStore.get("Vehicle type");
        Vehicle_subtype = CsvUtils.TestDataStore.get("Vehicle subtype");
        Carrier_Type = CsvUtils.TestDataStore.get("CarrierType");
        Intermediary_Name = CsvUtils.TestDataStore.get("Intermediary Name");
        DP_No = CsvUtils.TestDataStore.get("DP No");
        DP_Login_Id = CsvUtils.TestDataStore.get("DP Login Id");
        Relationship_Manager = CsvUtils.TestDataStore.get("Relationship Manager");
        City_Head = CsvUtils.TestDataStore.get("City Head");
        Circle_Head = CsvUtils.TestDataStore.get("Circle Head");
        Business_Head = CsvUtils.TestDataStore.get("Business Head");
        Super_Franchisees = CsvUtils.TestDataStore.get("Super Franchisees");
        Super_Franchisees_ID = CsvUtils.TestDataStore.get("Super Franchisees ID");
        Super_Franchisees_DP_No = CsvUtils.TestDataStore.get("Super Franchisees DP No");
        DP_Level = CsvUtils.TestDataStore.get("DP Level");
        Partner_Parent_Subtype = CsvUtils.TestDataStore.get("Partner Parent Subtype");
        Parent_DP_Name = CsvUtils.TestDataStore.get("Parent DP Name");
        Parent_DP_Id = CsvUtils.TestDataStore.get("Parent DP Id");
        Parent_DP_No = CsvUtils.TestDataStore.get("Parent DP No");
        Parent_DP_Level = CsvUtils.TestDataStore.get("Parent DP Level");
        DP_Branch_Location = CsvUtils.TestDataStore.get("DP Branch Location");
        Business_Type = CsvUtils.TestDataStore.get("Business Type");
        Customer_Title = CsvUtils.TestDataStore.get("Customer Title");
        Customer_First_Name = CsvUtils.TestDataStore.get("Customer First Name");
        Customer_Last_Name = CsvUtils.TestDataStore.get("Customer Last Name");
        Registration_no = CsvUtils.TestDataStore.get("Registration no.");
        RTO_code = CsvUtils.TestDataStore.get("RTO code");
        make = CsvUtils.TestDataStore.get("Make");
        model = CsvUtils.TestDataStore.get("Model");
        variant = CsvUtils.TestDataStore.get("Variant");
        Cubic_Capacity = CsvUtils.TestDataStore.get("Cubic Capacity");
        Fuel_Type = CsvUtils.TestDataStore.get("Fuel Type");
        Mfg_Year = CsvUtils.TestDataStore.get("Mfg. Year");
        GVW_Tonnage = CsvUtils.TestDataStore.get("GVW/Tonnage");
        Seating_capacity = CsvUtils.TestDataStore.get("Seating capacity");
        zeroDepreciation = CsvUtils.TestDataStore.get("ZeroDepreciation");
        Discount_Percentage = CsvUtils.TestDataStore.get("Discount Percentage");
        Case_Status = CsvUtils.TestDataStore.get("Case Status");
        Case_Sub_Status = CsvUtils.TestDataStore.get("Case Sub Status");
        insurer = CsvUtils.TestDataStore.get("Insurer");
        Plan_type = CsvUtils.TestDataStore.get("Plan type");
        Plan_name = CsvUtils.TestDataStore.get("Plan name");
        Plan_Variant = CsvUtils.TestDataStore.get("Plan Variant");
        product_name = CsvUtils.TestDataStore.get("product name");
        Payment_Frequency = CsvUtils.TestDataStore.get("Payment Frequency");
        Upcoming_Payment_Due_Date = CsvUtils.TestDataStore.get("Upcoming Payment Due Date");
        Recent_Payment_Paid_Date = CsvUtils.TestDataStore.get("Recent Payment Paid Date");
        Installment_UniqueId = CsvUtils.TestDataStore.get("Installment UniqueId");
        Policy_term = CsvUtils.TestDataStore.get("Policy term");
        Policy_premium_term = CsvUtils.TestDataStore.get("Policy premium term");
        rawDate = CsvUtils.TestDataStore.get("odRiskStartDate");
        ODRiskStartDate = CsvUtils.toDdMmmYyyy(rawDate);
        rawDate = CsvUtils.TestDataStore.get("tpRiskStartDate");
        TPRiskStartDate = CsvUtils.toDdMmmYyyy(rawDate);
        Multiyear = CsvUtils.TestDataStore.get("multiyear");
        IDV_SA = CsvUtils.TestDataStore.get("IDV/SA");
        TM_Plan_ID = CsvUtils.TestDataStore.get("TM Plan ID");
        Cover_Type = CsvUtils.TestDataStore.get("Cover Type");
        Option_Name = CsvUtils.TestDataStore.get("Option Name");
        age = CsvUtils.TestDataStore.get("Age");
        Registration_City = CsvUtils.TestDataStore.get("Registration City");
        Registration_State = CsvUtils.TestDataStore.get("Registration State");
        Renewal_Year = CsvUtils.TestDataStore.get("Renewal Year");
        creation_source = CsvUtils.TestDataStore.get("creation source");
        rawDate = CsvUtils.TestDataStore.get("Creation Date");
        Creation_Date = CsvUtils.toDdMmmYyyy(rawDate);
        comments = CsvUtils.TestDataStore.get("Comments");
        Basic_OD_Premium = CsvUtils.TestDataStore.get("Basic OD Premium");
        Net_OD_Premium = CsvUtils.TestDataStore.get("Net OD Premium");
        Basic_TP_Premium = CsvUtils.TestDataStore.get("Basic TP Premium");
        TP_Premium = CsvUtils.TestDataStore.get("TP Premium");
        Net_Premium = CsvUtils.TestDataStore.get("Net Premium");
        Annualised_Net_Premium = CsvUtils.TestDataStore.get("Annualised Net Premium");
        CPA_Premium = CsvUtils.TestDataStore.get("CPA Premium");
        ncb = CsvUtils.TestDataStore.get("NCB");
        Record_Status = CsvUtils.TestDataStore.get("Record Status");
        Insurer_Record_Status = CsvUtils.TestDataStore.get("Insurer Record Status");
        Channel_Type = CsvUtils.TestDataStore.get("Channel Type");
        ManualQCStatus = CsvUtils.TestDataStore.get("manualQCStatus");
        IrdaRuleId = CsvUtils.TestDataStore.get("irdaRuleId");
        InsurerRuleId = CsvUtils.TestDataStore.get("insurerRuleId");
        Conflicts = CsvUtils.TestDataStore.get("conflicts");
        Payout_AutoQC = CsvUtils.TestDataStore.get("Payout AutoQC");
        Payout_QC_Done_By = CsvUtils.TestDataStore.get("Payout QC Done By");
        Payout_QC = CsvUtils.TestDataStore.get("Payout QC");
        First_Payout_QC_Date = CsvUtils.TestDataStore.get("First Payout QC Date");
        Final_Payout_QC_Date = CsvUtils.TestDataStore.get("Final Payout QC Date");
        Post_QC_Review_Status = CsvUtils.TestDataStore.get("Post QC Review Status");
        Post_QC_Review_Done_By = CsvUtils.TestDataStore.get("Post QC Review Done By");
        Post_QC_Review_Date = CsvUtils.TestDataStore.get("Post QC Review Date");
        Payout_Type = CsvUtils.TestDataStore.get("Payout Type");
        Payouts_Percentage = CsvUtils.TestDataStore.get("Payouts %");
        Payouts_absolute = CsvUtils.TestDataStore.get("Payouts absolute");
        Payouts_Deviation_Percentage = CsvUtils.TestDataStore.get("Payouts Deviation %");
        Payouts_Deviation_absolute = CsvUtils.TestDataStore.get("Payouts Deviation absolute");
        Final_Payout_Total = CsvUtils.TestDataStore.get("Final Payout Total");
        Partner_cohort = CsvUtils.TestDataStore.get("Partner cohort");
        Partner_club = CsvUtils.TestDataStore.get("Partner club");
        Partner_slab = CsvUtils.TestDataStore.get("Partner slab");
        QuickPay_Eligible = CsvUtils.TestDataStore.get("QuickPay Eligible");
        Skip_Differential_deductions = CsvUtils.TestDataStore.get("Skip Differential deductions");
        Deduction_Per_For_differential = CsvUtils.TestDataStore.get("Deduction% for differential");
        Deduction_Per_For_QP = CsvUtils.TestDataStore.get("Deduction% for QP");
        Deduction_Per_for_campaign = CsvUtils.TestDataStore.get("Deduction% for campaign");
        Deduction_absolute_for_differential = CsvUtils.TestDataStore.get("Deduction absolute for differential");
        Deduction_absolute_for_QP = CsvUtils.TestDataStore.get("Deduction absolute for QP");
        Deduction_absolute_for_campaign = CsvUtils.TestDataStore.get("Deduction absolute for campaign");
        Initial_Effective_Percentages = CsvUtils.TestDataStore.get("Initial Effective Percentages");
        Initial_Effective_Payout_absolute = CsvUtils.TestDataStore.get("Initial Effective Payout absolute");
        Effective_Percentages = CsvUtils.TestDataStore.get("Effective Percentages");
        Effective_Payout_absolute = CsvUtils.TestDataStore.get("Effective Payout absolute");
        remark = CsvUtils.TestDataStore.get("Remark");
    }

    public String getPolicyCommisionID() throws Exception {
        csvAssert.storeCSVdata();
        policyCommissionId = CsvUtils.TestDataStore.get("policyCommissionId");
        return policyCommissionId;
    }

    public String getmisID() throws Exception {
        csvAssert.storeCSVdata();
        policyDetailsId = CsvUtils.TestDataStore.get("policyDetailsId");
        return policyDetailsId;
    }

    public void validateDeviationQuickSearchResult(String deviationType) throws Exception {
        File latestCsv = CsvUtils.getLatestCsvFile();
        WebCommands.staticSleep(1000);
        List<String[]> data = csvAssert.readCsv(latestCsv);
        if (deviationType.equalsIgnoreCase("INCORRECT_RULES")) {
            LogUtils.info("Verifying CSV Result For Uploaded INCORRECT_RULES Deviation");
            csvAssert.assertCell(data, 1, 0, this.getPcid()); //policyCommissionId
            csvAssert.assertCell(data, 1, 1, this.getmID()); //policyDetailsId
            csvAssert.assertCell(data, 1, 3, "MANUAL_DEVIATION"); //commissionSource
            csvAssert.assertCell(data, 1, 4, "INCORRECT_RULES"); //deviationType
            csvAssert.assertCell(data, 1, 100, "DONE"); //Payout QC
            csvAssert.assertCell(data, 1, 107, "0.8xN"); //Payouts %
            csvAssert.assertCell(data, 1, 109, "3.1xN"); //Payouts Deviation %
            csvAssert.assertCell(data, 1, 111, "1860.0");//Final Payout Total
            csvAssert.assertCell(data, 1, 123, "3.1xN"); //Initial Effective Percentages
            csvAssert.assertCell(data, 1, 125, "3.1xN"); //Effective Percentages
            csvAssert.assertCell(data, 1, 149, "1860.0"); //Points
            csvAssert.assertCell(data, 1, 151, "1860.0"); //NetPoints
            LogUtils.info("Validated CSV Result For Uploaded INCORRECT_RULES Deviation");
        } else if (deviationType.equalsIgnoreCase("SPECIAL_REQUEST")) {
            LogUtils.info("Verifying CSV Result For Uploaded SPECIAL_REQUEST Deviation");
            csvAssert.assertCell(data, 1, 0, this.getPcid()); //policyCommissionId
            csvAssert.assertCell(data, 1, 1, this.getmID()); //policyDetailsId
            csvAssert.assertCell(data, 1, 3, "MANUAL_DEVIATION"); //commissionSource
            csvAssert.assertCell(data, 1, 4, "SPECIAL_REQUEST"); //deviationType
            csvAssert.assertCell(data, 1, 100, "DONE"); //Payout QC
            csvAssert.assertCell(data, 1, 107, "0.8xN"); //Payouts %
            csvAssert.assertCell(data, 1, 109, "3.1xN"); //Payouts Deviation %
            csvAssert.assertCell(data, 1, 111, "1860.0");//Final Payout Total
            csvAssert.assertCell(data, 1, 123, "3.1xN"); //Initial Effective Percentages
            csvAssert.assertCell(data, 1, 125, "3.1xN"); //Effective Percentages
            csvAssert.assertCell(data, 1, 149, "1860.0"); //Points
            csvAssert.assertCell(data, 1, 151, "1860.0"); //NetPoints
            LogUtils.info("Validated CSV Result For Uploaded SPECIAL_REQUEST Deviation");
        } else if (deviationType.equalsIgnoreCase("NOMINAL_DEVIATION")) {
            LogUtils.info("Verifying CSV Result For Uploaded NOMINAL_DEVIATION Deviation");
            csvAssert.assertCell(data, 1, 0, this.getPcid()); //policyCommissionId
            csvAssert.assertCell(data, 1, 1, this.getmID()); //policyDetailsId
            csvAssert.assertCell(data, 1, 3, "MANUAL_DEVIATION"); //commissionSource
            csvAssert.assertCell(data, 1, 4, "NOMINAL_DEVIATION"); //deviationType
            csvAssert.assertCell(data, 1, 100, "DONE"); //Payout QC
            csvAssert.assertCell(data, 1, 107, "0.8xN"); //Payouts %
            csvAssert.assertCell(data, 1, 109, "3.1xN"); //Payouts Deviation %
            csvAssert.assertCell(data, 1, 111, "1860.0");//Final Payout Total
            csvAssert.assertCell(data, 1, 123, "3.1xN"); //Initial Effective Percentages
            csvAssert.assertCell(data, 1, 125, "3.1xN"); //Effective Percentages
            csvAssert.assertCell(data, 1, 149, "1860.0"); //Points
            csvAssert.assertCell(data, 1, 151, "1860.0"); //NetPoints
            LogUtils.info("Validated CSV Result For Uploaded NOMINAL_DEVIATION Deviation");
        } else if (deviationType.equalsIgnoreCase("SPLIT_DEVIATIONS")) {
            LogUtils.info("Verifying CSV Result For Uploaded SPLIT_DEVIATIONS");
            csvAssert.assertCell(data, 1, 0, this.getPcid()); //policyCommissionId
            csvAssert.assertCell(data, 1, 1, this.getmID()); //policyDetailsId
            csvAssert.assertCell(data, 1, 3, "MANUAL_DEVIATION"); //commissionSource
            csvAssert.assertCell(data, 1, 4, "NOMINAL_DEVIATION"); //deviationType
            csvAssert.assertCell(data, 1, 100, "DONE"); //Payout QC
            csvAssert.assertCell(data, 1, 107, "0.8xN"); //Payouts %
            csvAssert.assertCell(data, 1, 109, "3.1xN"); //Payouts Deviation %
            csvAssert.assertCell(data, 1, 111, "1860.0");//Final Payout Total
            csvAssert.assertCell(data, 1, 123, "3.1xN"); //Initial Effective Percentages
            csvAssert.assertCell(data, 1, 125, "3.1xN"); //Effective Percentages
            csvAssert.assertCell(data, 1, 149, "1860.0"); //Points
            csvAssert.assertCell(data, 1, 151, "1860.0"); //NetPoints
            LogUtils.info("Validated CSV Result For Uploaded SPLIT_DEVIATIONS Deviation");
        }
        TestUtil.click(qsp.resetButton, "");
    }

    public void validateQuickSearchResult(String uploadedFileName) throws Exception {
        File latestCsv = CsvUtils.getLatestCsvFile();
        WebCommands.staticSleep(5000);
        List<String[]> data = csvAssert.readCsv(latestCsv);
        if (uploadedFileName.equalsIgnoreCase("Adjustments")) {
            LogUtils.info("Verifying CSV Result For Uploaded Adjustments");
            csvAssert.assertCell(data, 1, 0, "692fce37e55fd35ac2d06b22"); //policyCommissionId
            LogUtils.info("policyCommissionId : " + data.get(1)[0]);
            csvAssert.assertCell(data, 1, 1, "MIS_AHSK7OHON57"); //policyDetailsId
            LogUtils.info("policyDetailsId : " + data.get(1)[1]);
            csvAssert.assertCell(data, 1, 3, "SYSTEM_GENERATED"); //commissionSource
            LogUtils.info("commissionSource : " + data.get(1)[3]);
            csvAssert.assertCell(data, 1, 132, "202512C2"); //Payment Cycle
            LogUtils.info("Payment Cycle : " + data.get(1)[132]);
            csvAssert.assertCell(data, 1, 135, "ADJUSTMENT"); //Ledger_Entity_Type
            LogUtils.info("Ledger_Entity_Type : " + data.get(1)[135]);
            csvAssert.assertCell(data, 1, 137, "Discrepancy"); //Payout Disbursal Type
            LogUtils.info("Payout Disbursal Type : " + data.get(1)[137]);
            csvAssert.assertCell(data, 1, 148, "AdjustmentsTest"); //Ledger_Remarks
            LogUtils.info("Ledger_Remarks : " + data.get(1)[148]);
            csvAssert.assertCell(data, 1, 149, "18.0");//Points
            LogUtils.info("Points : " + data.get(1)[149]);
            csvAssert.assertCell(data, 1, 151, "0.0"); //NetPoints
            LogUtils.info("NetPoints : " + data.get(1)[151]);
            csvAssert.assertCell(data, 1, 152, "PENDING"); //Ledger_Status
            LogUtils.info("Ledger_Status : " + data.get(1)[152]);
            LogUtils.info("Validated CSV Result For Uploaded Adjustments");
        } else if (uploadedFileName.equalsIgnoreCase("PayoutQC_DONE_misQC")) {
            LogUtils.info("Verifying CSV Result For Uploaded PayoutQC if misQC is DONE");
            csvAssert.assertCell(data, 1, 100, "DONE"); //Payout QC
            LogUtils.info("Payout QC : " + data.get(1)[100]);
            LogUtils.info("Validated CSV Result For Uploaded PayoutQC if misQC is DONE");
        } else if (uploadedFileName.equalsIgnoreCase("PayoutQC_PENDING_misQC")) {
            LogUtils.info("Verifying CSV Result For Uploaded PayoutQC if misQC is PENDING");
            csvAssert.assertCell(data, 1, 100, "PENDING"); //Payout QC
            LogUtils.info("Payout QC : " + data.get(1)[100]);
            LogUtils.info("Validated CSV Result For Uploaded PayoutQC if misQC is PENDING");
        } else if (uploadedFileName.equalsIgnoreCase("PostQC_Review_DONE_PayoutQC")) {
            LogUtils.info("Verifying CSV Result For Uploaded PostQC_Review if PayoutQC is DONE");
            csvAssert.assertCell(data, 1, 103, "PASS"); //PostQC_Review Status
            LogUtils.info("Post QC Review Status : " + data.get(1)[103]);
            csvAssert.assertCell(data, 1, 103, Post_QC_Review_Status);
            csvAssert.assertCell(data, 1, 104, Post_QC_Review_Done_By);
            csvAssert.assertCell(data, 1, 105, Post_QC_Review_Date);
            LogUtils.info("Validated CSV Result For Uploaded PostQC_Review if PayoutQC is PENDING");
        } else if (uploadedFileName.equalsIgnoreCase("PostQC_Review_PENDING_PayoutQC")) {
            LogUtils.info("Verifying CSV Result For Uploaded PostQC_Review if PayoutQC is PENDING");
            csvAssert.assertCell(data, 1, 103, ""); //PostQC_Review Status
            LogUtils.info("Post QC Review Status : " + data.get(1)[103]);
            csvAssert.assertCell(data, 1, 103, Post_QC_Review_Status);
            csvAssert.assertCell(data, 1, 104, Post_QC_Review_Done_By);
            csvAssert.assertCell(data, 1, 105, Post_QC_Review_Date);
            LogUtils.info("Validated CSV Result For Uploaded PostQC_Review if PayoutQC is PENDING");
        }
        action.moveToElement(qsp.resetButton).click().perform();
    }

    public void writeDeviationUploadCSV(String pcid, String deviationType) throws IOException {
        File latestCsv = CsvUtils.getLatestCsvFile();
        WebCommands.staticSleep(5000);
        CSVWriter writer = new CSVWriter(new FileWriter(latestCsv, true)); // true for append mode
        if (deviationType.equals("INCORRECT_RULES")) {
            System.out.println("INCORRECT_RULES BLOCK 3");
            String[] data = {pcid, "INCORRECT_RULES", "FALSE", "0", "0", "0", "0", "0", "0", "0", "31", "0", "Testing"};
            System.out.println("Entered PolicyCommistionID : " + pcid);
            writer.writeNext(data);
            writer.close();
            System.out.println("Data written to CSV successfully! for INCORRECT_RULES");
        } else if (deviationType.equals("SPECIAL_REQUEST")) {
            System.out.println("SPECIAL_REQUEST BLOCK 3");
            String[] data = {pcid, "SPECIAL_REQUEST", "FALSE", "0", "0", "0", "0", "0", "0", "0", "31", "0", "Testing"};

            System.out.println("Entered PolicyCommistionID : " + pcid);
            writer.writeNext(data);
            writer.close();
            System.out.println("Data written to CSV successfully! for SPECIAL_REQUEST");
        } else if (deviationType.equals("NOMINAL_DEVIATION")) {
            String[] data = {pcid, "NOMINAL_DEVIATION", "FALSE", "0", "0", "0", "0", "0", "0", "0", "31", "0", "Testing"};

            System.out.println("Entered PolicyCommistionID : " + pcid);
            writer.writeNext(data);
            writer.close();
            System.out.println("Data written to CSV successfully! for NOMINAL_DEVIATION");
        } else if (deviationType.equals("INCORRECT_RULESS")) {
            String[] data = {pcid, "INCORRECT_RULESS", "FALSE", "0", "0", "0", "0", "0", "0", "0", "31", "0", "Testing"};

            System.out.println("Entered PolicyCommistionID : " + pcid);
            writer.writeNext(data);
            writer.close();
            System.out.println("Data written to CSV successfully! for INCORRECT_RULESS -Invalid Type");
        } else if (deviationType.equals("SPLIT_DEVIATIONS") || (deviationType.equalsIgnoreCase("NonSplitPartner_SPLIT_DEVIATIONS"))) {
            String[] data = {pcid, "35", "Testing"};

            System.out.println("Entered PolicyCommistionID : " + pcid);
            writer.writeNext(data);
            writer.close();
            System.out.println("Data written to CSV successfully! for SPLIT_DEVIATIONS");
        }
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(latestCsv.getAbsolutePath());
        WebCommands.staticSleep(2000);
        if (deviationType.equalsIgnoreCase("INCORRECT_RULES") || deviationType.equalsIgnoreCase("SPECIAL_REQUEST") || deviationType.equalsIgnoreCase("NOMINAL_DEVIATION") || deviationType.equalsIgnoreCase("INCORRECT_RULESS")) {
            action.moveToElement(deviationTypeDrpdwn).click().build().perform();
        }
        WebCommands.staticSleep(2000);
        if (deviationType.equals("INCORRECT_RULES")) {
            action.moveToElement(IncretDeviationdeviation).click().build().perform();
        } else if (deviationType.equals("SPECIAL_REQUEST")) {
            action.moveToElement(SplReqstDeviationdeviation).click().build().perform();
        } else if (deviationType.equals("NOMINAL_DEVIATION")) {
            action.moveToElement(NominalDeviationdeviation).click().build().perform();
        } else if (deviationType.equals("INCORRECT_RULESS")) {
            action.moveToElement(IncretDeviationdeviation).click().build().perform();
        }

    }

    public void writeUploadCSV_PayoutQC() throws Exception {
        File latestCsv = CsvUtils.getLatestCsvFile();
        WebCommands.staticSleep(5000);
        CSVWriter writer = new CSVWriter(new FileWriter(latestCsv, true)); // true for append mode
        String[] data = {policyCommissionId, policyDetailsId, "", commissionSource, "", "", version, Policy_No, "", "", MIS_Data_entry_owner, "",
                Booking_Issued_Date, Booking_Issued_Month, Product_category, "", Vehicle_type,
                Vehicle_subtype, "", Intermediary_Name, DP_No, DP_Login_Id, Relationship_Manager, City_Head,
                Circle_Head, "", "", "", "", DP_Level, "", "", "", "", "", DP_Branch_Location, Business_Type, Customer_Title, Customer_First_Name,
                Customer_Last_Name, Registration_no, RTO_code, make, model, "", Cubic_Capacity, Fuel_Type, Mfg_Year, "",
                Seating_capacity, zeroDepreciation, "", Case_Status, "", insurer, "", "", "", product_name, "", "", "", "", Policy_term, "", "",
                "", ODRiskStartDate, "", TPRiskStartDate, "", Multiyear, IDV_SA, "", "", "", "",
                Registration_City, Registration_State, "", creation_source, Creation_Date, "", Basic_OD_Premium, Net_OD_Premium,
                Basic_TP_Premium, TP_Premium, Net_Premium, "", CPA_Premium, ncb, Record_Status, Insurer_Record_Status, Channel_Type,
                ManualQCStatus, IrdaRuleId, InsurerRuleId, "", "", "", "DONE", "", "", "", "", "", Payout_Type, Payouts_Percentage, "", "", "", Final_Payout_Total, "", "",
                Partner_slab, QuickPay_Eligible, Skip_Differential_deductions, "", Deduction_Per_For_QP, "", "", "", "", Initial_Effective_Percentages, "",
                Effective_Percentages, "", "Testing PayoutQC Upload"};
        writer.writeNext(data);
        System.out.println("QC File Writing : " + Arrays.toString(data));
        writer.close();
        System.out.println("Data written to CSV successfully!");

        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(latestCsv.getAbsolutePath());
    }

    public void writeUploadCSV_PostQC_Review() throws Exception {
        File latestCsv = CsvUtils.getLatestCsvFile();
        WebCommands.staticSleep(5000);
        CSVWriter writer = new CSVWriter(new FileWriter(latestCsv, true)); // true for append mode
        String[] data = {ledger_Id, "Pass"};
        writer.writeNext(data);
        System.out.println("QC File Writing : " + Arrays.toString(data));
        writer.close();
        System.out.println("Data written to CSV successfully!");
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(latestCsv.getAbsolutePath());
    }

    public void validateDownloadTemplateFile(String fileName) throws Exception {
        File latestCsv = CsvUtils.getLatestCsvFile();
        WebCommands.staticSleep(3000);
        List<String[]> data = csvAssert.readCsv(latestCsv);
        LogUtils.info("Verifying Template File *****");
        if (fileName.equalsIgnoreCase("ManualUpload.csv")) {
            LogUtils.info("Verifying Column Present in Manual Upload Template");
            csvAssert.assertRow(data, 0, Arrays.asList("policyDetailsId", "policyPaymentScheduleId",
                    "DP Login Id", "Customer First Name", "Customer Last Name", "Booking/Issued Date",
                    "Payment Cycle", "Case Status", "Payout Policy Type", "Channel Type", "Product category",
                    "Product subcategory", "Vehicle type", "Vehicle subtype", "Business Type", "Plan name",
                    "Insurer", "product name", "Registration no.", "Policy No.", "Master Policy No.",
                    "Payout IRDA ODB %", "Payout IRDA ODR %", "Payout IRDA TP %", "Payout IRDA NP %",
                    "Payout IRDA ABS", "Insurer Payout OD %", "Insurer Payout TP %", "Insurer Payout NP %",
                    "Insurer Payout ABS", "Basic OD Premium", "Net OD Premium", "Basic TP Premium", "TP Premium",
                    "Net Premium", "PC_CreatedAt", "Remark"));
            LogUtils.info("Validated Column Present in Manual Upload Template as Expected");
        } else if (fileName.equalsIgnoreCase("ManualCorrection.csv")) {
            LogUtils.info("Verifying Column Present in Manual Correction Template");
            csvAssert.assertRow(data, 0, Arrays.asList("policyCommissionId", "policyDetailsId",
                    "policyPaymentScheduleId", "DP Login Id", "Customer First Name", "Customer Last Name",
                    "Booking/Issued Date", "Case Status", "Payout Policy Type", "Channel Type", "Product category",
                    "Product subcategory", "Vehicle type", "Vehicle subtype", "Business Type", "Plan name",
                    "Insurer", "product name", "Registration no.", "Policy No.", "Master Policy No.",
                    "Payout IRDA ODB %", "Payout IRDA ODR %", "Payout IRDA TP %", "Payout IRDA NP %",
                    "Payout IRDA ABS", "Insurer Payout OD %", "Insurer Payout TP %", "Insurer Payout NP %",
                    "Insurer Payout ABS", "Basic OD Premium", "Net OD Premium", "Basic TP Premium", "TP Premium",
                    "Net Premium", "Remark"));
            LogUtils.info("Validated Column Present in Manual Correction Template as Expected");
        } else if (fileName.equalsIgnoreCase("INCORRECT_RULES") || fileName.equalsIgnoreCase("SPECIAL_REQUEST") || fileName.equalsIgnoreCase("NOMINAL_DEVIATION") || fileName.equalsIgnoreCase("INCORRECT_RULESS")) {
            LogUtils.info("Verifying Column Present in Manual Deviations Template");
            csvAssert.assertRow(data, 0, Arrays.asList("policyCommissionId", "Type", "SkipDifferentialDeductions",
                    "Payout IRDA ODB %", "Payout IRDA ODR %", "Payout IRDA TP %", "Payout IRDA NP %", "Payout IRDA ABS",
                    "Insurer Payout OD %", "Insurer Payout TP %", "Insurer Payout NP %", "Insurer Payout ABS", "Remark"));
            LogUtils.info("Validated Column Present in Manual Deviations Template as Expected");
        } else if (fileName.equalsIgnoreCase("SPLIT_DEVIATIONS") || (fileName.equalsIgnoreCase("NonSplitPartner_SPLIT_DEVIATIONS"))) {
            LogUtils.info("Verifying Column Present in SplitDeviations Template");
            csvAssert.assertRow(data, 0, Arrays.asList("policyCommissionId", "Dealer Commission Retention %", "Remark"));
            LogUtils.info("Validated Column Present in SplitDeviations Template as Expected");
        } else if (fileName.equalsIgnoreCase("Adjustments.csv") || fileName.equalsIgnoreCase("AdjustmentsInvalid.csv")) {
            LogUtils.info("Verifying Column Present in Adjustments Template");
            csvAssert.assertRow(data, 0, Arrays.asList("Recorded_At", "policyCommissionId", "policyDetailsId",
                    "policyPaymentScheduleId", "DP Login Id", "Customer First Name", "Customer Last Name",
                    "Booking/Issued Date", "Payment Cycle", "Case Status", "Channel Type", "Product category",
                    "Product subcategory", "Vehicle type", "Vehicle subtype", "Business Type", "Plan name", "Insurer",
                    "product name", "Registration no.", "Policy No.", "Master Policy No.", "Points", "Remark"));
            LogUtils.info("Validated Column Present in Adjustments Template as Expected");
        } else if (fileName.equalsIgnoreCase("PayoutQC_DONE_misQC") || fileName.equalsIgnoreCase("PayoutQC_PENDING_misQC")) {
            LogUtils.info("* Verifying Column Present in PayoutQC Template *");
            csvAssert.assertRow(data, 0, Arrays.asList("policyCommissionId", "policyDetailsId", "policyPaymentScheduleId",
                    "commissionSource", "deviationType", "Payout Policy Type", "version", "Policy No.", "Master Policy No.",
                    "Application No.", "MIS / Data entry owner", "PI CreatedBy", "Booking/Issued Date", "Booking/Issued Month",
                    "Product category", "Product subcategory", "Vehicle type", "Vehicle subtype", "CarrierType", "Intermediary Name",
                    "DP No", "DP Login Id", "Relationship Manager", "City Head", "Circle Head", "Business Head", "Super Franchisees",
                    "Super Franchisees ID", "Super Franchisees DP No", "DP Level", "Partner Parent Subtype", "Parent DP Name", "Parent DP Id",
                    "Parent DP No", "Parent DP Level", "DP Branch Location", "Business Type", "Customer Title", "Customer First Name",
                    "Customer Last Name", "Registration no.", "RTO code", "Make", "Model", "Variant", "Cubic Capacity", "Fuel Type", "Mfg. Year",
                    "GVW/Tonnage", "Seating capacity", "ZeroDepreciation", "Discount Percentage", "Case Status", "Case Sub Status", "Insurer",
                    "Plan type", "Plan name", "Plan Variant", "product name", "Payment Frequency", "Upcoming Payment Due Date", "Recent Payment Paid Date",
                    "Installment UniqueId", "Policy term", "Policy premium term", "Risk Start Date", "Risk End Date", "odRiskStartDate", "odRiskEndDate",
                    "tpRiskStartDate", "tpRiskEndDate", "multiyear", "IDV/SA", "TM Plan ID", "Cover Type", "Option Name", "Age", "Registration City",
                    "Registration State", "Renewal Year", "creation source", "Creation Date", "Comments", "Basic OD Premium", "Net OD Premium",
                    "Basic TP Premium", "TP Premium", "Net Premium", "Annualised Net Premium", "CPA Premium", "NCB", "Record Status", "Insurer Record Status",
                    "Channel Type", "manualQCStatus", "irdaRuleId", "insurerRuleId", "conflicts", "Payout AutoQC", "Payout QC Done By", "Payout QC",
                    "First Payout QC Date", "Final Payout QC Date", "Post QC Review Status", "Post QC Review Done By", "Post QC Review Date", "Payout Type",
                    "Payouts %", "Payouts absolute", "Payouts Deviation %", "Payouts Deviation absolute", "Final Payout Total", "Partner cohort", "Partner club",
                    "Partner slab", "QuickPay Eligible", "Skip Differential deductions", "Deduction% for differential", "Deduction% for QP", "Deduction% for campaign",
                    "Deduction absolute for differential", "Deduction absolute for QP", "Deduction absolute for campaign", "Initial Effective Percentages",
                    "Initial Effective Payout absolute", "Effective Percentages", "Effective Payout absolute", "Remark"));
            LogUtils.info("Validated Column Present in PayoutQC Template as Expected");
        } else if (fileName.equalsIgnoreCase("InsurerRewards.csv")) {
            LogUtils.info("Verifying Column Present in InsurerRewards Template");
            csvAssert.assertRow(data, 0, Arrays.asList("policyCommissionId", "Insurer Volume Rewards %", "Insurer Rewards Total", "Insurer Rewards Volume", "Remark"));
            LogUtils.info("Validated Column Present in InsurerRewards Template as Expected");
        } else if (fileName.equalsIgnoreCase("PartnerLevelActivity.csv")) {
            LogUtils.info("Verifying Column Present in PartnerLevelActivity Template");
            csvAssert.assertRow(data, 0, Arrays.asList("Recorded_At", "Partner Id", "Points", "Payment Cycle", "Type", "Remark"));
            LogUtils.info("Validated Column Present in PartnerLevelActivity Template as Expected");
        } else if (fileName.equalsIgnoreCase("PostQC_Review_DONE_PayoutQC") || fileName.equalsIgnoreCase("PostQC_Review_PENDING_PayoutQC")) {
            LogUtils.info("Verifying Column Present in PostQC_Review Template");
            csvAssert.assertRow(data, 0, Arrays.asList("Ledger_Id", "Post-QC Review"));
            LogUtils.info("Validated Column Present in PostQC_Review Template as Expected");
        }

    }

    public void selectPaymentCycle(String paymentCycle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement scrollBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='rc-virtual-list-holder'])[2]")));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        long scrollHeight = (long) js.executeScript("return arguments[0].scrollHeight", scrollBox);
        long clientHeight = (long) js.executeScript("return arguments[0].clientHeight", scrollBox);
        long currentScroll = 0;
        boolean found = false;
        while (currentScroll < scrollHeight) {
            List<WebElement> options = driver.findElements(By.xpath("(//div[contains(@class,'rc-virtual-list-holder-inner')])[2]//div[text()='" + paymentCycle + "']"));
            if (!options.isEmpty()) {
                options.get(0).click();
                found = true;
                break;
            }
            currentScroll += clientHeight;
            js.executeScript("arguments[0].scrollTop = arguments[1];", scrollBox, currentScroll);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        if (!found) {
            throw new RuntimeException("Cycle '" + paymentCycle + "' not found");
        }
    }

    public void UploadPayoutsHistoryAssertion(String fileName) {
        LogUtils.info("Uploaded By : " + uploadeByUser.getText());
        Assert.assertEquals(uploadeByUser.getText(), "automationtesting");
        LogUtils.info("Entered Comment : " + enteredComment.getText());
        Assert.assertEquals(enteredComment.getText(), "Automation Test");

        if (fileName.equalsIgnoreCase("ManualUpload.csv")) {
            LogUtils.info("Payment Cycle : " + paymentCycle.getText());
            Assert.assertEquals(paymentCycle.getText(), "202512C2");
            Assert.assertEquals("Manual Upload", uploadedType.getText());
            LogUtils.info("Success count is : " + successCount.getText());
            Assert.assertEquals(successCount.getText(), "5");
            LogUtils.info("Failure count is : " + failureCount.getText());
            Assert.assertEquals(failureCount.getText(), "2");
        } else if (fileName.equalsIgnoreCase("ManualCorrection.csv")) {
            LogUtils.info("Payment Cycle : " + paymentCycle.getText());
            Assert.assertEquals(paymentCycle.getText(), "-");
            Assert.assertEquals("Manual Correction", uploadedType.getText());
            LogUtils.info("Success count is : " + successCount.getText());
            Assert.assertEquals(successCount.getText(), "5");
            LogUtils.info("Failure count is : " + failureCount.getText());
            Assert.assertEquals(failureCount.getText(), "1");
        } else if (fileName.equalsIgnoreCase("INCORRECT_RULES") || fileName.equalsIgnoreCase("SPECIAL_REQUEST") || fileName.equalsIgnoreCase("NOMINAL_DEVIATION")) {
            LogUtils.info("Payment Cycle : " + paymentCycle.getText());
            Assert.assertEquals(paymentCycle.getText(), "-");
            Assert.assertEquals("Deviations", uploadedType.getText());
            LogUtils.info("Success count is : " + successCount.getText());
            Assert.assertEquals(successCount.getText(), "1");
            LogUtils.info("Failure count is : " + failureCount.getText());
            Assert.assertEquals(failureCount.getText(), "0");
        } else if (fileName.equalsIgnoreCase("INCORRECT_RULESS")) {
            LogUtils.info("Payment Cycle : " + paymentCycle.getText());
            Assert.assertEquals(paymentCycle.getText(), "-");
            Assert.assertEquals("Deviations", uploadedType.getText());
            LogUtils.info("Success count is : " + successCount.getText());
            Assert.assertEquals(successCount.getText(), "0");
            LogUtils.info("Failure count is : " + failureCount.getText());
            Assert.assertEquals(failureCount.getText(), "1");
        } else if (fileName.equalsIgnoreCase("SPLIT_DEVIATIONS")) {
            LogUtils.info("Payment Cycle : " + paymentCycle.getText());
            Assert.assertEquals(paymentCycle.getText(), "-");
            Assert.assertEquals("Split Deviations", uploadedType.getText());
            LogUtils.info("Success count is : " + successCount.getText());
            Assert.assertEquals(successCount.getText(), "1");
            LogUtils.info("Failure count is : " + failureCount.getText());
            Assert.assertEquals(failureCount.getText(), "0");
        } else if (fileName.equalsIgnoreCase("NonSplitPartner_SPLIT_DEVIATIONS")) {
            LogUtils.info("Payment Cycle : " + paymentCycle.getText());
            Assert.assertEquals(paymentCycle.getText(), "-");
            Assert.assertEquals("Split Deviations", uploadedType.getText());
            LogUtils.info("Success count is : " + successCount.getText());
            Assert.assertEquals(successCount.getText(), "0");
            LogUtils.info("Failure count is : " + failureCount.getText());
            Assert.assertEquals(failureCount.getText(), "1");
        } else if (fileName.equalsIgnoreCase("Adjustments.csv")) {
            LogUtils.info("Payment Cycle : " + paymentCycle.getText());
            Assert.assertEquals(paymentCycle.getText(), "202512C2");
            Assert.assertEquals("Adjustments", uploadedType.getText());
            LogUtils.info("Success count is : " + successCount.getText());
            Assert.assertEquals(successCount.getText(), "1");
            LogUtils.info("Failure count is : " + failureCount.getText());
            Assert.assertEquals(failureCount.getText(), "0");
        } else if (fileName.equalsIgnoreCase("AdjustmentsInvalid.csv")) {
            LogUtils.info("Payment Cycle : " + paymentCycle.getText());
            Assert.assertEquals(paymentCycle.getText(), "202512C2");
            Assert.assertEquals("Adjustments", uploadedType.getText());
            LogUtils.info("Success count is : " + successCount.getText());
            Assert.assertEquals(successCount.getText(), "0");
            LogUtils.info("Failure count is : " + failureCount.getText());
            Assert.assertEquals(failureCount.getText(), "1");
        } else if (fileName.equalsIgnoreCase("PayoutQC_DONE_misQC")) {
            LogUtils.info("Payment Cycle : " + paymentCycle.getText());
            Assert.assertEquals(paymentCycle.getText(), "-");
            Assert.assertEquals("Payout QC", uploadedType.getText());
            LogUtils.info("Success count is : " + successCount.getText());
            Assert.assertEquals(successCount.getText(), "1");
            LogUtils.info("Failure count is : " + failureCount.getText());
            Assert.assertEquals(failureCount.getText(), "0");
        } else if (fileName.equalsIgnoreCase("PayoutQC_PENDING_misQC")) {
            LogUtils.info("Payment Cycle : " + paymentCycle.getText());
            Assert.assertEquals(paymentCycle.getText(), "-");
            Assert.assertEquals("Payout QC", uploadedType.getText());
            LogUtils.info("Success count is : " + successCount.getText());
            Assert.assertEquals(successCount.getText(), "0");
            LogUtils.info("Failure count is : " + failureCount.getText());
            Assert.assertEquals(failureCount.getText(), "1");
        } else if (fileName.equalsIgnoreCase("PostQC_Review_DONE_PayoutQC")) {
            LogUtils.info("Payment Cycle : " + paymentCycle.getText());
            Assert.assertEquals(paymentCycle.getText(), "-");
            Assert.assertEquals("Post QC Review", uploadedType.getText());
            LogUtils.info("Success count is : " + successCount.getText());
            Assert.assertEquals(successCount.getText(), "1");
            LogUtils.info("Failure count is : " + failureCount.getText());
            Assert.assertEquals(failureCount.getText(), "0");
        } else if (fileName.equalsIgnoreCase("PostQC_Review_PENDING_PayoutQC") || fileName.equalsIgnoreCase("Reupload_PostQC_Review_DONE_PayoutQC")) {
            LogUtils.info("Payment Cycle : " + paymentCycle.getText());
            Assert.assertEquals(paymentCycle.getText(), "-");
            Assert.assertEquals("Post QC Review", uploadedType.getText());
            LogUtils.info("Success count is : " + successCount.getText());
            Assert.assertEquals(successCount.getText(), "0");
            LogUtils.info("Failure count is : " + failureCount.getText());
            Assert.assertEquals(failureCount.getText(), "1");
        }
        Assert.assertTrue(outputFileBtn.isDisplayed());
    }

    public void validateOutputFile(String fileName, String deviationType, String pcid) throws Exception {
        File latestCsv = CsvUtils.getLatestCsvFile();
        WebCommands.staticSleep(5000);
        List<String[]> data = csvAssert.readCsv(latestCsv);
        if (fileName.equalsIgnoreCase("ManualUpload.csv")) {
            LogUtils.info("Validating Manual Upload Output File");
            csvAssert.assertCell(data, 1, 0, "MIS_MHA13RIGX7A4V1"); // Validate MIS ID in Output File
            csvAssert.assertCell(data, 2, 0, "MIS_MHA13RIGX7A4V2");
            csvAssert.assertCell(data, 3, 0, "MIS_MHA13RIGX7A4V3");
            csvAssert.assertCell(data, 4, 0, "MIS_MHA13RIGX7A4V4");
            csvAssert.assertCell(data, 5, 0, "MIS_MHA13RIGX7A4V5");
            csvAssert.assertCell(data, 6, 0, "MIS_MHA13RIGX7A4V6");
            csvAssert.assertCell(data, 7, 0, "MIS_MHA13RIGX7A4V7");
            csvAssert.assertCell(data, 1, 6, "202512C2");           // Validate Payment Cycle in Output File
            csvAssert.assertCell(data, 2, 6, "202512C2");
            csvAssert.assertCell(data, 3, 6, "202512C2");
            csvAssert.assertCell(data, 4, 6, "202512C2");
            csvAssert.assertCell(data, 5, 6, "202512C2");
            csvAssert.assertCell(data, 6, 6, "202512C1");
            csvAssert.assertCell(data, 7, 6, "202512C1");
            csvAssert.assertCell(data, 1, 37, "SUCCESS");          // Validate Output Status in Output File
            csvAssert.assertCell(data, 2, 37, "SUCCESS");
            csvAssert.assertCell(data, 3, 37, "SUCCESS");
            csvAssert.assertCell(data, 4, 37, "SUCCESS");
            csvAssert.assertCell(data, 5, 37, "SUCCESS");
            csvAssert.assertCell(data, 6, 37, "FAILURE");
            csvAssert.assertCell(data, 7, 37, "FAILURE");
            csvAssert.assertCell(data, 6, 38, "Payment Cycle is not 202512C2"); // Validate Output Remark in Output File
            csvAssert.assertCell(data, 7, 38, "Payment Cycle is not 202512C2");
            LogUtils.info("Validated Manual Upload Output File");
        } else if (fileName.equalsIgnoreCase("ManualCorrection.csv")) {
            LogUtils.info("Validating Column Present in Manual Correction Output File");
            csvAssert.assertCell(data, 1, 1, "MIS_MHA13RIGX7A4V1"); // Validate MIS ID in Output File
            csvAssert.assertCell(data, 2, 1, "MIS_MHA13RIGX7A4V2");
            csvAssert.assertCell(data, 3, 1, "MIS_MHA13RIGX7A4V3");
            csvAssert.assertCell(data, 4, 1, "MIS_MHA13RIGX7A4V4");
            csvAssert.assertCell(data, 5, 1, "MIS_MHA13RIGX7A4V5");
            csvAssert.assertCell(data, 6, 1, "MIS_AHSJCV6AHAI");
            csvAssert.assertCell(data, 1, 36, "SUCCESS");          // Validate Output Status in Output File
            csvAssert.assertCell(data, 2, 36, "SUCCESS");
            csvAssert.assertCell(data, 3, 36, "SUCCESS");
            csvAssert.assertCell(data, 4, 36, "SUCCESS");
            csvAssert.assertCell(data, 5, 36, "SUCCESS");
            csvAssert.assertCell(data, 6, 36, "FAILURE");
            csvAssert.assertCell(data, 6, 37, "Transition from SYSTEM_GENERATED to MANUAL_CORRECTION is not possible;"); // Validate Output Remark in Output File
            LogUtils.info("Validated Column Present in Manual Correction Output File as Expected");
        } else if (fileName.equalsIgnoreCase("Deviations")) {
            LogUtils.info("Validating Data Present in Manual Deviations Output File");
            if (deviationType.equalsIgnoreCase("INCORRECT_RULES")) {
                csvAssert.assertRow(data, 0, Arrays.asList("policyCommissionId", "Type", "SkipDifferentialDeductions", "Payout IRDA ODB %", "Payout IRDA ODR %", "Payout IRDA TP %", "Payout IRDA NP %", "Payout IRDA ABS", "Insurer Payout OD %", "Insurer Payout TP %", "Insurer Payout NP %", "Insurer Payout ABS", "Remark", "Output Status", "Output Remark"));
                csvAssert.assertRow(data, 1, Arrays.asList(pcid, "INCORRECT_RULES", "false", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "31.0", "0.0", "Testing", "SUCCESS", ""));
            } else if (deviationType.equalsIgnoreCase("SPECIAL_REQUEST")) {
                csvAssert.assertRow(data, 0, Arrays.asList("policyCommissionId", "Type", "SkipDifferentialDeductions", "Payout IRDA ODB %", "Payout IRDA ODR %", "Payout IRDA TP %", "Payout IRDA NP %", "Payout IRDA ABS", "Insurer Payout OD %", "Insurer Payout TP %", "Insurer Payout NP %", "Insurer Payout ABS", "Remark", "Output Status", "Output Remark"));
                csvAssert.assertRow(data, 1, Arrays.asList(pcid, "SPECIAL_REQUEST", "false", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "31.0", "0.0", "Testing", "SUCCESS", ""));
            } else if (deviationType.equalsIgnoreCase("NOMINAL_DEVIATION")) {
                csvAssert.assertRow(data, 0, Arrays.asList("policyCommissionId", "Type", "SkipDifferentialDeductions", "Payout IRDA ODB %", "Payout IRDA ODR %", "Payout IRDA TP %", "Payout IRDA NP %", "Payout IRDA ABS", "Insurer Payout OD %", "Insurer Payout TP %", "Insurer Payout NP %", "Insurer Payout ABS", "Remark", "Output Status", "Output Remark"));
                csvAssert.assertRow(data, 1, Arrays.asList(pcid, "NOMINAL_DEVIATION", "false", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "31.0", "0.0", "Testing", "SUCCESS", ""));
            } else if (deviationType.equalsIgnoreCase("INCORRECT_RULESS")) {
                csvAssert.assertRow(data, 0, Arrays.asList("policyCommissionId", "Type", "SkipDifferentialDeductions", "Payout IRDA ODB %", "Payout IRDA ODR %", "Payout IRDA TP %", "Payout IRDA NP %", "Payout IRDA ABS", "Insurer Payout OD %", "Insurer Payout TP %", "Insurer Payout NP %", "Insurer Payout ABS", "Remark", "Output Status", "Output Remark"));
                csvAssert.assertRow(data, 1, Arrays.asList(pcid, "INCORRECT_RULESS", "false", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "31.0", "0.0", "Testing", "FAILURE", "Invalid Deviation Type"));
            }
            LogUtils.info("Validated Data Present in Manual Deviations Output File as Expected");
        } else if (fileName.equalsIgnoreCase("SPLIT_DEVIATIONS")) {
            LogUtils.info("Validating Column Present in SplitDeviations Output File as Expected");
            csvAssert.assertRow(data, 0, Arrays.asList("policyCommissionId", "Dealer Commission Retention %", "Remark", "Output Status", "Output Remark"));
            csvAssert.assertRow(data, 1, Arrays.asList(pcid, "35.0", "Testing", "SUCCESS", ""));
            LogUtils.info("Validated Column Present in SplitDeviations Template Output File as Expected");
        } else if (fileName.equalsIgnoreCase("NonSplitPartner_SPLIT_DEVIATIONS")) {
            LogUtils.info("Validating Column Present in NonSplitPartner_SplitDeviations Output File as Expected");
            csvAssert.assertRow(data, 0, Arrays.asList("policyCommissionId", "Dealer Commission Retention %", "Remark", "Output Status", "Output Remark"));
            csvAssert.assertRow(data, 1, Arrays.asList(pcid, "35.0", "Testing", "FAILURE", "dealer payout deviations is only allowed for dealerPayoutSplit parentSubType"));
            LogUtils.info("Validated Column Present in NonSplitPartner_SplitDeviations Output File as Expected");
        } else if (fileName.equalsIgnoreCase("Adjustments.csv")) {
            LogUtils.info("Validating Column Present in Adjustments Output File as Expected");
            csvAssert.assertRow(data, 0, Arrays.asList("Recorded_At", "policyCommissionId", "policyDetailsId", "policyPaymentScheduleId", "DP Login Id", "Customer First Name", "Customer Last Name", "Booking/Issued Date", "Payment Cycle", "Case Status", "Channel Type", "Product category", "Product subcategory", "Vehicle type", "Vehicle subtype", "Business Type", "Plan name", "Insurer", "product name", "Registration no.", "Policy No.", "Master Policy No.", "Points", "Remark", "Output Status", "Output Remark"));
            csvAssert.assertRow(data, 1, Arrays.asList("18-Dec-2025", "692fce37e55fd35ac2d06b22", "MIS_AHSK7OHON57", "", "63b54bb9ee10470001250bb6", "Sanity", "Test", "03-Dec-2025", "202512C2", "Issued", "Partner", "Two Wheeler", "", "TW", "Bike", "New", "", "Bajaj Allianz", "Comprehensive", "MH-01-WW-2177", "'DNNDNDndjjsjss", "", "18.0", "AdjustmentsTest", "SUCCESS", ""));
            LogUtils.info("Validated Column Present in Adjustments Output File as Expected");
        } else if (fileName.equalsIgnoreCase("AdjustmentsInvalid.csv")) {
            LogUtils.info("Validating Column Present in AdjustmentsInvalid Output File as Expected");
            csvAssert.assertRow(data, 0, Arrays.asList("Recorded_At", "policyCommissionId", "policyDetailsId", "policyPaymentScheduleId", "DP Login Id", "Customer First Name", "Customer Last Name", "Booking/Issued Date", "Payment Cycle", "Case Status", "Channel Type", "Product category", "Product subcategory", "Vehicle type", "Vehicle subtype", "Business Type", "Plan name", "Insurer", "product name", "Registration no.", "Policy No.", "Master Policy No.", "Points", "Remark", "Output Status", "Output Remark"));
            csvAssert.assertRow(data, 1, Arrays.asList("18-Dec-2025", "6901f22709147c665f37c18c", "MIS_MHRGXWDYUJ2", "", "6290f07ed35ae3058a14b495", "UTKARSH VIKAS", "CHANDEL", "29-Oct-2025", "202512C2", "Issued", "Partner", "Motor", "", "Car", "Car", "New", "", "Reliance", "Comprehensive", "NEW", "'920222523740005378", "", "18.0", "InvalidAdjustmentsTest", "FAILURE", "`commissionId` does not exist / Payment not yet finalised."));
            LogUtils.info("Validated Column Present in AdjustmentsInvalid Output File as Expected");
        } else if (fileName.equalsIgnoreCase("PayoutQC_DONE_misQC.csv")) {
            LogUtils.info("Validating Column Present in PayoutQC Output File as Expected");
            csvAssert.assertRow(data, 0, Arrays.asList("policyCommissionId", "policyDetailsId", "policyPaymentScheduleId", "commissionSource", "deviationType", "Payout Policy Type", "version", "Policy No.", "Master Policy No.", "Application No.", "MIS / Data entry owner", "PI CreatedBy", "Booking/Issued Date", "Booking/Issued Month", "Product category", "Product subcategory", "Vehicle type", "Vehicle subtype", "CarrierType", "Intermediary Name", "DP No", "DP Login Id", "Relationship Manager", "City Head", "Circle Head", "Business Head", "Super Franchisees", "Super Franchisees ID", "Super Franchisees DP No", "DP Level", "Partner Parent Subtype", "Parent DP Name", "Parent DP Id", "Parent DP No", "Parent DP Level", "DP Branch Location", "Business Type", "Customer Title", "Customer First Name", "Customer Last Name", "Registration no.", "RTO code", "Make", "Model", "Variant", "Cubic Capacity", "Fuel Type", "Mfg. Year", "GVW/Tonnage", "Seating capacity", "ZeroDepreciation", "Discount Percentage", "Case Status", "Case Sub Status", "Insurer", "Plan type", "Plan name", "Plan Variant", "product name", "Payment Frequency", "Upcoming Payment Due Date", "Recent Payment Paid Date", "Installment UniqueId", "Policy term", "Policy premium term", "Risk Start Date", "Risk End Date", "odRiskStartDate", "odRiskEndDate", "tpRiskStartDate", "tpRiskEndDate", "multiyear", "IDV/SA", "TM Plan ID", "Cover Type", "Option Name", "Age", "Registration City", "Registration State", "Renewal Year", "creation source", "Creation Date", "Comments", "Basic OD Premium", "Net OD Premium", "Basic TP Premium", "TP Premium", "Net Premium", "Annualised Net Premium", "CPA Premium", "NCB", "Record Status", "Insurer Record Status", "Channel Type", "manualQCStatus", "irdaRuleId", "irdaEarningsBaseODB", "irdaEarningsBaseODR", "irdaEarningsBaseTP", "irdaEarningsBaseNP", "irdaEarningsBaseABS", "irdaEarningsTotalOD", "irdaEarningsTotalTP", "irdaEarningsTotalNP", "irdaEarningsTotalABS", "irdaPayoutsBaseODB", "irdaPayoutsBaseODR", "irdaPayoutsBaseTP", "irdaPayoutsBaseNP", "irdaPayoutsBaseABS", "irdaPayoutsTotalOD", "irdaPayoutsTotalTP", "irdaPayoutsTotalNP", "irdaPayoutsTotalABS", "insurerRuleId", "insurerEarningsBaseODB", "insurerEarningsBaseODR", "insurerEarningsBaseTP", "insurerEarningsBaseNP", "insurerEarningsBaseABS", "insurerEarningsTotalOD", "insurerEarningsTotalTP", "insurerEarningsTotalNP", "insurerEarningsTotalABS", "insurerPayoutsBaseODB", "insurerPayoutsBaseODR", "insurerPayoutsBaseTP", "insurerPayoutsBaseNP", "insurerPayoutsBaseABS", "insurerPayoutsTotalOD", "insurerPayoutsTotalTP", "insurerPayoutsTotalNP", "insurerPayoutsTotalABS", "earningsBaseODB", "earningsBaseODR", "earningsBaseTP", "earningsBaseNP", "earningsBaseABS", "earningsTotalOD", "earningsTotalTP", "earningsTotalNP", "earningsTotalABS", "earningsMasterTotal", "payoutsBaseODB", "payoutsBaseODR", "payoutsBaseTP", "payoutsBaseNP", "payoutsBaseABS", "payoutsTotalOD", "payoutsTotalTP", "payoutsTotalNP", "payoutsTotalABS", "payoutsMasterTotal", "conflicts", "Payout AutoQC", "Payout QC Done By", "Payout QC", "First Payout QC Date", "Final Payout QC Date", "Post QC Review Status", "Post QC Review Done By", "Post QC Review Date", "Payout Type", "Payout IRDA ODB %", "Payout IRDA ODR %", "Payout IRDA TP %", "Payout IRDA NP %", "Payout IRDA ABS", "IRDA Volume Rewards %", "IRDA Payout Total", "IRDA Rewards Total", "Insurer Payout OD %", "Insurer Payout TP %", "Insurer Payout NP %", "Insurer Payout ABS", "Insurer Applicable Payout %", "Insurer Volume Rewards %", "Insurer Payout Total", "Insurer Rewards Total", "Final Payout Total", "Partner cohort", "Partner club", "Partner slab", "QuickPay Eligible", "Skip Differential deductions", "Deduction% for differential", "Deduction% for QP", "Deduction% for campaign", "Deduction absolute for differential", "Deduction absolute for QP", "Deduction absolute for campaign", "Initial Effective Percentages", "Initial Effective Payout absolute", "Effective Percentages", "Effective Payout absolute", "PC_CreatedAt", "Payable_Partner_Id", "Remark", "Payouts %", "Payouts absolute", "Payouts Deviation %", "Payouts Deviation absolute", "Output Status", "Output Remark"));
            csvAssert.assertCell(data, 1, 0, policyCommissionId);
            csvAssert.assertCell(data, 1, 1, policyDetailsId);
            csvAssert.assertCell(data, 1, 202, "SUCCESS");
            LogUtils.info("Validated Column Present in PayoutQC Output File as Expected");
        } else if (fileName.equalsIgnoreCase("PayoutQC_PENDING_misQC.csv")) {
            LogUtils.info("Validating Column Present in PayoutQC Output File as Expected");
            csvAssert.assertRow(data, 0, Arrays.asList("policyCommissionId", "policyDetailsId", "policyPaymentScheduleId", "commissionSource", "deviationType", "Payout Policy Type", "version", "Policy No.", "Master Policy No.", "Application No.", "MIS / Data entry owner", "PI CreatedBy", "Booking/Issued Date", "Booking/Issued Month", "Product category", "Product subcategory", "Vehicle type", "Vehicle subtype", "CarrierType", "Intermediary Name", "DP No", "DP Login Id", "Relationship Manager", "City Head", "Circle Head", "Business Head", "Super Franchisees", "Super Franchisees ID", "Super Franchisees DP No", "DP Level", "Partner Parent Subtype", "Parent DP Name", "Parent DP Id", "Parent DP No", "Parent DP Level", "DP Branch Location", "Business Type", "Customer Title", "Customer First Name", "Customer Last Name", "Registration no.", "RTO code", "Make", "Model", "Variant", "Cubic Capacity", "Fuel Type", "Mfg. Year", "GVW/Tonnage", "Seating capacity", "ZeroDepreciation", "Discount Percentage", "Case Status", "Case Sub Status", "Insurer", "Plan type", "Plan name", "Plan Variant", "product name", "Payment Frequency", "Upcoming Payment Due Date", "Recent Payment Paid Date", "Installment UniqueId", "Policy term", "Policy premium term", "Risk Start Date", "Risk End Date", "odRiskStartDate", "odRiskEndDate", "tpRiskStartDate", "tpRiskEndDate", "multiyear", "IDV/SA", "TM Plan ID", "Cover Type", "Option Name", "Age", "Registration City", "Registration State", "Renewal Year", "creation source", "Creation Date", "Comments", "Basic OD Premium", "Net OD Premium", "Basic TP Premium", "TP Premium", "Net Premium", "Annualised Net Premium", "CPA Premium", "NCB", "Record Status", "Insurer Record Status", "Channel Type", "manualQCStatus", "irdaRuleId", "irdaEarningsBaseODB", "irdaEarningsBaseODR", "irdaEarningsBaseTP", "irdaEarningsBaseNP", "irdaEarningsBaseABS", "irdaEarningsTotalOD", "irdaEarningsTotalTP", "irdaEarningsTotalNP", "irdaEarningsTotalABS", "irdaPayoutsBaseODB", "irdaPayoutsBaseODR", "irdaPayoutsBaseTP", "irdaPayoutsBaseNP", "irdaPayoutsBaseABS", "irdaPayoutsTotalOD", "irdaPayoutsTotalTP", "irdaPayoutsTotalNP", "irdaPayoutsTotalABS", "insurerRuleId", "insurerEarningsBaseODB", "insurerEarningsBaseODR", "insurerEarningsBaseTP", "insurerEarningsBaseNP", "insurerEarningsBaseABS", "insurerEarningsTotalOD", "insurerEarningsTotalTP", "insurerEarningsTotalNP", "insurerEarningsTotalABS", "insurerPayoutsBaseODB", "insurerPayoutsBaseODR", "insurerPayoutsBaseTP", "insurerPayoutsBaseNP", "insurerPayoutsBaseABS", "insurerPayoutsTotalOD", "insurerPayoutsTotalTP", "insurerPayoutsTotalNP", "insurerPayoutsTotalABS", "earningsBaseODB", "earningsBaseODR", "earningsBaseTP", "earningsBaseNP", "earningsBaseABS", "earningsTotalOD", "earningsTotalTP", "earningsTotalNP", "earningsTotalABS", "earningsMasterTotal", "payoutsBaseODB", "payoutsBaseODR", "payoutsBaseTP", "payoutsBaseNP", "payoutsBaseABS", "payoutsTotalOD", "payoutsTotalTP", "payoutsTotalNP", "payoutsTotalABS", "payoutsMasterTotal", "conflicts", "Payout AutoQC", "Payout QC Done By", "Payout QC", "First Payout QC Date", "Final Payout QC Date", "Post QC Review Status", "Post QC Review Done By", "Post QC Review Date", "Payout Type", "Payout IRDA ODB %", "Payout IRDA ODR %", "Payout IRDA TP %", "Payout IRDA NP %", "Payout IRDA ABS", "IRDA Volume Rewards %", "IRDA Payout Total", "IRDA Rewards Total", "Insurer Payout OD %", "Insurer Payout TP %", "Insurer Payout NP %", "Insurer Payout ABS", "Insurer Applicable Payout %", "Insurer Volume Rewards %", "Insurer Payout Total", "Insurer Rewards Total", "Final Payout Total", "Partner cohort", "Partner club", "Partner slab", "QuickPay Eligible", "Skip Differential deductions", "Deduction% for differential", "Deduction% for QP", "Deduction% for campaign", "Deduction absolute for differential", "Deduction absolute for QP", "Deduction absolute for campaign", "Initial Effective Percentages", "Initial Effective Payout absolute", "Effective Percentages", "Effective Payout absolute", "PC_CreatedAt", "Payable_Partner_Id", "Remark", "Payouts %", "Payouts absolute", "Payouts Deviation %", "Payouts Deviation absolute", "Output Status", "Output Remark"));
            csvAssert.assertCell(data, 1, 0, policyCommissionId);
            csvAssert.assertCell(data, 1, 1, policyDetailsId);
            csvAssert.assertCell(data, 1, 202, "FAILURE");
            csvAssert.assertCell(data, 1, 203, "MIS QC is not done");
            LogUtils.info("Validated Column Present in PayoutQC Output File as Expected");
        } else if (fileName.equalsIgnoreCase("InsurerRewards.csv")) {
            LogUtils.info("Validating Column Present in InsurerRewards Output File as Expected");

            LogUtils.info("Validated Column Present in InsurerRewards Output File as Expected");
        } else if (fileName.equalsIgnoreCase("PartnerLevelActivity.csv")) {
            LogUtils.info("Validating Column Present in PartnerLevelActivity Output File as Expected");

            LogUtils.info("Validated Column Present in PartnerLevelActivity Output File as Expected");
        } else if (fileName.equalsIgnoreCase("PostQC_Review_DONE_PayoutQC")) {
            LogUtils.info("Validating Column Present in PostQC_Review Output File as Expected");
            csvAssert.assertRow(data, 0, Arrays.asList("Ledger_Id", "Post-QC Review", "Output Status", "Output Remark"));
            csvAssert.assertCell(data, 1, 0, ledger_Id);
            csvAssert.assertCell(data, 1, 1, "Pass");
            csvAssert.assertCell(data, 1, 2, "SUCCESS");
            LogUtils.info("Validated Column Present in PostQC_Review Output File as Expected");
        } else if (fileName.equalsIgnoreCase("Reupload_PostQC_Review_DONE_PayoutQC")) {
            LogUtils.info("Validating Column Present in PostQC_Review Output File as Expected");
            csvAssert.assertRow(data, 0, Arrays.asList("Ledger_Id", "Post-QC Review", "Output Status", "Output Remark"));
            csvAssert.assertCell(data, 1, 0, ledger_Id);
            csvAssert.assertCell(data, 1, 1, "Pass");
            csvAssert.assertCell(data, 1, 2, "FAILURE");
            csvAssert.assertCell(data, 1, 3, "invalid Ledger_Id or QC not done or review already done");
            LogUtils.info("Validated Column Present in PostQC_Review Output File as Expected");
        } else if (fileName.equalsIgnoreCase("PostQC_Review_PENDING_PayoutQC")) {
            LogUtils.info("Validating Column Present in PostQC_Review Output File as Expected");
            csvAssert.assertRow(data, 0, Arrays.asList("Ledger_Id", "Post-QC Review", "Output Status", "Output Remark"));
            csvAssert.assertCell(data, 1, 0, ledger_Id);
            csvAssert.assertCell(data, 1, 1, "Pass");
            csvAssert.assertCell(data, 1, 2, "FAILURE");
            csvAssert.assertCell(data, 1, 3, "invalid Ledger_Id or QC not done or review already done");
            LogUtils.info("Validated Column Present in PostQC_Review Output File as Expected");
        }
    }

    public void verifyVia_BulkSearch(String fileName) {
        TestUtil.click(cmp.quickSearchSectnBtn, "Quick Search Button Clicked");
        TestUtil.click(cmp.bulkSearchBtn, "Bulk Search Button Clicked");
        TestUtil.click(cmp.uploadBtn, "Upload Button Clicked");

        if (fileName.equalsIgnoreCase("ManualUploadBulkSearch.csv")) {
            driver.findElement(By.xpath("//input[@type='file']")).sendKeys("//Users//rahulpatil//Documents//Payouts Files//UploadPayouts//" + fileName + "");
            LogUtils.info(fileName + " :File Uploaded");
        } else if (fileName.equalsIgnoreCase("ManualCorrectionBulkSearch.csv")) {
            driver.findElement(By.xpath("//input[@type='file']")).sendKeys("//Users//rahulpatil//Documents//Payouts Files//UploadPayouts//" + fileName + "");
            LogUtils.info(fileName + " :File Uploaded");
        }
        WebCommands.staticSleep(1000);
        TestUtil.click(cmp.searchBtn, "Search Button Clicked");
        TestUtil.click(cmp.downloadBtn, "Download Button Clicked");
        cmp.commentEnter();
        TestUtil.click(cmp.resultDownloadBtn, "Result Download Button Clicked");
        WebCommands.staticSleep(1000);
        cmp.bulkSearchFileAssert(fileName);
    }

}