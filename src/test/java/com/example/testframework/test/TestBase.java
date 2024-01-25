package com.example.testframework.test;

import com.example.testframework.utils.LoggerUtil;
import org.testng.ITestResult;
import org.testng.annotations.*;

import static com.example.testframework.utils.TestContext.*;

public class TestBase {

    @BeforeTest
    public void beforeTest() {
        LoggerUtil.getInfoLogger().info("______________beforeTest______________");
    }

    @Parameters("BrowserType")
    @BeforeMethod
    public void testBaseBeforeMethod(ITestResult result, @Optional("chrome") String browserType) {
        LoggerUtil.getInfoLogger().info("______________testBaseBeforeMethod______________ " +
                result.getMethod().getMethodName());

        initTestContext(browserType, false);
        getDriver().manage().window().maximize();
    }

    @AfterMethod
    public void testBaseAfterMethod(ITestResult result) {
        LoggerUtil.getInfoLogger().info("______________testBaseAfterMethod______________ " +
                result.getMethod().getMethodName());

        //invoke closeMultipleTabs() method if drive is not closed in this method
        closeMultipleTabs();

//        getDriver().quit();
    }

    @AfterTest
    public void afterTest() {
        LoggerUtil.getInfoLogger().info("______________afterTest______________");
        getDriver().quit();
        cleanTestContext();
    }

    private void closeMultipleTabs() {
        Object[] openedTabs = getDriver().getWindowHandles().toArray();
        try {
            if (openedTabs.length > 1) {
                for (int i = openedTabs.length; i > 1; i--) {
                    getDriver().switchTo().window(openedTabs[i - 1].toString()).close();
                }
            }
        } finally {
            getDriver().switchTo().window(openedTabs[0].toString());
        }
    }

    protected void assertAll() {
        try {
            getSoftAssert().assertAll();
            LoggerUtil.getDebugLogger().info("Test Passed.");
        } catch (AssertionError error) {
            LoggerUtil.getDebugLogger().error("Test Failed. ", error);
            throw new AssertionError(error.toString());
        }
    }
}
