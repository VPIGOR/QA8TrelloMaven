package org.example.tests;

import org.example.util.DataProviders;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class LogInTests extends TestBase {


    @BeforeMethod
    public void init() {
        log4j.startMethod("'LogInTests - init()'");
        loginPage.openLoginPage()
                .waitUntilPageIsLoaded();
    }
// -----------------not work need to check--------------------------------------------------------------------
//    @Test
//    @Parameters({"email", "password"})
//    public void loginNegativeParametresTest(String login, String pass) {
//        loginPage.enterLoginAndPassNoAtl(login, pass)
//                .waitErrorMassage();
//        Assert.assertTrue(loginPage.isDisplayErrorMessage(), "the message doesn't displaed");
//    }
//-------------------------------------------------------------------------------------------------------

    @Test(dataProviderClass = DataProviders.class, dataProvider = "loginNegative")
    public void loginNegativeTestAllWrongData(String login, String pass, String errorMassage) {

        log4j.startTestCase("loginNegativeTestAllWrongData");
        loginPage.enterLoginAndPassNoAtl(login, pass)
                .waitErrorMassage();
        Assert.assertEquals(loginPage.getErrorMessage(), errorMassage, "the message doesn't displayd");
        log4j.endTestCase2();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderThird")
    public void loginNegative(String login, String pass) {
        log4j.startTestCase("loginNegative");
        loginPage.enterLoginAndPassNoAtl(login, pass)
                .waitErrorMassage();
        Assert.assertTrue(loginPage.isDisplayErrorMessage(), "the message doesn't displaed");
        log4j.endTestCase2();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderRandomLogin")
    public void loginNegativeRandom(String login, String pass) {
        log4j.startTestCase("loginNegativeRandom");
        loginPage.enterLoginAndPassNoAtl(login, pass)
                .waitErrorMassage();
        Assert.assertTrue(loginPage.isDisplayErrorMessage(), "the message doesn't displaed");
        log4j.endTestCase2();
    }

    @Test
    public void loginNegativeTestWrongPassword() {
        log4j.startTestCase("loginNegativeTestWrongPassword");
        loginPage.loginExistEmailAnyPass(EMAIL, "password")
                .waitUntilLoginErrorMessageIsPresent();
        Assert.assertTrue(loginPage.isDisplayLoginErrorMessage(), "the message doesn't displayd");
        log4j.endTestCase2();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "loginPositive")
    public void loginPossitiveTest(String login, String pass) {
        log4j.startTestCase("loginPossitiveTest");
        loginPage.loginExistEmailAnyPass(login, pass);
        boardsPage.waitUntilPageIsLoaded();
        Assert.assertEquals(boardsPage.boardsPageTitle(), "Boards | Trello", "something vrong");
        log4j.endTestCase2();
    }


}
