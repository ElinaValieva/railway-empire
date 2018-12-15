package com.elina.railwayApp.selenium.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverRailway extends ChromeDriver {

    private final static Integer SPEED = 1000;
    private WebDriver webDriver;

    public WebDriverRailway(){
        WebDriverManager.chromedriver().setup();
        webDriver = new WebDriverRailway();
        webDriver.manage().window().maximize();
    }

    @Override
    public WebElement findElement(By by) {
        try {
            Thread.sleep(this.SPEED);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return by.findElement(this);
    }
}
