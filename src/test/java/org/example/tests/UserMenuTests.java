package org.example.tests;

import org.example.pages.CurrentBoardPageHelper;
import org.example.pages.UserMenuPageHelper;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserMenuTests extends TestBase {
    UserMenuPageHelper userHelper;
    CurrentBoardPageHelper currentBoard;

    @BeforeMethod
    public void init() {
        userHelper = PageFactory.initElements(driver, UserMenuPageHelper.class);
        loginPage.openLoginPage()
                .loginExistEmailAnyPass(EMAIL, PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        userHelper.enterToMemberMenu();
    }

    @Test
    public void emailVisabilityTest() {
        Assert.assertEquals(userHelper.getEmail(), EMAIL, "it's not my mail");
    }

    @Test
    public void activityTest() {
        currentBoard = new CurrentBoardPageHelper(driver, "QA Haifa8");
        boardsPage.waitUntilPageIsLoaded()
                .enterToCurrentBoard(currentBoard.boardName);
        currentBoard.addEmptyList("for activity");
        String activityText =
                userHelper
                        .enterToMemberMenu()
                        .enterToactivity()
                        .getLasttActivity();
        System.out.println(activityText);
        Assert.assertTrue(activityText.contains("Igor added list for activity to QA Haifa8"), "wrong activity");

    }

    @Test
    public void memberTitleTest() {
        String title =
                userHelper
                        .enterToProfile()
                        .getMemberName();
        Assert.assertEquals(ACCOUNTNAME, title, "wrong account name");

    }
}
