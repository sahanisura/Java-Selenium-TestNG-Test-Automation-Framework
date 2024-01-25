package com.example.testframework.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.ConcurrentHashMap;

public class TestContext {
    private final SoftAssert softAssert;
    private final WebDriver driver;
    private static final ConcurrentHashMap<Long, TestContext> testContextMap = new ConcurrentHashMap<>();

    private TestContext(SoftAssert softAssert, WebDriver driver) {
        this.softAssert = softAssert;
        this.driver = driver;
    }

    public static void initTestContext(String browserType, boolean initDriverForEachTest) {
        long threadId = Thread.currentThread().threadId();
        SoftAssert softAssert;
        WebDriver driver;

        if (testContextMap.get(threadId) == null) {
            softAssert = new SoftAssert();
            driver = createDriver(browserType.toLowerCase());
            testContextMap.put(threadId, new TestContext(softAssert, driver));
            LoggerUtil.getDebugLogger().info("New SoftAssert and Driver instances created. Thread ID: " + threadId);
        } else {
            softAssert = new SoftAssert();
            if (initDriverForEachTest) {
                driver = createDriver(browserType.toLowerCase());
                testContextMap.put(threadId, new TestContext(softAssert, driver));
                LoggerUtil.getDebugLogger().info("New SoftAssert and Driver instances created. Thread ID: " + threadId);
            } else {
                testContextMap.put(threadId, new TestContext(softAssert, testContextMap.get(threadId).driver));
                LoggerUtil.getDebugLogger().info("New SoftAssert instance created. Thread ID: " + threadId);
            }
        }
    }

    private static WebDriver createDriver(String browser) {
        WebDriver driver;
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver",
                        Constants.CHROME_DRIVER_LOCATION);
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver",
                        Constants.GECKO_DRIVER_LOCATION);
                driver = new FirefoxDriver();
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver",
                        Constants.EDGE_DRIVER_LOCATION);
                driver = new EdgeDriver();
                break;
            // Add cases for other browsers if needed
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browser);
        }
        return driver;
    }

    public static SoftAssert getSoftAssert() {
        return testContextMap.get(Thread.currentThread().threadId()).softAssert;
    }

    public static WebDriver getDriver() {
        return testContextMap.get(Thread.currentThread().threadId()).driver;
    }

    public static void cleanTestContext() {
        testContextMap.remove(Thread.currentThread().threadId());
    }
}
