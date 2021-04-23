package org.example.tests;

import com.google.common.io.Files;
import org.example.SuiteConfiguration;
import org.example.pages.BoardsPageHelper;
import org.example.pages.HomePageHelper;
import org.example.pages.LoginPageHelper;
import org.example.util.LogLog4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.selenium.factory.WebDriverPool;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;


/**
 * Base class for TestNG-based test classes
 */
public abstract class TestBase {

    public static LogLog4j log4j = new LogLog4j();
    Logger logger = LoggerFactory.getLogger(TestBase.class);


    protected static URL gridHubUrl = null;
    protected static String baseUrl;
    protected static Capabilities capabilities;

    protected EventFiringWebDriver driver;

    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;

    public static final String EMAIL = "hadashqa@gmail.com";
    public static final String PASSWORD = "starQA21";
    public static final String ACCOUNTNAME = "Igor";

    //------------------------------loger class----------------------------------------------
    public static class Mylistener extends AbstractWebDriverEventListener {

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            log4j.info1(by + " element was found");
        }
        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            log4j.error("Error: " + throwable);
            getScreenshot((TakesScreenshot) driver);
        }
    }

    private static void getScreenshot(TakesScreenshot driver) {
        File tmp = driver.getScreenshotAs(OutputType.FILE);
        File screen = new File("screen-"+System.currentTimeMillis()+".png");
        try {
            Files.copy(tmp,screen);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log4j.info(""+screen);
    }


    @BeforeSuite
    public void initTestSuite() throws IOException {

        log4j.startMethod("'TestBase - initTestSuite()'");
        SuiteConfiguration config = new SuiteConfiguration();
        baseUrl = config.getProperty("site.url");
        if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
            gridHubUrl = new URL(config.getProperty("grid.url"));
        }
        capabilities = config.getCapabilities();
    }

    @BeforeMethod
    public void initWebDriver(Method m, Object[] p) {
//-------------------logger start --------------------------------------
        logger.info("Start test: " + m.getName());
        if (p.length != 0) {
            logger.info(" --> With data: " + Arrays.asList(p));
        }
//----------------------------------------------------------------------
        log4j.startMethod("'TestBase - initWebDriver()'");
//        driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
        driver = new EventFiringWebDriver(WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities));
        driver.register(new Mylistener());
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        homePage = PageFactory.initElements(driver, HomePageHelper.class);

        driver.get(baseUrl);
        homePage.waitUntilPageIsLoaded();
    }
    @AfterMethod
    public void finishTest(ITestResult result){
       if(result.getStatus()==ITestResult.FAILURE){
           log4j.error("!!!!!!!!!!!!!----Test failed----!!!!!!!!!!!!!!");
           getScreenshot((TakesScreenshot) driver);

       }

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        WebDriverPool.DEFAULT.dismissAll();
//-------------------logger write--------------------------------------------
        if (result.isSuccess()) {
            logger.info("Test result: PASSED");
        } else {
            logger.error("Test result: FAILED");
        }
        logger.info("Stop test: " + result.getMethod().getMethodName());
        logger.info("======================================================");
//------------------------------------------------------------------------------
    }
}
