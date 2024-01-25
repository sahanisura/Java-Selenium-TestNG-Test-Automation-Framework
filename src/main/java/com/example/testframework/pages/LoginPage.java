package com.example.testframework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase {
    @FindBy(css = "[for=\"login_field\"]")
    private WebElement usernameOrEmailAddressLbl;
    @FindBy(id = "login_field")
    private WebElement usernameOrEmailAddressTxt;
    @FindBy(css = "[for=\"password\"]")
    private WebElement passwordLbl;
    @FindBy(id = "password")
    private WebElement passwordTxt;
    @FindBy(className = "js-sign-in-button")
    private WebElement signInBtn;
    @FindBy(id = "forgot-password")
    private WebElement forgotPasswordLnk;
    @FindBy(css = ".login-callout .Button-label")
    private WebElement signInWithAPasskeyLbl;
    @FindBy(className = "mt-1")
    private WebElement createAnAccountLbl;

    //Use 'By' locators for dynamically loaded elements
    private final By logo = By.className("header-logo");
    private final By errorLbl = By.cssSelector("#js-flash-container [role=\"alert\"]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLogoVisible() {
        return waitUntilElementIsLocatedAndDisplayed(logo).isDisplayed();
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
        return signInBtn.getAttribute("value");
    }

    public String getForgetPasswordText() {
        return forgotPasswordLnk.getText();
    }

    public String getSignInWithAPasskeyText() {
        return waitUntilElementIsDisplayed(signInWithAPasskeyLbl).getText();
    }

    public String getCreateAnAccountLblText() {
        return createAnAccountLbl.getText();
    }

    public void enterEmail(String email) {
        waitUntilElementIsDisplayed(usernameOrEmailAddressTxt).sendKeys(email);
    }

    public void enterPassword(String password) {
        waitUntilElementIsDisplayed(passwordTxt).sendKeys(password);
    }

    public void clickLoginButton() {
        waitUntilElementIsDisplayed(signInBtn).click();
    }

    public String getErrorMessage() {
        return waitUntilElementIsLocatedAndDisplayed(errorLbl).getText();
    }
}
