package com.elina.railwayApp.service;

import com.elina.railwayApp.DAO.TrainDAO;
import com.elina.railwayApp.DTO.TrainDTO;
import com.elina.railwayApp.model.Seat;
import com.elina.railwayApp.model.Train;
import com.elina.railwayApp.service.Implementation.TrainServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrainServiceTest {
    private Train train;
    private TrainDTO trainDTO;

    @Mock
    private TrainDAO trainDAO;

    @InjectMocks
    private TrainServiceImpl trainService;

    @Before
    public void setUp() throws Exception {
        trainDTO = new TrainDTO();
        trainDTO.setCntSeats(10);
        trainDTO.setCntCarriage(2);
        trainDTO.setTrainName("T123");
        trainDTO.setTrainNewName("T124");
        train = new Train();
        train.setId(18L);
        train.setName("T123");
        Set<Seat> seats = trainService.getSeats(2, 10, train);
        train.setSeats(seats);
    }

    @Test
    public void getAll() {
        when(trainDAO.getAll()).thenReturn(new ArrayList<>());
        trainService.getAll();
        verify(trainDAO).getAll();
    }

    @Test
    public void getById() {
        when(trainDAO.getById(1L)).thenReturn(new Train());
        trainService.getById(1L);
        verify(trainDAO).getById(1L);
    }

    @Test
    public void getByName() {
        when(trainDAO.getByName("T120")).thenReturn(new Train());
        trainService.getByName("T120");
        verify(trainDAO).getByName("T120");
    }
}