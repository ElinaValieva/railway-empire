package com.elina.railwayApp.controller;

import com.elina.railwayApp.DTO.ScheduleDTO;
import com.elina.railwayApp.DTO.SeatsDTO;
import com.elina.railwayApp.DTO.TicketDTO;
import com.elina.railwayApp.DTO.TransferScheduleDTO;
import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.model.User;
import com.elina.railwayApp.service.ScheduleService;
import com.elina.railwayApp.service.TicketService;
import com.elina.railwayApp.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = URLs.SCHEDULE)
@Log4j
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    /**
     * get all schedules
     *
     * @return all schedules
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping(URLs.GET_SCHEDULES)
    public ResponseEntity<?> getSchedules() {
        List<ScheduleDTO> schedules = scheduleService.getAll();
        return ResponseEntity.ok(schedules);
    }

    /**
     * add schedule
     * conditionals:
     * 1. can't add same stations in schedule
     * 2. can't add wrong times in schedule (arrival < departure)
     * 3. can't add intersection of schedules
     * 4. can't add schedule for train which placed on another station! //TODO
     * 5. can't add duplicate schedule
     * 6. can't add schedule for current day or earlier day
     *
     * @param scheduleDTO with id, date arrival/departure, stations arrival/departure, train
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PutMapping(URLs.CREATE_SCHEDULE)
    public void createSchedule(@RequestBody ScheduleDTO scheduleDTO) throws ParseException, BusinessLogicException {
        scheduleService.add(scheduleDTO);
    }

    /**
     * get schedules by all parameters: stations, dates and train
     * only direct trip
     */
    @PostMapping(URLs.GET_SCHEDULE_DIRECT)
    public ResponseEntity<?> getDirectSchedulesByStationsAndDatesAndTrain(@RequestBody ScheduleDTO scheduleDTO) throws ParseException, BusinessLogicException {
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
    public ResponseEntity<?> getDirectSchedulesByStationsAndDate(@RequestBody ScheduleDTO scheduleDTO) throws ParseException, BusinessLogicException {
        List<ScheduleDTO> scheduleDTOList = scheduleService.getDirectSchedulesFromDTOByStations(scheduleDTO);
        return ResponseEntity.ok(scheduleDTOList);
    }

    /**
     * get schedules by train and date
     * only direct trip
     */
    @PostMapping(URLs.GET_SCHEDULE_DIRECT_BY_TRAIN)
    public ResponseEntity<?> getDirectSchedulesByTrainAndDate(@RequestBody ScheduleDTO scheduleDTO) throws ParseException, BusinessLogicException {
        List<ScheduleDTO> scheduleDTOList = scheduleService.getDirectSchedulesFromDTOByTrain(scheduleDTO);
        return ResponseEntity.ok(scheduleDTOList);
    }

    /**
     * get schedules by stations and date
     * with transfer
     */
    @PostMapping(URLs.GET_SCHEDULE_TRANSFER_BY_STATIONS)
    public ResponseEntity<?> getTransferSchedules(@RequestBody ScheduleDTO scheduleDTO) throws ParseException {
        List<TransferScheduleDTO> schedules = scheduleService.getTransferSchedules(scheduleDTO);
        return ResponseEntity.ok(schedules);
    }

    /**
     * get seats info: booking seats, cntCarriages
     *
     * @param id
     * @return
     */
    @GetMapping(URLs.GET_SEATS_INFO_OF_DIRECT_TRIP)
    public ResponseEntity<?> getSeats(@PathVariable Long id) throws BusinessLogicException {
        SeatsDTO seatsDTO = scheduleService.getSeats(id);
        return ResponseEntity.ok(seatsDTO);
    }

    /**
     * add ticket
     * conditionals:
     * 1. schedule is available
     * 2. ticket not booked yet
     * 3. user with same name and date not registered on same train
     *
     * @param ticketDTO
     * @return
     */
    @PutMapping(URLs.BOOK_TICKET_OF_DIRECT_TRIP)
    public void bookTicket(@RequestBody TicketDTO ticketDTO) throws BusinessLogicException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByEmail(userName);
        ticketService.add(ticketDTO, user);
    }

    /**
     * remove schedule if for this schedule did't bought tickets
     *
     * @param id
     * @throws BusinessLogicException
     */
    @DeleteMapping(URLs.DELETE_SCHEDULE)
    public void deleteSchedule(@PathVariable Long id) throws BusinessLogicException {
        scheduleService.delete(id);
    }

    /**
     * update schedule if
     * * conditionals:
     * * 1. can't add same stations in schedule
     * * 2. can't add wrong times in schedule (arrival < departure)
     * * 3. can't add intersection of schedules
     * * 4. can't add schedule for train which placed on another station! //TODO
     * * 5. can't add duplicate schedule
     * * 6. can't add schedule for current day or earlier day
     * * 7. nobody bought ticket for this schedule
     *
     * @param scheduleDTO
     * @throws ParseException
     * @throws BusinessLogicException
     */

    @PutMapping(URLs.UPDATE_SCHEDULE)
    public void updateSchedule(@RequestBody ScheduleDTO scheduleDTO) throws ParseException, BusinessLogicException {
        scheduleService.update(scheduleDTO);
    }
}
