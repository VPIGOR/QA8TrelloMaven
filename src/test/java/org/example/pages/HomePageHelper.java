package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageHelper extends PageBase {
     @FindBy(xpath = "//*[@class='btn btn-sm btn-link text-primary']")
     WebElement loginIcon;


    public HomePageHelper(WebDriver driver) {
        super(driver);
    }



    public HomePageHelper waitUntilPageIsLoaded() {
        waitUntilElementIsClickabl(loginIcon,40);
        return this;
    }
    public String getPageTitle(){
       return  driver.getTitle();
    }


}
