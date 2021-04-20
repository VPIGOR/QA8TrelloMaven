package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ActivityPageHelper extends PageBase {

    @FindBy(css = "[class='phenom mod-attachment-type']")
    List<WebElement> listActions;


    public ActivityPageHelper(WebDriver driver) {
        super(driver);
    }



    public String getLasttActivity() {
        return listActions.get(0).getText();
    }
}
