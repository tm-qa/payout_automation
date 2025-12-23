package com.qa.turtlemint.pages.Ninja;

import com.qa.turtlemint.base.TestBase;
import com.qa.turtlemint.commands.WebCommands;
import com.qa.turtlemint.pages.payouts.CycleMovePage;
import com.qa.turtlemint.util.LogUtils;
import com.qa.turtlemint.util.TestUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ninja extends TestBase {

    public String p;
    public String c;

    public String mintproW;
    public String ninjaW;
    public String ninjasignupW;


    JavascriptExecutor js= (JavascriptExecutor)driver;

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

    //------------------------------//

    TestUtil tl = new TestUtil();
    public String mID;
    @FindBy(xpath = "//img[@alt='Plus']")
    WebElement NewSaleButton;
    @FindBy(xpath = "//input[@id='createMISEntry_policyStatus']//parent::span")
    WebElement PolicyIssuanceDropdown;

    @FindBy(xpath = "//input[@id='createMISEntry_policyStatus']//parent::span")
    WebElement PolicyStatusDropdown;

    @FindBy(xpath = "//div[text()='Issued']")
    WebElement issuedStatus;

    @FindBy(xpath = "//input[@id='createMISEntry_insurer']//parent::span")
    WebElement insurerDropdown;

    @FindBy(xpath = "//div[text()='Bajaj Allianz']")
    WebElement insurerBajaj;

    @FindBy(xpath = "//input[@id='createMISEntry_vehicleType']//parent::span")
    WebElement vehicleTypeDropdown;

    @FindBy(xpath = "//div[@id='createMISEntry_vehicleType_list']//following::div//div[text()='TW']")
    WebElement vehicleTypeTW;

    @FindBy(xpath = "//button[text()='Create Sale']")
    WebElement createCTA;

    @FindBy(xpath = "//button[text()='Switch to Manual mode']")
    WebElement switchToManualCTA;

    @FindBy(xpath = "//input[@id='createMISEntry_productCategory']//parent::span")
    WebElement VerticlDropdown;

    @FindBy(xpath = "//div[@title='TW']")
    WebElement selectTW;


    @FindBy(xpath = "//input[@id='createMISEntry_insurer']//parent::span")
    WebElement InsurerDropdown;
    @FindBy(xpath = "//input[@id='createMISEntry_insurer']")
    WebElement InsurerSearch;
    @FindBy(xpath = "//div[@id='createMISEntry_insurer_list']//following-sibling::div[@class='rc-virtual-list']//div[@class='ant-select-item-option-content']")
    WebElement insurerselect;
    @FindBy(xpath = "//input[@id='createMISEntry_vehicleType']//parent::span")
    WebElement VehicleTypeDropdown;
    @FindBy(xpath = "//div[text()='Car']")
    WebElement vehicleTypeSelect;

    @FindBy(xpath = "//button[contains(text(),'Create Sale')]")
    WebElement CreateSale;

    @FindBy(xpath = "//button[text()='Switch to Manual mode']")
    WebElement switchToManualModeCTA;

    @FindBy(xpath = "//label[@title='Turtlemint Broker Code']//parent::div//following-sibling::div//label")
    WebElement brokercode;
    @FindBy(id = "Motor_businessType")
    WebElement busType;
    @FindBy(xpath = "//div[text()='New']")
    WebElement newselect;
    @FindBy(xpath = "//input[@id='Motor_channelType']")
    WebElement channelType;
    @FindBy(xpath = "//div[@title=\"Partner\"]")
    WebElement partnerSelect;
    @FindBy(xpath = "//label[@title='Policy Issuance Date']//parent::div//following-sibling::div//input")
    WebElement PolicyIssuanceDatePicker;
    @FindBy(xpath = "//td[@class='ant-picker-cell ant-picker-cell-in-view ant-picker-cell-today']")
    WebElement PolicyIssuanceDate;
    @FindBy(xpath = "//input[@id='Motor_salesDetail.saleClosedDate']")
    WebElement SaleCloseDatePicker;
    @FindBy(xpath = "//a[text()='Today']")
    WebElement SaleCloseDate;

    @FindBy(xpath = "(//td[@class=\"ant-picker-cell ant-picker-cell-in-view ant-picker-cell-today\"])[4]")
    WebElement salesCloseDate;

    @FindBy(xpath = "//span[text()='Search an Intermediary']/parent::span//input")
    WebElement enterDpNumber;

    @FindBy(xpath = "//div[text()='automation testing - [DP-1585924]']")
    WebElement selectDpNumber;

    @FindBy(xpath = "//input[@type='file']")
    WebElement FileUpload;
    @FindBy(xpath = "//button[@data-auto=\"save-mis-button\"]")
    WebElement parseandSave;
    @FindBy(xpath = "//label[text()='MIS ID']//..//..//input")
    WebElement misID;

    @FindBy(xpath = "//label[text()='Policy Number']//..//..//input")
    WebElement policyNumberCheck;


    @FindBy(xpath = "//input[@data-auto=\"search-input-nav\"]")
    WebElement searchBox;
    @FindBy(xpath = "//b[text()='Action Reqd']//ancestor::td")
    WebElement selectPolicy;
    @FindBy(xpath = "(//button[@class='md-icon-button hover-btn md-button'])[1]")
    WebElement editButton;
    @FindBy(xpath = "//md-select[@id='policyStatus']")
    WebElement policyStatusMis;
    @FindBy(xpath = "//div[text()='Junk']")
    WebElement junkPolicyStatus;
    @FindBy(xpath = "//button[text()='Save Sale']")
    WebElement saveSaleButton;
    @FindBy(xpath = "//button[text()='YES']")
    WebElement yesButton;
    @FindBy(xpath = "//span[text()='Junk']")
    WebElement policyJunkMark;
    @FindBy(xpath = "//input[@name='policyNumber']")
    WebElement PolicyNumber;

    @FindBy(id = "applicationNumber")
    WebElement applicationNumber;

    @FindBy(xpath = "//input[@id='Motor_productName']")
    WebElement productname;
    @FindBy(xpath = "//div[@title='Comprehensive']")
    WebElement comp;

    @FindBy(xpath = "//input[@id='Motor_vehicleSubType']")
    WebElement vehicleSubTypeDD;

    @FindBy(xpath = "//div[text()='Bike']")
    WebElement selectBikeVehicleSubType;

    @FindBy(xpath = "//div[text()='Bike']")
    WebElement selectCarVehicleSubType;

    @FindBy(xpath = "//input[@id='registrationNo']")
    WebElement RegNo;
    @FindBy(xpath = "//input[@id='Motor_manualQCStatus']")
    WebElement dataqc;
    @FindBy(xpath = "//md-option[@value=\"NOT_DONE\"]")
    WebElement notdone;
    @FindBy(xpath = "//input[@id='Motor_proposer.title']//parent::span")
    WebElement proposertitle;
    @FindBy(xpath = "//div[text()='Mr']")
    WebElement mr;
    @FindBy(xpath = "//input[@name='proposer.fName']")
    WebElement prposername;
    @FindBy(xpath = "//input[@name='proposer.mobile']")
    WebElement prposerphone;
    @FindBy(xpath = "//input[@name='proposer.email']")
    WebElement proposeremail;
    @FindBy(xpath = "//input[@name='proposer.address']")
    WebElement regadress;
    @FindBy(xpath = "//input[@name='Registration Pincode']")
    WebElement regpincode;
    @FindBy(xpath = "//input[@id=\"NCB\"]")
    WebElement ncb;
    @FindBy(xpath = "//input[@id=\"SumInsured\"]")
    WebElement idv;
    @FindBy(xpath = "//input[@id=\"BasicODPremium\"]")
    WebElement odPremium;
    @FindBy(xpath = "//input[@id=\"Total OD Premium\"]")
    WebElement netOdPremium;
    @FindBy(xpath = "//input[@id=\"BasicTPPremium\"]")
    WebElement basicTpPremium;
    @FindBy(xpath = "//input[@id=\"TotalTPPremium\"]")
    WebElement tpPremium;
    @FindBy(xpath = "//input[@id=\"NetPremium\"]")
    WebElement netPremium;
    @FindBy(xpath = "//input[@id=\"ServiceTax\"]")
    WebElement serviceTax;
    @FindBy(xpath = "//input[@id=\"GrossPremium\"]")
    WebElement grossPremium;
    @FindBy(xpath = "//input[@id='rc_select_21']")
    WebElement makemodel;
    @FindBy(xpath = "//div[text()=\"Hero Honda Splendor Pro\"]")
    WebElement modelselect;
    @FindBy(id = "variant")
    WebElement variant;
    @FindBy(xpath = "//md-option[@tabindex=\"0\"] //div[contains(text(),'CC')]")
    WebElement variantSelect;
    @FindBy(xpath = "//input[@id=\"manufactureYear\"]")
    WebElement manufactureYear;

    @FindBy(xpath = "//input[@id='cc']")
    WebElement cubicCapacityBox;

    @FindBy(xpath = "//input[@id='fuelType']")
    WebElement fuelTypeBox;


    @FindBy(xpath = "//input[@id='seatingCapacity']")
    WebElement seatingCapacity;

    @FindBy(xpath = "//input[@id=\"engineNo\"]")
    WebElement engineNo;
    @FindBy(xpath = "//input[@id=\"chassisNo\"]")
    WebElement chassisNo;

    @FindBy(xpath = "//input[@id='Motor_startDate']")
    WebElement StartDatePicker;
    @FindBy(xpath = "//input[@id='Motor_endDate']")
    WebElement EndDatePicker;

    @FindBy(xpath = "//span[text()='Policy']")
    WebElement PolicyTag;
    @FindBy(xpath = "//button[text()='OK']")
    WebElement FileTagDone;
    @FindBy(xpath = "(//td[@class=\"ant-picker-cell ant-picker-cell-in-view ant-picker-cell-today\"])[2]")
    WebElement Today;

    @FindBy(xpath = "//input[@type=\"email\"]")
    WebElement emailgoogle;

    @FindBy(xpath = "//input[@type=\"password\"]")
    WebElement passworgoogle;

    @FindBy(xpath = "//input[@type=\"text\"]")
    WebElement empoyeeID;

    @FindBy(xpath = "//md-icon[@class='go-back-icon ng-scope']")
    WebElement backToMIS;

    @FindBy(xpath = "//a[@ng-click='sidemenu = !sidemenu']")
    WebElement menuBtn;

    @FindBy(xpath = "//a[@data-auto='payouts-module']")
    WebElement payouts;

    @FindBy(xpath = "//td[text()='automation testing']")
    WebElement intermediaryName;

    @FindBy(xpath = "(//td[@class='ant-table-cell'])[4]")
    WebElement policyDetailID;

    @FindBy(xpath = "(//td[@class='ant-table-cell'])[5]")
    WebElement policyNumberQS;

    @FindBy(xpath = "(//td[@class='ant-table-cell'])[5]")
    WebElement policyCommissionId;

    //Health
    @FindBy(xpath = "//md-select[@id = 'coverType']")
    WebElement coverTypeDD;

    @FindBy(xpath = "//div[text()='Family floater']")
    WebElement selectCoverType;

    @FindBy(xpath = "//input[@id='tmPlanId']")
    WebElement planName;

    @FindBy(xpath = "//span[text()='Corona Rakshak']")
    WebElement planNameSelect;

    @FindBy(xpath = "//md-select[@id='policyProposerGender']")
    WebElement genderDD;

    @FindBy(xpath = "//div[text()='Male']")
    WebElement selectGender;

    @FindBy(xpath = "//md-datepicker[@name='policyProposerDob']//parent::div//input")
    WebElement dobDatePicker;

    @FindBy(xpath = "//button[text()='Add Member']")
    WebElement addMember;

    @FindBy(xpath = "//md-checkbox[@data-auto='SELF-radio-member']")
    WebElement selfChkbox;

    @FindBy(xpath = "//div[text()='Done']")
    WebElement doneButton;

    @FindBy(xpath = "//div[text()='Pending']")
    WebElement pendingButton;

//    @FindBy(xpath = "//button[text()='Yes']")
//    WebElement yesButton;

    @FindBy(xpath = "//md-select[@id='preExistingDisease']")
    WebElement diseaseDD;

    @FindBy(xpath = "//div[text()='No']")
    WebElement diseaseNoSelect;

    @FindBy(xpath = "//md-datepicker[@name='inceptionDate']")
    WebElement inceptionDatePicker;

    @FindBy(xpath = "//md-select[@id='paymentFrequency']")
    WebElement paymentFrequencyDD;

    @FindBy(xpath = "//div[text()='Monthly']")
    WebElement monthlyPaymentFrequencySelect;

    @FindBy(xpath = "//input[@id='policyPremiumTerm']")
    WebElement policyPayingTerm;

    @FindBy(xpath = "//input[@id='firstInstalment']")
    WebElement firstInstalment;

    @FindBy(xpath = "//input[@id='grossPremium']")
    WebElement grossPremiumBox;

    @FindBy(xpath = "//input[@id='sumInsured']")
    WebElement sumInsured;

    @FindBy(xpath = "//md-select[@id='paymentStatus']")
    WebElement paymentStatusDD;

    @FindBy(xpath = "//div[text()='Completed']")
    WebElement completedPaymentStatusSelect;

    @FindBy(xpath = "//md-select[@id='paymentMode']")
    WebElement paymentModeDD;

    @FindBy(xpath = "//div[text()='Online']")
    WebElement onlineSelect;

    //Life
    @FindBy(xpath = "//md-select[@id='planVariant']")
    WebElement planVariantDD;

    @FindBy(xpath = "//md-option[@data-auto='planVariant-online-select']//div[text()='Online']")
    WebElement planVariantOnlineSelect;


    @FindBy(xpath = "//md-select[@id='subStatus']")
    WebElement subStatusDD;

    @FindBy(xpath = "//div[text()='In force']")
    WebElement InForceSubStatusSelect;

    @FindBy(xpath = "//md-select[@id='category']")
    WebElement categoryDD;

    @FindBy(xpath = "//div[text()='Investment']")
    WebElement categoryInvestmentSelect;

    @FindBy(xpath = "//md-select[@id='planType']")
    WebElement planTypeDD;

    @FindBy(xpath = "//div[text()='Term']")
    WebElement planTypeTermSelect;

    @FindBy(xpath = "//span[text()='Smart Protection Goal']")
    WebElement planNameLiSelect;

    @FindBy(xpath = "//md-select[@id='typeOfBusiness']")
    WebElement clientCategoryDD;

    @FindBy(xpath = "//div[text()='Individual - RI']")
    WebElement clientCategorySelect;

    @FindBy(xpath = "//md-checkbox[@name='sameAsRegistrationAddress']")
    WebElement regiAddCheckBox;

    @FindBy(xpath = "//input[@id='policyTerm']")
    WebElement policyTerm;

    @FindBy(xpath = "//input[@id='benefitPayoutTerm']")
    WebElement benefitPayoutTerm;

    @FindBy(xpath = "//input[@id='modalPremium']")
    WebElement modalPremium;

    @FindBy(xpath = "//input[@id='modalGST']")
    WebElement modalGST;

    @FindBy(xpath = "//md-option[@data-auto='paymentMode-ONLINE-select']//div[text()='Online']")
    WebElement lifePaymentModeSelect;

    @FindBy(xpath = "//input[@id='Motor_insurerRecordStatus']")
    WebElement insurerRecordStatusDropdown;

    @FindBy(xpath = "//div[text()='Present']")
    WebElement insurerRecordStatus_Present;


    public ninja() {
        PageFactory.initElements(driver, this);
    }

//    DBAssertion dbAssertion = new DBAssertion();
    CycleMovePage cmp = new CycleMovePage();

    public static void setPlatformCookie(String value) {
        try {
            Cookie cookie = new Cookie
                    .Builder("PLATFORM", value)
//                    .domain(prop.getProperty("cookieDomain"))
                    .path("/")
                    .isHttpOnly(false)
                    .isSecure(false)
                    .build();

            driver.manage().deleteCookieNamed("PLATFORM");
            driver.manage().addCookie(cookie);

            LogUtils.info("Updated cookie PLATFORM = " + value);
        } catch (Exception e) {
            LogUtils.error("Failed to set cookie PLATFORM", e);
        }
    }



    public static void loginWithMobileAndOTP() {
        try {

            WebElement mobile = driver.findElement(By.xpath("//input[@placeholder=\"Enter Your Mobile Number\"]"));
//            mobile.sendKeys(prop.getProperty("loginMobile"));
            mobile.sendKeys("6999912345");

            driver.findElement(By.xpath("//form[@name=\"mobileNoForm\"]//button[text()=\"Continue\"]")).click();


            WebElement otp = driver.findElement(By.xpath("(//input[@ng-model=\"otp\"])[2]"));
//           otp.sendKeys(prop.getProperty("loginOtp"));
            otp.sendKeys("1234");

            driver.findElement(By.xpath("(//button[text()=\"Submit\"])[2]")).click();

            LogUtils.info("Login completed");
            Thread.sleep(5000);

        } catch (Exception e) {
            LogUtils.error("Login failed", e);
        }
    }


    public void NinjaLogin(String NinjaEmail, String NinjaPassword) throws Exception {
        //               driver.get("https://ninja.turtlemint.com/login");
//
//      //  driver.get(prop.getProperty("pmurl"));
//      //  driver.get(System.getProperty("ninjaurl"));

        setPlatformCookie("APP");
        driver.navigate().refresh();

        loginWithMobileAndOTP();

        setPlatformCookie("WEB_APP");
        driver.navigate().refresh();


//        public static String generateRandomString(int count) { /* ... */ return ""; }
//        public static long generateRandomMobileNumber() { /* ... */ return 0; }

    }


    public void NinjaLoginn(String NinjaEmail, String NinjaPassword) throws Exception {
        driver.get("https://accounts.google.com/");
        WebCommands.staticSleep(3000);

        TestUtil.sendKeys(emailgoogle, "automationtesting@turtlemint.com", "email Id entered");
        TestUtil.sendKeys(emailgoogle, String.valueOf(Keys.RETURN), "email Id entered");
        TestUtil.sendKeys(passworgoogle, "Turtle@202626", "Password entered");
        TestUtil.sendKeys(passworgoogle, String.valueOf(Keys.RETURN), "Password entered");

//        try {
//            Thread.sleep(3000);
//            TestUtil.getScreenShot();
//            TestUtil.sendKeys(empoyeeID, "FBS4825", "Employee ID entered");
//            Thread.sleep(3000);
//            TestUtil.sendKeys(empoyeeID, String.valueOf(Keys.RETURN), "Password entered");
//            Thread.sleep(8000);
//        } catch (Exception e) {
//            System.out.println("********");
//        }

//        TestUtil.sendKeys(empoyeeID, "FBS6032", "Password entered");
//        TestUtil.sendKeys(empoyeeID, String.valueOf(Keys.RETURN), "Password entered");

        WebCommands.staticSleep(8000);
        TestUtil.getScreenShot();
        driver.getWindowHandles().forEach(tab -> driver.switchTo().window(tab));

        // driver.get(System.getProperty("ninjaurl"));
       // driver.get(prop.getProperty("sanityurl"));
//          driver.get(prop.getProperty("limelighturl"));

        TestUtil.click(SignInbtn, "Sign in button clicked");
        WebCommands.staticSleep(5000);
        TestUtil.getScreenShot();
    }

//        public void NinjaLogin(String NinjaEmail, String NinjaPassword) throws Exception {
//
//               driver.get("https://ninja.sanity.turtle-feature.com/login");         ////sanityURL
//              // driver.get("https://ninja.hobbit.turtle-feature.com/login");           ////hobbitURL
//
//            Thread.sleep(2000);
//            LogUtils.info("Ninja Website Opened");
//            SignInbtn.click();
//            LogUtils.info("Clicked on Sign In with Google Button");
//
//            Set<String> allWindowHandles = driver.getWindowHandles();
//            ArrayList<String> tabs = new ArrayList<String>(allWindowHandles);
//            System.out.println("No. of tabs: " + tabs.size());
//
//            if (tabs.size() <= 2) {
//                Set<String> windows = driver.getWindowHandles();
//                Iterator<String> it = windows.iterator();
//                p = it.next();
//                c = it.next();
//                driver.switchTo().window(c);
//            } else {
//                Set<String> windows = driver.getWindowHandles();
//                Iterator<String> it = windows.iterator();
//                mintproW = it.next();
//                ninjaW = it.next();
//                ninjasignupW = it.next();
//                driver.switchTo().window(ninjasignupW);
//            }
//            LogUtils.info("Switched to child window");
//            Email.sendKeys(NinjaEmail);
//            LogUtils.info("Email Entered");
//            Nextbtn1.click();
//            LogUtils.info("Clicked on Next button");
//            Password.sendKeys(NinjaPassword);
//            LogUtils.info("Password entered");
//            Nextbtn2.click();
//            LogUtils.info("Clicked on Next button after password");
//            if (tabs.size() <= 2) {
//                driver.switchTo().window(p);
//            } else {
//                driver.switchTo().window(ninjaW);
//            }
//            WebCommands.staticSleep(10000);
//            LogUtils.info("Switched Back to Parent window");
//            WebCommands.staticSleep(1000);
//            TestUtil.getScreenShot();
//        }

    public void vertical_list(String vertical) {
        List<WebElement> vertical_menu = driver.findElements(By.xpath("//div[contains(@class,'rc-virtual-list-holder-inner')]//div[@class='ant-select-item ant-select-item-option']//div"));
        System.out.println("Vertical Menu = " + vertical_menu.size());
        for (int i = 0; i < vertical_menu.size(); i++) {
            System.out.println(vertical_menu.get(i).getText());
            if (vertical_menu.get(i).getText().contains(vertical)) {
                vertical_menu.get(i).click();
                break;
            }
        }
    }

    public void policy_issuance_status() {
        List<WebElement> Issuance_menu = driver.findElements(By.xpath("//div[contains(@class,'ant-select-dropdown css-txh9fw ant-select-dropdown-placement')]//div[contains(@class,'ant-select-item ant-select-item-option')]//div"));
        System.out.println("Issuance Status = " + Issuance_menu.size());
        for (int j = 0; j < Issuance_menu.size(); j++) {
            System.out.println(Issuance_menu.get(j).getText());
            if (Issuance_menu.get(j).getText().contains("Issued")) {
                Issuance_menu.get(j).click();
                break;
            }
        }
    }

    public void punch_TW_Policy(String misQCStatus) throws Exception {
        driver.get(System.getProperty("ninjaurl")); // Jenkins
//        driver.get(prop.getProperty("sanityninjaurl")); // Local
        driver.findElement(By.xpath("(//a[@data-auto='mis-module'])[2]")).click();
        TestUtil.click(NewSaleButton, "New Sale Button");
        Thread.sleep(1000);
        TestUtil.click(VerticlDropdown, "");
        Thread.sleep(1000);
        TestUtil.click(selectTW,"TW Selected");
//        driver.findElement(By.xpath("//div[@title='TW']")).click();

//        driver.navigate().refresh();
//        TestUtil.click(NewSaleButton, "New Sale Button");
//        TestUtil.click(VerticlDropdown, "");
//        driver.findElement(By.xpath("//div[@title='TW']")).click();

        Thread.sleep(500);
        LogUtils.info("Vertical selected");
        TestUtil.click(PolicyStatusDropdown,"");
        WebCommands.staticSleep(1000);
        TestUtil.click(issuedStatus,"");
        TestUtil.click(insurerDropdown,"");
//        TestUtil.sendKeys(insurerBajaj, "Bajaj", "");
        TestUtil.click(insurerBajaj,"");
        TestUtil.click(vehicleTypeDropdown,"");
        TestUtil.click(vehicleTypeTW,"");
        WebCommands.staticSleep(2000);
        TestUtil.click(createCTA,"");
        TestUtil.click(switchToManualCTA,"");
        WebCommands.staticSleep(2000);
        TestUtil.click(brokercode, "broker code checkmaek click");
        tl.GenerateRegNo();
        PolicyNumber.sendKeys(TestUtil.generateRandomPolicyNo(8) + tl.RegNo);
        TestUtil.click(busType, "bus Type dropdown");
        TestUtil.click(newselect, "New select");
        TestUtil.click(PolicyIssuanceDatePicker, "policy issuance date dropdown");
        Thread.sleep(1000);
        TestUtil.click(PolicyIssuanceDate, "Date select");
        TestUtil.click(proposertitle, "proposer title dropdown");
        TestUtil.click(mr, "mr clicked");
        TestUtil.sendKeys(prposername, "Automation Testing", " proposer name enter");
        TestUtil.sendKeys(prposerphone, "6999912345", " proposer phone enter");
        TestUtil.sendKeys(proposeremail, "automationtesting@turtlemint.com", " proposer email enter");
        TestUtil.sendKeys(regadress, "Automation test", " proposer address enter");
        TestUtil.sendKeys(regpincode, "411001", " proposer pin code enter");
        driver.findElement(By.xpath("//input[@name='proposer.registrationCity']")).click();
        TestUtil.click(productname, "Product name dropdown");
        Thread.sleep(1000);
        TestUtil.click(comp, "comprehensive Select");
        TestUtil.click(vehicleSubTypeDD,"");
        TestUtil.click(selectBikeVehicleSubType,"");
        Thread.sleep(1000);
        RegNo.sendKeys(tl.RegNo);
        driver.findElement(By.xpath("//label[@title='RTO Location']//parent::div//following-sibling::div//input")).click();
        Thread.sleep(1000);
        TestUtil.click(makemodel, "make model clicked");
        makemodel.clear();
        TestUtil.sendKeys(makemodel, "Splendor pro", "make model clicked");
        TestUtil.click(modelselect, "Model Select");
        driver.findElement(By.xpath("//input[@id='cubicCapacity']")).sendKeys("100");
        fuelTypeBox.sendKeys("Petrol");
        TestUtil.sendKeys(manufactureYear, "2023", "manufacture Year enter");
        WebCommands.staticSleep(2000);
        TestUtil.sendKeys(engineNo, TestUtil.generateRandomPolicyNo(7), "engine number enter");
        TestUtil.sendKeys(chassisNo, TestUtil.generateRandomPolicyNo(9), "chassis number enter");
        TestUtil.sendKeys(seatingCapacity, "2", "Seating Capacity 2 enter");
        TestUtil.click(StartDatePicker, "");
        WebCommands.staticSleep(2000);
        TestUtil.click(Today, "Selected for risk start date");
        WebCommands.staticSleep(1000);
        EndDatePicker.click();
        WebCommands.staticSleep(1000);
        EndDatePicker.sendKeys(Keys.ESCAPE);
        WebCommands.staticSleep(1000);
        EndDatePicker.sendKeys(TestUtil.ninjaFutureDate(364));
        WebCommands.staticSleep(1000);
        EndDatePicker.sendKeys(Keys.RETURN);
        WebCommands.staticSleep(1000);
        TestUtil.sendKeys(ncb, "20", "NCB 20 enter");
        TestUtil.sendKeys(idv, "10000", "idv 10000 enter");
        TestUtil.sendKeys(odPremium, "1000", "odPremium enter");
        TestUtil.sendKeys(netOdPremium, "2000", "netOdPremium enter");
        TestUtil.sendKeys(basicTpPremium, "3000", "basicTpPremium enter");
        TestUtil.sendKeys(tpPremium, "4000", "tpPremium enter");
        TestUtil.sendKeys(netPremium, "6000", "netPremium enter");
        TestUtil.sendKeys(serviceTax, "100", "serviceTax ente");
        TestUtil.sendKeys(grossPremium, "6100", "grossPremium enter");
        TestUtil.click(dataqc, "Data QC dropdown");
        WebCommands.staticSleep(2000);

        if(misQCStatus.equalsIgnoreCase("DONE")){
            TestUtil.click(doneButton, "Done select");
            WebCommands.staticSleep(1000);
            driver.findElement(By.xpath("//button[text()='Yes']")).click();
        } else if(misQCStatus.equalsIgnoreCase("PENDING")) {
            TestUtil.click(pendingButton, "Pending selected");
        }
        TestUtil.click(insurerRecordStatusDropdown, "Insurer Record Status dropdown");
        TestUtil.click(insurerRecordStatus_Present, "Present Insurer Record Status Selected");

        TestUtil.click(channelType, "channel Type dropdown");
        TestUtil.click(partnerSelect, "Partner select");
        WebCommands.staticSleep(2000);
        TestUtil.sendKeys(SaleCloseDatePicker, TestUtil.PresentDate(),"sale close date dropdown");
//        WebCommands.staticSleep(500);
        TestUtil.click(salesCloseDate, "Selected sales close date");
        WebCommands.staticSleep(2000);
        enterDpNumber.sendKeys("1585924");
        TestUtil.click(selectDpNumber,"");
        WebCommands.staticSleep(5000);
        cmp.uploadFile("PolicyDocumentPDF");
//        FileUpload.sendKeys("/Users/rahulpatil/Documents/Payouts Files/DocForPolicyPunch/BAJAJ_NEW_tp_MIS_PG2OXPWXZZL.pdf");
        TestUtil.click(PolicyTag, "Policy Tag select");
        TestUtil.click(FileTagDone, "File Tag Done");
        driver.findElement(By.xpath("//button[text()='Save Sale']")).click();
        WebCommands.staticSleep(2000);
        TestUtil.waitUntilVisibilityOfElement(driver.findElement(By.xpath("//div[text()='(View Mode)']")));
    }

    public void MIS_Manual_FW(String vertical, String insurer, String model) throws Exception {
        TestUtil.click(NewSaleButton, "New Sale Button");
        Thread.sleep(1000);
        TestUtil.click(VerticlDropdown, "");
        Thread.sleep(1000);
        vertical_list(vertical);
        Thread.sleep(1000);
        LogUtils.info("Vertical selected");
        TestUtil.click(PolicyIssuanceDropdown, "Policy Issuance Dropdown");
        LogUtils.info("Policy Issuance status selected");
        Thread.sleep(500);
        policy_issuance_status();
        Thread.sleep(500);
        TestUtil.click(InsurerDropdown, "Issuance Dropdown");
        Thread.sleep(1000);
        TestUtil.sendKeys(InsurerSearch, insurer, "Insurer select");
        Thread.sleep(1000);
        TestUtil.click(insurerselect, " bajaj insurer selected");
        Thread.sleep(1000);
        TestUtil.click(VehicleTypeDropdown,"Clicked on Vehicle Type Dropdown");
        Thread.sleep(1000);
        TestUtil.click(vehicleTypeSelect, " Vehicle Type Car Selected");

        TestUtil.click(CreateSale, "Create Sale select");
        LogUtils.info("Clicked on Create Sale button");

//        if(switchToManualModeCTA.isDisplayed()) {
        TestUtil.click(switchToManualModeCTA, "Switch To Manual Mode CTA CLicked");
        WebCommands.staticSleep(1000);
//        } else {
        TestUtil.click(brokercode, "broker code checkmark click");
//        }

        tl.GenerateRegNo();
        PolicyNumber.sendKeys(TestUtil.generateRandomPolicyNo(8) + tl.RegNo);
        TestUtil.click(busType, "bus Type dropdown");
        TestUtil.click(newselect, "New select");
        TestUtil.click(PolicyIssuanceDatePicker, "policy issuance date dropdown");
        Thread.sleep(1000);
        TestUtil.click(PolicyIssuanceDate, "Date select");
        TestUtil.click(proposertitle, "proposer title dropdown");
        TestUtil.click(mr, "mr clicked");
        TestUtil.sendKeys(prposername, "Automation Testing", " proposer name enter");
        TestUtil.sendKeys(prposerphone, "6999912345", " proposer phone enter");
        WebCommands.staticSleep(2000);
        TestUtil.sendKeys(proposeremail, "automationtesting@turtlemint.com", " proposer email enter");
        TestUtil.sendKeys(regadress, "Automation test", " proposer address enter");
        TestUtil.sendKeys(regpincode, "411001", " proposer pin code enter");


        TestUtil.click(channelType, "channel Type dropdown");
        TestUtil.click(partnerSelect, "Partner select");

        TestUtil.click(productname, "Product name dropdown");
        Thread.sleep(1000);
        TestUtil.click(comp, "comprehensive Select");
//        TestUtil.click(vehicleSubTypeDD,"");
//        TestUtil.click(selectCarVehicleSubType,"");
        Thread.sleep(1000);
        RegNo.sendKeys(tl.RegNo);
        TestUtil.click(dataqc, "Data QC dropdown");
        TestUtil.click(notdone, " NOT Done select");
        TestUtil.click(SaleCloseDatePicker, "sale close date dropdown");
        Thread.sleep(1000);
        TestUtil.click(SaleCloseDate, "Date select");
        enterDpNumber.sendKeys("DP - 1585924");
        TestUtil.click(selectDpNumber,"");



        TestUtil.click(StartDatePicker, "");
        WebCommands.staticSleep(2000);
        TestUtil.click(Today, "Selected for risk start date");
        WebCommands.staticSleep(3000);
        EndDatePicker.click();
        WebCommands.staticSleep(5000);
        EndDatePicker.sendKeys(Keys.ESCAPE);
        WebCommands.staticSleep(2000);
        EndDatePicker.sendKeys(TestUtil.ninjaFutureDate(364));
        WebCommands.staticSleep(2000);
        EndDatePicker.sendKeys(Keys.RETURN);
        WebCommands.staticSleep(2000);
        TestUtil.sendKeys(ncb, "5", "NCB 5 enter");
        TestUtil.sendKeys(idv, "10000", "idv 10000 enter");
        TestUtil.sendKeys(odPremium, "1000", "odPremium enter");
        TestUtil.sendKeys(netOdPremium, "2000", "netOdPremium enter");
        TestUtil.sendKeys(basicTpPremium, "3000", "basicTpPremium enter");
        TestUtil.sendKeys(tpPremium, "4000", "tpPremium enter");
        TestUtil.sendKeys(netPremium, "6000", "netPremium enter");
        TestUtil.sendKeys(serviceTax, "100", "serviceTax ente");
        TestUtil.sendKeys(grossPremium, "6100", "grossPremium enter");
        TestUtil.click(makemodel, "make model clicked");
        makemodel.clear();
        TestUtil.sendKeys(makemodel, model, "make model clicked");
        TestUtil.click(modelselect, "Model Select");
        cubicCapacityBox.sendKeys("1000");
        fuelTypeBox.sendKeys("Diesel");
        TestUtil.sendKeys(manufactureYear, "2023", "manufacture Year enter");
        WebCommands.staticSleep(2000);
        TestUtil.sendKeys(engineNo, TestUtil.generateRandomPolicyNo(7), "engine number enter");
        TestUtil.sendKeys(chassisNo, TestUtil.generateRandomPolicyNo(9), "chassis number enter");
//        TestUtil.sendKeys(seatingCapacity, "2", "Seating Capacity 2 enter");
        FileUpload.sendKeys("/Users/rahulpatil/Documents/BAJAJ_NEW_tp_MIS_PG2OXPWXZZL.pdf");
        TestUtil.click(PolicyTag, "Policy Tag select");
        TestUtil.click(FileTagDone, "File Tag Done");
    }

    public void MIS_Manual_Health(String vertical, String insurer) throws Exception {
        TestUtil.click(NewSaleButton, "New Sale Button");
        Thread.sleep(1000);
        TestUtil.click(VerticlDropdown, "");
        Thread.sleep(2000);
        vertical_list(vertical);
        Thread.sleep(2000);
        LogUtils.info("Vertical selected");
        TestUtil.click(PolicyIssuanceDropdown, "Policy Issuance Dropdown");
        LogUtils.info("Policy Issuance status selected");
        Thread.sleep(500);
        policy_issuance_status();
        Thread.sleep(500);
        TestUtil.click(InsurerDropdown, "Issuance Dropdown");
        Thread.sleep(1000);
        TestUtil.sendKeys(InsurerSearch, insurer, "Insurer select");
        Thread.sleep(1000);
        TestUtil.click(insurerselect, " bajaj insurer select");
        TestUtil.click(CreateSale, "Create Sale select");
        LogUtils.info("Clicked on Create Sale button");
        WebCommands.staticSleep(1000);
        TestUtil.click(brokercode, "broker code checkmaek click");
        PolicyNumber.sendKeys(TestUtil.generateRandomPolicyNo(8) + tl.RegNo);
        TestUtil.click(busType, "bus Type dropdown");
        TestUtil.click(newselect, "New select");
        TestUtil.click(channelType, "channel Type dropdown");
        TestUtil.click(partnerSelect, "Partner select");
        TestUtil.click(PolicyIssuanceDatePicker, "policy issuance date dropdown");
        Thread.sleep(1000);
        TestUtil.click(PolicyIssuanceDate, "Date select");
        Thread.sleep(1000);
        TestUtil.click(coverTypeDD,"");
        TestUtil.click(selectCoverType,"");
        planName.clear();
        planName.sendKeys("Corona Rakshak");
        TestUtil.click(planNameSelect,"");
        TestUtil.click(dataqc, "Data QC dropdown");
        TestUtil.click(notdone, " NOT Done select");
        TestUtil.click(SaleCloseDatePicker, "sale close date dropdown");
        Thread.sleep(1000);
        TestUtil.click(SaleCloseDate, "Date select");
        enterDpNumber.sendKeys("DP - 1585924");
        TestUtil.click(selectDpNumber,"");
        TestUtil.click(proposertitle, "proposer title dropdown");
        TestUtil.click(mr, "mr clicked");
        TestUtil.sendKeys(prposername, "Automation Testing", " proposer name enter");
        TestUtil.click(genderDD,"");
        TestUtil.click(selectGender,"");
        dobDatePicker.click();
        WebCommands.staticSleep(5000);
        dobDatePicker.sendKeys(Keys.ESCAPE);
        WebCommands.staticSleep(2000);
//        dobDatePicker.sendKeys(TestUtil.ninjaPastDate(20));
        TestUtil.sendKeys(prposerphone, "6999912345", " proposer phone enter");
        WebCommands.staticSleep(2000);
        TestUtil.sendKeys(proposeremail, "automationtesting@turtlemint.com", " proposer email enter");
        TestUtil.sendKeys(regadress, "Automation test", " proposer address enter");
        TestUtil.sendKeys(regpincode, "411001", " proposer pin code enter");
        TestUtil.click(addMember,"");
        WebCommands.staticSleep(2000);
        TestUtil.click(selfChkbox,"");
        TestUtil.click(doneButton,"");
        WebCommands.staticSleep(2000);
        TestUtil.click(diseaseDD,"");
        TestUtil.click(diseaseNoSelect,"");
        TestUtil.click(inceptionDatePicker, "");
        WebCommands.staticSleep(2000);
        TestUtil.click(Today, "Selected for Inception date");
        WebCommands.staticSleep(3000);
        EndDatePicker.click();
        WebCommands.staticSleep(5000);
        EndDatePicker.sendKeys(Keys.ESCAPE);
        WebCommands.staticSleep(2000);
//        EndDatePicker.sendKeys(TestUtil.ninjaFutureYearDate(3));
        WebCommands.staticSleep(2000);
        EndDatePicker.sendKeys(Keys.RETURN);
        WebCommands.staticSleep(2000);
        TestUtil.click(paymentFrequencyDD,"");
        TestUtil.click(monthlyPaymentFrequencySelect,"");
        policyPayingTerm.sendKeys("12");
        firstInstalment.sendKeys("10000");
        grossPremiumBox.sendKeys("50000");
        sumInsured.sendKeys("100000");
        WebCommands.staticSleep(2000);
        TestUtil.click(paymentStatusDD,"");
        TestUtil.click(completedPaymentStatusSelect,"");
        WebCommands.staticSleep(2000);
        TestUtil.click(paymentModeDD,"");
        TestUtil.click(onlineSelect,"");
        WebCommands.staticSleep(2000);
        FileUpload.sendKeys("/Users/rahulpatil/Documents/BAJAJ_NEW_tp_MIS_PG2OXPWXZZL.pdf");
        TestUtil.click(PolicyTag, "Policy Tag select");
        TestUtil.click(FileTagDone, "File Tag Done");
    }

    public void MIS_Manual_Life(String vertical, String insurer) throws Exception {
        TestUtil.click(NewSaleButton, "New Sale Button");
        Thread.sleep(1000);
        TestUtil.click(VerticlDropdown, "");
        Thread.sleep(2000);
        vertical_list(vertical);
        Thread.sleep(2000);
        LogUtils.info("Vertical selected");
        TestUtil.click(PolicyIssuanceDropdown, "Policy Issuance Dropdown");
        LogUtils.info("Policy Issuance status selected");
        Thread.sleep(500);
        policy_issuance_status();
        Thread.sleep(500);
        TestUtil.click(InsurerDropdown, "Issuance Dropdown");
        Thread.sleep(1000);
        TestUtil.sendKeys(InsurerSearch, insurer, "Insurer select");
        Thread.sleep(1000);
        TestUtil.click(insurerselect, " bajaj insurer select");
        TestUtil.click(CreateSale, "Create Sale select");
        LogUtils.info("Clicked on Create Sale button");

//        if(switchToManualModeCTA.isDisplayed()) {
//            TestUtil.click(switchToManualModeCTA, "Switch To Manual Mode CTA CLicked");
        WebCommands.staticSleep(1000);
//        } else {
//        TestUtil.click(brokercode, "broker code checkmaek click");
//        }
        PolicyNumber.sendKeys(TestUtil.generateRandomPolicyNo(8) + tl.RegNo);
        applicationNumber.sendKeys(TestUtil.generateRandomPolicyNo(8) + tl.RegNo);
        TestUtil.click(channelType, "channel Type dropdown");
        TestUtil.click(partnerSelect, "Partner select");
        TestUtil.click(planVariantDD,"");
        TestUtil.click(planVariantOnlineSelect,"");
        TestUtil.click(PolicyIssuanceDatePicker, "policy issuance date dropdown");
        Thread.sleep(1000);
        TestUtil.click(PolicyIssuanceDate, "Date select");
        TestUtil.click(subStatusDD, "Sub Status dropdown");
        TestUtil.click(InForceSubStatusSelect, "InForce SubStatus Select");
        Thread.sleep(1000);
        TestUtil.click(categoryDD,"");
        TestUtil.click(categoryInvestmentSelect,"");
        Thread.sleep(1000);
        TestUtil.click(planTypeDD,"");
        TestUtil.click(planTypeTermSelect,"");
        planName.sendKeys("Smart Protection Goal");
        TestUtil.click(planNameLiSelect,"");
        TestUtil.click(dataqc, "Data QC dropdown");
        TestUtil.click(notdone, " NOT Done select");
        TestUtil.click(SaleCloseDatePicker, "sale close date dropdown");
        Thread.sleep(1000);
        TestUtil.click(SaleCloseDate, "Date select");
        enterDpNumber.sendKeys("DP - 1585924");
        TestUtil.click(selectDpNumber,"");
        TestUtil.click(clientCategoryDD,"");
        TestUtil.click(clientCategorySelect,"");
        TestUtil.click(proposertitle, "proposer title dropdown");
        TestUtil.click(mr, "mr clicked");
        TestUtil.sendKeys(prposername, "Automation Testing", " proposer name enter");
        TestUtil.click(genderDD,"");
        TestUtil.click(selectGender,"");
        dobDatePicker.click();
        WebCommands.staticSleep(5000);
        dobDatePicker.sendKeys(Keys.ESCAPE);
        WebCommands.staticSleep(2000);
//        dobDatePicker.sendKeys(TestUtil.ninjaPastDate(20));
        TestUtil.sendKeys(prposerphone, "6999912345", " proposer phone enter");
        WebCommands.staticSleep(2000);
        TestUtil.sendKeys(proposeremail, "automationtesting@turtlemint.com", " proposer email enter");
        TestUtil.sendKeys(regadress, "Automation test", " proposer address enter");
        TestUtil.sendKeys(regpincode, "411001", " proposer pin code enter");
        WebCommands.staticSleep(2000);
        TestUtil.click(regiAddCheckBox,"");
        WebCommands.staticSleep(2000);
        TestUtil.click(StartDatePicker, "");
        WebCommands.staticSleep(2000);
        TestUtil.click(Today, "Selected for risk start date");
        WebCommands.staticSleep(3000);
        policyTerm.sendKeys("12");
        WebCommands.staticSleep(2000);
        TestUtil.click(paymentFrequencyDD,"");
        TestUtil.click(monthlyPaymentFrequencySelect,"");
        policyPayingTerm.sendKeys("12");
        benefitPayoutTerm.sendKeys("12");
        modalPremium.sendKeys("1000");
        modalGST.sendKeys("5");
        sumInsured.sendKeys("100000");
        WebCommands.staticSleep(2000);
        TestUtil.click(paymentStatusDD,"");
        TestUtil.click(completedPaymentStatusSelect,"");
        WebCommands.staticSleep(2000);
        TestUtil.click(paymentModeDD,"");
        TestUtil.click(lifePaymentModeSelect,"");
        WebCommands.staticSleep(2000);
        FileUpload.sendKeys("/Users/rahulpatil/Documents/BAJAJ_NEW_tp_MIS_PG2OXPWXZZL.pdf");
        TestUtil.click(PolicyTag, "Policy Tag select");
        TestUtil.click(FileTagDone, "File Tag Done");
    }

    public String CommissionId;

    public void validate_MIS_EntryAtPayouts(String FileName) throws Exception{
        WebCommands.staticSleep(5000);
        js.executeScript("arguments[0].scrollIntoView(true);", misID);
        WebCommands.staticSleep(2000);
        String mID = misID.getAttribute("value");
        String policyNo = policyNumberCheck.getAttribute("value");
        System.out.println("MIS ID : " + mID);
        System.out.println("Policy Number : " + policyNo);
        WebCommands.staticSleep(5000);
        driver.navigate().to("https://ninja.sanity.turtle-feature.com");
        TestUtil.click(payouts,"");
        WebCommands.staticSleep(2000);
//        TestUtil.click(plp.ledgerBtn,"");
        WebCommands.staticSleep(2000);
//        TestUtil.click(plp.quickSearchSectnBtn,"Clicked on Quick Search");
//        plp.misIdTxtbox.sendKeys(mID);
        WebCommands.staticSleep(2000);
//        TestUtil.click(plp.searchButton,"");
        WebCommands.staticSleep(3000);
        Assert.assertEquals(policyDetailID.getText(),mID);
        Assert.assertEquals(policyNumberQS.getText(),policyNo);
        Assert.assertEquals(intermediaryName.getText(),"automation testing");
        TestUtil.getFullPageScreenShot();

//      Validate entry in DB Before Uploading Deviation in LedgerEntity collection
//        dbAssertion.deviation_LE_DB_Validation(mID);
//        CommissionId = dbAssertion.policyComisionId;
        System.out.println(CommissionId);
        String pcid = CommissionId;
        System.out.println(pcid);
//        PayoutLedgerPage payoutLedgerPage = new PayoutLedgerPage();

//      Upload Deviation on Punch Policy
        if(FileName.equals("INCORRECT_RULES")){
            System.out.println("INCORRECT_RULES BLOCK");
//            payoutLedgerPage.deviation("INCORRECT_RULES",pcid);
        } else if (FileName.equals("SPECIAL_REQUEST")) {
            System.out.println("SPECIAL_REQUEST BLOCK");
//            payoutLedgerPage.deviation("SPECIAL_REQUEST",pcid);
        } else {
            System.out.println("NOMINAL_DEVIATION BLOCK");
//            payoutLedgerPage.deviation("NOMINAL_DEVIATION",pcid);
        }
        //payoutLedgerPage.deviation("SPECIAL_REQUEST",pcid);


//      Validate entry in DB After Uploading Deviation in LedgerEntity collection

        if(FileName.equals("INCORRECT_RULES")){
//            dbAssertion.deviation_LE_DB_After_Deviationupload(mID, "INCORRECT_RULES");
        } else if (FileName.equals("SPECIAL_REQUEST")) {
//            dbAssertion.deviation_LE_DB_After_Deviationupload(mID, "SPECIAL_REQUEST");
        } else {
//            dbAssertion.deviation_LE_DB_After_Deviationupload(mID, "NOMINAL_DEVIATION");
        }
    }

    public void junkMarkPolicy() {
        WebCommands.staticSleep(5000);
        TestUtil.click(searchBox, "");
        TestUtil.sendKeys(searchBox, mID, "MIS Id enter in search box");
        searchBox.sendKeys(Keys.RETURN);
        WebCommands.staticSleep(5000);
        TestUtil.click(selectPolicy, "Policy Selected");
        TestUtil.click(editButton, "Clicked On Pen Edit Button");
//        js.executeScript("arguments[0].click()", clientCategory);//
//        js.executeScript("arguments[0].click()", individualRIClient);//
//
//
//        //  TestUtil.click(clientCategory, "Clicked On Client Category Section");
//        // TestUtil.click(individualRIClient, "Individual RI Client Category Selected");
//        TestUtil.sendKeys(benefiitTerm, "1", "Benefit Payout Term 1 given");//
//        TestUtil.sendKeys(commentBox, "Testing", "Entered Comment");//
        js.executeScript("arguments[0].click()", policyStatusMis);//
        js.executeScript("arguments[0].click()", junkPolicyStatus);//
        js.executeScript("arguments[0].click()", saveSaleButton);//
        js.executeScript("arguments[0].click()", yesButton);//
//        TestUtil.click(policyStatusMis, "Clicked on Policy Status DropDown");
//        TestUtil.click(junkPolicyStatus, "Junk Selected Policy Status");
//        TestUtil.click(saveSaleButton, "Clicked on Save Sale Button");
//        TestUtil.click(yesButton, "Yes Button Clicked");
        Assert.assertTrue(policyJunkMark.isDisplayed());
        TestUtil.getScreenShot();
        LogUtils.info("Policy Junk Marked Successfully");

    }
}
