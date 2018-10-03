package com.elina.railwayApp.integrations;

import com.elina.railwayApp.configuration.*;
import com.elina.railwayApp.service.RoleService;
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
public class RoleServiceJTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void getRole(){
        Assert.assertNotNull(roleService.getRole());
    }
}
