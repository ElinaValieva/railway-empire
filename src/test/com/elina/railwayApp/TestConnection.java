package com.elina.railwayApp;


import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Log4j
public class TestConnection {

    private static final String DRIVER_NOT_FOUND_MSG = "DRIVER NOT FOUND!";
    private static final String DRIVER_FOUND_MSG = "DRIVER FOUND!";
    private static final String CONNECTED_MSG = "CONNECTED!";
    private static final String ERROR_MSG = "ERROR";

    private static final String DRIVER_TO_CHECK = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/railwayDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "elinas";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        try {
            Class.forName(DRIVER_TO_CHECK);
            log.info(DRIVER_FOUND_MSG);
            System.out.println(DRIVER_FOUND_MSG);
        } catch (ClassNotFoundException e) {
            log.warn(DRIVER_NOT_FOUND_MSG);
            System.out.println(DRIVER_NOT_FOUND_MSG);
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println(CONNECTED_MSG);
            log.info(CONNECTED_MSG);
        } catch (SQLException e) {
            log.warn(ERROR_MSG);
            System.out.println(ERROR_MSG + e.toString());
        }
    }

}
