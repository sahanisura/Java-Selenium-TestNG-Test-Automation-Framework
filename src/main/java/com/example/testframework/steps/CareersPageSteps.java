package com.example.testframework.steps;

import com.example.testframework.pages.CareersPage;
import com.example.testframework.utils.Constants;

import java.util.List;
import java.util.Map;

public class CareersPageSteps extends StepBase {
    protected CareersPage careersPage;

    @Override
    protected void initPages() {
        careersPage = new CareersPage(driver);
    }

    public void validateHeadingText(String expectedHeadingText) {
        String actualHeadingText = careersPage.getHeadingText();
        softAssert.assertEquals(actualHeadingText, expectedHeadingText);
    }

    public void validateParagraphText(String expectedParagraphText) {
        String actualParagraphText = careersPage.getParagraphText();
        softAssert.assertTrue(actualParagraphText.matches(expectedParagraphText),
                "Actual and expected values do not match. " +
                        "\nExpected : " + expectedParagraphText +
                        "\nActual : " + actualParagraphText);
    }

    public void click0penPositionsButton() {
        careersPage.click0penPositionsButton();
    }

    public void filterOpenPositionsBy(String filterCriterion, String value) {
        if (filterCriterion.equalsIgnoreCase("Locations")) {
            careersPage.clickLocationsFilter();
        } else if (filterCriterion.equalsIgnoreCase("Categories")) {
            careersPage.clickCategoriesFilter();
        } else if (filterCriterion.equalsIgnoreCase("Type")) {
            careersPage.clickTypeFilter();
        } else if (filterCriterion.equalsIgnoreCase("Management Level")) {
            careersPage.clickManagementLevelFilter();
        } else if (filterCriterion.equalsIgnoreCase("Remote")) {
            careersPage.clickRemoteFilter();
        } else {
            throw new IllegalArgumentException("Invalid filter criterion: " + filterCriterion);
        }

        careersPage.filterOpenPositionsBy(value);
    }

    public void validateFilteredPositions(List<Map<String, String>> expectedPositionsDetailList) {
        List<Map<String, String>> visiblePositionsDetails = careersPage.getVisiblePositionsDetails();

        expectedPositionsDetailList.forEach(expectedPositionDetailsMap -> {
            int index = Integer.parseInt(expectedPositionDetailsMap.get(Constants.POSITION_INDEX));
            Map<String, String> actualPositionDetailsMap = visiblePositionsDetails.get(index);

            softAssert.assertEquals(actualPositionDetailsMap.get(Constants.POSITION_NAME),
                    expectedPositionDetailsMap.get(Constants.POSITION_NAME),
                    "The expected Position Name of Position Index "
                            + expectedPositionDetailsMap.get(Constants.POSITION_INDEX) +
                            " does not match the actual Position Name.");

            softAssert.assertEquals(actualPositionDetailsMap.get(Constants.REQ_ID),
                    expectedPositionDetailsMap.get(Constants.REQ_ID),
                    "The expected Req ID of Position Index "
                            + expectedPositionDetailsMap.get(Constants.POSITION_INDEX) +
                            " does not match the actual Req ID.");

            softAssert.assertEquals(actualPositionDetailsMap.get(Constants.LOCATION),
                    expectedPositionDetailsMap.get(Constants.LOCATION),
                    "The expected Location of Position Index "
                            + expectedPositionDetailsMap.get(Constants.POSITION_INDEX) +
                            " does not match the actual Location.");

            softAssert.assertEquals(actualPositionDetailsMap.get(Constants.CATEGORIES),
                    expectedPositionDetailsMap.get(Constants.CATEGORIES),
                    "The expected Category of Position Index "
                            + expectedPositionDetailsMap.get(Constants.POSITION_INDEX) +
                            " does not match the actual Category.");
        });
    }

    public void expandPositionDescription(String position) {
        careersPage.expandPositionDescription(position);
    }

    public void validateJobSummary(String expectedJobSummaryText) {
        String actualJobSummaryText = careersPage.getJobSummaryText();
        softAssert.assertTrue(actualJobSummaryText.matches(expectedJobSummaryText),
                "Actual and expected values do not match. " +
                        "\nExpected : " + expectedJobSummaryText +
                        "\nActual : " + actualJobSummaryText);
    }

    public void clickReadMoreButton() {
        careersPage.clickReadMoreButton();
    }

    public void clickApplyNowButton() {
        careersPage.clickApplyNowButton();
    }

    public void scrollToThePosition(String position) {
        careersPage.scrollToThePosition(position);
    }
}
