package com.elina.railwayApp.controller;

import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.configuration.common.Views;
import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Station;
import com.elina.railwayApp.model.Train;
import com.elina.railwayApp.service.ScheduleService;
import com.elina.railwayApp.service.StationService;
import com.elina.railwayApp.service.TrainService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@Log4j
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private StationService stationService;

    @Autowired
    private TrainService trainService;

    /**
     * get all schedules
     *
     * @return all schedules
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = {URLs.GET_SCHEDULES}, method = RequestMethod.GET)
    public String getSchedules(Model model) {
        log.info("GET ALL SCHEDULES");
        List<Schedule> schedules = scheduleService.getAll();
        model.addAttribute("schedules", schedules);
        return Views.SCHEDULE;
    }

    /**
     * add schedule
     *
     * @param schedule with id, date arrival/department, stations arrival/department, train
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = {URLs.CREATE_SCHEDULE}, method = RequestMethod.POST)
    public String createSchedule(@ModelAttribute("schedule") Schedule schedule) {

        //TODO
        /*
        condition with train that schedule with this train not exist with same dates
        condition with times timeA < timeB
        condition with train and station (train can't teleporting fast to another place
         */
        log.info("CREATE SCHEDULE");
        schedule = new Schedule();
        String nameStationA = "station1";
        String nameStationB = "station6";
        String trainName = "train1";
        String dateArrival = "2018-06-29 18:15:00";
        String dateDepartment = "2018-06-29 19:15:00";
        Train train = trainService.getByName(trainName);
        Station stationArrival = stationService.getByName(nameStationA);
        Station stationDepartment = stationService.getByName(nameStationB);
        if (stationArrival != null && stationDepartment != null && train != null) {
            schedule.setStationArrival(stationArrival);
            schedule.setStationDepartment(stationDepartment);
            schedule.setTrain(train);
            schedule.setDateArrival(dateArrival);
            schedule.setDateDepartment(dateDepartment);
            scheduleService.add(schedule);
        } else log.warn("STATIONS and TRAIN NOT FOUNDS, WRONG PARAMETERS");
        return Views.SCHEDULE;
    }

    /**
     * get schedule by date
     */
    @RequestMapping(value = {URLs.GET_SCHEDULE_BY_DATE_ARRIVAL}, method = RequestMethod.GET)
    public String getByDateArrival(Model model, String dateArrival) {
        log.info("GET ALL SCHEDULE BY DATE");
        List<Schedule> schedules = scheduleService.getByDateArrival(dateArrival);
        model.addAttribute("schedules", schedules);
        return Views.SCHEDULE;
    }

    /**
     * get schedule by stations and date
     * only direct trip
     */
    @RequestMapping(value = {URLs.GET_SCHEDULE_DIRECT}, method = RequestMethod.GET)
    public String getDirectSchedules(Model model, String date, Station stationArrival, Station stationDepartment) {
        log.info("GET DIRECT SCHEDULE");
        //TODO
        return Views.SCHEDULE;
    }

    /**
     * get schedule by stations and date
     * with transfer
     */
    @RequestMapping(value = {URLs.GET_SCHEDULE_TRANSFER}, method = RequestMethod.GET)
    public String getTransferSchedules(Model model, String dateArrival) {
        log.info("GET TRANSFER SCHEDULE");
        //TODO
        return Views.SCHEDULE;
    }
}
