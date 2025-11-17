package com.qa.turtlemint.pages.payouts;

import com.qa.turtlemint.base.TestBase;
import com.qa.turtlemint.commands.WebCommands;
import com.qa.turtlemint.util.LogUtils;
import com.qa.turtlemint.util.TestUtil;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PayoutSplitRulesPage extends TestBase {

    @FindBy(xpath = "//span[text()='Create an account/ Sign in']")
    WebElement CreateAccount;

    @FindBy(xpath = "//span[text()='Configure Rules']")
    WebElement configureRulesModule;

    @FindBy(xpath = "//span[text()='Payouts Split Rules']")
    WebElement payoutSplitRule;

    @FindBy(xpath = "//div[text()='Test and Upload']")
    WebElement testAndUploadBtn;

    @FindBy(xpath = "//div[text()='Production History']")
    WebElement ruleHistoryBtn;

    @FindBy(xpath = "//h3[text()='Test and Upload']")
    WebElement uploadRulePageTitle;

    @FindBy(xpath = "//span[text()='Current Rule File']")
    WebElement crntRuleFileBtn;

    @FindBy(xpath = "//span[text()='Current Master Test Data']")
    WebElement masterDataBtn;

    @FindBy(xpath = "(//span[text()='Upload'])[1]")
    WebElement ruleUploadBtn;

    @FindBy(xpath = "(//span[text()='Upload'])[2]")
    WebElement masterDataUploadBtn;

    @FindBy(xpath = "//textarea[@name='comment']")
    WebElement commentTxtBox;

    @FindBy(xpath = "//button//span[text()='Upload']")
    WebElement mainUploadBtn;

    @FindBy(xpath = "(//button//span[text()='Upload'])[2]")
    WebElement pushToProductionUploadBtn;

    @FindBy(xpath = "//label[text()='Comments*']//parent::div//textarea")
    WebElement pushToProductionComment;

    @FindBy(xpath = "//span[text()='Start Simulation']")
    WebElement startSimulationBtn;

    @FindBy(xpath = "//h3[text()='Last Test Report']")
    WebElement reportTitle;

    @FindBy(xpath = "//span[text()='Rule File']")
    WebElement ruleFileButton;

    @FindBy(xpath = "//span[contains(text(),'Comparison')]//parent::div//following-sibling::div//a//span")
    WebElement comparisonReportButtn;

    @FindBy(xpath = "//span[contains(text(),'Data Impact')]//parent::div//following-sibling::div//a//span[text()='See Report']")
    WebElement masterDataButtn;

    @FindBy(xpath = "//span[text()='Close']")
    WebElement closeButtn;

    @FindBy(xpath = "//span[contains(text(),'reviewed')]//parent::div//label//span")
    WebElement reviewReportCheckBox;

    @FindBy(xpath = "//span[text()='Push to Production']")
    WebElement pushToProdButton;

    @FindBy(xpath = "//span[text()='Process without Re-processing']//parent::label//child::span//input")
    WebElement without_ReProcessingCTA;

    @FindBy(xpath = "//h3[text()='Split Rules Production History']")
    WebElement ruleFileHistryTitle;

    @FindBy(xpath = "(//span[text()='Open'])[1]")
    WebElement openbtn;

    @FindBy(xpath = "(//td[text()='No. of Records']//following-sibling::td)[1]")
    WebElement currentRuleNumbers;

    @FindBy(xpath = "(//td[text()='No. of Records']//following-sibling::td)[2]")
    WebElement addRuleNumbers;

    @FindBy(xpath = "(//td[text()='No. of Records']//following-sibling::td)[3]")
    WebElement changeRuleNumbers;

    @FindBy(xpath = "(//td[text()='No. of Records']//following-sibling::td)[4]")
    WebElement deletedRuleNumbers;

    @FindBy(xpath = "(//td[text()='No. of Records']//following-sibling::td)[5]")
    WebElement newRuleNumbers;

    @FindBy(xpath = "//span[text()='Duplicate rule ids found: [50705]']")
    WebElement dupplicateRuleIdError;

    @FindBy(xpath = "//h3[text()='Test Report']")
    WebElement splitRuleSimulationPageTitle;

    @FindBy(xpath = "//td[text()='automationtesting']")
    WebElement uploadedByUserName;

    @FindBy(xpath = "//span[text()='Production Rule File Comparison']")
    WebElement splitRulesCompareTitle;

    @FindBy(xpath = "//span[text()='Rule File']")
    WebElement splitRulesFileDownloadCTA;



    public PayoutSplitRulesPage() {
        PageFactory.initElements(driver, this);
    }

    public void toUploadLatestSPlitRuleFile(String fileName){
        String strUrl = driver.getCurrentUrl();
        LogUtils.info("Opened Website: " + strUrl);
        //       TestUtil.click(CreateAccount, "Create an account/Sign in Button Clicked");
        TestUtil.click(configureRulesModule, "Clicked On Configure Rules Module");
        TestUtil.click(payoutSplitRule, "Clicked On Payout Rule Tab");
        TestUtil.click(testAndUploadBtn, "Clicked On Test And Upload Tab");
        WebCommands.staticSleep(1000);
        driver.findElement(By.xpath("(//span[text()='Upload'])[1]//preceding-sibling::input")).sendKeys("//Users//rahulpatil//Documents//Payouts Files//SplitRuleFile//" + fileName + "");
        WebCommands.staticSleep(1000);
        TestUtil.click(commentTxtBox, "Clicked on comment Text Box");
        TestUtil.sendKeys(commentTxtBox,"Automation Test", "Comment Entered");
        TestUtil.click(mainUploadBtn, "Clicked on upload button");
        WebCommands.staticSleep(1000);
        TestUtil.click(startSimulationBtn, "Clicked on Start Test Button");
        WebCommands.staticSleep(1000);
        if(fileName.equalsIgnoreCase("DeleteSplitRules.csv")){
            Assert.assertTrue(splitRuleSimulationPageTitle.isDisplayed());
            Assert.assertTrue(uploadedByUserName.isDisplayed());
            Assert.assertTrue(splitRulesCompareTitle.isDisplayed());
            TestUtil.click(splitRulesFileDownloadCTA, "Clicked on Download CTA");
            Assert.assertEquals(currentRuleNumbers.getText(),"7");
            Assert.assertEquals(addRuleNumbers.getText(),"0");
            Assert.assertEquals(changeRuleNumbers.getText(),"0");
            Assert.assertEquals(deletedRuleNumbers.getText(),"2");
            Assert.assertEquals(newRuleNumbers.getText(),"5");
        } else if (fileName.equalsIgnoreCase("AddNewSplitRules.csv")) {
            Assert.assertEquals(currentRuleNumbers.getText(),"5");
            Assert.assertEquals(addRuleNumbers.getText(),"2");
            Assert.assertEquals(changeRuleNumbers.getText(),"0");
            Assert.assertEquals(deletedRuleNumbers.getText(),"0");
            Assert.assertEquals(newRuleNumbers.getText(),"7");
        } else if (fileName.equalsIgnoreCase("ChangeInSplitRules.csv")) {
            Assert.assertEquals(currentRuleNumbers.getText(),"7");
            Assert.assertEquals(addRuleNumbers.getText(),"0");
            Assert.assertEquals(changeRuleNumbers.getText(),"2");
            Assert.assertEquals(deletedRuleNumbers.getText(),"0");
            Assert.assertEquals(newRuleNumbers.getText(),"7");
        }
        driver.findElement(By.xpath("//html")).sendKeys(Keys.PAGE_DOWN);
        TestUtil.click(reviewReportCheckBox, "Review Report Checkbox clicked");
        TestUtil.click(pushToProdButton, "Push To Production button clicked");
        TestUtil.click(pushToProductionComment, "Clicked on comment Text Box");
        TestUtil.sendKeys(pushToProductionComment,"Automation Test", "Comment Entered");
        WebCommands.staticSleep(2000);
        TestUtil.click(pushToProductionUploadBtn, "Clicked on Final Upload button");
    }

//    public void toUploadRuleFileWithMasterDataFile(String fileName, String MDfileName){
//        String strUrl = driver.getCurrentUrl();
//        LogUtils.info("Opened Website: " + strUrl);
//        TestUtil.click(configureRulesModule, "Clicked On Configure Rules Module");
//        TestUtil.click(payoutsRule, "Clicked On Payout Rule Tab");
//        TestUtil.click(testAndUploadBtn, "Clicked On Test And Upload Tab");
//        Assert.assertEquals(uploadRulePageTitle.getText(),"Test and Upload");
//        WebCommands.staticSleep(2000);
//        TestUtil.click(crntRuleFileBtn, "Current Rule File Downloaded");
//        WebCommands.staticSleep(2000);
//        TestUtil.click(masterDataBtn, "Current Master Data File Downloaded");
//        WebCommands.staticSleep(2000);
//        driver.findElement(By.xpath("(//span[text()='Upload'])[1]//preceding-sibling::input")).sendKeys("//Users//rahulpatil//Documents//Payouts Files//TestRuleFiles//" + fileName + "");
//        WebCommands.staticSleep(1000);
//        TestUtil.click(commentTxtBox, "Clicked on comment Text Box");
//        TestUtil.sendKeys(commentTxtBox,"Automation Test", "Comment Entered");
//        TestUtil.click(mainUploadBtn, "Clicked on upload button");
//        WebCommands.staticSleep(4000);
//        driver.findElement(By.xpath("//span[text()='Test Data (Optional): ']//following-sibling::span//following-sibling::span//preceding-sibling::input")).sendKeys("//Users//rahulpatil//Documents//Payouts Files//TestRuleFiles//" + MDfileName + "");
//        WebCommands.staticSleep(1000);
//        TestUtil.click(startSimulationBtn, "Clicked on Start Test Button");
//        WebCommands.staticSleep(1000);
//        driver.findElement(By.xpath("//html")).sendKeys(Keys.PAGE_DOWN);
//        TestUtil.click(reviewReportCheckBox, "Review Report Checkbox clicked");
//        TestUtil.click(pushToProdButton, "Push To Production button clicked");
//        TestUtil.click(pushToProductionComment, "Clicked on comment Text Box");
//        TestUtil.sendKeys(pushToProductionComment,"Automation Test", "Comment Entered");
//        TestUtil.click(pushToProductionUploadBtn, "Clicked on Upload button");
//    }

    public void uploadDuplicateIdRuleFile(String fileName) {
        String strUrl = driver.getCurrentUrl();
        LogUtils.info("Opened Website: " + strUrl);
        TestUtil.click(configureRulesModule, "Clicked On Configure Rules Module");
        TestUtil.click(payoutSplitRule, "Clicked On Payout Rule Tab");
        TestUtil.click(testAndUploadBtn, "Clicked On Test And Upload Tab");
        WebCommands.staticSleep(2000);
        driver.findElement(By.xpath("(//span[text()='Upload'])[1]//preceding-sibling::input")).sendKeys("//Users//rahulpatil//Documents//Payouts Files//TestRuleFiles//" + fileName + "");
        WebCommands.staticSleep(2000);
        TestUtil.click(commentTxtBox, "Clicked on comment Text Box");
        TestUtil.sendKeys(commentTxtBox,"Automation Test", "Comment Entered");
        TestUtil.click(mainUploadBtn, "Clicked on upload button");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Duplicate rule ids found: [50705]']"))
        );
        Assert.assertEquals(errorMsg.getText(), "Duplicate rule ids found: [50705]");
    }

    public void downloadReportFrmHistory() {
        TestUtil.click(payoutSplitRule, "Clicked On Payout Rule Tab");
        TestUtil.click(ruleHistoryBtn, "Rule button Clicked");
        Assert.assertEquals(ruleFileHistryTitle.getText(),"Split Rules Production History");
        TestUtil.click(openbtn, "Open File button Clicked");
        TestUtil.click(ruleFileButton, "Rule File downloaded from report");
        TestUtil.click(masterDataButtn, "Master Data report downloaded");
        TestUtil.click(closeButtn, "Clicked on Close Button");
    }

}
