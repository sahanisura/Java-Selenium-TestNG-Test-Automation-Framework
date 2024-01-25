package com.example.testframework.utils;

import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class Constants {
    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE = "config.properties";

    static {
        try (InputStream input = Constants.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            properties.load(input);
        } catch (Exception e) {
            LoggerUtil.getInfoLogger().error("An exception occurred while loading the config.properties file", e);
        }
    }

    //config properties constants
    public static final String SITE_URl = Optional.ofNullable(
            properties.getProperty("SITE_URL")).orElse("");
    public static final String CHROME_DRIVER_LOCATION = Optional.ofNullable(
            properties.getProperty("CHROME_DRIVER_LOCATION")).orElse("");
    public static final String EDGE_DRIVER_LOCATION = Optional.ofNullable(
            properties.getProperty("EDGE_DRIVER_LOCATION")).orElse("");
    public static final String GECKO_DRIVER_LOCATION = Optional.ofNullable(
            properties.getProperty("GECKO_DRIVER_LOCATION")).orElse("");
    public static final int WAIT_TIMEOUT_IN_SECONDS = Integer.parseInt(Optional.ofNullable(
            properties.getProperty("WAIT_TIMEOUT_IN_SECONDS")).orElse("20"));
    public static final int POLLING_INTERVAL_IN_MILLISECONDS = Integer.parseInt(Optional.ofNullable(
            properties.getProperty("POLLING_INTERVAL_IN_MILLISECONDS")).orElse("100"));


    //other constants
    public static final String POSITION_INDEX = "Position Index";
    public static final String POSITION_NAME = "Position Name";
    public static final String REQ_ID = "Req ID";
    public static final String LOCATION = "Location";
    public static final String CATEGORIES = "Categories";
}
