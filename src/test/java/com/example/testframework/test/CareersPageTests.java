package com.example.testframework.test;

import com.example.testframework.steps.CommonSteps;
import com.example.testframework.utils.Constants;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.example.testframework.steps.CareersPageSteps;

import java.util.List;
import java.util.Map;

public class CareersPageTests extends TestBase {
    private CommonSteps commonSteps;
    private CareersPageSteps careersPageSteps;

    @BeforeMethod(dependsOnMethods = "testBaseBeforeMethod")
    public void beforeMethod() {
        commonSteps = new CommonSteps();
        careersPageSteps = new CareersPageSteps();
    }

    @Test
    public void testValidateHeadingAndParagraph() {
        commonSteps.navigateToPage("home");
        commonSteps.navigateToPage("careers");
        careersPageSteps.validateHeadingText("Come build the home for all developers");
        careersPageSteps.validateParagraphText("Do the best work of your career and " +
                "join in our mission to accelerate human progress by connecting communities all over " +
                "the world through software collaboration.");
        commonSteps.validateNavigationMenuIsVisible();
        commonSteps.scrollPage(1500);
        commonSteps.validateNavigationMenuIsNotVisible();
        assertAll();
    }

    /* As tc1ValidateHeaderSection, tests should be added to validate features section, utility section
    and above section. But it's better to validate static content/text at lower layers of the test pyramid */

    @Test
    public void testValidateEngineeringPositionsFiltering() {
        commonSteps.navigateToPage("home");
        commonSteps.navigateToPage("careers");
        careersPageSteps.click0penPositionsButton();
        commonSteps.acceptCookies();
        careersPageSteps.filterOpenPositionsBy("Categories", "Engineering");
        careersPageSteps.filterOpenPositionsBy("Remote", "Yes");
        careersPageSteps.validateFilteredPositions(List.of(
                Map.of(
                        Constants.POSITION_INDEX, "0",
                        Constants.POSITION_NAME, "Senior Software Engineer, Copilot Platform",
                        Constants.REQ_ID, "2640",
                        Constants.LOCATION, "Remote, Germany",
                        Constants.CATEGORIES, "Engineering"),
                Map.of(
                        Constants.POSITION_INDEX, "1",
                        Constants.POSITION_NAME, "Principal Software Engineer",
                        Constants.REQ_ID, "2526",
                        Constants.LOCATION, "Remote, United States",
                        Constants.CATEGORIES, "Engineering"),
                Map.of(
                        Constants.POSITION_INDEX, "2",
                        Constants.POSITION_NAME, "Software Engineer, Internal Development Experience",
                        Constants.REQ_ID, "2484",
                        Constants.LOCATION, "Remote, United Kingdom",
                        Constants.CATEGORIES, "Engineering"),
                Map.of(
                        Constants.POSITION_INDEX, "5",
                        Constants.POSITION_NAME, "Senior Software Engineer, CodeQL Experiences",
                        Constants.REQ_ID, "2590",
                        Constants.LOCATION, "Remote, United Kingdom",
                        Constants.CATEGORIES, "Engineering")));
        assertAll();
    }

    @Test
    public void testValidatePrincipalSoftwareEngineerPositionAndApply() {
        commonSteps.navigateToPage("home");
        commonSteps.navigateToPage("careers");
        careersPageSteps.click0penPositionsButton();
        commonSteps.acceptCookies();
        careersPageSteps.filterOpenPositionsBy("Categories", "Engineering");
        careersPageSteps.expandPositionDescription("Principal Software Engineer");
        careersPageSteps.validateJobSummary("About GitHub\n" +
                "\n" +
                "As the global home for all developers, GitHub is the complete AI-powered developer platform to build, " +
                "scale, and deliver secure software. Over 100 million people, including developers from 90 of the " +
                "Fortune 100 companies, use GitHub to build amazing things together across 330\\+ million repositories. " +
                "With all the collaborative features of GitHub, it has never been easier for individuals and teams to " +
                "write faster, better code.\n" +
                "\n" +
                "Locations\n" +
                "\n" +
                "In this .*");
        careersPageSteps.clickReadMoreButton();
        commonSteps.verifyThatTheUserIsOnThePage("https://www.github.careers/jobs/2526?");
        commonSteps.navigateBack();
        careersPageSteps.scrollToThePosition("Principal Software Engineer");
        careersPageSteps.clickApplyNowButton();
        commonSteps.verifyThatTheUserIsOnThePage("https://careers-githubinc.icims.com/jobs/2526/login?");
        assertAll();
    }
}
