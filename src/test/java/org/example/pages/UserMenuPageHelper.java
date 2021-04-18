package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class UserMenuPageHelper extends PageBase {
    @FindBy(xpath = "//button[@aria-label='Close popover']")
    WebElement memberMenue;
    @FindBy(xpath = "//span[@class='_2TvKKP0vwCN5Zd']")
    WebElement emailText;
    @FindBy(css = "[aria-label='Open member menu']")
    WebElement memButton;
    @FindBy(css = "[class='lcLi2SyiMFND91']")
    List<WebElement> memberHedersList;
    @FindBy(css = "[class='window-module-title-icon icon-lg icon-organization']")
    WebElement workspacesActivity;
    @FindBy(css = "[class='phenom mod-attachment-type']")
    List<WebElement> listActions;
    @FindBy(css = "[class='_2Hw6DOX63Ah-JJ']")
    WebElement pofilePage;
    @FindBy(css = "[class='_32mB-ZO8fxjtUy']")
    WebElement memberName;
    @FindBy(css = "[target='_blank']")
    WebElement helpButton;

    public UserMenuPageHelper(WebDriver driver) {
        super(driver);
    }


    public UserMenuPageHelper clickHelpButton(){
        helpButton.click();
        return this;
    }
    public String getEmail() {
        return emailText.getText();
    }

    public UserMenuPageHelper enterToMemberMenu() {
        memButton.click();
        return this;
    }
    public UserMenuPageHelper enterToactivity() {
        memberHedersList.get(2).click();
        waitUntilElementIsVisable(workspacesActivity,3);
        return this;
    }
    public UserMenuPageHelper enterToProfile() {
        memberHedersList.get(1).click();
        waitUntilElementIsVisable(pofilePage,3);
        return this;
    }
    public String getMemberName() {
        return memberName.getText();
    }

    public String getLasttActivity() {
        return listActions.get(0).getText();
    }



    public void waitHelpPageLoaded(){
        waitUntilWindowIsOpened(2,3);
    }

    public void goToHelpMenuWindow(){
        ArrayList<String> handels = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(handels.get(1));
    }

}
