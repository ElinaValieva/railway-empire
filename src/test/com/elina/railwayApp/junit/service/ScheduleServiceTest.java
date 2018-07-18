package com.elina.railwayApp.junit.service;

import com.elina.railwayApp.DAO.ScheduleDAO;
import com.elina.railwayApp.configuration.common.Utils;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Station;
import com.elina.railwayApp.model.Train;
import com.elina.railwayApp.service.Implementation.ScheduleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleServiceTest {

    private Schedule schedule;
    private Station station;
    private Date date, dateTo;

    @Mock
    private ScheduleDAO scheduleDAO;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @Before
    public void setUp() throws Exception {
        date = new Date();
        dateTo = Utils.getNextDay(new Date());
        schedule = new Schedule();
        schedule.setId(31L);

        Train train = new Train();
        train.setId(13L);
        train.setName("T120");
        schedule.setTrain(train);

        station = new Station();
        schedule.setStationDeparture(station);

        Station stationArrival = new Station();
        schedule.setStationArrival(stationArrival);

        Date dateDeparture = new Date();
        schedule.setDateDeparture(dateDeparture);

        Date dateArrival = new Date();
        schedule.setDateArrival(dateArrival);
    }

    @Test
    public void getById() {
        when(scheduleDAO.getById(31L)).thenReturn(new Schedule());
        scheduleService.getById(31L);
        verify(scheduleDAO).getById(31L);
    }

    @Test
    public void getByDate() {
        when(scheduleDAO.getByDate(date)).thenReturn(new ArrayList<>());
        scheduleService.getByDate(date);
        verify(scheduleDAO).getByDate(date);
    }

    @Test
    public void getByDates() throws ParseException, BusinessLogicException {
        when(scheduleDAO.getByDates(date, dateTo)).thenReturn(new ArrayList<>());
        scheduleService.getByDates(date, dateTo);
        verify(scheduleDAO).getByDates(date, dateTo);
    }

    @Test
    public void getByStationsAndDate() {
        when(scheduleDAO.getByStationsAndDates(schedule)).thenReturn(new ArrayList<>());
        scheduleService.getByStationsAndDate(schedule);
        verify(scheduleDAO).getByStationsAndDate(schedule);
    }

    @Test
    public void getByDateAndTrainToCheckIntersection() {
        when(scheduleDAO.getByDateAndTrainToCheckIntersection(schedule)).thenReturn(new ArrayList<>());
        scheduleService.getByDateAndTrainToCheckIntersection(schedule);
        verify(scheduleDAO).getByDateAndTrainToCheckIntersection(schedule);
    }

    @Test
    public void getByTrainAndDate() {
        when(scheduleDAO.getByTrainAndDate(schedule)).thenReturn(new ArrayList<>());
        scheduleService.getByTrainAndDate(schedule);
        verify(scheduleDAO).getByTrainAndDate(schedule);
    }

    @Test
    public void getByStationArrivalAndDate() {
        when(scheduleDAO.getByStationArrivalAndDate(schedule)).thenReturn(new ArrayList<>());
        scheduleService.getByStationArrivalAndDate(schedule);
        verify(scheduleDAO).getByStationArrivalAndDate(schedule);
    }

    @Test
    public void getByStationArrivalAndDates() {
        when(scheduleDAO.getByStationArrivalAndDates(station, date, date)).thenReturn(new ArrayList<>());
        scheduleService.getByStationArrivalAndDates(station, date, date);
        verify(scheduleDAO).getByStationArrivalAndDates(station, date, date);
    }
}