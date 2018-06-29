package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Station;
import com.elina.railwayApp.model.Train;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;

@Repository
public interface ScheduleDAO {

    void add(Schedule schedule);

    void update(Schedule schedule);

    void delete(Schedule schedule);

    List<Schedule> getAll();

    Schedule getById(Long id);

    List<Schedule> getByDate(String date) throws ParseException;

    List<Schedule> getByStationAndDate(String date, Station stationArrival, Station stationDepartment);

    List<Schedule> getByDateAndTrain(Schedule schedule);
}
