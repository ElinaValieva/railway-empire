package com.elina.railwayApp.junit.configuration.common;

import com.elina.railwayApp.configuration.common.Utils;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void checkCurrentDay() throws ParseException {
        Date date = Utils.parseToDate("2018-07-06 17:30:00");
        assertTrue(Utils.checkCurrentDay(date));
    }

    @Test
    public void checkForCurrentDayForBookingTicket() throws ParseException {
        Date date = Utils.parseToDateTime("2018-07-09 22:30:00");
        assertFalse(Utils.checkForCurrentDayForBookingTicket(date));

        date = Utils.parseToDateTime("2022-08-01 22:00:00");
        assertTrue(Utils.checkForCurrentDayForBookingTicket(date));
    }
}