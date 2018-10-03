package com.elina.railwayApp.selenium;

import com.elina.railwayApp.selenium.config.SeleniumConfig;
import com.elina.railwayApp.selenium.config.WebDriverRailway;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutoTestForMaps {

    private WebDriver driver = null;

    @Before
    public void createDriver() {
        System.setProperty(SeleniumConfig.PROPERTY_DRIVER, SeleniumConfig.PROPERTY_DRIVER_PATH);
        driver = new WebDriverRailway();
        driver.manage().window().fullscreen();
        driver.get(SeleniumConfig.LOGIN_URL);
    }

    /**
     * 1. Log as MANAGER/ADMIN
     * 2. Look at all stations on map
     * 3. Look at all real time manipulation on map
     * 4. Logout
     *
     * @throws InterruptedException
     */
    @Test
    public void automaticTest() throws InterruptedException {
        WebDriverWait webDriverWait = new WebDriverWait(driver, SeleniumConfig.TIME_OUT);

        // ---- Log as MANAGER

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("userNameUser")))
                .sendKeys("login@mail.ru");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("passwordUser")))
                .sendKeys("pass");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//button[@id = 'loginUser']")))
                .click();

        // ---- Look at all stations on map

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//a[contains(text(),'Map')]")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//button[@id='addStationBtn']")))
                .click();

        Thread.sleep(2000);

        // ---- Look at all real time manipulation on map

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//button[@id='addAllBtn']")))
                .click();

        Thread.sleep(10000);

        // ---- Logout

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//a[contains(text(),'Sign out')]")))
                .click();

        // ---- Log as USER
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("userNameUser")))
                .sendKeys("bob@mail.ru");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("passwordUser")))
                .sendKeys("pass");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//button[@id = 'loginUser']")))
                .click();

        // ---- Look at all trips on map
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//a[contains(text(),'Map')]")))
                .click();

        Thread.sleep(6000);

        // ---- Look at all trips
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//a[contains(text(),'Trips')]")))
                .click();

        Thread.sleep(2000);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("65")))
                .click();

        Thread.sleep(3000);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//a[contains(text(),'Sign out')]")))
                .click();

    }

    @After
    public void closeDriver() {
        driver.quit();
    }
}
