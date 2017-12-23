import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;
import sun.text.normalizer.Utility;

import java.io.File;

public class TestLoginScreen {
    WebDriver driver;
    ExtentReports reports;
    ExtentTest logger;

    @BeforeTest

    public void startReport(){
        //ExtentReports(String filePath,Boolean replaceExisting)
        //filepath - path of the file, in .htm or .html format - path where your report needs to generate.
        //replaceExisting - Setting to overwrite (TRUE) the existing file or append to it
        //True (default): the file will be replaced with brand new markup, and all existing data will be lost. Use this option to create a brand new report
        //False: existing data will remain, new tests will be appended to the existing report. If the the supplied path does not exist, a new file will be created.
        reports = new ExtentReports (System.getProperty("user.dir") +"/test-output/remomedi_automation_report.html", true);
        //extent.addSystemInfo("Environment","Environment Name")
        reports
                .addSystemInfo("Host Name", "Remomedi Web")
                .addSystemInfo("Environment", "UAT Server")
                .addSystemInfo("User Name", "Pratibh Acharya");
        //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
        //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
      //  reports.loadConfig(new File(System.getProperty("user.dir")+"//extent-config.xml"));
      // logger = reports.startTest("Login Page");
//
//        // OPEN CHROME BROWSER
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        logger.log(LogStatus.INFO,"Open Chrome Browser !! ");
//
//        //OPEN REMOMEDI URL IN CHROME BROWSER
//        driver.get("https://remomedi-dev.westeurope.cloudapp.azure.com/");
//        logger.log(LogStatus.INFO,"Open Remomedi Login Page.");
//
//        //GET WEBPAGE TITLE AND VERIFY IF IT CONTAINS THE TEXT REMOMEDI
//        String title = driver.getTitle();
//        Assert.assertTrue(title.contains("Remomedi"));
//        logger.log(LogStatus.INFO,"Webpage Title Verified");
    }
    @AfterMethod
    public void getResult(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            // logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
            logger.log(LogStatus.FAIL, "Test failed with error: '\n "+result.getThrowable());
        }else if(result.getStatus() == ITestResult.SKIP){
            logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
        }
        // ending test
        //endTest(logger) : It ends the current test and prepares to create HTML report
        reports.endTest(logger);
    }
    @AfterTest
    public void endReport(){
        // writing everything to document
        //flush() - to write or update test information to your report.
        reports.flush();
        //Call close() at the very end of your session to clear all resources.
        //If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.
        //You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream.
        //Once this method is called, calling any Extent method will throw an error.
        //close() - To close all the operation
        reports.close();
    }
    @Test
    public void testLogin()  {

        //GIVE NECESSARY PATH TO FIND CHROMEDRIVER
        System.setProperty("webdriver.chrome.driver", "/Users/pratibh/IdeaProjects/Remomedi Test Automation/Drivers/chromedriver");

        //LOG FOR THE NAME OF THE TEST THAT IS BEING PERFORMED
        logger = reports.startTest("Automation Test");

        // OPEN CHROME BROWSER
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logger.log(LogStatus.INFO,"Open Chrome Browser");

        //OPEN REMOMEDI URL IN CHROME BROWSER
        driver.get("https://remomedi-dev.westeurope.cloudapp.azure.com/");
        logger.log(LogStatus.INFO,"Open Remomedi Application");

        //GET WEBPAGE TITLE AND VERIFY IF IT CONTAINS THE TEXT REMOMEDI

        //Assert.assertTrue(title.contains("Remomedia"));

        try{
            String title = driver.getTitle();
            logger.log(LogStatus.INFO,"Remomedi Title Verification ");
            title.contains("Remomedia");
        }catch (java.lang.Exception e){
            throw new AssertionError("A clear description of the failure", e);
        }

        //  logger.log(LogStatus.INFO,"Verify the title of the page as Remomedi: ");


        logger.log(LogStatus.INFO,"  Login to the system using valid username and password ");

        driver.findElement(By.xpath("/html/body/app-root/app-login/div/div/div/div/div/div/div/div/div[2]/form/div[1]/div/input")).sendKeys("pratibh.smartmobe@gmail.com");

        logger.log(LogStatus.INFO,"  Login to the system using valid username and password ");

        driver.findElement(By.xpath("//label[text()='User Name:']/following::div/input")).sendKeys(Keys.TAB);
        driver.findElement(By.xpath("/html/body/app-root/app-login/div/div/div/div/div/div/div/div/div[2]/form/div[1]/div/input")).sendKeys("123456");
        driver.findElement(By.xpath("/html/body/app-root/app-login/div/div/div/div/div/div/div/div/div[2]/form/div[2]/div/input")).sendKeys(Keys.TAB);

        driver.findElement(By.xpath("/html/body/app-root/app-login/div/div/div/div/div/div/div/div/div[2]/form/div[3]/button")).click();

        try {
            driver.wait(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         driver.close();
        //Create an instance of extent reports
    }

//    @Test
//    public void passTest(){
//        //extent.startTest("TestCaseName", "Description")
//        //TestCaseName – Name of the test
//        //Description – Description of the test
//        //Starting test
//        logger = reports.startTest("Pharmacy User Login ");
//        Assert.assertTrue(true);
//        //To generate the log when the test case is passed
//        logger.log(LogStatus.PASS, "");
//    }
//
//    @Test
//    public void failTest(){
//        logger = reports.startTest("Failed Tests");
//        Assert.assertTrue(true);
//        logger.log(LogStatus.PASS, "..");
//    }
//    @Test
//    public void skipTest(){
//        logger = reports.startTest("skipTest");
//        throw new SkipException("Skipping - This is not ready for testing ");
//    }
//




   /* @AfterMethod
    public void getResult(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            logger.log(LogStatus.INFO, "Test Case Failed is "+result.getName());
            logger.log(LogStatus.INFO, "Test Case Failed is "+result.getThrowable());
        }else if(result.getStatus() == ITestResult.SKIP){
            logger.log(LogStatus.INFO, "Test Case Skipped is "+result.getName());
        }
        // ending test
        //endTest(logger) : It ends the current test and prepares to create HTML report
        reports.endTest(logger);
    }
    @AfterTest
    public void endReport(){
        // writing everything to document
        //flush() - to write or update test information to your report.
        reports.flush();
        //Call close() at the very end of your session to clear all resources.
        //If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.
        //You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream.
        //Once this method is called, calling any Extent method will throw an error.
        //close() - To close all the operation
        reports.close();
    }*/
}
