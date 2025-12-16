package com.qa.turtlemint.pages.payouts;

import com.qa.turtlemint.base.TestBase;
import com.qa.turtlemint.commands.WebCommands;
import com.qa.turtlemint.pages.CSV_Validatator.CsvUtils;
import com.qa.turtlemint.util.LogUtils;
import com.qa.turtlemint.util.TestUtil;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

public class DownloadPayoutsCyclePage extends TestBase {

    @FindBy(xpath = "//span[text()='Payouts Ledger']")
    public WebElement ledgerBtn;

    @FindBy(xpath = "//div[text()='Download Payouts']")
    WebElement dwnloadPayoutBtn;

    @FindBy(xpath = "//h3[text()='Download Payouts']")
    WebElement downloadPayoutTitle;

    @FindBy(xpath = "//label[text()='Payment Cycles: ']")
    WebElement dropdownText1;

    @FindBy(xpath = "//p[text()='(Multi-Select)']")
    WebElement dropdownText2;

    @FindBy(xpath = "//h3[text()='Download Payouts History']")
    WebElement downloadPayoutHistryTitle;

    @FindBy(xpath = "(//input[@class='ant-select-selection-search-input'])[1]")
    WebElement paymentCycleDrpdwn;

    @FindBy(xpath = "//span[text()='Download ']")
    WebElement downloadBtn;

    @FindBy(xpath = "//span[text()='Download']")
    WebElement downloadBtn2;

    @FindBy(xpath = "//textarea[@name='comment']")
    WebElement commentTxtBox;

    @FindBy(xpath = "//td[text()='automationtesting']")
    WebElement dowloadedBy;

    @FindBy(xpath = "//td[text()='Automation Test']")
    WebElement commentText;



    @FindBy(xpath = "//span[@aria-label='close-circle']")
    WebElement clearDrpdwn;

    public DownloadPayoutsCyclePage() {
        PageFactory.initElements(driver, this);
    }

    public void downloadPayouts (){
        String strUrl = driver.getCurrentUrl();
        LogUtils.info("Opened Website: " + strUrl);
        TestUtil.click(ledgerBtn, "Payout Ledger Button Clicked");;
        TestUtil.click(dwnloadPayoutBtn, "Download Payout Button Clicked");
        Assert.assertEquals("Download Payouts", downloadPayoutTitle.getText());
        Assert.assertEquals("Payment Cycles:", dropdownText1.getText());
        Assert.assertEquals("(Multi-Select)", dropdownText2.getText());
        Assert.assertEquals("Download Payouts History", downloadPayoutHistryTitle.getText());
    }

    public void selectPaymentCycle(String paymentCycle, String code){
        Actions act=new Actions(driver);
        try {
            if (clearDrpdwn.isDisplayed()) {
                act.moveToElement(clearDrpdwn).click().perform();
            }
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            System.out.println("Clear dropdown cta not present");
        }
        TestUtil.click(paymentCycleDrpdwn, "Clicked on Dropdown");
        paymentCycleDrpdwn.sendKeys(code);
        driver.findElement(By.xpath("//*[text()='"+ paymentCycle + "']")).click();
    }

    public void downloadClick(){
        TestUtil.click(downloadBtn, "Download Button Clicked");
        commentEnter();
        WebCommands.staticSleep(2000);
        TestUtil.click(downloadBtn2, "Download Button Clicked after entered comment");
        WebCommands.staticSleep(2000);
        Assert.assertEquals(dowloadedBy.getText(),"automationtesting");
        Assert.assertEquals(commentText.getText(),"Automation Test");
        TestUtil.getScreenShot();
        WebCommands.staticSleep(5000);
    }

    public void commentEnter(){
        TestUtil.sendKeys(commentTxtBox,"Automation Test","Comment Entered");
    }

    public void clearDropdown(){
        Actions act=new Actions(driver);
        act.moveToElement(clearDrpdwn).build().perform();
        act.click(clearDrpdwn).build().perform();
    }

    public void validateDownloadedCycle(String cycleType){
        try {
            String downloadDirectory = "/var/lib/jenkins/workspace/payout";
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
                    LogUtils.info(String.valueOf(mostRecentFile));
                    if (cycleType.equalsIgnoreCase("regularCycle")) {
                        csvAssert.assertCell(data, 1, 132, "202510C2");
                        LogUtils.info("202510C1 Regular Cycle Present in Dump");
                        csvAssert.assertCell(data, 101, 132, "202510C2");
                        LogUtils.info("202510C2 Regular Cycle Present in Dump");
                        csvAssert.assertCell(data, 186, 132, "202511C1");
                        LogUtils.info("202511C1 Regular Cycle Present in Dump");
                        csvAssert.assertCell(data, 377, 132, "202511C2");
                        LogUtils.info("202511C2 Regular Cycle Present in Dump");
                    }
                    else if (cycleType.equalsIgnoreCase("quickpayCycle")) {
                        csvAssert.assertCell(data, 1, 132, "20251120");
                        LogUtils.info("20251120 QuickPay Cycle Present in Dump");
                        csvAssert.assertCell(data, 9, 132, "20251121");
                        LogUtils.info("20251121 QuickPay Cycle Present in Dump");
                        csvAssert.assertCell(data, 18, 132, "20251122");
                        LogUtils.info("20251122 QuickPay Cycle Present in Dump");
                        csvAssert.assertCell(data, 21, 132, "20251123");
                        LogUtils.info("20251123 QuickPay Cycle Present in Dump");
                    }
                    else if (cycleType.equalsIgnoreCase("regular_quickpayCycle")) {
                        csvAssert.assertCell(data, 1, 132, "20251120");
                        LogUtils.info("20251120 QuickPay Cycle Present in Dump");
                        csvAssert.assertCell(data, 10, 132, "20251121");
                        LogUtils.info("20251121 QuickPay Cycle Present in Dump");
                        csvAssert.assertCell(data, 18, 132, "202511C1");
                        LogUtils.info("202511C1 Regular Cycle Present in Dump");
                        csvAssert.assertCell(data, 210, 132, "202511C2");
                        LogUtils.info("202511C2 Regular Cycle Present in Dump");
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("An error occurred during the CSV file validation.", e);
        }

    }

}
