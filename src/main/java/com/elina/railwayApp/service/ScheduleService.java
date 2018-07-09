package com.elina.railwayApp.service;

import com.elina.railwayApp.DTO.ScheduleDTO;
import com.elina.railwayApp.DTO.SeatDTO;
import com.elina.railwayApp.DTO.SeatsDTO;
import com.elina.railwayApp.DTO.TransferScheduleDTO;
import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Seat;
import com.elina.railwayApp.model.Station;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public interface ScheduleService {

    void add(ScheduleDTO scheduleDTO) throws ParseException ;

    void delete(Schedule schedule);

    void update(Schedule schedule);

    List<Schedule> getAll();

    Schedule getById(Long id);

    List<Schedule> getByDate(Date dateDeparture);

    List<Schedule> getByDates(Date dateDeparture, Date dateArrival);

    List<Schedule> getByStationsAndDate(Schedule schedule);

    List<Schedule> getByDateAndTrainToCheckIntersection(Schedule schedule);

    List<Schedule> getByTrainAndDate(Schedule schedule);

    List<Schedule> getByStationArrivalAndDate(Schedule schedule);

    List<TransferScheduleDTO> getTransferSchedules(ScheduleDTO scheduleDTO) throws ParseException;

    boolean checkWorkingStation(Station station, Date date);

    List<ScheduleDTO> getDirectSchedulesFromDTOByStationsAndDatesAndTrain(ScheduleDTO scheduleDTO) throws ParseException;

    List<ScheduleDTO> getDirectSchedulesFromDTOByStations(ScheduleDTO scheduleDTO) throws ParseException;

    List<ScheduleDTO> getDirectSchedulesFromDTOByTrain(ScheduleDTO scheduleDTO) throws ParseException;

    List<ScheduleDTO> getDirectSchedulesFromDTOByDates(ScheduleDTO scheduleDTO) throws ParseException;

    List<SeatDTO> getFreeSeats(Schedule schedule);

    SeatsDTO getSeats(Long id);

}

