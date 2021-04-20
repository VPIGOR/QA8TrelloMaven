package org.example.tests;

import org.example.pages.ActivityPageHelper;
import org.example.pages.CurrentBoardPageHelper;
import org.example.pages.UserMenuPageHelper;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ActivityTests extends TestBase {

    UserMenuPageHelper userHelper;
    CurrentBoardPageHelper currentBoard;
    ActivityPageHelper activityHelper;

    @BeforeMethod
    public void init() {
        activityHelper = PageFactory.initElements(driver, ActivityPageHelper.class);
        userHelper = PageFactory.initElements(driver, UserMenuPageHelper.class);
        loginPage.openLoginPage()
                .loginExistEmailAnyPass(EMAIL, PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        userHelper.enterToMemberMenu()
                .enterToactivity();

    }


    @Test()
    public void activityTest() {

        currentBoard = new CurrentBoardPageHelper(driver, "QA Haifa8");
        boardsPage
                .returnToMainPage()
                .waitUntilPageIsLoaded()
                .enterToCurrentBoard(currentBoard.boardName);
        currentBoard
                .ifBoardsListEmpty()
                .addCard("card for activity", 0);
        userHelper
                .enterToMemberMenu()
                .enterToactivity();
        String activityText = activityHelper.getLasttActivity();

        Assert.assertTrue(activityText.contains("Igor added card for activity"), "wrong activity");

    }
}
