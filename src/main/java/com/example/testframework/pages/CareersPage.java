package com.example.testframework.pages;

import com.example.testframework.utils.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.*;

public class CareersPage extends PageBase {
    private String clickedPositionName;
    @FindBy(css = ".clearfix .h2-mktg")
    private WebElement heading;
    @FindBy(css = ".clearfix .f3")
    private WebElement paragraph;
    @FindBy(linkText = "Open positions")
    private WebElement openPositionsLnk;
    @FindBy(id = "location-filter")
    private WebElement locationsFilter;
    @FindBy(id = "category-filter")
    private WebElement categoriesFilter;
    @FindBy(xpath = "//mat-label[text()='Type']/ancestor::*[7]")
    private WebElement typeFilter;
    @FindBy(xpath = "//mat-label[text()='Management Level']/ancestor::*[7]")
    private WebElement managementLevelFilter;
    @FindBy(xpath = "//mat-label[text()='Remote']/ancestor::*[7]")
    private WebElement remoteFilter;
    @FindBy(className = "mat-option-text")
    private List<WebElement> filterValuesList;
    @FindBy(id = "search-results-indicator")
    private WebElement searchResultsIndicator;

    //Use 'By' locators for dynamically loaded elements
    private final By openPositions = By.cssSelector(".job-results-container .search-result-item");

    public CareersPage(WebDriver driver) {
        super(driver);
    }

    public String getHeadingText() {
        return waitUntilElementIsDisplayed(heading).getText();
    }

    public String getParagraphText() {
        return paragraph.getText();
    }

    public void click0penPositionsButton() {
        waitUntilElementIsDisplayed(openPositionsLnk).click();
    }

    public void clickLocationsFilter() {
        locationsFilter.click();
    }

    public void clickCategoriesFilter() {
        categoriesFilter.click();
    }

    public void clickTypeFilter() {
        typeFilter.click();
    }

    public void clickManagementLevelFilter() {
        managementLevelFilter.click();
    }

    public void clickRemoteFilter() {
        remoteFilter.click();
    }

    public void filterOpenPositionsBy(String value) {
        Optional<WebElement> filterValueElement = filterValuesList.stream()
                .filter(webElement -> webElement.getText().substring(0, webElement.getText().indexOf("(") - 1)
                        .equalsIgnoreCase(value))
                .findFirst();

        filterValueElement.ifPresentOrElse(WebElement::click,
                () -> {
                    throw new NoSuchElementException("Filter value with name '"
                            + value + "' not found");
                });

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ESCAPE).perform();

        waitUntilFilteringIsCompleted();
    }

    public List<Map<String, String>> getVisiblePositionsDetails() {
        List<Map<String, String>> visiblePositionsDetailsList = new ArrayList<>();
        List<WebElement> openPositionsList = waitUntilElementsAreLocatedAndDisplayed(openPositions);
        openPositionsList.forEach(webElement -> {
            Map<String, String> positionDetailsMap = new HashMap<>();
            positionDetailsMap.put(Constants.POSITION_NAME, webElement.findElement(By.className("job-title")).getText());
            positionDetailsMap.put(Constants.REQ_ID, webElement.findElement(By.className("req-id")).getText().replaceAll("[^0-9]", ""));
            positionDetailsMap.put(Constants.LOCATION, webElement.findElement(By.className("location")).getText().replace("\n", ""));
            positionDetailsMap.put(Constants.CATEGORIES, webElement.findElement(By.className("categories")).getText());
            visiblePositionsDetailsList.add(positionDetailsMap);
        });

        return visiblePositionsDetailsList;
    }

    public void expandPositionDescription(String position) {
        scrollToThePosition(position);
        WebElement element = getPosition(position).findElement(By.className("mat-expansion-indicator"));
        element.click();
        clickedPositionName = position;
    }

    public String getJobSummaryText() {
        checkClickedPositionNameNotNull();
        String jobDescription = waitUntilChildElementIsDisplayed(getPosition(clickedPositionName),
                By.cssSelector("div.inner-html-description")).getText();
        return jobDescription.substring(0, jobDescription.indexOf("Overview")).trim();
    }

    public void clickReadMoreButton() {
        checkClickedPositionNameNotNull();
        waitUntilChildElementIsDisplayed(getPosition(clickedPositionName), By.linkText("Read More")).click();
    }

    public void clickApplyNowButton() {
        checkClickedPositionNameNotNull();
        waitUntilChildElementIsDisplayed(getPosition(clickedPositionName), By.linkText("Apply Now")).click();
    }

    public void scrollToThePosition(String position){
        WebElement element = getPosition(position);
        executeJavaScript("arguments[0].scrollIntoView(true);", element);

        //Custom wait is used to wait until scrolling is complete.
        waitUntilForTheCustomCondition(driver -> {
            int elementYCoordinate = element.getLocation().getY();
            long windowYCoordinate = (long) executeJavaScript("return window.scrollY;");
            if (elementYCoordinate == windowYCoordinate) {
                return element;
            }
            return null;
        });
    }

    private void checkClickedPositionNameNotNull() {
        if (clickedPositionName == null) {
            throw new IllegalStateException("clickedPositionName is null. Click on the position before you perform this action");
        }
    }

    private WebElement getPosition(String positionName) {
        List<WebElement> openPositionsList = waitUntilElementsAreLocatedAndDisplayed(openPositions);

        Optional<WebElement> actualPosition = openPositionsList.stream().filter(webElement ->
                        webElement.findElement(By.className("job-title")).getText().equalsIgnoreCase(positionName))
                .findFirst();

        if (actualPosition.isPresent()) {
            return actualPosition.get();
        } else {
            throw new NoSuchElementException("Position with name '"
                    + positionName + "' not found");
        }
    }

    private void waitUntilFilteringIsCompleted() {
        waitUntilElementIsDisplayed(searchResultsIndicator);
    }
}
