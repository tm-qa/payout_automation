package com.qa.turtlemint.pages.Ninja;

import com.qa.turtlemint.base.TestBase;
import com.qa.turtlemint.commands.WebCommands;
import com.qa.turtlemint.util.LogUtils;
import com.qa.turtlemint.util.TestUtil;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;



public class ninja extends TestBase {

    public ninja() {
        PageFactory.initElements(driver, this);
    }

    public String p;
    public String c;

    public String reqID;
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @FindBy(xpath = "//span/a[@id='google-signin-button']")
    WebElement SignInbtn;

    @FindBy(xpath = "//input[@type='email']")
    WebElement Email;

    @FindBy(xpath = "//span[text()='Next']")
    WebElement Nextbtn1;

    @FindBy(xpath = "//div[@id='password']/div/div/div/input[@type='password']")
    WebElement Password;

    @FindBy(xpath = "//div[@id='passwordNext']")
    WebElement Nextbtn2;

    @FindBy(xpath = "//input[@placeholder=\"Search by Reg Number, Customer Name, DP Name, DP Phone Number\"]")
    WebElement SearchBar;

    @FindBy(xpath = "//a[contains(text(),'Policy Issuance')]")
    WebElement PolicyIssuance;

    @FindBy(xpath = "//span[text()='Action Reqd']")
    WebElement ActionReq;

    @FindBy(xpath = "//button[text()='OWN']")
    WebElement OWNbtn;

    @FindBy(xpath = "//button[normalize-space()='Save Issuance']")
    WebElement saveissuance;

    @FindBy(xpath = "//md-select[@id=\"policyIssuanceStatus\"]")
    WebElement policystatus;

    @FindBy(xpath = "//md-option[@data-auto=\"policyIssuanceStatus-CANCELLED-select\"]")
    WebElement Canceeled;

    @FindBy(xpath = "//textarea[@id=\"cancellationReason\"]")
    WebElement cancelledReson;

    @FindBy(xpath = "//button[@data-auto=\"close-pi-button\"]")
    WebElement closeButton;

    @FindBy(id = "businessType")
    WebElement selectbusinessType;

    @FindBy(xpath = "//div[text()='New']")
    WebElement new_Buss_select;

    @FindBy(xpath = "//div//md-datepicker[@id='paymentCompleteDate']")
    WebElement completepaymentdate;

    @FindBy(xpath = "//td[@class='md-calendar-date md-calendar-date-today md-focus']")
    WebElement selectCurrentdate;

    @FindBy(xpath = "//td[text()='CANCELLED']")
    WebElement CANCELLED;

    @FindBy(xpath = "//md-select[@id=\"issuanceSubStatus\"]")
    WebElement SubStatus;

    @FindBy(xpath = "//md-option[@data-auto=\"issuanceSubStatus-CANCELLED-select\"]")
    WebElement subcancelled;

    @FindBy(xpath = "//span[text()='(View Mode)']")
    WebElement viewDetails;

    @FindBy(xpath = "//span[@class=\"subTitle ng-binding\"]")
    WebElement subtilel;

    @FindBy(xpath = "//input[@id=\"requestId\"]")
    WebElement reqid;

    @FindBy(xpath = "//md-select[@id='premiumPaymentFrequency']")//
    WebElement paymentFreq;

    @FindBy(xpath = "//div[text()='Monthly']")//
    WebElement selectPaymentFreq;

    @FindBy(xpath = "//input[@id='tmPlanId']")//
    WebElement planNameDrpDwn;

    @FindBy(xpath = "//li[@role=\"option\"]")//
    WebElement SelectPlanName;

    @FindBy(xpath = "//input[@id='planName']")//
    WebElement planNameCuctom;

    @FindBy(xpath = "//md-select[@id=\"category\"]")
    WebElement category;

    @FindBy(xpath = "//input[@type=\"email\"]")
    WebElement emailgoogle;

    @FindBy(xpath = "//input[@type=\"password\"]")
    WebElement passworgoogle;

    public void NinjaLogin() {
        driver.get("https://accounts.google.com/");
        WebCommands.staticSleep(3000);

        TestUtil.sendKeys(emailgoogle, "automationtesting@turtlemint.com", "email Id entered");
        TestUtil.sendKeys(emailgoogle, String.valueOf(Keys.RETURN), "email Id entered");
        TestUtil.sendKeys(passworgoogle, "Turtle@2k25", "Password entered");
        TestUtil.sendKeys(passworgoogle, String.valueOf(Keys.RETURN), "Password entered");
        WebCommands.staticSleep(8000);
        TestUtil.getScreenShot();
        // driver.getWindowHandles().forEach(tab -> driver.switchTo().window(tab));

           driver.get(System.getProperty("ninjaurl"));
        // driver.get(prop.getProperty("url"));
       // driver.get("https://ninja.turtlemint.com/login");
        System.out.println(driver.getCurrentUrl());
        WebCommands.staticSleep(2000);
        TestUtil.click(SignInbtn, "Sign in button clicked");
        WebCommands.staticSleep(10000);
        TestUtil.getScreenShot();
    }

    public boolean NinjaLoginCheck() {
        try {


            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public void cancelpolicyThroughNinja(String u_ID) throws Exception {
        WebCommands.staticSleep(3000);
        driver.get(System.getProperty("ninjaurl"));
        WebCommands.staticSleep(4000);
        try {
            PolicyIssuance.isDisplayed();
        } catch (Exception e){
            Thread.sleep(2000);

            WebCommands.staticSleep(5000);
            System.out.println(u_ID);
            System.out.println("Ninja not logged in");
            NinjaLogin();
        }

        Thread.sleep(2000);
        PolicyIssuance.click();
        LogUtils.info("Clicked on PolicyIssuance btn");
        Thread.sleep(3500);
        SearchBar.click();
        WebCommands.staticSleep(1000);
        SearchBar.sendKeys(u_ID);
        LogUtils.info("Clicked on Searchbar and put unique ID");
        Thread.sleep(2000);
        SearchBar.sendKeys(Keys.RETURN);
        LogUtils.info("Enter on Search bar");
        WebCommands.staticSleep(4000);
        TestUtil.getScreenShot();
        try {
            CANCELLED.isDisplayed();
            Assert.assertEquals(CANCELLED.getText(), "CANCELLED");
        } catch (Exception e) {
            TestUtil.click(ActionReq, "Clicked On action required for own");
            TestUtil.click(OWNbtn, "Clicked on OWN button");
            WebCommands.staticSleep(10000);
            reqID = reqid.getAttribute("value");
            TestUtil.click(policystatus, "policy status click");
            TestUtil.click(Canceeled, "Cancelled selected");
            WebCommands.staticSleep(2000);
            TestUtil.sendKeys(cancelledReson, "Policy cancelled by Automation Testing", "Cancelled");
            WebCommands.staticSleep(1000);
            TestUtil.click(saveissuance, "Save successfully");//
            WebCommands.staticSleep(3000);
            js.executeScript("arguments[0].click()", paymentFreq);//
            TestUtil.click(selectPaymentFreq, "Select Monthly Payment Frequency");//
            WebCommands.staticSleep(2000);

            WebCommands.staticSleep(1000);//

            planNameDrpDwn.clear();

            TestUtil.click(planNameDrpDwn, "Entered ot in Dropdown");//
            WebCommands.staticSleep(2000);
            TestUtil.click(SelectPlanName, "Plan name Selected as Other");
            WebCommands.staticSleep(1000);
            TestUtil.click(SubStatus, "Substatus");
            TestUtil.click(subcancelled, "Substatus cancelled");
            WebCommands.staticSleep(1000);
            TestUtil.click(saveissuance, "Save successfully for inspection");
            WebCommands.staticSleep(2000);
            //  if ( subtilel.getText().contains("View Mode")){
            viewDetails.isDisplayed();
            TestUtil.getScreenShot();
            //      }
//            else {
//                category.click();
//            }
            TestUtil.click(closeButton, "close button clicked");
            WebCommands.staticSleep(2000);
            TestUtil.getScreenShot();

        }

    }
    public static ArrayList<String> listOfPolicies = new ArrayList<String>();
    public static ArrayList<String> Cancelled_Policies = new ArrayList<String>();
    public static ArrayList<String> Uncancelled_Policies = new ArrayList<String>();

    public void AddID(String UID) {
        listOfPolicies.add(UID);
    }



    public void print_ids(ArrayList list,String text){
        for (Object print:list){
            System.out.println("************--"+text+ " Policy List--***********"+print);
        }
    }

    public void GetID() {
        System.out.println("Number of order ID=  " + listOfPolicies.size());

        for (int i = 0; i < listOfPolicies.size(); i++) {
            if (listOfPolicies.size() == 0) {
                System.out.println(" NO Entry created in Ninja");
            } else {
                try {
                    System.out.println("Id for Ninja Canclation  " + listOfPolicies.get(i));
                    cancelpolicyThroughNinja(listOfPolicies.get(i));
                    System.out.println("Ninja Cancle Policy Flow Completed for ID= " + listOfPolicies.get(i));
                    Allure.description("Ninja Cancle Policy Flow Completed for ID= " + listOfPolicies.get(i));
                    Cancelled_Policies.add(listOfPolicies.get(i));

                } catch (Exception e) {
                    System.out.println("Unable to cancle Policy  ID= " + listOfPolicies.get(i));
                    Allure.description("Unable to cancle Policy  ID= " + listOfPolicies.get(i));
                    Uncancelled_Policies.add(listOfPolicies.get(i));
                }

            }

        }
        System.out.println("###################################################################");
        System.out.println("                                                                   ");
        print_ids(Cancelled_Policies,"Canceeled");
        System.out.println("                                                                   ");
        System.out.println("                                                                   ");
        print_ids(Uncancelled_Policies,"UNCancelled");
        System.out.println("###################################################################");
        System.out.println("                                                                   ");
    }

}
