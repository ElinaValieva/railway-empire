package com.elina.railwayApp.integrations;

import com.elina.railwayApp.DTO.TrainDTO;
import com.elina.railwayApp.configuration.*;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.exception.ErrorCode;
import com.elina.railwayApp.model.Train;
import com.elina.railwayApp.service.TrainService;
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
public class TrainServiceJTest {

    Train train;
    TrainDTO trainDTO;

    @Autowired
    private TrainService trainService;

    @Before
    public void setUp() {
        train = new Train();
        trainDTO = new TrainDTO();

    }

    @Test
    public void addNotUniqueTrain() {
        trainDTO.setTrainName("T135");
        try {
            trainService.add(trainDTO);
        } catch (Exception e) {
            if (e instanceof BusinessLogicException) {
                Assert.assertEquals(ErrorCode.TRAIN_NOT_UNIQUE.getMessage(), ((BusinessLogicException) e).getError());
            }
        }
    }

    @Test
    public void addTrainWithWrongParameters() {
        trainDTO.setTrainName("T135");
        trainDTO.setCntCarriage(-1);
        try {
            trainService.add(trainDTO);
        } catch (Exception e) {
            if (e instanceof BusinessLogicException) {
                Assert.assertEquals(ErrorCode.TRAIN_NOT_UNIQUE.getMessage(), ((BusinessLogicException) e).getError());
            }
        }
    }

    @Test
    public void deleteNull() {
        try {
            trainService.delete("T00000");
        } catch (Exception e) {
            if (e instanceof BusinessLogicException) {
                Assert.assertEquals(ErrorCode.NULL_ELEMENTS.getMessage(), ((BusinessLogicException) e).getError());
            }
        }
    }

    @Test
    public void updateNotUniqueTrain() {
        trainDTO.setTrainName("T135");
        try {
            trainService.update(trainDTO);
        } catch (Exception e) {
            if (e instanceof BusinessLogicException) {
                Assert.assertEquals(ErrorCode.TRAIN_NOT_UNIQUE.getMessage(), ((BusinessLogicException) e).getError());
            }
        }
    }

    @Test
    public void getAll() {
        Assert.assertTrue(trainService.getAll().size() > 0);
    }

    @Test
    public void getAllTrains() {
        Assert.assertTrue(trainService.getAllTrains().size() > 0);
    }

    @Test
    public void getById() {
        Assert.assertNotNull(trainService.getById(30L));
    }

    @Test
    public void getByName() {
        Assert.assertNotNull(trainService.getByName("T135"));
    }

    @Test
    public void getLastPositionTrain() {
        Assert.assertNotNull(trainService.getLastPositionTrain());
    }

    @Test
    public void getAllDeletedTrains() {
        Assert.assertTrue(trainService.getAllDeletedTrains().size() > 0);
    }

    @Test
    public void getTrainsName() {
        Assert.assertTrue(trainService.getTrainsName().size() > 0);
    }
}
