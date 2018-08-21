package com.elina.railwayApp.integrations;

import com.elina.railwayApp.DTO.ScheduleDTO;
import com.elina.railwayApp.configuration.*;
import com.elina.railwayApp.configuration.common.Utils;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.exception.ErrorCode;
import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.service.ScheduleService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.ParseException;
import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        HibernateConfiguration.class,
        SecurityConfiguration.class,
        SecurityInitializer.class,
        ViewConfigurations.class,
        ViewInitializer.class})
@WebAppConfiguration
public class ScheduleServiceJTest {

    ScheduleDTO schedule;

    @Autowired
    private ScheduleService scheduleService;

    @Before
    public void setUp() {
        schedule = new ScheduleDTO();
        schedule.setTrainName("T120");
        schedule.setStationArrivalName("Ufa");
        schedule.setDateDeparture("2018-08-01 01:00:00");
    }

    @Test
    public void addScheduleWithNullParameter() {
        try {
            scheduleService.add(schedule);
        } catch (Exception e) {
            if (e instanceof BusinessLogicException) {
                Assert.assertEquals(ErrorCode.NULL_ELEMENTS.getMessage(), ((BusinessLogicException) e).getError());
            }
        }
    }

    @Test
    public void addScheduleForSameDates() {
        try {
            schedule.setStationDepartureName("Ufa");
            scheduleService.add(schedule);
        } catch (Exception e) {
            if (e instanceof BusinessLogicException) {
                Assert.assertEquals(ErrorCode.SAME_STATIONS.getMessage(), ((BusinessLogicException) e).getError());
            }
        }
    }

    @Test
    public void addScheduleWithWrongDates() {
        try {
            schedule.setDateDeparture("2018-01-01 01:00:00");
            schedule.setStationDepartureName("Moscow");
            scheduleService.add(schedule);
        } catch (Exception e) {
            if (e instanceof BusinessLogicException) {
                Assert.assertEquals(ErrorCode.WRONG_DATES.getMessage(), ((BusinessLogicException) e).getError());
            }
        }
    }

    @Test
    public void addScheduleForPast() {
        try {
            schedule.setDateDeparture("2018-07-30 01:00:00");
            schedule.setStationDepartureName("Moscow");
            scheduleService.add(schedule);
        } catch (Exception e) {
            if (e instanceof BusinessLogicException) {
                Assert.assertEquals(ErrorCode.SCHEDULE_FOR_CURRENT_DAY.getMessage(), ((BusinessLogicException) e).getError());
            }
        }
    }

    @Test
    public void deleteAvailableSchedule() {
        try {
            scheduleService.delete(165L);
        } catch (Exception e) {
            if (e instanceof BusinessLogicException) {
                Assert.assertEquals(ErrorCode.SCHEDULE_IS_AVAILABLE.getMessage(), ((BusinessLogicException) e).getError());
            }
        }
    }

    @Test
    public void updateScheduleWithWrongParameters() {
        try {
            scheduleService.update(schedule);
        } catch (Exception e) {
            if (e instanceof BusinessLogicException) {
                Assert.assertEquals(ErrorCode.SCHEDULE_IS_AVAILABLE.getMessage(), ((BusinessLogicException) e).getError());
            }
        }
    }

    @Test
    public void updateScheduleForSameDates() {
        try {
            schedule.setStationDepartureName("Ufa");
            scheduleService.update(schedule);
        } catch (Exception e) {
            if (e instanceof BusinessLogicException) {
                Assert.assertEquals(ErrorCode.SAME_STATIONS.getMessage(), ((BusinessLogicException) e).getError());
            }
        }
    }

    @Test
    public void updateScheduleWithWrongDates() {
        try {
            schedule.setDateDeparture("2018-01-01 01:00:00");
            schedule.setStationDepartureName("Moscow");
            scheduleService.update(schedule);
        } catch (Exception e) {
            if (e instanceof BusinessLogicException) {
                Assert.assertEquals(ErrorCode.WRONG_DATES.getMessage(), ((BusinessLogicException) e).getError());
            }
        }
    }

    @Test
    public void getAll() {
        Assert.assertTrue(scheduleService.getAll().size() > 0);
    }

    @Test
    public void getAllForToday() throws ParseException {
        Assert.assertTrue(scheduleService.getAllForToday().size() > 0);
    }

    @Test
    public void getById() {
        Assert.assertNotNull(scheduleService.getById(165L));
    }

    @Test
    public void getByDate() throws ParseException {
        Date dateDeparture = Utils.parseToDate("2018-08-02");
        Assert.assertTrue(scheduleService.getByDate(dateDeparture).size() > 0);
    }

    @Test
    public void getByDates() throws BusinessLogicException, ParseException {
        Date dateDeparture = Utils.parseToDate("2018-08-01");
        Date dateArrival = Utils.parseToDate("2018-08-30");
        Assert.assertTrue(scheduleService.getByDates(dateDeparture, dateArrival).size() > 0);
    }

    @Test
    public void getByStationsAndDate() {
        Schedule scheduleTest = scheduleService.getById(165L);
        Assert.assertTrue(scheduleService.getByStationsAndDate(scheduleTest).size() > 0);
    }

    @Test
    public void getByDateAndTrainToCheckIntersection() {
        Schedule scheduleTest = scheduleService.getById(165L);
        Assert.assertFalse(scheduleService.getByDateAndTrainToCheckIntersection(scheduleTest).size() == 0);
    }

    @Test
    public void getByTrainAndDate() {
        Schedule scheduleTest = scheduleService.getById(165L);
        Assert.assertTrue(scheduleService.getByTrainAndDate(scheduleTest).size() > 0);
    }

    @Test
    public void getByStationArrivalAndDate() {
        Schedule scheduleTest = scheduleService.getById(165L);
        Assert.assertTrue(scheduleService.getByStationArrivalAndDate(scheduleTest).size() > 0);
    }


    @Test
    public void getTransferList() throws ParseException, BusinessLogicException {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setDateArrival("2018-08-24");
        scheduleDTO.setDateDeparture("2018-08-22");
        scheduleDTO.setId(170L);
        scheduleDTO.setStationDepartureName("Kazan");
        scheduleDTO.setStationArrivalName("Moscow");
        scheduleDTO.setTrainName("T135");
        Assert.assertTrue(scheduleService.getTransferSchedules(scheduleDTO).size() == 0);
    }


    @Test
    public void getDirectSchedulesFromDTOByStationsAndDatesAndTrain() throws
            ParseException, BusinessLogicException {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setDateArrival("2018-08-24");
        scheduleDTO.setDateDeparture("2018-08-22");
        scheduleDTO.setId(170L);
        scheduleDTO.setStationDepartureName("Kazan");
        scheduleDTO.setStationArrivalName("Moscow");
        scheduleDTO.setTrainName("T135");
        Assert.assertTrue(scheduleService.getDirectSchedulesFromDTOByStationsAndDatesAndTrain(scheduleDTO).size() > 0);
    }

    @Test
    public void getDirectSchedulesFromDTOByStations() throws ParseException, BusinessLogicException {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setDateArrival("2018-08-24");
        scheduleDTO.setDateDeparture("2018-08-22");
        scheduleDTO.setId(170L);
        scheduleDTO.setStationDepartureName("Kazan");
        scheduleDTO.setStationArrivalName("Moscow");
        scheduleDTO.setTrainName("T135");
        Assert.assertTrue(scheduleService.getDirectSchedulesFromDTOByStations(scheduleDTO).size() > 0);
    }

    @Test
    public void getDirectSchedulesFromDTOByTrain() throws ParseException, BusinessLogicException {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setDateArrival("2018-08-24");
        scheduleDTO.setDateDeparture("2018-08-22");
        scheduleDTO.setId(170L);
        scheduleDTO.setStationDepartureName("Kazan");
        scheduleDTO.setStationArrivalName("Moscow");
        scheduleDTO.setTrainName("T135");
        Assert.assertTrue(scheduleService.getDirectSchedulesFromDTOByTrain(scheduleDTO).size() > 0);
    }

    @Test
    public void getDirectSchedulesFromDTOByDates() throws ParseException, BusinessLogicException {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setDateArrival("2018-08-24");
        scheduleDTO.setDateDeparture("2018-08-22");
        scheduleDTO.setId(170L);
        scheduleDTO.setStationDepartureName("Kazan");
        scheduleDTO.setStationArrivalName("Moscow");
        scheduleDTO.setTrainName("T135");
        Assert.assertTrue(scheduleService.getDirectSchedulesFromDTOByDates(scheduleDTO).size() > 0);
    }

    @Test
    public void getSeats() throws BusinessLogicException {
        Assert.assertNotNull(scheduleService.getSeats(165L));
    }

    @Test
    public void getByIdScheduleDTO() {
        Assert.assertNotNull(scheduleService.getByIdScheduleDTO(165L));
    }
}