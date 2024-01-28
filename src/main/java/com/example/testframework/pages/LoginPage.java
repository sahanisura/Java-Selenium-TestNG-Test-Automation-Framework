package com.example.testframework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase {
    @FindBy(css = "[for=\"login_field\"]")
    private WebElement usernameOrEmailAddressLbl;
    @FindBy(css = "[for=\"password\"]")
    private WebElement passwordLbl;

    //Use 'By' locators for dynamically loaded elements
    private final By logo = By.className("header-logo");
    private final By signInWithAPasskeyLbl = By.cssSelector(".login-callout .Button-label");
    private final By createAnAccountLbl = By.className("mt-1");
    private final By signInBtn = By.className("js-sign-in-button");
    private final By forgotPasswordLnk = By.id("forgot-password");
    private final By usernameOrEmailAddressTxt = By.id("login_field");
    private final By passwordTxt = By.id("password");
    private final By errorLbl = By.cssSelector("#js-flash-container [role=\"alert\"]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLogoVisible() {
        return waitUntilElementIsLocated(logo).isDisplayed();
    }

    public boolean isUsernameOrEmailAddressLabelVisible() {
        return usernameOrEmailAddressLbl.isDisplayed();
    }

    public String getUsernameOrEmailAddressLabelText() {
        return usernameOrEmailAddressLbl.getText();
    }

    public boolean isPasswordLabelVisible() {
        return passwordLbl.isDisplayed();
    }

    public String getPasswordLabelText() {
        return passwordLbl.getText();
    }

    public String getSignInButtonText() {
        return waitUntilElementIsLocatedAndDisplayed(signInBtn).getAttribute("value");
    }

    public String getForgetPasswordText() {
        return waitUntilElementIsLocatedAndDisplayed(forgotPasswordLnk).getText();
    }

    public String getSignInWithAPasskeyText() {
        return waitUntilElementIsLocatedAndDisplayed(signInWithAPasskeyLbl).getText();
    }

    public String getCreateAnAccountLblText() {
        return waitUntilElementIsLocatedAndDisplayed(createAnAccountLbl).getText();
    }

    public void enterEmail(String email) {
        waitUntilElementIsLocatedAndDisplayed(usernameOrEmailAddressTxt).sendKeys(email);
    }

    public void enterPassword(String password) {
        waitUntilElementIsLocatedAndDisplayed(passwordTxt).sendKeys(password);
    }

    public void clickLoginButton() {
        waitUntilElementIsLocatedAndDisplayed(signInBtn).click();
    }

    public String getErrorMessage() {
        return waitUntilElementIsLocatedAndDisplayed(errorLbl).getText();
    }
}
