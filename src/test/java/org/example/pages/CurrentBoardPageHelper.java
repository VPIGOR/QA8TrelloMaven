package org.example.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CurrentBoardPageHelper extends PageBase {
    @FindBy(css = "[class='placeholder']")
    WebElement placHolder;
    @FindBy(className = "list-name-input")
    WebElement listNameField;
    @FindBy(css = ".js-close-list")
    WebElement arhiveButton;
    @FindBy(css = ".js-cancel-edit")
    WebElement cancelEditButton;
    @FindBy(css = "[type='submit']")
    WebElement submitNameButton;
    @FindBy(css = ".js-cancel")
    WebElement xCardButton;
    @FindBy(css = ".js-add-card")
    WebElement addCardButton;
    @FindBy(css = ".js-card-title")
    WebElement cardTitle;
    @FindBy(xpath = "//div[@class='js-list list-wrapper']")
    List<WebElement> wrapperList;
    @FindBy(xpath = "//a[@class='list-card js-member-droppable ui-droppable']")
    List<WebElement> cardList;
    @FindBy(xpath = "//div[@class='list-header js-list-header u-clearfix is-menu-shown']")
    List<WebElement> listOfListsNameField;
    @FindBy(xpath = "//a[@class='open-card-composer js-open-card-composer']")
    List<WebElement> listOfCreateCardButton;
    @FindBy(css = ".list-header-name")
    List<WebElement> listHeaderName;
    @FindBy(css = ".list-header-extras-menu")
    List<WebElement> listOfMenueButton;

    public int testLists;
    public int testCards;
    public String boardName;

    public CurrentBoardPageHelper(WebDriver driver, String boardName) {
        super(driver);
        this.boardName = boardName;
        PageFactory.initElements(driver, this);
    }

    public CurrentBoardPageHelper addEmptyList(String name) {
        int countListsBefore = listsSize();
        ClickAddListButton();
        enterListName(name);
        clickSubmitChangeNameButton();
        clickCancelEditButton();
        int countListsAfter = listsSize();
        testLists = countListsAfter - countListsBefore;
        return this;
    }

    public CurrentBoardPageHelper addCard(String cardName, int listNum) {
        int countOfCardBefore = cardSize();
        clickCreateCardButton(listNum);
        enterNameOfCard(cardName);
        clickAddCreatedCardButton();
        closeAddCardIcon();
        int countOfCardsAfter = cardSize();
        testCards = countOfCardsAfter - countOfCardBefore;
        return this;
    }

    public CurrentBoardPageHelper dellList(int listNum) {
        int countListsBefore = listsSize();
        clickMenuOflistButton(listNum);
        archiveThisList();
        int countListsAfter = listsSize();
        testLists = countListsBefore - countListsAfter;
        return this;
    }

    private CurrentBoardPageHelper clickCancelEditButton() {
        cancelEditButton.click();
        waitUntilElementIsVisable(placHolder, 5);
        return this;
    }

    public CurrentBoardPageHelper clickSubmitChangeNameButton() {
        submitNameButton.click();
        return this;
    }

    public CurrentBoardPageHelper enterListName(String name) {
        fillField(listNameField, name);
        return this;
    }

    public CurrentBoardPageHelper ClickAddListButton() {
        placHolder.click();
        waitUntilElementIsVisable(listNameField, 5);
        return this;
    }

    public int listsSize() {
        return wrapperList.size();
    }

    public int cardSize() {
        return cardList.size();
    }

    public CurrentBoardPageHelper ifBoardsListEmpty() {
        if (listsSize() == 0) {
            addEmptyList("first list");
        }
        return this;
    }

    public CurrentBoardPageHelper changeListName(String listName, int listNum) {
        WebElement lastList = listHeaderName.get(listNum);
        lastList.clear();
        lastList.sendKeys(listName);
        lastList.sendKeys(Keys.ENTER);
        driver.navigate().refresh();
        return this;
    }

    public String getListName(int listNum) {
        return listOfListsNameField.get(listNum).getText();
    }

    private CurrentBoardPageHelper closeAddCardIcon() {
        xCardButton.click();
        driver.navigate().refresh();
        return this;
    }

    private CurrentBoardPageHelper clickAddCreatedCardButton() {
        addCardButton.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return this;
    }

    private CurrentBoardPageHelper enterNameOfCard(String cardName) {
        fillField(cardTitle, cardName);
        return this;
    }

    private CurrentBoardPageHelper clickCreateCardButton(int listNum) {
        listOfCreateCardButton.get(listNum).click();
        return this;
    }

    private CurrentBoardPageHelper archiveThisList() {
        arhiveButton.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return this;
    }

    private CurrentBoardPageHelper clickMenuOflistButton(int listNum) {
        listOfMenueButton.get(listNum).click();
        waitUntilElementIsVisable(arhiveButton, 5);
        return this;
    }
}
