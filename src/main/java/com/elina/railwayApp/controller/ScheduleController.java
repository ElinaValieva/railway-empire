package com.elina.railwayApp.controller;

import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.configuration.common.Utils;
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
import java.util.Date;
import java.util.List;
import java.util.Set;

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
     * 2. can't add wrong times in schedule (arrival < departure)
     * 3. can't add intersection of schedules
     * 4. can't add schedule for train which placed on another station!
     * 5. can't add duplicate schedule
     *
     * @param schedule with id, date arrival/departure, stations arrival/departure, train
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = {URLs.CREATE_SCHEDULE}, method = RequestMethod.POST)
    public String createSchedule(@ModelAttribute("schedule") Schedule schedule) throws ParseException {

        //TODO
        /*
        condition with train and station (train can't teleporting fast to another place
         */

        //for test
        String nameStationA = "station12";
        String nameStationB = "station67";
        String trainName = "train5";
        String dateD = "2018-06-30 18:55:00";
        String dateA = "2018-06-30 20:20:00";

        Date dateDeparture = Utils.parseToDateTime(dateD);
        Date dateArrival = Utils.parseToDateTime(dateA);
        log.info("CREATE SCHEDULE ...");
        Train train = trainService.getByName(trainName);
        Station stationArrival = stationService.getByName(nameStationA);
        Station stationDeparture = stationService.getByName(nameStationB);
        if (stationArrival != null && stationDeparture != null && train != null) {
            schedule.setStationArrival(stationArrival);
            schedule.setStationDeparture(stationDeparture);
            schedule.setTrain(train);
            schedule.setDateArrival(dateArrival);
            schedule.setDateDeparture(dateDeparture);
            scheduleService.add(schedule);
        } else log.warn("STATIONS and TRAIN NOT FOUNDS, WRONG PARAMETERS");
        return Views.SCHEDULE;
    }

    /**
     * get schedules by date departure
     */
    @RequestMapping(value = {URLs.GET_SCHEDULE_BY_DATE}, method = RequestMethod.GET)
    public String getByDate(Model model, String date) throws ParseException {
        //test
        date = "2018-06-29";

        log.info("GET ALL SCHEDULE BY DATE");
        Date dateDeparture = Utils.parseToDate(date);
        List<Schedule> schedules = scheduleService.getByDate(dateDeparture);
        model.addAttribute("schedules", schedules);
        return Views.SCHEDULE;
    }

    /**
     * get schedules by departure/arrival dates
     */
    @RequestMapping(value = {URLs.GET_SCHEDULE_BY_DATES}, method = RequestMethod.GET)
    public String getByDates(Model model, String dateDepartures, String dateArrivals) throws ParseException {
        //test
        dateDepartures = "2018-06-29";
        dateArrivals = "2018-06-29";

        log.info("GET ALL SCHEDULE BY DATES");
        Date dateDeparture = Utils.parseToDate(dateDepartures);
        Date dateArrival = Utils.parseToDate(dateArrivals);
        List<Schedule> schedules = scheduleService.getByDates(dateDeparture, dateArrival);
        model.addAttribute("schedules", schedules);
        return Views.SCHEDULE;
    }

    /**
     * get schedules by stations and date
     * only direct trip
     */
    @RequestMapping(value = {URLs.GET_SCHEDULE_DIRECT}, method = RequestMethod.GET)
    public String getDirectSchedules(Model model, String from, String to, String date) throws ParseException {
        //test
        date = "2018-06-29";
        Station stationDeparture = stationService.getByName("station1");
        Station stationArrival = stationService.getByName("station6");

        log.info("GET DIRECT SCHEDULES BY STATIONS");
        if (stationArrival != null && stationDeparture != null) {
            Schedule schedule = new Schedule();
            schedule.setStationDeparture(stationDeparture);
            schedule.setStationArrival(stationArrival);
            Date dateDeparture = Utils.parseToDate(date);
            schedule.setDateDeparture(dateDeparture);
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
        //test
        date = "2018-06-29";
        Schedule schedule = new Schedule();
        train = trainService.getByName("train1");

        log.info("GET DIRECT SCHEDULES BY TRAIN");
        if (train != null) {
            Date dateDeparture = Utils.parseToDate(date);
            schedule.setDateDeparture(dateDeparture);
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
    public String getTransferSchedules(Model model, String date, String from, String to) throws ParseException {
        //test
        Station stationA = stationService.getByName("station4");
        Station stationD = stationService.getByName("station1");
        date = "2018-06-29";

        log.info("GET TRANSFER SCHEDULE");
        Date dateDeparture = Utils.parseToDate(date);
        Set<List<Schedule>> schedules = scheduleService.getTransferSchedules(dateDeparture, stationD, stationA);
        model.addAttribute("schedules", schedules);
        return Views.SCHEDULE;
    }

    /*
    TODO
    delete and update schedule
     */

}
