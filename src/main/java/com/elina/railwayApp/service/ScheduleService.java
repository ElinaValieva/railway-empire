package com.elina.railwayApp.service;

import com.elina.railwayApp.DTO.ScheduleDTO;
import com.elina.railwayApp.DTO.SeatDTO;
import com.elina.railwayApp.DTO.SeatsDTO;
import com.elina.railwayApp.DTO.TransferScheduleDTO;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Seat;
import com.elina.railwayApp.model.Station;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

@Service
public interface ScheduleService {

    void add(ScheduleDTO scheduleDTO) throws ParseException, BusinessLogicException, IOException, TimeoutException;

    void delete(Long id) throws BusinessLogicException, IOException, TimeoutException;

    void update(ScheduleDTO scheduleDTO) throws BusinessLogicException, ParseException, IOException, TimeoutException;

    List<ScheduleDTO> getAll();

    List<ScheduleDTO> getAllForToday();

    Schedule getById(Long id);

    List<Schedule> getByDate(Date dateDeparture);

    List<Schedule> getByDates(Date dateDeparture, Date dateArrival) throws ParseException, BusinessLogicException;

    List<Schedule> getByStationsAndDate(Schedule schedule);

    List<Schedule> getByDateAndTrainToCheckIntersection(Schedule schedule);

    List<Schedule> getByTrainAndDate(Schedule schedule);

    List<Schedule> getByStationArrivalAndDate(Schedule schedule);

    List<TransferScheduleDTO> getTransferSchedules(ScheduleDTO scheduleDTO) throws ParseException, BusinessLogicException;

    boolean checkWorkingStation(Station station, Date date);

    List<ScheduleDTO> getDirectSchedulesFromDTOByStationsAndDatesAndTrain(ScheduleDTO scheduleDTO) throws ParseException, BusinessLogicException;

    List<ScheduleDTO> getDirectSchedulesFromDTOByStations(ScheduleDTO scheduleDTO) throws ParseException, BusinessLogicException;

    List<ScheduleDTO> getDirectSchedulesFromDTOByTrain(ScheduleDTO scheduleDTO) throws ParseException, BusinessLogicException;

    List<ScheduleDTO> getDirectSchedulesFromDTOByDates(ScheduleDTO scheduleDTO) throws ParseException, BusinessLogicException;

    List<SeatDTO> getFreeSeats(Schedule schedule);

    SeatsDTO getSeats(Long id) throws BusinessLogicException;

    List<Schedule> getByStationArrivalAndDates(Station station, Date dateFrom, Date dateTo);

}

