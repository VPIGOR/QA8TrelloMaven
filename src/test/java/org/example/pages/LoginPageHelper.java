package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class LoginPageHelper extends PageBase {
    @FindBy(css = ".field-group")
    WebElement passField;
    @FindBy(id = "user")
    WebElement emailField;
    @FindBy(xpath = "//*[@class='btn btn-sm btn-link text-primary']")
    WebElement enterTologinPageButton;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(id = "login")
    WebElement loginButton;
    @FindBy(id = "login-submit")
    WebElement loginSubmitButton;
    @FindBy(id = "error")
    WebElement errorMassage;
    @FindBy(id = "login-error")
    WebElement loginErrorMasage;

    public LoginPageHelper(WebDriver driver) {
        super(driver);
    }


    public LoginPageHelper enterLoginAndPassNoAtl(String log, String pas) {
        log4j.startMethod("enterLoginAndPassNoAtl");
        this
                .enterLoginNotAttl(log)
                .enterPasswordNoAtl(pas)
                .clicToLoginField();
        log4j.endMethod("enterLoginAndPassNoAtl");
        return this;
    }

    public LoginPageHelper loginExistEmailAnyPass(String mail, String pas) {
        log4j.startMethod("loginExistEmailAnyPass");
        this.enterLoginNotAttl(mail)
                .clicToLoginField()
                .waitUntilPassFildPresent()
                .enterPasswordNoAtl(pas)
                .clickToPasswoIn();
        log4j.endMethod("loginExistEmailAnyPass");
        return this;

    }

    public LoginPageHelper waitUntilPageIsLoaded() {
        log4j.info("wait Until Page Is Loaded");
        waitUntilElementIsVisable(emailField,5);
        waitUntilElementIsClickabl(emailField, 3);
        return this;
    }

    public LoginPageHelper openLoginPage() {
        log4j.info("enter to login button");
        enterTologinPageButton.click();
        return this;
    }

    public LoginPageHelper enterLoginNotAttl(String mail) {
        log4j.parametricII("Enter login", mail);
        fillField(emailField, mail);
        return this;
    }

    public LoginPageHelper enterPasswordNoAtl(String pas) {
        log4j.parametricII("Enter password", pas);
        fillField(passwordField, pas);
        waiterWithThread(1000);
        return this;
    }

    public LoginPageHelper clicToLoginField() {
        log4j.info("Clic To Login Field Button");
        loginButton.click();
        return this;
    }

    public LoginPageHelper waitUntilPassFildPresent() {
        log4j.info("wait Until Password Fild is Present");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        waitUntilElementIsClickabl(passField, 3);
        return this;
    }

    public LoginPageHelper clickToPasswoIn() {
        log4j.info("click to login submit button");
        loginSubmitButton.click();
        return this;
    }

    public boolean isDisplayErrorMessage() {
        log4j.info("wait is Display Error Message");
        return errorMassage.isDisplayed();
    }


    public LoginPageHelper waitUntilLoginErrorMessageIsPresent() {
        log4j.info("wait Until Login Error Message Is Present");
        waitUntilElementIsVisable(loginErrorMasage, 10);
        return this;
    }

    public boolean isDisplayLoginErrorMessage() {
        log4j.info("wait Is Display Login Error Message");
        return loginErrorMasage.isDisplayed();
    }


    public String getErrorMessage() {
        log4j.info("receiving error message");
        return errorMassage.getText();
    }

    public LoginPageHelper waitErrorMassage() {
        log4j.info(" waiting Error Massage ");
        waitUntilElementIsVisable(errorMassage, 3);
        return this;
    }
}
