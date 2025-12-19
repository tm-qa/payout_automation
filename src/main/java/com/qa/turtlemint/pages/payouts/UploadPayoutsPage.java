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
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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


    private String mID;
    private String pcid;
    public String CommissionId;
    JavascriptExecutor js = (JavascriptExecutor) driver;
    DownloadPayoutsCyclePage dpc = new DownloadPayoutsCyclePage();
    CycleMovePage cmp = new CycleMovePage();
    QuickSearchPage qsp = new QuickSearchPage();
    ninja ninj = new ninja();
    DBAssertion dbAssertion = new DBAssertion();

    public UploadPayoutsPage() {
        PageFactory.initElements(driver, this);
    }

    public void manualUpload(String fileName, String paymentCycle) throws InterruptedException {
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
        validateOutputFile(fileName, "","");
    }

    public void manualCorrection(String fileName) throws InterruptedException {
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
        validateOutputFile(fileName,"","");
    }

    public void deviations(String deviationType, String pcid) throws InterruptedException {
        TestUtil.click(uploadPayoutBtn, "Upload Payouts Button Clicked");
        Assert.assertEquals(uploadPayoutTitle.getText(), "Upload Payouts");
        TestUtil.click(uploadPayoutDrpdwn, "Upload Payouts dropdown Clicked");
        WebCommands.staticSleep(1000);
        TestUtil.getScreenShot();
        if (deviationType.equalsIgnoreCase("INCORRECT_RULES")||deviationType.equalsIgnoreCase("SPECIAL_REQUEST")||deviationType.equalsIgnoreCase("NOMINAL_DEVIATION")||deviationType.equalsIgnoreCase("INCORRECT_RULESS")){
            TestUtil.click(deviationBtn, "Deviations Clicked");
        } else if (deviationType.equalsIgnoreCase("SPLIT_DEVIATIONS")||(deviationType.equalsIgnoreCase("NonSplitPartner_SPLIT_DEVIATIONS"))) {
            TestUtil.click(splitDeviationBtn, "Split Deviations Clicked");
        }
        TestUtil.click(templateDownloadBtn, "Deviations Template download");
        WebCommands.staticSleep(1000);
        validateDownloadTemplateFile(deviationType);
        WebCommands.staticSleep(1000);
            if (deviationType.equalsIgnoreCase("INCORRECT_RULES")) {
                writeDeviationUploadCSV(pcid,deviationType);
            }
            else if (deviationType.equalsIgnoreCase("SPECIAL_REQUEST")) {
                writeDeviationUploadCSV(pcid,deviationType);
            }
            else if (deviationType.equalsIgnoreCase("NOMINAL_DEVIATION")) {
                writeDeviationUploadCSV(pcid,deviationType);
            }
            else if (deviationType.equalsIgnoreCase("INCORRECT_RULESS")) {
                writeDeviationUploadCSV(pcid,deviationType);
            }
            else if (deviationType.equalsIgnoreCase("SPLIT_DEVIATIONS")||(deviationType.equalsIgnoreCase("NonSplitPartner_SPLIT_DEVIATIONS"))) {
                writeDeviationUploadCSV(pcid,deviationType);
            }
        cmp.commentEnter();
        TestUtil.click(finalUploadBtn, "Clicked on upload button");
        WebCommands.staticSleep(1000);
        UploadPayoutsHistoryAssertion(deviationType);
        TestUtil.click(outputFileBtn, "Download output file");
        WebCommands.staticSleep(1000);
        if (deviationType.equalsIgnoreCase("INCORRECT_RULES")||deviationType.equalsIgnoreCase("SPECIAL_REQUEST")||deviationType.equalsIgnoreCase("NOMINAL_DEVIATION")||deviationType.equalsIgnoreCase("INCORRECT_RULESS")) {
            validateOutputFile("Deviations", deviationType, pcid);
        } else if (deviationType.equalsIgnoreCase("SPLIT_DEVIATIONS"))  {
            validateOutputFile("SPLIT_DEVIATIONS", deviationType, pcid);
        } else if (deviationType.equalsIgnoreCase("NonSplitPartner_SPLIT_DEVIATIONS"))  {
            validateOutputFile("NonSplitPartner_SPLIT_DEVIATIONS", deviationType, pcid);
        }
    }

    public void uploadDeviation(String deviationType) throws Exception {
        String getPcid = getPcid();
        System.out.println("commision ID :"+getPcid);
        if(deviationType.equalsIgnoreCase("INCORRECT_RULES")){
            System.out.println("INCORRECT_RULES BLOCK");
            deviations("INCORRECT_RULES",getPcid);
        } else if (deviationType.equalsIgnoreCase("SPECIAL_REQUEST")) {
            System.out.println("SPECIAL_REQUEST BLOCK");
            deviations("SPECIAL_REQUEST",getPcid);
        } else if (deviationType.equalsIgnoreCase("NOMINAL_DEVIATION")) {
            System.out.println("NOMINAL_DEVIATION BLOCK");
            deviations("NOMINAL_DEVIATION",getPcid);
        } else {
            System.out.println("else BLOCK executed");
            deviations("INCORRECT_RULESS",getPcid);
        }
    }

    public void uploadSplitDeviations(String deviationType) throws Exception {
        String getPcid = getPcid();
        System.out.println("commision ID :"+getPcid);
        if (deviationType.equalsIgnoreCase("SPLIT_DEVIATIONS")){
            System.out.println("SPLIT_DEVIATIONS BLOCK");
            deviations("SPLIT_DEVIATIONS",getPcid);
        } else {
            System.out.println("NonSplitPartner SPLIT_DEVIATIONS BLOCK");
            deviations("NonSplitPartner_SPLIT_DEVIATIONS",getPcid);
        }
    }

    public void selectAdjustments(){
        TestUtil.click(dpc.ledgerBtn,"");
        TestUtil.click(uploadPayoutBtn, "Upload Payouts Button Clicked");
    }

    public void uploadAdjustment(String fileName, String paymentCycle) throws Exception {
        TestUtil.click(dpc.ledgerBtn,"");
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

    public void validate_MIS_EntryAtPayouts() throws Exception{
        WebCommands.staticSleep(5000); //uncomment me
        js.executeScript("arguments[0].scrollIntoView(true);", misID); //uncomment me
        WebCommands.staticSleep(2000); //uncomment me
        String mID = misID.getAttribute("value"); //uncomment me

//        String mID = "MIS_AHSUPFGETCV"; // new added

        this.mID=mID;
        System.out.println("MIS ID : " + mID);
        WebCommands.staticSleep(5000);
        driver.navigate().to("https://ninja.sanity.turtle-feature.com");
        TestUtil.click(payouts,"");
        WebCommands.staticSleep(2000);
        TestUtil.click(dpc.ledgerBtn,"");
        WebCommands.staticSleep(2000);
        TestUtil.click(cmp.quickSearchSectnBtn,"Clicked on Quick Search");
        qsp.misIdTxtbox.sendKeys(mID);
        WebCommands.staticSleep(2000);
        TestUtil.click(qsp.searchButton,"");
        WebCommands.staticSleep(3000);
        org.testng.Assert.assertEquals(policyDetailID.getText(),mID);
        TestUtil.click(cmp.downloadBtn, "Download button clicked");
        cmp.commentEnter();
        TestUtil.click(cmp.resultDownloadBtn, "Download button clicked to download result file");
        TestUtil.getFullPageScreenShot();
        validateDeviationQuickSearchResult("Policy_Punch");
        //      Validate entry in DB Before Uploading Deviation in LedgerEntity collection
        dbAssertion.deviation_LE_DB_Validation(mID);
        CommissionId = dbAssertion.policyComisionId;
        String pcid = CommissionId;
        this.pcid=pcid;
    }

    public String getPcid(){
        return pcid;
    }

    public String getmID(){
        return mID;
    }

    public void downloadDeviationQuickSearchResult(){
        String misID = getmID();
        QuickSearchPage qsp = new QuickSearchPage();
        TestUtil.click(cmp.quickSearchSectnBtn,"Quick Search Selected");
        TestUtil.sendKeys(qsp.misIdTxtbox, misID, "Valid MIS ID Entered");
        TestUtil.click(qsp.searchButton, "Search Button Clicked");
        TestUtil.click(cmp.downloadBtn, "Download button clicked");
        cmp.commentEnter();
        TestUtil.click(cmp.resultDownloadBtn, "Download button clicked to download result file");
        WebCommands.staticSleep(1000);
        TestUtil.getScreenShot();
    }

    public void downloadAdjustmentsQuickSearchResult(){
//        TestUtil.click(dpc.ledgerBtn,"");
        TestUtil.click(cmp.quickSearchSectnBtn,"Quick Search Selected");
        TestUtil.sendKeys(qsp.misIdTxtbox, "MIS_AHSK7OHON57", "MIS ID Entered");
        TestUtil.click(qsp.searchButton, "Search Button Clicked");
        TestUtil.click(cmp.downloadBtn, "Download button clicked");
        cmp.commentEnter();
        TestUtil.click(cmp.resultDownloadBtn, "Download button clicked to download result file");
        WebCommands.staticSleep(1000);
        TestUtil.getScreenShot();
    }

    public void validateDeviationQuickSearchResult(String deviationType){
        try {
//            String downloadDirectory = "//Users//rahulpatil//Downloads";  // Local
            String downloadDirectory = "/var/lib/jenkins/workspace/payout"; // Jenkins
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

                    if (deviationType.equalsIgnoreCase("INCORRECT_RULES")) {
                        LogUtils.info("Verifying CSV Result For Uploaded INCORRECT_RULES Deviation");
                        csvAssert.assertCell(data,1,0,this.getPcid()); //policyCommissionId
                        csvAssert.assertCell(data,1,1,this.getmID()); //policyDetailsId
                        csvAssert.assertCell(data,1,3,"MANUAL_DEVIATION"); //commissionSource
                        csvAssert.assertCell(data,1,4,"INCORRECT_RULES"); //deviationType
                        csvAssert.assertCell(data,1,100,"DONE"); //Payout QC
                        csvAssert.assertCell(data,1,107,"0.8xN"); //Payouts %
                        csvAssert.assertCell(data,1,109,"3.1xN"); //Payouts Deviation %
                        csvAssert.assertCell(data,1,111,"1860.0");//Final Payout Total
                        csvAssert.assertCell(data,1,123,"3.1xN"); //Initial Effective Percentages
                        csvAssert.assertCell(data,1,125,"3.1xN"); //Effective Percentages
                        csvAssert.assertCell(data,1,149,"1860.0"); //Points
                        csvAssert.assertCell(data,1,151,"1860.0"); //NetPoints
                        LogUtils.info("Validated CSV Result For Uploaded INCORRECT_RULES Deviation");
                    }
                    else if(deviationType.equalsIgnoreCase("SPECIAL_REQUEST")) {
                        LogUtils.info("Verifying CSV Result For Uploaded SPECIAL_REQUEST Deviation");
                        csvAssert.assertCell(data,1,0,this.getPcid()); //policyCommissionId
                        csvAssert.assertCell(data,1,1,this.getmID()); //policyDetailsId
                        csvAssert.assertCell(data,1,3,"MANUAL_DEVIATION"); //commissionSource
                        csvAssert.assertCell(data,1,4,"SPECIAL_REQUEST"); //deviationType
                        csvAssert.assertCell(data,1,100,"DONE"); //Payout QC
                        csvAssert.assertCell(data,1,107,"0.8xN"); //Payouts %
                        csvAssert.assertCell(data,1,109,"3.1xN"); //Payouts Deviation %
                        csvAssert.assertCell(data,1,111,"1860.0");//Final Payout Total
                        csvAssert.assertCell(data,1,123,"3.1xN"); //Initial Effective Percentages
                        csvAssert.assertCell(data,1,125,"3.1xN"); //Effective Percentages
                        csvAssert.assertCell(data,1,149,"1860.0"); //Points
                        csvAssert.assertCell(data,1,151,"1860.0"); //NetPoints
                        LogUtils.info("Validated CSV Result For Uploaded SPECIAL_REQUEST Deviation");
                    }
                    else if(deviationType.equalsIgnoreCase("NOMINAL_DEVIATION")) {
                        LogUtils.info("Verifying CSV Result For Uploaded NOMINAL_DEVIATION Deviation");
                        csvAssert.assertCell(data,1,0,this.getPcid()); //policyCommissionId
                        csvAssert.assertCell(data,1,1,this.getmID()); //policyDetailsId
                        csvAssert.assertCell(data,1,3,"MANUAL_DEVIATION"); //commissionSource
                        csvAssert.assertCell(data,1,4,"NOMINAL_DEVIATION"); //deviationType
                        csvAssert.assertCell(data,1,100,"DONE"); //Payout QC
                        csvAssert.assertCell(data,1,107,"0.8xN"); //Payouts %
                        csvAssert.assertCell(data,1,109,"3.1xN"); //Payouts Deviation %
                        csvAssert.assertCell(data,1,111,"1860.0");//Final Payout Total
                        csvAssert.assertCell(data,1,123,"3.1xN"); //Initial Effective Percentages
                        csvAssert.assertCell(data,1,125,"3.1xN"); //Effective Percentages
                        csvAssert.assertCell(data,1,149,"1860.0"); //Points
                        csvAssert.assertCell(data,1,151,"1860.0"); //NetPoints
                        LogUtils.info("Validated CSV Result For Uploaded NOMINAL_DEVIATION Deviation");
                    }
                    else if(deviationType.equalsIgnoreCase("SPLIT_DEVIATIONS")) {
                        LogUtils.info("Verifying CSV Result For Uploaded SPLIT_DEVIATIONS");
                        csvAssert.assertCell(data,1,0,this.getPcid()); //policyCommissionId
                        csvAssert.assertCell(data,1,1,this.getmID()); //policyDetailsId
                        csvAssert.assertCell(data,1,3,"MANUAL_DEVIATION"); //commissionSource
                        csvAssert.assertCell(data,1,4,"NOMINAL_DEVIATION"); //deviationType
                        csvAssert.assertCell(data,1,100,"DONE"); //Payout QC
                        csvAssert.assertCell(data,1,107,"0.8xN"); //Payouts %
                        csvAssert.assertCell(data,1,109,"3.1xN"); //Payouts Deviation %
                        csvAssert.assertCell(data,1,111,"1860.0");//Final Payout Total
                        csvAssert.assertCell(data,1,123,"3.1xN"); //Initial Effective Percentages
                        csvAssert.assertCell(data,1,125,"3.1xN"); //Effective Percentages
                        csvAssert.assertCell(data,1,149,"1860.0"); //Points
                        csvAssert.assertCell(data,1,151,"1860.0"); //NetPoints
                        LogUtils.info("Validated CSV Result For Uploaded SPLIT_DEVIATIONS Deviation");
                    }
                    else if(deviationType.equalsIgnoreCase("Adjustments")) {
                        LogUtils.info("Verifying CSV Result For Uploaded Adjustments");
                        csvAssert.assertCell(data,1,0,"692fce37e55fd35ac2d06b22"); //policyCommissionId
                            LogUtils.info("policyCommissionId : " + data.get(1)[0]);
                        csvAssert.assertCell(data,1,1,"MIS_AHSK7OHON57"); //policyDetailsId
                            LogUtils.info("policyDetailsId : " + data.get(1)[1]);
                        csvAssert.assertCell(data,1,3,"SYSTEM_GENERATED"); //commissionSource
                            LogUtils.info("commissionSource : " + data.get(1)[3]);
                        csvAssert.assertCell(data,1,132,"202512C2"); //Payment Cycle
                            LogUtils.info("Payment Cycle : " + data.get(1)[132]);
                        csvAssert.assertCell(data,1,135,"ADJUSTMENT"); //Ledger_Entity_Type
                            LogUtils.info("Ledger_Entity_Type : " + data.get(1)[135]);
                        csvAssert.assertCell(data,1,137,"Discrepancy"); //Payout Disbursal Type
                            LogUtils.info("Payout Disbursal Type : " + data.get(1)[137]);
                        csvAssert.assertCell(data,1,148,"AdjustmentsTest"); //Ledger_Remarks
                            LogUtils.info("Ledger_Remarks : " + data.get(1)[148]);
                        csvAssert.assertCell(data,1,149,"18.0");//Points
                            LogUtils.info("Points : " + data.get(1)[149]);
                        csvAssert.assertCell(data,1,151,"0.0"); //NetPoints
                            LogUtils.info("NetPoints : " + data.get(1)[151]);
                        csvAssert.assertCell(data,1,152,"PENDING"); //Ledger_Status
                            LogUtils.info("Ledger_Status : " + data.get(1)[152]);
                        LogUtils.info("Validated CSV Result For Uploaded Adjustments");
                    }
                    TestUtil.click(qsp.resetButton, "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("An error occurred during the CSV file validation.", e);
        }
    }

    public void writeDeviationUploadCSV(String pcid, String deviationType){
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
                    System.out.println("Downloaded file path: " + mostRecentFile.getAbsolutePath());
                    try (FileInputStream excelFile = new FileInputStream(mostRecentFile)) {
                        WebCommands.staticSleep(2000);
                        CSVReader readcsv = new CSVReader(new FileReader(mostRecentFile));
                        // this will load content into list
                        List<String[]> li=readcsv.readAll();
                        // create Iterator reference
                        Iterator<String[]> i1= li.iterator();
                        // Iterate all values
                        while(i1.hasNext()) {
                            String[] str = i1.next();
                            for (int i = 0; i < str.length; i++) {
                                System.out.println(" " + str[i]);
                            }
                            CSVWriter writer = new CSVWriter(new FileWriter(mostRecentFile, true)); // true for append mode
                            if (deviationType.equals("INCORRECT_RULES")) {
                                System.out.println("INCORRECT_RULES BLOCK 3");
                                String[] data = {pcid, "INCORRECT_RULES", "FALSE", "0", "0", "0", "0", "0", "0", "0", "31", "0", "Testing"};
                                System.out.println("Entered PolicyCommistionID : " + pcid);
                                writer.writeNext(data);
                                writer.close();
                                System.out.println("Data written to CSV successfully!");
                            } else if (deviationType.equals("SPECIAL_REQUEST")) {
                                System.out.println("SPECIAL_REQUEST BLOCK 3");
                                String[] data = {pcid, "SPECIAL_REQUEST", "FALSE", "0", "0", "0", "0", "0", "0", "0", "31", "0", "Testing"};

                                System.out.println("Entered PolicyCommistionID : " + pcid);
                                writer.writeNext(data);
                                writer.close();
                                System.out.println("Data written to CSV successfully!");
                            } else if (deviationType.equals("NOMINAL_DEVIATION")) {
                                String[] data = {pcid, "NOMINAL_DEVIATION", "FALSE", "0", "0", "0", "0", "0", "0", "0", "31", "0", "Testing"};

                                System.out.println("Entered PolicyCommistionID : " + pcid);
                                writer.writeNext(data);
                                writer.close();
                                System.out.println("Data written to CSV successfully!");
                            } else if (deviationType.equals("INCORRECT_RULESS")) {
                                String[] data = {pcid, "INCORRECT_RULESS", "FALSE", "0", "0", "0", "0", "0", "0", "0", "31", "0", "Testing"};

                                System.out.println("Entered PolicyCommistionID : " + pcid);
                                writer.writeNext(data);
                                writer.close();
                                System.out.println("Data written to CSV successfully!");
                            } else if (deviationType.equals("SPLIT_DEVIATIONS")||(deviationType.equalsIgnoreCase("NonSplitPartner_SPLIT_DEVIATIONS"))) {
                                String[] data = {pcid, "35", "Testing"};

                                System.out.println("Entered PolicyCommistionID : " + pcid);
                                writer.writeNext(data);
                                writer.close();
                                System.out.println("Data written to CSV successfully!");
                            }
                            driver.findElement(By.xpath("//input[@type='file']")).sendKeys(mostRecentFile.getAbsolutePath());
                            Actions act = new Actions(driver);
                            WebCommands.staticSleep(2000);
                            if (deviationType.equalsIgnoreCase("INCORRECT_RULES")||deviationType.equalsIgnoreCase("SPECIAL_REQUEST")||deviationType.equalsIgnoreCase("NOMINAL_DEVIATION")||deviationType.equalsIgnoreCase("INCORRECT_RULESS")) {
                                act.moveToElement(deviationTypeDrpdwn).click().build().perform();
                            }
                            WebCommands.staticSleep(2000);
                            if(deviationType.equals("INCORRECT_RULES")){
                                act.moveToElement(IncretDeviationdeviation).click().build().perform();
                            } else if (deviationType.equals("SPECIAL_REQUEST")) {
                                act.moveToElement(SplReqstDeviationdeviation).click().build().perform();
                            } else if (deviationType.equals("NOMINAL_DEVIATION")) {
                                act.moveToElement(NominalDeviationdeviation).click().build().perform();
//                                TestUtil.clickByJS(NominalDeviationdeviation);
                            } else if (deviationType.equals("INCORRECT_RULESS")) {
                                act.moveToElement(IncretDeviationdeviation).click().build().perform();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("An error occurred during the XLSX file validation.", e);
        }
    }

    public void validateDownloadTemplateFile(String fileName) {
        try {
//            String downloadDirectory = "//Users//rahulpatil//Downloads";  // Local
            String downloadDirectory = "/var/lib/jenkins/workspace/payout"; // Jenkins
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
                    if (fileName.equalsIgnoreCase("ManualUpload.csv")) {
                        LogUtils.info("Validating Column Present in Manual Upload Template");
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
                    }
                    else if (fileName.equalsIgnoreCase("ManualCorrection.csv")) {
                        LogUtils.info("Validating Column Present in Manual Correction Template");
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
                    }
                    else if (fileName.equalsIgnoreCase("INCORRECT_RULES")||fileName.equalsIgnoreCase("SPECIAL_REQUEST")||fileName.equalsIgnoreCase("NOMINAL_DEVIATION")||fileName.equalsIgnoreCase("INCORRECT_RULESS")) {
                        LogUtils.info("Validating Column Present in Manual Deviations Template");
                        csvAssert.assertRow(data, 0, Arrays.asList("policyCommissionId", "Type", "SkipDifferentialDeductions",
                                "Payout IRDA ODB %", "Payout IRDA ODR %", "Payout IRDA TP %", "Payout IRDA NP %", "Payout IRDA ABS",
                                "Insurer Payout OD %", "Insurer Payout TP %", "Insurer Payout NP %", "Insurer Payout ABS", "Remark"));
                        LogUtils.info("Validated Column Present in Manual Deviations Template as Expected");
                    }
                    else if (fileName.equalsIgnoreCase("SPLIT_DEVIATIONS")||(fileName.equalsIgnoreCase("NonSplitPartner_SPLIT_DEVIATIONS"))) {
                        LogUtils.info("Validating Column Present in SplitDeviations Template");
                        csvAssert.assertRow(data, 0, Arrays.asList("policyCommissionId", "Dealer Commission Retention %", "Remark"));
                        LogUtils.info("Validated Column Present in SplitDeviations Template as Expected");
                    }
                    else if (fileName.equalsIgnoreCase("Adjustments.csv")||fileName.equalsIgnoreCase("AdjustmentsInvalid.csv")) {
                        LogUtils.info("Validating Column Present in Adjustments Template");
                        csvAssert.assertRow(data, 0, Arrays.asList("Recorded_At", "policyCommissionId", "policyDetailsId",
                                "policyPaymentScheduleId", "DP Login Id", "Customer First Name", "Customer Last Name",
                                "Booking/Issued Date", "Payment Cycle", "Case Status", "Channel Type", "Product category",
                                "Product subcategory", "Vehicle type", "Vehicle subtype", "Business Type", "Plan name", "Insurer",
                                "product name", "Registration no.", "Policy No.", "Master Policy No.", "Points", "Remark"));
                        LogUtils.info("Validated Column Present in Adjustments Template as Expected");
                    }
                    else if (fileName.equalsIgnoreCase("PayoutQC.csv")) {
                        LogUtils.info("Validating Column Present in PayoutQC Template");
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
                    }
                    else if (fileName.equalsIgnoreCase("InsurerRewards.csv")) {
                        LogUtils.info("Validating Column Present in InsurerRewards Template");
                        csvAssert.assertRow(data, 0, Arrays.asList("policyCommissionId", "Insurer Volume Rewards %", "Insurer Rewards Total", "Insurer Rewards Volume", "Remark"));
                        LogUtils.info("Validated Column Present in InsurerRewards Template as Expected");
                    }
                    else if (fileName.equalsIgnoreCase("PartnerLevelActivity.csv")) {
                        LogUtils.info("Validating Column Present in PartnerLevelActivity Template");
                        csvAssert.assertRow(data, 0, Arrays.asList("Recorded_At", "Partner Id", "Points", "Payment Cycle", "Type", "Remark"));
                        LogUtils.info("Validated Column Present in PartnerLevelActivity Template as Expected");
                    }
                    else if (fileName.equalsIgnoreCase("PostQC_Review.csv")) {
                        LogUtils.info("Validating Column Present in PostQC_Review Template");
                        csvAssert.assertRow(data, 0, Arrays.asList("Ledger_Id", "Post-QC Review"));
                        LogUtils.info("Validated Column Present in PostQC_Review Template as Expected");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("An error occurred during the CSV file validation.", e);
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
            if (!options.isEmpty()) {options.get(0).click();
                found = true;
                break;
            }
            currentScroll += clientHeight;
            js.executeScript("arguments[0].scrollTop = arguments[1];", scrollBox, currentScroll);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {}
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
        }
        else if (fileName.equalsIgnoreCase("ManualCorrection.csv")) {
            LogUtils.info("Payment Cycle : " + paymentCycle.getText());
            Assert.assertEquals(paymentCycle.getText(), "-");
            Assert.assertEquals("Manual Correction", uploadedType.getText());
            LogUtils.info("Success count is : " + successCount.getText());
            Assert.assertEquals(successCount.getText(), "5");
            LogUtils.info("Failure count is : " + failureCount.getText());
            Assert.assertEquals(failureCount.getText(), "1");
        }
        else if (fileName.equalsIgnoreCase("INCORRECT_RULES")||fileName.equalsIgnoreCase("SPECIAL_REQUEST")||fileName.equalsIgnoreCase("NOMINAL_DEVIATION")) {
            LogUtils.info("Payment Cycle : " + paymentCycle.getText());
            Assert.assertEquals(paymentCycle.getText(), "-");
            Assert.assertEquals("Deviations", uploadedType.getText());
            LogUtils.info("Success count is : " + successCount.getText());
            Assert.assertEquals(successCount.getText(), "1");
            LogUtils.info("Failure count is : " + failureCount.getText());
            Assert.assertEquals(failureCount.getText(), "0");
        }
        else if (fileName.equalsIgnoreCase("INCORRECT_RULESS")) {
            LogUtils.info("Payment Cycle : " + paymentCycle.getText());
            Assert.assertEquals(paymentCycle.getText(), "-");
            Assert.assertEquals("Deviations", uploadedType.getText());
            LogUtils.info("Success count is : " + successCount.getText());
            Assert.assertEquals(successCount.getText(), "0");
            LogUtils.info("Failure count is : " + failureCount.getText());
            Assert.assertEquals(failureCount.getText(), "1");
        }
        else if (fileName.equalsIgnoreCase("SPLIT_DEVIATIONS")) {
            LogUtils.info("Payment Cycle : " + paymentCycle.getText());
            Assert.assertEquals(paymentCycle.getText(), "-");
            Assert.assertEquals("Split Deviations", uploadedType.getText());
            LogUtils.info("Success count is : " + successCount.getText());
            Assert.assertEquals(successCount.getText(), "1");
            LogUtils.info("Failure count is : " + failureCount.getText());
            Assert.assertEquals(failureCount.getText(), "0");
        }
        else if (fileName.equalsIgnoreCase("NonSplitPartner_SPLIT_DEVIATIONS")) {
            LogUtils.info("Payment Cycle : " + paymentCycle.getText());
            Assert.assertEquals(paymentCycle.getText(), "-");
            Assert.assertEquals("Split Deviations", uploadedType.getText());
            LogUtils.info("Success count is : " + successCount.getText());
            Assert.assertEquals(successCount.getText(), "0");
            LogUtils.info("Failure count is : " + failureCount.getText());
            Assert.assertEquals(failureCount.getText(), "1");
        }
        else if (fileName.equalsIgnoreCase("Adjustments.csv")) {
            LogUtils.info("Payment Cycle : " + paymentCycle.getText());
            Assert.assertEquals(paymentCycle.getText(), "202512C2");
            Assert.assertEquals("Adjustments", uploadedType.getText());
            LogUtils.info("Success count is : " + successCount.getText());
            Assert.assertEquals(successCount.getText(), "1");
            LogUtils.info("Failure count is : " + failureCount.getText());
            Assert.assertEquals(failureCount.getText(), "0");
        }
        else if (fileName.equalsIgnoreCase("AdjustmentsInvalid.csv")) {
            LogUtils.info("Payment Cycle : " + paymentCycle.getText());
            Assert.assertEquals(paymentCycle.getText(), "202512C2");
            Assert.assertEquals("Adjustments", uploadedType.getText());
            LogUtils.info("Success count is : " + successCount.getText());
            Assert.assertEquals(successCount.getText(), "0");
            LogUtils.info("Failure count is : " + failureCount.getText());
            Assert.assertEquals(failureCount.getText(), "1");
        }
        Assert.assertTrue(outputFileBtn.isDisplayed());
    }

    public void validateOutputFile(String fileName, String deviationType, String pcid) {
        try {
//            String downloadDirectory = "//Users//rahulpatil//Downloads";  // Local
            String downloadDirectory = "/var/lib/jenkins/workspace/payout"; // Jenkins
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

                    if (fileName.equalsIgnoreCase("ManualUpload.csv")) {
                        LogUtils.info("Validating Manual Upload Output File");
                        csvAssert.assertCell(data,1,0,"MIS_MHA13RIGX7A4V1"); // Validate MIS ID in Output File
                        csvAssert.assertCell(data,2,0,"MIS_MHA13RIGX7A4V2");
                        csvAssert.assertCell(data,3,0,"MIS_MHA13RIGX7A4V3");
                        csvAssert.assertCell(data,4,0,"MIS_MHA13RIGX7A4V4");
                        csvAssert.assertCell(data,5,0,"MIS_MHA13RIGX7A4V5");
                        csvAssert.assertCell(data,6,0,"MIS_MHA13RIGX7A4V6");
                        csvAssert.assertCell(data,7,0,"MIS_MHA13RIGX7A4V7");
                        csvAssert.assertCell(data,1,6,"202512C2");           // Validate Payment Cycle in Output File
                        csvAssert.assertCell(data,2,6,"202512C2");
                        csvAssert.assertCell(data,3,6,"202512C2");
                        csvAssert.assertCell(data,4,6,"202512C2");
                        csvAssert.assertCell(data,5,6,"202512C2");
                        csvAssert.assertCell(data,6,6,"202512C1");
                        csvAssert.assertCell(data,7,6,"202512C1");
                        csvAssert.assertCell(data,1,37,"SUCCESS");          // Validate Output Status in Output File
                        csvAssert.assertCell(data,2,37,"SUCCESS");
                        csvAssert.assertCell(data,3,37,"SUCCESS");
                        csvAssert.assertCell(data,4,37,"SUCCESS");
                        csvAssert.assertCell(data,5,37,"SUCCESS");
                        csvAssert.assertCell(data,6,37,"FAILURE");
                        csvAssert.assertCell(data,7,37,"FAILURE");
                        csvAssert.assertCell(data,6,38,"Payment Cycle is not 202512C2"); // Validate Output Remark in Output File
                        csvAssert.assertCell(data,7,38,"Payment Cycle is not 202512C2");
                        LogUtils.info("Validated Manual Upload Output File");
                    }
                    else if (fileName.equalsIgnoreCase("ManualCorrection.csv")) {
                        LogUtils.info("Validating Column Present in Manual Correction Output File");
                        csvAssert.assertCell(data,1,1,"MIS_MHA13RIGX7A4V1"); // Validate MIS ID in Output File
                        csvAssert.assertCell(data,2,1,"MIS_MHA13RIGX7A4V2");
                        csvAssert.assertCell(data,3,1,"MIS_MHA13RIGX7A4V3");
                        csvAssert.assertCell(data,4,1,"MIS_MHA13RIGX7A4V4");
                        csvAssert.assertCell(data,5,1,"MIS_MHA13RIGX7A4V5");
                        csvAssert.assertCell(data,6,1,"MIS_AHSJCV6AHAI");
                        csvAssert.assertCell(data,1,36,"SUCCESS");          // Validate Output Status in Output File
                        csvAssert.assertCell(data,2,36,"SUCCESS");
                        csvAssert.assertCell(data,3,36,"SUCCESS");
                        csvAssert.assertCell(data,4,36,"SUCCESS");
                        csvAssert.assertCell(data,5,36,"SUCCESS");
                        csvAssert.assertCell(data,6,36,"FAILURE");
                        csvAssert.assertCell(data,6,37,"Transition from SYSTEM_GENERATED to MANUAL_CORRECTION is not possible;"); // Validate Output Remark in Output File
                        LogUtils.info("Validated Column Present in Manual Correction Output File as Expected");
                    }
                    else if (fileName.equalsIgnoreCase("Deviations")) {
                        LogUtils.info("Validating Data Present in Manual Deviations Output File");
                        if(deviationType.equalsIgnoreCase("INCORRECT_RULES")){
                            csvAssert.assertRow(data, 0, Arrays.asList("policyCommissionId", "Type", "SkipDifferentialDeductions", "Payout IRDA ODB %", "Payout IRDA ODR %", "Payout IRDA TP %", "Payout IRDA NP %", "Payout IRDA ABS", "Insurer Payout OD %", "Insurer Payout TP %", "Insurer Payout NP %", "Insurer Payout ABS", "Remark", "Output Status", "Output Remark"));
                            csvAssert.assertRow(data, 1, Arrays.asList(pcid, "INCORRECT_RULES", "false", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "31.0", "0.0", "Testing", "SUCCESS", ""));
                        }
                        else if (deviationType.equalsIgnoreCase("SPECIAL_REQUEST")) {
                            csvAssert.assertRow(data, 0, Arrays.asList("policyCommissionId", "Type", "SkipDifferentialDeductions", "Payout IRDA ODB %", "Payout IRDA ODR %", "Payout IRDA TP %", "Payout IRDA NP %", "Payout IRDA ABS", "Insurer Payout OD %", "Insurer Payout TP %", "Insurer Payout NP %", "Insurer Payout ABS", "Remark", "Output Status", "Output Remark"));
                            csvAssert.assertRow(data, 1, Arrays.asList(pcid, "SPECIAL_REQUEST", "false", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "31.0", "0.0", "Testing", "SUCCESS", ""));
                        }
                        else if (deviationType.equalsIgnoreCase("NOMINAL_DEVIATION")) {
                            csvAssert.assertRow(data, 0, Arrays.asList("policyCommissionId", "Type", "SkipDifferentialDeductions", "Payout IRDA ODB %", "Payout IRDA ODR %", "Payout IRDA TP %", "Payout IRDA NP %", "Payout IRDA ABS", "Insurer Payout OD %", "Insurer Payout TP %", "Insurer Payout NP %", "Insurer Payout ABS", "Remark", "Output Status", "Output Remark"));
                            csvAssert.assertRow(data, 1, Arrays.asList(pcid, "NOMINAL_DEVIATION", "false", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "31.0", "0.0", "Testing", "SUCCESS", ""));
                        }
                        else if (deviationType.equalsIgnoreCase("INCORRECT_RULESS")) {
                            csvAssert.assertRow(data, 0, Arrays.asList("policyCommissionId", "Type", "SkipDifferentialDeductions", "Payout IRDA ODB %", "Payout IRDA ODR %", "Payout IRDA TP %", "Payout IRDA NP %", "Payout IRDA ABS", "Insurer Payout OD %", "Insurer Payout TP %", "Insurer Payout NP %", "Insurer Payout ABS", "Remark", "Output Status", "Output Remark"));
                            csvAssert.assertRow(data, 1, Arrays.asList(pcid, "INCORRECT_RULESS", "false", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "31.0", "0.0", "Testing", "FAILURE", "Invalid Deviation Type"));
                        }
                        LogUtils.info("Validated Data Present in Manual Deviations Output File as Expected");
                    }
                    else if (fileName.equalsIgnoreCase("SPLIT_DEVIATIONS")) {
                        LogUtils.info("Validating Column Present in SplitDeviations Output File as Expected");
                        csvAssert.assertRow(data,0,Arrays.asList("policyCommissionId", "Dealer Commission Retention %", "Remark", "Output Status", "Output Remark"));
                        csvAssert.assertRow(data,1,Arrays.asList(pcid, "35.0", "Testing", "SUCCESS", ""));
                        LogUtils.info("Validated Column Present in SplitDeviations Template Output File as Expected");
                    }
                    else if (fileName.equalsIgnoreCase("NonSplitPartner_SPLIT_DEVIATIONS")) {
                        LogUtils.info("Validating Column Present in NonSplitPartner_SplitDeviations Output File as Expected");
                            csvAssert.assertRow(data,0,Arrays.asList("policyCommissionId", "Dealer Commission Retention %", "Remark", "Output Status", "Output Remark"));
                            csvAssert.assertRow(data,1,Arrays.asList(pcid, "35.0", "Testing", "FAILURE", "dealer payout deviations is only allowed for dealerPayoutSplit parentSubType"));
                        LogUtils.info("Validated Column Present in NonSplitPartner_SplitDeviations Output File as Expected");
                    }
                    else if (fileName.equalsIgnoreCase("Adjustments.csv")) {
                        LogUtils.info("Validating Column Present in Adjustments Output File as Expected");
                        csvAssert.assertRow(data,0,Arrays.asList("Recorded_At", "policyCommissionId", "policyDetailsId", "policyPaymentScheduleId", "DP Login Id", "Customer First Name", "Customer Last Name", "Booking/Issued Date", "Payment Cycle", "Case Status", "Channel Type", "Product category", "Product subcategory", "Vehicle type", "Vehicle subtype", "Business Type", "Plan name", "Insurer", "product name", "Registration no.", "Policy No.", "Master Policy No.", "Points", "Remark", "Output Status", "Output Remark"));
                        csvAssert.assertRow(data,1,Arrays.asList("18-Dec-2025", "692fce37e55fd35ac2d06b22", "MIS_AHSK7OHON57", "", "63b54bb9ee10470001250bb6", "Sanity", "Test", "03-Dec-2025", "202512C2", "Issued", "Partner", "Two Wheeler", "", "TW", "Bike", "New", "", "Bajaj Allianz", "Comprehensive", "MH-01-WW-2177", "'DNNDNDndjjsjss", "", "18.0", "AdjustmentsTest", "SUCCESS", ""));
                        LogUtils.info("Validated Column Present in Adjustments Output File as Expected");
                    }
                    else if (fileName.equalsIgnoreCase("AdjustmentsInvalid.csv")) {
                        LogUtils.info("Validating Column Present in AdjustmentsInvalid Output File as Expected");
                        csvAssert.assertRow(data,0,Arrays.asList("Recorded_At", "policyCommissionId", "policyDetailsId", "policyPaymentScheduleId", "DP Login Id", "Customer First Name", "Customer Last Name", "Booking/Issued Date", "Payment Cycle", "Case Status", "Channel Type", "Product category", "Product subcategory", "Vehicle type", "Vehicle subtype", "Business Type", "Plan name", "Insurer", "product name", "Registration no.", "Policy No.", "Master Policy No.", "Points", "Remark", "Output Status", "Output Remark"));
                        csvAssert.assertRow(data,1,Arrays.asList("18-Dec-2025", "6901f22709147c665f37c18c", "MIS_MHRGXWDYUJ2", "", "6290f07ed35ae3058a14b495", "UTKARSH VIKAS", "CHANDEL", "29-Oct-2025", "202512C2", "Issued", "Partner", "Motor", "", "Car", "Car", "New", "", "Reliance", "Comprehensive", "NEW", "'920222523740005378", "", "18.0", "InvalidAdjustmentsTest", "FAILURE", "`commissionId` does not exist / Payment not yet finalised."));
                        LogUtils.info("Validated Column Present in AdjustmentsInvalid Output File as Expected");
                    }
                    else if (fileName.equalsIgnoreCase("PayoutQC.csv")) {
                        LogUtils.info("Validating Column Present in PayoutQC Template");

                        LogUtils.info("Validated Column Present in PayoutQC Template as Expected");
                    }
                    else if (fileName.equalsIgnoreCase("InsurerRewards.csv")) {
                        LogUtils.info("Validating Column Present in InsurerRewards Template");

                        LogUtils.info("Validated Column Present in InsurerRewards Template as Expected");
                    }
                    else if (fileName.equalsIgnoreCase("PartnerLevelActivity.csv")) {
                        LogUtils.info("Validating Column Present in PartnerLevelActivity Template");

                        LogUtils.info("Validated Column Present in PartnerLevelActivity Template as Expected");
                    }
                    else if (fileName.equalsIgnoreCase("PostQC_Review.csv")) {
                        LogUtils.info("Validating Column Present in PostQC_Review Template");

                        LogUtils.info("Validated Column Present in PostQC_Review Template as Expected");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("An error occurred during the CSV file validation.", e);
        }
    }

    public void verifyVia_BulkSearch(String fileName){
        TestUtil.click(cmp.quickSearchSectnBtn, "Quick Search Button Clicked");
        TestUtil.click(cmp.bulkSearchBtn, "Bulk Search Button Clicked");
        TestUtil.click(cmp.uploadBtn, "Upload Button Clicked");

        if(fileName.equalsIgnoreCase("ManualUploadBulkSearch.csv")) {
            driver.findElement(By.xpath("//input[@type='file']")).sendKeys("//Users//rahulpatil//Documents//Payouts Files//UploadPayouts//" + fileName + "");
            LogUtils.info(fileName + " :File Uploaded");
        }
        else if (fileName.equalsIgnoreCase("ManualCorrectionBulkSearch.csv")) {
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

