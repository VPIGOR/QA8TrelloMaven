package org.example.tests;


import org.example.pages.CurrentBoardPageHelper;
import org.example.util.DataProviders;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CurrentBoardTests extends TestBase {
    CurrentBoardPageHelper currentBoard;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        log4j.startMethod("'CurrentBoardTests - init()'");
        currentBoard = new CurrentBoardPageHelper(driver, "QA Haifa8");
        loginPage.openLoginPage()
                .loginExistEmailAnyPass(EMAIL, PASSWORD);
        boardsPage.waitUntilPageIsLoaded()
                .enterToCurrentBoard(currentBoard.boardName);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "names")
    public void createNewList(String listName) {
        log4j.startTestCase("createNewList");
        int countOfListsBefore = currentBoard.listsSize();
        currentBoard.addEmptyList(listName);
        int countOfListsAfter = currentBoard.listsSize();
        Assert.assertEquals(countOfListsBefore + 1, countOfListsAfter, "List didn't add");
        log4j.endTestCase2();
    }


    @Test
    public void changeLastListName() {
        log4j.startTestCase("changeLastListName");
        if (currentBoard.listsSize() == 0) currentBoard.addEmptyList("new list");
        currentBoard.changeListName("new changed name", currentBoard.listsSize() - 1);
        Assert.assertEquals(currentBoard.getListName(currentBoard.listsSize() - 1), "new changed name", "THE LIST NAME DIDN'T CHANGE");
        log4j.endTestCase2();
    }

    @Test(groups = {"smoke"})
    public void addCard() {
        log4j.startTestCase("addCard");
        currentBoard.ifBoardsListEmpty()
                .addCard("Good Card", currentBoard.listsSize() - 1);
        Assert.assertEquals(currentBoard.testCards, 1, "Card wasn't added");
        log4j.endTestCase2();
    }

    @Test
    public void deletionList() {
        log4j.startTestCase("deletionList");
        currentBoard.ifBoardsListEmpty();
        int countListsBefore = currentBoard.listsSize();
        currentBoard.dellList(0);
        int countListsAfter = currentBoard.listsSize();
        Assert.assertEquals(countListsBefore - 1, countListsAfter, "List didn't delete");
        log4j.endTestCase2();
    }
}
