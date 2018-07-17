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
 * - login, add new train
 */
public class AutomaticNewPageTest {

    private WebDriver driver = null;

    @Before
    public void createDriver() {
        System.setProperty(SeleniumConfig.PROPERTY_DRIVER, SeleniumConfig.PROPERTY_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.get(SeleniumConfig.LOGIN_URL);
    }

    @Test
    public void automaticNewPage() throws InterruptedException {
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
         * new page
         */
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("showNewItemID")))
                .click();


        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("addTrainBtn")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("trainItemsRailway")))
                .sendKeys("T001");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("cntCarriageItemsRailway")))
                .sendKeys("10");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("addItem2")))
                .click();

        Thread.sleep(3000);
    }

    @After
    public void closeDriver() throws Exception {
        driver.quit();
    }
}
