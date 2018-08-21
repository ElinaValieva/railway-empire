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

public class AutoTestForBookingTicket {

    private WebDriver driver = null;

    @Before
    public void createDriver() {
        System.setProperty(SeleniumConfig.PROPERTY_DRIVER, SeleniumConfig.PROPERTY_DRIVER_PATH);
        driver = new WebDriverRailway();
        driver.manage().window().fullscreen();
        driver.get(SeleniumConfig.LOGIN_URL);
    }

    /**
     * 1. Log as user
     * 2. Find schedule Minsk - Voronezh 22 Aug
     * 3. Book ticket
     * 4. Look all trips on map
     * 5. Look all tickets
     * 6. LogOut
     *
     * @throws InterruptedException
     */
    @Test
    public void automaticTest() throws InterruptedException {
        WebDriverWait webDriverWait = new WebDriverWait(driver, SeleniumConfig.TIME_OUT);
        // ---- Log as USER
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("userNameUser")))
                .sendKeys("patric@mail.ru");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("passwordUser")))
                .sendKeys("pass");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//button[@id = 'loginUser']")))
                .click();

        // ---- Search schedule
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("showScheduleID")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("stationDepartureSearchingByStationsAndDates")))
                .sendKeys("Ufa");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("stationArrivalSearchingByStationsAndDates")))
                .sendKeys("Kazan");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("dateDepartureSearchingByStationsAndDates")))
                .sendKeys("21.08.2018");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//button[@id='searchingBtnByStationsAndDates']")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//button[@id='169']")))
                .click();

        // ---- Book ticket
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//ul[@id='place']/li[30]")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("btnBookTicket")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector("button.swal2-confirm.swal2-styled")))
                .click();

        // ---- Look at all trips
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//a[contains(text(),'Trips')]")))
                .click();

        Thread.sleep(3000);

        // ---- Look at all trips on map
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("showTripsOnMapID")))
                .click();

        Thread.sleep(3000);

        // ---- Log out
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//a[contains(text(),'Sign out')]")))
                .click();
    }

    @After
    public void closeDriver() {
        driver.quit();
    }
}
