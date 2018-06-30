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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
     * conditionals:
     * 1. can't add same stations in schedule
     * 2. can't add wrong times in schedule (arrival > department)
     * 3. can't add intersection of schedules
     * 4. can't add schedule for train which placed on another station!
     *
     * @param schedule with id, date arrival/department, stations arrival/department, train
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = {URLs.CREATE_SCHEDULE}, method = RequestMethod.POST)
    public String createSchedule(@ModelAttribute("schedule") Schedule schedule) throws ParseException {

        //TODO
        /*
        condition with train and station (train can't teleporting fast to another place
         */

        //for test
        String nameStationA = "station1";
        String nameStationB = "station6";
        String trainName = "train1";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateArrival = format.parse("2018-06-30 15:55:00");
        Date dateDepartment = format.parse("2018-06-30 16:20:00");


        log.info("CREATE SCHEDULE ...");
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
            log.info("SCHEDULE WAS CREATED!");
        } else log.warn("STATIONS and TRAIN NOT FOUNDS, WRONG PARAMETERS");
        return Views.SCHEDULE;
    }

    /**
     * get schedules by date
     */
    @RequestMapping(value = {URLs.GET_SCHEDULE_BY_DATE_ARRIVAL}, method = RequestMethod.GET)
    public String getByDateArrival(Model model, String date) throws ParseException {
        //test
        date = "2018-06-29";

        log.info("GET ALL SCHEDULE BY DATE");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateArrival = format.parse(date);
        dateArrival.setTime(dateArrival.getTime() + (long) 1000 * 24 * 60 * 60);
        List<Schedule> schedules = scheduleService.getByDate(dateArrival);
        model.addAttribute("schedules", schedules);
        return Views.SCHEDULE;
    }

    /**
     * get schedules by stations and date
     * only direct trip
     */
    @RequestMapping(value = {URLs.GET_SCHEDULE_DIRECT}, method = RequestMethod.GET)
    public String getDirectSchedules(Model model, Station stationArrival, Station stationDepartment, String date) throws ParseException {
        log.info("GET DIRECT SCHEDULES BY STATIONS");

        //test
        date = "2018-06-29";
        stationArrival = stationService.getByName("station1");
        stationDepartment = stationService.getByName("station6");


        if (stationArrival != null && stationDepartment != null) {
            Schedule schedule = new Schedule();
            schedule.setStationDepartment(stationDepartment);
            schedule.setStationArrival(stationArrival);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dateArrival = format.parse(date);
            dateArrival.setTime(dateArrival.getTime() + (long) 1000 * 24 * 60 * 60);
            schedule.setDateArrival(dateArrival);
            List<Schedule> schedules = scheduleService.getByStationsAndDate(schedule);
            model.addAttribute("schedules", schedules);
            log.info("FOUND SCHEDULES BY STATIONS AND DATE");
        } else log.warn("CAN'T FOUND SCHEDULES BY STATIONS AND DATE. WRONG PARAMETERS");
        return Views.SCHEDULE;
    }

    /**
     * get schedules by train and date
     * only direct trip
     */
    @RequestMapping(value = {URLs.GET_SCHEDULE_BY_TRAIN}, method = RequestMethod.GET)
    public String getScheduleByTrain(Model model, String date, Train train) throws ParseException {
        log.info("GET DIRECT SCHEDULES BY TRAIN");
        //test
        date = "2018-06-29";
        Schedule schedule = new Schedule();
        train = trainService.getByName("train1");

        if (train != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dateArrival = format.parse(date);
            dateArrival.setTime(dateArrival.getTime() + (long) 1000 * 24 * 60 * 60);
            schedule.setDateArrival(dateArrival);
            schedule.setTrain(train);
            List<Schedule> schedules = scheduleService.getByTrainAndDate(schedule);
            model.addAttribute("schedules", schedules);
            log.info("FOUND SCHEDULES BY TRAIN AND DATE");
        } else log.warn("CAN'T FOUND SCHEDULES BY TRAIN AND DATE. WRONG PARAMETERS");
        return Views.SCHEDULE;
    }

    /**
     * get schedules by stations and date
     * with transfer
     */
    @RequestMapping(value = {URLs.GET_SCHEDULE_TRANSFER}, method = RequestMethod.GET)
    public String getTransferSchedules(Model model, String dateArrival) {
        log.info("GET TRANSFER SCHEDULE");
        //TODO
        return Views.SCHEDULE;
    }
}
