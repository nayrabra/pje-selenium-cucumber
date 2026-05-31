package com.meuteste.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverFactory {

    public static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();

        String headless = System.getProperty("headless");

        if ("true".equals(headless)) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        options.addArguments("--disable-blink-features=AutomationControlled");

        return new ChromeDriver(options);
    }

    public static WebDriverWait createWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(30));
    }
}