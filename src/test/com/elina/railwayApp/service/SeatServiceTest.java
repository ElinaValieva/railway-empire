package com.elina.railwayApp.service;

import com.elina.railwayApp.DAO.SeatDAO;
import com.elina.railwayApp.model.Seat;
import com.elina.railwayApp.model.Train;
import com.elina.railwayApp.service.Implementation.SeatServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SeatServiceTest {

    private Seat seat;
    private Train train;

    @Mock
    private SeatDAO seatDAO;

    @InjectMocks
    private SeatServiceImpl seatService;

    @Before
    public void setUp(){
        seat = new Seat();
        seat.setSeat(1);
        seat.setCarriage(2);

        train = new Train();
        train.setId(13L);
        train.setName("T120");

        seat.setTrain(train);
    }

    @Test
    public void add() {
        seatDAO.add(seat);
        seatService.add(seat);
    }

    @Test
    public void delete() {
        seatDAO.delete(seat);
        seatService.delete(seat);
    }

    @Test
    public void update() {
        seatDAO.update(seat);
        seatService.update(seat);
    }

    @Test
    public void getAll() {
        when(seatDAO.getAll()).thenReturn(new ArrayList<>());
        seatService.getAll();
        verify(seatDAO).getAll();
    }

    @Test
    public void getById() {
        when(seatDAO.getById(1791L)).thenReturn(new Seat());
        seatService.getById(1791L);
        verify(seatDAO).getById(1791L);
    }

    @Test
    public void getByTrainAndCarriageAndSeat() {
        when(seatDAO.getByTrainAndCarriageAndSeat(train, 10, 30)).thenReturn(new Seat());
        seatService.getByTrainAndCarriageAndSeat(train, 10, 30);
        verify(seatDAO).getByTrainAndCarriageAndSeat(train, 10, 30);
    }
}