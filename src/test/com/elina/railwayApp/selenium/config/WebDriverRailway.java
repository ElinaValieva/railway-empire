package com.elina.railwayApp.selenium.config;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverRailway extends ChromeDriver {

    private final static Integer SPEED = 500;

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
