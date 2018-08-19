package com.elina.railwayApp.selenium;

import com.elina.railwayApp.selenium.config.SeleniumConfig;
import com.elina.railwayApp.selenium.config.WebDriverRailway;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Business case:
 * - login, add new train
 */
public class AutoTestForEditingItems {

    private WebDriver driver = null;

    @Before
    public void createDriver() {
        System.setProperty(SeleniumConfig.PROPERTY_DRIVER, SeleniumConfig.PROPERTY_DRIVER_PATH);
        driver = new WebDriverRailway();
        driver.get(SeleniumConfig.LOGIN_URL);
    }

    /**
     * 1. Log as MANAGER
     * 2. Create new train
     * 3. Edit this train
     * 4. Create schedule with this train
     * 5. Try to delete train
     * 6. Logout
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

        // ---- Create new train

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("showNewItemID")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//button[@id='addTrainBtn']/img")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("(//input[@type='text'])[7]")))
                .sendKeys("T1001");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//form[@id='trainId']/button")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("(//button[@type='button'])[2]")))
                .click();

        // ---- Edit this train

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//a[contains(text(),'Edit')]")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//button[@id='addTrainBtn']/img")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//button[@id='T1001']")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//input[@id='swal-input1']")))
                .clear();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//input[@id='swal-input1']")))
                .sendKeys("T000");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector("button.swal2-confirm.swal2-styled")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector("button.swal2-confirm.swal2-styled")))
                .click();

        // ---- Create schedule with this train

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//a[contains(text(),'New')]")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//button[@id='addScheduleBtn']")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("(//input[@type='text'])[4]")))
                .sendKeys("Ufa");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("(//input[@type='text'])[5]")))
                .sendKeys("Moscow");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("(//input[@type='text'])[6]")))
                .sendKeys("T000");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//input[@value='2018-00-00T00:00:00']")))
                .sendKeys("01.09.2018" + Keys.TAB + "10:00:00");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//form[@id='scheduleId']/button")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector("button.swal2-confirm.swal2-styled")))
                .click();

        // ---- Try to delete train

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//a[contains(text(),'Edit')]")))
                .click();
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//button[@id='addTrainBtn']/img")))
                .click();
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("(//button[@id='T000'])[2]")))
                .click();

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
