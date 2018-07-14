package com.elina.railwayApp.service;

import com.elina.railwayApp.DAO.AuditDAO;
import com.elina.railwayApp.service.Implementation.AuditServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ChartServiceTest {

    private String dateFrom, dateTo;


    @InjectMocks
    private AuditServiceImpl auditService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void chartForPopularStations() {
    }

    @Test
    public void chartForUsersAge() {
    }

    @Test
    public void chartCntTicketsForDay() {
    }

    @Test
    public void chartGetProfit() {
    }
}