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
 * - login, show all charts and logout
 */
public class AutomaticChartPageTest {

    private WebDriver driver = null;

    @Before
    public void createDriver() {
        System.setProperty(SeleniumConfig.PROPERTY_DRIVER, SeleniumConfig.PROPERTY_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.get(SeleniumConfig.LOGIN_URL);
    }

    @Test
    public void automaticChartPage() throws InterruptedException {
        WebDriverWait webDriverWait = new WebDriverWait(driver, SeleniumConfig.TIME_OUT);
        /**
         * Login as admin
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

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("showChartID")))
                .click();
        /**
         * chart
         */
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("chartStationBtn")))
                .click();

        Thread.sleep(3000);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("chartStatisticAgesBtn")))
                .click();

        Thread.sleep(3000);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("chartTicketCntBtn")))
                .click();

        Thread.sleep(3000);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("chartProfitBtn")))
                .click();

        Thread.sleep(3000);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("logoutID")))
                .click();

        Thread.sleep(1000);
    }

    @After
    public void closeDriver() throws Exception {
        driver.quit();
    }
}
