package com.qa.turtlemint.pages.payouts;

import com.opencsv.CSVReader;
import com.qa.turtlemint.base.TestBase;
import com.qa.turtlemint.commands.WebCommands;
import com.qa.turtlemint.pages.CSV_Validatator.CsvUtils;
import com.qa.turtlemint.util.LogUtils;
import com.qa.turtlemint.util.TestUtil;
import com.qa.turtlemint.util.Utils;
import junit.framework.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.*;
import java.time.Duration;
import java.util.*;
import java.io.File;
import java.io.FileReader;

public class CycleMovePage extends TestBase {

    @FindBy(xpath = "//div[text()='Early Cycle Payments']")
    WebElement earlyCycleBtn;

    @FindBy(xpath = "//div[text()='Move to Later Cycles']")
    WebElement laterCycleBtn;

    @FindBy(xpath = "//div[text()='Move to Quick Pay Cycle']")
    WebElement quickPayCycleBtn;

    @FindBy(xpath = "//div[text()='Quick Search Section']")
    public WebElement quickSearchSectnBtn;

    @FindBy(xpath = "//div[text()='Bulk Search']")
    public WebElement bulkSearchBtn;

    @FindBy(xpath = "(//span[text()='Search'])[2]")
    public WebElement searchBtn;

    @FindBy(xpath = "//span[text()='Download']")
    public WebElement downloadBtn;

    @FindBy(xpath = "//span[text()='Cancel']//parent::button//following-sibling::button//span")
    public WebElement resultDownloadBtn;

    @FindBy(xpath = "//h3[text()='Early Cycle Payments']")
    WebElement earlyCyclePageTitle;

    @FindBy(xpath = "//h3[text()='Move to Later Cycles']")
    WebElement laterCyclePageTitle;

    @FindBy(xpath = "//h3[text()='Move to Quickpay Cycle']")
    WebElement quickPayCyclePageTitle;

    @FindBy(xpath = "//span[text()='Download File Template']")
    WebElement templateDownloadBtn;

    @FindBy(xpath = "//span[text()='Upload']")
    WebElement uploadBtn;

    @FindBy(xpath = "//b[text()='Source Payment Cycle']//parent::div//following-sibling::div//child::div[@class='ant-select-selector']")
    WebElement sourceCycleDrpdwn;

    @FindBy(xpath = "//b[text()='Destination Payment Cycle']//parent::div//following-sibling::div//child::div[@class='ant-select-selector']")
    WebElement destinationCycleDrpdwn;

    @FindBy(xpath = "//textarea[@name='comment']")
    WebElement commentTxtBox;

    @FindBy(xpath = "(//span[text()='Upload'])[2]")
    WebElement mainUploadBtn;

    @FindBy(xpath = "(//span[text()='Output_File'])[1]")
    WebElement outputFileBtn;

    @FindBy(xpath = "(//div[@class='ant-table-body']//tbody//tr//following-sibling::tr//td)[3]")
    WebElement uploadeByUser;

    @FindBy(xpath = "(//div[@class='ant-table-body']//tbody//tr//following-sibling::tr//td)[4]")
    WebElement srcPaymentCycle;

    @FindBy(xpath = "(//div[@class='ant-table-body']//tbody//tr//following-sibling::tr//td)[5]")
    WebElement destPaymentCycle;

    @FindBy(xpath = "(//div[@class='ant-table-body']//tbody//tr//following-sibling::tr//td)[6]")
    WebElement enteredComment;

    @FindBy(xpath = "(//div[@class='ant-table-body']//tbody//tr//following-sibling::tr//td)[7]")
    WebElement successCount;

    @FindBy(xpath = "(//div[@class='ant-table-body']//tbody//tr//following-sibling::tr//td)[8]")
    WebElement failureCount;

    DownloadPayoutsCyclePage dpc = new DownloadPayoutsCyclePage();

    public CycleMovePage() {

        PageFactory.initElements(driver, this);
    }

    public void move_CyclePayments(String fileName, String srcCycle, String destCycle) throws Exception {
        TestUtil.click(dpc.ledgerBtn, "Payout Ledger Button Clicked");
        if(fileName.equalsIgnoreCase("EarlyCycleMove_C2_C1.csv")){
            TestUtil.click(earlyCycleBtn, "Early Cycle Payments Button Clicked");
            Assert.assertEquals(earlyCyclePageTitle.getText(), "Early Cycle Payments");
        }
        else if (fileName.equalsIgnoreCase("LaterCycleMove_C1_C2.csv")) {
            TestUtil.click(laterCycleBtn, "Later Cycle Payments Button Clicked");
            Assert.assertEquals(laterCyclePageTitle.getText(), "Move to Later Cycles");
        }
        else if (fileName.equalsIgnoreCase("QuickPayCycleMove_C2_QP.csv")) {
            TestUtil.click(quickPayCycleBtn, "Move to QuickPay Cycle Button Clicked");
            Assert.assertEquals(quickPayCyclePageTitle.getText(), "Move to Quickpay Cycle");
        }

        TestUtil.click(templateDownloadBtn, "Download Early Cycle File Template");
        WebCommands.staticSleep(1000);
        validateDownloadTemplateFile();
        TestUtil.click(uploadBtn, "Upload button clicked");
        uploadFile(fileName);
        WebCommands.staticSleep(2000);
        commentEnter();
        TestUtil.click(sourceCycleDrpdwn, "Source Cycle dropdown Clicked");
        selectSourceCycle(srcCycle);
        LogUtils.info(srcCycle + " : Source Cycle Selected");
        TestUtil.click(destinationCycleDrpdwn, "Destination Cycle dropdown Clicked");
        selectDestinationCycle(destCycle);
        LogUtils.info(destCycle + " : Destination Cycle Selected");
        TestUtil.click(mainUploadBtn, "Upload button clicked");
        WebCommands.staticSleep(2000);
        TestUtil.click(outputFileBtn, "OutputFile button clicked");
        WebCommands.staticSleep(3000);
        AssertionOn_OutputFile(fileName);
        WebCommands.staticSleep(4000);
        moveFileUploadHistoryAssertion(fileName);
        TestUtil.getScreenShot();
    }

    public void verifyMoveEntryCycle_ViaBulkSearch(String fileName){
        TestUtil.click(quickSearchSectnBtn, "Quick Search Button Clicked");
        TestUtil.click(bulkSearchBtn, "Bulk Search Button Clicked");
        TestUtil.click(uploadBtn, "Upload Button Clicked");
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys("//Users//rahulpatil//Documents//Payouts Files//MoveCycleAssert//MoveCycleAssert.csv");
        WebCommands.staticSleep(1000);
        TestUtil.click(searchBtn, "Search Button Clicked");
        TestUtil.click(downloadBtn, "Download Button Clicked");
        commentEnter();
        TestUtil.click(resultDownloadBtn, "Result Download Button Clicked");
        WebCommands.staticSleep(1000);
        bulkSearchFileAssert(fileName);
    }

    public void bulkSearchFileAssert(String fileName){
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
                            if (fileName.equalsIgnoreCase("EarlyCycleMove_C2_C1.csv")) {
                                LogUtils.info("Validating Entries Present in Expected Cycle After Early Cycle Move");
                                csvAssert.assertCell(data, 1, 132, "202511C1");
                                csvAssert.assertCell(data, 2, 132, "202511C1");
                                csvAssert.assertCell(data, 3, 132, "202511C1");
                                csvAssert.assertCell(data, 4, 132, "202511C1");
                                csvAssert.assertCell(data, 5, 132, "202511C1");
                                csvAssert.assertCell(data, 6, 132, "202511C1");
                                csvAssert.assertCell(data, 7, 132, "202510C2");
                                csvAssert.assertCell(data, 8, 132, "202510C2");
                                csvAssert.assertCell(data, 9, 132, "202510C2");
                                LogUtils.info("Early Cycle Move Entries Are Present as in Expected Cycle");
                            }
                            else if (fileName.equalsIgnoreCase("LaterCycleMove_C1_C2.csv")) {
                                LogUtils.info("Validating Entries Present in Expected Cycle After Later Cycle Move");
                                csvAssert.assertCell(data, 1, 132, "202511C2");
                                csvAssert.assertCell(data, 2, 132, "202511C2");
                                csvAssert.assertCell(data, 3, 132, "202511C2");
                                csvAssert.assertCell(data, 4, 132, "202511C2");
                                csvAssert.assertCell(data, 5, 132, "202511C2");
                                csvAssert.assertCell(data, 6, 132, "202511C2");
                                csvAssert.assertCell(data, 7, 132, "202510C2");
                                csvAssert.assertCell(data, 8, 132, "202510C2");
                                csvAssert.assertCell(data, 9, 132, "202510C2");
                                LogUtils.info("Later Cycle Move Entries Are Present as in Expected Cycle");
                            } else if (fileName.equalsIgnoreCase("QuickPayCycleMove_C2_QP.csv")) {
                                LogUtils.info("Validating Entries Present in Expected Cycle After QuickPay Cycle Move");
                                csvAssert.assertCell(data, 1, 132, "20251125");
                                csvAssert.assertCell(data, 2, 132, "20251125");
                                csvAssert.assertCell(data, 3, 132, "20251125");
                                csvAssert.assertCell(data, 4, 132, "20251125");
                                csvAssert.assertCell(data, 5, 132, "20251125");
                                csvAssert.assertCell(data, 6, 132, "20251125");
                                csvAssert.assertCell(data, 7, 132, "202510C2");
                                csvAssert.assertCell(data, 8, 132, "202510C2");
                                csvAssert.assertCell(data, 9, 132, "202510C2");
                                LogUtils.info("Quickpay Cycle Move Entries Are Present as in Expected Cycle");
                            }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new AssertionError("An error occurred during the CSV file validation.", e);
            }
    }

    public void moveBackInCycle_ToContinueFlow(String fileName, String srcCycle, String destCycle) throws InterruptedException {
        TestUtil.click(dpc.ledgerBtn, "Payout Ledger Button Clicked");
        TestUtil.click(laterCycleBtn, "Early Cycle Payments Button Clicked");
        WebCommands.staticSleep(1000);
        TestUtil.click(uploadBtn, "Upload button clicked");
        uploadFile(fileName);
        WebCommands.staticSleep(5000);
        commentEnter();
        TestUtil.click(sourceCycleDrpdwn, "Source Cycle dropdown Clicked");
        selectSourceCycle(srcCycle);
        WebCommands.staticSleep(3000);
        TestUtil.click(destinationCycleDrpdwn, "Destination Cycle dropdown Clicked");
        WebCommands.staticSleep(2000);
        selectDestinationCycle(destCycle);
        WebCommands.staticSleep(1000);
        TestUtil.click(mainUploadBtn, "Upload button clicked");
        WebCommands.staticSleep(5000);
        LogUtils.info("Source Pay Cycle : " + srcPaymentCycle.getText());
        Assert.assertEquals("20251125", srcPaymentCycle.getText());
        LogUtils.info("Destination Pay Cycle : " + destPaymentCycle.getText());
        Assert.assertEquals("202511C2", destPaymentCycle.getText());
        LogUtils.info("Success count is : " + successCount.getText());
        Assert.assertEquals("6", successCount.getText());
        LogUtils.info("Failure count is : " + failureCount.getText());
        Assert.assertEquals("3", failureCount.getText());
        TestUtil.getScreenShot();
    }

    public void selectSourceCycle(String srcCycle) throws NoSuchElementException, InterruptedException {
        WebElement scrollBox = driver.findElement(By.xpath("(//div[@class='rc-virtual-list-holder'])[1]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        boolean found = false;
        for (int i = 0; i < 30 && !found; i++) {
            try {
                Utils.selectExactVisibleOption(driver, By.xpath("//div[contains(@class,'rc-virtual-list-holder-inner')]"), srcCycle, 3);
                found = true;
            } catch (NoSuchElementException e) {
                js.executeScript("arguments[0].scrollTop = arguments[0].scrollTop + arguments[0].offsetHeight;", scrollBox);
                Thread.sleep(150);
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        if (!found) {
            throw new RuntimeException("Cycle '" + srcCycle + "' not found in dropdown after scrolling");
        }
    }


    public void selectDestinationCycle(String destCycle) throws NoSuchElementException, InterruptedException {
        WebElement scrollBox = driver.findElement(By.xpath("(//div[@class='rc-virtual-list-holder'])[2]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        boolean found = false;
        for (int i = 0; i < 30 && !found; i++) {
            try {
                Utils.selectExactVisibleOption(driver, By.xpath("(//div[contains(@class,'rc-virtual-list-holder-inner')])[2]"), destCycle, 3);
                found = true;
            } catch (NoSuchElementException e) {
                js.executeScript("arguments[0].scrollTop = arguments[0].scrollTop + arguments[0].offsetHeight;", scrollBox);
                Thread.sleep(150);
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        if (!found) {
            throw new RuntimeException("Cycle '" + destCycle + "' not found in dropdown after scrolling");
        }
    }


    public static void uploadFiles(String relativePath, String fileName) {
        String filePath = System.getProperty("user.dir") + File.separator + relativePath+fileName;
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(filePath);
    }

    public static void uploadFile(String fileName) {

        if(fileName.equalsIgnoreCase("EarlyCycleMove_C2_C1.csv")) {
            uploadFiles("src/main/resources/data/",fileName);
          //  driver.findElement(By.xpath("//input[@type='file']")).sendKeys("//Users//rahulpatil//Documents//Payouts Files//EarlyCycleMove//" + fileName + "");
            LogUtils.info(fileName + " :File Uploaded");
        }
        else if (fileName.equalsIgnoreCase("LaterCycleMove_C1_C2.csv")) {
            uploadFiles("src/main/resources/data/",fileName);
//            driver.findElement(By.xpath("//input[@type='file']")).sendKeys("//Users//rahulpatil//Documents//Payouts Files//LaterCycleMove//" + fileName + "");
            LogUtils.info(fileName + " :File Uploaded");
        }
        else if (fileName.equalsIgnoreCase("QuickPayCycleMove_C2_QP.csv")) {
            driver.findElement(By.xpath("//input[@type='file']")).sendKeys("//Users//rahulpatil//Documents//Payouts Files//QuickPayCycleMove//" + fileName + "");
            LogUtils.info(fileName + " :File Uploaded");
        }
        else if (fileName.equalsIgnoreCase("MoveBackCycle_QP_C2.csv")) {
            driver.findElement(By.xpath("//input[@type='file']")).sendKeys("//Users//rahulpatil//Documents//Payouts Files//MoveBackCycle//" + fileName + "");
            LogUtils.info(fileName + " :File Uploaded");
        }
        else if (fileName.equalsIgnoreCase("Valid_MIS_BulkSearch.csv")) {
            driver.findElement(By.xpath("//input[@type='file']")).sendKeys("//Users//rahulpatil//Documents//Payouts Files//BulkSearchFiles//" + fileName + "");
            LogUtils.info(fileName + " :File Uploaded");
        }
        else if (fileName.equalsIgnoreCase("Valid_Invalid_MIS_BulkSearch.csv")) {
            driver.findElement(By.xpath("//input[@type='file']")).sendKeys("//Users//rahulpatil//Documents//Payouts Files//BulkSearchFiles//" + fileName + "");
            LogUtils.info(fileName + " :File Uploaded");
        }
        else if (fileName.equalsIgnoreCase("Invalid_MIS_BulkSearch.csv")) {
            driver.findElement(By.xpath("//input[@type='file']")).sendKeys("//Users//rahulpatil//Documents//Payouts Files//BulkSearchFiles//" + fileName + "");
            LogUtils.info(fileName + " :File Uploaded");
        }
    }

    public void commentEnter() {
        TestUtil.sendKeys(commentTxtBox, "Automation Test", "Comment Entered");
    }

    public void moveFileUploadHistoryAssertion(String fileName) {
//        Assert.assertEquals(uploadeByUser.getText(), "automationtesting");
//        LogUtils.info("Uploaded By : " + uploadeByUser.getText());

        if (fileName.equalsIgnoreCase("EarlyCycleMove_C2_C1.csv")) {
            LogUtils.info("Source Pay Cycle : " + srcPaymentCycle.getText());
            Assert.assertEquals(srcPaymentCycle.getText(), "202511C2");
            LogUtils.info("Destination Pay Cycle : " + destPaymentCycle.getText());
            Assert.assertEquals(destPaymentCycle.getText(), "202511C1");
        }
        else if (fileName.equalsIgnoreCase("LaterCycleMove_C1_C2.csv")) {
            LogUtils.info("Source Pay Cycle : " + srcPaymentCycle.getText());
            Assert.assertEquals(srcPaymentCycle.getText(), "202511C1");
            LogUtils.info("Destination Pay Cycle : " + destPaymentCycle.getText());
            Assert.assertEquals(destPaymentCycle.getText(), "202511C2");
        }
        else if (fileName.equalsIgnoreCase("QuickPayCycleMove_C2_QP.csv")) {
            LogUtils.info("Source Pay Cycle : " + srcPaymentCycle.getText());
            Assert.assertEquals(srcPaymentCycle.getText(), "202511C2");
            LogUtils.info("Destination Pay Cycle : " + destPaymentCycle.getText());
            Assert.assertEquals(destPaymentCycle.getText(), "20251125");
        }
        LogUtils.info("Entered Comment : " + enteredComment.getText());
        Assert.assertEquals(enteredComment.getText(), "Automation Test");
        LogUtils.info("Success count is : " + successCount.getText());
        Assert.assertEquals(successCount.getText(), "6");
        LogUtils.info("Failure count is : " + failureCount.getText());
        Assert.assertEquals(failureCount.getText(), "3");
        Assert.assertTrue(outputFileBtn.isDisplayed());
    }

    public void validateDownloadTemplateFile(){
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
                        List<String[]> li=readcsv.readAll();
                        Iterator<String[]> i1= li.iterator();
                        while(i1.hasNext()){
                            String[] str=i1.next();
                            System.out.println(" Values are ");
                            List<String> listActual =  new ArrayList<>();
                            for(int i=0;i<str.length;i++)
                            {
                                listActual.add(str[i]);
                            }
                            System.out.println(listActual);
                            List<String> listExpected = new ArrayList<>(Arrays.asList("policyDetailsId", "Booking/Issued Date", "Source Payment Cycle","Destination Payment Cycle","Ledger_Id","Ledger_Entity_Type","Ledger_Comment","DP Login Id","Customer First Name","Customer Last Name","Case Status","Channel Type","Product category","Product subcategory","Vehicle type","Vehicle subtype","Business Type","Plan name","Insurer","product name","Registration no.","Policy No.","Master Policy No."));
                            Assert.assertEquals(listActual,listExpected);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("An error occurred during the CSV file validation.", e);
        }
    }

    public void AssertionOn_OutputFile(String fileName){
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
                        WebCommands.staticSleep(2000);
                        CsvUtils csvAssert = new CsvUtils();
                        List<String[]> data = csvAssert.readCsv(mostRecentFile);

                        if (fileName.equalsIgnoreCase("EarlyCycleMove_C2_C1.csv")) {
                            csvAssert.assertRow(data, 0, Arrays.asList("policyDetailsId", "Booking/Issued Date", "Source Payment Cycle", "Destination Payment Cycle", "Ledger_Id", "Ledger_Entity_Type", "Ledger_Comment", "DP Login Id", "Customer First Name", "Customer Last Name", "Case Status", "Channel Type", "Product category", "Product subcategory", "Vehicle type", "Vehicle subtype", "Business Type", "Plan name", "Insurer", "product name", "Registration no.", "Policy No.", "Master Policy No.", "Output Status", "Output Remark"));
                            csvAssert.assertCell(data, 1, 23, "SUCCESS");
                            csvAssert.assertCell(data, 2, 23, "SUCCESS");
                            csvAssert.assertCell(data, 3, 23, "SUCCESS");
                            csvAssert.assertCell(data, 4, 23, "SUCCESS");
                            csvAssert.assertCell(data, 5, 23, "SUCCESS");
                            csvAssert.assertCell(data, 6, 23, "SUCCESS");
                            csvAssert.assertCell(data, 7, 23, "FAILURE");
                            csvAssert.assertCell(data, 8, 23, "FAILURE");
                            csvAssert.assertCell(data, 9, 23, "FAILURE");
                            csvAssert.assertCell(data, 7, 24, "source payment cycle mismatch expected 202511C2");
                            csvAssert.assertCell(data, 8, 24, "source payment cycle mismatch expected 202511C2");
                            csvAssert.assertCell(data, 9, 24, "destination payment cycle mismatch expected 202511C1");
                        }
                        else if (fileName.equalsIgnoreCase("LaterCycleMove_C1_C2.csv")) {
                            csvAssert.assertRow(data, 0, Arrays.asList("policyDetailsId", "Booking/Issued Date", "Source Payment Cycle", "Destination Payment Cycle", "Ledger_Id", "Ledger_Entity_Type", "Ledger_Comment", "DP Login Id", "Customer First Name", "Customer Last Name", "Case Status", "Channel Type", "Product category", "Product subcategory", "Vehicle type", "Vehicle subtype", "Business Type", "Plan name", "Insurer", "product name", "Registration no.", "Policy No.", "Master Policy No.", "Output Status", "Output Remark"));
                            csvAssert.assertCell(data, 1, 23, "SUCCESS");
                            csvAssert.assertCell(data, 2, 23, "SUCCESS");
                            csvAssert.assertCell(data, 3, 23, "SUCCESS");
                            csvAssert.assertCell(data, 4, 23, "SUCCESS");
                            csvAssert.assertCell(data, 5, 23, "SUCCESS");
                            csvAssert.assertCell(data, 6, 23, "SUCCESS");
                            csvAssert.assertCell(data, 7, 23, "FAILURE");
                            csvAssert.assertCell(data, 8, 23, "FAILURE");
                            csvAssert.assertCell(data, 9, 23, "FAILURE");
                            csvAssert.assertCell(data, 7, 24, "source payment cycle mismatch expected 202511C1; destination payment cycle mismatch expected 202511C2");
                            csvAssert.assertCell(data, 8, 24, "source payment cycle mismatch expected 202511C1; destination payment cycle mismatch expected 202511C2");
                            csvAssert.assertCell(data, 9, 24, "source payment cycle mismatch expected 202511C1; destination payment cycle mismatch expected 202511C2");
                        }
                        else if (fileName.equalsIgnoreCase("QuickPayCycleMove_C2_QP.csv")) {
                            csvAssert.assertRow(data, 0, Arrays.asList("policyDetailsId", "Booking/Issued Date", "Source Payment Cycle", "Destination Payment Cycle", "Ledger_Id", "Ledger_Entity_Type", "Ledger_Comment", "DP Login Id", "Customer First Name", "Customer Last Name", "Case Status", "Channel Type", "Product category", "Product subcategory", "Vehicle type", "Vehicle subtype", "Business Type", "Plan name", "Insurer", "product name", "Registration no.", "Policy No.", "Master Policy No.", "Output Status", "Output Remark"));
                            csvAssert.assertCell(data, 1, 23, "SUCCESS");
                            csvAssert.assertCell(data, 2, 23, "SUCCESS");
                            csvAssert.assertCell(data, 3, 23, "SUCCESS");
                            csvAssert.assertCell(data, 4, 23, "SUCCESS");
                            csvAssert.assertCell(data, 5, 23, "SUCCESS");
                            csvAssert.assertCell(data, 6, 23, "SUCCESS");
                            csvAssert.assertCell(data, 7, 23, "FAILURE");
                            csvAssert.assertCell(data, 8, 23, "FAILURE");
                            csvAssert.assertCell(data, 9, 23, "FAILURE");
                            csvAssert.assertCell(data, 7, 24, "source payment cycle mismatch expected 202511C2; destination payment cycle mismatch expected 20251125");
                            csvAssert.assertCell(data, 8, 24, "source payment cycle mismatch expected 202511C2; destination payment cycle mismatch expected 20251125");
                            csvAssert.assertCell(data, 9, 24, "destination payment cycle mismatch expected 20251125");
                        }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("An error occurred during the CSV file validation.", e);
        }

    }
}
