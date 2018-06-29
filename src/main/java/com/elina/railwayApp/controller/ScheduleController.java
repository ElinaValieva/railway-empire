package com.elina.railwayApp.controller;

import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.configuration.common.Views;
import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Station;
import com.elina.railwayApp.service.ScheduleService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@Log4j
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    /*
    GET ALL SCHEDULES
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = {URLs.GET_SCHEDULES}, method = RequestMethod.GET)
    public String getSchedules(Model model) {
        log.info("GET ALL SCHEDULES");
        List<Schedule> schedules = scheduleService.getAll();
        model.addAttribute("schedules", schedules);
        return Views.SCHEDULE;
    }

    /*
    ADD SCHEDULE WITH PARAMETERS:
    - TRAIN
    - DATA ARRIVAL/DEPARTMENT
    - STATION ARRIVAL/DEPARTMENT
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = {URLs.CREATE_SCHEDULE}, method = RequestMethod.GET)
    public String createStation(@ModelAttribute("schedule") Schedule schedule) {
        log.info("CREATE SCHEDULE");
        scheduleService.add(schedule);
        return Views.STATION;
    }

}
