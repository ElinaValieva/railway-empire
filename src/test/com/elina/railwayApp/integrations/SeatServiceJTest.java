package com.elina.railwayApp.integrations;

import com.elina.railwayApp.configuration.*;
import com.elina.railwayApp.service.SeatService;
import org.junit.Assert;
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
public class SeatServiceJTest {

    @Autowired
    private SeatService seatService;

    @Test
    public void getAll() {
        Assert.assertTrue(seatService.getAll().size() > 0);
    }

    @Test
    public void getById() {
        Assert.assertNotNull(seatService.getById(1796L));
    }
}
