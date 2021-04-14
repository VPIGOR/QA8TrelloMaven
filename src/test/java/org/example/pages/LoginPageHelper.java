package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        this.enterLoginNotAttl(log).
                enterPasswordNoAtl(pas)
                .clicToLoginField();

        return this;
    }

    public LoginPageHelper loginExistEmailAnyPass(String mail, String pas) {
        this.enterLoginNotAttl(mail)
                .clicToLoginField()
                .waitUntilPassFildPresent()
                .enterPasswordNoAtl(pas)
                .clickToPasswoIn();
        return this;

    }

    public LoginPageHelper waitUntilPageIsLoaded() {
        waitUntilElementIsClickabl(emailField, 10);
        return this;
    }

    public LoginPageHelper openLoginPage() {
        enterTologinPageButton.click();
        return this;
    }

    public LoginPageHelper enterLoginNotAttl(String mail) {
        fillField(emailField, mail);
        return this;
    }

    public LoginPageHelper enterPasswordNoAtl(String pas) {
        fillField(passwordField, pas);
        waiterWithThread(1000);
        return this;
    }

    public LoginPageHelper clicToLoginField() {
        loginButton.click();
        return this;
    }

    public LoginPageHelper waitUntilPassFildPresent() {
        waitUntilElementIsClickabl(passField, 5);
        return this;
    }

    public LoginPageHelper clickToPasswoIn() {
        loginSubmitButton.click();
        return this;
    }

    public boolean isDisplayErrorMessage() {
        return errorMassage.isDisplayed();
    }


    public LoginPageHelper waitUntilLoginErrorMessageIsPresent() {
        waitUntilElementIsVisable(loginErrorMasage, 10);
        return this;
    }

    public boolean isDisplayLoginErrorMessage() {
        return loginErrorMasage.isDisplayed();
    }


    public String getErrorMessage() {
        return errorMassage.getText();
    }

    public LoginPageHelper waitErrorMassage() {
        waitUntilElementIsVisable(errorMassage, 3);
        return this;
    }
}
