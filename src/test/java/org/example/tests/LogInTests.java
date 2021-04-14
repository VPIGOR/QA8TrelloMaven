package org.example.tests;

import org.example.util.DataProviders;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogInTests extends TestBase {


    @BeforeMethod
    public void init() {
        loginPage.openLoginPage()
                .waitUntilPageIsLoaded();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "loginNegative")
    public void loginNegativeTestAllWrongData(String login,String pass,String errorMassage) {
        loginPage.enterLoginAndPassNoAtl(login, pass)
                  .waitErrorMassage();
        Assert.assertEquals(loginPage.getErrorMessage(),errorMassage, "the message doesn't displayd");
    }

    @Test
    public void loginNegativeTestWrongPassword() {
        loginPage.loginExistEmailAnyPass(EMAIL, "password")
                .waitUntilLoginErrorMessageIsPresent();
        Assert.assertTrue(loginPage.isDisplayLoginErrorMessage(), "the message doesn't displayd");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "loginPositive")
    public void loginPossitiveTest(String login,String pass) {
//        loginPage.loginExistEmailAnyPass(EMAIL, PASSWORD);
        loginPage.loginExistEmailAnyPass(login, pass);
        boardsPage.waitUntilPageIsLoaded();
        Assert.assertEquals(boardsPage.boardsPageTitle(), "Boards | Trello", "something vrong");
    }


}
