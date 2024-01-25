package com.example.testframework.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import static com.example.testframework.utils.Constants.POLLING_INTERVAL_IN_MILLISECONDS;
import static com.example.testframework.utils.Constants.WAIT_TIMEOUT_IN_SECONDS;

public class PageBase {
    private final WebDriverWait wait;
    protected final WebDriver driver;

    public PageBase(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_IN_SECONDS));
        this.driver = driver;
    }

    protected WebElement waitUntilElementIsLocatedAndDisplayed(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected List<WebElement> waitUntilElementsAreLocatedAndDisplayed(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected WebElement waitUntilElementIsDisplayed(WebElement webElement) {
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected WebElement waitUntilChildElementIsDisplayed(WebElement webElement, By locator) {
        return wait.until(ExpectedConditions.visibilityOf(webElement.findElement(locator)));
    }

    protected void waitUntilForTheCustomCondition(Function<WebDriver, WebElement> customFunction) {
        /*Use this if you are dealing with dynamic content that might take unpredictable
        amounts of time to load and if you need to create a custom condition for waiting */

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(WAIT_TIMEOUT_IN_SECONDS))
                .pollingEvery(Duration.ofMillis(POLLING_INTERVAL_IN_MILLISECONDS))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        wait.until(customFunction);
    }

    protected Object executeJavaScript(String script, WebElement webElement) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(script, webElement);
    }

    protected Object executeJavaScript(String script) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(script);
    }
}
