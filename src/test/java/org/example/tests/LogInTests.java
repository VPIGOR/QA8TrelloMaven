package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogInTests extends TestBase {


    @BeforeMethod
    public void init() {
        loginPage.openLoginPage()
                .waitUntilPageIsLoaded();
    }

    @Test
    public void loginNegativeTestAllWrongData() {
        loginPage.enterLoginAndPassNoAtl("email@jac.com", "password");
        Assert.assertTrue(loginPage.isDisplayErrorMessage(), "the message doesn't displayd");
    }

    @Test
    public void loginNegativeTestWrongPassword() {
        loginPage.loginExistEmailAnyPass(EMAIL, "password")
                .waitUntilLoginErrorMessageIsPresent();
        Assert.assertTrue(loginPage.isDisplayLoginErrorMessage(), "the message doesn't displayd");
    }

    @Test
    public void loginPossetiveTest() {
        loginPage.loginExistEmailAnyPass(EMAIL, PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        Assert.assertEquals(boardsPage.boardsPageTitle(), "Boards | Trello", "something vrong");
    }


}
