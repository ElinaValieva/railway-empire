package com.elina.railwayApp.selenium;

import com.elina.railwayApp.configuration.common.URLs;
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
 * - login, show schedule, find ticket, show seats page
 */
public class AutomaticSchedulePageTest {

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
         * Login as admin/manager
         */

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("userNameUser")))
                .sendKeys("login@mail.ru");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("passwordUser")))
                .sendKeys("pass");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//button[@id = 'loginUser']")))
                .click();
        /**
         * show schedule
         */
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("showScheduleID")))
                .click();


        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("stationDepartureSearchingByAllParameters")))
                .sendKeys("Ufa");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("stationArrivalSearchingByAllParameters")))
                .sendKeys("Saint Petersburg");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("dateDepartureSearchingByAllParameters")))
                .sendKeys("23.07.2018");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("dateArrivalSearchingByAllParameters")))
                .sendKeys("25.07.2018");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("trainSearchingByAllParameters")))
                .sendKeys("T120");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//button[@id = 'searchingBtnByAllParameters']")))
                .click();


        Thread.sleep(2000);

        /**
         * find ticket
         */

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.className("btnFindTicket")))
                .click();

        Thread.sleep(5000);
    }

    @After
    public void closeDriver() throws Exception {
        driver.quit();
    }
}
