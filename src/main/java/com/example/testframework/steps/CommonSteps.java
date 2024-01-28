package com.example.testframework.steps;

import com.example.testframework.utils.Constants;
import com.example.testframework.pages.NavigationMenuPage;
import com.example.testframework.pages.HomePage;
import com.example.testframework.utils.LoggerUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;

public class CommonSteps extends StepBase {

    public void navigateToPage(String pageName) {
        if (pageName.equalsIgnoreCase("home")) {
            driver.get(Constants.SITE_URl);
        } else if (pageName.equalsIgnoreCase("sign in")) {
            HomePage homePage = new HomePage(driver);
            homePage.clickSignInButton();
        } else if (pageName.equalsIgnoreCase("careers")) {
            HomePage homePage = new HomePage(driver);
            homePage.clickCareersLink();
        } else {
            throw new IllegalArgumentException("Invalid page name \"" + pageName + "\"");
        }
    }

    public void validateNavigationMenuIsVisible() {
        NavigationMenuPage navigationMenuPage = new NavigationMenuPage(driver);
        softAssert.assertEquals(navigationMenuPage.isNavigationMenuVisibleInViewport(), true,
                "Navigation menu is not visible.");
    }

    public void validateNavigationMenuIsNotVisible() {
        NavigationMenuPage navigationMenuPage = new NavigationMenuPage(driver);
        softAssert.assertEquals(navigationMenuPage.isNavigationMenuVisibleInViewport(), false,
                "Navigation menu is visible.");
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void verifyThatTheUserIsOnThePage(String expectedPageLink) {
        softAssert.assertTrue(driver.getCurrentUrl().contains(expectedPageLink),
                "The user is not on the expected page. " +
                        "\nExpected Page link : " + expectedPageLink +
                        "\nActual Page link : \"" + driver.getCurrentUrl());
    }

    public void acceptCookies() {
        HomePage homePage = new HomePage(driver);
        if (!homePage.isCookieConsentAccepted()) {
            homePage.clickAcceptCookies();
        }
    }

    public void scrollPage(int pixels) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, arguments[0]);", pixels);
    }

    public void validateSuccessfullyLoggedIn() {
        HomePage homePage = new HomePage(driver);
        try {
            homePage.isDashboardVisible();
        } catch (NoSuchElementException e) {
            LoggerUtil.getDebugLogger().error("Test Failed. Failed to log in.");
            throw new AssertionError("Failed to log in.");
        }
    }

    public void signOut() {
        HomePage homePage = new HomePage(driver);
        homePage.clickProfileButton();
        homePage.clickSignOutButton();
        homePage.clickSignOutFromAllAccountsButton();
    }
}
