package com.elina.railwayApp.controller;

import com.elina.railwayApp.DTO.ScheduleDTO;
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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = URLs.SCHEDULE)
@Log4j
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private StationService stationService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private ModelMapper modelMapper;

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
     * get schedules by all parameters: stations, dates and train
     * only direct trip
     */
    @PostMapping(URLs.GET_SCHEDULE_DIRECT)
    public ResponseEntity<?> getDirectSchedulesByStationsAndDatesAndTrain(@RequestBody ScheduleDTO scheduleDTO) throws ParseException {
        List<ScheduleDTO> scheduleDTOList = scheduleService.getDirectSchedulesFromDTOByStationsAndDatesAndTrain(scheduleDTO);
        return ResponseEntity.ok(scheduleDTOList);
    }

    /**
     * get schedules by dates
     * if dateArrival empty return schedule for dateDeparture
     * only direct trip
     */
    @PostMapping(URLs.GET_SCHEDULE_DIRECT_BY_DATES)
    public ResponseEntity<?> getDirectSchedulesByDates(@RequestBody ScheduleDTO scheduleDTO) throws ParseException {
        List<ScheduleDTO> scheduleDTOList = scheduleService.getDirectSchedulesFromDTOByDates(scheduleDTO);
        return ResponseEntity.ok(scheduleDTOList);
    }

    /**
     * get schedules by stations and date
     * only direct trip
     */
    @PostMapping(URLs.GET_SCHEDULE_DIRECT_BY_STATIONS)
    public ResponseEntity<?> getDirectSchedulesByStationsAndDate(@RequestBody ScheduleDTO scheduleDTO) throws ParseException {
        List<ScheduleDTO> scheduleDTOList = scheduleService.getDirectSchedulesFromDTOByStations(scheduleDTO);
        return ResponseEntity.ok(scheduleDTOList);
    }

    /**
     * get schedules by train and date
     * only direct trip
     */
    @PostMapping(URLs.GET_SCHEDULE_DIRECT_BY_TRAIN)
    public ResponseEntity<?> getDirectSchedulesByTrainAndDate(@RequestBody ScheduleDTO scheduleDTO) throws ParseException {
        List<ScheduleDTO> scheduleDTOList = scheduleService.getDirectSchedulesFromDTOByTrain(scheduleDTO);
        return ResponseEntity.ok(scheduleDTOList);
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
