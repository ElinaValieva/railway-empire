package com.elina.railwayApp.junit.service;

import com.elina.railwayApp.DAO.StationDAO;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.model.Station;
import com.elina.railwayApp.model.Status;
import com.elina.railwayApp.service.Implementation.StationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StationServiceTest {

    private Station station;

    @Mock
    private StationDAO stationDAO;

    @InjectMocks
    private StationServiceImpl stationService;

    @Before
    public void setUp() {
        station = new Station();
        station.setId(27L);
        station.setName("Ufa");
        station.setLongitude(55.9720554);
        station.setLatitude(54.7387621);
        Status status = new Status();
        status.setId(2L);
        status.setStatusName("NOT_USED");
        station.setStatus(status);
    }

    @Test
    public void getAllStations() {
        when(stationDAO.getAll()).thenReturn(new ArrayList<>());
        stationService.getAllStations();
        verify(stationDAO).getAll();
    }

    @Test
    public void getById() {
        when(stationDAO.getById(27L)).thenReturn(new Station());
        stationService.getById(27L);
        verify(stationDAO).getById(27L);
    }

    @Test
    public void getByName() {
        when(stationDAO.findByName("Ufa")).thenReturn(new Station());
        stationService.getByName("Ufa");
        verify(stationDAO).findByName("Ufa");
    }
}