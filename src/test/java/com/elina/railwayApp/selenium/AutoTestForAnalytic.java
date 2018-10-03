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

public class AutoTestForAnalytic {

    private WebDriver driver = null;

    @Before
    public void createDriver() {
        System.setProperty(SeleniumConfig.PROPERTY_DRIVER, SeleniumConfig.PROPERTY_DRIVER_PATH);
        driver = new WebDriverRailway();
        driver.manage().window().fullscreen();
        driver.get(SeleniumConfig.LOGIN_URL);
    }

    /**
     * 1. Log as ADMIN
     * 2. Look all charts
     * 3. Look all reestablish stations page
     * 4. Look all reestablish trains page
     * 5. Reestablish train
     * 6. Look audit page
     * 7. Look audit info
     * 8. Logout
     *
     * @throws InterruptedException
     */
    @Test
    public void automaticTest() throws InterruptedException {
        WebDriverWait webDriverWait = new WebDriverWait(driver, SeleniumConfig.TIME_OUT);

        // ---- log as ADMIN

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

        // ---- Look all charts

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("chartStationBtn")))
                .click();

        Thread.sleep(1000);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("chartStatisticAgesBtn")))
                .click();

        Thread.sleep(1000);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("chartTicketCntBtn")))
                .click();

        Thread.sleep(1000);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("chartProfitBtn")))
                .click();

        Thread.sleep(1000);

        // ---- Look all reestablish stations page

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//a[contains(text(),'Audit')]")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//div[@id='app-deleted']/div/div/button/img")))
                .click();

        // ---- Look all reestablish trains page

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//div[@id='app-deleted']/div/div[2]/button/img")))
                .click();

        // ---- Reestablish train

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//table[@id='trainTable']/tbody/tr[4]/td[4]/button/img")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("(//button[@type='button'])[2]")))
                .click();

        // ---- Look audit page

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//div[@id='app-deleted']/div/div[3]/button/img")))
                .click();

        Thread.sleep(1000);

        // ---- Look audit info

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//table[@id='auditTable']/tbody/tr/td[5]/button/img")))
                .click();

        Thread.sleep(500);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector("button.swal2-confirm.swal2-styled")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//table[@id='auditTable']/tbody/tr[8]/td[5]/button/img")))
                .click();

        Thread.sleep(500);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector("button.swal2-confirm.swal2-styled")))
                .click();

        // ---- Logout

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//a[contains(text(),'Sign out')]")))
                .click();

        Thread.sleep(1000);
    }

    @After
    public void closeDriver() {
        driver.quit();
    }
}
