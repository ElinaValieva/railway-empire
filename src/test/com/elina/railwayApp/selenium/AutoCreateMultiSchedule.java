package com.elina.railwayApp.selenium;

import com.elina.railwayApp.selenium.config.SeleniumConfig;
import com.elina.railwayApp.selenium.config.WebDriverRailway;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class AutoCreateMultiSchedule {

    private WebDriver webDriver = null;
    private final String STATION_DEPARTURE = "Ufa";
    private final String STATION_INTER = "Kazan";
    private final String STATION_ARRIVAL = "Moscow";
    private final String DATE_DEPARTURE = "21.08.2018";
    private final String TIME_DEPARTURE = "19:00:00";
    private final String TIME_INTER = "04:00:00";
    private final String DATE_ARRIVAL = "23.08.2018";
    private final String DATE_INNER = "22.08.2018";
    private final String TRAIN = "T135";

    @Before
    public void createDriver() {
        System.setProperty(SeleniumConfig.PROPERTY_DRIVER, SeleniumConfig.PROPERTY_DRIVER_PATH);
        webDriver = new WebDriverRailway();
        webDriver.manage().window().maximize();
        webDriver.get(SeleniumConfig.LOGIN_URL);
    }

    /**
     * 1. Log as ADMIN
     * 2. Create schedule Ufa - SPb
     * 3. Look board app
     * 4. Log as USER
     * 5. Book ticket
     * 6. Look at mail messages
     * 7. Look at trips tab
     * 8. Log out
     */
    @Test
    public void automaticTest() throws InterruptedException {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, SeleniumConfig.TIME_OUT);

        // ---- Log as ADMIN in main App
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("userNameUser")))
                .sendKeys("login@mail.ru");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("passwordUser")))
                .sendKeys("pass");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//button[@id = 'loginUser']")))
                .click();

        // ---- Create schedule

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//a[contains(text(),'New')]")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//button[@id='addScheduleBtn']")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("(//input[@type='text'])[4]")))
                .sendKeys(STATION_DEPARTURE);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("(//input[@type='text'])[5]")))
                .sendKeys(STATION_INTER);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("(//input[@type='text'])[6]")))
                .sendKeys(TRAIN);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//input[@value='2018-00-00T00:00:00']")))
                .sendKeys(DATE_DEPARTURE + Keys.TAB + TIME_DEPARTURE);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//form[@id='scheduleId']/button")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector("button.swal2-confirm.swal2-styled")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("(//input[@type='text'])[4]")))
                .clear();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("(//input[@type='text'])[4]")))
                .sendKeys(STATION_INTER);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("(//input[@type='text'])[5]")))
                .clear();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("(//input[@type='text'])[5]")))
                .sendKeys(STATION_ARRIVAL);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//input[@value='2018-00-00T00:00:00']")))
                .sendKeys(DATE_INNER + Keys.TAB + TIME_INTER);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//form[@id='scheduleId']/button")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector("button.swal2-confirm.swal2-styled")))
                .click();


        // ---- Look at board APP
        ((JavascriptExecutor) webDriver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
        webDriver.get(SeleniumConfig.BOARD_URL);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//div[@id='j_idt11:menu']/div[3]/span")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//li[@id='j_idt11:menu_0']")))
                .click();

        Thread.sleep(3000);

        // ---- Log as USER in new App
        ((JavascriptExecutor) webDriver).executeScript("window.open()");
        tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(2));
        webDriver.get(SeleniumConfig.ANGULAR_URL);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.name("username")))
                .sendKeys("veaufa@mail.ru");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.name("password")))
                .sendKeys("pass");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.name("singIn")))
                .click();

        // ---- Book ticket
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("showScheduleID")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector("a.carousel-control-next")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("stationDepartureByDates")))
                .sendKeys(STATION_DEPARTURE);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("stationArrivalByDates")))
                .sendKeys(STATION_ARRIVAL);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("dateDepartureByDates")))
                .sendKeys(DATE_DEPARTURE);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("dateArrivalByDates")))
                .sendKeys(DATE_ARRIVAL);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//input[@name='transfer']")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.name("findSchedule")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//div/div[2]/button")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//li[20]")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//button[2]")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector("button.swal2-confirm.swal2-styled")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.name("next")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//li[8]")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//button[2]")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector("button.swal2-confirm.swal2-styled")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("showTripsID")))
                .click();

        ((JavascriptExecutor) webDriver).executeScript("window.open()");
        tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(3));
        webDriver.get(SeleniumConfig.MAIL_URL);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.name("login")))
                .sendKeys("veaufa@mail.ru");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.name("password")))
                .sendKeys("******");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//input[@value='Войти']")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//div[@id='b-letters']/div/div[2]/div/div[2]/div/div/a/div[5]/div[3]/div[2]")))
                .click();

        Thread.sleep(2000);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//a[contains(text(),'Посмотреть')]")))
                .click();

        Thread.sleep(10000);
    }

    @After
    public void closeDriver() {
        webDriver.quit();
    }
}
