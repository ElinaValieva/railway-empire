package com.elina.railwayApp.integrations;


import com.elina.railwayApp.DTO.StationDTO;
import com.elina.railwayApp.configuration.*;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.exception.ErrorCode;
import com.elina.railwayApp.model.Station;
import com.elina.railwayApp.service.StationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        HibernateConfiguration.class,
        SecurityConfiguration.class,
        SecurityInitializer.class,
        ViewConfigurations.class,
        ViewInitializer.class})
@WebAppConfiguration
public class StationServiceJTest {

    @Autowired
    private StationService stationService;

    Station station;
    StationDTO stationDTO;

    @Before
    public void setUp() {
        station = new Station();
        stationDTO = new StationDTO();
        stationDTO.setName("Ufa");
    }

    @Test
    public void addWrongStation() {
        try {
            stationService.add(stationDTO);
        } catch (Exception e) {
            if (e instanceof BusinessLogicException) {
                Assert.assertEquals(ErrorCode.STATION_NOT_UNIQUE.getMessage(), ((BusinessLogicException) e).getError());
            }
        }
    }

    @Test
    public void deleteNull() {
        try {
            stationService.delete("Wrong");
        } catch (Exception e) {
            if (e instanceof BusinessLogicException) {
                Assert.assertEquals(ErrorCode.NULL_ELEMENTS.getMessage(), ((BusinessLogicException) e).getError());
            }
        }
    }

    @Test
    public void updateWrong() {
        stationDTO.setNewName("Ufa");
        try {
            stationService.update(stationDTO);
        } catch (Exception e) {
            if (e instanceof BusinessLogicException) {
                Assert.assertEquals(ErrorCode.STATION_NOT_UNIQUE.getMessage(), ((BusinessLogicException) e).getError());
            }
        }
    }

    @Test
    public void getAll() {
        Assert.assertTrue(stationService.getAll().size() > 0);
    }

    @Test
    public void getAllStations() {
        Assert.assertTrue(stationService.getAllStations().size() > 0);
    }

    @Test
    public void getAllDeletedStations() {
        Assert.assertTrue(stationService.getAllDeletedStations().size() > 0);
    }

    @Test
    public void getById() {
        Assert.assertNotNull(stationService.getById(30L));
    }

    @Test
    public void getByName() {
        Assert.assertNotNull(stationService.getByName("Ufa"));
    }

    @Test
    public void getStationsName() {
        Assert.assertTrue(stationService.getStationsName().size() > 0);
    }
}
