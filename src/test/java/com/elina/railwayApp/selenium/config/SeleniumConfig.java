package com.elina.railwayApp.selenium.config;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Log4j
public enum SeleniumConfig {
    SELENIUM_CONFIG_LOGIN_URL("login.url"),
    SELENIUM_CONFIG_BOARD_URL("board.url"),
    SELENIUM_CONFIG_CLIENT_URL("client.url"),
    SELENIUM_CONFIG_MAIL("mail.url"),
    SELENIUM_CONFIG_TIMEOUT("timeout.value");

    private String config;
    private Properties properties;
    private Logger logger = LogManager.getLogger(SeleniumConfig.class.getName());

    SeleniumConfig(String config) {
        properties = new Properties();
        try {
            properties.load(new FileReader(SeleniumConfig.class.getClassLoader().getResource("selenoum.properties").getFile()));
        } catch (IOException e) {
            logger.error("", e);
        }
        this.config = config;
    }

    public String getConfig(){
        return properties.getProperty(config);
    }

}
