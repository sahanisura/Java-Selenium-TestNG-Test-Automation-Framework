package com.example.testframework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase {
    @FindBy(className = "HeaderMenu-link--sign-in")
    private WebElement signInBtn;
    @FindBy(linkText = "Careers")
    private WebElement careersLnk;
    @FindBy(id = "cookie-consent-accept-button")
    private WebElement acceptCookiesBtn;
    @FindBy(id = "dashboard")
    private WebElement dashboard;

    //Use 'By' locators for dynamically loaded elements
    private final By profileBtn = By.className("AppHeader-user");
    private final By signOutBtn = By.cssSelector("[href=\"/logout\"]");
    private final By signOutFromAllAccountsBtn = By.cssSelector("[value=\"Sign out from all accounts\"]");
    private final By cookiesConsentLbl = By.id("pixel-consent-container");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickSignInButton() {
        signInBtn.click();
    }

    public void clickCareersLink() {
        careersLnk.click();
    }

    public void clickAcceptCookies() {
        waitUntilElementIsDisplayed(acceptCookiesBtn).click();
    }

    public boolean isCookieConsentAccepted() {
        try {
            return driver.findElement(cookiesConsentLbl).getAttribute("style").equals("display: none;");
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public boolean isDashboardVisible() {
        return dashboard.isDisplayed();
    }

    public void clickProfileButton() {
        waitUntilElementIsLocatedAndDisplayed(profileBtn).click();
    }

    public void clickSignOutButton() {
        waitUntilElementIsLocatedAndDisplayed(signOutBtn).click();
    }

    public void clickSignOutFromAllAccountsButton() {
        waitUntilElementIsLocatedAndDisplayed(signOutFromAllAccountsBtn).click();
    }

    //Other home page actions should be added here
}
