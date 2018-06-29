package com.elina.railwayApp.service;

import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Station;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {

    void add(Schedule schedule);

    void delete(Schedule schedule);

    void update(Schedule schedule);

    List<Schedule> getAll();

    Schedule getById(Long id);

    List<Schedule> getByDateArrival(String dateArrival);

    List<Schedule> getByStationAndDate(String date, Station stationArrival, Station stationDepartment);
}
