package org.example.pages;

import org.example.util.LogLog4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public abstract class PageBase {
    WebDriver driver;

    protected static LogLog4j log4j = new LogLog4j();


    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    public PageBase waitUntilWindowIsOpened(int numOfWindows, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.numberOfWindowsToBe(numOfWindows));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }


    public PageBase waitUntilElementIsClickabl(WebElement element, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public PageBase waitUntilElementIsVisable(WebElement element, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public String getPagTitle() {
        return driver.getTitle();
    }

    public PageBase waitUntilTitleIs(String str, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.titleIs(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public PageBase fillField(WebElement element, String value) {
        element.clear();
        element.click();
        element.sendKeys(value);
        return this;
    }

    public PageBase waiterWithThread(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    //--------------get name all of tabs-------------------
    public List<String> handlesList() {
        return new ArrayList<>(driver.getWindowHandles());

    }

    //---------------go to tab---------------------------------
    public PageBase goToCurrentTab(String currentTabName) {
        driver.switchTo().window(currentTabName);
        return this;
    }

    public PageBase closeCurrentWindow() {
        driver.close();
        return this;
    }

    public String generateRandomMail() {
        return "name" + (new Random()).nextInt() + "@mail.com";
    }

}