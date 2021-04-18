package org.example.pages;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;


public class SupportPageHelper extends PageBase {


    public SupportPageHelper(WebDriver driver) {
        super(driver);
    }

    public SupportPageHelper waitHelpPageLoaded(){
        waitUntilWindowIsOpened(2,3);
        return this;
    }

    public SupportPageHelper goToHelpMenuWindow(){
        goToCurrentTab(handlesList().get(1));
        return this;
    }
    public String titleHelpWindow(){
        return getPagTitle();
    }

    public SupportPageHelper closeHelpWindow(){
       closeCurrentWindow();
        return this;
    }
    public SupportPageHelper goToMainWindow(){
        goToCurrentTab(handlesList().get(0));
        return this;
    }



}
