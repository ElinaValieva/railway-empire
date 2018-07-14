package com.elina.railwayApp.service;

import com.elina.railwayApp.DAO.TicketDAO;
import com.elina.railwayApp.configuration.common.Utils;
import com.elina.railwayApp.model.*;
import com.elina.railwayApp.service.Implementation.TicketServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceTest {

    private Ticket ticket;
    private Schedule schedule;
    private Date dateFrom, dateTo;

    @Mock
    private TicketDAO ticketDAO;

    @InjectMocks
    private TicketServiceImpl ticketService;


    @Before
    public void setUp() throws Exception {
        dateFrom = Utils.parseToDate("2018-06-29");
        dateTo = Utils.parseToDate("2018-06-30");
        Train train = new Train();
        train.setId(1L);
        train.setName("T120");
        Station stationDeparture = new Station();
        stationDeparture.setId(1L);
        Station stationArrival = new Station();
        stationArrival.setId(2L);

        schedule = new Schedule();
        schedule.setId(100L);
        schedule.setTrain(train);
        schedule.setDateDeparture(dateFrom);
        schedule.setDateArrival(dateTo);
        schedule.setStationDeparture(stationDeparture);
        schedule.setStationArrival(stationArrival);

        Seat seat = new Seat();
        User user = new User();
        ticket = new Ticket();
        ticket.setSeat(seat);
        ticket.setPrice(1200);
        ticket.setUser(user);
        ticket.setSchedule(new Schedule());
    }


    @Test
    public void add() {
        ticketDAO.add(ticket);
        ticketService.add(ticket);
    }

    @Test
    public void delete() {
        ticketDAO.delete(ticket);
        ticketService.delete(ticket);
    }

    @Test
    public void update() {
        ticketDAO.update(ticket);
        ticketService.update(ticket);
    }

    @Test
    public void getAll() {
        when(ticketDAO.getAll()).thenReturn(new ArrayList<>());
        ticketService.getAll();
        verify(ticketDAO).getAll();
    }

    @Test
    public void getById() {
        when(ticketDAO.getById(2L)).thenReturn(new Ticket());
        ticketService.getById(2L);
        verify(ticketDAO).getById(2L);
    }


    @Test
    public void getBySchedules() {
        when(ticketDAO.getBySchedule(schedule)).thenReturn(new ArrayList<>());
        ticketService.getBySchedules(schedule);
        verify(ticketDAO).getBySchedule(schedule);
    }

    @Test
    public void getByDate() {
        when(ticketDAO.getByDate(dateFrom)).thenReturn(new ArrayList<>());
        ticketService.getByDate(dateTo);
        verify(ticketDAO).getByDate(dateTo);
    }

    @Test
    public void getByDates() {
        when(ticketDAO.getByDates(dateFrom, dateTo)).thenReturn(new ArrayList<>());
        ticketService.getByDates(dateFrom, dateTo);
        verify(ticketDAO).getByDates(dateFrom, dateTo);
    }
}