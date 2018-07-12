package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.service.ScheduleService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

public class DistanceServiceImplTest {

    @Autowired
    private ScheduleService scheduleService;

    @Before
    public void init(){
        Schedule schedule = scheduleService.getById((long) 3);
        calculateDateArrival(schedule);
    }
    @Test
    public Date calculateDateArrival(Schedule schedule) {
        System.out.println(calculateDateArrival(schedule));
        return calculateDateArrival(schedule);
    }
}