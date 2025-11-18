package com.qa.turtlemint.pages.payouts;

import com.qa.turtlemint.base.TestBase;
import com.qa.turtlemint.commands.WebCommands;
import com.qa.turtlemint.util.LogUtils;
import com.qa.turtlemint.util.TestUtil;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;

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
    }

    public void commentEnter(){
        TestUtil.sendKeys(commentTxtBox,"Automation Test","Comment Entered");
    }

    public void clearDropdown(){
        Actions act=new Actions(driver);
        act.moveToElement(clearDrpdwn).build().perform();
        act.click(clearDrpdwn).build().perform();
    }

    @AfterTest()
    public void close() throws Exception {
        driver.close();
    }
}
