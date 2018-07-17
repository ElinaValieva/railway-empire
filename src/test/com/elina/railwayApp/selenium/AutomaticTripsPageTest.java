package com.elina.railwayApp.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Business case:
 * - login, show users trips as tickets, show trips on map
 */
public class AutomaticTripsPageTest {
    private WebDriver driver = null;

    @Before
    public void createDriver() {
        System.setProperty(SeleniumConfig.PROPERTY_DRIVER, SeleniumConfig.PROPERTY_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.get(SeleniumConfig.LOGIN_URL);
    }

    @Test
    public void automaticSchedulePage() throws InterruptedException {
        WebDriverWait webDriverWait = new WebDriverWait(driver, SeleniumConfig.TIME_OUT);
        /**
         * Login as user
         */
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("userNameUser")))
                .sendKeys("bob@mail.ru");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("passwordUser")))
                .sendKeys("pass");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//button[@id = 'loginUser']")))
                .click();
        /**
         * show trips
         */
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("showTripsID")))
                .click();

        Thread.sleep(3000);

        /**
         * show trips on map
         */
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("showTripsOnMapID")))
                .click();

        Thread.sleep(3000);
    }

    @After
    public void closeDriver() throws Exception {
        driver.quit();
    }
}
