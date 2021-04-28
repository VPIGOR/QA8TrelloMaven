package org.example.tests;

import org.example.pages.SupportPageHelper;
import org.example.pages.UserMenuPageHelper;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SupportPageTest extends TestBase {

    UserMenuPageHelper userHelper;
    SupportPageHelper supportHelper;

    @BeforeMethod(alwaysRun = true)
    public void init()  {
        supportHelper = PageFactory.initElements(driver, SupportPageHelper.class);
        userHelper = PageFactory.initElements(driver, UserMenuPageHelper.class);
        loginPage
                .openLoginPage()
                .loginExistEmailAnyPass(EMAIL, PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        userHelper
                .enterToMemberMenu()
                .clickHelpButton();
        supportHelper
                .waitHelpPageLoaded()
                .goToHelpMenuWindow();
    }

    @Test(groups = {"smoke"})
    public void supportPageTest(){
        Assert.assertEquals(supportHelper.titleHelpWindow(), "Contact Support", "not Help page");
    }

    @Test(groups = {"regression"})
    public void mainPageExistTest() {
        supportHelper
                .closeHelpWindow()
                .goToMainWindow();
        Assert.assertEquals(boardsPage.boardsPageTitle(), "Boards | Trello", "not main page");
    }

}
