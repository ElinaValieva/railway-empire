package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduleDAO {

    void add(Schedule schedule);

    void update(Schedule schedule);

    void delete(Schedule schedule);

    List<Schedule> getAll();

    List<Schedule> getByDateAll();

    Schedule getById(Long id);

    List<Schedule> getByDate(Date date);

    List<Schedule> getByDates(Date dateDeparture, Date dateArrival);

    List<Schedule> getByStationsAndDate(Schedule schedule);

    List<Schedule> getByStationsAndDates(Schedule schedule);

    List<Schedule> getByDateAndTrainToCheckIntersection(Schedule schedule);

    List<Schedule> getByTrainAndDate(Schedule schedule);

    List<Schedule> getByTrainAndDates(Schedule schedule);

    List<Schedule> getByStationArrivalAndDate(Schedule schedule);

    List<Schedule> getWorkingStation(Station station, Date date);

    List<Schedule> getByStationsAndDatesAndTrains(Schedule schedule);

    List<Schedule> getByStationArrivalAndDates(Station station, Date dateFrom, Date dateTo);
}
