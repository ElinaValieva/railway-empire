package com.elina.railwayApp.mock.configuration.common;

import com.elina.railwayApp.configuration.common.Utils;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void checkCurrentDay() throws ParseException {
        Date date = Utils.parseToDateTime("2018-08-01 14:00:00");
        assertTrue(Utils.checkCurrentDay(date));
    }

    @Test
    public void checkForCurrentDayForBookingTicket() throws ParseException {
        Date date = Utils.parseToDateTime("2018-07-09 22:30:00");
        assertFalse(Utils.checkForCurrentDayForBookingTicket(date));

        date = Utils.parseToDateTime("2018-08-01 16:30:00");
        assertFalse(Utils.checkForCurrentDayForBookingTicket(date));
    }

    @Test
    public void checkTransfer() throws ParseException {
        Date dateDeparture = Utils.parseToDate("2018-08-22 13:00:38.0");
        Date dateArrival = Utils.parseToDate("2018-08-22 23:50:00.0");
        int deltaMin = 15;
        int deltaMax = 360;
        assertFalse(Utils.checkTransfer(dateDeparture, dateArrival, deltaMin, deltaMax));
    }
}